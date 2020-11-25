package io.task.contacts.contacts.repositories;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import io.task.contacts.contacts.models.Contact;

/**
 * Pagination enabled Contact repository.
 */
@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

//    Page<Contact> findByName(String name, Pageable pageable);

}
