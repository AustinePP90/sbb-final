package com.mysite.sbbfinal.dtoNaverFavorite;

import lombok.Data;

@Data
public class Favorite {
	Long userId;
	Long productId;
	String productName; // title
	String productUrl;
	String productImage;
}
