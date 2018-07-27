package cn.wolfcode.wms.query;

import cn.wolfcode.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author DRD
 * @date 2018年7月16日 下午7:03:52
 * @website www.wolfcode.cn
 * @description
 */
@Setter
@Getter
public class ChartOrderQueryObject extends QueryObject {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private long supplierId = -1L; // 根据供应商分组
    private long brandId = -1L; // 根据品牌分组
    private String groupByType = "inputUser.name";

    // 在加载这个类时初始化分组条件并装进Map中
    public static Map<String, Object> groupMap;

    static {
        groupMap = new LinkedHashMap<>();
        groupMap.put("inputUser.name", "订货人员");
        groupMap.put("p.name", "货品名称");
        groupMap.put("supplier.name", "供应商");
        groupMap.put("p.brand_name", "品牌");
        groupMap.put("DATE_FORMAT(bill.vdate,'%Y-%m')", "订货日期(月)");
        groupMap.put("DATE_FORMAT(bill.vdate,'%Y-%m-%d')", " 订货日期(日)");
    }

    public Date getEndDate() {
        if (endDate != null) {
            return DateUtil.getEndDate(endDate);
        }
        return null;
    }
}
