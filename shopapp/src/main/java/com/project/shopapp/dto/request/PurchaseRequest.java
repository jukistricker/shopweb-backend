package com.project.shopapp.dto.request;

import com.project.shopapp.dto.PurchaseDto;
import com.project.shopapp.entity.PurchaseItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PurchaseRequest {
    private PurchaseDto purchase;
    private List<PurchaseItem> items;
}
