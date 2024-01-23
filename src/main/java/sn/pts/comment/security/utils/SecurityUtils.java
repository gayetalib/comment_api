package sn.pts.comment.security.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import sn.pts.comment.commons.utils.UtilityClass;
import sn.pts.comment.entity.Account;
import sn.pts.comment.entity.Permission;
import sn.pts.comment.entity.Role;
import sn.pts.comment.entity.User;
import sn.pts.comment.entity.audit.Auditable;
import sn.pts.comment.exception.CommentException;
import sn.pts.comment.repository.UserRepository;
import sn.pts.comment.security.services.UserDetailsImpl;
import sn.pts.comment.web.dto.mapper.UserMapper;
import sn.pts.comment.web.dto.response.auth.AuthenticatedUserInfosDTO;
import sn.pts.comment.web.tools.response.CommentMessage;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class for Spring Security.
 */

@Component
public class SecurityUtils {

    private static UserMapper userMapper;
    private static UserRepository userRepository;

    @Autowired
    private SecurityUtils(UserMapper userMapper, UserRepository userRepository) {
        SecurityUtils.userMapper = userMapper;
        SecurityUtils.userRepository = userRepository;
    }

    public static AuthenticatedUserInfosDTO formatUser(Account account) {
        AuthenticatedUserInfosDTO details = userMapper.map(account);
//        if (AccountType.isCctUser(account.getAccountType())) {
//            UserCCT cct = (UserCCT) account.getUser();
//            details.setSite(siteMapper.map(cct.getSite()));
//        }

        return details;
    }

    public static Optional<UserDetailsImpl> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication)) return Optional.empty();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
            return Optional.of(userPrincipal);
        } else return Optional.empty();
    }

    public static User getAuthenticatedUserInfos() throws CommentException {
        UserDetailsImpl auth = SecurityUtils.getAuthenticatedUser().orElseThrow(() -> new CommentException(CommentMessage.USER_NOT_FOUND, " in the authentication context"));

        if (Objects.nonNull(auth.getDetails())) {
            return userRepository.findById(auth.getDetails().getId()).orElseThrow(() -> new CommentException(CommentMessage.GENERIC_ERROR, "inability to recover the current user's"));
        } else
            throw new CommentException(CommentMessage.GENERIC_ERROR, "inability to recover the current user's information's");
    }

    public static boolean hasPermissions(Set<Permission> permissions) throws CommentException {
        Set<Permission> userPermissions = getAuthenticatedUserPermissions();
        return UtilityClass.ArrayUtility.checkIfAllValuesAreInList(new ArrayList<>(permissions), new ArrayList<>(userPermissions));
    }

    public static Set<Permission> getAuthenticatedUserPermissions() throws CommentException {
        return getAuthenticatedUserInfos().getRoles().stream()
                .filter(Auditable::isEnabled)
                .map(Role::getPermissions).flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
