//package com.cxxy.demo.controller;
//
//import com.cxxy.demo.bean.*;
//import com.cxxy.demo.service.AdminManager;
//import com.cxxy.demo.util.JSONData;
//import com.cxxy.demo.util.Md5;
//import com.cxxy.demo.util.PaperUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class AdminController {
//
//	@Autowired
//	AdminManager adminManager;
////	public AdminManager getAdminManager() {
////		return adminManager;
////	}
////	public void setAdminManager(AdminManager adminManager) {
////		this.adminManager = adminManager;
////	}
//
//	String tip;
//
//	/**
//	 * @Title: saveAdmin
//	 * @Description: 保存修改个人信息
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_saveAdmin.action",method=RequestMethod.POST)
//	public String saveAdmin(User paramsUser,
//                            ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			//验证用户会话是否失效
//			if (!validateAdmin(httpSession)) {
//				return "loginTip";
//			}
//			 //保存修改个人信息
//			adminManager.updateUser(paramsUser);
//			//更新session
//			User admin = new User();
//			admin.setUser_id(paramsUser.getUser_id());
//			admin = adminManager.queryUser(admin);
//			httpSession.setAttribute("admin", admin);
//
//			setSuccessTip("编辑成功", "modifyInfo.jsp", model);
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("编辑异常", "modifyInfo.jsp", model);
//		}
//		return "infoTip";
//	}
//
//	/**
//	 * @Title: saveAdminPass
//	 * @Description: 保存修改个人密码
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_saveAdminPass.action",method=RequestMethod.POST)
//	public String saveAdminPass(User paramsUser,
//                                ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			//验证用户会话是否失效
//			if (!validateAdmin(httpSession)) {
//				return "loginTip";
//			}
//			User admin = (User)httpSession.getAttribute("admin");
//			if (!admin.getUser_pass().equals(Md5.makeMd5(paramsUser.getUser_passOld()))) {
//				setErrorTip("修改异常，原密码不正确", "modifyPwd.jsp", model);
//				return "infoTip";
//			}
//			 //保存修改个人密码
//			adminManager.updateUser(paramsUser);
//			//更新session
//			if (admin!=null) {
//				admin.setUser_pass(paramsUser.getUser_pass());
//				httpSession.setAttribute("admin", admin);
//			}
//
//			setSuccessTip("修改成功", "modifyPwd.jsp", model);
//		} catch (Exception e) {
//			setErrorTip("修改异常", "modifyPwd.jsp", model);
//		}
//		return "infoTip";
//	}
//
//	/**
//	 * @Title: listConfigs
//	 * @Description: 查询考勤参数
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_listConfigs.action")
//	public String listConfigs(Config paramsConfig, PaperUtil paperUtil,
//                              ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			if (paramsConfig==null) {
//				paramsConfig = new Config();
//			}
//			//设置分页信息
//			if (paperUtil==null) {
//				paperUtil = new PaperUtil();
//			}
//			paperUtil.setPagination(paramsConfig);
//			//总的条数
//			int[] sum={0};
//			//查询考勤参数列表
//			List<Config> configs = adminManager.listConfigs(paramsConfig,sum);
//			model.addAttribute("configs", configs);
//			paperUtil.setTotalCount(sum[0]);
//
//		} catch (Exception e) {
//			setErrorTip("查询异常", "Admin_listConfigs.action", model);
//			return "infoTip";
//		}
//
//		return "configShow";
//	}
//
//	/**
//	 * @Title: saveConfig
//	 * @Description: 保存编辑考勤参数
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_saveConfig.action",method=RequestMethod.POST)
//	@ResponseBody
//	public JSONData saveConfig(Config paramsConfig,
//                               ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		JSONData jsonData = new JSONData();
//		try {
//			 //保存编辑考勤参数
//			adminManager.updateConfig(paramsConfig);
//		} catch (Exception e) {
//			jsonData.setErrorReason("保存编辑考勤参数失败，服务器异常");
//			return jsonData;
//		}
//
//		return jsonData;
//	}
//
//	/**
//	 * @Title: listUsers2
//	 * @Description: 查询教师
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_listUsers2.action")
//	public String listUsers2(User paramsUser, PaperUtil paperUtil,
//                             ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			if (paramsUser==null) {
//				paramsUser = new User();
//			}
//			if (paperUtil==null) {
//				paperUtil = new PaperUtil();
//			}
//			//总的条数
//			int[] sum={0};
//			//查询教师列表
//			paramsUser.setUser_type(2);
//			List<User> users = adminManager.listUsers(paramsUser,sum);
//			model.addAttribute("users", users);
//			paperUtil.setTotalCount(sum[0]);
//			model.addAttribute("paramsUser", paramsUser);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("查询教师异常", "main.jsp", model);
//			return "infoTip";
//		}
//
//		return "userShow2";
//	}
//
//	/**
//	 * @Title: addUserShow2
//	 * @Description: 显示添加教师页面
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_addUserShow2.action",method=RequestMethod.GET)
//	public String addUserShow2(ModelMap model){
//		return "userEdit2";
//	}
//
//	/**
//	 * @Title: addUser2
//	 * @Description: 添加教师
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_addUser2.action",method=RequestMethod.POST)
//	public String addUser2(User paramsUser,
//                           ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			//检查教师名是否存在
//			User user = new User();
//			user.setUser_name(paramsUser.getUser_name());
//			user = adminManager.queryUser(user);
//			if (user!=null) {
//				model.addAttribute("tip","失败，该用户名已经存在！");
//				model.addAttribute("user", paramsUser);
//
//				return "userEdit";
//			}
//
//			 //添加教师
//			paramsUser.setUser_type(2);
//			adminManager.addUser(paramsUser);
//
//			setSuccessTip("添加成功", "Admin_listUsers2.action", model);
//		} catch (Exception e) {
//			setErrorTip("添加教师异常", "Admin_listUsers2.action", model);
//		}
//
//		return "infoTip";
//	}
//
//
//	/**
//	 * @Title: editUser2
//	 * @Description: 编辑教师
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_editUser2.action",method=RequestMethod.GET)
//	public String editUser2(User paramsUser,
//                            ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			 //得到教师
//			User user = adminManager.queryUser(paramsUser);
//			model.addAttribute("user", user);
//
//		} catch (Exception e) {
//			setErrorTip("查询教师异常", "Admin_listUsers2.action", model);
//			return "infoTip";
//		}
//
//		return "userEdit2";
//	}
//
//	/**
//	 * @Title: saveUser2
//	 * @Description: 保存编辑教师
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_saveUser2.action",method=RequestMethod.POST)
//	public String saveUser2(User paramsUser,
//                            ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			 //保存编辑教师
//			adminManager.updateUser(paramsUser);
//
//			setSuccessTip("编辑成功", "Admin_listUsers2.action", model);
//		} catch (Exception e) {
//			model.addAttribute("tip","编辑失败！");
//			model.addAttribute("user", paramsUser);
//			return "userEdit2";
//		}
//
//		return "infoTip";
//	}
//
//	/**
//	 * @Title: delUsers2
//	 * @Description: 删除教师
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_delUsers2.action")
//	public String delUsers2(User paramsUser,
//                            ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			 //删除教师
//			adminManager.delUsers(paramsUser);
//
//			setSuccessTip("删除教师成功", "Admin_listUsers2.action", model);
//		} catch (Exception e) {
//			setErrorTip("删除教师异常", "Admin_listUsers2.action", model);
//		}
//
//		return "infoTip";
//	}
//
//	/**
//	 * @Title: listUsers
//	 * @Description: 查询学生
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_listUsers.action")
//	public String listUsers(User paramsUser, PaperUtil paperUtil,
//                            ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			if (paramsUser==null) {
//				paramsUser = new User();
//			}
//			if (paperUtil==null) {
//				paperUtil = new PaperUtil();
//			}
//			//总的条数
//			int[] sum={0};
//			//查询学生列表
//			paramsUser.setUser_type(1);
//			List<User> users = adminManager.listUsers(paramsUser,sum);
//			model.addAttribute("users", users);
//			paperUtil.setTotalCount(sum[0]);
//			model.addAttribute("paramsUser", paramsUser);
//
//			//查询班级
//			Dept dept = new Dept();
//			dept.setStart(-1);
//			List<Dept> depts = adminManager.listDepts(dept, null);
//			if (depts==null) {
//				depts = new ArrayList<Dept>();
//			}
//			model.addAttribute("depts", depts);
//		} catch (Exception e) {
//			setErrorTip("查询学生异常", "main.jsp", model);
//			return "infoTip";
//		}
//
//		return "userShow";
//	}
//
//	/**
//	 * @Title: addUserShow
//	 * @Description: 显示添加学生页面
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_addUserShow.action",method=RequestMethod.GET)
//	public String addUserShow(ModelMap model){
//		//查询班级
//		Dept dept = new Dept();
//		dept.setStart(-1);
//		List<Dept> depts = adminManager.listDepts(dept, null);
//		if (depts==null) {
//			depts = new ArrayList<Dept>();
//		}
//		model.addAttribute("depts", depts);
//
//		return "userEdit";
//	}
//
//	/**
//	 * @Title: addUser
//	 * @Description: 添加学生
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_addUser.action",method=RequestMethod.POST)
//	public String addUser(User paramsUser,
//                          ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			//检查学生名是否存在
//			User user = new User();
//			user.setUser_name(paramsUser.getUser_name());
//			user = adminManager.queryUser(user);
//			if (user!=null) {
//				model.addAttribute("tip","失败，该用户名已经存在！");
//				model.addAttribute("user", paramsUser);
//
//				//查询班级
//				Dept dept = new Dept();
//				dept.setStart(-1);
//				List<Dept> depts = adminManager.listDepts(dept, null);
//				if (depts==null) {
//					depts = new ArrayList<Dept>();
//				}
//				model.addAttribute("depts", depts);
//
//				return "userEdit";
//			}
//
//			 //添加学生
//			paramsUser.setUser_type(1);
//			adminManager.addUser(paramsUser);
//
//			setSuccessTip("添加成功", "Admin_listUsers.action", model);
//		} catch (Exception e) {
//			setErrorTip("添加学生异常", "Admin_listUsers.action", model);
//		}
//
//		return "infoTip";
//	}
//
//
//	/**
//	 * @Title: editUser
//	 * @Description: 编辑学生
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_editUser.action",method=RequestMethod.GET)
//	public String editUser(User paramsUser,
//                           ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			 //得到学生
//			User user = adminManager.queryUser(paramsUser);
//			model.addAttribute("user", user);
//
//			//查询班级
//			Dept dept = new Dept();
//			dept.setStart(-1);
//			List<Dept> depts = adminManager.listDepts(dept, null);
//			if (depts==null) {
//				depts = new ArrayList<Dept>();
//			}
//			model.addAttribute("depts", depts);
//		} catch (Exception e) {
//			setErrorTip("查询学生异常", "Admin_listUsers.action", model);
//			return "infoTip";
//		}
//
//		return "userEdit";
//	}
//
//	/**
//	 * @Title: saveUser
//	 * @Description: 保存编辑学生
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_saveUser.action",method=RequestMethod.POST)
//	public String saveUser(User paramsUser,
//                           ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			 //保存编辑学生
//			adminManager.updateUser(paramsUser);
//
//			setSuccessTip("编辑成功", "Admin_listUsers.action", model);
//		} catch (Exception e) {
//			model.addAttribute("tip","编辑失败！");
//			model.addAttribute("user", paramsUser);
//			//查询班级
//			Dept dept = new Dept();
//			dept.setStart(-1);
//			List<Dept> depts = adminManager.listDepts(dept, null);
//			if (depts==null) {
//				depts = new ArrayList<Dept>();
//			}
//			model.addAttribute("depts", depts);
//			return "userEdit";
//		}
//
//		return "infoTip";
//	}
//
//	/**
//	 * @Title: delUsers
//	 * @Description: 删除学生
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_delUsers.action")
//	public String delUsers(User paramsUser,
//                           ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			 //删除学生
//			adminManager.delUsers(paramsUser);
//
//			setSuccessTip("删除学生成功", "Admin_listUsers.action", model);
//		} catch (Exception e) {
//			setErrorTip("删除学生异常", "Admin_listUsers.action", model);
//		}
//
//		return "infoTip";
//	}
//
//	/**
//	 * @Title: listDepts
//	 * @Description: 查询班级
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_listDepts.action")
//	public String listDepts(Dept paramsDept, PaperUtil paperUtil,
//                            ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			if (paramsDept==null) {
//				paramsDept = new Dept();
//			}
//			if (paperUtil==null) {
//				paperUtil = new PaperUtil();
//			}
//			//总的条数
//			int[] sum={0};
//			//查询班级列表
//			List<Dept> depts = adminManager.listDepts(paramsDept,sum);
//			model.addAttribute("depts", depts);
//			paperUtil.setTotalCount(sum[0]);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("查询班级异常", "main.jsp", model);
//			return "infoTip";
//		}
//
//		return "deptShow";
//	}
//
//	/**
//	 * @Title: addDeptShow
//	 * @Description: 显示添加班级页面
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_addDeptShow.action",method=RequestMethod.GET)
//	public String addDeptShow(ModelMap model){
//		//查询教师
//		User user = new User();
//		user.setStart(-1);
//		user.setUser_type(2);
//		List<User> users = adminManager.listUsers(user, null);
//		if (users==null) {
//			users = new ArrayList<User>();
//		}
//		model.addAttribute("users", users);
//
//		return "deptEdit";
//	}
//
//	/**
//	 * @Title: addDept
//	 * @Description: 添加班级
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_addDept.action",method=RequestMethod.POST)
//	public String addDept(Dept paramsDept,
//                          ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			//检查班级是否存在
//			Dept dept = new Dept();
//			dept.setDept_name(paramsDept.getDept_name());
//			dept = adminManager.queryDept(dept);
//			if (dept!=null) {
//				model.addAttribute("tip","失败，该班级已经存在！");
//				model.addAttribute("dept", paramsDept);
//				//查询教师
//				User user = new User();
//				user.setStart(-1);
//				user.setUser_type(2);
//				List<User> users = adminManager.listUsers(user, null);
//				if (users==null) {
//					users = new ArrayList<User>();
//				}
//				model.addAttribute("users", users);
//
//				return "deptEdit";
//			}
//
//			//检查辅导员是否存在
//			dept = new Dept();
//			dept.setUser_id(paramsDept.getUser_id());
//			dept = adminManager.queryDept(dept);
//			if (dept!=null) {
//				model.addAttribute("tip","失败，辅导员已经被其他班级占用！");
//				model.addAttribute("dept", paramsDept);
//				//查询教师
//				User user = new User();
//				user.setStart(-1);
//				user.setUser_type(2);
//				List<User> users = adminManager.listUsers(user, null);
//				if (users==null) {
//					users = new ArrayList<User>();
//				}
//				model.addAttribute("users", users);
//
//				return "deptEdit";
//			}
//
//			 //添加班级
//			adminManager.addDept(paramsDept);
//
//			setSuccessTip("添加成功", "Admin_listDepts.action", model);
//		} catch (Exception e) {
//			setErrorTip("添加班级异常", "Admin_listDepts.action", model);
//		}
//
//		return "infoTip";
//	}
//
//
//	/**
//	 * @Title: editDept
//	 * @Description: 编辑班级
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_editDept.action",method=RequestMethod.GET)
//	public String editDept(Dept paramsDept,
//                           ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			 //得到班级
//			Dept dept = adminManager.queryDept(paramsDept);
//			model.addAttribute("dept", dept);
//
//			//查询教师
//			User user = new User();
//			user.setStart(-1);
//			user.setUser_type(2);
//			List<User> users = adminManager.listUsers(user, null);
//			if (users==null) {
//				users = new ArrayList<User>();
//			}
//			model.addAttribute("users", users);
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("查询班级异常", "Admin_listDepts.action", model);
//			return "infoTip";
//		}
//
//		return "deptEdit";
//	}
//
//	/**
//	 * @Title: saveDept
//	 * @Description: 保存编辑班级
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_saveDept.action",method=RequestMethod.POST)
//	public String saveDept(Dept paramsDept,
//                           ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			//检查班级是否存在
//			Dept dept = new Dept();
//			dept.setDept_name(paramsDept.getDept_name());
//			dept = adminManager.queryDept(dept);
//			if (dept!=null&&dept.getDept_id()!=paramsDept.getDept_id()) {
//				model.addAttribute("tip","失败，该班级已经存在！");
//				model.addAttribute("dept", paramsDept);
//
//				//查询教师
//				User user = new User();
//				user.setStart(-1);
//				user.setUser_type(2);
//				List<User> users = adminManager.listUsers(user, null);
//				if (users==null) {
//					users = new ArrayList<User>();
//				}
//				model.addAttribute("users", users);
//
//				return "deptEdit";
//			}
//			//检查辅导员是否存在
//			dept = new Dept();
//			dept.setUser_id(paramsDept.getUser_id());
//			dept = adminManager.queryDept(dept);
//			if (dept!=null&&dept.getDept_id()!=paramsDept.getDept_id()) {
//				model.addAttribute("tip","失败，辅导员已经被其他班级占用！");
//				model.addAttribute("dept", paramsDept);
//				//查询教师
//				User user = new User();
//				user.setStart(-1);
//				user.setUser_type(2);
//				List<User> users = adminManager.listUsers(user, null);
//				if (users==null) {
//					users = new ArrayList<User>();
//				}
//				model.addAttribute("users", users);
//
//				return "deptEdit";
//			}
//			 //保存编辑班级
//			adminManager.updateDept(paramsDept);
//
//			setSuccessTip("编辑成功", "Admin_listDepts.action", model);
//		} catch (Exception e) {
//			model.addAttribute("tip","编辑失败");
//			model.addAttribute("dept", paramsDept);
//			return "deptEdit";
//		}
//
//		return "infoTip";
//	}
//
//	/**
//	 * @Title: delDepts
//	 * @Description: 删除班级
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_delDepts.action")
//	public String delDepts(Dept paramsDept,
//                           ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			 //删除班级
//			adminManager.delDepts(paramsDept);
//
//			setSuccessTip("删除班级成功", "Admin_listDepts.action", model);
//		} catch (Exception e) {
//			setErrorTip("删除班级异常", "Admin_listDepts.action", model);
//		}
//
//		return "infoTip";
//	}
//
//	/**
//	 * @Title: listCourses
//	 * @Description: 查询课程
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_listCourses.action")
//	public String listCourses(Course paramsCourse, PaperUtil paperUtil,
//                              ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			if (paramsCourse==null) {
//				paramsCourse = new Course();
//			}
//			if (paperUtil==null) {
//				paperUtil = new PaperUtil();
//			}
//			//总的条数
//			int[] sum={0};
//			//查询课程列表
//			List<Course> courses = adminManager.listCourses(paramsCourse,sum);
//			model.addAttribute("courses", courses);
//			paperUtil.setTotalCount(sum[0]);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("查询课程异常", "main.jsp", model);
//			return "infoTip";
//		}
//
//		return "courseShow";
//	}
//
//	/**
//	 * @Title: addCourseShow
//	 * @Description: 显示添加课程页面
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_addCourseShow.action",method=RequestMethod.GET)
//	public String addCourseShow(ModelMap model){
//		return "courseEdit";
//	}
//
//	/**
//	 * @Title: addCourse
//	 * @Description: 添加课程
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_addCourse.action",method=RequestMethod.POST)
//	public String addCourse(Course paramsCourse,
//                            ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			//检查课程是否存在
//			Course course = new Course();
//			course.setCourse_name(paramsCourse.getCourse_name());
//			course = adminManager.queryCourse(course);
//			if (course!=null) {
//				model.addAttribute("tip","失败，该课程已经存在！");
//				model.addAttribute("course", paramsCourse);
//
//				return "courseEdit";
//			}
//
//			 //添加课程
//			adminManager.addCourse(paramsCourse);
//
//			setSuccessTip("添加成功", "Admin_listCourses.action", model);
//		} catch (Exception e) {
//			setErrorTip("添加课程异常", "Admin_listCourses.action", model);
//		}
//
//		return "infoTip";
//	}
//
//
//	/**
//	 * @Title: editCourse
//	 * @Description: 编辑课程
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_editCourse.action",method=RequestMethod.GET)
//	public String editCourse(Course paramsCourse,
//                             ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			 //得到课程
//			Course course = adminManager.queryCourse(paramsCourse);
//			model.addAttribute("course", course);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("查询课程异常", "Admin_listCourses.action", model);
//			return "infoTip";
//		}
//
//		return "courseEdit";
//	}
//
//	/**
//	 * @Title: saveCourse
//	 * @Description: 保存编辑课程
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_saveCourse.action",method=RequestMethod.POST)
//	public String saveCourse(Course paramsCourse,
//                             ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			//检查课程是否存在
//			Course course = new Course();
//			course.setCourse_name(paramsCourse.getCourse_name());
//			course = adminManager.queryCourse(course);
//			if (course!=null&&course.getCourse_id()!=paramsCourse.getCourse_id()) {
//				model.addAttribute("tip","失败，该课程已经存在！");
//				model.addAttribute("course", paramsCourse);
//
//				return "courseEdit";
//			}
//			 //保存编辑课程
//			adminManager.updateCourse(paramsCourse);
//
//			setSuccessTip("编辑成功", "Admin_listCourses.action", model);
//		} catch (Exception e) {
//			model.addAttribute("tip","编辑失败");
//			model.addAttribute("course", paramsCourse);
//			return "courseEdit";
//		}
//
//		return "infoTip";
//	}
//
//	/**
//	 * @Title: delCourses
//	 * @Description: 删除课程
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_delCourses.action")
//	public String delCourses(Course paramsCourse,
//                             ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			 //删除课程
//			adminManager.delCourses(paramsCourse);
//
//			setSuccessTip("删除课程成功", "Admin_listCourses.action", model);
//		} catch (Exception e) {
//			setErrorTip("删除课程异常", "Admin_listCourses.action", model);
//		}
//
//		return "infoTip";
//	}
//
//
//	/**
//	 * @Title: listPlans
//	 * @Description: 查询教学安排
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_listPlans.action")
//	public String listPlans(Plan paramsPlan, PaperUtil paperUtil,
//                            ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			if (paramsPlan==null) {
//				paramsPlan = new Plan();
//			}
//			//设置分页信息
//			if (paperUtil==null) {
//				paperUtil = new PaperUtil();
//			}
//			paperUtil.setPagination(paramsPlan);
//			//总的条数
//			int[] sum={0};
//			//查询课表列表
//			List<Plan> plans = adminManager.listPlans(paramsPlan, sum);
//			model.addAttribute("plans", plans);
//			paperUtil.setTotalCount(sum[0]);
//			model.addAttribute("paramsPlan", paramsPlan);
//
//			//查询班级
//			Dept dept = new Dept();
//			dept.setStart(-1);
//			List<Dept> depts = adminManager.listDepts(dept, null);
//			if (depts==null) {
//				depts = new ArrayList<Dept>();
//			}
//			model.addAttribute("depts", depts);
//
//			//查询课程
//			Course course = new Course();
//			course.setStart(-1);
//			List<Course> courses = adminManager.listCourses(course, null);
//			if (courses==null) {
//				courses = new ArrayList<Course>();
//			}
//			model.addAttribute("courses", courses);
//
//			//查询教师
//			User user = new User();
//			user.setStart(-1);
//			user.setUser_type(2);
//			List<User> users = adminManager.listUsers(user, null);
//			if (users==null) {
//				users = new ArrayList<User>();
//			}
//			model.addAttribute("users", users);
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("查询教学安排异常", "main.jsp", model);
//			return "infoTip";
//		}
//
//		return "planShow";
//	}
//
//	/**
//	 * @Title: listPlansView
//	 * @Description: 查询班级课表视图
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_listPlansView.action")
//	public String listPlansView(Plan paramsPlan, PaperUtil paperUtil,
//                                ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			if (paramsPlan==null) {
//				paramsPlan = new Plan();
//			}
//			//身份限制
//			User admin = (User)httpSession.getAttribute("admin");
//			if (admin.getUser_type()==1) {
//				paramsPlan.setDept_id(admin.getDept_id());
//			}else if (admin.getUser_type()==2) {
//				paramsPlan.setDept_id(admin.getDept_fdy());
//			}
//			//查询课表列表
//			List<Plan> plans = adminManager.listPlansByClazz(paramsPlan);
//			model.addAttribute("plans", plans);
//			model.addAttribute("paramsPlan", paramsPlan);
//
//			//查询班级
//			Dept dept = new Dept();
//			dept.setStart(-1);
//			if (admin.getUser_type()==1) {
//				dept.setDept_id(admin.getDept_id());
//			}else if (admin.getUser_type()==2) {
//				dept.setDept_id(admin.getDept_fdy());
//			}
//			List<Dept> depts = adminManager.listDepts(dept, null);
//			if (depts==null) {
//				depts = new ArrayList<Dept>();
//			}
//			model.addAttribute("depts", depts);
//
//			//查询课程
//			Course course = new Course();
//			course.setStart(-1);
//			List<Course> courses = adminManager.listCourses(course, null);
//			if (courses==null) {
//				courses = new ArrayList<Course>();
//			}
//			model.addAttribute("courses", courses);
//
//			//查询教师
//			User user = new User();
//			user.setStart(-1);
//			user.setUser_type(2);
//			List<User> users = adminManager.listUsers(user, null);
//			if (users==null) {
//				users = new ArrayList<User>();
//			}
//			model.addAttribute("users", users);
//
//		} catch (Exception e) {
//			setErrorTip("查询课表异常", "main.jsp", model);
//			return "infoTip";
//		}
//
//		return "planViewShow";
//	}
//
//	/**
//	 * @Title: listPlansSelf
//	 * @Description: 查询任课教师课表
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_listPlansSelf.action")
//	public String listPlansSelf(Plan paramsPlan, PaperUtil paperUtil,
//                                ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			if (paramsPlan==null) {
//				paramsPlan = new Plan();
//			}
//			//用户身份限制
//			User admin = (User)httpSession.getAttribute("admin");
//			if (admin.getUser_type()==2) {
//				paramsPlan.setUser_id(admin.getUser_id());
//			}
//			//查询课表列表
//			List<Plan> plans = adminManager.listPlansByClazz(paramsPlan);
//			model.addAttribute("plans", plans);
//			model.addAttribute("paramsPlan", paramsPlan);
//
//			//查询班级
//			Dept dept = new Dept();
//			dept.setStart(-1);
//			List<Dept> depts = adminManager.listDepts(dept, null);
//			if (depts==null) {
//				depts = new ArrayList<Dept>();
//			}
//			model.addAttribute("depts", depts);
//
//			//查询课程
//			Course course = new Course();
//			course.setStart(-1);
//			List<Course> courses = adminManager.listCourses(course, null);
//			if (courses==null) {
//				courses = new ArrayList<Course>();
//			}
//			model.addAttribute("courses", courses);
//
//		} catch (Exception e) {
//			setErrorTip("查询课表异常", "main.jsp", model);
//			return "infoTip";
//		}
//
//		return "planSelfShow";
//	}
//
//	/**
//	 * @Title: addPlanShow
//	 * @Description: 显示添加课表页面
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_addPlanShow.action",method=RequestMethod.GET)
//	public String addPlanShow(ModelMap model){
//		//查询班级
//		Dept dept = new Dept();
//		dept.setStart(-1);
//		List<Dept> depts = adminManager.listDepts(dept, null);
//		if (depts==null) {
//			depts = new ArrayList<Dept>();
//		}
//		model.addAttribute("depts", depts);
//
//		//查询教师
//		User user = new User();
//		user.setStart(-1);
//		user.setUser_type(2);
//		List<User> users = adminManager.listUsers(user, null);
//		if (users==null) {
//			users = new ArrayList<User>();
//		}
//		model.addAttribute("users", users);
//
//		//查询课程
//		Course course = new Course();
//		course.setStart(-1);
//		List<Course> courses = adminManager.listCourses(course, null);
//		if (courses==null) {
//			courses = new ArrayList<Course>();
//		}
//		model.addAttribute("courses", courses);
//
//		return "planEdit";
//	}
//
//	/**
//	 * @Title: addPlan
//	 * @Description: 添加课表
//	 * 同一个班级不能同时上两门不同的课程
//	 * 同一个教师不能同时上两门不同的课程
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_addPlan.action",method=RequestMethod.POST)
//	public String addPlan(Plan paramsPlan,
//                          ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			//检查班级-时间是否存在
//			boolean flag = true;
//			Plan plan = new Plan();
//			plan.setDept_id(paramsPlan.getDept_id());
//			plan.setPlan_week(paramsPlan.getPlan_week());
//			plan.setPlan_lesson(paramsPlan.getPlan_lesson());
//			plan = adminManager.queryPlan(plan);
//			if (plan!=null) {
//				model.addAttribute("tip","失败，该班级上课时间已经存在！");
//				model.addAttribute("plan", paramsPlan);
//				flag = false;
//			}
//			//检查教师-时间是否存在
//			plan = new Plan();
//			plan.setUser_id(paramsPlan.getUser_id());
//			plan.setPlan_week(paramsPlan.getPlan_week());
//			plan.setPlan_lesson(paramsPlan.getPlan_lesson());
//			plan = adminManager.queryPlan(plan);
//			if (plan!=null) {
//				model.addAttribute("tip","失败，该教师上课时间已经存在！");
//				model.addAttribute("plan", paramsPlan);
//				flag = false;
//			}
//			if (!flag) {
//				//查询班级
//				Dept dept = new Dept();
//				dept.setStart(-1);
//				List<Dept> depts = adminManager.listDepts(dept, null);
//				if (depts==null) {
//					depts = new ArrayList<Dept>();
//				}
//				model.addAttribute("depts", depts);
//
//				//查询教师
//				User user = new User();
//				user.setStart(-1);
//				user.setUser_type(2);
//				List<User> users = adminManager.listUsers(user, null);
//				if (users==null) {
//					users = new ArrayList<User>();
//				}
//				model.addAttribute("users", users);
//
//				//查询课程
//				Course course = new Course();
//				course.setStart(-1);
//				List<Course> courses = adminManager.listCourses(course, null);
//				if (courses==null) {
//					courses = new ArrayList<Course>();
//				}
//				model.addAttribute("courses", courses);
//
//				return "planEdit";
//			}
//
//			 //添加课表
//			adminManager.addPlan(paramsPlan);
//
//			setSuccessTip("添加教学安排成功", "Admin_listPlans.action", model);
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("添加教学安排异常", "Admin_listPlans.action", model);
//		}
//
//		return "infoTip";
//	}
//
//	/**
//	 * @Title: editPlan
//	 * @Description: 显示编辑课表页面
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_editPlan.action",method=RequestMethod.GET)
//	public String editPlan(Plan paramsPlan,ModelMap model){
//		//查询班级
//		Dept dept = new Dept();
//		dept.setStart(-1);
//		List<Dept> depts = adminManager.listDepts(dept, null);
//		if (depts==null) {
//			depts = new ArrayList<Dept>();
//		}
//		model.addAttribute("depts", depts);
//
//		//查询教师
//		User user = new User();
//		user.setStart(-1);
//		user.setUser_type(2);
//		List<User> users = adminManager.listUsers(user, null);
//		if (users==null) {
//			users = new ArrayList<User>();
//		}
//		model.addAttribute("users", users);
//
//		//查询课程
//		Course course = new Course();
//		course.setStart(-1);
//		List<Course> courses = adminManager.listCourses(course, null);
//		if (courses==null) {
//			courses = new ArrayList<Course>();
//		}
//		model.addAttribute("courses", courses);
//
//		Plan plan = adminManager.queryPlan(paramsPlan);
//		model.addAttribute("plan", plan);
//
//		return "planEdit";
//	}
//
//	/**
//	 * @Title: savePlan
//	 * @Description: 添加课表
//	 * 同一个班级不能同时上两门不同的课程
//	 * 同一个教师不能同时上两门不同的课程
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_savePlan.action",method=RequestMethod.POST)
//	public String savePlan(Plan paramsPlan,
//                           ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			//检查班级-时间是否存在
//			boolean flag = true;
//			Plan plan = new Plan();
//			plan.setDept_id(paramsPlan.getDept_id());
//			plan.setPlan_week(paramsPlan.getPlan_week());
//			plan.setPlan_lesson(paramsPlan.getPlan_lesson());
//			plan = adminManager.queryPlan(plan);
//			if (plan!=null && plan.getPlan_id()!=paramsPlan.getPlan_id()) {
//				model.addAttribute("tip","失败，该班级上课时间已经存在！");
//				model.addAttribute("plan", paramsPlan);
//				flag = false;
//			}
//			//检查教师-时间是否存在
//			plan = new Plan();
//			plan.setUser_id(paramsPlan.getUser_id());
//			plan.setPlan_week(paramsPlan.getPlan_week());
//			plan.setPlan_lesson(paramsPlan.getPlan_lesson());
//			plan = adminManager.queryPlan(plan);
//			if (plan!=null && plan.getPlan_id()!=paramsPlan.getPlan_id()) {
//				model.addAttribute("tip","失败，该教师上课时间已经存在！");
//				model.addAttribute("plan", paramsPlan);
//				flag = false;
//			}
//			if (!flag) {
//				//查询班级
//				Dept dept = new Dept();
//				dept.setStart(-1);
//				List<Dept> depts = adminManager.listDepts(dept, null);
//				if (depts==null) {
//					depts = new ArrayList<Dept>();
//				}
//				model.addAttribute("depts", depts);
//
//				//查询教师
//				User user = new User();
//				user.setStart(-1);
//				user.setUser_type(2);
//				List<User> users = adminManager.listUsers(user, null);
//				if (users==null) {
//					users = new ArrayList<User>();
//				}
//				model.addAttribute("users", users);
//
//				//查询课程
//				Course course = new Course();
//				course.setStart(-1);
//				List<Course> courses = adminManager.listCourses(course, null);
//				if (courses==null) {
//					courses = new ArrayList<Course>();
//				}
//				model.addAttribute("courses", courses);
//
//				return "planEdit";
//			}
//
//			 //编辑课表
//			adminManager.updatePlan(paramsPlan);
//
//			setSuccessTip("编辑教学安排成功", "Admin_listPlans.action", model);
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("编辑教学安排异常", "Admin_listPlans.action", model);
//		}
//
//		return "infoTip";
//	}
//
//	/**
//	 * @Title: delPlans
//	 * @Description: 删除课表
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_delPlans.action")
//	public String delPlans(Plan paramsPlan,
//                           ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			 //删除课表
//			adminManager.delPlans(paramsPlan);
//
//			setSuccessTip("删除教学安排成功", "Admin_listPlans.action", model);
//		} catch (Exception e) {
//			setErrorTip("删除教学安排异常", "Admin_listPlans.action", model);
//		}
//
//		return "infoTip";
//	}
//
//
//	/**
//	 * @Title: listAttends
//	 * @Description: 查询考勤记录
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_listAttends.action")
//	public String listAttends(Attend paramsAttend, PaperUtil paperUtil,
//                              ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			if (paramsAttend==null) {
//				paramsAttend = new Attend();
//			}
//			if (paperUtil==null) {
//				paperUtil = new PaperUtil();
//			}
//			paperUtil.setPagination(paramsAttend);
//			//总的条数
//			int[] sum={0};
//			//身份限制
//			User admin = (User)httpSession.getAttribute("admin");
//			if (admin.getUser_type()==1) {
//				paramsAttend.setUser_id(admin.getUser_id());
//			}else if (admin.getUser_type()==2) {
//				paramsAttend.setDept_id(admin.getDept_fdy());
//			}
//			//查询考勤列表
//			List<Attend> attends = adminManager.listAttends(paramsAttend,sum);
//			model.addAttribute("attends", attends);
//			paperUtil.setTotalCount(sum[0]);
//			model.addAttribute("paramsAttend", paramsAttend);
//
//			//查询班级
//			Dept dept = new Dept();
//			dept.setStart(-1);
//			List<Dept> depts = adminManager.listDepts(dept, null);
//			if (depts==null) {
//				depts = new ArrayList<Dept>();
//			}
//			model.addAttribute("depts", depts);
//
//			//查询课程
//			Course course = new Course();
//			course.setStart(-1);
//			List<Course> courses = adminManager.listCourses(course, null);
//			if (courses==null) {
//				courses = new ArrayList<Course>();
//			}
//			model.addAttribute("courses", courses);
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("查询考勤异常", "main.jsp", model);
//			return "infoTip";
//		}
//
//		return "attendShow";
//	}
//
//	/**
//	 * @Title: listAttendsSelf
//	 * @Description: 查询考勤记录--任课教师
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_listAttendsSelf.action")
//	public String listAttendsSelf(Attend paramsAttend, PaperUtil paperUtil,
//                                  ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			if (paramsAttend==null) {
//				paramsAttend = new Attend();
//			}
//			if (paperUtil==null) {
//				paperUtil = new PaperUtil();
//			}
//			paperUtil.setPagination(paramsAttend);
//			//总的条数
//			int[] sum={0};
//			//身份限制
//			User admin = (User)httpSession.getAttribute("admin");
//			if (admin.getUser_type()==2) {
//				paramsAttend.setTeacher_id(admin.getUser_id());
//			}
//			//查询考勤列表
//			List<Attend> attends = adminManager.listAttends(paramsAttend,sum);
//			model.addAttribute("attends", attends);
//			paperUtil.setTotalCount(sum[0]);
//			model.addAttribute("paramsAttend", paramsAttend);
//
//			//查询班级
//			Dept dept = new Dept();
//			dept.setStart(-1);
//			List<Dept> depts = adminManager.listDepts(dept, null);
//			if (depts==null) {
//				depts = new ArrayList<Dept>();
//			}
//			model.addAttribute("depts", depts);
//
//			//查询课程
//			Course course = new Course();
//			course.setStart(-1);
//			List<Course> courses = adminManager.listCourses(course, null);
//			if (courses==null) {
//				courses = new ArrayList<Course>();
//			}
//			model.addAttribute("courses", courses);
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("查询考勤异常", "main.jsp", model);
//			return "infoTip";
//		}
//
//		return "attendSelfShow";
//	}
//
//	/**
//	 * @Title: listAttendsClazz
//	 * @Description: 查询班级考勤汇总
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_listAttendsClazz.action")
//	public String listAttendsClazz(Attend paramsAttend, PaperUtil paperUtil,
//                                   ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			if (paramsAttend==null) {
//				paramsAttend = new Attend();
//			}
//			if (paperUtil==null) {
//				paperUtil = new PaperUtil();
//			}
//			//身份限制
//			User admin = (User)httpSession.getAttribute("admin");
//			if (admin.getUser_type()==2) {
//				paramsAttend.setDept_id(admin.getDept_fdy());
//			}
//			//查询考勤列表
//			if (paramsAttend.getDept_id()==0 || paramsAttend.getCourse_id()==0) {
//				model.addAttribute("attends", new ArrayList<>());
//				paperUtil.setTotalCount(0);
//			}else {
//				List<Attend> attends = adminManager.listAttendsByClazzCourse(paramsAttend);
//				model.addAttribute("attends", attends);
//			}
//			model.addAttribute("paramsAttend", paramsAttend);
//
//			//查询班级
//			Dept dept = new Dept();
//			dept.setStart(-1);
//			if (admin.getUser_type()==2) {
//				dept.setDept_id(admin.getDept_fdy());
//			}
//			List<Dept> depts = adminManager.listDepts(dept, null);
//			if (depts==null) {
//				depts = new ArrayList<Dept>();
//			}
//			model.addAttribute("depts", depts);
//
//			//查询课程
//			Course course = new Course();
//			course.setStart(-1);
//			List<Course> courses = adminManager.listCourses(course, null);
//			if (courses==null) {
//				courses = new ArrayList<Course>();
//			}
//			model.addAttribute("courses", courses);
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("查询考勤异常", "main.jsp", model);
//			return "infoTip";
//		}
//
//		return "attendClazzShow";
//	}
//
//	/**
//	 * @Title: listAttendsClazzSelf
//	 * @Description: 查询班级考勤汇总--任课教师
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_listAttendsClazzSelf.action")
//	public String listAttendsClazzSelf(Attend paramsAttend, PaperUtil paperUtil,
//                                       ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			if (paramsAttend==null) {
//				paramsAttend = new Attend();
//			}
//			if (paperUtil==null) {
//				paperUtil = new PaperUtil();
//			}
//			//身份限制
//			User admin = (User)httpSession.getAttribute("admin");
//			if (admin.getUser_type()==2) {
//				paramsAttend.setTeacher_id(admin.getUser_id());
//			}
//			//查询考勤列表
//			if (paramsAttend.getDept_id()==0 || paramsAttend.getCourse_id()==0) {
//				model.addAttribute("attends", new ArrayList<>());
//				paperUtil.setTotalCount(0);
//			}else {
//				List<Attend> attends = adminManager.listAttendsByClazzCourse(paramsAttend);
//				model.addAttribute("attends", attends);
//			}
//			model.addAttribute("paramsAttend", paramsAttend);
//
//			//查询班级
//			Dept dept = new Dept();
//			dept.setStart(-1);
//			List<Dept> depts = adminManager.listDepts(dept, null);
//			if (depts==null) {
//				depts = new ArrayList<Dept>();
//			}
//			model.addAttribute("depts", depts);
//
//			//查询课程
//			Course course = new Course();
//			course.setStart(-1);
//			List<Course> courses = adminManager.listCourses(course, null);
//			if (courses==null) {
//				courses = new ArrayList<Course>();
//			}
//			model.addAttribute("courses", courses);
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("查询考勤异常", "main.jsp", model);
//			return "infoTip";
//		}
//
//		return "attendClazzSelfShow";
//	}
//
//	/**
//	 * @Title: listAttendsStudent
//	 * @Description: 查询考勤成绩统计
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_listAttendsStudent.action")
//	public String listAttendsStudent(Attend paramsAttend, PaperUtil paperUtil,
//                                     ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			if (paramsAttend==null) {
//				paramsAttend = new Attend();
//			}
//			if (paperUtil==null) {
//				paperUtil = new PaperUtil();
//			}
//			paperUtil.setPagination(paramsAttend);
//			//总的条数
//			int[] sum={0};
//			//身份限制
//			User admin = (User)httpSession.getAttribute("admin");
//			if (admin.getUser_type()==2) {
//				paramsAttend.setDept_id(admin.getDept_fdy());
//			}else if (admin.getUser_type()==1) {
//				paramsAttend.setUser_id(admin.getUser_id());
//			}
//			//查询考勤列表
//			List<Attend> attends = adminManager.listAttendsByStuCourse(paramsAttend, sum);
//			paperUtil.setTotalCount(sum[0]);
//			model.addAttribute("attends", attends);
//			model.addAttribute("paramsAttend", paramsAttend);
//
//			//查询班级
//			Dept dept = new Dept();
//			dept.setStart(-1);
//			List<Dept> depts = adminManager.listDepts(dept, null);
//			if (depts==null) {
//				depts = new ArrayList<Dept>();
//			}
//			model.addAttribute("depts", depts);
//
//			//查询课程
//			Course course = new Course();
//			course.setStart(-1);
//			List<Course> courses = adminManager.listCourses(course, null);
//			if (courses==null) {
//				courses = new ArrayList<Course>();
//			}
//			model.addAttribute("courses", courses);
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("查询考勤异常", "main.jsp", model);
//			return "infoTip";
//		}
//
//		return "attendStudentShow";
//	}
//
//	/**
//	 * @Title: listAttendsStudentSelf
//	 * @Description: 查询考勤成绩统计--任课教师
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_listAttendsStudentSelf.action")
//	public String listAttendsStudentSelf(Attend paramsAttend, PaperUtil paperUtil,
//                                         ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			if (paramsAttend==null) {
//				paramsAttend = new Attend();
//			}
//			if (paperUtil==null) {
//				paperUtil = new PaperUtil();
//			}
//			paperUtil.setPagination(paramsAttend);
//			//总的条数
//			int[] sum={0};
//			//身份限制
//			User admin = (User)httpSession.getAttribute("admin");
//			if (admin.getUser_type()==2) {
//				paramsAttend.setTeacher_id(admin.getUser_id());
//			}
//			//查询考勤列表
//			List<Attend> attends = adminManager.listAttendsByStuCourse(paramsAttend, sum);
//			paperUtil.setTotalCount(sum[0]);
//			model.addAttribute("attends", attends);
//			model.addAttribute("paramsAttend", paramsAttend);
//
//			//查询班级
//			Dept dept = new Dept();
//			dept.setStart(-1);
//			List<Dept> depts = adminManager.listDepts(dept, null);
//			if (depts==null) {
//				depts = new ArrayList<Dept>();
//			}
//			model.addAttribute("depts", depts);
//
//			//查询课程
//			Course course = new Course();
//			course.setStart(-1);
//			List<Course> courses = adminManager.listCourses(course, null);
//			if (courses==null) {
//				courses = new ArrayList<Course>();
//			}
//			model.addAttribute("courses", courses);
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("查询考勤异常", "main.jsp", model);
//			return "infoTip";
//		}
//
//		return "attendStudentSelfShow";
//	}
//
//	/**
//	 * @Title: listPlansAttend
//	 * @Description: 查询教学安排--进行点名
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_listPlansAttend.action")
//	public String listPlansAttend(Plan paramsPlan, PaperUtil paperUtil,
//                                  ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			if (paramsPlan==null) {
//				paramsPlan = new Plan();
//			}
//			//设置分页信息
//			if (paperUtil==null) {
//				paperUtil = new PaperUtil();
//			}
//			paramsPlan.setStart(-1);
//			//身份限制
//			User admin = (User)httpSession.getAttribute("admin");
//			paramsPlan.setUser_id(admin.getUser_id());
//			//查询课表列表
//			List<Plan> plans = adminManager.listPlans(paramsPlan, null);
//			model.addAttribute("plans", plans);
//			model.addAttribute("paramsPlan", paramsPlan);
//
//			//查询班级
//			Dept dept = new Dept();
//			dept.setStart(-1);
//			List<Dept> depts = adminManager.listDepts(dept, null);
//			if (depts==null) {
//				depts = new ArrayList<Dept>();
//			}
//			model.addAttribute("depts", depts);
//
//			//查询课程
//			Course course = new Course();
//			course.setStart(-1);
//			List<Course> courses = adminManager.listCourses(course, null);
//			if (courses==null) {
//				courses = new ArrayList<Course>();
//			}
//			model.addAttribute("courses", courses);
//
//			//查询教师
//			User user = new User();
//			user.setStart(-1);
//			user.setUser_type(2);
//			List<User> users = adminManager.listUsers(user, null);
//			if (users==null) {
//				users = new ArrayList<User>();
//			}
//			model.addAttribute("users", users);
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("查询教学安排异常", "main.jsp", model);
//			return "infoTip";
//		}
//
//		return "planAttendShow";
//	}
//
//	/**
//	 * @Title: addAttendShow
//	 * @Description: 显示添加考勤页面
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_addAttendShow.action",method=RequestMethod.GET)
//	public String addAttendShow(Plan paramsPlan,ModelMap model){
//		try {
//			Plan plan = adminManager.queryPlan(paramsPlan);
//			model.addAttribute("plan", plan);
//
//			//查询学生记录
//			User user = new User();
//			user.setStart(-1);
//			user.setUser_type(1);
//			user.setDept_fdy(plan.getDept_id());
//			List<User> users = adminManager.listUsers(user, null);
//			if (users==null) {
//				users = new ArrayList<User>();
//			}
//			model.addAttribute("users", users);
//		}catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("点名展示异常", "main.jsp", model);
//			return "infoTip";
//		}
//
//		return "attendEdit";
//	}
//
//	/**
//	 * @Title: addAttend
//	 * @Description: 添加考勤
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_addAttend.action",method=RequestMethod.POST)
//	@ResponseBody
//	public JSONData addAttend(Attend paramsAttend,
//                              ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		JSONData jsonData = new JSONData();
//		try {
//			//检查点名是否存在
//			Attend attend = new Attend();
//			attend.setPlan_id(paramsAttend.getPlan_id());
//			attend.setAttend_dmrq(paramsAttend.getAttend_dmrq());
//			attend.setAttend_dmsj(paramsAttend.getAttend_dmsj());
//			attend = adminManager.queryAttend(attend);
//			if (attend!=null) {
//				jsonData.setErrorReason("当前点名时间已经点过名了，请换个时间");
//				return jsonData;
//			}
//
//			 //添加考勤
//			adminManager.addAttendBatch(paramsAttend);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			jsonData.setErrorReason("点名出现异常");
//		}
//
//		return jsonData;
//	}
//
//	/**
//	 * @Title: listAttendsTj
//	 * @Description: 查询点名记录--任课教师
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_listAttendsTj.action")
//	public String listAttendsTj(Attend paramsAttend, PaperUtil paperUtil,
//                                ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			if (paramsAttend==null) {
//				paramsAttend = new Attend();
//			}
//			if (paperUtil==null) {
//				paperUtil = new PaperUtil();
//			}
//			//身份限制
//			User admin = (User)httpSession.getAttribute("admin");
//			if (admin.getUser_type()==2) {
//				paramsAttend.setTeacher_id(admin.getUser_id());
//			}
//			//查询考勤列表
//			if (paramsAttend.getAttend_status()==0) {
//				//paramsAttend.setAttend_status(1);
//			}
//			//查询考勤列表
//			if (paramsAttend.getDept_id()==0 || paramsAttend.getCourse_id()==0) {
//				model.addAttribute("attends", new ArrayList<>());
//				paperUtil.setTotalCount(0);
//			}else {
//				List<Attend> attends = adminManager.listAttendsByClazzCourse(paramsAttend);
//				model.addAttribute("attends", attends);
//			}
//			model.addAttribute("paramsAttend", paramsAttend);
//
//			//查询班级
//			Dept dept = new Dept();
//			dept.setStart(-1);
//			List<Dept> depts = adminManager.listDepts(dept, null);
//			if (depts==null) {
//				depts = new ArrayList<Dept>();
//			}
//			model.addAttribute("depts", depts);
//
//			//查询课程
//			Course course = new Course();
//			course.setStart(-1);
//			List<Course> courses = adminManager.listCourses(course, null);
//			if (courses==null) {
//				courses = new ArrayList<Course>();
//			}
//			model.addAttribute("courses", courses);
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("查询考勤异常", "main.jsp", model);
//			return "infoTip";
//		}
//
//		return "attendTjShow";
//	}
//
//	/**
//	 * @Title: editAttendTj
//	 * @Description: 编辑考勤
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_editAttendTj.action",method=RequestMethod.GET)
//	public String editAttendTj(Plan paramsPlan, Attend paramsAttend,
//                               ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			Plan plan = adminManager.queryPlan(paramsPlan);
//			model.addAttribute("plan", plan);
//
//			 //得到考勤
//			paramsAttend.setStart(-1);
//			List<Attend> attends = adminManager.listAttends(paramsAttend,null);
//			model.addAttribute("attends", attends);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorTip("查询考勤异常", "main.jsp", model);
//			return "infoTip";
//		}
//
//		return "attendTjEdit";
//	}
//
//	/**
//	 * @Title: saveAttendTj
//	 * @Description: 保存编辑考勤
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_saveAttendTj.action",method=RequestMethod.POST)
//	@ResponseBody
//	public JSONData saveAttendTj(Attend paramsAttend,
//                                 ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		JSONData jsonData = new JSONData();
//		try {
//			 //添加考勤
//			adminManager.addAttendBatch(paramsAttend);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			jsonData.setErrorReason("保存点名出现异常");
//		}
//
//		return jsonData;
//	}
//
//	/**
//	 * @Title: delAttendsTj
//	 * @Description: 删除点名信息--注，此方法不对外提供
//	 * @return String
//	 */
//	@RequestMapping(value="admin/Admin_delAttendsTj.action")
//	public String delAttendsTj(Attend paramsAttend,
//                               ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
//		try {
//			 //删除考勤
//			adminManager.delAttendsByPlanIds(paramsAttend);
//
//			setSuccessTip("删除点名成功", "Admin_listAttendsTj.action", model);
//		} catch (Exception e) {
//			setErrorTip("删除点名异常", "Admin_listAttendsTj.action", model);
//		}
//
//		return "infoTip";
//	}
//
//	/**
//	 * @Title: validateAdmin
//	 * @Description: admin登录验证
//	 * @return boolean
//	 */
//	private boolean validateAdmin(HttpSession httpSession){
//		User admin = (User)httpSession.getAttribute("admin");
//		if (admin!=null) {
//			return true;
//		}else {
//			return false;
//		}
//	}
//
//	private void setErrorTip(String tip,String url,ModelMap model){
//		model.addAttribute("tipType", "error");
//		model.addAttribute("tip", tip);
//		model.addAttribute("url1", url);
//		model.addAttribute("value1", "确 定");
//	}
//
//	private void setSuccessTip(String tip,String url,ModelMap model){
//		model.addAttribute("tipType", "success");
//		model.addAttribute("tip", tip);
//		model.addAttribute("url1", url);
//		model.addAttribute("value1", "确 定");
//	}
//
//	public String getTip() {
//		return tip;
//	}
//
//	public void setTip(String tip) {
//		this.tip = tip;
//	}
//
//}
