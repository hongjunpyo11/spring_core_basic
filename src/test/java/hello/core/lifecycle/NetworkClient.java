package hello.core.lifecycle;

public class NetworkClient {

    public String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }

    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}

/**
 * 빈 등록 초기화 메서드
 *   * 메서드 이름을 자유롭게 줄 수 있다.
 *   * 스프링 빈이 스프링 코드에 의존하지 않는다.
 *   * 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있다.
 *
 * 종료 메서드 추론
 *   * @Bean의 destroyMethod 속성에는 아주 특별한 기능이 있다.
 *   * 라이브러리는 대부분 close , shutdown 이라는 이름의 종료 메서드를 사용한다.
 *   * @Bean의 destroyMethod 는 기본값이 (inferred) (추론)으로 등록되어 있다.
 *   * 이 추론 기능은 close , shutdown 라는 이름의 메서드를 자동으로 호출해준다. 이름 그대로 종료 메서드를 추론해서 호출해준다.
 *     따라서 직접 스프링 빈으로 등록하면 종료 메서드는 따로 적어주지 않아도 잘 동작한다.
 *   * 추론 기능을 사용하기 싫으면 destroyMethod="" 처럼 빈 공백을 지정하면 된다.
 */
