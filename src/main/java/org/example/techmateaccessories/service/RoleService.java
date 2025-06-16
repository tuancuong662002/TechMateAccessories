package org.example.techmateaccessories.service;

import org.example.techmateaccessories.domain.Role;
import org.example.techmateaccessories.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
       private final RoleRepository roleRepository  ;
       public RoleService(RoleRepository roleRepository){
            this.roleRepository = roleRepository  ;
       }
       public List<Role> finAllRole(){
            return this.roleRepository.findAll() ;
       }
       public Role getRolebyName(String name){
            return this.roleRepository.findByName(name).orElseThrow(()-> new RuntimeException("Role not found"));
       }
}
