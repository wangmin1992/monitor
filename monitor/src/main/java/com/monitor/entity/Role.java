package com.monitor.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * @ClassName: Role.java
 * @Description: 角色实体类，只考虑一个用户有多个角色，不考虑多对多的关系；
 * @author wangmin
 * @date 2018年6月4日-下午4:27:33
 * @version 1.0V
 */
@Entity
@Data
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "role_name")
	private String roleName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
	private List<Permission> permissions;
}
