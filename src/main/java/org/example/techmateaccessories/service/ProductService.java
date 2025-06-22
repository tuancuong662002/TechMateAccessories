package org.example.techmateaccessories.service;


import jakarta.servlet.http.HttpSession;
import org.example.techmateaccessories.domain.*;
import org.example.techmateaccessories.domain.dto.OrderItemDTO;
import org.example.techmateaccessories.repository.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository ;
    private final CartRepository cartRepository ;
    private final UserService userService ;
    private final CartDetailRepository cartDetailRepository ;
    private final OrderRepository orderRepository ;
    private final OrderDetailRepository orderDetailRepository  ;
    public ProductService(ProductRepository productRepository ,
                          CartRepository cartRepository,
                          UserService userService,
                          CartDetailRepository cartDetailRepository,
                          OrderRepository orderRepository,
                          OrderDetailRepository orderDetailRepository){
         this.productRepository = productRepository;
         this.cartRepository = cartRepository;
         this.userService = userService;
         this.cartDetailRepository =  cartDetailRepository ;
         this.orderRepository =  orderRepository ;
         this.orderDetailRepository = orderDetailRepository;
    }
    public List<Product> findAllProducts(){
          return this.productRepository.findAll() ;
    }
    public Product handleSaveProduct(Product product){
        return this.productRepository.save(product);
    }
    public Product findProductByID(long id ){
        return this.productRepository.findById(id).get() ;
    }
    public void deleteProductById(long id ){
        this.productRepository.deleteById(id) ;
    }
    public List<Product>findTopSellingProducts(){
         return this.productRepository.findTopSellingProducts() ;
    }
    public int handleAddToCart(String email, long id) {
        User user = this.userService.findByEmail(email);
        if (user != null) {
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                cart = new Cart();
                cart.setUser(user);
                cart.setSum(0);
                cart = this.cartRepository.save(cart);
            }

            Optional<Product> optionalProduct = this.productRepository.findById(id);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                CartDetail existingDetail = this.cartDetailRepository.findByCartAndProduct(cart, product);
                if (existingDetail == null) {
                    CartDetail newDetail = new CartDetail();
                    newDetail.setCart(cart);
                    newDetail.setProduct(product);
                    newDetail.setPrice(product.getPrice());
                    newDetail.setQuantity(1);
                    this.cartDetailRepository.save(newDetail);
                    cart.setSum(cart.getSum() + 1);
                } else {
                    existingDetail.setQuantity(existingDetail.getQuantity() + 1);
                    this.cartDetailRepository.save(existingDetail);
                }
                this.cartRepository.save(cart);
                return cartDetailRepository.countByCart(cart);
            }
        }
        return 0;
    }
    public List<CartDetail> handleCartBeforeCheckOut(OrderForm orderForm){
        List<OrderItemDTO> items = orderForm.getOrderItems() ;
        List<CartDetail> StorageCartDetial = new ArrayList<>();
        if (items != null) {
            for (OrderItemDTO item : items) {
                 Optional<CartDetail>cartDetail =  this.cartDetailRepository.findById(item.getProductId()) ;
                 if(cartDetail.isPresent()){
                      CartDetail  currentCartDetail = cartDetail.get();
                      currentCartDetail.setQuantity(item.getQuantity());
                      StorageCartDetial.add(currentCartDetail);
                      this.cartDetailRepository.save(currentCartDetail);
                 }
            }
        }
        return  StorageCartDetial;
    }
    public void handPlaceOrder(User user,
                               String name,
                               String address,
                               String phone,
                               double totalPrice,
                               HttpSession session){
           //create order
        Order order = new Order();
        order.setUser(user);
        order.setName(name);
        order.setAddress(address);
        order.setPhone(phone);
        order.setTotalPrice(totalPrice);
        order = this.orderRepository.save(order);
         //create orderDetail
        Cart cart  = this.cartRepository.findByUser(user);
        if(cart != null){
            List<CartDetail> cartDetails = cart.getCartDetail() ;
            for(CartDetail cartDetail : cartDetails){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setProduct(cartDetail.getProduct());
                orderDetail.setPrice(cartDetail.getPrice());
                orderDetail.setQuantity(cartDetail.getQuantity());
                this.orderDetailRepository.save(orderDetail);
            }
            this.cartDetailRepository.deleteAll(cart.getCartDetail());
            cart.setSum(0);
            this.cartRepository.save(cart);
            session.setAttribute("sum"  , 0);
        }
    }
}
