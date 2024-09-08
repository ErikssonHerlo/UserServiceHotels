package com.erikssonherlo.common.infraestructure.security.anotation;

import com.erikssonherlo.common.application.security.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Method;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Aspect
@Component
@RequiredArgsConstructor
public class RoleValidationInterceptor {
    private final JWTService jwtService;

    @Before("@annotation(ValidateRole)")
    public void validateRole(JoinPoint joinPoint) {
        // Extract the token from the request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = extractToken(request);

        // Get the required role included in the annotation
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ValidateRole validateRole = method.getAnnotation(ValidateRole.class);
        String requiredRole = validateRole.value();

        // Get the role from the token
        String userRole = jwtService.getRoleFromToken(token);

        if (!userRole.equals(requiredRole)) {
            throw new ResponseStatusException(FORBIDDEN, "Acceso por rol denegado");
        }
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
