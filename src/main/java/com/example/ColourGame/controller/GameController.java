package com.example.ColourGame.controller;

import com.example.ColourGame.service.GameRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    @Autowired
    private GameRunner gameRunner;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("subscriptions", gameRunner.listSubscriptions());
        return "game";
    }

    @PostMapping("/command")
    public String handleCommand(@RequestParam("command") String command, Model model) {
        gameRunner.processCommand(command);
        return "redirect:/";
    }
}
