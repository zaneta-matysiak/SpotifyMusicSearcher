package pl.zanettj.musicSearcher.model;

import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.specification.ExternalUrl;
import com.wrapper.spotify.model_objects.specification.Followers;
import com.wrapper.spotify.model_objects.specification.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
public class Artist {
    private String[] genres;
    private String href;
    private String id;
    //private Image[] images;
    private String name;
    private Integer popularity;
    private String uri;



    public static Artist[] mapArtists(com.wrapper.spotify.model_objects.specification.Artist[] spotifyArtists)
    {
        ModelMapper modelMapper = new ModelMapper();

        Artist[] artists = new Artist[spotifyArtists.length];

        for(int i=0; i< spotifyArtists.length; i++){
            artists[i] = modelMapper.map(spotifyArtists[i], Artist.class);
        }

        return artists;
    }
}
