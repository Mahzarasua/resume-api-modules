package com.mahzarasua.resumeapi.resume.mapper;

import com.mahzarasua.resumeapi.configuration.config.MyUserDetails;
import com.mahzarasua.resumeapi.configuration.domain.user.UserResponse;
import com.mahzarasua.resumeapi.configuration.mapper.ResumeMapper;
import com.mahzarasua.resumeapi.configuration.model.Resume;
import com.mahzarasua.resumeapi.configuration.model.School;
import com.mahzarasua.resumeapi.configuration.model.Skill;
import com.mahzarasua.resumeapi.configuration.model.User;
import com.mahzarasua.resumeapi.configuration.model.WorkExperience;
import com.mahzarasua.resumeapi.resume.domain.GetResumeResponse;
import com.mahzarasua.resumeapi.resume.domain.ResumeRequest;
import com.mahzarasua.resumeapi.resume.domain.ResumeResponse;
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

        factory.classMap(Resume.class, GetResumeResponse.class)
                .byDefault().mapNulls(false).register();
        factory.classMap(Resume.class, ResumeResponse.class)
                .byDefault().mapNulls(false).register();
        factory.classMap(ResumeRequest.class, Resume.class)
                .byDefault().mapNulls(false).register();
        factory.classMap(ResumeRequest.School.class, School.class)
                .field("id","id.id")
                .field("resumeId","id.resumeId")
                .byDefault().mapNulls(false).register();
        factory.classMap(ResumeRequest.WorkExperience.class, WorkExperience.class)
                .field("id","id.id")
                .field("resumeId","id.resumeId")
                .byDefault().mapNulls(false).register();
        factory.classMap(ResumeRequest.Skill.class, Skill.class)
                .field("id","id.id")
                .field("resumeId","id.resumeId")
                .byDefault().mapNulls(false).register();

        return factory;
    }
}
