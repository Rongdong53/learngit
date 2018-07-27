package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.query.ChartOrderQueryObject;
import cn.wolfcode.wms.query.ChartSaleQueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IChartsService;
import cn.wolfcode.wms.service.IClientService;
import cn.wolfcode.wms.service.ISupplierService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DRD
 * @date 2018年7月18日 下午7:12:05
 * @website www.wolfcode.cn
 * @description
 */
@Controller
@RequestMapping("/chart")
public class ChartsController {

    @Autowired
    private IChartsService chartsService;
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private IClientService clientService;
    @Autowired
    private IBrandService brandService;

    @RequestMapping("/order")
    public String order(Model model, @ModelAttribute("qo") ChartOrderQueryObject qo) {
        model.addAttribute("orders", chartsService.chartOrder(qo));
        model.addAttribute("suppliers", supplierService.selectAll());
        model.addAttribute("brands", brandService.selectAll());
        model.addAttribute("groupMaps", ChartOrderQueryObject.groupMap);
        return "chart/orderList";
    }

    @RequestMapping("/sale")
    public String sale(Model model, @ModelAttribute("qo") ChartSaleQueryObject qo) {
        model.addAttribute("sales", chartsService.chartSale(qo));
        model.addAttribute("clients", clientService.selectAll());
        model.addAttribute("brands", brandService.selectAll());
        model.addAttribute("groupMaps", ChartSaleQueryObject.groupMap);
        return "chart/saleList";
    }

    @RequestMapping("/saleChartBar")
    public String saleChartBar(Model model, @ModelAttribute("qo") ChartSaleQueryObject qo) {
        model.addAttribute("groupByTypeName", ChartSaleQueryObject.groupMap.get(qo.getGroupByType()));
        List<Object> groupByType = new ArrayList<>();
        List<Object> totalAmount = new ArrayList<>();
        //获取到表中groupByType列的值和totalAmount列的值添加到List集合
        List<Map<String, Object>> chartSale = chartsService.chartSale(qo);
        for (Map<String, Object> chart : chartSale) {
            groupByType.add(chart.get("groupByType"));
            totalAmount.add(chart.get("totalAmount"));
        }
        //将集合转换为JSON数据返回
        model.addAttribute("groupByType", JSON.toJSONString(groupByType));
        model.addAttribute("totalAmount", JSON.toJSONString(totalAmount));
        return "chart/saleChartBar";
    }

    @RequestMapping("/saleChartPie")
    public String saleChartPie(Model model, @ModelAttribute("qo") ChartSaleQueryObject qo) {
        model.addAttribute("groupByTypeName", ChartSaleQueryObject.groupMap.get(qo.getGroupByType()));
        List<Object> groupByType = new ArrayList<>();//装分组条件的名称
        List<Map<String, Object>> datas = new ArrayList<>(); //装数据

        BigDecimal maxTotalAmount = BigDecimal.ZERO;
        List<Map<String, Object>> chartSale = chartsService.chartSale(qo);
        for (Map<String, Object> chart : chartSale) {
            groupByType.add(chart.get("groupByType"));
            Map<String, Object> data = new HashMap<>();
            data.put("name", chart.get("groupByType"));
            data.put("value", chart.get("totalAmount"));
            datas.add(data);
            BigDecimal totalAmout = (BigDecimal) chart.get("totalAmount");
            if (totalAmout.compareTo(maxTotalAmount) > 0){
                maxTotalAmount = totalAmout;
            }
        }
        //将集合转换为JSON数据返回
        model.addAttribute("groupByType", JSON.toJSONString(groupByType));
        model.addAttribute("datas", JSON.toJSONString(datas));
        model.addAttribute("maxTotalAmount", JSON.toJSONString(maxTotalAmount));
        return "chart/saleChartPie";
    }
}
