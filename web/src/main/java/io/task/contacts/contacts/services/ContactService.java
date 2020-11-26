package io.task.contacts.contacts.services;

import org.springframework.data.domain.Page;

import io.task.contacts.contacts.models.Contact;

/**
 * To serve queries against contacts.
 */
public interface ContactService {

    Page<Contact> getContactList(int page, int size);

}
