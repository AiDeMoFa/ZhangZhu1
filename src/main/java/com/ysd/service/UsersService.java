package com.ysd.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ysd.entity.Users;

public interface UsersService {
	 /**
	  * ����������ѯ�����û���Ϣ
	  * */
	List<Users> findAllUsers(String LoginName,
			
			String IsLockout,
			Integer page,
			Integer limit
			);
	
	/*
	 * ��ѯ��������
	 * **/
	int countAll(String LoginName,
			
			String IsLockout);
	
	/*
	 * ��¼��֤
	 * **/
	Users logins(String LoginName,String PassWord);
	
	/*
	 * ɾ��
	 * **/
	int DeleteUser(String Id);
	
	/*
	 * ���
	 * **/
	int CreateUser(String Id,String LoginName,String PassWord,String ProtectEMail,String ProtectMTel);
	
	/*
	 * �޸�
	 * **/
	int UpdateUser(String LoginName,String Id,String ProtectEMail,String ProtectMTel);
	/*
	 * �����û�
	 * **/
	int LockUser(String LoginName);
	/*
	 * �����û�
	 * **/
	int UnLockUser(String LoginName);
	
	/*
	 * ����id�޸��û�������¼ʱ��
	 * **/
	int updateTime(String Id,Date LastLoginTime);
}
