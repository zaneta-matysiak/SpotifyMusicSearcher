package pl.zanettj.musicSearcher.web;

import lombok.var;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zanettj.musicSearcher.repository.SearchRepository;


@RequestMapping
@Controller
public class SavedSearchController {

    static Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    SearchRepository searchRepository;

    @GetMapping("/getAll-saved-searches")
    public String getAllSavedSearch(Model model){

        var allSavedSearches = searchRepository.findAll();
        LOGGER.info("Get all saved searches. " + searchRepository.findAll().size() + " elements.");
        model.addAttribute("allSavedSearches", allSavedSearches);

        return "index";
    }

    @ResponseBody
    @PostMapping("/delete")
    public Long deleteSavedSearch(@RequestBody Long id){
        searchRepository.deleteById(id);
        return id;
    }

    @ResponseBody
    @PostMapping("/deleteAll")
    public String deleteAllSavedSearch(){
        searchRepository.deleteAll();
        return "All searches deleted.";
    }

}
