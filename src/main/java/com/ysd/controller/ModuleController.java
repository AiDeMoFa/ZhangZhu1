package com.ysd.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ysd.entity.Modules;
import com.ysd.entity.Roles;
import com.ysd.service.ModuleService;
import com.ysd.service.RoleService;
import com.ysd.util.Manager;

@RestController
@RequestMapping("/api")
public class ModuleController {
	  @Resource(name="moduleServiceImpl")
	  private ModuleService moduleService;
	  
	  //���ݽ�ɫid��ȡģ����Ϣ
	  @GetMapping(value="/GetModulesByRoleIdLayUI")
	  public @ResponseBody List<Object>
	  GetModulesByRoleIdLayUI(String roleId) {
		  List<Object> list = new ArrayList<Object>(); 
	  List<Modules> modules=moduleService.GetModulesByRoleIdLayUI(roleId);
	  for (Modules m: modules) {   
          Map<String,Object> mapArr = new LinkedHashMap<String, Object>(); 
          if(m.getParentId()==0){//�ж��Ƿ�Ϊ����
            mapArr.put("id", m.getId()); 
            mapArr.put("title", m.getName()); 
            mapArr.put("children", Manager.menuChild(m.getId(),modules));  //ȥ�Ӽ����ұ���
            list.add(mapArr); 
          } 
        } 
	   System.out.println(list);
	  return list;
	  
	  }
	//��ȡ����ģ����Ϣ
	  @GetMapping(value="/GetAllModuleLayUI")
	  public @ResponseBody List<Object>
	  GetRolesByUserIdLayUI(String Id) throws ParseException{
		  List<Object> list = new ArrayList<Object>(); 
	  List<Modules> modules=moduleService.GetAllModuleLayUI();
	  for (Modules m: modules) {   
          Map<String,Object> mapArr = new LinkedHashMap<String, Object>(); 
          if(m.getParentId()==0){//�ж��Ƿ�Ϊ����
            mapArr.put("id", m.getId()); 
            mapArr.put("title", m.getName()); 
            mapArr.put("children", Manager.menuChild(m.getId(),modules));  //ȥ�Ӽ����ұ���
            list.add(mapArr); 
          } 
        } 
	   
	  return list;
	  
	  }
	  
	  //�ݹ�tree����
	  @GetMapping(value="/GetAllModuleLayUITree")
	  public  List<Object> getTree() {
	        List<Object> list = new ArrayList<Object>(); 
	        List<Modules> modules=moduleService.GetAllModuleLayUI();
	        System.out.println(modules);
	        for (Modules m: modules) {   
	              Map<String,Object> mapArr = new LinkedHashMap<String, Object>(); 
	              if(m.getParentId()==0){//�ж��Ƿ�Ϊ����
	                mapArr.put("id", m.getId()); 
	                mapArr.put("title", m.getName()); 
	                mapArr.put("children", Manager.menuChild(m.getId(),modules));  //ȥ�Ӽ����ұ���
	                list.add(mapArr); 
	              } 
	            } 
	        return list;
	    }
      //����id��ȡģ����Ϣ
	  @GetMapping(value="/GetModuleById")
	  public @ResponseBody Map<String, Object>
	  GetModuleById(Integer Id) {
	  Map<String, Object> resultMap=Manager.getResultMap();
	  Modules modules=moduleService.GetModuleById(Id);
	  if(modules!=null) {
		  resultMap.put("message", modules);
		   resultMap.put("success", true);
	  }
	   
	  return resultMap;
	  
	  }
	  
	 
	 
	  
	  
	  
	  //����ģ����Ϣ
	  @GetMapping(value="/CreateModule")
	  public @ResponseBody Map<String, Object>
	  CreateModule(String Name, Integer ParentId, String path, Integer Weight) {
	  Map<String, Object> resultMap=Manager.getResultMap();
	  int n=moduleService.CreateModule(Name, ParentId, path, Weight);
	  if(n>0) {
		   resultMap.put("message", "");
		   resultMap.put("success", true);
	  }
	   
	  return resultMap;
	  
	  }
	  
	  //����id�޸�ģ����Ϣ
	  @GetMapping(value="/UpdateModule")
	  public @ResponseBody Map<String, Object>
	  UpdateModule(Integer parentId,Integer id) {
	  Map<String, Object> resultMap=Manager.getResultMap();
	  int n=moduleService.UpdateModule(id, parentId);
	  if(n>0) {
		   resultMap.put("message", "");
		   resultMap.put("success", true);
	  }
	   
	  return resultMap;
	  
	  }
	  
	 
	  //����idɾ��ģ����Ϣ
	  @GetMapping(value="/DeleteModule")
	  public @ResponseBody Map<String, Object>
	  DeleteModule(Integer id) {
	  Map<String, Object> resultMap=Manager.getResultMap();
	  int n=moduleService.DeleteModule(id);
	  System.out.println(id);
	  if(n>0) {
		   resultMap.put("message", "");
		   resultMap.put("success", true);
	  }else {
		  resultMap.put("message", "ɾ������");
	  }
	   
	  return resultMap;
	  
	  }
	  
	  //�����û���ɫ
	  @GetMapping(value="AddUserToModule")
	  public @ResponseBody Map<String, Object>
	  AddUserToModule(String RoleId,Integer ModuleId){
		  String Id="";
		  Map<String, Object> resultMap=Manager.getResultMap();
		  int n=moduleService.AddUserToModule(Id,RoleId, ModuleId);
		  if(n>0) {
			  resultMap.put("success", true);
		  }
		  
		   return resultMap;
	  }
	  //�Ƴ��û���ɫ
	  @GetMapping(value="RemoveUserFromModule")
	  public @ResponseBody Map<String, Object>
	  RemoveUserFromModule(Integer ModuleId,String RoleId){
		  Map<String, Object> resultMap=Manager.getResultMap();
		  int n=moduleService.RemoveUserFromModule(RoleId, ModuleId);
		  if(n>0) {
			  resultMap.put("success", true);
		  }
		  
		   return resultMap;
	  }
}
