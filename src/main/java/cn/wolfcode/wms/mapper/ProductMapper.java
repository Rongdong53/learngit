package cn.wolfcode.wms.mapper;

import java.util.List;

import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.query.QueryObject;

public interface ProductMapper {
    void deleteByPrimaryKey(Long id);

    void insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    void updateByPrimaryKey(Product record);
    
    int queryForCount(QueryObject qo);
    List<Product> queryForList(QueryObject qo);
}