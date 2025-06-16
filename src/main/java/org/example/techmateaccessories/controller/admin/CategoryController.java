package org.example.techmateaccessories.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
     @GetMapping("/admin/category")
     public String index(){
          return "admin/category/index"  ;
     }
}
