package com.company.app.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.entity.Notice;
import com.company.app.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice/api")
@CrossOrigin(origins = "*")   //CORS 허용
public class NoticeRestController {
    private final NoticeService noticeService;

    @GetMapping("/list")
    public List<Notice> list() {
        return noticeService.getAll();
    }

    @PostMapping("/add")
    public Notice add(@RequestBody Notice notice) {
        return noticeService.save(notice);
    }

    @GetMapping("/{no}")
    public Notice get(@PathVariable("no") Long no) {
        return noticeService.getById(no).orElseThrow();
    }

    @DeleteMapping("/{no}")
    public void delete(@PathVariable("no") Long no) {
        noticeService.delete(no);
    }
}