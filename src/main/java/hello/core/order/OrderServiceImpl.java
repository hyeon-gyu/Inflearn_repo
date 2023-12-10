package hello.core.order;


import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    /**이렇게 구현 클래스를 바꿔치지 하는것은 DIP원칙 위반이다. 인터페이스에만 의존하는 것이 아닌 구현클래스에도 의존하고 있다는 것을 나타냄 */

    private final MemberRepository memberRepository; /** final이 붙은 변수들은 필수값이라서 무조건 활용할 필요가 있다고 판한*/
    private final DiscountPolicy discountPolicy; /** */


    @Autowired /** 생성자가 하나만 잇으면 autowired 생략가능 */
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy; // autowired는 타입으로 조회하기 때문에 discountpolicy 객체종류로 컴포넌트가 두개 존재한다면 필드명,파라미터명으로 빈 이름작성
    }
/** RequiredArgsContructor annotation을 쓰면 final로 선언된 객체들을 인자로 한 생성자가 자동으로 만들어진다! 따로 구현할 필요가 없다.*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
