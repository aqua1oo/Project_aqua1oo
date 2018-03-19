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
package egovframework.egov.autoSar.controll;

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
public class EgovAutoSarController {

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	/**
	 * 정부사업 autoSarFile을 다운하기위한 페이지로 이동한다.
	 * @param model
	 * @return "autoSar/egovAutoSar"
	 * @exception Exception
	 */
	@RequestMapping(value = "/egovAutoSar.do", method = RequestMethod.GET)
	public String goEgovAutoSar(Model model) throws Exception {
		
		return "autoSar/egovAutoSar";
	}
	
	/**
	 * 정부사업 autoSarFile을 다운한다.
	 * @param model
	 * @exception Exception
	 */
	@RequestMapping(value = "/egovAutoSarDown.do")
	public void getEgovAutoSarDown(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		  String dFile = "Air Conditioner  Reference Autosar Porting Guide Document.zip";
		  //local
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

