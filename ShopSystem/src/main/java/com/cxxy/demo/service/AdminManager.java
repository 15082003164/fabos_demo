//package com.cxxy.demo.service;
//
//
//import com.cxxy.demo.dao.*;
//import com.cxxy.demo.bean.*;
//import com.cxxy.demo.util.Md5;
//import com.cxxy.demo.util.StringUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AdminManager {
//	@Autowired
//	IDeptDao deptDao;
//	@Autowired
//	ICourseDao courseDao;
//	@Autowired
//	IUserDao userDao;
//	@Autowired
//	IPlanDao planDao;
//	@Autowired
//	IConfigDao configDao;
//	@Autowired
//	IAttendDao attendDao;
//
//	/**
//	 * @Title: listConfigs
//	 * @Description: 考核参数查询
//	 * @param config
//	 * @return List<Config>
//	 */
//	public List<Config> listConfigs(Config config, int[] sum) {
//		if (sum != null) {
//			sum[0] = configDao.listConfigsCount(config);
//		}
//		List<Config> configs = configDao.listConfigs(config);
//
//		return configs;
//	}
//
//	/**
//	 * @Title: queryConfig
//	 * @Description: 考核参数查询
//	 * @param config
//	 * @return Config
//	 */
//	public Config queryConfig(Config config) {
//		Config _config = configDao.getConfig(config);
//		return _config;
//	}
//
//	/**
//	 * @Title: updateConfig
//	 * @Description: 更新考核参数
//	 * @param config
//	 * @return void
//	 */
//	public void updateConfig(Config config) {
//		configDao.updateConfig(config);
//	}
//
//	/**
//	 * @Title: listUsers
//	 * @Description: 用户查询
//	 * @param user
//	 * @return List<User>
//	 */
//	public List<User> listUsers(User user, int[] sum) {
//		if (sum != null) {
//			sum[0] = userDao.listUsersCount(user);
//		}
//		List<User> users = userDao.listUsers(user);
//
//		return users;
//	}
//
//	/**
//	 * @Title: queryUser
//	 * @Description: 用户查询
//	 * @param user
//	 * @return User
//	 */
//	public User queryUser(User user) {
//		User _user = userDao.getUser(user);
//		return _user;
//	}
//
//	/**
//	 * @Title: addUser
//	 * @Description: 添加用户
//	 * @param user
//	 * @return void
//	 */
//	public void addUser(User user) {
//		if (!StringUtil.isEmptyString(user.getUser_pass())) {
//			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
//		}
//		userDao.addUser(user);
//	}
//
//	/**
//	 * @Title: updateUser
//	 * @Description: 更新用户信息
//	 * @param user
//	 * @return void
//	 */
//	public void updateUser(User user) {
//		if (!StringUtil.isEmptyString(user.getUser_pass())) {
//			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
//		}
//		userDao.updateUser(user);
//	}
//
//
//	/**
//	 * @Title: delUsers
//	 * @Description: 删除用户信息
//	 * @param user
//	 * @return void
//	 */
//	public void delUsers(User user) {
//		userDao.delUsers(user.getIds().split(","));
//	}
//
//	/**
//	 * @Title: listDepts
//	 * @Description: 班级查询
//	 * @param dept
//	 * @return List<Dept>
//	 */
//	public List<Dept> listDepts(Dept dept, int[] sum) {
//		if (sum != null) {
//			sum[0] = deptDao.listDeptsCount(dept);
//		}
//		List<Dept> depts = deptDao.listDepts(dept);
//
//		return depts;
//	}
//
//	/**
//	 * @Title: queryDept
//	 * @Description: 班级查询
//	 * @param dept
//	 * @return Dept
//	 */
//	public Dept queryDept(Dept dept) {
//		Dept _dept = deptDao.getDept(dept);
//		return _dept;
//	}
//
//	/**
//	 * @Title: addDept
//	 * @Description: 添加班级
//	 * @param dept
//	 * @return void
//	 */
//	public void addDept(Dept dept) {
//		deptDao.addDept(dept);
//	}
//
//	/**
//	 * @Title: updateDept
//	 * @Description: 更新班级信息
//	 * @param dept
//	 * @return void
//	 */
//	public void updateDept(Dept dept) {
//		deptDao.updateDept(dept);
//	}
//
//	/**
//	 * @Title: delDept
//	 * @Description: 删除班级信息
//	 * @param dept
//	 * @return void
//	 */
//	public void delDepts(Dept dept) {
//		deptDao.delDepts(dept.getIds().split(","));
//	}
//
//	/**
//	 * @Title: listCourses
//	 * @Description: 课程查询
//	 * @param course
//	 * @return List<Course>
//	 */
//	public List<Course> listCourses(Course course, int[] sum) {
//		if (sum != null) {
//			sum[0] = courseDao.listCoursesCount(course);
//		}
//		List<Course> courses = courseDao.listCourses(course);
//
//		return courses;
//	}
//
//	/**
//	 * @Title: queryCourse
//	 * @Description: 课程查询
//	 * @param course
//	 * @return Course
//	 */
//	public Course queryCourse(Course course) {
//		Course _course = courseDao.getCourse(course);
//		return _course;
//	}
//
//	/**
//	 * @Title: addCourse
//	 * @Description: 添加课程
//	 * @param course
//	 * @return void
//	 */
//	public void addCourse(Course course) {
//		courseDao.addCourse(course);
//	}
//
//	/**
//	 * @Title: updateCourse
//	 * @Description: 更新课程信息
//	 * @param course
//	 * @return void
//	 */
//	public void updateCourse(Course course) {
//		courseDao.updateCourse(course);
//	}
//
//	/**
//	 * @Title: delCourse
//	 * @Description: 删除课程信息
//	 * @param course
//	 * @return void
//	 */
//	public void delCourses(Course course) {
//		courseDao.delCourses(course.getIds().split(","));
//	}
//
//	/**
//	 * @Title: listPlans
//	 * @Description: 课表查询
//	 * @param plan
//	 * @return List<Plan>
//	 */
//	public List<Plan> listPlans(Plan plan, int[] sum) {
//		if (sum != null) {
//			sum[0] = planDao.listPlansCount(plan);
//		}
//		List<Plan> plans = planDao.listPlans(plan);
//		return plans;
//	}
//
//	/**
//	 * @Title: listPlansByClazz
//	 * @Description: 课表周视图查询
//	 * @param plan
//	 * @return List<Plan>
//	 */
//	public List<Plan> listPlansByClazz(Plan plan) {
//		List<Plan> plans = planDao.listPlansByClazz(plan);
//		return plans;
//	}
//
//	/**
//	 * @Title: queryPlan
//	 * @Description: 课表查询
//	 * @param plan
//	 * @return Plan
//	 */
//	public Plan queryPlan(Plan plan) {
//		Plan _plan = planDao.getPlan(plan);
//		return _plan;
//	}
//
//	/**
//	 * @Title: addPlan
//	 * @Description: 添加课表
//	 * @param plan
//	 * @return void
//	 */
//	public void addPlan(Plan plan) {
//		planDao.addPlan(plan);
//	}
//
//	/**
//	 * @Title: updatePlan
//	 * @Description: 更新课表信息
//	 * @param plan
//	 * @return void
//	 */
//	public void updatePlan(Plan plan) {
//		planDao.updatePlan(plan);
//	}
//
//	/**
//	 * @Title: delPlan
//	 * @Description: 删除课表信息
//	 * @param plan
//	 * @return void
//	 */
//	public void delPlans(Plan plan) {
//		planDao.delPlans(plan.getIds().split(","));
//	}
//
//	/**
//	 * @Title: listAttends
//	 * @Description: 出勤查询
//	 * @param attend
//	 * @return List<Attend>
//	 */
//	public List<Attend> listAttends(Attend attend, int[] sum) {
//		if (sum != null) {
//			sum[0] = attendDao.listAttendsCount(attend);
//		}
//		List<Attend> attends = attendDao.listAttends(attend);
//		return attends;
//	}
//
//	/**
//	 * @Title: listAttendsByClazzCourse
//	 * @Description: 班级考勤汇总
//	 * @param attend
//	 * @return List<Attend>
//	 */
//	public List<Attend> listAttendsByClazzCourse(Attend attend) {
//		List<Attend> attends = attendDao.listAttendsByClazzCourse(attend);
//		return attends;
//	}
//
//	/**
//	 * @Title: listAttendsByStuCourse
//	 * @Description: 考勤成绩统计
//	 * @param attend
//	 * @return List<Attend>
//	 */
//	public List<Attend> listAttendsByStuCourse(Attend attend, int[] sum) {
//		if (sum != null) {
//			sum[0] = attendDao.listAttendsByStuCourseCount(attend);
//		}
//		List<Attend> attends = attendDao.listAttendsByStuCourse(attend);
//		if (attends!=null) {
//			for (Attend attend2 : attends) {
//				attend2.setConfig(configDao.getConfig(new Config()));
//			}
//		}
//		return attends;
//	}
//
//	/**
//	 * @Title: queryAttend
//	 * @Description: 出勤查询
//	 * @param attend
//	 * @return Attend
//	 */
//	public Attend queryAttend(Attend attend) {
//		Attend _attend = attendDao.getAttend(attend);
//		return _attend;
//	}
//
//	/**
//	 * @Title: addAttend
//	 * @Description: 添加出勤
//	 * @param attend
//	 * @return void
//	 */
//	public void addAttend(Attend attend) {
//		attendDao.addAttend(attend);
//	}
//
//	/**
//	 * @Title: addAttendBatch
//	 * @Description: 添加出勤
//	 * @param attend
//	 * @return void
//	 */
//	public void addAttendBatch(Attend attend) {
//		Attend attend2 = new Attend();
//		attend2.setPlan_id(attend.getPlan_id());
//		attend2.setAttend_dmrq(attend.getAttend_dmrq());
//		attend2.setAttend_dmsj(attend.getAttend_dmsj());
//
//		//删除旧记录
//		attendDao.delAttendsByPlan(attend2);
//		attend2.setAttend_status(attend.getAttend_status());
//
//		String[] user_ids = attend.getUser_ids().split(",");
//		String[] attend_flags = attend.getAttend_flags().split(",");
//		Config config = configDao.getConfig(new Config());
//		for (int i = 0; i < user_ids.length; i++) {
//			int user_id = Integer.parseInt(user_ids[i]);
//			int attend_flag = Integer.parseInt(attend_flags[i]);
//			attend2.setUser_id(user_id);
//			attend2.setAttend_flag(attend_flag);
//			attend2.setAttend_score(config.getAttend_score(attend_flag));
//			attendDao.addAttend(attend2);
//		}
//
//	}
//
//
//	/**
//	 * @Title: updateAttend
//	 * @Description: 更新出勤信息
//	 * @param attend
//	 * @return void
//	 */
//	public void updateAttend(Attend attend) {
//		attendDao.updateAttend(attend);
//	}
//
//	/**
//	 * @Title: delAttend
//	 * @Description: 删除出勤信息
//	 * @param attend
//	 * @return void
//	 */
//	public void delAttends(Attend attend) {
//		attendDao.delAttends(attend.getIds().split(","));
//	}
//
//	/**
//	 * @Title: delAttend
//	 * @Description: 删除点名信息
//	 * @param attend
//	 * @return void
//	 */
//	public void delAttendsByPlanIds(Attend attend) {
//		attendDao.delAttendsByPlanIds(attend.getPlan_ids().split(","));
//	}
//}
