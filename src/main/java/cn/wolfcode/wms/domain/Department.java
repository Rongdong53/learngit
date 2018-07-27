package cn.wolfcode.wms.domain;

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
public class Department extends BaseDomain{
	private static final long serialVersionUID = 1L;
	private String name;
	private String sn;
}
