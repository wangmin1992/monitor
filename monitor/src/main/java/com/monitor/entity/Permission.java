package com.monitor.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * @ClassName: Permission.java
 * @Description: 用户权限；
 * @author wangmin
 * @date 2018年6月4日-下午4:42:03
 * @version 1.0V
 */

@Entity
@Data
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String permission;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;
}
