package pl.zanettj.musicSearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/log")
public class LogController {

    @GetMapping
    public String log(Model model) throws IOException {
        File file = ResourceUtils.getFile("classpath:logs/spotifyMusicSearcher.log");

        //Read File Content
        String content = new String(Files.readAllBytes(file.toPath()));

        model.addAttribute("logcontent", content);

        return "index";
    }
}
