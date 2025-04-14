package com.company.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.app.entity.Member;
import com.company.app.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@CrossOrigin(origins = "*")   //CORS 허용
public class MemberController {

    private final MemberService memberService;

	@GetMapping("/join")
    public String joinForm(Model model) {
		model.addAttribute("member", new Member());
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute Member member) {
        memberService.join(member);
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }
    //Java의 객체
    //현재 페이지 내에서만 인지 가능한 객체:page(Page)
    //로그인하고 있는 동안에만 저장하고 객체:session(HttpSession)
    //요청한 곳으로만 보내는 객체: request(HttpServletRequest)
    //보내지는 곳에서만 알 수 있는 객체: response(HttpServletResponse)
    @PostMapping("/login")
    public String login(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session) {
        Member member = memberService.login(id, pw);
        if (member != null) {
            session.setAttribute("loginMember", member);	//로그인 처리
            return "redirect:/";
        } else {
            return "redirect:/member/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
    @GetMapping("/mypage")
    public String myPage(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("loginMember");
        model.addAttribute("member", member);
        return "member/my-page";
    }

    @GetMapping("/edit")
    public String editForm(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("loginMember");
        model.addAttribute("member", member);
        return "member/member-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Member member, HttpSession session) {
        memberService.update(member);
        session.setAttribute("loginMember", member); // 갱신
        return "redirect:/member/mypage";
    }

    @GetMapping("/delete")
    public String delete(HttpSession session) {
        Member member = (Member) session.getAttribute("loginMember");
        memberService.delete(member.getNo());
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("members", memberService.getMemberList());
        return "member/member-list";
    }
    
    @GetMapping("/checkId")
    @ResponseBody
    public boolean checkDuplicateId(@RequestParam("id") String id) {
        return memberService.idCheck(id);  // true이면 이미 존재함
    }
}
