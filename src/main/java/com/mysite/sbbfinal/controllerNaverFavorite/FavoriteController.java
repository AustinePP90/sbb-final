package com.mysite.sbbfinal.controllerNaverFavorite;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.sbbfinal.dtoNaverFavorite.Favorite;
import com.mysite.sbbfinal.dtoNaverFavorite.Product;
import com.mysite.sbbfinal.serviceNaverShopping.FavoriteService;
import com.mysite.sbbfinal.serviceNaverShopping.NaverShoppingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FavoriteController {

    private final NaverShoppingService naverShoppingService;
    private final FavoriteService favoriteService;
	
    // /search?query=사과
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam("query") String query) {
        return naverShoppingService.searchProducts(query);
    }
    
    @PostMapping("/favorites")
    public ResponseEntity<String> addFavorite(@RequestBody Favorite favorite) {
        favoriteService.addFavorite(favorite);
        return ResponseEntity.ok("Added to favorites");
    }
}
