package com.ysd.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ysd.entity.Modules;
import com.ysd.entity.Roles;

public interface RoleService {
	 /*
	   * �����û�id��ȡ��ɫ����
	   * **/
		List<Roles> GetRolesByUserId(String UserId);
		
		 /*
		  * ��ȡ��ɫ����
		  * **/
		 int Count();
		/*
		 * ��ѯ���н�ɫ����
		 * **/
		List<Roles> GetRolesAll(Integer page,Integer limit);
		
		/*
		 * �����û���ɫ
		 * **/
		int AddUserToRole(String Id,String UserId,String RoleId);
	    /*
	     * �Ƴ��û���ɫ
	     * **/
		int RemoveUserFromRole(String UserId,String RoleId);

		/*
		 * ���ݽ�ɫ����ѯ��Ӧ��ģ����
		 * **/
		 List<Modules> GetSysRightsJsonLayUI(List<String> roleName);

		List<Roles> GetRolesAlll();
}
