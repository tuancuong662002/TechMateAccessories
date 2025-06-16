package org.example.techmateaccessories.controller.client;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.techmateaccessories.domain.Cart;
import org.example.techmateaccessories.domain.CartDetail;
import org.example.techmateaccessories.domain.User;
import org.example.techmateaccessories.repository.CartDetailRepository;
import org.example.techmateaccessories.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CartController {
      private final UserService  userService;
      private final CartDetailRepository cartDetailRepository;
      public CartController(UserService userService , CartDetailRepository cartDetailRepository ) {
            this.userService = userService;
            this.cartDetailRepository = cartDetailRepository;
      }
       @GetMapping("/cart")
       public String  cart(HttpServletRequest request , Model model){
             HttpSession session = request.getSession();
             String email = (String)session.getAttribute("email") ;
             if (email == null) {
               return "redirect:/login";
             }
             User user = this.userService.findByEmail(email);
             if (user == null || user.getCart() == null) {
               return "redirect:/";
             }
             Cart cart  = user.getCart() ;
             List<CartDetail> cartDetails = cart.getCartDetail() ;
             model.addAttribute("cartDetails", cartDetails);
             return "client/showCart" ;
       }
    @GetMapping("/cart/delete/{id}")
    public String deleteProductInCart(@PathVariable long id  ){
            this.cartDetailRepository.deleteById(id);
            return "redirect:/cart" ;
    }
}
