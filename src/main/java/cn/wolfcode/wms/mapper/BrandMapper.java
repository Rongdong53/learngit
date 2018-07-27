package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface BrandMapper {
    void deleteByPrimaryKey(Long id);

    void insert(Brand record);

    Brand selectByPrimaryKey(Long id);

    List<Brand> selectAll();

    void updateByPrimaryKey(Brand record);
    
    int queryForCount(QueryObject qo);
    List<Brand> queryForList(QueryObject qo);
}