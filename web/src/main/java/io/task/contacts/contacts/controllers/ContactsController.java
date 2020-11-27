package io.task.contacts.contacts.controllers;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.task.contacts.contacts.models.Contact;
import io.task.contacts.contacts.services.ContactService;

/**
 * REST Controller to interact with user contacts.
 */
@Controller
public class ContactsController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 20;

    private static Integer currentPageNumber = DEFAULT_PAGE_NUMBER;
    private static Integer currentPageSize = DEFAULT_PAGE_SIZE;
    private static String currentSearchQuery;

    private final ContactService contactService;

    public ContactsController(ContactService contactService) {
        this.contactService = contactService;
    }

    /**
     * API REST endpoint to query the contact list.
     *
     * @param page  is Optional value for pagination. If nothing is given, it will default to 0.
     * @param size  is Optional value for pagination. If nothing is given, it will default to 20.
     * @param model is Spring MVC attribute.
     * @return name of the Thymeleaf view.
     */
    @GetMapping(value = "/contacts")
    public String getContactList(@RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "size", required = false) Integer size,
                                 @RequestParam(value = "query", required = false) String searchQuery,
                                 Model model) {

        computeCurrentPageNumber(page);
        computeRecordSizeAndResetPageNumber(size);
        computeCurrentSearchQuery(searchQuery);
        Page<Contact> contactsResult = contactService.getContactList(currentPageNumber, currentPageSize,
                currentSearchQuery);

        addContactAttributesInModel(model, contactsResult);

        return "contacts";
    }

    private void computeCurrentSearchQuery(String newSearchQuery) {
        if (!Objects.isNull(newSearchQuery)) {
            currentSearchQuery = newSearchQuery;
        }
    }

    private void addContactAttributesInModel(Model model, Page<Contact> contactsResult) {
        model.addAttribute("contactList", contactsResult.getContent());
        model.addAttribute("currentPage", currentPageNumber);
        model.addAttribute("currentPageSize", currentPageSize);
        model.addAttribute("totalPages", contactsResult.getTotalPages());
        model.addAttribute("totalRecords", contactsResult.getTotalElements());
        model.addAttribute("query", currentSearchQuery);
    }

    private void computeCurrentPageNumber(Integer newPageNumber) {
        if (!Objects.isNull(newPageNumber) && newPageNumber >= 0) {
            currentPageNumber = newPageNumber;
        }
    }

    private void computeRecordSizeAndResetPageNumber(Integer newPageSize) {
        if (!Objects.isNull(newPageSize) && newPageSize >= 1) {
            currentPageSize = newPageSize;
            currentPageNumber = 0;
        }
    }

}
