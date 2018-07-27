package cn.wolfcode.wms.query;

import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DRD
 * @date 2018年7月18日 上午12:12:47
 * @website www.wolfcode.cn
 * @description
 */
@Setter
@Getter
public class ProductStockQueryObject extends QueryObject {
	private String keywords;
	private long depotId = -1;
	private long brandId = -1;
	private Long limitNumber;

	public String getKeywords() {
		return StringUtils.hasLength(keywords) ? keywords : null;
	}
}
