package io.task.contacts.contacts.controllers;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.task.contacts.contacts.models.Contact;
import io.task.contacts.contacts.services.ContactService;

/**
 * REST Controller to interact with user contacts.
 */
@Controller
public class ContactsController {

    private static Integer currentPageNumber = 0;
    private static Integer currentPageSize = 50;

    private final ContactService contactService;

    public ContactsController(ContactService contactService) {
        this.contactService = contactService;
    }

    /**
     * API REST endpoint to query the contact list.
     *
     * @param page     is Optional value for pagination. If nothing is given, it will default to 0.
     * @param size is Optional value for pagination. If nothing is given, it will default to 20.
     * @param model      is Spring MVC attribute.
     * @return name of the Thymeleaf page.
     */
    @GetMapping(value = "/contacts")
    public String getContactList(@RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "size", required = false) Integer size,
                                 Model model) {

        // TODO: add another parameter for nameSearch, pass that to service (if condition to pass values to service),
        //  get the result and paginate and send it back!

        currentPageNumber = getPageNumber(page);
        currentPageSize = getRecordSize(size);
        Page<Contact> contacts = contactService.getContactList(currentPageNumber, currentPageSize);

        addAttributesInModel(model, contacts);

        return "contacts";
    }

    private void addAttributesInModel(Model model, Page<Contact> contacts) {
        model.addAttribute("contactList", contacts.getContent());
        model.addAttribute("currentPage", currentPageNumber);
        model.addAttribute("currentPageSize", currentPageSize);
        model.addAttribute("totalPages", contacts.getTotalPages());
        model.addAttribute("totalRecords", contacts.getTotalElements());
    }

    private Integer getPageNumber(Integer newPageNumber) {
        return (Objects.isNull(newPageNumber) || newPageNumber < 0)
                ? currentPageNumber
                : newPageNumber;
    }

    private Integer getRecordSize(Integer newRecordSize) {
        return (Objects.isNull(newRecordSize) || newRecordSize < 1)
                ? currentPageSize
                : newRecordSize;
    }

}
