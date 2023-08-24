package com.example.exintermediate.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.exintermediate.domain.Hotel;

@Repository
public class HotelRepository {

  @Autowired
  private NamedParameterJdbcTemplate template;

  /** Hotelオブジェクトを操作するRowMapper */
  private static final RowMapper<Hotel> HOTEL_ROW_MAPPER = (rs, i) -> {
    Hotel hotel = new Hotel();

    hotel.setId(rs.getInt("id"));
    hotel.setAreaName(rs.getString("area_name"));
    hotel.setHotelName(rs.getString("hotel_name"));
    hotel.setAddress(rs.getString("address"));
    hotel.setNearestStation(rs.getString("nearest_station"));
    hotel.setParking(rs.getString("parking"));

    return hotel;
  };

    /**
     * フォームに入力した金額以下のホテル一覧を取得する
     * 
     * @param price
     * @return 取得したホテルのリスト
     */
  public List<Hotel> load(Integer price) {
    String sql = "select * from hotels where price <=:price order by price desc;";
    SqlParameterSource param = new MapSqlParameterSource().addValue("price", price);

    List<Hotel> hotelList = template.query(sql, param, HOTEL_ROW_MAPPER);

    return hotelList;
  }
}
