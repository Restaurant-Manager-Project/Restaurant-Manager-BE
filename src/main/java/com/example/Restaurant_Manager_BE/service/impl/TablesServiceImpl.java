package com.example.Restaurant_Manager_BE.service.impl;
import com.example.Restaurant_Manager_BE.entity.TableEntity;
import com.example.Restaurant_Manager_BE.exception.ErrorCode;
import com.example.Restaurant_Manager_BE.exception.BussinessException;
import com.example.Restaurant_Manager_BE.model.TablesModel;
import com.example.Restaurant_Manager_BE.repository.StatusTableRepository;
import com.example.Restaurant_Manager_BE.repository.TablesRepository;
import com.example.Restaurant_Manager_BE.service.TablesService;
import com.example.Restaurant_Manager_BE.util.QRcode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TablesServiceImpl implements TablesService {
    @Autowired
    private TablesRepository tablesRepository;
    @Autowired
    private StatusTableRepository statusTableRepository;

    ModelMapper modelMapper = new ModelMapper();


    @Override
    public TablesModel findByDirection(String direction) {
        TableEntity tableEntity = tablesRepository.findByDirection(direction);
        if (tableEntity == null) {
            throw new BussinessException(ErrorCode.INVALID_INPUT_VALUE);
        }
        TablesModel tableModel = modelMapper.map(tableEntity, TablesModel.class);
        tableModel.setStatusName(tableEntity.getStatusTable().getName());

        return tableModel;
    }

    public boolean generateDirection(Long id) {
        TableEntity tableEntity = tablesRepository.findById(id).get();
        if (tableEntity == null) {
            throw new BussinessException(ErrorCode.INVALID_INPUT_VALUE);
        }
        String direction = QRcode.generatePassword(25);
        tableEntity.setDirection(direction);
        tablesRepository.save(tableEntity);
        return true;
    }

    @Override
    public String generateQRCode(Long id) {
        TableEntity tableEntity = tablesRepository.findById(id).get();
        if (tableEntity == null) {
            throw new BussinessException(ErrorCode.INVALID_INPUT_VALUE);
        }
        String linkQR = QRcode.generateQR(tableEntity.getDirection());
        return linkQR;
    }
}
