package io.task.contacts.contacts.services.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.task.contacts.contacts.models.Contact;
import io.task.contacts.contacts.repositories.ContactRepository;
import io.task.contacts.contacts.services.ContactService;

/**
 * Implementation of the {@link ContactService}.
 */
@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> getContactList(int page, int size) {
        return contactRepository.findAll(PageRequest.of(page, size))
                .toList();
    }
}
