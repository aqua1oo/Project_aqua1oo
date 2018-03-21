var Common_GiGAGenie = new Common_GiGAGenie();
function Common_GiGAGenie()
{
	
	/**
	 * Common_GiGAGenie 초기화
	 */
	this.init = function()
	{
		options={};
		options.apikey="RTUwMDEyMjB8R0JPWERFVk18MTUyMDQ2ODM3OTk1OA=="; // api key given from developer portal
		options.keytype="GBOXDEVM"; // 개발자모드를 설정하고 사용하세요.
	    //options.keytype="GBOXCOMM"; // 개발자센터에서 승인이 되어야 사용하실 수 있습니다.

		gigagenie.init(options,function(result_cd,result_msg,extra){
			if(result_cd===200){
				status='IS';
				console.log('Initialize Success');
				Common_GiGAGenie.startGiGAGenie();
			};
		});
	}
	
	/**
	 * 음성인식을 시작해 text로 변환
	 */
	this.startGiGAGenie = function()
	{
		var options={};
		options.voicemsg='음성인식을 시작합니다';	
		var command = "";
		
		gigagenie.voice.getVoiceText(options,function(result_cd,result_msg,extra){	
			
			if(result_cd===200){
				command = extra.voicetext;
				alert(command);
				Common_GiGAGenie.checkVoiceText(command);	
			} else {
				alert('다시해보세요');
			}
		});	
		
	}
	
	/**
	 * text를 음성으로 변환
	 */
	this.sendTTS = function(text) 
	{
		var options={};
		options.ttstext=text;
		gigagenie.voice.sendTTS(options,function(result_cd,result_msg,extra){
		    if(result_cd===200){	    			    
		        //do next action
		    } else {
		        //extra.reason 에 voice 오류 전달.
		    };
		    setTimeout('Common_GiGAGenie.startGiGAGenie()',2000);
		});
	}
	
	/**
	 * 음성인식을 중단
	 */
	this.stopTTS = function() 
	{
		alert("음성인식 중단 요청");
		var options={};
		gigagenie.voice.stopTTS(options,function(result_cd, result_msg, extra) {
			if (result_cd==200) {
				alert("음성인식 중단 성공");
			} else if (result_cd==404) {
				alert("음성인식 실행 중이 아님");
			} else {
				alert("음성인식 중단 실패: " + result_msg);
			}
		});
	}
	
	/**
	 * text화 된 명령을 분석 후 코드화
	 */
	this.checkVoiceText = function(command) 
	{
		var text;
		
		if($.inArray(command,stop_array) != -1){
			Common_GiGAGenie.stopTTS();
		}else{	
			
			var temp;
			var code;
			var comTemp;
			var com;
			
			for(var i = 0; i < command.length; i++){
				temp = command.substring(0,i+1);
				code = $.inArray(temp, ah_array);						
				comTemp = command.substring(i+1,command.length).replace(/(\s*)/g, "");
				com = $.inArray(comTemp, command_array);
				if(code != -1)break;
			}

			if(code != -1){
				Common_GiGAGenie.sendControllCode(code, com); 
			}else{
				text = "해당 기기는[P2]지원되지 않습니다[P2]다른 기기로[P2]시도하여 주십시오";
				Common_GiGAGenie.sendTTS(text);				
			}    
		}	
	}
	
	this.sendControllCode = function(kind_code, command_code)
	{
		var url;
		
		//controll_code => 0:냉장고, 1:에어컨, 2:세탁기, 3:공기청정기 
		switch(kind_code){
			case 0 : url = "http://14.63.165.164:5011/Project_Gov/egovControllCode.do";
			break;
			case 1 : url = "http://14.63.165.164:5011/Project_Gov/egovControllCode.do";
			break;
			case 2 : url = "http://14.63.165.164:5011/Project_Gov/egovControllCode.do";
			break;
			case 3 : url = "http://14.63.165.164:5011/Project_Gov/egovControllCode.do";
			break;		
		}
		
		$.ajax({
	        type : "GET",        
	        url : url, //각 가전의 서버			            
	        data : {kind_code : kind_code, command_code : command_code},
	        dataType : "jsonp", 			            			            
	        success : function(data){
	        	var kind_name = ah_array[data.kind_code];
	        	var text = "";
	        	
	        	if(data.gubun == 0){
	        		text = kind_name + '[P2]제어에[P2] 성공하였습니다!!';
	        		Common_GiGAGenie.sendTTS(text);
	        	}else if(data.gubun == 1){
	        		text = kind_name + '[P2]제어에[P2] 실패하였습니다!!';
	        		Common_GiGAGenie.sendTTS(text);
	        	}else{
	        		setTimeout('Common_GiGAGenie.startGiGAGenie()',2000);
	        	}
	        },
	        error:function(request,status,error){
	        	alert('code:'+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);			            
	        }
	    });	
	}
	
}