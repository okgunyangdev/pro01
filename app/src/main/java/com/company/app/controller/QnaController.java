package com.company.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.app.entity.Member;
import com.company.app.entity.Qna;
import com.company.app.service.QnaService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qna")
@CrossOrigin(origins = "*")   //CORS 허용
public class QnaController {

	private final QnaService qnaService;
	
	@GetMapping("/list")	//전체 글 목록
	public String getAllQna(Model model) {
		model.addAttribute("qnas", qnaService.getAllQna());
		return "qna/list";
	}
	
	@GetMapping("/detail/{no}")		//답글 상세보기
	public String getQna(@PathVariable("no") Long no, Model model, HttpServletRequest req, HttpServletResponse res) {
		
		Cookie[] cookies = req.getCookies();	//사용자 컴퓨터의 쿠키 선언
		boolean isViewed = false;
		String cookieName = "qna_view_" + no;
		
		if(cookies != null) {	//사용자 컴퓨터의 쿠키를 순회하여 조회
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals(cookieName)) {
					isViewed = true;
					break;
				}
			}
		}
		
		//조회한 적 없다고 판단된 경우 조회수 증가 및 쿠키 추가
		if(!isViewed) {
			qnaService.increaseHits(no);
			Cookie newCookie = new Cookie(cookieName, "viewed");	//쿠키 생성
			newCookie.setMaxAge(60 * 60 * 24);	//24시간
			newCookie.setPath("/");
			res.addCookie(newCookie);
		}
		
		model.addAttribute("qna", qnaService.findByNo(no));
		model.addAttribute("replies", qnaService.findReplies(no));
		return "qna/detail";
	}
	
	@GetMapping("/question/{parno}")	//질문글 상세보기
	public String getQuestion(@PathVariable("parno") Long parno, Model model) {
		model.addAttribute("qnas", qnaService.findReplies(parno));
		return "qna/detail";
	}
	
	@GetMapping("/ins")		//질문 폼 로딩
	public String QnaForm(Model model, HttpSession session) {
		Qna qna = new Qna();
		Member member = (Member) session.getAttribute("loginMember");
		qna.setAuthor(member.getId());
		model.addAttribute("qna", qna);
		return "qna/form";
	}
	
	@GetMapping("/reply/ins/{no}")		//답변 폼 로딩
	public String replyForm(@PathVariable("no") Long no, Model model, HttpSession session) {
		Qna qna = new Qna();
		Member member = (Member) session.getAttribute("loginMember");
		qna.setParno(no);
		qna.setAuthor(member.getId());
		model.addAttribute("qna", qna);
		return "qna/reply";
	}
	
	@PostMapping("/new")		//질문 글 쓰기
	public String insQuestion(@ModelAttribute Qna qna) {
		qnaService.save(qna);
		return "redirect:/qna/list";
	}
	
	@PostMapping("/reply")	//답글 쓰기
	public String insReply(@ModelAttribute Qna qna) {
		qna.setLevel(2);
		qnaService.save(qna);
		return "redirect:/qna/list";
	}
	
	@GetMapping("/edit/{no}")	//글 수정 폼 로딩
	public String updateForm(@PathVariable("no") Long no, Model model) {
		model.addAttribute("qna", qnaService.findByNo(no));
		return "qna/edit";
	}
	
	@PostMapping("/update")	//글 수정 처리
	public String update(@ModelAttribute Qna qna) {
		qnaService.update(qna);
		return "redirect:/qna/list";
	}	
	
	@GetMapping("/reply/del/{no}")	//해당 답글 삭제
	public String deleteReply(@PathVariable("no") Long no) {
		qnaService.delete(no);
		return "redirect:/qna/list";
	}
	
	@GetMapping("/delete/{parno}")	//질문 삭제시 해당 답글도 연쇄 삭제
	public String deleteQuestion(@PathVariable("parno") Long parno) {
		qnaService.deleteByParno(parno);
		return "redirect:/qna/list";
	}
}
