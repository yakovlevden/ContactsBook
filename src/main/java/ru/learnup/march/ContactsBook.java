package ru.learnup.march;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ContactsBook {

    private HashMap<String, Contact> contactsMap = new HashMap<String, Contact>();

    public void add(Contact contact) {
        contactsMap.put(contact.getPhone(), contact);
    }

    public Contact getByPhone(String phone) {
        return contactsMap.get(phone);
    }

    public void removeByPhone(String phone) {
        if (contactsMap.containsKey(phone)) {
            contactsMap.remove(phone);
        } else {
            throw new NoSuchElementException("Контакт по т." + phone + " не найден.");
        }
    }

    public List<Contact> getContactsSortByName() {
        List<Contact> contactsList = new ArrayList<Contact>(contactsMap.values());
        Collections.sort(contactsList);
        return contactsList;
    }

    public List<Contact> searchBy(Predicate<Contact> predicate) {
        List<Contact> resultList = new ArrayList<Contact>();
        for (Contact contact : contactsMap.values()) {
            if (predicate.test(contact)) {
                resultList.add(contact);
            }
        }
        Collections.sort(resultList);
        return resultList;
    }

    public List<Contact> searchBy(String formula) {
        int index = formula.indexOf("*");
        if (index == -1) {
            return searchBy(c -> c.getName().contentEquals(formula));
        }

        String prefix = formula.substring(0, index);
        String suffix = "";
        if (index < formula.length()) {
            suffix = formula.substring(index + 1);
        }
        String finalSuffix = suffix;
        return searchBy(c -> c.getName().startsWith(prefix) && c.getName().endsWith(finalSuffix));
    }

    public Set<Integer> getCountries() {
        return contactsMap.values().stream()
                .map(Contact::getCode)
                .distinct()
                .collect(Collectors.toSet());
    }
}
