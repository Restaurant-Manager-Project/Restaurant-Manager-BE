package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.request.ImportRequest;
import com.example.Restaurant_Manager_BE.dto.response.DetailsImportResponse;
import com.example.Restaurant_Manager_BE.dto.response.ImportResponse;
import com.example.Restaurant_Manager_BE.entities.ImportEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.mapper.request.ImportRequestMapper;
import com.example.Restaurant_Manager_BE.mapper.response.DetailsImportResponseMapper;
import com.example.Restaurant_Manager_BE.mapper.response.ImportResponseMapper;
import com.example.Restaurant_Manager_BE.repositories.DetailImportRepository;
import com.example.Restaurant_Manager_BE.repositories.ImportRepository;
import com.example.Restaurant_Manager_BE.repositories.ProductRepository;
import com.example.Restaurant_Manager_BE.services.ImportService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ImportServiceImpl implements ImportService {
    private final ImportRepository importRepository;
    private final LocalizationUtils localizationUtils;
    private final ProductRepository productRepository;
    private final DetailImportRepository detailImportRepository;
    private final ImportRequestMapper importRequestMapper;
    private final ImportResponseMapper importResponseMapper;
    private final DetailsImportResponseMapper detailsImportResponseMapper;

    @Override
    public boolean createImport(ImportRequest importRequest) {
        ImportEntity importEntity = importRequestMapper.toEntity(importRequest);
        importEntity.setIsDeleted(false);
//        if (importEntity == null) {
//            throw new InvalidInputException(MessageKeys.INVALID_INPUT);
//        }
//        APIResponse APIResponse = new APIResponse();
//        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.IMPORT_CREATE_SUCCESS));
//        updateProductQuantityAndSellPrice(importEntity);
        return importRepository.save(importEntity) != null ? true : false;
    }

//    public void updateProductQuantityAndSellPrice(ImportEntity importEntity) {
//        if(importEntity == null ) {return;}
//        List<DetailsImportEntity> listDetailImport = importEntity.getDetailsProductList();
//        listDetailImport.forEach(detailsImportEntity -> {
//            if(!checkValidDetailImport(detailsImportEntity)) {return;}
//            ProductEntity product = productRepository.findById(detailsImportEntity.getProduct().getId())
//                    .orElseThrow(()->new DataNotFoundException(MessageKeys.PRODUCT_NOT_EXISTED));
//            if(product.getQuantity()== null || product.getQuantity() <= 0) {
//                product.setQuantity(detailsImportEntity.getQuantity());
//                productRepository.save(product);
//            }
//        });
//    }
//
//    public boolean checkValidDetailImport(DetailsImportEntity detailsImportEntity) {
//        if(detailsImportEntity == null) {return false;}
//        else if(detailsImportEntity.getProduct() == null||detailsImportEntity.getQuantity()<=0) {return false;}
//        return true;
//    }

    @Override
    public ImportResponse getImportById(Long id) {
        ImportEntity importEntity = importRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(MessageKeys.IMPORT_NOT_EXISTED));
        return importResponseMapper.toDto(importEntity);
    }

    @Override
    public List<ImportResponse> getAllImport() {
        List<ImportEntity> listImport = importRepository.getAllWithEmployeeAndSupplier();
        List<DetailsImportResponse> test = new ArrayList<>();
        for(ImportEntity importEntity : listImport) {
            test = (detailsImportResponseMapper.toListDto(importEntity.getDetailsProductList()));
        }
        List<ImportResponse> t = importResponseMapper.toListDto(listImport);
        return importResponseMapper.toListDto(listImport);
    }

    @Override
    public boolean updateImport(Long id, ImportRequest importRequest) {
        ImportEntity importEntity = importRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(MessageKeys.IMPORT_NOT_EXISTED));
        importRequestMapper.update(importEntity, importRequest);
        return importRepository.save(importEntity) != null ? true : false;
    }
}
