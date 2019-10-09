package pl.zanettj.musicSearcher.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.zanettj.musicSearcher.domain.Search;

@Repository
public interface SearchRepository extends MongoRepository<Search, Long> {

}
