package pl.zanettj.musicSearcher.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pl.zanettj.musicSearcher.model.SearchResult;

@Repository
public class SearchRepositoryCustomImpl implements SearchRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public long getMaxEmptyId() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "id"));
        query.limit(1);
        SearchResult maxObject = mongoTemplate.findOne(query, SearchResult.class);
        if (maxObject == null) {
            return 0L;
        }
        return maxObject.getId();
    }
}
