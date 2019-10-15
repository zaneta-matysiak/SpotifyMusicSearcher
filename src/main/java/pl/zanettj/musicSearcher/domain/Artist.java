package pl.zanettj.musicSearcher.domain;

import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.Arrays;

@Data
@NoArgsConstructor
public class Artist {

    private String[] genres;
    private String href;
    private String id;
    private String name;
    private Integer popularity;
    private String uri;

    public static Artist[] mapArtists(com.wrapper.spotify.model_objects.specification.Artist[] spotifyArtists) {
        ModelMapper modelMapper = new ModelMapper();
        Artist[] artists = Arrays.stream(spotifyArtists).map(spotifyArtist -> modelMapper
                .map(spotifyArtist, Artist.class))
                .toArray(Artist[]::new);
        return artists;
    }
}
