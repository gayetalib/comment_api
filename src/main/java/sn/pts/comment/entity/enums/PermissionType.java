package sn.pts.comment.entity.enums;

import org.apache.commons.lang3.EnumUtils;

public enum PermissionType {
    //ROLE MANAGEMENT
    READ_ROLE(""),
    WRITE_ROLE(""),
    DELETE_ROLE(""),
    CHANGE_ROLE_STATUS(""),
    WRITE_ROLE_USER("");

    private final String formattedName;

    PermissionType(String formattedName) {
        this.formattedName = formattedName;
    }

    public static boolean findByName(String name) {
        return EnumUtils.isValidEnum(PermissionType.class, name.toUpperCase());
    }

    public String getFormattedName() {
        return formattedName;
    }
}
