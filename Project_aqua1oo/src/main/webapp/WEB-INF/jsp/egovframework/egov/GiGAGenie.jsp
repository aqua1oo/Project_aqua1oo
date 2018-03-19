<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><meta http-equiv="Content-Type" Content="text/html; charset=utf-8" />
<meta name="viewport" content="height=1080, width=1920, user-scalable=no" />
<style type ="text/css">
.btn {
    font-size: 30px;
    font-weight: bold;
}
</style>

<script type="text/javascript" src="https://svcapi.gigagenie.ai/sdk/v1.0/js/gigagenie.js"></script>
<script type="text/javascript" src="/js/egov/ninenine/comapi.js"></script>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript">
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
		var controll_code;
		if(result_cd===200){
			controll_code = extra.voicetext;
			alert(controll_code);			 
			if(controll_code === '그만' || controll_code === '그 만'){
				stopTTS();
			}else{
				$(document).ready(function(){
			        $.ajax({
			            type : "GET",
			            url : "/Project_Gov/egovControllCode.do", //각 가전의 서버
			            //url : "http://14.63.165.164:5011/Project_Gov/egovControllCode.do", //각 가전의 서버			            
			            data : controll_code,
			            dataType : "text", //test : json, 운영 : jsonp			            
			            success : function(data){
			                alert('제어에 성공하였습니다!!' + data.gubun);
			            },
			            error:function(request,status,error){   
			            	alert('code:'+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);			            
			            }
			        });
			    });
				//startGiGAGenie();
			}			
		} else {
			alert('다시해보세요');
			//startGiGAGenie();
		}
		//startNineNine();
	});
};

function onVoiceCommand(){
	var options={};

	gigagenie.voice.getVoiceText(options,function(result_cd,result_msg,extra){
		if(result_cd===200){
			alert('연결 되었습니다.');
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
		} else if (result_cd==404) {
			alert("음성인식 실행 중이 아님");
		} else {
			alert("음성인식 중단 실패: " + result_msg);
		}
	});
}
</script>
</head>
<body>
<br><br>
<h1><font color="white">기가지니에게 물어보세요</font></h1>
<button onclick="startGiGAGenie()">ddddd</button>
</body>
</html>