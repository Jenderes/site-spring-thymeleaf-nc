package com.simple.demo.controller;

import com.simple.demo.entity.User;
import com.simple.demo.model.MessageDto;
import com.simple.demo.model.SearchDto;
import com.simple.demo.service.EmailService;
import com.simple.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    @PostMapping("/add")
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result){
        if (result.hasErrors()) {
            return "user-add";
        }
        userService.createUser(user);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String showAddUserForm (@ModelAttribute("user") User user){

        return "user-add";
    }

    @PostMapping("/initial")
    public String initialUSer(){
        userService.initialUser();
        return "redirect:/";
    }

    @GetMapping("/")
    public String userLit(Model model){
        model.addAttribute("users",userService.findAllUser());
        return "user-list";
    }

    @GetMapping("/search")
    public String searchInfo(@ModelAttribute("search") SearchDto searchDto){
        return "user-search";
    }

    @PostMapping("/search")
    public String SearchFind(@ModelAttribute("search") @Valid SearchDto searchDto, BindingResult result){
        if (result.hasErrors()) {
            return "user-search";
        }
        userService.findUser(searchDto);
        return "redirect:/search";
    }

    @GetMapping("/send")
    public String sendMessage(@ModelAttribute("message") MessageDto messageDto){

        return "user-send-message";
    }

    @PostMapping("/send")
    public String CreateMessage(@Valid @ModelAttribute("message") MessageDto messageDto, BindingResult result){
        if (result.hasErrors()) {
            return "user-send-message";
        }
        emailService.SendMessage(messageDto);
        return "redirect:/";
    }
    @PostMapping("/delete/{id}")
    public String CreateMessage(@PathVariable String id){
        userService.delete(Long.parseLong(id));
        return "redirect:/";
    }
}
