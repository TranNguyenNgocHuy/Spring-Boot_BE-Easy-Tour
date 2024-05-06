package com.easy.tour.service.Impl;

import com.easy.tour.dto.PriceDTO;
import com.easy.tour.entity.Price.Price;
import com.easy.tour.entity.Price.PriceDetail;
import com.easy.tour.mapper.PriceDetailMapper;
import com.easy.tour.mapper.PriceMapper;
import com.easy.tour.repository.PriceRepository;
import com.easy.tour.service.PriceService;
import com.easy.tour.utils.AspectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class PriceServiceImpl extends AbstractBaseServiceImpl<PriceDTO>
        implements PriceService {
    @Autowired
    PriceRepository repository;

    @Autowired
    PriceMapper priceMapper;

    @Autowired
    PriceDetailMapper priceDetailMapper;

    public PriceServiceImpl(AspectService aspect) {
        super(aspect);
    }

    @Override
    public PriceDTO createPrice(PriceDTO priceDTO) {
        try {
            // Check Price exist by Tour Code
            if (repository.existsByTourCode(priceDTO.getTourCode())) {
                log.trace("TourCode already exist: {}",priceDTO.getTourCode());
                return null;
            }

            Price price = priceMapper.convertDTOToEntity(priceDTO);

            // Create priceDetail then set property
            PriceDetail priceDetail = priceDetailMapper.convertDTOToEntity(priceDTO);

            //Connect Price with PriceDetail
            priceDetail.setPrice(price);
            price.setPriceDetail(priceDetail);

            return priceMapper.convertEntityToDTO(repository.save(price));
        } catch (Exception e) {
          log.error("Error when creating price: " + e);
            return null;
        }
    }

    @Override
    public boolean updatePriceByTourCode(PriceDTO priceDTO, String tourCode) {
        try {
            Price price = repository.findByTourCode(tourCode);

            // Get priceDetail into Price
            PriceDetail priceDetail = price.getPriceDetail();

            priceDetailMapper.mapDTOToEntity(priceDTO, priceDetail);
            repository.save(price);
            return true;
        } catch (Exception e) {
            log.error("Error when update price: " + e);
            return false;
        }
    }

    @Override
    public List<PriceDTO> findAll() {
        List<Price> priceList = repository.findAll();
        return priceList == null ? new ArrayList<>()
                : priceList.stream().map(entity -> {
                    PriceDTO priceDTO = priceMapper.convertEntityToDTO(entity);
                    priceDTO.setCreator(entity.getCreatedBy());
                    priceDTO.setCreateDate(entity.getCreatedDate());
                    priceDTO.setModifiedBy(entity.getModifiedBy());
                    priceDTO.setModifiedDate(entity.getModifiedDate());
                    return priceDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public boolean deletePriceByTourCode(String tourCode) {
        Price price = repository.findByTourCode(tourCode);
        if (price != null) {
            repository.delete(price);
            return true;
        }
        return false;
    }

    @Override
    public PriceDTO findByTourCode(String tourCode) {
        Price price = repository.findByTourCode(tourCode);

        if (price != null) {
            // Get Price Detail
            PriceDetail priceDetail = price.getPriceDetail();
            // Mapping Price
            PriceDTO priceDto = priceMapper.convertEntityToDTO(price);
            // Mapping priceDetail
            priceDetailMapper.mapEntityToDTO(priceDetail, priceDto);
            log.info("price: {}" + priceDto);
            return priceDto;
        }
        return null;
    }
}
