package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.reposittory.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class) //junit 실행시 spring과 함께
@SpringBootTest // springboot container 활용해야하므로 필요
@Transactional // 끝나면 롤백하는 용도
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
//    @Rollback(value = false)
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("lim");

        //when
        Long saveId = memberService.join(member);
        //then
        Assert.assertEquals(member, memberRepository.findOne(saveId));
    }


    @Test(expected = IllegalStateException.class)
    public void 중복_회원_제외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
//        try {
//            memberService.join(member2); //예외발생해야한다!
//        } catch (IllegalStateException e){
//            return; // 성공으로 테스트케이스가 종료하는 것이 정상 -> test annotation 내부에 exception 설정하면 57번줄과 같이 알아서 에러터지는거 잡아줌
//        }
        memberService.join(member2); //예외발생해야한다!
            //then
        Assert.fail("예외가 발생해야 한다."); //여기에 도달하면 안됨!!
    }
}