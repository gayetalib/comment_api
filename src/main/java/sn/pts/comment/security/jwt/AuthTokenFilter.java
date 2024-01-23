package sn.pts.comment.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import sn.pts.comment.exception.CommentException;
import sn.pts.comment.security.services.UserDetailsServiceImpl;
import sn.pts.comment.web.tools.response.CommentMessage;

import java.io.IOException;
import java.util.Objects;

import static sn.pts.comment.commons.constant.SecurityConstants.AUTH_WHITELIST_START_WITH;

public class AuthTokenFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenFilter.class);
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Qualifier("handlerExceptionResolver")
    @Autowired
    private HandlerExceptionResolver resolver;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (isUnSecurePath(AUTH_WHITELIST_START_WITH, request.getServletPath())) {
            try {
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                resolver.resolveException(request, response, null, new CommentException(CommentMessage.INTERNAL_SERVER_ERROR));
            }
        } else {
            processDoFilter(request, response, filterChain);
        }

    }

    private void processDoFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String username;

        try {
            String jwt = jwtUtils.getJwt(request);
            username = jwtUtils.extractUsername(jwt);

            if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (Boolean.TRUE.equals(jwtUtils.isTokenValid(jwt, userDetails))) {

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

                filterChain.doFilter(request, response);
            } else
                resolver.resolveException(request, response, null, new CommentException(CommentMessage.INVALID_TOKEN, "JWT token is required"));
        } catch (MalformedJwtException e) {
            LOGGER.error(String.format("Invalid token found for %s", UrlUtils.buildFullRequestUrl(request)));
            resolver.resolveException(request, response, null, new CommentException(CommentMessage.INVALID_TOKEN, "Invalid JWT token"));

        } catch (ExpiredJwtException e) {
            LOGGER.error(String.format("Invalid token found for %s", UrlUtils.buildFullRequestUrl(request)));
            resolver.resolveException(request, response, null, new CommentException(CommentMessage.INVALID_TOKEN_EXPIRED));

        } catch (UnsupportedJwtException e) {
            LOGGER.error(String.format("Invalid token found for %s", UrlUtils.buildFullRequestUrl(request)));
            resolver.resolveException(request, response, null, new CommentException(CommentMessage.INVALID_TOKEN, "JWT token is unsupported"));

        } catch (IllegalArgumentException e) {
            LOGGER.error(String.format("Invalid token found for %s", UrlUtils.buildFullRequestUrl(request)));
            resolver.resolveException(request, response, null, new CommentException(CommentMessage.INVALID_TOKEN, "JWT claims string is empty"));
        } catch (UsernameNotFoundException e) {
            LOGGER.error(String.format("Invalid token found for %s", UrlUtils.buildFullRequestUrl(request)));
            resolver.resolveException(request, response, null, new CommentException(CommentMessage.INVALID_TOKEN, "JWT token is required"));
        }

    }

    boolean isUnSecurePath(String[] list, String path) {
        boolean containsPath = false;

        for (String s : list) {
            if (path.startsWith(s)) {
                containsPath = true;
                break;
            }
        }

        return containsPath;
    }

}
