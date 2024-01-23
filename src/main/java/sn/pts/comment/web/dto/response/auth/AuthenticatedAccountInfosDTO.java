package sn.pts.comment.web.dto.response.auth;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import sn.pts.comment.entity.enums.AccountType;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
//exclure les propriétés ayant des valeurs nulles / vides ou par défaut.
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class AuthenticatedAccountInfosDTO implements Serializable {
    //@Serial
    private static final long serialVersionUID = 3336712090424782155L;

    private Long id;
    private String login;
    private boolean firstLogin;
    private AccountType accountType;
    private boolean enabled;
}