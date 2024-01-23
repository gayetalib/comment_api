package sn.pts.comment.commons.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class CodeGenerator {
    private static final int REFERRAL_CODE_LENGTH = 6;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHKMNPQRSTUVWXYZ23456789";
    private static final String NUMERIC_STRING = "0123456789";
    private static final String POSITIVE_NUMERIC_STRING = "123456789";

    public static String randomAlphaNumeric(int size) {
        StringBuilder builder = new StringBuilder();
        while (size-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());

            builder.append(ALPHA_NUMERIC_STRING.charAt(character));

        }
        return builder.toString();

    }

    public static String randomNumeric(int size) {
        StringBuilder builder = new StringBuilder();
        while (size-- != 0) {
            int character = (int) (Math.random() * NUMERIC_STRING.length());
            builder.append(NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static String randomPositiveNumeric(int size) {
        StringBuilder builder = new StringBuilder();
        while (size-- != 0) {
            int character = (int) (Math.random() * POSITIVE_NUMERIC_STRING.length());
            builder.append(POSITIVE_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static String randomOrderReference() {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        return dateTime + uuid;
    }
}
