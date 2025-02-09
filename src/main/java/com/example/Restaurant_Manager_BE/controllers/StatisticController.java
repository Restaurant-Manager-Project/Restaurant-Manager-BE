package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.services.InvoiceService;
import com.example.Restaurant_Manager_BE.services.ProductService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StatisticController {
    private final ProductService productService;
    private final InvoiceService invoiceService;
    private final LocalizationUtils localizationUtils;
//    @PreAuthorize("hasAuthority('statistic.view')")
//    @Operation(summary = "Thống kê sản phẩm (theo loại) theo số lượng bán được và xếp hạng chúng")
//    @GetMapping("api/statistic/products/{category_id}")
//    public ResponseEntity<APIResponse> getStatisticProductsByCategory(@PathVariable(value = "category_id",required = false)
//                                                                          Long category_id,
//                                                                      @RequestParam(value = "topRank",required = false)
//                                                                      Long topRank
//    ) {
//        return productService.StatisticProductByCategoryAndSoldQuantity(category_id,topRank);
//    }
//    @PreAuthorize("hasAuthority('statistic.view')")
//    @Operation(summary = "Thống kê sản phẩm theo số lượng bán được và xếp hạng chúng")
//    @GetMapping("api/statistic/products/all")
//    public ResponseEntity<APIResponse> getStatisticProducts(@RequestParam(value = "topRank",required = false)
//                                                            Long topRank) {
//        return productService.StatisticProductByCategoryAndSoldQuantity(null,topRank);
//    }
//    @PreAuthorize("hasAuthority('statistic.view')")
//    @Operation(summary = "Thóng kê doanh thu theo năm ( có 12 tháng )  ")
//    @GetMapping("api/statistic/revenue/{year}")
//    public ResponseEntity<APIResponse> getStatisticRevenueByYear(@PathVariable("year") int year)
//    {
//        return invoiceService.getStatisticRevenue(year);
//    }
//    @PreAuthorize("hasRole('statistic.view')")
//    @Operation(summary ="Thống kê doanh thu và hóa đơn theo ngày hoặc theo tháng hoăc theo năm")
//    @GetMapping("api/statistic/revenue/")
}
