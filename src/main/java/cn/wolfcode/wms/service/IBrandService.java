package cn.wolfcode.wms.service;

import java.util.List;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

/**
 * @author DRD
 * @date 2018年7月13日 下午7:26:00
 * @website www.wolfcode.cn
 * @description
 */
public interface IBrandService {
	void deleteByPrimaryKey(Long id);

    void insert(Brand record);

    Brand selectByPrimaryKey(Long id);

    List<Brand> selectAll();

    void updateByPrimaryKey(Brand record);
    
    PageResult query(QueryObject qo);
}
