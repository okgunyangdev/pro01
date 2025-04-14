package com.company.app.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.company.app.entity.Note;
import com.company.app.repository.NoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    private final String uploadDir = "src/main/resources/static/uploads/";
    
    public List<Note> getAllNotes() {
        return noteRepository.findAllByOrderByNoDesc();
    }

    public Note getNoteById(Long no) {
        return noteRepository.findById(no).orElse(null);
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public void deleteNote(Long no) {
        noteRepository.deleteById(no);
    }

    public List<Note> searchNotes(String keyword) {
        return noteRepository.findByTitleContainingOrContentContainingOrderByNoDesc(keyword, keyword);
    }
    
    /** 썸머노트 이미지 업로드 처리 메서드 */
    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) return null;

        // 고유 파일명 생성
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        File saveFile = new File(uploadDir + fileName);

        // 디렉토리 없으면 생성
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 파일 저장
        file.transferTo(saveFile);

        // 이미지 경로를 클라이언트에 전달
        return "/uploads/" + fileName;
    }
}