package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; //1000원할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){ //enum은 ==으로 비교하자
            return discountFixAmount;
        }
        else{
            return 0; //vip면 1000원 할인이라고 알려줘야하기에 return 1000이고 일반 회원이면 할인금액 0원이므로 return 0
        }
    }
}
