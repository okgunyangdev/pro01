package com.company.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.app.entity.Board;
import com.company.app.entity.Qna;
import com.company.app.repository.QnaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor			//생성자 주입
public class QnaService {
	private final QnaRepository qnaRepository;
	
	public List<Qna> getAllQna() {	//전체 글 목록
		return qnaRepository.findAllByOrderByParnoDescNoAsc();
	}
	
	public Qna findByNo(Long no) {	//답변글 상세보기
		return qnaRepository.findById(no).orElse(null);
	}
	
	public Qna save(Qna qna) {	//글 추가
		Qna q = qnaRepository.save(qna);
		if(q.getLevel() == 1) {
			q.setParno(q.getNo());
		}
		return qnaRepository.save(qna);
	}
	
	public Qna update(Qna qna) {	//글 수정
		return qnaRepository.save(qna);
	}
	
	public void delete(Long no) {	//답변 글만 삭제
		qnaRepository.deleteById(no);
	}
	
	public void deleteByParno(Long parno) {  //질문 삭제시 해당 답변도 연쇄삭제
		qnaRepository.deleteByParno(parno);
	}
	
	public List<Qna> findReplies(Long parno){	//질문 글 상세보기 -> 해당 답글도 같이 가져옴
		return qnaRepository.findByParno(parno);
	}

	public void increaseHits(Long no) {
		Qna qna = findByNo(no);
		qna.setHits(qna.getHits() + 1);
		qnaRepository.save(qna);
	}
}
