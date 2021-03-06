package com.learning.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<Product> listProducts=service.listALl();
        model.addAttribute("listProducts",listProducts); // "listProducts" harus sama dengan yang ada di html
        return "index";
    }
    @RequestMapping("/new")
    public String showNewProductForm(Model model){
        Product product=new Product();
        model.addAttribute("product",product); // "product" harus sama dengan yang ada di html

        return "new_product"; //nama dari html filenya
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute(name = "product") Product product){
        service.save(product);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("edit_product");//nama dari html filenya

        Product product=service.get(id);
        mav.addObject("product",product);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name ="id") Long id){ // nama dalam annotation patvarible harus sama dengan nama di request mapping
        service.delete(id);
        return "redirect:/";
    }
}
