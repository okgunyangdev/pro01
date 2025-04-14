package com.company.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.company.app.entity.Note;
import com.company.app.service.NoteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
        List<Note> notes = (keyword != null) ? noteService.searchNotes(keyword) : noteService.getAllNotes();
        model.addAttribute("notes", notes);
        model.addAttribute("keyword", keyword);
        return "note/list";
    }

    @GetMapping("/detail/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        Note note = noteService.getNoteById(no);
        model.addAttribute("note", note);
        return "note/detail";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("note", new Note());
        return "note/form";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute Note note) {
        noteService.saveNote(note);
        return "redirect:/note/list";
    }

    @GetMapping("/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        Note note = noteService.getNoteById(no);
        model.addAttribute("note", note);
        return "note/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Note note) {
        noteService.saveNote(note);
        return "redirect:/note/detail/" + note.getNo();
    }

    @GetMapping("/delete/{no}")
    public String delete(@PathVariable("no") Long no) {
        noteService.deleteNote(no);
        return "redirect:/note/list";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        return noteService.uploadImage(file);
    }
}