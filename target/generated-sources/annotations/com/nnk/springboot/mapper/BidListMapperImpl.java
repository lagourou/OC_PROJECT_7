package com.nnk.springboot.mapper;

import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.model.BidList;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T20:38:22+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250526-2018, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class BidListMapperImpl implements BidListMapper {

    @Override
    public BidListDTO tobidListDTO(BidList bidList) {
        if ( bidList == null ) {
            return null;
        }

        BidListDTO bidListDTO = new BidListDTO();

        bidListDTO.setAccount( bidList.getAccount() );
        bidListDTO.setBidListId( bidList.getBidListId() );
        bidListDTO.setBidQuantity( bidList.getBidQuantity() );
        bidListDTO.setType( bidList.getType() );

        return bidListDTO;
    }

    @Override
    public BidList tobidList(BidListDTO bidListDTO) {
        if ( bidListDTO == null ) {
            return null;
        }

        BidList bidList = new BidList();

        bidList.setAccount( bidListDTO.getAccount() );
        if ( bidListDTO.getBidListId() != null ) {
            bidList.setBidListId( bidListDTO.getBidListId() );
        }
        bidList.setBidQuantity( bidListDTO.getBidQuantity() );
        bidList.setType( bidListDTO.getType() );

        return bidList;
    }

    @Override
    public void updateBidListFromDTO(BidListDTO dto, BidList entity) {
        if ( dto == null ) {
            return;
        }

        entity.setAccount( dto.getAccount() );
        if ( dto.getBidListId() != null ) {
            entity.setBidListId( dto.getBidListId() );
        }
        entity.setBidQuantity( dto.getBidQuantity() );
        entity.setType( dto.getType() );
    }
}
