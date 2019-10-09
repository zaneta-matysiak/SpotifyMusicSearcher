package pl.zanettj.musicSearcher.controller;

import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zanettj.musicSearcher.model.Search;
import pl.zanettj.musicSearcher.repository.SearchRepository;
import pl.zanettj.musicSearcher.repository.SearchRepositoryCustom;
import pl.zanettj.musicSearcher.spotify.SpotifySearch;

import java.time.LocalDateTime;

@Controller
public class SearchController {

    @GetMapping
    public String search(){
        return "index";
    }

    Paging<Artist> searchArtists;
    Paging<Track> searchTracks;
    String searchType;
    String searchName;

    @Autowired
    SearchRepository searchRepository;
    @Autowired
    SearchRepositoryCustom searchRepositoryCustom;

    @GetMapping(value="/search")
    public String searchByName(@RequestParam(value = "type", defaultValue = "artist") String type,
                               @RequestParam(value = "name") String name,
                               Model model){

        switch (type){
            case "artist":
                searchArtists = SpotifySearch.searchArtists(name);
                model.addAttribute("collection", searchArtists.getItems());
                break;
            case "track":
                searchTracks = SpotifySearch.searchTracks(name);
                model.addAttribute("collection", searchTracks.getItems());
                break;
        }
        searchType = type;
        searchName = name;

        return "index";
    }

    @RequestMapping("/testInsert")
    public String testInsert() {

        Search search = new Search();
        search.setId(this.searchRepositoryCustom.getMaxEmptyId() +1);
        search.setSearchTime(LocalDateTime.now().toString());
        search.setSearchType(searchType);
        search.setSearchQuery(searchName);

        switch (searchType){
            case "artist":
                search.setSearchArtistResult(searchArtists.getItems());
            case "track":
                search.setSearchTrackResult(searchTracks.getItems());
        }

        searchRepository.insert(search);
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
}
