package com.ysd.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ysd.entity.Modules;
import com.ysd.entity.Roles;
import com.ysd.entity.Users;
import com.ysd.service.RoleService;
import com.ysd.service.UsersService;
import com.ysd.util.Manager;

@RestController
@RequestMapping("/api")
public class RoleController {
	
	  @Resource(name="usersServiceImpl")
	  private UsersService usersService;
	  @Resource(name="roleServiceImpl")
	  private RoleService roleService;
    
	  //��ȡȫ����ɫ����
	  
	  @GetMapping(value="/GetRolesAlll")
	  public @ResponseBody Map<String, Object>
	  GetRolesAlll() throws ParseException{
	
	  List<Roles> roles=roleService.GetRolesAlll();
	  int count=roleService.Count();
      Map<String,Object> resultMap = new HashMap<String, Object>();
	  
	  resultMap.put("code",0);
	  
	  resultMap.put("msg","");
	  
	  resultMap.put("count",count);
	  resultMap.put("data",roles); 
	  return resultMap;
	   

	  
	  }
	  
	  @GetMapping(value="/GetRolesAll")
	  public @ResponseBody Map<String, Object>
	  GetRolesAll(Integer page,Integer limit) throws ParseException{
	
	  List<Roles> roles=roleService.GetRolesAll((page-1)*limit, limit);
	  int count=roleService.Count();
      Map<String,Object> resultMap = new HashMap<String, Object>();
	  
	  resultMap.put("code",0);
	  
	  resultMap.put("msg","");
	  
	  resultMap.put("count",count);
	  resultMap.put("data",roles); 
	  return resultMap;
	   

	  
	  }
	  //�����û�id��ȡ�û���ɫ
	  @GetMapping(value="/GetRolesByUserIdLayUI")
	  public @ResponseBody List<Roles>
	  GetRolesByUserIdLayUI(String Id) throws ParseException{
	
	  List<Roles> roles=roleService.GetRolesByUserId(Id);
	  System.out.println(roles);
	   
	  return roles;
	  
	  }
	  
	  //�����û���ɫ
	  @GetMapping(value="AddUserToRole")
	  public @ResponseBody Map<String, Object>
	  AddUserToRole(String Id,String UserId,String RoleId){
		  Map<String, Object> resultMap=Manager.getResultMap();
		  int n=roleService.AddUserToRole(Id, UserId, RoleId);
		  if(n>0) {
			  resultMap.put("success", true);
		  }
		  
		   return resultMap;
	  }
	  //�Ƴ��û���ɫ
	  @GetMapping(value="RemoveUserFromRole")
	  public @ResponseBody Map<String, Object>
	  RemoveUserFromRole(String UserId,String RoleId){
		  Map<String, Object> resultMap=Manager.getResultMap();
		  int n=roleService.RemoveUserFromRole(UserId, RoleId);
		  if(n>0) {
			  resultMap.put("success", true);
		  }
		  
		   return resultMap;
	  }
	
      //�����û���ɫ��ȡ�û���Ӧ��Ȩ��ģ��
	  @GetMapping(value="/GetSysRightsJsonLayUI")
	  public Map<String,Object> getTree(@RequestParam(value="roleNames")List<String> roleNames) {
		    Map<String, Object> map=Manager.getResultMap();
	        List<Object> list = new ArrayList<Object>(); 
	        List<Modules> modules=roleService.GetSysRightsJsonLayUI(roleNames);
	        
	        for (Modules m: modules) {   
	              Map<String,Object> mapArr = new LinkedHashMap<String, Object>(); 
	              if(m.getParentId()==0){//�ж��Ƿ�Ϊ����
	                mapArr.put("id", m.getId()); 
	                mapArr.put("title", m.getName()); 
	                mapArr.put("children", Manager.menuChild(m.getId(),modules));  //ȥ�Ӽ����ұ���
	                list.add(mapArr); 
	              } 
	            } 
	        map.put("message",list);
	        map.put("success", true);
	        return map;
	    }
       
	  
	  //�����û�id��ȡ�û���ɫ

	  @GetMapping(value="/getRoleNames")
	  public @ResponseBody Map<String,Object>
	  getRoleNames(String uid){
	  Map<String, Object> map=Manager.getResultMap();
	  List<Roles> roles=roleService.GetRolesByUserId(uid);
	  List<String> roleNamess=Manager.getRoleByName(roles);
	  map.put("message", roleNamess);
	  map.put("success", true);
	  return map;
	  
	  }
}
