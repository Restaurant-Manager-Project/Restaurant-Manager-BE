package com.example.Restaurant_Manager_BE.services.impl;
import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.converters.ConverterStatistic;
import com.example.Restaurant_Manager_BE.dto.request.ProductRequest;
import com.example.Restaurant_Manager_BE.dto.response.ProductRespose;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.mapper.request.ProductRequestMapper;
import com.example.Restaurant_Manager_BE.mapper.response.ProductResponseMapper;
import com.example.Restaurant_Manager_BE.repositories.ProductRepository;
import com.example.Restaurant_Manager_BE.services.ProductService;
import com.example.Restaurant_Manager_BE.services.CloudinaryService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import com.example.Restaurant_Manager_BE.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.Restaurant_Manager_BE.repositories.DetailImportRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final DetailImportRepository importRepository;
    private final LocalizationUtils localizationUtils;
    private final CategoryRepository categoryRepository;
    private final ConverterStatistic converterStatistic;
    private final CloudinaryService cloudinaryService;
    private final ProductResponseMapper productResponseMapper;
    private final ProductRequestMapper productRequestMapper;

    @Override
    public List<ProductRespose> getAll() {
        List<ProductEntity> productEntityList = productRepository.getProduct_with_Price_from_Import();
//        APIResponse APIResponse = new APIResponse();
//        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_LIST_GET_SUCCESS));
//        APIResponse.setResult(productDTOList);
//        return ResponseEntity.ok(APIResponse);
        return productResponseMapper.toListDto(productEntityList);

    }
//    @Override
//    public ResponseEntity<APIResponse> getAll_pagination(Integer pageNo,Integer pageSize,String sortBy) {
//        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//        List<ProductEntity> productEntityList =productRepository.getProduct_with_Price_from_Import();
//        Page<ProductEntity> pageProduct = productRepository.getProduct_with_Price_from_Import_Page(paging);
//        List<ProductDTO> productDTOList =converterProducts.toDTOList(productEntityList);
//        Page<ProductDTO> dtoPage = converterProducts.toDTO_pagination(pageProduct);
//        APIResponse APIResponse = new APIResponse();
//        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_LIST_GET_SUCCESS));
//        APIResponse.setResult(dtoPage);
//        return ResponseEntity.ok(APIResponse);
//
//    }

    @Override
    public List<ProductRespose> getByName(String name) {
        List<ProductEntity> productEntityList = productRepository.findByNameContaining(name);
//        List<ProductDTO> productDTOList = new ArrayList<>();
//        productEntityList.forEach(productEntity -> {
//            ProductDTO product = modelMapper.map(productEntity, ProductDTO.class);
//            productDTOList.add(product);
//        });
//        APIResponse APIResponse = new APIResponse();
//        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_GET_SUCCESS));
//        APIResponse.setResult(productDTOList);
//        return ResponseEntity.ok(APIResponse);
        return productResponseMapper.toListDto(productEntityList);
    }

    @Override
    public ProductRespose getById(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_NOT_EXISTED)));
        return productResponseMapper.toDto(productEntity);
    }
    //create new product
    @Override
    public boolean createProducts(ProductRequest productRequest) {
        ProductEntity productEntity = productRequestMapper.toEntity(productRequest);
        productEntity.setImg(cloudinaryService.uploadImg(productRequest.getImg()));
        return productRepository.save(productEntity) != null ? true : false;


    }

    //update product
    @Override
    public boolean updateProducts(Long id , ProductRequest productRequest){
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_NOT_EXISTED)));
        productRequestMapper.update(productEntity, productRequest);
        if (productRequest.getImg() != null) {
            productEntity.setImg(cloudinaryService.uploadImg(productRequest.getImg()));
        }
        return productRepository.save(productEntity) != null ? true : false;
    }

    //delete product
    @Override
    public boolean deleteProducts(Long id){
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_NOT_EXISTED)));
        productEntity.setDeleted(true);
        return productRepository.save(productEntity) != null ? true : false;

   }


//    @Override
//    public ResponseEntity<APIResponse> StatisticProductByCategoryAndSoldQuantity(Long id,Long topRank) {
//        List<Object[]> productEntity = productRepository.getStatisticProductByCategoryAndSoldQuantity(id,topRank);
//        List<ProductStatisticDTO> result = converterStatistic.ProductStatisticDTO_List(productEntity);
//        APIResponse APIResponse = new APIResponse();
//        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_GET_SUCCESS));
//        APIResponse.setResult(result);
//        return ResponseEntity.ok(APIResponse);
//    }

    @Override
    public List<ProductRespose> getProductsQuantityZero() {
//        List<ProductEntity> listProducts = productRepository.findByQuantityEquals(0);
//        List<ProductDTO> productDTOList =converterProducts.toDTOList(listProducts);
//        APIResponse APIResponse = new APIResponse();
//        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_LIST_GET_SUCCESS));
//        APIResponse.setResult(productDTOList);
//        return ResponseEntity.ok(APIResponse);
        return null;
    }
}
