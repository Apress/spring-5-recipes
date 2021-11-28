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

### 4-2-1. Kafka Admin 기능을 서비스에 내재화 하는 방법 설계
* Confluent API 기능이 제한적이라, KafkaAdminClient 라이브러리를 활용하는 경우 커플링을 어떻게 피할 수 있을까?
  - Interface 를 이용하여 제공하는 API 메소드를 추상화 할 것
  - Service 와 반환 DTO 객체의 구현에만 의존성을 가지도록 범위를 최소화 할 것
  - 쳐기서 DTO 객체의 반환 값에 대한 처리를 별도의 래퍼 클래스를 통해 반환할 것
* 외부 객체의 반환값을 인터페이스에서 가겨가기 싫은데 어떻게 하면 좋을까?
  - 래퍼 클래스를 선언하고, ObjectMapper 이용하여 toString 추상화 함수를 상속받은 개별 클래스를 구성하는 방안 
  - 이러한 경우 객체를 반환하지 못하고, String 으로 반환해야 하는 단점이 존재한다

> [엔터프라이즈 서비스 버스](https://ko.wikipedia.org/wiki/엔터프라이즈_서비스_버스) : 엔터프라이즈 서비스 버스(영어: Enterprise service bus, 약칭 ESB)는 서비스들을 컴포넌트화된 논리적 집합으로 묶는 핵심 미들웨어이며, 비즈니스 프로세스 환경에 맞게 설계 및 전개할 수 있는 아키텍처 패턴이다.

