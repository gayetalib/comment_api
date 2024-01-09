package sn.pts.comment.web.dto.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@MapperConfig
public interface EntityMapper<E, DRQ, DRP> {
    DRP toDto(E entity);

    List<DRP> toDtoList(List<E> entityList);

    default Page<DRP> toDtoPage(Page<E> entityPage) {
        Pageable pageable = entityPage.getPageable();
        List<DRP> dtoList = toDtoList(entityPage.getContent());
        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    E toEntity(DRQ dto);

    List<E> toEntityList(List<DRQ> dtoList);

    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(DRQ dto, @MappingTarget E entity);

    @InheritConfiguration
    void updateDtoFromEntity(E entity, @MappingTarget DRQ dto);
}
