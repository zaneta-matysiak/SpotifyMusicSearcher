package pl.zanettj.musicSearcher.controller;

import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zanettj.musicSearcher.model.SearchResult;
import pl.zanettj.musicSearcher.repository.SearchRepository;
import pl.zanettj.musicSearcher.repository.SearchRepositoryCustom;
import pl.zanettj.musicSearcher.spotify.SpotifySearch;

import java.time.LocalDateTime;

@Controller
public class SearchController {

    SearchResult searchResult;

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

        switch (type){
            case "artist":
                searchResult.setSearchArtistResult(SpotifySearch.searchArtists(name).getItems());
                model.addAttribute("collection", searchResult.getSearchArtistResult());
                break;
            case "track":
                searchResult.setSearchTrackResult(SpotifySearch.searchTracks(name).getItems());
                model.addAttribute("collection", searchResult.getSearchTrackResult());
                break;
        }
        searchResult.setSearchType(type);
        searchResult.setSearchQuery(name);

        return "index";
    }

    @RequestMapping("/testInsert")
    public String testInsert() {

        searchResult.setId(this.searchRepositoryCustom.getMaxEmptyId() +1);
        searchResult.setSearchTime(LocalDateTime.now().toString());

        searchRepository.insert(searchResult);
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
