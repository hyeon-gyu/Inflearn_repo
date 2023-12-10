package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach //각각의 테스트 메소드 동작전에 실행
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        //직접 리포지토리를 건드리는것이 아니라 외부에서 의존적으로 생성해서 작동
        // = dependency injection
    }

    @AfterEach //테스트 메소드 하나가 실행된 이후 실행되는 콜백함수
    //테스트 케이스는 독립적으로 수행됨
    public void afterEach(){
        memberRepository.clearStore();
    }


    @Test //테스트코드에서는 함수명을 한글로 적어도 됨
//    void join() {
//    }
    void 회원가입(){ //gradle에서 한글 깨지는것도 잡아야함
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        Long saveId = memberService.join(member);
        //then 구조로 나눠서 코드를 작성하자.
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원처리(){
        //give
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //when

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("already joined same member name");

        /** 변수 추출 명령어 : ctrl alt v */
//        try { //너무 번거롭게 try catch 쓰지는 말자
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("already joined same member name");
//        }
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}