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
    public MessageRespone findByDirection(String direction) {
        MessageRespone messageRespone = new MessageRespone(404, "Không tìm thấy bàn hợp lệ");
        if (!direction.isEmpty()) {
            TableEntity tableEntity = tablesRepository.findByDirection(direction);
            if (tableEntity != null) {
                messageRespone = new MessageRespone(200, tableEntity.getId() + "");
            }
        }
        return messageRespone;
    }

    @Override
    public MessageRespone generateDirection(Long id) {
        MessageRespone messageRespone = new MessageRespone(404, "Không thể tạo mã");
        if (id != null) {
            TableEntity tableEntity = tablesRepository.findById(id).get();
            tableEntity.setDirection(QRcode.generatePassword(25));
            tablesRepository.save(tableEntity);
            messageRespone = new MessageRespone(200, "Đã tạo thành công");
        }
        return messageRespone;
    }

    @Override
    public MessageRespone generateQRCode(Long id) {
        MessageRespone messageRespone = new MessageRespone(404, "Bàn không tồn tại");
        if (id != null) {
            TableEntity tableEntity = tablesRepository.findById(id).get();
            if (tableEntity != null) {
                String qrCode = QRcode.generateQR(tableEntity.getDirection());
                messageRespone = new MessageRespone(200, qrCode);
            }
        }
        return messageRespone;
    }
}
