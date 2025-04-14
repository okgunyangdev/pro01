package com.company.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.app.entity.Notice;
import com.company.app.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("noticeList", noticeService.getAll());
        return "notice/list";
    }

    @GetMapping("/ins")
    public String form(Model model) {
        model.addAttribute("notice", new Notice());
        return "notice/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Notice notice) {
        noticeService.save(notice);
        return "redirect:/notice/list";
    }

    @GetMapping("/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        Notice notice = noticeService.getById(no).orElseThrow();
        model.addAttribute("notice", notice);
        return "notice/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Notice notice) {
        noticeService.save(notice);
        return "redirect:/notice/list";
    }

    @GetMapping("/detail/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        Notice notice = noticeService.getById(no).orElseThrow();
        notice.setHits(notice.getHits() + 1);
        noticeService.save(notice);
        model.addAttribute("notice", notice);
        return "notice/detail";
    }

    @GetMapping("/delete/{no}")
    public String delete(@PathVariable("no") Long no) {
        noticeService.delete(no);
        return "redirect:/notice/list";
    }
}