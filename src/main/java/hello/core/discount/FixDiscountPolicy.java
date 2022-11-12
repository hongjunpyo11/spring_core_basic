package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
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

/**
 * 조회 빈이 2개 이상일 때 해결 방법
 *   * @Autowired 필드 명 매칭
 *   * @Qualifier @Qualifier끼리 매칭 빈 이름 매칭
 *   * @Primary 사용
 *
 * @Autowired 는 타입 매칭을 시도하고, 이때 여러 빈이 있으면 필드 이름, 파라미터 이름으로 빈 이름을 추가 매칭한다.
 *
 * @Autowired 필드 명 매칭 정리
 * 1. 타입 매칭
 * 2. 타입 매칭의 결과가 2개 이상일 때 필드 명, 파라미터 명으로 빈 이름 매칭
 *
 * @Qualifier 사용
 *   * @Qualifier 는 추가 구분자를 붙여주는 방법이다. 주입시 추가적인 방법을 제공하는 것이지 빈 이름을
 *     변경하는 것은 아니다.
 *
 * @Qualifier 사용 정리
 * 1. @Qualifier끼리 매칭
 * 2. 빈 이름 매칭
 * 3. NoSuchBeanDefinitionException 예외 발생
 *
 * @Primary 사용
 *   * @Primary 는 우선순위를 정하는 방법이다. @Autowired 시에 여러 빈이 매칭되면 @Primary 가 우선권을
 *     가진다.
 *
 * 우선순위
 * @Primary 는 기본값 처럼 동작하는 것이고, @Qualifier 는 매우 상세하게 동작한다. 이런 경우 어떤 것이 우선권을 가져갈까?
 * 스프링은 자동보다는 수동이, 넒은 범위의 선택권 보다는 좁은 범위의 선택권이 우선 순위가 높다.
 * 따라서 여기서도 @Qualifier 가 우선권이 높다.
*/
