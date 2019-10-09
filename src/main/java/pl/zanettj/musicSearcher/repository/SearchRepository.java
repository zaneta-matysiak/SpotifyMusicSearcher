package pl.zanettj.musicSearcher.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import pl.zanettj.musicSearcher.model.Search;
import java.util.List;

@NoRepositoryBean
public interface SearchRepository extends MongoRepository<Search, Long> {

    List<Search> getAllSearch();

    List<Search> getAllBySearchQueryLikeOrderBySearchQuery(String query);
}
