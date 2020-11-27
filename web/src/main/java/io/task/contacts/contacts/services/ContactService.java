package io.task.contacts.contacts.services;

import org.springframework.data.domain.Page;

import io.task.contacts.contacts.models.Contact;

/**
 * To serve queries against contacts.
 */
public interface ContactService {

    /**
     * Search contacts by name. With wildcard capability.
     *
     * @param page        refers to the page number for pagination.
     * @param size        refers to the record size pertaining to page number.
     * @param searchQuery is the query for name which would be checked against the existing records.
     * @return paginated list of {@link Contact}
     */
    Page<Contact> getContactList(final int page, final int size, final String searchQuery);

}
