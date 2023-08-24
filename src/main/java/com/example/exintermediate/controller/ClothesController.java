package com.example.exintermediate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exintermediate.domain.Clothes;
import com.example.exintermediate.form.ClothesForm;
import com.example.exintermediate.service.ClothesService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/clothes-search")
public class ClothesController {
  @Autowired
  private ClothesService clothesService;

  @Autowired
  private HttpSession session;

  @GetMapping("")

  /**
   * 検索画面を表示する
   * 
   * @param form
   * @return clothes-search.htmlに遷移する
   */
  public String clothesSearch(ClothesForm form) {
    return "clothes-search";
  }

  @PostMapping("/result") 

  /**
   * clothesServiceのclothes()メソッドを呼び出して検索結果のリストを取得し、requestスコープに格納する
   * 
   * @param form
   * @param model
   * @return
   */
  public String result(ClothesForm form, Model model) {
    List<Clothes> clothesList = clothesService.clothes(Integer.parseInt(form.getGender()), form.getColor());

    if(clothesList.size() == 0) {
      String message = "検索結果に合うものはありません。";
      model.addAttribute("error", message);
    }

    model.addAttribute("clothesList", clothesList);

    session.setAttribute("gender", form.getGender());
    session.setAttribute("color", form.getColor());

    return "clothes-search";
  }
}
