package com.openlabs.framework.util;

import com.openlabs.framework.config.AppConfig;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ObjectConverter {


    private ObjectConverter() {
    }

    private static ModelMapper getModelMapper() {
        ApplicationContext ac
                = new AnnotationConfigApplicationContext(AppConfig.class);
        return ac.getBean(ModelMapper.class);
    }

    public static <S, T> T toObject(S source, Class<T> targetClass) {
        ModelMapper mapper = getModelMapper();
        return mapper.map(source, targetClass);
    }
}
