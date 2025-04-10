package com.company.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.app.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

    // 제목 또는 내용에 키워드가 포함된 노트 검색 (최신순 정렬)
    List<Note> findByTitleContainingOrContentContainingOrderByNoDesc(String title, String content);

    // 전체 노트 목록 (최신순 정렬)
    List<Note> findAllByOrderByNoDesc();
}
