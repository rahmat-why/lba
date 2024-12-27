package LBAJXLibrariesV1.dto;

public class DtoCombobox {
    private String value;
    private String display;
    private String helper;

    public DtoCombobox(String value, String display) {
        this.value = value;
        this.display = display;
    }

    public DtoCombobox(String value, String display, String helper) {
        this.value = value;
        this.display = display;
        this.helper = helper;
    }

    public DtoCombobox() {}

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getHelper() {
        return helper;
    }

    public void setHelper(String helper) {
        this.helper = helper;
    }

    @Override
    public String toString() {
        return display;
    }
}
