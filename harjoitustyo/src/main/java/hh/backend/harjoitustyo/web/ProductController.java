package hh.backend.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.backend.harjoitustyo.domain.AppUserRepository;
import hh.backend.harjoitustyo.domain.CategoryRepository;
import hh.backend.harjoitustyo.domain.ManufacturerRepository;
import hh.backend.harjoitustyo.domain.Product;
import hh.backend.harjoitustyo.domain.ProductRepository;
import jakarta.validation.Valid;


@Controller
public class ProductController {
    @Autowired
    private ProductRepository pRepository;
    @Autowired
    private CategoryRepository cRepository;
    @Autowired
    private ManufacturerRepository mRepository;
    @Autowired
    private AppUserRepository uRepository;

    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	

    //listaa kaikki tuotteet
    @RequestMapping(value="/productlist", method=RequestMethod.GET)
    public String productList(Model model) {
        model.addAttribute("products", pRepository.findAll());
        return "productlist";
    }
    //lisää uusi tuote
    @RequestMapping(value= "/addproduct", method=RequestMethod.GET)
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", cRepository.findAll());
        model.addAttribute("manufacturers", mRepository.findAll());
        return "addproduct";
    }
    //muokkaa tuotetta
    @RequestMapping(value = "/editproduct/{id}", method=RequestMethod.GET)
    public String editProduct(@PathVariable("id") Long id, Model model){
        model.addAttribute("product", pRepository.findById(id));
        model.addAttribute("categories", cRepository.findAll());
        model.addAttribute("manufacturers", mRepository.findAll());
        return "editproduct";
    }

    //poista tuote
     @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public String deleteProduct(@PathVariable("id") Long pid, Model model){
        pRepository.deleteById(pid);
        return "redirect:../productlist";
        
    }
    //tallenna tuote
    @RequestMapping(value="/saveproduct", method=RequestMethod.POST)
    public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) { //jos on virheitä
            model.addAttribute("categories", cRepository.findAll());
            model.addAttribute("manufacturers", mRepository.findAll());
        	return "addproduct";
        }
        pRepository.save(product);
        return "redirect:/productlist";
    }
   
}
