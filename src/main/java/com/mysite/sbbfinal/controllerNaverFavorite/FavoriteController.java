package com.mysite.sbbfinal.controllerNaverFavorite;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.sbbfinal.dtoNaverFavorite.Product;
import com.mysite.sbbfinal.serviceNaverShopping.NaverShoppingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FavoriteController {

    private final NaverShoppingService naverShoppingService;
	
    // /search?query=사과
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam("query") String query) {
        return naverShoppingService.searchProducts(query);
    }
    
}
