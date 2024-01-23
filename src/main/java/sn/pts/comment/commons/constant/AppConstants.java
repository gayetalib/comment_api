package sn.pts.comment.commons.constant;

public class AppConstants {

    // for authentication
    public static final Integer ATTEMPT_CACHE_DURATION_TIME = 1;
    public static final Integer MAX_LOGIN_ATTEMPT = 3;
    // end for authentication

    // for validation
    public static final int PHONE_NUMBER_LENGTH = 13;
    public static final int PASSCODE_MIN_LENGTH = 6;
    public static final int PASSCODE_MAX_LENGTH = 9;
    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 50;
    // end for validation

    // for storage directory
    public static final String SLIDER_IMAGE_DIRECTORY = "saytu/sliders";
    public static final String POST_IMAGE_DIRECTORY = "saytu/posts";
    public static final String SITE_IMAGE_DIRECTORY = "saytu/sites";
    public static final String BATCH_PROCESSING_FILES_DIRECTORY = "batch/files/processing";
    public static final String BATCH_RESULTS_FILES_DIRECTORY = "batch/files/results";
    public static final String MAIL_SAYTU = "tima@yopmail.com";
    public static final String MAIL_SAYTU_OBJECT = "Contact SAYTU";

    // end for storage directory

    private AppConstants() {
    }
}
