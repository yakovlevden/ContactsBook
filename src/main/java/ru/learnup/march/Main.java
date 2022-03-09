package ru.learnup.march;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory;
        ContactsMapper contactsMapper;
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            contactsMapper = sqlSessionFactory.openSession().getMapper(ContactsMapper.class);
            List<Contact> contacts = contactsMapper.getContacts();

            ContactsBook contactsBook = new ContactsBook();
            for (Contact contact : contacts) {
                contactsBook.add(contact);
            }

            for (String arg : args) {
                log.info("Отфильтрованный список контактов по \"" + arg + "\":");
                List<Contact> filteredContacts1 = contactsBook.searchBy(arg);
                for (Contact contact : filteredContacts1) {
                    log.info(contact.getName() + " т." + contact.getPhone());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printContact(ContactsBook contactsBook, String phone) {
        Contact contact = contactsBook.getByPhone(phone);
        if (contact != null) {
            System.out.println("Найден контакт: " + contact.getName() + " т." + contact.getPhone());
        } else {
            System.out.println("Контакт по т." + phone + " не найден");
        }
    }
}
