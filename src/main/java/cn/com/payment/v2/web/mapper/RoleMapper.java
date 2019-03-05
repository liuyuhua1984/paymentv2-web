package cn.com.payment.v2.web.mapper;

import java.util.List;

import cn.com.payment.v2.web.exceptions.DBAccessException;
import cn.com.payment.v2.web.model.Role;
/**
 * 代码生成器自动生成
 * Date:2015-6-10 16:41:38
 * @author rono
 */
public interface RoleMapper extends BaseMapper<Role,Long>{
	public List<Role> selectAdminRolesByFK(Long id)throws DBAccessException;
}