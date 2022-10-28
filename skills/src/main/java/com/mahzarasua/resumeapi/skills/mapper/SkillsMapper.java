package com.mahzarasua.resumeapi.skills.mapper;

import com.mahzarasua.resumeapi.configuration.config.MyUserDetails;
import com.mahzarasua.resumeapi.configuration.domain.user.UserResponse;
import com.mahzarasua.resumeapi.configuration.mapper.ResumeMapper;
import com.mahzarasua.resumeapi.configuration.model.Skill;
import com.mahzarasua.resumeapi.configuration.model.User;
import com.mahzarasua.resumeapi.skills.domain.SkillsRequest;
import com.mahzarasua.resumeapi.skills.domain.SkillsResponse;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SkillsMapper extends ResumeMapper {

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

        factory.classMap(Skill.class, SkillsResponse.SkillsResponses.class)
                .field("id.id","id")
                .field("id.resumeId","resumeId")
                .byDefault().mapNulls(false).register();
        factory.classMap(Skill.class, SkillsRequest.SkillsRequests.class)
                .field("id.id","id")
                .field("id.resumeId","resumeId")
                .byDefault().mapNulls(false).register();
        factory.classMap(SkillsRequest.SkillsRequests.class, Skill.class)
                .field("id","id.id")
                .field("resumeId","id.resumeId")
                .byDefault().mapNulls(false).register();
        factory.classMap(SkillsResponse.SkillsResponses.class, Skill.class)
                .field("id","id.id")
                .field("resumeId","id.resumeId")
                .byDefault().mapNulls(false).register();

        return factory;
    }
}
