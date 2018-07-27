package cn.wolfcode.wms.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.wolfcode.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @author DRD
 * @date 2018年7月16日 下午7:03:52
 * @website www.wolfcode.cn
 * @description
 */
@Setter
@Getter
public class StockincomeBillQueryObject extends QueryObject{
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date beginDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	private long depotId = -1L;
	private long status = -1L;
	
	public Date getEndDate(){
		if(endDate != null){
			return DateUtil.getEndDate(endDate);
		}
		return null;
	}
}
