package io.task.contacts.contacts.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.task.contacts.contacts.models.Contact;
import io.task.contacts.contacts.repositories.ContactJpaRepository;
import io.task.contacts.contacts.services.ContactService;

/**
 * Implementation of the {@link ContactService}.
 */
@Service
public class ContactServiceImpl implements ContactService {

    private ContactJpaRepository contactJpaRepository;

    public ContactServiceImpl(ContactJpaRepository contactJpaRepository) {
        this.contactJpaRepository = contactJpaRepository;
    }

    @Override
    public Page<Contact> getContactList(final int page, final int size, final String searchQuery) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return StringUtils.isEmpty(searchQuery)
                ? contactJpaRepository.findAll(pageRequest)
                : contactJpaRepository.findByName(searchQuery, pageRequest);
    }
}
