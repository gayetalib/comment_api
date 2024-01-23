package sn.pts.comment.web.dto.response.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class AuthenticatedUserInfosDTO implements Serializable {

    private Long id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String role;
    private AuthenticatedAccountInfosDTO accountDetails;
}