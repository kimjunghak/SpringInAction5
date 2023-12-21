package tacos.web.react


import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import tacos.data.mongodb.TacoRepository
import tacos.mongodb.Taco

@RestController
@RequestMapping(path = ["/design"], produces = ["application/json"])
@CrossOrigin(origins = ["*"])
class ReactiveDesignTacoController(
    private val tacoRepository: TacoRepository,
) {

    @GetMapping("/recent")
    fun recentTacos(): Flux<Taco> {
        return tacoRepository.findByOrderByCreatedAtDesc().take(12)
    }

    @GetMapping("/{id}")
    fun tacoById(@PathVariable id: Long): Mono<Taco> {
        return tacoRepository.findById(id)
    }

    @PostMapping(consumes = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun postTaco(@RequestBody taco: Taco): Mono<Taco> {
        return tacoRepository.save(taco)
    }
}