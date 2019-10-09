package pl.zanettj.musicSearcher.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "Search_Archive")
public class Search {

    @Id
    Long id;
    @Field(value = "search_time")
    LocalDateTime searchTime;
    @Field(value = "search_type")
    String searchType;
    @Field(value = "search_query")
    String searchQuery;
    @Field(value = "search_result")
    String[] searchResult;

}
