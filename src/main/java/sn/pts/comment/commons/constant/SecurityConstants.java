package sn.pts.comment.commons.constant;

public class SecurityConstants {

    public static final String BASE_API_URL = "/kumba-saytu/api/v1";
    public static final String[] AUTH_WHITELIST = {
            //BASE_API_URL + "/**",
            BASE_API_URL + "/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            //"/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            BASE_API_URL + "/portal/**",
            BASE_API_URL + "/ws/**"
    };

    public static final String[] AUTH_WHITELIST_START_WITH = {
            //BASE_API_URL + "/",
            BASE_API_URL + "/auth/",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs",
            "/swagger-resources",
            "/swagger-resources",
            "/configuration/ui",
            //"/configuration/security",
            "/swagger-ui",
            "/webjars",
            "/swagger-ui.html",
            BASE_API_URL + "/portal/",
            BASE_API_URL + "/ws/"
    };

    public static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    public static final String REQUEST_HEADER_NAME = "Authorization";
    public static final String AUTH_HEADER_BEARER_NAME = "Bearer ";
    public static final String SYSTEM = "system";

    public static final Long WEB_ACCESS_DURATION_MN = 100000L * 60 * 60;
    public static final Long WEB_REFRESH_DURATION_HOURS = 1000000L * 60 * 60;
    public static final Long WEB_FORGOT_PASSWORD_DURATION_MIN = 10L * 60 * 60;
    public static final String RESET_PASSWORD_LINK = "auth/reset-password";
    public static final String TOKEN_NAME_IN_MAIL_NOTIFICATION = "credentials";

    private SecurityConstants() {
    }
}
