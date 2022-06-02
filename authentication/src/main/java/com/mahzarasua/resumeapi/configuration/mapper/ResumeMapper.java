package com.mahzarasua.resumeapi.configuration.mapper;

import com.mahzarasua.resumeapi.configuration.config.MyUserDetails;
import com.mahzarasua.resumeapi.configuration.domain.user.UserResponse;
import com.mahzarasua.resumeapi.configuration.model.User;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Primary
public class ResumeMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory){
        factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDate.class));
        configureMapper(factory);
    }

    public MapperFactory configureMapper(MapperFactory factory){
        factory.classMap(User.class, UserResponse.class)
                .byDefault().mapNulls(false).register();
        factory.classMap(User.class, MyUserDetails.class)
                .byDefault().mapNulls(false).register();

        return factory;
    }
}
