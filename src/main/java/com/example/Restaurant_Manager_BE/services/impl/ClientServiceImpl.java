package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
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

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final LocalizationUtils localizationUtils;
    private final ModelMapper modelMapper;
    @Override
    public ResponseEntity<APIResponse> findByPhone(String phone) {
        ClientEntity clientEntity = clientRepository.findByPhone(phone)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_NOT_EXISTS)));
        ClientDTO clientDTO = modelMapper.map(clientEntity, ClientDTO.class);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_GET_SUCCESS));
        APIResponse.setResult(clientDTO);
        return ResponseEntity.ok(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> createClient(ClientDTO clientDTO) {
        ClientEntity clientEntity = modelMapper.map(clientDTO, ClientEntity.class);
        clientEntity.setIsDeleted(false);
        if (clientRepository.save(clientEntity) == null) {
            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_CREATE_FALIED));
        }
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_CREATE_SUCCESS));
        return ResponseEntity.ok(APIResponse);
    }
}
