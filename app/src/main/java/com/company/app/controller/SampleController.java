package com.company.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.app.entity.Sample;
import com.company.app.service.SampleService;

@Controller
@RequestMapping("/sample")
public class SampleController {
	//Entity => Repository => Service => Controller
	private final SampleService sampleService;

	public SampleController(SampleService sampleService) {	//서비스 생성자 주입
		this.sampleService = sampleService;
	}
	
	//@GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping => @RequestMapping
	//매핑 어노테이션으로 @매핑어노테이션("url경로") 형식으로 기술하고, 그 아래에 메서드를 구성하면, 해당 주소 요청시 아래 메서드가 실행됨
	//Model(모델): 전달 저장소 클래스
	//Model.addAttribute("저장소명", 저장할값또는식)
	
	@GetMapping("/list")				//localhost:8081/sample/list
	public String getAllSamples(Model model) {
		model.addAttribute("samples", sampleService.getAllSamples());
		return "sample-list";
	}
	
	//@PathVariable: 매핑주소의 경로 상에 매개변수가 있는 경우 활용
	@GetMapping("/detail/{no}")
	public String getSampleById(@PathVariable("no") Long no, Model model) {
		model.addAttribute("sample", sampleService.getSampleByNo(no));
		return "sample-detail";
	}
	
	@GetMapping("/ins")				//localhost:8081/sample/ins
	public String getInsForm(Model model) {
		model.addAttribute("sample", new Sample());
		return "sample-form";
	}
	
	//@ModelAttribute: 객체로 받을 경우 활용되는 어노테이션
	@PostMapping("/save")
	public String saveSample(@ModelAttribute Sample sample) {
		sampleService.saveSample(sample);
		return "redirect:/sample/list";
	}
	
	@GetMapping("/edit/{no}")
	public String editForm(@PathVariable("no") Long no, Model model) {
		model.addAttribute("sample", sampleService.getSampleByNo(no));
		return "sample-edit";
	}
	
	@PostMapping("/update/{no}")
	public String updateSample(@PathVariable("no") Long no, 
			@ModelAttribute Sample sample) {
		sample.setNo(no);
		sampleService.updateSample(sample);
		return "redirect:/sample/list";
	}
	
	@GetMapping("/delete/{no}")
	public String deleteSample(@PathVariable("no") Long no) {
		sampleService.deleteSample(no);
		return "redirect:/sample/list";
	}
}
