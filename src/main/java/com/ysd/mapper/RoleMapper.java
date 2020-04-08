package com.ysd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysd.entity.Modules;
import com.ysd.entity.Roles;

public interface RoleMapper {
  /*
   * �����û�id��ȡ��ɫ����
   * **/
	List<Roles> GetRolesByUserId(@Param("userId")String UserId);
	/*
	 * ��ѯ���н�ɫ����
	 * **/
	List<Roles> GetRolesAll(@Param("page")Integer page,@Param("limit")Integer limit);
	
	/*
	 * �����û���ɫ
	 * **/
	int AddUserToRole(@Param("Id")String Id,@Param("UserId")String UserId,@Param("RoleId")String RoleId);
    /*
     * �Ƴ��û���ɫ
     * **/
	int RemoveUserFromRole(@Param("UserId")String UserId,@Param("RoleId")String RoleId);
	
	/*
	 * ���ݽ�ɫ����ѯ��Ӧ��ģ����
	 * **/
	 List<Modules> GetSysRightsJsonLayUI(List<String> roleNames);
	 
	 /*
	  * ��ȡ��ɫ����
	  * **/
	 int Count();
	List<Roles> GetRolesAlll();
} 
