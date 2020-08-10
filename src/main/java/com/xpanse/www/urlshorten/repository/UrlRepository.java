package com.xpanse.www.urlshorten.repository;

import com.xpanse.www.urlshorten.dao.UrlDao;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<UrlDao, Long> {
}
