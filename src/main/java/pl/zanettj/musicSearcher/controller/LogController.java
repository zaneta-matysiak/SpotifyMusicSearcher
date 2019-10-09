package pl.zanettj.musicSearcher.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/log")
public class LogController {

    static Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @GetMapping
    public String log(Model model) {

        try {
            File file = ResourceUtils.getFile("classpath:logs/spotifyMusicSearcher.log");
            String content = new String(Files.readAllBytes(file.toPath()));
            model.addAttribute("logcontent", content);
            LOGGER.info("Show all user logs.");
        } catch (FileNotFoundException e) {
            LOGGER.error("Error: " + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("Error: " + e.getMessage());
        }

        return "index";
    }
}
