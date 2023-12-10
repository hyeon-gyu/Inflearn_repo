package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MemberApp {
    public static void main(String[] args) {


        //MemberService memberService = new MemberServiceImpl();
        /** 이젠 모든 건 appconfig에서부터 시작*/
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService(); //여기 memberService에는 memberServiceImpl(memorymemberservice)이 들어있게 된다.


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);



        Member member = new Member(1L,"memberA", Grade.VIP); /** 1L이라고 쓴건 long이라 l 붙인거, 1과 같은 의미*/
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member="+member.getName());
        System.out.println("find member="+findMember.getName());
    }

}
