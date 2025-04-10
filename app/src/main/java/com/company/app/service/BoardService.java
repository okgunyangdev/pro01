package com.company.app.service;

//import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.app.entity.Board;
import com.company.app.repository.BoardRepository;

@Service
public class BoardService {
	private final BoardRepository boardRepository;
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public List<Board> getAllBoards(){	//게시판 목록
		return boardRepository.findAll();
	}
	
	//페이징 처리
	public Page<Board> getBoardList(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	public Board getBoardById(Long no) {	//게시판 1건 조회
		Board board = boardRepository.findById(no).orElse(null);
		return board;
	}
	
	public void increaseHits(Long no) {
		Board board = getBoardById(no);
		board.setHits(board.getHits() + 1);
		boardRepository.save(board);
	}
	
	public Optional<Board> getBoardByNo(Long no) {	//게시판 1건 조회
		return boardRepository.findById(no);
	}
	
	public Board saveBoard(Board board) {	//게시판 글 추가
		return boardRepository.save(board);  		//board.setResdate(LocalDateTime.now());
	}
	
	public Board updateBoard(Board board) {	//게시판 글 수정
		return boardRepository.save(board);
	}
	
	public void deleteBoard(Long no) {	//게시판 글 삭제
		boardRepository.deleteById(no);
	}
}
