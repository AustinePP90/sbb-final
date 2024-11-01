package com.mysite.sbbfinal.mapperNaverShopping;

import org.apache.ibatis.annotations.Mapper;

import com.mysite.sbbfinal.dtoNaverFavorite.Favorite;

@Mapper
public interface FavoriteMapper {
    void insertFavorite(Favorite favorite);
}
