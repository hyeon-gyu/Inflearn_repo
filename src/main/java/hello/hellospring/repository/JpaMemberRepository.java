package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    /** JPA를 사용하려면 ENTITIYMANAGER를 주입받아야한다!*/

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        /** jpa가 insert 쿼리 만들어서 db에 집어넣고 id까지 세팅해줌*/
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class,id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }


    @Override
    public List<Member> findAll() {
        /** Entity를 대상으로 query를 날려서 조회한 다음 객체 자체를 조회*/
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
