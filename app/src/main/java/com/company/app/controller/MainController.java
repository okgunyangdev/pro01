package com.company.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.company.app.service.MainService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final MainService mainService;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("products", mainService.getLatestProducts());
		model.addAttribute("boards", mainService.getLatestBoards());
		model.addAttribute("qnas", mainService.getLatestQnas());
		return "index";
	}
	
	
}