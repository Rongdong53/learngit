package cn.wolfcode.wms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.mapper.RoleMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IRoleService;

/**
 * @author DRD
 * @date 2018年7月9日 下午1:22:11
 * @website www.wolfcode.cn
 * @description
 */
@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public void save(Role role, Long[] permissionIds, Long[] menuIds) {
		roleMapper.save(role);
		if (permissionIds != null) {
			for (Long permissionId : permissionIds) {
				roleMapper.insertPermissionRelation(role.getId(), permissionId);
			}
		}
		if (menuIds != null) {
			for (Long menuId : menuIds) {
				roleMapper.insertSystemMenuRelation(role.getId(), menuId);
			}
		}
	}

	@Override
	public void delete(Long id) {
		roleMapper.deletePermissionRelation(id);
		roleMapper.deleteSystemMenuRelation(id);
		roleMapper.delete(id);
	}

	@Override
	public void update(Role role, Long[] permissionIds, Long[] menuIds) {
		roleMapper.update(role);
		roleMapper.deletePermissionRelation(role.getId());
		if (permissionIds != null) {
			for (Long permissionId : permissionIds) {
				roleMapper.insertPermissionRelation(role.getId(), permissionId);
			}
		}
		roleMapper.deleteSystemMenuRelation(role.getId());
		if (menuIds != null) {
			for (Long menuId : menuIds) {
				roleMapper.insertSystemMenuRelation(role.getId(), menuId);
			}
		}
	}

	@Override
	public Role get(Long id) {
		return roleMapper.get(id);
	}

	@Override
	public List<Role> listAll() {
		return roleMapper.listAll();
	}

	@Override
	public PageResult query(QueryObject qo) {
		int rows = roleMapper.queryForCount(qo);
		List<Role> data = roleMapper.queryForList(qo);
		if (rows == 0) {
			return new PageResult(qo.getPageSize());
		}
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
	}

}
