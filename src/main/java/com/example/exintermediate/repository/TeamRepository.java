package com.example.exintermediate.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.exintermediate.domain.Team;

@Repository
public class TeamRepository {
  @Autowired
  private NamedParameterJdbcTemplate template;

  /** Teamオブジェクトを操作するRowMapper */
  private static final RowMapper<Team> TEAM_ROW_MAPPER = (rs, i) -> {
    Team team = new Team();

    team.setId(rs.getInt("id"));
    team.setLeagueName(rs.getString("league_name"));
    team.setTeamName(rs.getString("team_name"));
    team.setHeadquarters(rs.getString("headquarters"));
    team.setInauguration(rs.getString("inauguration"));
    team.setHistory(rs.getString("history"));

    return team;
  };

  /**
   * チームの情報一覧を取得する
   *
   * @return チーム情報を詰めたリスト
   */
  public List<Team> teams() {
    String sql = "select * from teams order by inauguration asc;";

    List<Team> teamList = template.query(sql,TEAM_ROW_MAPPER);

    return teamList;
  }

  /**
   * IDでチームを検索する
   * 
   * @param id
   * @return 検索したチーム情報
   */
  public Team load(Integer id) {
    String sql = "select * from teams where id =:id;";
    SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

    Team team = template.queryForObject(sql, param, TEAM_ROW_MAPPER);
    return team;
  }

}
