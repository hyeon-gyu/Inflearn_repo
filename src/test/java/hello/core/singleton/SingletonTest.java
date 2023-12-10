package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        //1.조회 : 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2.조회 : 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 서로 다른 것을 확인
//        System.out.println("memberservice1 = " + memberService1);
//        System.out.println("memberservice2 = " + memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singtletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonservice1 " + singletonService1);
        System.out.println("singletonservice2 " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);

        // same == 객체 비교하려면 이걸로, isEqual은 수치 비교
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1.조회 : 호출할 때마다 같은 객체를 생성
        MemberService memberService1 = ac.getBean("memberService", MemberServiceImpl.class);
        MemberService memberService2 = ac.getBean("memberService", MemberServiceImpl.class);
        //참조값이 서로 다른 것을 확인
//        System.out.println("memberservice1 = " + memberService1);
//        System.out.println("memberservice2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
