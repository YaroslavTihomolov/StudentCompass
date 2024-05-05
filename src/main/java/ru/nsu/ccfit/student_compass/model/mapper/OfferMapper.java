package ru.nsu.ccfit.student_compass.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.nsu.ccfit.student_compass.model.dto.OfferDto;
import ru.nsu.ccfit.student_compass.model.entity.Offer;

@Mapper(uses = {UserMapper.class})
public interface OfferMapper {

    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);

    OfferDto toDto(Offer offer);

}
