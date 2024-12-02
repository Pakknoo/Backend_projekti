package hh.backend.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.backend.harjoitustyo.domain.Manufacturer;
import hh.backend.harjoitustyo.domain.ManufacturerRepository;
import jakarta.validation.Valid;

@Controller
public class ManufacturerController {
    @Autowired
    private ManufacturerRepository mRepository;

    //listaa kaikki valmistajat
    @RequestMapping("/manufacturerlist")
    public String showAllManufacturers(Model model) {
        model.addAttribute("manufacturers", mRepository.findAll());
        return "manufacturerlist";
    }
    //lisää valmistaja
     @RequestMapping(value="/addmanufacturer", method=RequestMethod.GET)
    public String addManufactorer(Model model){
        model.addAttribute("manufacturer", new Manufacturer());
        return "addmanufacturer";
    }
    //poista valmistaja
    @RequestMapping(value="/deletemanufacturer/{id}", method=RequestMethod.GET)
    public String deleteCategory(@PathVariable ("id") Long mid, Model model) {
        mRepository.deleteById(mid);
        return "redirect:../manufacturerlist";
    }
    //muokkaa valmistajaa
    @RequestMapping(value="/editmanufacturer/{id}", method=RequestMethod.GET)
    public String editManufacturer(@PathVariable("id") Long mid, Model model) {
        model.addAttribute("category", mRepository.findById(mid));
        return "editmanufacturer";
    }
    //tallenna valmistaja
     @RequestMapping(value="/savemanufacturer", method=RequestMethod.POST)
    public String saveManufacturer(@Valid Manufacturer manufacturer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
        	return "addmanufacturer";
        }
        mRepository.save(manufacturer);
        return "redirect:manufacturerlist";
    }
}
