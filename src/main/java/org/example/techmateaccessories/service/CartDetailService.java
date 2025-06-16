package org.example.techmateaccessories.service;

import org.example.techmateaccessories.repository.CartDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class CartDetailService {
       private final CartDetailRepository  cartDetailRepository ;
       public CartDetailService(CartDetailRepository cartDetailRepository){
              this.cartDetailRepository = cartDetailRepository ;
       }
       public void deleteCartDetail(long id ){
             this.cartDetailRepository.deleteById(id);
       }
}
