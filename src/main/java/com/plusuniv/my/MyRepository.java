package com.plusuniv.my;


import com.plusuniv.dto.MyTable;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MyRepository {

    @Insert("INSERT INTO tb_1(c1, c2) values(#{c1}, #{c2})")
    int save(MyTable myTable);

    @Select("Select * from tb_1")
    List<MyTable> read();
}