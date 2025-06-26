package com.auction.auctionapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotItemDTO {
    private String img;
    private String title;
    private String category;
    private String desc;
    private String price;
    private String date;
}
