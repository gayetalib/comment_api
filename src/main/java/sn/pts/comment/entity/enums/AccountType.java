package sn.pts.comment.entity.enums;

import org.apache.commons.lang3.EnumUtils;

import java.util.Arrays;
import java.util.List;

public enum AccountType {
    USER("USER"),
    ADMIN("ADMIN");

    private final String formattedName;

    AccountType(String formattedName) {
        this.formattedName = formattedName;
    }


    public static boolean findByName(String name) {
        return EnumUtils.isValidEnum(AccountType.class, name.toUpperCase());
    }

    public String getFormattedName() {
        return formattedName;
    }
}
