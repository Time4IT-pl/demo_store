package pl.time4it.demo_store_2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @Autowired
    ProductController productController;


   public String home() {

       return "index";
   }


   @RequestMapping(value = "products", method = RequestMethod.GET)
   public String getProducts(Model model) {
       model.addAttribute("products",  productController.getProducts());
       return "products";
   }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(Model model,
                         @RequestParam(value = "serialNo") String serialNo) {
        productController.delete(serialNo);
        return "redirect:" + getProducts(model);
    }

}
