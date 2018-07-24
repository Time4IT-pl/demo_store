package pl.time4it.demo_store_2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.time4it.demo_store_2.dtos.ProductDto;
import pl.time4it.demo_store_2.entities.Product;

import java.math.BigDecimal;
import java.util.Optional;


@Controller
public class HomeController {

    @Autowired
    ProductController productController;


    public String home() {

        return "index";
    }


    @RequestMapping(value = "products", method = RequestMethod.GET)
    public String getProducts(Model model) {
        model.addAttribute("products", productController.getProducts());
        return "products";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(Model model,
                         @RequestParam(value = "serialNo") String serialNo) {
        productController.delete(serialNo);
        return "redirect:" + getProducts(model);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(

            Model model,
            @RequestParam(value = "serialNo") String serialNo,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "price") String price,
            @RequestParam(value = "quantity", required = false) Integer quantity,
            @RequestParam(value = "promotion", required = false) boolean promotion,
            @RequestParam(value = "picture", required = false) String picture
//            ,@RequestParam(value = "category", required = false) String category,
//            @RequestParam(value = "producer", required = false) String producer
    ) {

        productController.update(serialNo, name, description, price, quantity, promotion, picture);

        return getProducts(model);
    }

    @RequestMapping(value = "update-form", method = RequestMethod.GET)
    public String updatePage(
            Model model,
            @RequestParam(value = "serialNo") String serialNo) {

        ProductDto product = productController.getProduct(serialNo);
        model.addAttribute("product", product);
        return "update";
    }


}
