package cn.wolfcode.wms.mapper;

import java.util.List;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.query.QueryObject;

public interface ClientMapper {
    void deleteByPrimaryKey(Long id);

    void insert(Client record);

    Client selectByPrimaryKey(Long id);

    List<Client> selectAll();

    void updateByPrimaryKey(Client record);
    
    int queryForCount(QueryObject qo);
    List<Client> queryForList(QueryObject qo);
}