package tacos.web.api

import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.server.RepresentationModelAssembler
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.stereotype.Component
import tacos.Taco

@Component
class TacoRepresentationModelAssembler: RepresentationModelAssembler<Taco, TacoEntityModel> {

    override fun toModel(entity: Taco): TacoEntityModel {
        return TacoEntityModel(entity)
    }

    override fun toCollectionModel(entities: MutableIterable<Taco>): CollectionModel<TacoEntityModel> {
        return super.toCollectionModel(entities )
            .add(linkTo(methodOn(RecentTacosController::class.java).recentTacos()).withSelfRel())
    }
}