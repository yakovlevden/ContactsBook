package ru.learnup.march;

import java.util.List;

public class ContactsBookExt extends ContactsBook {

    @Override
    public List<Contact> searchBy(String formula) {
        int index = formula.indexOf("*");
        if (index == -1) {
            return searchBy(c -> c.getName().contains(formula));
        }

        String prefix = formula.substring(0, index);
        String suffix = "";
        if (index < formula.length()) {
            suffix = formula.substring(index + 1);
        }

        String finalSuffix = suffix;
        if (!prefix.isEmpty()) {
            if (!suffix.isEmpty()) {
                return searchBy(c -> {
                    int prefixIndex = c.getName().indexOf(prefix);
                    int suffixIndex = c.getName().indexOf(finalSuffix);
                    return prefixIndex != -1 && prefixIndex < suffixIndex;
                });
            } else {
                return searchBy(c -> c.getName().contains(prefix));
            }
        } else if (!suffix.isEmpty()) {
            return searchBy(c -> c.getName().contains(finalSuffix));
        }
        return searchBy(c -> c.getName() != null);
    }
}