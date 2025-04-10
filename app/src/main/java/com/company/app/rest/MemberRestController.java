package com.company.app.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.entity.Member;
import com.company.app.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/api")
@CrossOrigin(origins = "*")   //CORS 허용
public class MemberRestController {
    private final MemberService memberService;
    
    @PostMapping("/join")
    public Member joinMember(@RequestBody Member member) {
        return memberService.join(member);
    }

    @PostMapping("/login")
    public boolean login(@RequestParam("id") String id, @RequestParam("pw") String pw) {
    	Member member = memberService.login(id, pw);
    	boolean ps = false;
    	if(member!=null) {
    		ps = true;
    	}
        return ps;
    }
    
    @GetMapping("/detail/{no}")
    public Member getMemberByNo(@PathVariable("no") Long no) {
        return memberService.getMemberByNo(no);
    }

    @GetMapping("/id/{id}")
    public Member getMemberById(@PathVariable("id") String id) {
        return memberService.getMember(id);
    }

    @GetMapping("/list")
    public List<Member> getAllMembers() {
        return memberService.getMemberList();
    }
    
    @PutMapping("/update")
    public Member updateMember(@RequestBody Member member) {
        return memberService.update(member);
    }

    @DeleteMapping("/delete/{no}")
    public void deleteMember(@PathVariable("no") Long no) {
        memberService.delete(no);
    }

    @GetMapping("/checkId")
    public boolean checkDuplicateId(@RequestParam("id") String id) {
        return memberService.idCheck(id);  // true이면 이미 존재함
    }

}
