package com.monitor.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * @ClassName: User.java
 * @Description: 用户实体类；
 * @author wangmin
 * @date 2018年6月4日-下午4:25:20
 * @version 1.0V
 */
@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long userId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_password")
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Role> roles;
}
