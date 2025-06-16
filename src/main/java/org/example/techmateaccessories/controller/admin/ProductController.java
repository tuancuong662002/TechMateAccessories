package org.example.techmateaccessories.controller.admin;

import jakarta.validation.Valid;
import org.example.techmateaccessories.domain.Category;
import org.example.techmateaccessories.domain.Product;
import org.example.techmateaccessories.domain.User;
import org.example.techmateaccessories.repository.CategoryRepository;
import org.example.techmateaccessories.service.CategoryService;
import org.example.techmateaccessories.service.ProductService;
import org.example.techmateaccessories.service.UploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ProductController {
    private final CategoryService categoryService;
    private ProductService productService;
    private final UploadService uploadService ;
    public ProductController(ProductService productService , UploadService uploadService,  CategoryService categoryService){
           this.productService =  productService;
           this.uploadService =  uploadService ;
           this.categoryService = categoryService;
    }
    @GetMapping("/admin/product")
    public String index(Model model){
        List<Product> products = this.productService.findAllProducts();
        model.addAttribute("products",products);
        return "admin/product/index" ;
    }
    @GetMapping("/admin/product/create")
    public String create(Model model){
        Product product = new Product();
        product.setCategory(new Category());
        model.addAttribute("product" , product );
        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories" , categories);
        System.out.println("Categories: " + categories);
        return "admin/product/create" ;
    }
    @PostMapping("/admin/product/store")
    public String store(@Valid @ModelAttribute("product") Product product , BindingResult result , @RequestParam("fileImg") MultipartFile file)  {
        if (result.hasErrors()) {
            return "admin/product/create";
        }
        String fileName = this.uploadService.handleUploadFile(file , "product") ;
        if(fileName != null )product.setImage(fileName);
        this.productService.handleSaveProduct(product) ;
        return "redirect:/admin/product";
    }
    @GetMapping("/admin/product/edit/{id}")
    public String edit(Model model , @PathVariable Long id ) {
        Product product =  this.productService.findProductByID(id) ;
        model.addAttribute("product" , product );
        return "admin/product/update";
    }
    @PostMapping("/admin/product/update")
    public String update(@ModelAttribute("product") Product product, @RequestParam("fileImg") MultipartFile file) {
        Product currentProduct = this.productService.findProductByID(product.getId());

        if (currentProduct == null) {
            return "redirect:/admin/product?error=notfound";
        }

        currentProduct.setName(product.getName());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setTarget(product.getTarget());

        if (!file.isEmpty()) {
            String fileName = this.uploadService.handleUploadFile(file, "product");
            if (fileName != null) {
                currentProduct.setImage(fileName);
            }
        }
        currentProduct.setDetailDesc(product.getDetailDesc());
        currentProduct.setShortDesc(product.getShortDesc());
        currentProduct.setFactory(product.getFactory());
        currentProduct.setQuantity(product.getQuantity());

        this.productService.handleSaveProduct(currentProduct);
        return "redirect:/admin/product";
    }
    @GetMapping("/admin/product/delete/{id}")
    public String delete(@PathVariable Long id ){
        this.productService.deleteProductById(id);
        return "redirect:/admin/product";
    }
}
