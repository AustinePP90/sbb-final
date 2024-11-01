package com.mysite.sbbfinal.controllerNaverFavorite;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.sbbfinal.dtoNaverFavorite.Favorite;
import com.mysite.sbbfinal.dtoNaverFavorite.Product;
import com.mysite.sbbfinal.service.UserService;
import com.mysite.sbbfinal.serviceNaverShopping.FavoriteService;
import com.mysite.sbbfinal.serviceNaverShopping.NaverShoppingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class FavoriteController {

    private final NaverShoppingService naverShoppingService;
    private final FavoriteService favoriteService;
    private final UserService userService;
	
    // /search?query=사과
    @GetMapping("/search")
    @ResponseBody
    public List<Product> searchProducts(@RequestParam("query") String query) {
        return naverShoppingService.searchProducts(query);
    }
    
    @PostMapping("/favorites")
    @ResponseBody
    public ResponseEntity<String> addFavorite(@RequestBody Favorite favorite) {
        favoriteService.addFavorite(favorite);
        return ResponseEntity.ok("Added to favorites");
    }
    
    @GetMapping("/favorites")
    public String getFavorites(Model model) {
        // 인증된 사용자 ID 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
    	
        // userId를 서비스에서 가져오는 방식으로 변경 (username으로 검색)
        int userId = userService.getUserIdByUsername(username);
        
        // 해당 사용자 ID의 즐겨찾기 목록 조회
        List<Favorite> favorites = favoriteService.getFavoritesByUserId(userId);
        
        // 모델에 즐겨찾기 목록 추가
        model.addAttribute("favorites", favorites);
        
        // 즐겨찾기 목록을 표시할 HTML 파일 이름 반환 (예: favorites.html)
        return "favorites";
    }
}
