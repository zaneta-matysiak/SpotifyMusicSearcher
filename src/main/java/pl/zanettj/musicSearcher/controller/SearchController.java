package pl.zanettj.musicSearcher.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zanettj.musicSearcher.model.Artist;
import pl.zanettj.musicSearcher.model.SearchResult;
import pl.zanettj.musicSearcher.model.Track;
import pl.zanettj.musicSearcher.repository.SearchRepository;
import pl.zanettj.musicSearcher.repository.SearchRepositoryCustom;
import pl.zanettj.musicSearcher.spotify.SpotifySearch;

import java.time.LocalDateTime;
import java.util.Arrays;

@Controller
@RequestMapping("/")
@Slf4j
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
        var searchResult = new SearchResult();
        searchResult.setSearchTime(LocalDateTime.now().toString());

        LOGGER.info("Search query: " + name + ", search type: " + type);
        switch (type){
            case "artist":
                var spotifyArtists = SpotifySearch.searchArtists(name).getItems();
                searchResult.setSearchArtistResult(Artist.mapArtists(spotifyArtists));
                LOGGER.info("Search result: " +  searchResult.toString());
                break;
            case "track":
                var spotifyTracks = SpotifySearch.searchTracks(name).getItems();
                searchResult.setSearchTrackResult(Track.mapTracks(spotifyTracks));
                LOGGER.info("Search result: " +  searchResult.toString());
                break;
        }

        searchResult.setSearchType(type);
        searchResult.setSearchQuery(name);

        model.addAttribute("searchResult", searchResult);

        return "index";
    }

    @PostMapping("/search-saved")
    public String saveFavouriteSearch(@RequestBody SearchResult searchResult) {

        var nextId = searchRepositoryCustom.getMaxEmptyId() +1;
        searchResult.setId(nextId);

        try {
            searchRepository.insert(searchResult);
            LOGGER.info("Last search saved with id: " + nextId);
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
        }

        return "index";
    }

//    @PostMapping
//    public String setSearchAsFavourite(){
//
//    }

//    @GetMapping("/{artistName}")
//    public String getArtist(
//            @PathVariable String documentId,
//            @PathVariable String caseId,
//            Model model) throws IOException {
//
//        long idL = Long.parseLong(documentId);
//
//        ArrayList<Document> documentList = documentsService.getAllTitle(caseId);
//        Document document = imageService.getDocumentById(idL, documentList);
//        Long prevId = imageService.getPrevId(idL, documentList);
//        Long nextId = imageService.getNextId(idL, documentList);
//
//        model.addAttribute("caseId", caseId);
//        model.addAttribute("sidebarTitle", SIDEBAR_TITLE);
//        model.addAttribute("generalTitle", generalTitle + getCaseNumber(caseId));
//        model.addAttribute("documents", documentList);
//        model.addAttribute("currentDocument", document);
//        model.addAttribute("prevId", prevId);
//        model.addAttribute("nextId", nextId);
//        model.addAttribute("documentAuthor", document.getUserCreated().getUserName());
//        model.addAttribute("documentDataCreated", document.getDataCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        return "document-list";
//    }
//    @ResponseBody
//    @RequestMapping("/showAllUsers")
//    public String showAllEmployee() {
//
//        List<User> users = this.userRepository.findAll();
//
//        String html = "";
//        for (User user : users) {
//            html += user + "<br>";
//        }
//
//        return html;
//    }
//
//    @ResponseBody
//    @RequestMapping("/showFullNameLikeTom")
//    public String showFullNameLikeTom() {
//
//        List<User> employees = this.userRepository.findByName("Tom");
//
//        String html = "";
//        for (User emp : employees) {
//            html += emp + "<br>";
//        }
//
//        return html;
//    }
//
//    @ResponseBody
//    @RequestMapping("/deleteAllUsers")
//    public String deleteAllEmployee() {
//
//        this.userRepository.deleteAll();
//        return "Deleted!";
//    }
}
