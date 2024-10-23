package com.example.Restaurant_Manager_BE.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;


import java.util.Locale;

@RequiredArgsConstructor
@Component
public class LocalizationUtils {
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;


    public String getLocalizedMessage(String messageKey, Object... params) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Locale locale = localeResolver.resolveLocale(request);

        return messageSource.getMessage(messageKey, params, locale);
    }
}
