package pl.zanettj.musicSearcher.model;

import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Track;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "Search_Archive")
public class SearchResult {

    @Id
    Long id;
    @Field(value = "search_time")
    String searchTime;
    @Field(value = "search_type")
    String searchType;
    @Field(value = "search_query")
    String searchQuery;
    @Field(value = "search_artist_result")
    Artist[] searchArtistResult;
    @Field(value = "search_track_result")
    Track[] searchTrackResult;

}
