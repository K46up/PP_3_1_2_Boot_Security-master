package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
public class AdminController {
    private RoleService roleService;
    private UserService userService;

    @Autowired
    public AdminController(RoleService roleService, UserService userService ) {
        this.roleService = roleService;
        this.userService = userService;

    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "people";
    }
    //------------------------------------------------------------------------------------------------------------------
    @GetMapping("/admin/addUser")
    public String formAddUser(Model model) {//(@ModelAttribute("user") User user)
        model.addAttribute("user", new User());
        model.addAttribute("role", roleService.getAll());
        return "addUser";
    }

    @PostMapping("/admin/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }
    //------------------------------------------------------------------------------------------------------------------
/*    @GetMapping("/admin/update")
    public String updateUser(@RequestParam(name = "id") Long id, Model model){
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "/admin/update";
    }

    @PutMapping("/admin/update")
    public String update(@ModelAttribute("userUp") User user) {
        userService.update(user);
        return "redirect:/people";
    }*/
    //------------------------------------------------------------------------------------------------------------------
    @DeleteMapping("/admin/delete_user")                                                                         //работает
    public String deleteUserById(@RequestParam(name = "id") Long id) {
        userService.getDelete(id);
        return "redirect:/admin";
    }
}
