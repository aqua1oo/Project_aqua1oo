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
package egovframework.egov.common.controll;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class Common_GiGAGenie {

	/**
	 * 가전제어 command를 받는다.
	 * @param model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping(value ="/egovControllCode.do")
	public ModelAndView getControllCode(HttpServletRequest request, Locale locale, Model model, @RequestParam HashMap<String, String> param)
	{		
		System.out.println("=========+>>>>>> /egovControllCode.do");
				
		String kind_code = request.getParameter("kind_code") == null ? "" : request.getParameter("kind_code");		
		
		System.out.println("kind_code ====> " + kind_code);

		ModelAndView MView = new ModelAndView("jsonView");
		
		//동작 완료 구분 0:제어성공, 1:제어실패
		String resultGubun = "1";
		
		try {

			//명령 처리 로직
			/*if(){
				
			}else{
				
			}*/
			resultGubun = "0";

			MView.addObject("kind_code", kind_code);
			MView.addObject("gubun", resultGubun);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return MView;
	}

}
