package web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;


@Controller

public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }




    @GetMapping("/")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @PostMapping("/edit-user")
    public String editUser(@RequestParam("id") Long id,
                           @RequestParam("name") String name) {
        User userToEdit = userService.findById(id);
        if (userToEdit != null) {
            userToEdit.setName(name);
            userService.saveOrUpdate(userToEdit);
        }
        return "redirect:/";
    }

    @PostMapping("/add-user")
    public String addUser(@ModelAttribute("user") @Valid User user, Model model) {
        if (user.getName() == null || user.getName().isEmpty()) {
            logger.error("Попытка передать пустой объект пользователя");
            model.addAttribute("errorMessage",
                    "Ошибка: Объект пользователя не может быть пустым.");
            model.addAttribute("users", userService.findAll());
            return "users";
        }
        userService.saveOrUpdate(user);
        return "redirect:/";
    }


    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam("id") Long id, Model model) {
        userService.delete(id);
        return "redirect:/";
    }



}
