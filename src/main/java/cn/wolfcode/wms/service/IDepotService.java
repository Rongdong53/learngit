package cn.wolfcode.wms.service;

import java.util.List;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

/**
 * @author DRD
 * @date 2018年7月13日 下午7:26:00
 * @website www.wolfcode.cn
 * @description
 */
public interface IDepotService {
	void deleteByPrimaryKey(Long id);

    void insert(Depot record);

    Depot selectByPrimaryKey(Long id);

    List<Depot> selectAll();

    void updateByPrimaryKey(Depot record);
    
    PageResult query(QueryObject qo);
}
