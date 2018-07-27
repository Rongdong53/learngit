package cn.wolfcode.wms.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DRD
 * @date 2018年7月9日 下午12:44:12
 * @website www.wolfcode.cn
 * @description
 */
@Getter
@Setter
public class Role extends BaseDomain{
	private static final long serialVersionUID = 1L;
	private String name;
	private String sn;
	private List<Permission> permissions = new ArrayList<>();
	private List<SystemMenu> systemMenus = new ArrayList<>();
}
