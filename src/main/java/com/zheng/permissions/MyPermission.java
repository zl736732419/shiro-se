package com.zheng.permissions;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.shiro.authz.Permission;

import com.google.common.base.Preconditions;

/**
 * 规则： 
 * 	+资源字符串+操作+实例 以+开头，中间用+分割 
 * 权限： 
 * 	0 表示所有权限 
 * 	1 新增 
 * 	2 修改 
 * 	4 删除 
 * 	8 查看
 * 
 * 如：+user+10表示对用户的修改操作
 *
 * @author Administrator
 * @data 2016年5月23日 下午11:17:10
 */
public class MyPermission implements Permission {

	private String resource;
	private int operate;
	private String instance;

	public MyPermission(String permissionStr) {
		Preconditions.checkNotNull(permissionStr, "权限字符串不能为空!");
		String[] arr = permissionStr.split("\\+");

		if (arr.length > 1) {
			resource = arr[1];
		}

		if (StringUtils.isBlank(resource)) {
			resource = "*";
		}

		if (arr.length > 2) {
			operate = Integer.parseInt(arr[2]);
		}

		if (arr.length > 3) {
			instance = arr[3];
		}

		if (StringUtils.isBlank(instance)) {
			instance = "*";
		}
	}

	@Override
	public boolean implies(Permission p) {
		
		if(!(p instanceof MyPermission)) {
			return false;
		}
		
		MyPermission mp = (MyPermission) p;
		
		if(!("*".equals(this.resource) || this.resource.equals(mp.getResource()))) {
			return false;
		}
		
		if(!(this.operate == 0 || (this.operate & mp.operate) != 0)) {
			return false;
		}
		
		if(!("*".equals(this.instance) || this.instance.equals(mp.getInstance()))) {
			return false;
		}
		
		return true;
	}

	public String getResource() {
		return resource;
	}

	public int getOperate() {
		return operate;
	}

	public String getInstance() {
		return instance;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public void setOperate(int operate) {
		this.operate = operate;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("resource", this.resource).append("operate", this.operate)
				.append("instance", this.instance).build();
	}

}
