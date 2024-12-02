package com.example.Restaurant_Manager_BE.services.impl;
import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.converters.ConverterProducts;
import com.example.Restaurant_Manager_BE.converters.ConverterStatistic;
import com.example.Restaurant_Manager_BE.dto.StatisticDTO.ProductStatisticDTO;
import com.example.Restaurant_Manager_BE.entities.ClientEntity;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.exceptions.InvalidInputException;
import com.example.Restaurant_Manager_BE.repositories.ProductRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.ProductService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import com.example.Restaurant_Manager_BE.entities.CategoryEntity;
import com.example.Restaurant_Manager_BE.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.Restaurant_Manager_BE.entities.DetailsImportEntity;
import com.example.Restaurant_Manager_BE.repositories.DetailImportRepository;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ConverterProducts converterProducts;
    private final ProductRepository productRepository;
    private final DetailImportRepository importRepository;
    private final ModelMapper modelMapper;
    private final LocalizationUtils localizationUtils;
    private final CategoryRepository categoryRepository;
    private final ConverterStatistic converterStatistic;
    @Override
    public ResponseEntity<APIResponse> getAll(Integer pageNo,Integer pageSize,String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        List<ProductEntity> productEntityList =productRepository.getProduct_with_Price_from_Import();
        Page<ProductEntity> pageProduct = productRepository.getProduct_with_Price_from_Import_Page(paging);
        List<ProductDTO> productDTOList =converterProducts.toDTOList(productEntityList);
        Page<ProductDTO> dtoPage = converterProducts.toDTO_pagination(pageProduct);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_LIST_GET_SUCCESS));
        APIResponse.setResult(dtoPage);

        return ResponseEntity.ok(APIResponse);

    }

    @Override
    public ResponseEntity<APIResponse> getByName(String name) {
        List<ProductEntity> productEntityList = productRepository.findByNameContaining(name);
        List<ProductDTO> productDTOList = new ArrayList<>();
        productEntityList.forEach(productEntity -> {
            ProductDTO product = modelMapper.map(productEntity, ProductDTO.class);
            productDTOList.add(product);
        });
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_GET_SUCCESS));
        APIResponse.setResult(productDTOList);
        return ResponseEntity.ok(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> getById(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_NOT_EXISTED)));
        ProductDTO product = modelMapper.map(productEntity, ProductDTO.class);
//        product.setCategoryName(productEntity.getCategory().getName());
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_GET_SUCCESS));
        APIResponse.setResult(product);
        return ResponseEntity.ok(APIResponse);
    }
    //create new product
    @Override
    public ResponseEntity<APIResponse> createProducts(ProductDTO productDTO){
        ProductEntity productEntity = converterProducts.toEntity(productDTO);
        productRepository.save(productEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_CREATE_SUCCESS));
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse);
    }
    //delete product
    @Override
    public ResponseEntity<APIResponse> deleteProducts(Long id){
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_NOT_EXISTED)));
        System.out.print(productEntity.getName());
        productEntity.setIsDeleted(true);
        productRepository.save(productEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_DELETE_SUCCESS));
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse);
    }
    //update product
    @Override
    public ResponseEntity<APIResponse> updateProducts(Long id , ProductDTO productDTO){
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_NOT_EXISTED)));
        ProductEntity productEntityUpdate = converterProducts.toEntity(productDTO);
        converterProducts.mergeNonNullFieldsProduct(productEntity, productEntityUpdate);
        productRepository.save(productEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_UPDATE_SUCCESS));
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> StatisticProductByCategoryAndSoldQuantity(Long id) {
        List<Object[]> productEntity = productRepository.getStatisticProductByCategoryAndSoldQuantity(id);
        List<ProductStatisticDTO> result = converterStatistic.ProductStatisticDTO_List(productEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_GET_SUCCESS));
        APIResponse.setResult(result);
        return ResponseEntity.ok(APIResponse);
    }
}
