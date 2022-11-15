package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}

/**
 * 웹 스코프의 특징
 *   * 웹 스코프는 웹 환경에서만 동작한다.
 *   * 웹 스코프는 프로토타입과 다르게 스프링이 해당 스코프의 종료시점까지 관리한다. 따라서 종료 메서드가 호출된다.
 *
 * 웹 스코프의 종류
 *   * request: HTTP 요청 하나가 들어오고 나갈 때 까지 유지되는 스코프, 각각의 HTTP 요청마다 별도의 빈 인스턴스가 생성되고, 관리된다.
 *   * session: HTTP Session과 동일한 생명주기를 가지는 스코프
 *   * application: 서블릿 컨텍스트('ServletContext')와 동일한 생명주기를 가지는 스코프
 *   * websoket: 웹 소켓과 동일한 생명주기를 가지는 스코프
 */

/**
 * 실제 고객의 요청이 오지 않았기 때문에 에러
 */
