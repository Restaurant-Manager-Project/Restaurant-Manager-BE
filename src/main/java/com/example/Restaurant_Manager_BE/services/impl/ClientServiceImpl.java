package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.converters.ClientConverter;
import com.example.Restaurant_Manager_BE.dto.ClientDTO;
import com.example.Restaurant_Manager_BE.entities.ClientEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.exceptions.InvalidInputException;
import com.example.Restaurant_Manager_BE.repositories.ClientRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.ClientService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final LocalizationUtils localizationUtils;
//    private final ModelMapper modelMapper;
    private final ClientConverter clientConverter;
    @Override
    public ResponseEntity<APIResponse> findByPhone(String phone) {
        ClientEntity clientEntity = clientRepository.findByPhone(phone)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_NOT_EXISTS)));
        ClientDTO clientDTO = clientConverter.toDTO(clientEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_GET_SUCCESS));
        APIResponse.setResult(clientDTO);
        return ResponseEntity.ok(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> findById(Long id) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_NOT_EXISTS)));
        ClientDTO clientDTO = clientConverter.toDTO(clientEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_GET_SUCCESS));
        APIResponse.setResult(clientDTO);
        return ResponseEntity.ok(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> createClient(ClientDTO clientDTO) {
//        ClientEntity clientEntity = modelMapper.map(clientDTO, ClientEntity.class);
//        clientEntity.setIsDeleted(false);
//        if (clientRepository.save(clientEntity) == null) {
//            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_CREATE_FALIED));
//        }
//        APIResponse APIResponse = new APIResponse();
//        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_CREATE_SUCCESS));
//        return ResponseEntity.ok(APIResponse);
        return null;
    }
    @Override
    public ResponseEntity<APIResponse> deleteClient(Long client_id) {
        ClientEntity clientEntity = clientRepository.findById(client_id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_NOT_EXISTS)));
        clientEntity.setIsDeleted(true);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_DELETE_SUCCESS));
        clientRepository.save(clientEntity);
        return ResponseEntity.ok(APIResponse);
    }
    @Override
    public ResponseEntity<APIResponse> updateClient(Long id, ClientDTO clientDTO) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_NOT_EXISTS)));

        SkipNullFields(clientDTO, clientEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_UPDATE_SUCCESS));
        clientRepository.save(clientEntity);
        return ResponseEntity.ok(APIResponse);
    }

    @Override
    public void SkipNullFields(ClientDTO clientDTO, ClientEntity clientEntity) {
        // Sử dụng clientEntity đã truyền vào để cập nhật
        for (Field field : clientDTO.getClass().getDeclaredFields()) {
            field.setAccessible(true); // Đảm bảo truy cập vào các field private
            try {
                Object value = field.get(clientDTO);
                System.out.println(field.getName() + " " + value);
                if (value != null) {
                    // Gán giá trị không null vào ClientEntity
                    Field entityField = clientEntity.getClass().getDeclaredField(field.getName());
                    entityField.setAccessible(true); // Đảm bảo truy cập vào các field private
                    entityField.set(clientEntity, value); // Cập nhật giá trị vào ClientEntity
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Updated ClientEntity: " + clientEntity);
    }
    @Override
    public  ResponseEntity<APIResponse> getALL(){
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_GET_SUCCESS));
        APIResponse.setResult(clientRepository.findAll());
        return ResponseEntity.ok(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> getRank(Long id, Long paid) {
        return null;
    }

//    @Override
//    public ResponseEntity<APIResponse> getRank(Long id) {
//        APIResponse APIResponse = new APIResponse();
//        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_GET_SUCCESS));
//        APIResponse.setResult(clientRepository.getClientRank());
//        return ResponseEntity.ok(APIResponse);
//    }
}
