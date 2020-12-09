package Steps;

public enum SortOrder {
    ПоУмолчанию("По умолчанию", 101),
    Дешевле("Дешевле", 1),
    Дороже("Дороже", 2),
    ПоДате("По дате", 104);
    private String value;
    private int id;

    SortOrder(String value, int id) {
        this.id = id;
        this.value = value;
    }

    public String getValue() { return value; }

    public int getId() { return id; }
}
