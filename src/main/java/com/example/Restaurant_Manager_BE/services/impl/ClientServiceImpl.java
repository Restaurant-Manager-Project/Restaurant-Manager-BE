package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.request.ClientRequest;
import com.example.Restaurant_Manager_BE.dto.response.ClientResponse;
import com.example.Restaurant_Manager_BE.entities.ClientEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.exceptions.InvalidInputException;
import com.example.Restaurant_Manager_BE.mapper.request.ClientRequestMapper;
import com.example.Restaurant_Manager_BE.mapper.response.ClientResponseMapper;
import com.example.Restaurant_Manager_BE.repositories.ClientRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.ClientService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final LocalizationUtils localizationUtils;
    private final ClientRequestMapper clientRequestMapper;
    private final ClientResponseMapper clientResponseMapper;

    @Override
    public ClientResponse findByPhone(String phone) {
        ClientEntity clientEntity = clientRepository.findByPhone(phone)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_NOT_EXISTS)));
        return clientResponseMapper.toDto(clientEntity);
    }

    @Override
    public ClientResponse findById(Long id) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_NOT_EXISTS)));
        return clientResponseMapper.toDto(clientEntity);
    }

    @Override
    public boolean createClient(ClientRequest clientRequest) {
        ClientEntity clientEntity = clientRequestMapper.toEntity(clientRequest);
        if (clientRepository.save(clientEntity) == null) {
            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_CREATE_FALIED));
        }
        return clientRepository.save(clientEntity) != null ? true : false;

    }
    @Override
    public boolean deleteClient(Long clientId) {
        ClientEntity clientEntity = clientRepository.findById(clientId)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_NOT_EXISTS)));
        clientEntity.setIsDeleted(true);
        return clientRepository.save(clientEntity) != null ? true : false;
    }
    @Override
    public boolean updateClient(Long id, ClientRequest clientRequest) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_NOT_EXISTS)));
        clientRequestMapper.update(clientEntity, clientRequest);
        return clientRepository.save(clientEntity) != null ? true : false;
    }

    @Override
    public List<ClientResponse> getAll(){
        List<ClientResponse> clientResponseList = clientResponseMapper.toListDto(clientRepository.findAll());
        return clientResponseList;
    }



//    @Override
//    public ResponseEntity<APIResponse> getRank(Long id) {
//        APIResponse APIResponse = new APIResponse();
//        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_GET_SUCCESS));
//        APIResponse.setResult(clientRepository.getClientRank());
//        return ResponseEntity.ok(APIResponse);
//    }
}
