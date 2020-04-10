package com.ysd.service;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.ysd.entity.Modules;

public interface ModuleService {
	/*
     * ��ѯ����ģ��
     * **/
	List<Modules> GetModulesByRoleIdLayUI(String roleId);
	
	List<Modules> GetAllModuleLayUI();
	/*
	 * ����id��ȡģ����Ϣ
	 * **/
	Modules GetModuleById(Integer Id);
	
	/*
	 * ���ģ����Ϣ
	 * **/
	int CreateModule(String Name,Integer ParentId,String path,Integer Weight);

	
	/*
	 * ����id�޸ĸ��ڵ�
	 * **/
   int UpdateModule(Integer Id,Integer ParentId);
   
   /*
    * ����idɾ��ģ����Ϣ
    * **/
   int DeleteModule(Integer Id);
   
   /*
    * ����ģ��
    * **/
   int AddUserToModule(String Id,String RoleId,Integer ModuleId);
   
   /*
    * �Ƴ�ģ��
    * **/
   int RemoveUserFromModule(String RoleId);
}
