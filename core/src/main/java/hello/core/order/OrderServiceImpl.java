package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    //DIP 지키고 있음! 인터페이스에만 의존!

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy ;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // 구현이 잘됨! OrderService에서는 할인에 대한 걸 모른다. 할인에 대한 건 DiscountPolicy 너가 알아서 해줘!
        // 단일 체계 원칙을 잘 지킴. 주문과 할인을 분리함.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}