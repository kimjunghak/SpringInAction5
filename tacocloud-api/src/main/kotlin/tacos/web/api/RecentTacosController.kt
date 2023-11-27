package tacos.web.api

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.CollectionModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import tacos.Taco
import tacos.data.TacoRepository


@RepositoryRestController
class RecentTacosController(
    private val tacoRepository: TacoRepository,
    private val pagedModelAssembler: PagedResourcesAssembler<Taco>,
    private val tacoRepresentationModelAssembler: TacoRepresentationModelAssembler,
) {

    @GetMapping(path = ["/tacos/recent"], produces = ["application/hal+json"])
    fun recentTacos(): ResponseEntity<CollectionModel<TacoEntityModel>> {
        val page = PageRequest.of(0, 12, Sort.by("createdAt").descending())


        val tacosPage = tacoRepository.findAll(page)
        val pagedModel = pagedModelAssembler.toModel(tacosPage, tacoRepresentationModelAssembler)

        return ResponseEntity.ok(pagedModel)
    }
}