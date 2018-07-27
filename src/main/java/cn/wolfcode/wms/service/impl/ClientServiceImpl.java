package cn.wolfcode.wms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.mapper.ClientMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IClientService;

/**
 * @author DRD
 * @date 2018年7月13日 下午7:27:22
 * @website www.wolfcode.cn
 * @description
 */
@Service
public class ClientServiceImpl implements IClientService {
	@Autowired
	private ClientMapper clientMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		clientMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Client record) {
		clientMapper.insert(record);
	}

	@Override
	public Client selectByPrimaryKey(Long id) {
		return clientMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Client> selectAll() {
		return clientMapper.selectAll();
	}

	@Override
	public void updateByPrimaryKey(Client record) {
		clientMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult query(QueryObject qo) {
		int rows = clientMapper.queryForCount(qo);
		if(rows == 0){
			return new PageResult(qo.getPageSize());
		}
		List<Client> data = clientMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
	}

}
