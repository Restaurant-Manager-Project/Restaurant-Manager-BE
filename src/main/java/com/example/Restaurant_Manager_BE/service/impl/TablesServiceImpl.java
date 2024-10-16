package com.example.Restaurant_Manager_BE.service.impl;
import com.example.Restaurant_Manager_BE.entity.StatusTableEntity;
import com.example.Restaurant_Manager_BE.entity.TableEntity;
import com.example.Restaurant_Manager_BE.exception.MessageRespone;
import com.example.Restaurant_Manager_BE.model.TablesModel;
import com.example.Restaurant_Manager_BE.repository.StatusTableRepository;
import com.example.Restaurant_Manager_BE.repository.TablesRepository;
import com.example.Restaurant_Manager_BE.service.TablesService;
import com.example.Restaurant_Manager_BE.util.QRcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TablesServiceImpl implements TablesService {
    @Autowired
    private TablesRepository tablesRepository;
    @Autowired
    private StatusTableRepository statusTableRepository;

    @Override
    public void updateTable(TablesModel tablesModel) {
        TableEntity tableEntity = tablesRepository.findById(tablesModel.getId()).get();
        tableEntity.setName(tablesModel.getName());
        tableEntity.setIs_deleted(tablesModel.getIs_deleted());
        String str = QRcode.generatePassword(25);
        tableEntity.setPassword(str);
        StatusTableEntity statusTableEntity = statusTableRepository.findById(tablesModel.getId()).get();
        tablesRepository.save(tableEntity);
    }

    @Override
    public MessageRespone findTableByPassword(String password) {
        MessageRespone messageRespone = new MessageRespone(404, "Không tìm thấy bàn hợp lệ");
        if (!password.isEmpty()) {
            TableEntity tableEntity = tablesRepository.findByPassword(password);
            if (tableEntity != null) {
                messageRespone = new MessageRespone(200, tableEntity.getId() + "");
            }
        }
        return messageRespone;
    }
}
