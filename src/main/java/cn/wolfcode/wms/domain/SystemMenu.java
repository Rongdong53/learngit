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
public class SystemMenu extends BaseDomain{
	private static final long serialVersionUID = 1L;
	private String name;
	private String url;
	private String sn;
	private SystemMenu parent;
}
