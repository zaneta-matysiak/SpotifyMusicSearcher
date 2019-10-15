package pl.zanettj.musicSearcher.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Saved_Search")
public class Search {

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
