package hello.core;


import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) //@Configuration 어노테이션 안에 component 기능도 있어서
)

public class AutoAppConfig {
    @Bean(name = "memoryMemberRepository1")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
