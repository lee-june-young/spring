package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; //1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){ // ==써도 되나? Enum 타입은 == 쓰는 게 맞다.
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}
