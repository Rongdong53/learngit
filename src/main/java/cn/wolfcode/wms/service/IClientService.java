package cn.wolfcode.wms.service;

import java.util.List;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

/**
 * @author DRD
 * @date 2018年7月13日 下午7:26:00
 * @website www.wolfcode.cn
 * @description
 */
public interface IClientService {
	void deleteByPrimaryKey(Long id);

    void insert(Client record);

    Client selectByPrimaryKey(Long id);

    List<Client> selectAll();

    void updateByPrimaryKey(Client record);
    
    PageResult query(QueryObject qo);
}
