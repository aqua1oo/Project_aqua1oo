/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.egov.mirrorLink.controll;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.property.EgovPropertyService;


@Controller
public class EgovMirrorLinkController {

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	/**
	 * 정부사업 MirrorLinkHtml을 다운하기위한 페이지로 이동한다.
	 * @param model
	 * @return "mirrorLink/egovMirrorLinkHSML"
	 * @exception Exception
	 */
	@RequestMapping(value = "/egovMirrorLinkHSML.do", method = RequestMethod.GET)
	public String goEgovMirrorLinkHSML(Model model) throws Exception {
		
		return "mirrorLink/egovMirrorLinkHSML";
	}
	
	/**
	 * 정부사업 egovMirrorLinkHSML을 다운한다.
	 * @param model
	 * @exception Exception
	 */
	@RequestMapping(value = "/egovMirrorLinkHSMLDown.do")
	public void getEgovMirrorLinkHSMLDown(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		  String dFile = "HSML Reference Development Document.zip";
		  
		  String upDir = "/data2/svc/thingseye/was/apache-tomcat-8.5.28/apache-tomcat-8.5.28/webapps/Project_Gov/data2/svc/";
		  String path = upDir+File.separator+dFile;
		  
		  File file = new File(path);

		  String userAgent = request.getHeader("User-Agent"); //버전 확인 
		  
		  boolean ie = userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("rv:11") > -1;
		  String fileName = null;
		   
		  if (ie) {
		   fileName = URLEncoder.encode(file.getName(), "utf-8");
		  } else {
		   fileName = new String(file.getName().getBytes("utf-8"),"iso-8859-1");
		  }
		  
		  response.setContentType("application/octet-stream");
		  response.setHeader("Content-Disposition","attachment;filename=\"" +fileName+"\";");
		  
		  FileInputStream fis=new FileInputStream(file);
		  BufferedInputStream bis=new BufferedInputStream(fis);
		  ServletOutputStream so=response.getOutputStream();
		  BufferedOutputStream bos=new BufferedOutputStream(so);
		  
		  byte[] data=new byte[2048];
		  int input=0;
		  while((input=bis.read(data))!=-1){
		   bos.write(data,0,input);
		   bos.flush();
		  }
		  
		  if(bos!=null) bos.close();
		  if(bis!=null) bis.close();
		  if(so!=null) so.close();
		  if(fis!=null) fis.close();
	}
	
	/**
	 * 정부사업 MirrorLinkRAS을 다운하기위한 페이지로 이동한다.
	 * @param model
	 * @return "mirrorLink/egovMirrorLinkRAS"
	 * @exception Exception
	 */
	@RequestMapping(value = "/egovMirrorLinkRAS.do", method = RequestMethod.GET)
	public String MirrorLinkRAS(Model model) throws Exception {
		
		return "mirrorLink/egovMirrorLinkRAS";
	}
	
	/**
	 * 정부사업 egovMirrorLinkRAS을 다운한다.
	 * @param model
	 * @exception Exception
	 */
	@RequestMapping(value = "/egovMirrorLinkRASDown.do")
	public void getEgovMirrorLinkRASDown(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		  String dFile = "Reference Android Source.zip";
		  
		  String upDir = "/data2/svc/thingseye/was/apache-tomcat-8.5.28/apache-tomcat-8.5.28/webapps/Project_Gov/data2/svc/";
		  String path = upDir+File.separator+dFile;
		  
		  File file = new File(path);

		  String userAgent = request.getHeader("User-Agent");
		  boolean ie = userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("rv:11") > -1;
		  String fileName = null;
		   
		  if (ie) {
		   fileName = URLEncoder.encode(file.getName(), "utf-8");
		  } else {
		   fileName = new String(file.getName().getBytes("utf-8"),"iso-8859-1");
		  }
		  
		  response.setContentType("application/octet-stream");
		  response.setHeader("Content-Disposition","attachment;filename=\"" +fileName+"\";");
		  
		  FileInputStream fis=new FileInputStream(file);
		  BufferedInputStream bis=new BufferedInputStream(fis);
		  ServletOutputStream so=response.getOutputStream();
		  BufferedOutputStream bos=new BufferedOutputStream(so);
		  
		  byte[] data=new byte[2048];
		  int input=0;
		  while((input=bis.read(data))!=-1){
		   bos.write(data,0,input);
		   bos.flush();
		  }
		  
		  if(bos!=null) bos.close();
		  if(bis!=null) bis.close();
		  if(so!=null) so.close();
		  if(fis!=null) fis.close();
	}
}

