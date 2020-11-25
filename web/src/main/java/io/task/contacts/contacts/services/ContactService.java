package io.task.contacts.contacts.services;

import java.util.List;

import io.task.contacts.contacts.models.Contact;

/**
 * To serve queries against contacts.
 */
public interface ContactService {
    List<Contact> getContactList(int page, int size);
    
}
