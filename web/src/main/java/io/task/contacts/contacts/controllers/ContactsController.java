package io.task.contacts.contacts.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.task.contacts.contacts.models.Contact;
import io.task.contacts.contacts.services.ContactService;

/**
 * REST Controller to interact with user contacts.
 */
@RestController
public class ContactsController {

    public static final String CONTACT_LIST_URL = "/api/contacts";

    @Value("${pagination.default.page}")
    private Integer default_page_number;

    @Value("${pagination.default.size}")
    private Integer default_page_size;

    private ContactService contactService;

    public ContactsController(ContactService contactService) {
        this.contactService = contactService;
    }

    /**
     * API REST endpoint to query the contact list.
     * TODO
     *
     * @param pageNumber is Optional value for pagination. If nothing is given, it will default to 0.
     * @param recordSize is Optional value for pagination. If nothing is given, it will default to 20.
     * @return list of @{Contact}
     */
    @GetMapping(CONTACT_LIST_URL)
    public @ResponseBody
    List<Contact> getContactList(@RequestParam(value = "page", required = false) Integer pageNumber,
                                 @RequestParam(value = "size", required = false) Integer recordSize) {

        Integer page = getPageNumber(pageNumber);
        Integer size = getRecordSize(recordSize);

        return contactService.getContactList(page, size);
    }

    private Integer getPageNumber(Integer pageNumber) {
        return (Objects.isNull(pageNumber) || pageNumber < 0)
                ? default_page_number
                : pageNumber;
    }

    private Integer getRecordSize(Integer recordSize) {
        return (Objects.isNull(recordSize) || recordSize < 1)
                ? default_page_size
                : recordSize;
    }

}
