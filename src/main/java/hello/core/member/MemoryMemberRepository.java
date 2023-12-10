package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
/** 데이터베이스가 확정되지 않은 상황이므로 메모리에 저장하는 방식으로 진행 */
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); /** 여러 군데에서 접근할 수 있기 때문에(동시성이슈) concurrenthashmap 사용권장*/

    @Override
    public void save(Member member) {
        store.put(member.getId(),member);
    }

    @Override
    public Member findById(Long memberID) {
        return store.get(memberID);
    }
}
