package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    /**
     * 다양한 의존관계 주입 방법
     *   의존관계 주입은 크게 4가지 방법이 있다.
     *     * 생성자 주입 - 이름 그대로 생성자를 통해서 의존 관계를 주입 받는 방법이다, 지금까지 우리가 진행했던 방법이 바로 생성자 주입이다.
     *       * 특징: * 생성자 호출시점에 딱 1번만 호출되는 것이 보장된다.
     *              * 불편 필수 의존관계에 사용
     *                * 중요: 생성자가 딱 1개만 있으면 @Autowired를 생략해도 자동 주입 된다. 물론 스프링 빈에만 해당한다.
     *     * 수정자 주입(setter 주입) - setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입하는 방법이다.
     *       * 특징: * 선택, 변경 가능성이 있는 의존관계에 사용
     *              * 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.
     *                * 참고: @Autowired 의 기본 동작은 주입할 대상이 없으면 오류가 발생한다.
     *                       주입할 대상이 없어도 동작하게 하려면 @Autowired(required = false) 로 지정하면 된다.
     *
     *     * 필드 주입 - 이름 그대로 필드에 바로 주입하는 방법이다.
     *       * 특징: 코드가 간결해서 많은 개발자들을 유혹하지만 외부에서 변경이 불가능해서 테스트 하기 힘들다는 치명적인 단점이 있다.
     *              DI 프레임워크가 없으면 아무것도 할 수 없다.
     *              사용하지 말자!
     *                * 애플리케이션의 실제 코드와 관계 없는 테스트 코드 or
     *                * 스프링 설정을 목적으로 하는 @Configuration 같은 곳에서만 특별한 용도로 사용
     *
     *     * 일반 메서드 주입 - 일반 메서드를 통해서 주입 받을 수 있다.
     *       * 특징
     *         * 한번에 여러 필드를 주입 받을 수 있다.
     *         * 일반적으로 잘 사용하지 않는다.
     */
}
