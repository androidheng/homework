package com.homework.controller;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.homework.pojo.TbAdmin;
import com.homework.pojo.TbTeacher;
import com.homework.pojo.TbUser;
import com.homework.service.AdminService;
import com.homework.service.TeacherService;
import com.homework.service.UserService;
import com.homework.vo.LoginVo;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
public class CommonController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private AdminService adminService;
	
	
	/**
	 * 统一登录操作
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/login",produces = "application/json;charset=UTF-8")
	public Result login(@RequestBody LoginVo loginVo,HttpSession session){
		if(loginVo.getUsertype().equals("0")) {
			TbUser user=new TbUser();
			user.setUsername(loginVo.getUsername());
			user.setPassword(loginVo.getPassword());
			TbUser loginUser=userService.login(user);
			if(loginUser!=null) {
				session.setAttribute("student", loginUser);	
				return new Result(true, "登录成功");
			}else {
				return new Result(false, "登录失败");	
			}
		}else if(loginVo.getUsertype().equals("1")) {
			TbTeacher tbTeacher=new TbTeacher();
			tbTeacher.setUsername(loginVo.getUsername());
			tbTeacher.setPassword(loginVo.getPassword());
			TbTeacher loginTeacher = teacherService.login(tbTeacher);
			if(loginTeacher!=null) {
				session.setAttribute("teacher", loginTeacher);	
				return new Result(true, "登录成功");
			}else {
				return new Result(false, "登录失败");	
			}
			
		}else if(loginVo.getUsertype().equals("2")) {
			TbAdmin tbAdmin=new TbAdmin();
			tbAdmin.setUsername(loginVo.getUsername());
			tbAdmin.setPassword(loginVo.getPassword());
			TbAdmin loginAdmin = adminService.login(tbAdmin);
			if(loginAdmin!=null) {
				session.setAttribute("admin", loginAdmin);	
				return new Result(true, "登录成功");
			}else {
				return new Result(false, "登录失败");	
			}
			
		}
		return new Result(false, "登录失败");	
	}
	@RequestMapping(value="/upload",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result upload(MultipartFile file,HttpServletRequest request,HttpServletResponse response,HttpSession session){
	    if (file!=null) {// 判断上传的文件是否为空
            String path=null;// 文件路径
            String type=null;// 文件类型
            String fileName=file.getOriginalFilename();// 文件原名称
            System.out.println("上传的文件原名称:"+fileName);
            // 判断文件类型
            type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
            if (type!=null) {// 判断文件类型是否为空
                if ("DOC".equals(type.toUpperCase())||"DOCX".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath=request.getSession().getServletContext().getRealPath("file/");
                    // 自定义的文件名称
                    String trueFileName=String.valueOf(System.currentTimeMillis())+fileName.substring(fileName.indexOf("."));
                    // 设置存放图片文件的路径
                    path=realPath+File.separator+trueFileName;
                    System.out.println("存放图片文件的路径:"+path);
                    // 转存文件到指定的路径
                    try {
						file.transferTo(new File(path));
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    System.out.println("文件成功上传到指定目录下");
                    return new Result(true, trueFileName);
                }else {
                    System.out.println("不是我们想要的文件类型,请按要求重新上传");
                    return new Result(false, "文件类型不正确");
                }
            }else {
                System.out.println("文件类型为空");
                return new Result(false, "文件类型为空");
            }
        }else {
            System.out.println("没有找到相对应的文件");
            return null;
        }
	}
	
	
	
}
