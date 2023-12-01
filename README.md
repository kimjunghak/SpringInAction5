# Spring in Action 5 정리 (Kotlin + Spring)

### tacocloud-ui
Angular 구버전으로 현재 실행 불가 -> React 로 변경..?

### Spring Hateoas
Spring Boot 2.2.0 이후로 변경된 점
- ResourceSupport -> RepresentationModel
- Resource -> EntityModel
- Resources -> CollectionModel
- PagedResources -> PagedModel
- ResourceAssembler -> RepresentationModelAssembler
- ControllerLinkBuilder -> WebMvcLinkBuilder

### RabbitMQ
RabbitMQ 를 docker 로 실행
```bash
docker-compose up -d rabbitmq
```
TacoCloudApplication(Producer): 8080 & TacoKitchenApplication(Consumer): 8081 실행

### Kafka
#### Kafka 실행법(Default)
1. kafka를 다운을 받은 후 압축을 해제
2. ./zookeeper-server-start.sh -daemon ../config/zookeeper.properties / zookeeper 실행 -> 분산 시스템 간의 정보 공유, 상태 체크, 동기화 등을 위한 검증된 분산 코디네이션 서비스
3. ./kafka-server-start.sh -daemon ../config/server.properties / kafka 실행

#### Spring 설정
`producer 설정법`
```kotlin
@Bean
fun kafkaAdmin(): KafkaAdmin {
    val configs: MutableMap<String, Any> = HashMap()
    configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
    return KafkaAdmin(configs)
}

@Bean
fun newTopic() = NewTopic("tacocloud.orders.topic", 10, 1)
```
- spring-kafka에서 제공하는 kafka admin client를 이용하여 Topic을 생성한다. -> cli에서 직접 생성하는 것이 어렵거나 귀찮은 경우

```yaml
spring: 
  kafka:
    bootstrap-servers:
      - localhost:9092
    template:
      default-topic: tacocloud.orders.topic
    producer:
      key-serializer: org.springframework.kafka.support.serializer.JsonSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
```
- kafka를 하나만 띄운 상태이기 때문에 bootstrap-servers 는 우선 한개만 설정
- key, value serializer 를 이용하여 객체 전송 가능

`consumer 설정법`
```yaml
spring:  
  kafka:
    consumer:
      group-id: tacocloud-group
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring.json.trusted.packages: "*"
```
- group-id 가 없는 경우 `No group.id found in consumer config, container properties, or @KafkaListener annotation; a group.id is required when group management is used.` 에러 발생
- spring.json.trusted.packages를 사용하지 않을 경우 `class is not in the trusted packages` 에러 발생
  - 직렬화, 역직렬화 과정에서는 package 이름까지 포함하기때문에 consumer 쪽에서 에러 발생

spring-kafka 에 대해서 간단히 알아보았지만 kafka에 대해서 좀 더 깊게 알아볼 필요를 느꼈다 RabbitMQ 와 다르게 복잡하다..
