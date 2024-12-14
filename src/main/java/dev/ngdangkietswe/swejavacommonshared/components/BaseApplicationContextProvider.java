package dev.ngdangkietswe.swejavacommonshared.components;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author ngdangkietswe
 * @since 12/13/2024
 */

public abstract class BaseApplicationContextProvider implements ApplicationContextAware {

    @Getter
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        BaseApplicationContextProvider.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
