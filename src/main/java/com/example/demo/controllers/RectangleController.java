package com.example.demo.controllers;

import com.example.demo.model.Rectangle;
import com.example.demo.repository.RectangleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rectangles")

public class RectangleController {

    @Autowired
    private RectangleRepository rectangleRepository;

    @GetMapping
    public String getAllRectangles(Model model) {
        List<Rectangle> rectangles = rectangleRepository.findAll();
        model.addAttribute("rectangles", rectangles);
        return "index";
    }

    @GetMapping("/add")
    public String addRectangleForm(Model model) {
        model.addAttribute("rectangle", new Rectangle());
        return "rectangle";
    }

    @PostMapping("/add")
    public String addRectangle(@ModelAttribute Rectangle rectangle) {
        rectangleRepository.save(rectangle);
        return "redirect:/rectangles";
    }

    @GetMapping("/edit/{id}")
    public String editRectangleForm(@PathVariable Long id, Model model) {
        Rectangle rectangle = rectangleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rectangle Id:" + id));
        model.addAttribute("rectangle", rectangle);
        return "editRectangle";
    }

    @PostMapping("/edit/{id}")
    public String updateRectangle(@PathVariable Long id, @ModelAttribute Rectangle rectangle) {
        rectangleRepository.save(rectangle);
        return "redirect:/rectangles";
    }

    @GetMapping("/delete/{id}")
    public String deleteRectangle(@PathVariable Long id) {
        rectangleRepository.deleteById(id);
        return "redirect:/rectangles";
    }
}
