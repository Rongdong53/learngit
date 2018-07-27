package cn.wolfcode.wms.mapper;

import java.util.List;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.query.QueryObject;

public interface DepotMapper {
    void deleteByPrimaryKey(Long id);

    void insert(Depot record);

    Depot selectByPrimaryKey(Long id);

    List<Depot> selectAll();

    void updateByPrimaryKey(Depot record);
    
    int queryForCount(QueryObject qo);
    List<Depot> queryForList(QueryObject qo);
}