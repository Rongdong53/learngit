package cn.wolfcode.wms.service;

import java.util.List;

import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

/**
 * @author DRD
 * @date 2018年7月13日 下午7:26:00
 * @website www.wolfcode.cn
 * @description
 */
public interface ISupplierService {
	void deleteByPrimaryKey(Long id);

    void insert(Supplier record);

    Supplier selectByPrimaryKey(Long id);

    List<Supplier> selectAll();

    void updateByPrimaryKey(Supplier record);
    
    PageResult query(QueryObject qo);
}
