package cn.wolfcode.wms.service.impl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.mapper.PermissionMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.util.PermissionUtil;
import cn.wolfcode.wms.util.RequiredPermission;

/**
 * @author DRD
 * @date 2018年7月9日 下午1:22:11
 * @website www.wolfcode.cn
 * @description
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private ApplicationContext context;

	@Override
	public void delete(Long id) {
		permissionMapper.delete(id);
	}

	@Override
	public Permission get(Long id) {
		return permissionMapper.get(id);
	}

	@Override
	public List<Permission> listAll() {
		return permissionMapper.listAll();
	}

	@Override
	public PageResult query(QueryObject qo) {
		int rows = permissionMapper.queryForCount(qo);
		List<Permission> data = permissionMapper.queryForList(qo);
		if(rows == 0){
			return new PageResult(qo.getPageSize());
		}
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
	}

	@Override
	public void loadPermission() {
		List<String> expressions = permissionMapper.selectExpression();
		//获取到所有控制器
		Map<String, Object> controllersMap = context.getBeansWithAnnotation(Controller.class);
		Collection<Object> Controllers = controllersMap.values();
		//遍历所有控制器
		for (Object controller : Controllers) {
			//获取到每个控制器的所有方法
			Method[] methods = controller.getClass().getMethods();
			//遍历方法
			for (Method method : methods) {
				//判断当前方法是否存在自定义注解，存在获取到权限表达式
				if(method.isAnnotationPresent(RequiredPermission.class)){
					String expression = PermissionUtil.getExpression(method);
					//集合中没有包含此表达式，则保存
					if(!expressions.contains(expression)){
						RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
						String name = annotation.value();
						
						Permission pm = new Permission();
						pm.setName(name);
						pm.setExpression(expression);
						
						permissionMapper.save(pm);
					}
				}
			}
		}
	}

}
