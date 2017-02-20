package com.taobao.yanye.springboot.dal;

import com.taobao.yanye.springboot.dal.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yanye on 17-2-18.
 */
@Transactional
@Mapper
public interface UserRepository  {
    @Select("select * from user limit #{pageSize} offset #{offset}")
    List<User> query(@Param("offset") int offset,
                     @Param("pageSize") int pageSize);

    @Select("select * from user where id = #{id}")
    User findOne(@Param("id") Long id);

    @Insert("insert into user (id, name, password) values (null, #{user.name}, #{user.password})")
    //@SelectKey(statement = "select max(id) as id from USER USER",
    //        keyProperty = "user.id",
    //        keyColumn = "id",
    //        before = false,
    //        statementType = StatementType.STATEMENT,
    //        resultType = Long.class)
    @Options(useGeneratedKeys = true, keyProperty = "user.id", keyColumn = "id")
    Long insert(@Param("user") User user);
}
