package ru.learnup.march;

import java.util.List;

public interface ContactsMapper {

    Contact getContactById(Long id);

    List<Contact> getContacts();

}
