package com.mobiquity.enums;

public enum FileType {
    CSV("csv"), //comma separated
    XML("xml"),
    YAML("yml"); // Tab delimited

    public static final String DEFAULT = "txt";
    String extensionType;

    FileType(String extensionType) {
        this.extensionType = extensionType;
    }

    public String getExtensionType() {
        return this.extensionType;
    }

}
