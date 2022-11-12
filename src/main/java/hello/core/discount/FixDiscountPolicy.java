package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; // 1000원 할인인
   @Override
    public int discount(Member member, int price) {
       if (member.getGrade() == Grade.VIP) {
           return discountFixAmount;
       } else {
           return 0;
       }
    }
}

/**
 * 조회 빈이 2개 이상 - 문제
 * 스프링 빈 조회에서 학습했듯이 타입으로 조회하면 선택된 빈이 2개 이상일 때 문제가 발생한다.
 * DiscountPolicy 의 하위 타입인 FixDiscountPolicy , RateDiscountPolicy 둘다 스프링 빈으로 선언해보자.
 *
 * 오류메시지가 친절하게도 하나의 빈을 기대했는데 fixDiscountPolicy , rateDiscountPolicy 2개가 발견되었다고 알려준다.
 *
 * 이때 하위 타입으로 지정할 수 도 있지만, 하위 타입으로 지정하는 것은 DIP를 위배하고 유연성이 떨어진다.
 * 그리고 이름만 다르고, 완전히 똑같은 타입의 스프링 빈이 2개 있을 때 해결이 안된다.
 * 스프링 빈을 수동 등록해서 문제를 해결해도 되지만, 의존 관계 자동 주입에서 해결하는 여러 방법이 있다.
 */
