package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService = new MemberService();
    //test이후에 repository clear해주고 싶은데 멤버 서비스 밖에 없네? => 정의해주기
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    //근데 service의 멤버 레포지토리랑 다른 객체야 => 같은 걸 쓰는게 낫겠죠.

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore(); //저장소 지우기
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long savedId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(savedId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    //예외 플로우가 중요! - 중복회원가입하는 경우
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //근데 이거때문에 try-catch 넣는게 애매해 => assertThrows이용
        /*
        try{
            memberService.join(member2); //예외가 터질것이다.
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}