package org.example.techmateaccessories.controller.admin;

import jakarta.validation.Valid;
import org.example.techmateaccessories.domain.User;

import org.example.techmateaccessories.service.UploadService;
import org.example.techmateaccessories.service.UserService;
import org.example.techmateaccessories.service.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Controller
public class UserController {
      private final UserService userService ;
      private final RoleService roleService;
      private final UploadService uploadService ;
      private final PasswordEncoder passwordEncoder ;

      public UserController(UserService userService , RoleService  roleService ,  UploadService uploadService  , PasswordEncoder passwordEncoder  ){
             this.userService = userService ;
             this.roleService = roleService ;
             this.uploadService = uploadService ;
             this.passwordEncoder = passwordEncoder;
      }

      @GetMapping("/admin/user")
      public String index(Model model ){
            List<User> users  = this.userService.findAllUsers() ;
            model.addAttribute("users"  ,  users) ;
            return "admin/user/index" ;
      }
      @GetMapping("/admin/user/create")
      public String create(Model model) {
            model.addAttribute("user" , new User());
            model.addAttribute("roles" , roleService.finAllRole() );
            return "admin/user/create";
      }
      @PostMapping("/admin/user/store")
      public String store( @Valid @ModelAttribute("user") User  user , BindingResult  result ,  @RequestParam("fileImg") MultipartFile file)  {
            if (result.hasErrors()) {
                  return "admin/user/create";  // Tên file HTML form bạn dùng
            }
            String fileName = this.uploadService.handleUploadFile(file , "avatar") ;
            if(fileName != null )user.setAvatar(fileName);
            String  hashedPassword = this.passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
            this.userService.handleSaveUser(user) ;
            return "redirect:/admin/user";
      }
      @GetMapping("/admin/user/edit/{id}")
      public String edit(Model model , @PathVariable Long id ) {
            User user =  this.userService.findUserByID(id) ;
            model.addAttribute("user" , user );
            return "admin/user/update";
      }
      @PostMapping("/admin/user/update")
      public String update(@ModelAttribute("user") User user){
            User currentUser = this.userService.findUserByID(user.getID()) ;

            String fileName = ""  ;

            if(currentUser != null){
                  currentUser.setAddress(user.getAddress());
                  currentUser.setFullName(user.getFullName());
                  currentUser.setPhone(user.getPhone());
                  currentUser.setAvatar(fileName);
            }
            this.userService.handleSaveUser(currentUser);
            return "redirect:/admin/user";
      }
      @GetMapping("admin/user/delete/{id}")
      public String delete(@PathVariable Long id ){
             this.userService.deleteUserById(id);
             return "redirect:/admin/user";
      }
}
