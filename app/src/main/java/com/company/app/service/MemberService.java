package com.company.app.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.app.entity.Member;
import com.company.app.repository.MemberRepository;
//기능 구현은 서비스에서 
@Service
public class MemberService {
	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	public MemberService(MemberRepository memberRepository, 
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.memberRepository = memberRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public Member join(Member member) {  //회원가입 - 사용자가 입력한 비밀번호를 암호화하여 새로 저장
		member.setPw(bCryptPasswordEncoder.encode(member.getPw()));  //1234 => lkjkjoih;hhohiuhuh
		return memberRepository.save(member);
	}
	
	public Member login(String id, String pw) { //로그인 - 아이디와 비밀번호가 일치하는 회원 정보를 반환
		return memberRepository.findById(id)
				.filter(m -> bCryptPasswordEncoder.matches(pw, m.getPw()))
				.orElse(null);
	}
	
    public Member getMember(String id) {	//마이페이지 회원 정보
        return memberRepository.findById(id).orElse(null);
    }

    public Member getMemberByNo(Long no) {	//회원 번호로 검색
        return memberRepository.findByNo(no).orElse(null);
    }
    
    public Member update(Member member) {	//회원 정보 변경
        return memberRepository.save(member);
    }

    public void updatePassword(Member member) {	//비밀번호 변경
        member.setPw(bCryptPasswordEncoder.encode(member.getPw()));
        memberRepository.save(member);
    }
    
    public void delete(Long no) {	//회원 탈퇴
        memberRepository.deleteById(no);
    }

    public List<Member> getMemberList() {	//관리자 회원 목록
        return memberRepository.findAll();
    }
    
    public boolean idCheck(String id) {
    	return memberRepository.existsById(id);
    }
	
}
