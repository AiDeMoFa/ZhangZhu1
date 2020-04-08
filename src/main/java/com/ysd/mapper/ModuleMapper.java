package com.ysd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ysd.entity.Modules;

public interface ModuleMapper {
    /*
     * ���ݽ�ɫId��ѯ����ģ��
     * **/
	List<Modules> GetModulesByRoleIdLayUI(@Param("roleId")String roleId);
	List<Modules> GetAllModuleLayUI();
	
	/*
	 * ����id��ȡģ����Ϣ
	 * **/
	Modules GetModuleById(@Param("Id")Integer Id);
	
	/*
	 * ���ģ����Ϣ
	 * **/
	int CreateModule(@Param("Name")String Name,@Param("ParentId")Integer ParentId,@Param("path")String path,@Param("Weight")Integer Weight);
    
	/*
	 * ����id�޸ĸ��ڵ�
	 * **/
   int UpdateModule(@Param("Id")Integer Id,@Param("ParentId")Integer ParentId);
   
   /*
    * ����idɾ��ģ����Ϣ
    * **/
   int DeleteModule(@Param("Id")Integer Id);
   
   /*
    * ����ģ��
    * **/
   int AddUserToModule(@Param("Id")String Id,@Param("RoleId")String RoleId,@Param("ModuleId")Integer ModuleId);
   
   /*
    * �Ƴ�ģ��
    * **/
   int RemoveUserFromModule(@Param("RoleId")String RoleId,@Param("ModuleId")Integer ModuleId);
   
   
}
