package hello.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository; //인터페이스, 추상화에만 의존 DIP 지킴

    public MemberServiceImpl(MemberRepository memberRepository) { // 구체적인건 밖에서 생성해 넣어준다. 생성자 주입
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
    //관례같은 건데, 구현체 하나만 있을 때는 인터페이스명+ Impl을 관례상 많이 쓴다.
}
