package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//MemberRepository 구현체

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    // 실무에서는 동시성 문제가 있을 수 있어서, 공유되는 변수일때는 ConcurrentHashMap을 써야한다.

    private static long sequence = 0L;
    // 시퀀스는 0,1,2 키값을 생성해주는 애라고 생각하기.
    // 이것도 실무에서는 Long에서 하는것보다, 동시성 문제를 고려해서 AtomicLong이런걸 써야함.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member->member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //자바에서 실무할땐 list를 많이쓴다.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
