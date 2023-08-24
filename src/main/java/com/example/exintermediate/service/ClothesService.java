package com.example.exintermediate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exintermediate.domain.Clothes;
import com.example.exintermediate.repository.ClothesRepository;

@Service
@Transactional
public class ClothesService {
  @Autowired 
  private ClothesRepository clothesRepository;

  /**
   * clothesRepositoryのload()メソッドを呼び出して検索結果を取得する
   * 
   * @param gender
   * @param color
   * @return 検索結果のリスト
   */
  public List<Clothes> clothes(Integer gender, String color) {
    List<Clothes> clothesList = clothesRepository.load(gender, color);
    return clothesList;
  }
}
