var apistatus=0;
var options={};
var ws;
var status='NI';
var appid;
var containerid;
var wsstate=0;
var initstate=0;
var authcode;

$(document).ready(function(){
	init();
});

function init(){
	options={};
	options.apikey="RTUwMDEyMjB8R0JPWERFVk18MTUyMDQ2ODM3OTk1OA=="; // api key given from developer portal
	options.keytype="GBOXDEVM"; // 개발자모드를 설정하고 사용하세요.
    //options.keytype="GBOXCOMM"; // 개발자센터에서 승인이 되어야 사용하실 수 있습니다.

	gigagenie.init(options,function(result_cd,result_msg,extra){
		if(result_cd===200){
			status='IS';
			console.log('Initialize Success');
			startGiGAGenie();
		};
	});

}

function startGiGAGenie(){
	var options={};
	options.voicemsg='음성인식을 시작합니다.';
	
	gigagenie.voice.getVoiceText(options,function(result_cd,result_msg,extra){
		var voiceText;
		if(result_cd===200){
			voiceText = extra.voicetext;
			alert(extra.voicetext);			 
			if(voiceText === '그만'){
				stopTTS();
			}else{
				$(document).ready(function(){
			        $.ajax({
			            type : "GET",
			            url : "http://14.63.165.164:5011/Project_Gov/",//각 가전의 서버
			            data : voiceText,
			            dataType : "text",
			            error : function(){
			                alert('통신실패!!');
			            },
			            success : function(data){
			                alert("통신데이터 값 : " + data);
			            }
			        });
			    });
				startGiGAGenie();
			}			
		} else {
			alert("다시해보세요");
			startGiGAGenie();
		}
		//startNineNine();
	});
};

function onVoiceCommand(){
	var options={};

	gigagenie.voice.getVoiceText(options,function(result_cd,result_msg,extra){
		if(result_cd===200){
			alert("연결 되었습니다.");
			//stopTTS();
		} else {
			alert("다시해보세요");
			startNineNine();
		}

	});
};

function stopTTS() {
	alert("음성인식 중단 요청");
	var options={};
	gigagenie.voice.stopTTS(options,function(result_cd, result_msg, extra) {
		if (result_cd==200) {
			alert("음성인식 중단 성공");
		}
		else if (result_cd==404) {
			alert("음성인식 실행 중이 아님");
		}
		else {
			alert("음성인식 중단 실패: " + result_msg);
		}
	});
}