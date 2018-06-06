package com.monitor.repository;

import com.monitor.entity.User;

/**
 * @ClassName: UserRepository.java
 * @Description: "Invalid derived query! No property name found for type User!"出现这个错误是因为实体类中没有
 * 某个属性(No property 属性名 found for type 类名)；
 * @author wangmin
 * @date 2018年6月5日-上午9:06:54
 * @version 1.0V
 */
public interface UserRepository extends BaseRepository<User, Long>{

	User findByUserName (String name);
}
