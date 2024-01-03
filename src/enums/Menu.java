package enums;

public enum Menu {
    ADD("1", "Add"),
    EXIST("2", "Exist"),
    UPDATE("3", "Update"),
    DELETE("4", "Delete"),
    SEARCH_ID("5.1", "SID"),
    SEARCH_NAME("5.2", "SName"),
    SHOW("6", "Show"),
    SAVE("7", "Save"),
    EXIT("8", "Exit");

    private String id;
    private String value;

    Menu(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

}
