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