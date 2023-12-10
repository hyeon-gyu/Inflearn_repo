package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


/**
 * 스프링부트 테스트 케이스 작성
 * <p>
 * = 개발한 기능을 실행해서 테스트를 할 때 자바의 main 메소드를 통해 실행하거나
 * 웹 애플리케이션의 컨트롤러를 통해서 해당 기능을 실행한다. 이러한 방법은 준비하고
 * 실행하는데 오래 걸리고, 반복 실행하기 어렵고 여러 테스트를 한번에 실행하기 어렵다는
 * 단점이 있다. 자바는 JUnit이라는 프레임워크로 테스트를 실행해서 이러한 문제를 해결한다.
 * <p>
 * <p>
 * CLASS 단위로 테스트 진행시
 * CLASS 내부 메소드 순서 지정은 안됨. 어떤 메소드가 먼저 실행될지 모르므로
 * 순서에 의존되게 설계하면 안됨. 객체는 다 따로 작성해놔야함.
 */

/**
 * DI에는 생성자 주입(의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 권장), 필드 주입(비추), SETTER 주입(public으로 설정되어야함
 * 실무에서는 주로 정형화된 컨트롤러 서비스, 리포지토리와 같은 코드는
 * 컴포넌트 스캔을 사용한다. 다만 상황에 따라 구현 클래스가 변경해야한다면
 * 스프링 빈으로 직접 등록한다.(16강)
 * autowired를 통핸 DI는 CONTROLLER, SERVICE 어노테이션으로 스프링이 관리하는 객체에서만 동작한다
 * 스프링빈으로 등록하지 않고 직접 생성한 객체에서는 동작X*/

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 0~ 값을 가지는

    //새로운 자바 문법 optional, stream, filter, findany, assertion
    @Override
    public Member save(Member member) {
        member.setId(++sequence); //id는 시스템이 지정해주는 숫자
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //null값도 다룰 수 있게 wrapping 처리하는 optional함수
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //하나 찾으면 곧장 반환(optional로 감싸진채 반환)
    }

    @Override
    //실무에서 자바 쓸 대 리스트를 많이 사용한다.
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
