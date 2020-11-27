package io.task.contacts.contacts.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import io.task.contacts.contacts.models.Contact;
import io.task.contacts.contacts.repositories.ContactJpaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by Shayan Ahmad (v-shahmad) on 11/27/2020.
 */
@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    private static final int PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 10;

    @Mock
    private ContactJpaRepository contactJpaRepository;

    @InjectMocks
    private ContactServiceImpl underTest;

    @Test
    @DisplayName("Should return contacts based on query when searchQuery is present.")
    void testWhenAllTheParamsGiven_getContactList() {
        // GIVEN
        final String searchQuery = "asd";
        final Page<Contact> expectedContactResult = Mockito.mock(Page.class);
        when(contactJpaRepository.findByName(eq(searchQuery), any(PageRequest.class)))
                .thenReturn(expectedContactResult);

        // WHEN
        Page<Contact> result = underTest.getContactList(PAGE_NUMBER, PAGE_SIZE, searchQuery);

        // THEN
        assertEquals(expectedContactResult, result);
        verify(contactJpaRepository, never()).findAll(any(PageRequest.class));
        verify(contactJpaRepository).findByName(eq(searchQuery), any(PageRequest.class));
        verifyNoMoreInteractions(contactJpaRepository);
    }

    @Test
    @DisplayName("Should return everything when searchQuery is absent.")
    void testWithoutSearchQuery_getContactList() {
        // GIVEN
        final String searchQuery = null;
        final Page<Contact> expectedContactResult = Mockito.mock(Page.class);
        when(contactJpaRepository.findAll(any(PageRequest.class)))
                .thenReturn(expectedContactResult);

        // WHEN
        Page<Contact> result = underTest.getContactList(PAGE_NUMBER, PAGE_SIZE, searchQuery);

        // THEN
        assertEquals(expectedContactResult, result);
        verify(contactJpaRepository, never()).findByName(eq(searchQuery), any(PageRequest.class));
        verify(contactJpaRepository).findAll(any(PageRequest.class));
        verifyNoMoreInteractions(contactJpaRepository);
    }
}