package pl.zanettj.musicSearcher.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface SearchRepositoryCustom {

    long getMaxEmptyId();

}
