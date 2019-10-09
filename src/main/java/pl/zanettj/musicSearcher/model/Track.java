package pl.zanettj.musicSearcher.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@Getter
@Setter
public class Track {
    //private final AlbumSimplified album;
    //private final ArtistSimplified[] artists;

    private String album;
    //private String[] artists;
    private Integer discNumber;
    private Integer durationMs;
    private Boolean explicit;
    private String href;
    private String id;
    private Boolean isPlayable;
    private String name;
    private Integer popularity;
    private String previewUrl;
    private Integer trackNumber;
    private String uri;


    public static Track[] mapTracks(com.wrapper.spotify.model_objects.specification.Track[] spotifyTracks)
    {
        ModelMapper modelMapper = new ModelMapper();

        Track[] tracks = new Track[spotifyTracks.length];

        for(int i=0; i< spotifyTracks.length; i++){
            tracks[i] = modelMapper.map(spotifyTracks[i], Track.class);
            tracks[i].album = spotifyTracks[i].getAlbum().getName();
        }

        return tracks;
    }
}
