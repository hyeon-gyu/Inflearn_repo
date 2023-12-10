package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/** 1. autowired, service 어노테이션 활용=> component scan 자동 빈 등록 방법
 *  2. 코드로 직접 빈 등록하는 방법 => config 파일 하나 생성해서 class에다가 @configuration 작성하고
 *  등록하려는 메소드에다가 @bean 작성 */


//@Service /**spring container 생성시 controller와 함께 등록해줌*/
/** service 어노테이션 안에 component 어노테이션도 포함되어있음
 * 각각의 component로 다루고 bean으로 등록 (자동으로 bean 등록하는 component scan 방식)
 * REPOSITORY를 추후에 변경할 예정이므로 직접 빈 등록 방식으로 진행한다.*/

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    /** SERVICE객체 생성되면서 memberRepository객체와 연결해줌
     * 직접 bean등록해서 활용시 해당 autowired는 필요없음
     * controller에서 service bean등록하는 autowired 하나만 활용, 나머진 config파일에서 직접 bean 등록*/
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        //같은 이름이 잇으면 안된다!
        validateDupMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDupMember(Member member) {
        memberRepository.findByName(member.getName()).
                ifPresent(m -> {
                    throw new IllegalStateException("already joined same member name");
                });
    }

    /**
     * 전체 회원 조회
     **/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberID) {
        return memberRepository.findById(memberID);
    }
}
