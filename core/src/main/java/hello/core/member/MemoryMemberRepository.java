package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//원래 인터페이스랑 구현체는 다른 패키지 안에 두는 게 설계 상 좋아.

@Component
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    // HashMap을 썼는데, 원래는 동시성 이슈 때문에 컨커런트 HashMap 써야해
    // 그치만 예제니까 간단히 하겠다.

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

    //예제니까 간단성을 위해 예외처리는 생략함.
}
