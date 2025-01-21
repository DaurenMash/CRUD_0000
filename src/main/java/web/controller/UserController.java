package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;
import java.util.List;


@Controller

public class UserController {

    private final UserService userService;

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
                           @RequestParam("name") String name, Model model) {
        User userToEdit = userService.findById(id);
        if (userToEdit != null) {
            userToEdit.setName(name);
            userService.saveOrUpdate(userToEdit);
        }
        return "redirect:/";
    }

    @PostMapping("/add-user")
    public String addUser(@ModelAttribute("user") @Valid User user) {
        userService.saveOrUpdate(user);
        return "redirect:/";
    }


    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam("id") Long id, Model model) {
        userService.delete(id);
        return "redirect:/";
    }



}

//@Controller
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/")
//    public String getAllUsers(Model model) {
//        model.addAttribute("users", userService.findAll());
//        return "index";
//    }
//
//    @GetMapping("/adduser")
//    public String CreateUserForm(@ModelAttribute("user") User user) {
//        return "adduser";
//    }
//
//    @PostMapping("/adduser")
//    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "adduser";
//        }
//        userService.save(user);
//        return "redirect:/";
//    }
//    @PostMapping("/add-user")
//    public String addUser(@RequestParam("name") String name, Model model) {
//        User newUser = new User();
//        userService.saveOrUpdate(newUser);
//        return "redirect:/";
//    }
//
//    @GetMapping("/deleteUser")
//    public String deleteUser(@RequestParam("id") long id) {
//        userService.delete(id);
//        return "redirect:/";
//    }
//
//    @GetMapping("/updateuser")
//    public String getEditUserForm(Model model, @RequestParam("id") long id) {
//        model.addAttribute("user", userService.findById(id));
//        return "updateuser";
//    }
//
//    @PostMapping("/updateuser")
//    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
//        userService.update(user);
//        return "redirect:/";
//    }
//}
