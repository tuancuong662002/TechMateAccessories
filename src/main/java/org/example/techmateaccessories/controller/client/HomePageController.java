package org.example.techmateaccessories.controller.client;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.techmateaccessories.domain.Product;
import org.example.techmateaccessories.domain.User;
import org.example.techmateaccessories.domain.dto.RegisterDTO;
import org.example.techmateaccessories.service.ProductService;
import org.example.techmateaccessories.service.RoleService;
import org.example.techmateaccessories.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomePageController {
      private final ProductService productService;
      private final UserService userService;
      private final PasswordEncoder passwordEncoder ;
      private final RoleService roleService;
      public HomePageController(ProductService productService  , UserService userService , PasswordEncoder passwordEncoder ,RoleService roleService) {
             this.productService =  productService;
             this.userService =  userService;
             this.passwordEncoder = passwordEncoder;
             this.roleService = roleService;
      }
      @GetMapping("/")
      public String  index(Model model   , HttpSession session){
             List<Product> products = this.productService.findAllProducts();
             List<Product> topSellingProducts = this.productService.findTopSellingProducts();
             model.addAttribute("products",products);
             model.addAttribute("topSellingProducts",topSellingProducts);

             return "client/index";
      }
      @GetMapping("/product/detail/{id}" )
      public String  detail(Model model , @PathVariable Long id , HttpServletRequest request ){
             Product product=  this.productService.findProductByID(id) ;
             model.addAttribute("product",product);
             return "client/productDetail";
      }
      @GetMapping("/register")
      public String register(Model model){
             RegisterDTO registerDTO = new RegisterDTO();
             model.addAttribute("registerDTO",registerDTO);
             return "client/register";
      }
      @PostMapping("/register")
      public  String register(@Valid @ModelAttribute("registerDTO") RegisterDTO registerDTO , BindingResult result, Model model){
             if (result.hasErrors()) {
                 model.addAttribute("registerDTO", registerDTO);
                 return "client/register";
             }
             boolean emailExist = this.userService.checkEmailExist(registerDTO.getEmail());
             if(emailExist){
                 result.rejectValue("email", "error.registerDTO", "Email already exists!");
                 return "client/register";
             }
             User user = this.userService.registerDTOtoUSER(registerDTO);
             user.setPassword(this.passwordEncoder.encode(user.getPassword()) );
             user.setRole(this.roleService.getRolebyName("USER"));
             this.userService.handleSaveUser(user) ;
             return  "redirect:/login";
      }
      @GetMapping("/login")
      public String login(Model model){
             return "client/login";
      }
      @GetMapping("/accessDenied")
      public String denyPage(){
             return "client/accessDenied";
       }
}
