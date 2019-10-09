package pl.zanettj.musicSearcher.domain;

import lombok.*;
import org.modelmapper.ModelMapper;

@NoArgsConstructor
@Getter
@Setter
public class Track {

    private String album;
    private String artists;
    private Integer discNumber;
    private Integer durationMs;
    private Boolean explicit;
    private String href;
    private String id;
    private Boolean isPlayable;
    private Integer popularity;
    private String previewUrl;
    private Integer trackNumber;
    private String uri;
    public String name;


    public static Track[] mapTracks(com.wrapper.spotify.model_objects.specification.Track[] spotifyTracks)
    {
        ModelMapper modelMapper = new ModelMapper();
        Track[] tracks = new Track[spotifyTracks.length];
        for(int i=0; i< spotifyTracks.length; i++){
            tracks[i] = modelMapper.map(spotifyTracks[i], Track.class);
            tracks[i].album = spotifyTracks[i].getAlbum().getName();
            String[] artists = new String[spotifyTracks[i].getArtists().length];
            for(int j=0; j<spotifyTracks[i].getArtists().length; j++){
                artists[j] = spotifyTracks[i].getArtists()[j].getName();
            }
            tracks[i].artists = String.join(", ", artists);
        }
        return tracks;
    }
}
