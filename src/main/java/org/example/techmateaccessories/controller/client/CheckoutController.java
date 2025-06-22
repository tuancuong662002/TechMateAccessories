package org.example.techmateaccessories.controller.client;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.techmateaccessories.domain.CartDetail;
import org.example.techmateaccessories.domain.OrderForm;
import org.example.techmateaccessories.domain.User;
import org.example.techmateaccessories.domain.dto.InfoUser;
import org.example.techmateaccessories.domain.dto.OrderItemDTO;
import org.example.techmateaccessories.repository.UserRepository;
import org.example.techmateaccessories.service.ProductService;
import org.example.techmateaccessories.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CheckoutController {
    private final UserService userService;
    private final ProductService productService;
    public CheckoutController(UserRepository userRepository, ProductService productService ,UserService userService) {
           this.userService = userService;
           this.productService = productService;
    }
      @GetMapping("checkout")
       public String checkout(){
            return "client/checkout";
       }
       @PostMapping("/checkout")
      public String checkout(@ModelAttribute OrderForm orderForm , Model model,  HttpSession session ) {
        List<OrderItemDTO> items = orderForm.getOrderItems();

        List<CartDetail> cartDetails = this.productService.handleCartBeforeCheckOut(orderForm);
        // ... Logic xử lý đơn hàng của bạn ...
        model.addAttribute("cartDetails" , cartDetails) ;
           model.addAttribute("totalPrice" , orderForm.getTotalPrice()) ;
        return "client/checkout"; // Hoặc trang thành công
      }
      @PostMapping("place-order")
      public String  placeOrder(  @RequestParam("name") String name,
                                  @RequestParam("address") String address,
                                  @RequestParam("phone") String phone,
                                  @RequestParam(value = "notes", required = false) String notes,
                                  @RequestParam("totalPrice") Double totalPrice,
                                  HttpSession session
                                  ) {

         long id  = (long)session.getAttribute("id") ;
         User user  = this.userService.findUserByID(id);
         this.productService.handPlaceOrder(user , name , address , phone , totalPrice , session);
         return "redirect:/";
      }
}
