package com.ysd.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ysd.entity.Users;

public interface UsersMapper { 
	 /**
	  * ����������ѯ�����û���Ϣ
	  * */
	List<Users> getAllUsers(@Param("LoginName")String LoginName,
			@Param("IsLockout")String IsLockout,
			@Param("page")Integer page,
			@Param("limit")Integer limit
			);
	
	/*
	 * ��ѯ��������
	 * **/
	int countAll(@Param("LoginName")String LoginName,
			
			@Param("IsLockout")String IsLockout);
	
	/*
	 * ��¼��֤
	 * **/
	Users login(@Param("LoginName")String LoginName,@Param("PassWord")String PassWord);
	
	/*
	 * ɾ��
	 * **/
	int DeleteUser(@Param("Id")String Id);
	
	/*
	 * ���
	 * **/
	int CreateUser(@Param("Id")String Id,@Param("LoginName")String LoginName,@Param("PassWord")String PassWord,@Param("ProtectEMail")String ProtectEMail,@Param("ProtectMTel")String ProtectMTel);
	
	/*
	 * �޸�
	 * **/
	int UpdateUser(@Param("LoginName")String LoginName,@Param("Id")String Id,@Param("ProtectEMail")String ProtectEMail,@Param("ProtectMTel")String ProtectMTel);
	/*
	 * �����û�
	 * **/
	int LockUser(@Param("LoginName")String LoginName);
	/*
	 * �����û�
	 * **/
	int UnLockUser(@Param("LoginName")String LoginName);
	
	/*
	 * ����id�޸��û�������¼ʱ��
	 * **/
	int updateTime(@Param("Id")String Id,@Param("LastLoginTime")Date LastLoginTime);
	/*
	 * �����û�id��ѯ��Ӧ��Ȩ��
	 * **/
	
}
