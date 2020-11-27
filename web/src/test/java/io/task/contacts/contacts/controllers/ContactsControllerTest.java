package io.task.contacts.contacts.controllers;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import io.task.contacts.contacts.models.Contact;
import io.task.contacts.contacts.services.impl.ContactServiceImpl;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Shayan Ahmad (v-shahmad) on 11/27/2020.
 */
@SpringBootTest
@AutoConfigureMockMvc
class ContactsControllerTest {
    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final List<Contact> CONTACT_LIST = Arrays.asList(
            Contact.builder().id(1L).build(),
            Contact.builder().id(2L).build(),
            Contact.builder().id(3L).build()
    );
    public static final Page<Contact> CONTACT_PAGED_RESULT = new PageImpl<>(CONTACT_LIST);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactServiceImpl contactService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should get values when only size given.")
    void shouldReturnResultWhenOnlySizeGiven() throws Exception {
        // GIVEN
        final Integer page = 0;
        final Integer size = 10;
        final String searchQuery = null;

        when(contactService.getContactList(page, size, searchQuery)).thenReturn(CONTACT_PAGED_RESULT);

        // WHEN & THEN
        mockMvc.perform(get("/contacts")
                .param("page", page.toString())
                .param("size", size.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("contacts"))
                .andExpect(model().attribute("currentPage", equalTo(page)))
                .andExpect(model().attribute("currentPageSize", equalTo(size)))
                .andExpect(model().attribute("query", nullValue()))
                .andExpect(model().attribute("totalPages", equalTo(1)))
                .andExpect(model().attribute("totalRecords", equalTo(3L)));

        verify(contactService).getContactList(page, size, searchQuery);
        verifyNoMoreInteractions(contactService);
    }

    @Test
    @DisplayName("Should get values when only size and query given.")
    void shouldReturnResultWhenOnlySizeAndSearchQueryGiven() throws Exception {
        // GIVEN
        final Integer size = 10;
        final String searchQuery = "Q";

        when(contactService.getContactList(DEFAULT_PAGE_NUMBER, size, searchQuery)).thenReturn(CONTACT_PAGED_RESULT);

        // WHEN & THEN
        mockMvc.perform(get("/contacts")
                .param("size", size.toString())
                .param("query", searchQuery))
                .andExpect(status().isOk())
                .andExpect(view().name("contacts"))
                .andExpect(model().attribute("currentPage", equalTo(DEFAULT_PAGE_NUMBER)))
                .andExpect(model().attribute("currentPageSize", equalTo(size)))
                .andExpect(model().attribute("query", searchQuery))
                .andExpect(model().attribute("totalPages", equalTo(1)))
                .andExpect(model().attribute("totalRecords", equalTo(3L)));

        verify(contactService).getContactList(DEFAULT_PAGE_NUMBER, size, searchQuery);
        verifyNoMoreInteractions(contactService);
    }

    @Test
    @DisplayName("Should reset page number to zero when page, size and query are given. To avoid conditions where pageNumber > totalPages")
    void shouldReturnResultWhenEverythingIsGiven() throws Exception {
        // GIVEN
        final Integer givenPage = 3;
        final Integer givenSize = 10;
        final String givenSearchQuery = "Query";
        when(contactService.getContactList(DEFAULT_PAGE_NUMBER, givenSize, givenSearchQuery)).thenReturn(CONTACT_PAGED_RESULT);

        // WHEN & THEN
        mockMvc.perform(get("/contacts")
                .param("page", givenPage.toString())
                .param("size", givenSize.toString())
                .param("query", givenSearchQuery))
                .andExpect(status().isOk())
                .andExpect(view().name("contacts"))
                .andExpect(model().attribute("currentPage", equalTo(DEFAULT_PAGE_NUMBER)))
                .andExpect(model().attribute("currentPageSize", equalTo(givenSize)))
                .andExpect(model().attribute("query", givenSearchQuery))
                .andExpect(model().attribute("totalPages", equalTo(1)))
                .andExpect(model().attribute("totalRecords", equalTo(3L)));

        verify(contactService).getContactList(DEFAULT_PAGE_NUMBER, givenSize, givenSearchQuery);
        verifyNoMoreInteractions(contactService);
    }

}