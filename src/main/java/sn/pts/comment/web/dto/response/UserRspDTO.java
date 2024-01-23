package sn.pts.comment.web.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class UserRspDTO {

    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    private String phoneNumber;

    private String jobTitle;

    private AccountRspDTO account;

}
