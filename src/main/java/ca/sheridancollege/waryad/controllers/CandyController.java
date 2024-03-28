package ca.sheridancollege.waryad.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ca.sheridancollege.waryad.beans.HalloweenCandyConsumption;
import ca.sheridancollege.waryad.database.DatabaseAccess;

@Controller
public class CandyController {

    @Autowired
    private DatabaseAccess da;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("halloweenCandyConsumption", new HalloweenCandyConsumption());
        model.addAttribute("halloweenCandyConsumptionList", da.getHalloweenCandyConsumptionList());
        return "index";
    }

    @PostMapping("/insertHalloweenCandyConsumption")
    public String insertHalloweenCandyConsumption(@ModelAttribute HalloweenCandyConsumption halloweenCandyConsumption) {
        if (halloweenCandyConsumption.getId() == null) {
            da.insertHalloweenCandyConsumption(halloweenCandyConsumption);
        } else {
            da.updateHalloweenCandyConsumption(halloweenCandyConsumption);
        }
        return "redirect:/";
    }

    @GetMapping("/editHalloweenCandyConsumptionById/{id}")
    public String editHalloweenCandyConsumptionById(Model model, @PathVariable Long id) {
        List<HalloweenCandyConsumption> candyConsumptionList = da.getHalloweenCandyConsumptionListById(id);
        if (candyConsumptionList.isEmpty()) {
            return "error";
        }
        HalloweenCandyConsumption halloweenCandyConsumption = candyConsumptionList.get(0);
        model.addAttribute("halloweenCandyConsumption", halloweenCandyConsumption);
        model.addAttribute("halloweenCandyConsumptionList", da.getHalloweenCandyConsumptionList());
        return "edit";
    }

    @PostMapping("/updateHalloweenCandyConsumption")
    public String updateHalloweenCandyConsumption(@ModelAttribute HalloweenCandyConsumption halloweenCandyConsumption) {
        da.updateHalloweenCandyConsumption(halloweenCandyConsumption);
        return "redirect:/";
    }

    @GetMapping("/deleteHalloweenCandyConsumptionById/{id}")
    public String deleteHalloweenCandyConsumptionById(@PathVariable Long id) {
        da.deleteHalloweenCandyConsumptionById(id);
        return "redirect:/";
    }
}
