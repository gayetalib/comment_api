package sn.pts.comment.web.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sn.pts.comment.entity.Account;
import sn.pts.comment.entity.User;
import sn.pts.comment.web.dto.request.UserReqDTO;
import sn.pts.comment.web.dto.request.auth.SigninRequestDTO;
import sn.pts.comment.web.dto.request.auth.SignupRequestDTO;
import sn.pts.comment.web.dto.response.UserRspDTO;
import sn.pts.comment.web.dto.response.auth.AuthenticatedUserInfosDTO;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
//@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper extends EntityMapper<User, UserReqDTO, UserRspDTO> {
    User map(SignupRequestDTO request);
    UserRspDTO mapSiteDetails (User user);

    @Override
    default List<UserRspDTO> toDtoList(List<User> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "firstName", source = "user.firstname")
    @Mapping(target = "lastName", source = "user.lastname")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "accountDetails.id", source = "id")
    @Mapping(target = "accountDetails.accountType", source = "accountType")
    @Mapping(target = "accountDetails.firstLogin", source = "firstLogin")
    @Mapping(target = "accountDetails.login", source = "login")
    @Mapping(target = "accountDetails.enabled", source = "enabled")
    AuthenticatedUserInfosDTO map(Account account);

//    @Mapping(target = "enabled", source = "user.account.enabled")
//    @Mapping(target = "accountType", source = "user.account.accountType")
//    @Mapping(target = "userType", source = "user", qualifiedByName = "formatUserType")
//    @Mapping(target = "siteId", source = "user", qualifiedByName = "formatUserSite")
//    UserRspTreeDTO map(User user);
//    List<UserRspTreeDTO> map(List<User> users);

    User map(SigninRequestDTO signinRequestDTO);

    //ResetPasswordReqDTO map (ResetOrForgetPasswordReqDTO dto);

}
