package cn.wolfcode.wms.query;

import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DRD
 * @date 2018年7月9日 下午9:56:28
 * @website www.wolfcode.cn
 * @description
 */
@Getter
@Setter
public class EmployeeQueryObject extends QueryObject {
	private String keywords;
	private Long deptId;

	public String getKeywords() {
		return StringUtils.isEmpty(keywords) ? null : keywords;
	}
}
