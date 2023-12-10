package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//공연기획자 역할을 수행하는 config
@Configuration /** 스프링으로 전환 시작 */ // 설정정보에 해당 어노테이션 작성하고 각 메소드에 bean 작성 => 스프링 컨테이너에다가 return되는 객체들이 빈으로 등록됨
public class AppConfig {
    /**어느 구현클래스를 사용할지는 여기서 지정한다.*/

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    /** 중복되는 함수들은 정리하고 리팩토링을 진행한다*/
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
