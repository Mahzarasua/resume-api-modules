package com.mahzarasua.resumeapi.education.mapper;

import com.mahzarasua.resumeapi.configuration.config.MyUserDetails;
import com.mahzarasua.resumeapi.configuration.domain.user.UserResponse;
import com.mahzarasua.resumeapi.configuration.mapper.ResumeMapper;
import com.mahzarasua.resumeapi.configuration.model.School;
import com.mahzarasua.resumeapi.configuration.model.User;
import com.mahzarasua.resumeapi.education.domain.EducationRequest;
import com.mahzarasua.resumeapi.education.domain.EducationResponse;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CustomMapper extends ResumeMapper {

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

        factory.classMap(School.class, EducationResponse.EducationResponses.class)
                .field("id.id","id")
                .field("id.resumeId","resumeId")
                .byDefault().mapNulls(false).register();
        factory.classMap(EducationRequest.EducationRequests.class, School.class)
                .field("id","id.id")
                .field("resumeId","id.resumeId")
                .byDefault().mapNulls(false).register();

        return factory;
    }
}
