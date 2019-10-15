package com.wx.spring.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wx.spring.model.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2019-10-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
