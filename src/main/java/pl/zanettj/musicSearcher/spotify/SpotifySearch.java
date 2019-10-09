package pl.zanettj.musicSearcher.spotify;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;


public class SpotifySearch {

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
            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static Paging<Artist> searchArtists(String artistName) {
        try {
            Paging<Artist> artistPaging = getSpotifyApi().searchArtists(artistName).build().execute();
            System.out.println("Total: " + artistPaging.getTotal());
            return artistPaging;
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static Paging<Track> searchTracks(String title) {
        try {
            Paging<Track> trackPaging = getSpotifyApi().searchTracks(title).build().execute();
            System.out.println("Total: " + trackPaging.getTotal());
            return trackPaging;
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

}
