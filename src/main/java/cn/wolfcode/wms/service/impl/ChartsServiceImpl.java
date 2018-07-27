package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.mapper.ChartsMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author DRD
 * @date 2018年7月18日 下午7:10:32
 * @website www.wolfcode.cn
 * @description
 */
@Service
public class ChartsServiceImpl implements IChartsService {

	@Autowired
	private ChartsMapper chartsMapper;
	
	@Override
	public List<Map<String, Object>> chartOrder(QueryObject qo) {
		return chartsMapper.chartOrder(qo);
	}
	@Override
	public List<Map<String, Object>> chartSale(QueryObject qo) {
		return chartsMapper.chartSale(qo);
	}

}
