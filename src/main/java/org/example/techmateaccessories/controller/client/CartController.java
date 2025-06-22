package org.example.techmateaccessories.controller.client;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.techmateaccessories.domain.*;
import org.example.techmateaccessories.repository.CartDetailRepository;
import org.example.techmateaccessories.repository.CartRepository;
import org.example.techmateaccessories.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {
      private final UserService  userService;
      private final CartDetailRepository cartDetailRepository;
      private final CartRepository  cartRepository;


    public CartController(UserService userService , CartDetailRepository cartDetailRepository, CartRepository cartRepository ) {
            this.userService = userService;
            this.cartDetailRepository = cartDetailRepository;
            this.cartRepository = cartRepository;

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
    public String deleteProductInCart(@PathVariable long id, HttpSession session) {
        String username = (String)session.getAttribute("email"); // lấy username đang đăng nhập
        User user = userService.findByEmail(username);
        // Tìm cart của user hiện tại
        Cart cart = cartRepository.findByUser(user);
        // Tìm cart detail theo id và kiểm tra có thuộc cart này không
        Optional<CartDetail> detailOpt = cartDetailRepository.findById(id);
        if (detailOpt.isPresent()) {
            CartDetail detail = detailOpt.get();
            if (detail.getCart().getId() == cart.getId()) {
                cartDetailRepository.deleteById(id);
            }
        }
        // Cập nhật lại session
        int sum = cartDetailRepository.countByCart(cart);
        session.setAttribute("sum", sum);
        cart.setSum(sum);
        this.cartRepository.save(cart) ;
        return "redirect:/cart";
    }


}
