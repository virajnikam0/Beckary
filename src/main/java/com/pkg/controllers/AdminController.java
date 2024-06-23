package com.pkg.controllers;


import com.pkg.dto.ProductDTO;
import com.pkg.models.Category;
import com.pkg.models.Product;
import com.pkg.services.CategoryService;
import com.pkg.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/admin")
    public String adminHome(){

        return "adminHome";
    }
    @GetMapping("/admin/categories")
    public String getCategories(Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        return "categories";
    }
    @GetMapping("/admin/categories/add")
    public String getCategoriesAdd(Model model){
        model.addAttribute("category",new Category());
        return "categoriesAdd";
    }
    @PostMapping("/admin/categories/add")
    public String postCategoryById(@ModelAttribute("category") Category category){

        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id){
        categoryService.deleteCategoryById(id);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable int id,Model model){
        Optional<Category> category = categoryService.updateCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("category",category);
            return "categoriesAdd";
        }else
            return "404";

    }
    @GetMapping("/admin/products")
    public String products(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return  "products";
    }

    @GetMapping("/admin/products/add")
    public String productAddId(Model model){
        model.addAttribute("productDTO",new ProductDTO());
        model.addAttribute("categories",categoryService.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String productAddPost(
            @ModelAttribute("productDTO")ProductDTO productDTO,
            @RequestParam("productImage")MultipartFile file,
            @RequestParam("imgName")String imgName) throws IOException{

        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.updateCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());
        productService.addProduct(product);


        return "redirect:/admin/products";
    }






}
