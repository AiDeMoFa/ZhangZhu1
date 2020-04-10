package com.ysd.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ysd.entity.Roles;
import com.ysd.entity.Users;
import com.ysd.redis.RedisTrans;
import com.ysd.service.RoleService;
import com.ysd.service.UsersService;
import com.ysd.util.Manager;

@RestController
@RequestMapping("/api")
public class UsersController {
	
	  @Resource(name="usersServiceImpl")
	  private UsersService usersService;
	  @Resource(name="roleServiceImpl")
	  private RoleService roleService;
	  @Resource(name="redisTrans")
	  private RedisTrans redisTrans;
      //��¼��֤ 
	  @GetMapping("/login")
	 public Map<String, Object> login(Users users,HttpServletRequest request,HttpServletResponse response) {
		  Map<String, Object> resultMap=Manager.getResultMap();
		  Users u =usersService.logins(users.getLoginName(), users.getPassWord());
		  Date date=new Date();
		  if(u!=null) {
			  int n=usersService.updateTime(u.getId(), date);			  
			  String uid=u.getId();
			  System.out.println(u);
			  redisTrans.set("uid", uid);
			  String isLockout=u.getIslockout();
			  List<Roles> roles=roleService.GetRolesByUserId(uid);
			  List<String> roleNames=Manager.getRoleByName(roles);
			  List<String> roleId=Manager.getRoleById(roles);
			 
			 
			  		  
			  if(u.getIslockout().equals("��")) {
				
				  Map<String, Object> messageMap=new HashMap<String, Object>();
				  messageMap.put("roleName", roleNames);
				  messageMap.put("error", "��ԿΪ��,���߹���");	
				  messageMap.put("uid", redisTrans.get("uid"));
				  messageMap.put("roleId", roleId);
				  messageMap.put("isLockout", isLockout);
				  resultMap.put("message", messageMap);
				  resultMap.put("success",true);
			  }else {
				  resultMap.put("message", "�˺ű�����,���ҹ���Ա�⿪");
			  }
			 
		  }else {
			  resultMap.put("message", "�˺Ż����������");
		  }
		  
		  return resultMap; 
		 
	 }
	 
	  //��ȡ�û�����
	  
	  @GetMapping(value="/GetUsersLayUI")
	  public @ResponseBody Map<String,Object>
	  getList(String LoginName,String Islockout,Integer page,Integer limit,HttpServletRequest request) throws ParseException{
		/*
		 * SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd"); Date cDate
		 * =sf.parse(CreateTime); Date lDate =sf.parse(LastLoginTime);
		 */
	  List<Users> user = usersService.findAllUsers(LoginName, Islockout, (page-1)*limit, limit);
	  int count=usersService.countAll(LoginName, Islockout);
	  Map<String,Object> resultMap = new HashMap<String, Object>();
	  resultMap.put("code",0);
	  
	  resultMap.put("msg","");
	  
	  resultMap.put("count",count);
	  resultMap.put("data",user); 
	  return resultMap;
	  
	  }
	  //�޸�����
	  @GetMapping(value="/UpdateUser")
	  public   Map<String, Object> UpdateUser(String LoginName,String ProtectEMail,String ProtectMTel,String Id){
		  Map<String, Object> resultMap=Manager.getResultMap();
		  int n=usersService.UpdateUser(LoginName, Id, ProtectEMail, ProtectMTel);
		  if(n>0) {
			  resultMap.put("success",true);
		  }
		  
		  return resultMap;
		  
	  }
	  //ɾ������
	  @GetMapping(value="/DeleteUser")
	  public   Map<String, Object> DeleteUser(String Id){
		  Map<String, Object> resultMap=Manager.getResultMap();
		  int n=usersService.DeleteUser(Id);
		  if(n>0) {
			  resultMap.put("success",true);
		  }
		  
		  return resultMap;
		  
	  }
	  
	  //�����û�id�ж��Ƿ���Ȩ��
	  @GetMapping(value="/RoleById")
	  public   Map<String, Object> RoleById(String Id){
		  Map<String, Object> resultMap=Manager.getResultMap();
		  List<Roles> roles=roleService.GetRolesByUserId(Id);
		  System.out.println(roles);
		  if(roles!=null && roles.size()>0) {
			  System.out.println("1");
			  resultMap.put("success",false);
		  }else {
			  resultMap.put("success",true);
			  System.out.println("2");
		  }
		  
		  return resultMap;
		  
	  }
	  
	  //�����û�
	  @GetMapping(value="/LockUser")
	  public   Map<String, Object> LockUser(String LoginName){
		  Map<String, Object> resultMap=Manager.getResultMap();
		  int n=usersService.LockUser(LoginName);
		  if(n>0) {
			  resultMap.put("success",true);
		  }
		  
		  return resultMap;
		  
	  }
      
	  //�����û�
	  @GetMapping(value="/UnLockUser")
	  public   Map<String, Object> UnLockUser(String LoginName){
		  Map<String, Object> resultMap=Manager.getResultMap();
		  int n=usersService.UnLockUser(LoginName);
		  if(n>0) {
			  resultMap.put("success",true);
		  }
		  
		  return resultMap;
		  
	  }
      
	  //����û�
	  @GetMapping(value="/CreateUser")
	  public   Map<String, Object> CreateUser(String LoginName,String Id,String PassWord,String ProtectEMail,String ProtectMTel){
		  Map<String, Object> resultMap=Manager.getResultMap();
		  System.out.println(LoginName);
		  int n=usersService.CreateUser(Id, LoginName, PassWord, ProtectEMail, ProtectMTel);
		  if(n>0) {
			  resultMap.put("success",true);
		  }
		  
		  return resultMap;
		  
	  }
	 
}
