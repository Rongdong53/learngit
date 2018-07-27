package cn.wolfcode.wms.query;

import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DRD
 * @date 2018年7月13日 下午10:32:11
 * @website www.wolfcode.cn
 * @description
 */
@Getter
@Setter
public class ProductQueryObject extends QueryObject{
	private String keywords;
	private Long brandId;
	
	public String getKeywords(){
		return StringUtils.isEmpty(keywords)? null : keywords;
	}
}
