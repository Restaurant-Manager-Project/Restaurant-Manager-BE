package com.example.Restaurant_Manager_BE.dto.PaginationDTO;

import com.example.Restaurant_Manager_BE.dto.CategoriesDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesDTOPagination {
    List<CategoriesDTO> content;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean last;
}
