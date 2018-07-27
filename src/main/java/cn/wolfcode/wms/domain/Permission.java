package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DRD
 * @date 2018年7月10日 上午9:11:03
 * @website www.wolfcode.cn
 * @description
 */
@Setter
@Getter
public class Permission extends BaseDomain{
	private static final long serialVersionUID = 1L;
	private String name;
	private String expression;
}
