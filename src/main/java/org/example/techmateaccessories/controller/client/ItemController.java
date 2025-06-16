package org.example.techmateaccessories.controller.client;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.techmateaccessories.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {
      private final ProductService productService;
      public ItemController(ProductService productService) {
             this.productService  =  productService ;
      }
       @PostMapping("/addtocart/{id}")
       public String AddToCart(Model model, @PathVariable long id, HttpServletRequest request){
           HttpSession session = request.getSession();
           String email = (String) session.getAttribute("email") ;
           int sum = 0 ;
                sum    = this.productService.handleAddToCart(email, id);
           session.setAttribute("sum",sum);
           return "redirect:/";
       }
}
