package com.auto.myte.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ScrollPagingationController {


	/**
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/scroll")
	public String scroll(Model model) throws Exception {

		return "scroll";
	}


//	/**
//	 * @param form
//	 * @param result
//	 * @return
//	 */
//	@RequestMapping(value = "/content")
//	public String content(Model model) throws Exception {
//
//		return "content";
//	}
}
