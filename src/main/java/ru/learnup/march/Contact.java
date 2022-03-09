package ru.learnup.march;

public class Contact implements Comparable<Contact> {

    private String name;
    private String phone;
    private Long id;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    @Override
    public int compareTo(Contact o) {
        return name.compareTo(o.getName());
    }

    public int getCode() {
        return Integer.valueOf(phone.substring(0, phone.indexOf('-')));
    }
}
