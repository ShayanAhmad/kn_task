package io.task.contacts.contacts.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.task.contacts.contacts.models.Contact;

/**
 * Pagination enabled Contact JPA repository.
 */
@Repository
public interface ContactJpaRepository extends JpaRepository<Contact, Long> {
    String SEARCH_BY_NAME_QUERY = "SELECT c FROM Contact c WHERE c.name LIKE %?1%";

    /**
     * Search contacts by name. With wildcard capability.
     *
     * @param searchQuery is the query for name which would be checked against the existing records.
     * @return paginated list of {@link Contact}
     */
    @Query(SEARCH_BY_NAME_QUERY)
    Page<Contact> findByName(String searchQuery, Pageable pageable);
}
