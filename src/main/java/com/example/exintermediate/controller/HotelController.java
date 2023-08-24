package com.example.exintermediate.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exintermediate.domain.Hotel;
import com.example.exintermediate.service.HotelService;

@Controller
@RequestMapping("/hotel-search")
public class HotelController {
  @Autowired
  private HotelService hotelService;

  @GetMapping("")

  /**
   * 検索画面を表示する
   * 
   * @return hotel-search.htmlに遷移する
   */
  public String hotelSearch() {
    return "hotel-search";
  }

  @PostMapping("/result")
  public String result(Integer price, Model model) {
    List<Hotel> hotelList = new LinkedList<>();
    if(price == null) {
      hotelList = hotelService.hotelIndex();
    } else {
      hotelList = hotelService.hotels(price);
    }

    if(price != null && price > 300000) {
      hotelList = null;
    }

    model.addAttribute("hotelList", hotelList);
    model.addAttribute("price", price);

    return "hotel-search";
  }
}
