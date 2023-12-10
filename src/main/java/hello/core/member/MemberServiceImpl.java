package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    /** 현재 MemoryMemberRepo 구현체와 의존관계 => dip원칙 위반중*/
    //private final MemberRepository memberRepository = new MemoryMemberRepository();


    /** 여기엔 인터페이스에 관한 코드만 존재해야하므로 다음과 같이 변경(dip원칙 준수)*/
    private final MemberRepository memberRepository;


    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}

