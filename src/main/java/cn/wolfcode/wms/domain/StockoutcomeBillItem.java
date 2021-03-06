package cn.wolfcode.wms.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DRD
 * @date 2018年7月15日 下午1:07:32
 * @website www.wolfcode.cn
 * @description
 */
@Setter
@Getter
public class StockoutcomeBillItem extends BaseDomain{
	private static final long serialVersionUID = 1L;
	
	private BigDecimal salePrice; //销售价
	private BigDecimal number;
	private BigDecimal amount;//总金额
	private String remark; //备注
	private Product product;
	private Long billId;//订单id
	
}
