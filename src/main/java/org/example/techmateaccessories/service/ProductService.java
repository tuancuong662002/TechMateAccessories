package org.example.techmateaccessories.service;


import jakarta.servlet.http.HttpSession;
import org.example.techmateaccessories.domain.Cart;
import org.example.techmateaccessories.domain.CartDetail;
import org.example.techmateaccessories.domain.Product;
import org.example.techmateaccessories.domain.User;
import org.example.techmateaccessories.repository.CartDetailRepository;
import org.example.techmateaccessories.repository.CartRepository;
import org.example.techmateaccessories.repository.ProductRepository;
import org.example.techmateaccessories.repository.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository ;
    private final CartRepository cartRepository ;
    private final UserService userService ;
    private final CartDetailRepository cartDetailRepository ;
    public ProductService(ProductRepository productRepository ,CartRepository cartRepository, UserService userService , CartDetailRepository cartDetailRepository ){
         this.productRepository = productRepository;
         this.cartRepository = cartRepository;
         this.userService = userService;
         this.cartDetailRepository =  cartDetailRepository ;
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
                return cart.getSum();
            }
        }
        return 0;
    }
}
