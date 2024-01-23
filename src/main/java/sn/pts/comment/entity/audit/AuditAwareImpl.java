/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/02/2022
 */

package sn.pts.comment.entity.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import sn.pts.comment.security.services.UserDetailsImpl;

import java.util.Optional;

import static sn.pts.comment.commons.constant.SecurityConstants.SYSTEM;


@Component
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();

                return Optional.of(user.getUsername());
            } else return Optional.of(SYSTEM);
        } else {
            return Optional.of(SYSTEM);
        }
    }
}
