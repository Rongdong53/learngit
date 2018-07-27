package cn.wolfcode.wms.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DRD
 * @date 2018年7月9日 下午8:25:05
 * @website www.wolfcode.cn
 * @description
 */
@Getter
@Setter
public class Employee extends BaseDomain{
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private String email;
	private Integer age;
	private boolean admin;
	private Department dept;
	private List<Role> roles = new ArrayList<>();
}
