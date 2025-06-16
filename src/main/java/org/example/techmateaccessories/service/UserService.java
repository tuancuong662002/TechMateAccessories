package org.example.techmateaccessories.service;


import org.example.techmateaccessories.domain.User;
import org.example.techmateaccessories.domain.dto.RegisterDTO;
import org.example.techmateaccessories.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
     private final UserRepository userRepository;
     public UserService(UserRepository userRepository){
                this.userRepository = userRepository;
     }
     public List<User> findAllUsers(){
          return  this.userRepository.findAll();
     }
     public User handleSaveUser(User user){
            return this.userRepository.save(user);
     }
     public User findUserByID(long id ){
          return this.userRepository.findById(id).get() ;
     }
     public void deleteUserById(long id ){
          this.userRepository.deleteById(id) ;
     }
     public User registerDTOtoUSER(RegisterDTO registerDTO){
           User user = new User() ;
           user.setFullName(registerDTO.getFullName());
           user.setPassword(registerDTO.getPassword());
           user.setEmail(registerDTO.getEmail());
           return user ;
     }
     public boolean checkEmailExist(String email){
          return  this.userRepository.findByEmail(email).isPresent() ;
     }
     public User findByEmail(String email){
          return  this.userRepository.findByEmail(email).get() ;
     }
}
