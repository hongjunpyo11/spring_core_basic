package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Bean만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.
 *   memberRepository() 처럼 의존관계 주입이 필요해서 메서드를 직접 호출할 때 싱글톤을 보장하지 않는다.
 *   크게 고민할 것이 없다. 스프링 설정 정보는 항상 @Configuration 을 사용하자.
 */
@Configuration
public class AppConfig {

    //@Bean memberService --> new MemoryMemberRepository()
    //@Bean orderService --> new MemoryMemberRepository()

    /**
     * memberService 빈을 만드는 코드를 보면 memberRepository() 를 호출한다.
     *   이 메서드를 호출하면 new MemoryMemberRepository() 를 호출한다.
     * orderService 빈을 만드는 코드도 동일하게 memberRepository() 를 호출한다.
     *   이 메서드를 호출하면 new MemoryMemberRepository() 를 호출한다.
     *
     * 결과적으로 각각 다른 2개의 MemoryMemberRepository 가 생성되면서 싱글톤이 깨지는 것 처럼 보인다.
     * 스프링 컨테이너는 이 문제를 어떻게 해결할까? --> 테스트 해보자(ConfigurationSingletonTest)
     */

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }



}
