package com.adidas.subscriptions.repository;

import com.adidas.subscriptions.model.Newsletter;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NewsletterRepository extends CrudRepository<Newsletter, Long> {
    Optional<Newsletter> findById (Long id);
}
