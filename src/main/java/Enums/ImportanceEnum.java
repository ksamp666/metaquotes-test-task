package Enums;

import Interfaces.CheckboxValues;

import java.util.stream.Stream;

public enum ImportanceEnum implements CheckboxValues {
    Holidays("Holidays"),
    Low("Low"),
    Medium("Medium"),
    High("High");

    private String checkboxLabel;

    ImportanceEnum(String checkboxLabel) {
        this.checkboxLabel = checkboxLabel;
    }

    @Override
    public String getLabel() {
        return checkboxLabel;
    }

    public static ImportanceEnum getImportanceByTitle(String title) {
        return Stream.of(ImportanceEnum.values()).filter(importance -> importance.getLabel().toUpperCase().equals(title.toUpperCase())).findFirst().orElse(null);
    }
}
