<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>ECU Reference Development Kit</title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egov/boot/bootstrap.css'/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egov/style/style.css'/>"/>
    <script type="text/javaScript" language="javascript" defer="defer">

    </script>
</head>

<body>
    <nav class="navbar navbar-default" role="navigation">
    	<div class="container">
    		<div class="navbar-header">
    			    		
    		</div>
    		<ul class="nav navbar-nav navbar-right">
    			<li>
    				<h3><font style="color:#5bc0de">ECU</font>  <font style="color:#ff8000">R</font>eference  <font style="color:#ff8000">D</font>evelopment  <font style="color:#ff8000">K</font>it</h3>
    			</li>
    		</ul>
    	</div>
    </nav>
    <div class="container text-center" id="home"> 
    		<br />
    		<br />
    		<h2>API Guide</h2>
    		<br />
    		<br />
    		<br />
    		<div class="row ">
    			<div class="col-md-6 body-main">
	    			<a href="http://14.63.165.164:5011/Project_Gov/html/egov/index.html">
	    				<img src="images/egov/mirrorlink.png"/>
	    		    </a>
	    			<br />
    		        <br />
    		        <br />
    		        <a class="btn btn-info btn-lg" href="http://14.63.165.164:5011/Project_Gov/egovMirrorLinkHSML.do">MirrorLink Client v1.2 HSML <br />Reference Development Document Download</a>
    		        <br />
    		        <br />
    		        <br />
    		        <a class="btn btn-info btn-lg" href="http://14.63.165.164:5011/Project_Gov/egovMirrorLinkRAS.do">MirrorLink Client v1.2 Reference <br />Android Source Download</a>
    			</div>  
    			<div class="col-md-6 body-main"> 
	    			<a href="http://14.63.165.164:5011/Project_Gov/egovAutoSar.do">
	    				<img src="images/egov/autosar.png"/>
	    			</a>
	    			<br />
    		        <br />
    		        <br />
    		        <a class="btn btn-info btn-lg" href="http://14.63.165.164:5011/Project_Gov/egovAutoSar.do">Air Conditioner  Reference Autosar <br />Porting Guide Document Download</a>
    			</div>    			
    		</div>    		 		
    	</div>
    	<nav class="navbar navbar-inverse navbar-fixed-bottom">
    		<div class="for-full-back" id="footer">
    		Copyright  © 2018 Ubivelox Mobile Inc. | All Right Reserved
    		</div> 
    	</nav>
    	
    	<script type="text/javascript" src="/js/egov/boot/bootstrap.js"></script>
        <script type="text/javascript" src="/js/egov/jquery/jquery-1.10.2.min.js"></script>
</body>
</html>
