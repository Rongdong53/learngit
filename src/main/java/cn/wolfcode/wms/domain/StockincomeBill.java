package cn.wolfcode.wms.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DRD
 * @date 2018年7月15日 下午12:54:54
 * @website www.wolfcode.cn
 * @description
 */
@Setter
@Getter
public class StockincomeBill extends BaseDomain{

	private static final long serialVersionUID = 1L;
	public static final int STATUS_NOMAL = 0; //未审核
	public static final int STATUS_AUDIT = 1; //审核
	private String sn; 
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date vdate; //业务发生时间
	private int status = STATUS_NOMAL; //状态值，在审核的时侯修改该值
	private BigDecimal totalAmount; //总金额
	private BigDecimal totalNumber; //总数量
	private Date auditTime; //审核时间
	private Date inputTime; //录入时间
	private Employee inputUser; //录入人
	private Employee auditor;  //审核人
	private Depot depot; //仓库
	private List<StockincomeBillItem> items = new ArrayList<>();
}
