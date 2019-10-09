package pl.zanettj.musicSearcher.spotify;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Slf4j
public class SpotifySearch {

    static Logger LOGGER = LoggerFactory.getLogger(SpotifySearch.class);

    private static final String clientId = "612e3cbb366b4415bd11210e082520a2";
    private static final String clientSecret = "b03a26b9cd89474f8f20f01385b4bfce";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .build();

    private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
            .build();

    private static SpotifyApi getSpotifyApi(){
        if (spotifyApi.getAccessToken() == null)
            clientCredentials();
        return spotifyApi;
    }

    public static void clientCredentials() {
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
            //log.info("Expires in: " + clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException e) {
            LOGGER.error("Error: " + e.getMessage());
        }
    }

    public static Paging<Artist> searchArtists(String artistName) {
        try {
            Paging<Artist> artistPaging = getSpotifyApi().searchArtists(artistName).build().execute();
            LOGGER.info("Total results: " + artistPaging.getTotal());
            return artistPaging;
        } catch (IOException | SpotifyWebApiException e) {
            LOGGER.error("Error: " + e.getMessage());
            return null;
        }
    }

    public static Paging<Track> searchTracks(String title) {
        try {
            Paging<Track> trackPaging = getSpotifyApi().searchTracks(title).build().execute();
            LOGGER.info("Total results: " + trackPaging.getTotal());
            return trackPaging;
        } catch (IOException | SpotifyWebApiException e) {
            LOGGER.error("Error: " + e.getMessage());
            return null;
        }
    }

}
