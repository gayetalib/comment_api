package sn.pts.comment.web.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.pts.comment.commons.constant.AppConstants;
import sn.pts.comment.web.tools.constraints.PatternConstraint;
import sn.pts.comment.web.tools.constraints.PatternType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserReqDTO {

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @Email
    @Size(max = 100)
    private String email;

    @Size(min = AppConstants.PHONE_NUMBER_LENGTH, max = AppConstants.PHONE_NUMBER_LENGTH)
    @PatternConstraint(type = PatternType.SN_PHONE_NUMBER)
    private String phoneNumber;

    @NotBlank
    private String jobTitle;
    @NotNull
    private AccountReqDTO account;
    private Long siteId; // for CCT User
    private Long structureId; // for State User
    private Long cashRegisterId;
}