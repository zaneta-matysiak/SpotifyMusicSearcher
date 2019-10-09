package pl.zanettj.musicSearcher;

import org.slf4j.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpotifyMusicSearcherApplication {

	static Logger LOGGER = LoggerFactory.getLogger(SpotifyMusicSearcherApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpotifyMusicSearcherApplication.class, args);
		LOGGER.info("Start application...");
	}
}
