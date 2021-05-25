package com.simple.demo.controller;

import com.simple.demo.entity.User;
import com.simple.demo.model.MessageDto;
import com.simple.demo.model.SearchDto;
import com.simple.demo.model.UserSearchDto;
import com.simple.demo.service.EmailService;
import com.simple.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    @PostMapping("/add")
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "user-add";
        }
        if (userService.existUserByUserNameAndLastName(user.getFirstName(), user.getLastName())){
            model.addAttribute("existedUsername","Пользователь с таким именем и фамилией уже добавлен");
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
    public String SearchFind(@ModelAttribute("search") @Valid SearchDto searchDto, BindingResult result, Model model, @RequestHeader(value = "User-Agent") String userAgent){
        if (result.hasErrors()) {
            return "user-search";
        }
        User user = userService.findUserByFirstNameAndLastName(searchDto.getFirstName(),searchDto.getLastName());
        if (user == null){
            model.addAttribute("notFoundUser","Пользователь не найден");
            return "user-search";
        }
        String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        model.addAttribute("userSearch", UserSearchDto.mapUserSearch(user,userAgent, timeStamp));
        return "user-search";
    }

    @GetMapping("/send")
    public String sendMessage(@ModelAttribute("message") MessageDto messageDto){
        return "user-send-message";
    }

    @PostMapping("/send")
    public String CreateMessage(@Valid @ModelAttribute("message") MessageDto messageDto, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "user-send-message";
        }
        User findUser = userService.findUserByFirstNameAndLastName(messageDto.getFirstName(), messageDto.getLastName());
        if (findUser == null){
            model.addAttribute("notFoundUser","Пользователь не найден");
            return "user-send-message";
        }
        model.addAttribute("sendUser","Отправлено сообщение на email: " + findUser.getEmail());
        messageDto.setEmail(findUser.getEmail());
        emailService.SendMessage(messageDto);
        return "user-send-message";
    }

    @PostMapping("/delete/{id}")
    public String CreateMessage(@PathVariable String id){
        userService.delete(Long.parseLong(id));
        return "redirect:/";
    }
}
