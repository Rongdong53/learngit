package cn.wolfcode.wms.mapper;

import java.util.List;

import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.query.QueryObject;

public interface SupplierMapper {
	void deleteByPrimaryKey(Long id);

    void insert(Supplier record);

    Supplier selectByPrimaryKey(Long id);

    List<Supplier> selectAll();

    void updateByPrimaryKey(Supplier record);
    
    int queryForCount(QueryObject qo);
    List<Supplier> queryForList(QueryObject qo);
}