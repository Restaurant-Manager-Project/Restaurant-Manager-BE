package com.example.Restaurant_Manager_BE.service.impl;
import com.example.Restaurant_Manager_BE.entity.TableEntity;
import com.example.Restaurant_Manager_BE.exception.MessageResponse;
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
    public MessageResponse findByDirection(String direction) {
        MessageResponse messageRespone = new MessageResponse(404, "Không tìm thấy bàn hợp lệ");
        if (!direction.isEmpty()) {
            TableEntity tableEntity = tablesRepository.findByDirection(direction);
            if (tableEntity != null) {
                messageRespone = new MessageResponse(200, tableEntity.getId() + "");
            }
        }
        return messageRespone;
    }

    @Override
    public MessageResponse generateDirection(Long id) {
        MessageResponse messageRespone = new MessageResponse(404, "Không thể tạo mã");
        if (id != null) {
            TableEntity tableEntity = tablesRepository.findById(id).get();
            tableEntity.setDirection(QRcode.generatePassword(25));
            tablesRepository.save(tableEntity);
            messageRespone = new MessageResponse(200, "Đã tạo thành công");
        }
        return messageRespone;
    }

    @Override
    public MessageResponse generateQRCode(Long id) {
        MessageResponse messageRespone = new MessageResponse(404, "Bàn không tồn tại");
        if (id != null) {
            TableEntity tableEntity = tablesRepository.findById(id).get();
            if (tableEntity != null) {
                String qrCode = QRcode.generateQR(tableEntity.getDirection());
                messageRespone = new MessageResponse(200, qrCode);
            }
        }
        return messageRespone;
    }
}
