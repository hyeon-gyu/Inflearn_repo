package jpabook.jpashop.reposittory;


import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch { //동적쿼리를 활용해 검색할 키워드 집합 클래스

    private String memberName;
    private OrderStatus orderStatus; //주문상태{ORDER, CANCEL}


}
