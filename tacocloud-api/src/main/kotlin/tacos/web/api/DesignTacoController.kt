package tacos.web.api


import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import tacos.Taco
import tacos.data.TacoRepository

@RestController
@RequestMapping(path = ["/design"], produces = ["application/json"])
@CrossOrigin(origins = ["*"])
class DesignTacoController(
    private val tacoRepository: TacoRepository,
) {

//    @GetMapping("/recent")
//    fun recentTacos(): MutableList<Taco> {
//        val page = PageRequest.of(0, 12, Sort.by("createdAt").descending())
//        return tacoRepository.findAll(page).content
//    }

    @GetMapping("/recent")
    fun recentTacos(): Flux<Taco> {
        return tacoRepository.findAll().take(12)
    }

//    @GetMapping("/{id}")
//    fun tacoById(@PathVariable id: Long): ResponseEntity<Taco>? {
//        val tacoOptional = tacoRepository.findById(id)
//        return if (tacoOptional.isEmpty) ResponseEntity(HttpStatus.NOT_FOUND)
//        else ResponseEntity.ok(tacoOptional.get())
//    }

    @GetMapping("/{id}")
    fun tacoById(@PathVariable id: Long): Mono<Taco> {
        return tacoRepository.findById(id)
    }

//    @PostMapping(consumes = ["application/json"])
//    @ResponseStatus(HttpStatus.CREATED)
//    fun postTaco(@RequestBody taco: Taco): Taco {
//        return tacoRepository.save(taco)
//    }

//    @PostMapping(consumes = ["application/json"])
//    @ResponseStatus(HttpStatus.CREATED)
//    fun postTaco(@RequestBody tacoMono: Mono<Taco>): Flux<Taco> {
//        // saveAll은 Mono, Flux 등 리액티브 스트림을 구현한 어떤 타입의 인자도 받을 수 있다.
//        // saveAll은 기본적으로 Flux<>를 반환한다.
//        return tacoRepository.saveAll(tacoMono)
//    }

    @PostMapping(consumes = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun postTaco(@RequestBody taco: Taco): Mono<Taco> {
        return tacoRepository.save(taco)
    }
}