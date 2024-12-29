package dev.ngdangkietswe.swejavacommonshared.aspect.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ngdangkietswe
 * @since 12/29/2024
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecuredAuth {

    SecuredAction action() default SecuredAction.READ;
    SecuredResource resource() default SecuredResource.UNSPECIFIED;
    String requestId() default "";
}
