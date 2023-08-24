package com.example.exintermediate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exintermediate.domain.Hotel;
import com.example.exintermediate.repository.HotelRepository;

@Service
@Transactional
public class HotelService {
  @Autowired
  private HotelRepository hotelRepository;

  /**
   * hotelRepositoryのload()メソッドから検索条件に合ったホテルのリストを取得する
   * 
   * @param price
   * @return 取得したホテルのリスト
   */
  public List<Hotel> hotels(Integer price) {
    List<Hotel> hotelList = hotelRepository.load(price);
    return hotelList;
  }
}
