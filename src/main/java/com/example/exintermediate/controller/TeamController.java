package com.example.exintermediate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exintermediate.domain.Team;
import com.example.exintermediate.service.TeamService;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/teamlist")
public class TeamController {
  @Autowired
  private TeamService teamService;

  @Autowired
  private ServletContext application;

  @GetMapping("")

  /**
   * teamServiceのteamIndex()からチームリストを取得し、requestスコープに格納する
   * 
   * @param model
   * @return teamlist.htmlに遷移する
   */
  public String teamlist(Model model) {
    List<Team> teamList = teamService.teamIndex();
    application.setAttribute("teamList", teamList);
    if(teamList.size() == 0) {
      String message = "1件もデータが存在しません";
      model.addAttribute("error", message);
    }
    return "teamlist";
  }

  @GetMapping("/detail")
  /**
   * チームの詳細画面を表示する
   * 
   * @param model
   * @return detail.htmlに遷移する
   */
  public String detail(Integer id, Model model) {
    Team team = teamService.search(id);
    model.addAttribute("team", team);

    return "detail";
  }

  @GetMapping("/back")

  /**
   * 一覧画面に戻る
   * 
   * @return teamlist.htmlを表示する
   */
  public String back() {
    return "teamlist";
  }
}
