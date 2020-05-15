package com.angularBootRef.springBootPortfolio.converter;

import com.angularBootRef.springBootPortfolio.domain.Owner;
import com.angularBootRef.springBootPortfolio.dto.OwnerDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class OwnerDtoConverter implements IObjectDtoConverter<OwnerDto, Owner> {

    @Override
    public OwnerDto convertToDto(Owner src) {
        final OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(src.getOwnerId());
        ownerDto.setFirstName(src.getFirstName());
        ownerDto.setLastName(src.getLastName());
        ownerDto.setUsername(src.getUsername());

        //TODO review the mapper for this here.
//        if (!CollectionUtils.isEmpty(src.getAuditEntries())) {
//            ownerDto.setAuditEntries(this.auditEntriesDtoConverter.convertToDto(src.getAuditEntries()));
//        }
        return ownerDto;
    }

    @Override
    public Owner convertFromDto(OwnerDto src) {
        final Owner owner = new Owner();
        owner.setOwnerId(src.getId());
        owner.setFirstName(src.getFirstName());
        owner.setLastName(src.getLastName());
        owner.setUsername(src.getUsername());

        return owner;
    }
}
