package com.angularBootRef.springBootPortfolio.converter;

import com.angularBootRef.springBootPortfolio.dto.IObjectDto;
import java.util.List;
import java.util.stream.Collectors;

public interface IObjectDtoConverter <T extends IObjectDto, O> {
    T convertToDto(O src);
    O convertFromDto(T src);

    default List<T> convertToDto(List<O> src) {
        return src.stream().map(obj -> this.convertToDto(obj)).collect(Collectors.toList());
    }


    default List<O> convertFromDto(List<T> src) {
        return src.stream().map(dto -> this.convertFromDto(dto)).collect(Collectors.toList());
    }
}
