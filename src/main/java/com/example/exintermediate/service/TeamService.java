package com.example.exintermediate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exintermediate.domain.Team;
import com.example.exintermediate.repository.TeamRepository;

@Service
@Transactional
public class TeamService {
  @Autowired
  private TeamRepository teamRepository;

  /**
   * チーム情報を取得する(teamRepositoryのteams()を呼ぶ)
   * 
   * @return 取得したチームリスト
   */
  public List<Team> teamIndex() {
    List<Team> teamList = teamRepository.teams();
    return teamList;
  }

  public Team search(Integer id) {
    Team team = teamRepository.load(id);
    return team;
  }
}
