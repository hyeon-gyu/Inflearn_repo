package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long id);

    //optional: 객체를 wrapping하는 객체
    // 사용 이유: id가 null값인 경우 null 그대로 반환 대신 optional로 감싸서 반환
    Optional<Member> findByName(String name);

    List<Member> findAll();
}

/**
 * 스프링을 왜 사용할까?
 * 객체지향적인 설계가 좋다 = 인터페이스를 두고 구현체를 바꿔끼우기 쉽다
 * 스프링컨테이너가 지원해줌 ( +DI 기능)
 * */
