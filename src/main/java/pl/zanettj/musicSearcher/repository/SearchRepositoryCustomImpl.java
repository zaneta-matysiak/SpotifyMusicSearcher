package pl.zanettj.musicSearcher.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pl.zanettj.musicSearcher.domain.Search;

@Repository
public class SearchRepositoryCustomImpl implements SearchRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public long getMaxEmptyId() {
        Query query = new Query().with(new Sort(Sort.Direction.DESC, "id"));
        Search maxObject = mongoTemplate.findOne(query.limit(1), Search.class);
        if (maxObject == null) {
            return 0L;
        }
        return maxObject.getId();
    }
}
