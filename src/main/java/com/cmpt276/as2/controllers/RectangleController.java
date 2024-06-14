package com.cmpt276.as2.controllers;

import com.cmpt276.as2.models.Rectangle;
import com.cmpt276.as2.models.RectangleRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RectangleController {

    @Autowired
    private RectangleRepository service;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("rectangles", service.findAll());
        return "rectangles/home";
    }

    @GetMapping("/rectangles/about")
    public String aboutPage(Model model) {
        model.addAttribute("rectangles", service.findAll());
        return "rectangles/about";
    }

    @GetMapping("/rectangles/view")
    public String getAllRectangles(Model model) {
        model.addAttribute("rectangles", service.findAll());
        return "rectangles/showAll";
    }

    @GetMapping("/rectangles/new")
    public String showAddRectangleForm(Model model) {
        model.addAttribute("rectangle", new Rectangle());
        return "rectangles/addR";
    }

    @PostMapping("/rectangles/add")
    public String addNewRectangle(@ModelAttribute Rectangle rectangle, Model model) {
        if (rectangle.getName() == null || rectangle.getName().isEmpty() || rectangle.getWidth() == null
                || rectangle.getHeight() == null || rectangle.getColor() == null || rectangle.getColor().isEmpty()
                || rectangle.getBorderColor() == null || rectangle.getBorderColor().isEmpty()
                || rectangle.getBorderWidth() == null || rectangle.getOpacity() == null) {
            model.addAttribute("error", "All fields are required.");
            model.addAttribute("rectangle", rectangle);
            return "rectangles/addR";
        }

        rectangle.setCreationDate(LocalDateTime.now());
        rectangle.setLastModifiedDate(LocalDateTime.now());
        service.save(rectangle);
        return "redirect:/rectangles/view";
    }

    @GetMapping("/rectangles/delete/{id}")
    public String deleteRectangle(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/rectangles/view";
    }

    @GetMapping("/rectangles/view/{id}")
    public String viewRectangle(@PathVariable Long id, Model model) {
        Optional<Rectangle> rectangle = service.findById(id);
        if (rectangle.isPresent()) {
            model.addAttribute("rectangle", rectangle.get());
            return "rectangles/rectangleDetail";
        } else {
            return "redirect:/rectangles/view";
        }
    }

    @PostMapping("/rectangles/edit")
    public String editRectangle(@ModelAttribute Rectangle rectangle, Model model) {
        if (rectangle.getName() == null || rectangle.getName().isEmpty() || rectangle.getWidth() == null
                || rectangle.getHeight() == null || rectangle.getColor() == null || rectangle.getColor().isEmpty()
                || rectangle.getBorderColor() == null || rectangle.getBorderColor().isEmpty()
                || rectangle.getBorderWidth() == null || rectangle.getOpacity() == null) {
            model.addAttribute("error", "All fields are required.");
            model.addAttribute("rectangle", rectangle);
            return "rectangles/rectangleDetail";
        }

        rectangle.setLastModifiedDate(LocalDateTime.now());
        service.save(rectangle);
        return "redirect:/rectangles/view";
    }
}
