package ua.azbest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.azbest.dao.SubjectDAO;
import ua.azbest.model.Subject;

import java.io.IOException;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectDAO subjectDAO;

    @Autowired
    public SubjectController(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    @GetMapping()
    public String index(Model model) {
        try {
            model.addAttribute("subjects", subjectDAO.index());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "subject/index";
    }

    @GetMapping("/{id}")
    public String show(
            @PathVariable("id") long id,
            Model model
    ) {
        model.addAttribute("subject", subjectDAO.show(id));
        return "subject/show";
    }

    @GetMapping("/new")
    public String newSubject(@ModelAttribute("subject") Subject subject) {
//        model.addAttribute("subject", new Subject());
        return "subject/new";
    }

    @PostMapping
    public String create(@ModelAttribute("subject") Subject subject) {
        subjectDAO.save(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("subject", subjectDAO.show(id));
        return "subject/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("subject") Subject subject, @PathVariable("id") long id) {
        subjectDAO.update(id, subject);
        return "redirect:/subjects";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        subjectDAO.delete(id);
        return "redirect:/subjects";
    }

}
