package sn.pts.comment.web.tools.constraints;

public enum PatternType {
    NUMERIC("^[0-9]+$"),
    ALPHANUMERIC("^[a-zA-Z0-9\\sÀ-ÿ]+$"),
    MATRICULATION("^[a-zA-Z0-9\\sÀ-ÿ\\-_]+$"),
    NUMBER("^[+-]?([0-9]*[.])?[0-9]+$"),
    SN_PHONE_NUMBER("^\\+221\\d{9}$"),
    STRING("^[a-zA-Z]+$");

    private final String pattern;

    PatternType(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}