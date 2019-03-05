package cn.com.payment.v2.web.mapper;

import java.util.List;

import cn.com.payment.v2.web.model.Admin;





/**
 * 代码生成器自动生成
 * Date:2015-6-10 16:41:38
 * @author rono
 */
public interface AdminMapper extends BaseMapper<Admin,Long>{
	/**
	 * 获取用户和角色列表
	 * @param admin
	 * @return
	 * @throws Exception
	 */
	List<Admin> selectAdminAndRole(Admin admin)throws Exception;
	
	void updateForDepartmentId(Admin admin)throws Exception;
}