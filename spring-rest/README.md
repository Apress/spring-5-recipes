# Spring 5 recipes - ch4 Spring REST
> REST 활용 예제

## 4-2. REST 서비스로 JSON 발행하기

### 4-2-1. Getter 규칙에 맞지 않는 외부 객체 사용 시에 ObjectMapper Visibility 설정
* KafkaAdminClient 같은 외부 라이브러를 이용하는 경우 Json 출력이 정상출력이 되는지
  - 직렬화를 위한 객체 구성이 어려운 외부 라이브러리를 사용하는 경우 ObjectMapper 사용 시에 주의가 필요하다
  - KafkaAdminClient 의 TopicListing 클래스의 경우 name 필드의 getter 가 name() 으로 되어 있어 출력이 안되기 때문에 아래와 같이 명시적으로 public 필드에 접근하는 것과 같이 Visibility 설정을 변경해야 합니다
```java
@Override
public String toString() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    return mapper.writeValueAsString(object);
}
```
> which would make all member fields serializable without further annotations, instead of just public fields (default setting).
> <br>
> 결국 이 방식으로 출력하는 경우 객체로 넘길 수 없기 때문에 반환값이 모두 String 이 되어야만 Json 출력이 되며, 객체로 모두 관리하고 싶다면, 모든 객체를 동일하게 정의하여 직렬화할 수 있도록 구성해야만 한다

### 4-2-2. Kafka Admin 기능을 서비스에 내재화 하는 방법 설계
* Confluent API 기능이 제한적이라, KafkaAdminClient 라이브러리를 활용하는 경우 커플링을 어떻게 피할 수 있을까?
  - Interface 를 이용하여 제공하는 API 메소드를 추상화 할 것
  - Service 와 반환 DTO 객체의 구현에만 의존성을 가지도록 범위를 최소화 할 것
  - 쳐기서 DTO 객체의 반환 값에 대한 처리를 별도의 래퍼 클래스를 통해 반환할 것
* 외부 객체의 반환값을 인터페이스에서 가겨가기 싫은데 어떻게 하면 좋을까?
  - 래퍼 클래스를 선언하고, ObjectMapper 이용하여 toString 추상화 함수를 상속받은 개별 클래스를 구성하는 방안 
  - 이러한 경우 객체를 반환하지 못하고, String 으로 반환해야 하는 단점이 존재한다

> [엔터프라이즈 서비스 버스](https://ko.wikipedia.org/wiki/엔터프라이즈_서비스_버스) : 엔터프라이즈 서비스 버스(영어: Enterprise service bus, 약칭 ESB)는 서비스들을 컴포넌트화된 논리적 집합으로 묶는 핵심 미들웨어이며, 비즈니스 프로세스 환경에 맞게 설계 및 전개할 수 있는 아키텍처 패턴이다.


### 4-2-3. Kafka 혹은 Zookeeper 접근이 불가능한 경우에도 안정적인 서비스를 위한 설계
* Kafka 접근이 일시적으로 안 되는 경우 (몇 분까지 일시적이라고 볼 것인가?)
  - [Admin Client Configuration](https://docs.confluent.io/platform/current/installation/configuration/admin-configs.html) 페이지를 참고하여 학습합니다
  - 웹 관리도구의 경우 클러스터 서비스에 최대한 영향이 없어야 하며, 부하의 원인이 되어서는 안된다는 것이 원칙
    - API 및 Connection 타임아웃은 각각 6초, 3초로 접속 실패 시에 최대한 빠르게 종료합니다
    - 재시도 횟수는 500ms 백오프 후에 최대 1회만 수행하도록 합니다
```java
    // API 요청에는 접속이 필수이므로, 접속의 타임아웃 값은 API 요청 타임아웃 보다는 작아야 한다 - 3/6초로 축소
    props.put(CommonClientConfigs.REQUEST_TIMEOUT_MS_CONFIG, 3000); // 커넥션 생성 요청 시에 타임아웃 시간 (default: 30 seconds)
    // 애플리케이션 기동 시에 리퀘스트 타임아웃 값보다는 API 타임아웃이 크게 설정되어야 하며, Client API 호출의 타임아웃 값 설정
    props.put(CommonClientConfigs.DEFAULT_API_TIMEOUT_MS_CONFIG, 6000); // API 호출 타임아웃 (default: 60 secs)

    // 아래의 RECONNECT 정보는 접속 실패 시에 지수적으로 늘려가면서 접속을 시도하는 시간을 의미 - 접속유지를 위해서는 설정을 유지할 필요 있음
    props.put(CommonClientConfigs.RECONNECT_BACKOFF_MAX_MS_CONFIG, 1000); // 연속적인 접속 실패시에 Backoff 지수적 상승 최대 시간 (default: 1 second)
    props.put(CommonClientConfigs.RECONNECT_BACKOFF_MS_CONFIG, 50); // Backoff 초기 시간 (default: 50ms -> 100ms ... 1000ms)

    // 아래의 RETRY 설정은 RECONNECT 실패가 반복되거나 여러가지 이유(타임아웃 등)로 접속에 실패한 경우 다시 시도하는 횟수 - 횟수만 1회로 조정
    props.put(CommonClientConfigs.RETRY_BACKOFF_MS_CONFIG, 500); // 리퀘스트 요청 실패 시에 대기 후에 다시 요청하는 시간 (default: 100ms)
    // 실패하는 경우 재시도하지 않으면, 일시적인 장애에도 서버를 계속 재시작 해주어야 하므로, 설정 조정이 필요함
    props.put(CommonClientConfigs.RETRIES_CONFIG, 1); // 리퀘스트 요청 실패 시에 최대 재시도하는 횟수 (default: 0 or 2147483647)
```
* Zookeeper 접근이 일시적으로 안 되는 경우
  - Kafka 접속만 된다면, 조회는 가능 하지만, POST 등의 변경 시에 500 오류발생
    - 이 경우에도 기존에 전송된 토픽이 생성되어 있는 경우가 있으므로, 주의가 필요함