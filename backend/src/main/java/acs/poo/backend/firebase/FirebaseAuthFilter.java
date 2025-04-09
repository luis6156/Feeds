package acs.poo.backend.firebase;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Log4j2
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "firebase.auth.enabled", havingValue = "true")
public class FirebaseAuthFilter extends OncePerRequestFilter {
    private final FirebaseService firebaseService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            FirebaseToken firebaseToken = firebaseService.verifyToken(token.substring(7));
            PreAuthenticatedAuthenticationToken auth = new PreAuthenticatedAuthenticationToken(
                    firebaseToken, null, null);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (FirebaseAuthException fe) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            log.error("FirebaseAuthException occurred while verifying token", fe);
        }
    }
}
