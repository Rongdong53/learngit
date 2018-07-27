package cn.wolfcode.wms.service;

import java.util.List;

import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

/**
 * @author DRD
 * @date 2018年7月13日 下午7:26:00
 * @website www.wolfcode.cn
 * @description
 */
public interface IProductService {
	void deleteByPrimaryKey(Long id);

    void insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    void updateByPrimaryKey(Product record);
    
    PageResult query(QueryObject qo);
}
