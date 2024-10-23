package com.example.Restaurant_Manager_BE.services.impl;
import com.example.Restaurant_Manager_BE.entities.TableEntity;
import com.example.Restaurant_Manager_BE.exceptions.ErrorCode;
import com.example.Restaurant_Manager_BE.exceptions.BussinessException;
import com.example.Restaurant_Manager_BE.dto.TableDTO;
import com.example.Restaurant_Manager_BE.repositories.StatusTableRepository;
import com.example.Restaurant_Manager_BE.repositories.TableRepository;
import com.example.Restaurant_Manager_BE.services.TableService;
import com.example.Restaurant_Manager_BE.utils.QRcode;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;
    private final StatusTableRepository statusTableRepository;
    private final ModelMapper modelMapper;


    @Override
    public TableDTO findByDirection(String direction) {
        TableEntity tableEntity = tableRepository.findByDirection(direction);
        if (tableEntity == null) {
            throw new BussinessException(ErrorCode.INVALID_INPUT_VALUE);
        }
        TableDTO tableModel = modelMapper.map(tableEntity, TableDTO.class);
        tableModel.setStatusName(tableEntity.getStatusTable().getName());

        return tableModel;
    }

    public boolean generateDirection(Long id) {
        TableEntity tableEntity = tableRepository.findById(id).get();
        if (tableEntity == null) {
            throw new BussinessException(ErrorCode.INVALID_INPUT_VALUE);
        }
        String direction = QRcode.generatePassword(25);
        tableEntity.setDirection(direction);
        tableRepository.save(tableEntity);
        return true;
    }

    @Override
    public String generateQRCode(Long id) {
        TableEntity tableEntity = tableRepository.findById(id).get();
        if (tableEntity == null) {
            throw new BussinessException(ErrorCode.INVALID_INPUT_VALUE);
        }
        String linkQR = QRcode.generateQR(tableEntity.getDirection());
        return linkQR;
    }
}
