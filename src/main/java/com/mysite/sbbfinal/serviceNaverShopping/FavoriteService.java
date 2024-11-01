package com.mysite.sbbfinal.serviceNaverShopping;

import org.springframework.stereotype.Service;

import com.mysite.sbbfinal.dtoNaverFavorite.Favorite;
import com.mysite.sbbfinal.mapperNaverShopping.FavoriteMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteMapper favoriteMapper;
    
    public void addFavorite(Favorite favorite) {
        favoriteMapper.insertFavorite(favorite);
    }

}
