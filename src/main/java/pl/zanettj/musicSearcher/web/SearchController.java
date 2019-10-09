package pl.zanettj.musicSearcher.web;

import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zanettj.musicSearcher.domain.Artist;
import pl.zanettj.musicSearcher.domain.Search;
import pl.zanettj.musicSearcher.domain.Track;
import pl.zanettj.musicSearcher.repository.SearchRepository;
import pl.zanettj.musicSearcher.repository.SearchRepositoryCustom;
import pl.zanettj.musicSearcher.service.SearchService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class SearchController {

    static Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    SearchRepository searchRepository;
    @Autowired
    SearchRepositoryCustom searchRepositoryCustom;

    @GetMapping
    public String search(){
        return "index";
    }

    @GetMapping(value="/search")
    public String searchByName(@RequestParam(value = "name") String name,
                               @RequestParam(value = "type", defaultValue = "artist") String type,
                               Model model){
        var searchResult = new Search();
        searchResult.setSearchTime(LocalDateTime.now().toString());

        LOGGER.info("Search query: " + name + ", search type: " + type);
        switch (type){
            case "artist":
                var spotifyArtists = SearchService.searchArtists(name).getItems();
                searchResult.setSearchArtistResult(Artist.mapArtists(spotifyArtists));
                LOGGER.info("Search result: " +  searchResult.toString());
                break;
            case "track":
                var spotifyTracks = SearchService.searchTracks(name).getItems();
                searchResult.setSearchTrackResult(Track.mapTracks(spotifyTracks));
                LOGGER.info("Search result: " +  searchResult.toString());
                break;
        }

        searchResult.setSearchType(type);
        searchResult.setSearchQuery(name);
        model.addAttribute("searchResult", searchResult);

        return "index";
    }

    @ResponseBody
    @PostMapping("/save")
    public Long saveFavouriteSearch(@RequestBody Search searchResult) {

        var nextId = searchRepositoryCustom.getMaxEmptyId() +1;
        searchResult.setId(nextId);

        try {
            searchRepository.insert(searchResult);
            LOGGER.info("Last search saved with id: " + nextId);
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
        }

        return nextId;
    }

}
