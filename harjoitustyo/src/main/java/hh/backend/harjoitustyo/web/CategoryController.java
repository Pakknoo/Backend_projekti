package hh.backend.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.backend.harjoitustyo.domain.Category;
import hh.backend.harjoitustyo.domain.CategoryRepository;
import jakarta.validation.Valid;


@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository cRepository;

    //listaa kaikki kategoriat
    @RequestMapping("/categorylist")
    public String showAllCategories(Model model) {
        model.addAttribute("categories", cRepository.findAll());
        return "categorylist";
    }
    //lisää kategoria
    @RequestMapping(value="/addcategory", method=RequestMethod.GET)
    public String addCategory(Model model){
        model.addAttribute("category", new Category());
        return "addcategory";
    }
    //poista kategoria
    @RequestMapping(value="/deletecategory/{id}", method=RequestMethod.GET)
    public String deleteCategory(@PathVariable ("id") Long cid, Model model) {
        cRepository.deleteById(cid);
        return "redirect:../categorylist";
    }
    // muokkaa kategoriaa
    @RequestMapping(value="/editcategory/{ccid}", method=RequestMethod.GET)
    public String editCategory(@PathVariable("ccid") Long cid, Model model) {
        model.addAttribute("category", cRepository.findById(cid));
        return "editcategory";
    }
    //tallenna kategoria
     @RequestMapping(value="/savecategory", method=RequestMethod.POST)
    public String saveCategory(@Valid Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
        	return "addcategory";
        }
        cRepository.save(category);
        return "redirect:categorylist";
    }
        
}
