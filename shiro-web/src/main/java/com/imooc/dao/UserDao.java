package com.imooc.dao;

import com.imooc.vo.User;

import java.util.List;

/**
 * @author 1639489689@qq.com
 * @date 2018/7/26 0026 下午 5:14
 */
public interface UserDao {
    User getUserByUserName(String userName);

    List<String> queryRolesByUserName(String userName);

    List<String> queryPermissionByUserName(String userName);
}
