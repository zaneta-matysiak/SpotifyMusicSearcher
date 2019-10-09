package pl.zanettj.musicSearcher.controller;

import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import lombok.var;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zanettj.musicSearcher.spotify.SpotifySearch;

import java.awt.print.Pageable;

@Controller
public class SearchController {

    @GetMapping
    public String search(){
        return "index";
    }

    Paging<Artist> searchArtists;
    Paging<Track> searchTracks;

    @GetMapping(value="/search")
    public String searchByName(@RequestParam(value = "type", defaultValue = "artist") String type,
                               @RequestParam(value = "name") String name,
                               Model model){

        switch (type){
            case "artist":
                searchArtists = SpotifySearch.searchArtists(name);
                model.addAttribute("collection", searchArtists.getItems());
            case "track":
                searchTracks = SpotifySearch.searchTracks(name);
                model.addAttribute("collection", searchTracks.getItems());
        }

        return "index";
    }


    @ResponseBody
    @RequestMapping("/testInsert")
    public Paging<Track> testInsert() {

        return searchTracks;
//        User user = new User();
//
//        long id = this.userRepositoryCustom.getMaxEmptyId() +1;
//        int idx = (int) (id % NAMES.length);
//        String name = NAMES[idx] + " " + id;
//
//        user.setId(id);
//        user.setName(name);
//        user.setName(name + "ski");
//        this.userRepository.insert(user);
//
//        return "Inserted: " + user;
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
