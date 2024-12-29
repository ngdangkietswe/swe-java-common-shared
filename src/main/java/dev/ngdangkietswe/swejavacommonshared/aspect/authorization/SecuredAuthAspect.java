package dev.ngdangkietswe.swejavacommonshared.aspect.authorization;

import com.google.protobuf.GeneratedMessageV3;
import dev.ngdangkietswe.swejavacommonshared.utils.ProtoUtil;
import dev.ngdangkietswe.sweprotobufshared.proto.common.GrpcUtil;
import dev.ngdangkietswe.sweprotobufshared.proto.domain.SweGrpcPrincipal;
import dev.ngdangkietswe.sweprotobufshared.proto.exception.GrpcAccessDeniedException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author ngdangkietswe
 * @since 12/29/2024
 */

@Aspect
public abstract class SecuredAuthAspect {

    @Before(value = "@annotation(securedAuth)")
    protected void enforceAccessControl(JoinPoint joinPoint, SecuredAuth securedAuth) {
        var principal = GrpcUtil.getGrpcPrincipal();
        var userPermissions = principal.getUserPermission().getPermissions();
        if (hasPermission(joinPoint, securedAuth, userPermissions)) {
            return;
        }
        throw new GrpcAccessDeniedException();
    }

    private boolean hasPermission(JoinPoint joinPoint, SecuredAuth securedAuth, List<SweGrpcPrincipal.Permission> permissions) {
        if (CollectionUtils.isEmpty(permissions)) {
            return false;
        }

        var action = securedAuth.action();

        // If the action is UPSERT, determine if it's an update or create action based on the request field value
        if (securedAuth.action().equals(SecuredAction.UPSERT)) {
            String requestId = securedAuth.requestId();
            String requestFieldValue = getRequestFieldValue(joinPoint, requestId);
            action = StringUtils.isNotEmpty(requestFieldValue) ? SecuredAction.UPDATE : SecuredAction.CREATE;
        }

        var actionRequired = action;
        return permissions.stream()
                .anyMatch(permission -> permission.getAction().equalsIgnoreCase(actionRequired.name())
                        && permission.getResource().equalsIgnoreCase(securedAuth.resource().name()));
    }

    private String getRequestFieldValue(JoinPoint joinPoint, String requestId) {
        Object request = joinPoint.getArgs()[0];
        return request instanceof GeneratedMessageV3
                ? (String) ProtoUtil.getProtobufFieldValue(requestId, (GeneratedMessageV3) request)
                : null;
    }
}
