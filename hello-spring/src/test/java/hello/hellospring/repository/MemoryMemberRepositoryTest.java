package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //테스트가 끝날 때마다 불리는 콜백 메소드
    public void afterEach(){
        repository.clearStore();   //저장소 지우기
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //Optional에서 값을 꺼낼땐 get으로 꺼낼 수 있다. 물론 좋은건 아닌데 테스트 코드니까 뭐

        //<검증은 어케할까??>
        //new해서 한거랑 DB에서 꺼낸거랑 같으면 참

        //방법1.
        //System.out.println("result = " + (result==member));
        //방법2. 항상 글자를 읽을 순 없으니, Assertion이용 (쥬피터)
        //Assertions.assertEquals(member, result);
        //방법3. assertj버전 Assertions <= 좀 더 편하게 쓸 수 있음
        assertThat(member).isEqualTo(result);

        //실무에서는 빌드 툴이랑 엮어서, 빌드할때 testcase 통과못하면, 다음단계로 못 넘어가게 막아버린다.
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member1);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}