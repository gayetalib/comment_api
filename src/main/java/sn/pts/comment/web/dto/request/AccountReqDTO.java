package sn.pts.comment.web.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountReqDTO {

    @NotBlank
    private String login;
    @NotNull
    @Size(min = 6, max = 8)
    private String password;
    @NotNull
    private String accountType;
    //private boolean firstLogin;
    //private int failedAttempt;
    //private Date lockTime;
    //private UserReqDTO userReqDTO;
}
