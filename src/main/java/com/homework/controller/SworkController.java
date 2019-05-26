package com.homework.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homework.pojo.TbSelect;
import com.homework.pojo.TbSwork;
import com.homework.pojo.TbTeacher;
import com.homework.pojo.TbTwork;
import com.homework.pojo.TbUser;
import com.homework.service.SelectService;
import com.homework.service.SworkService;
import com.homework.service.TworkService;
import com.homework.util.DateUtils;
import com.homework.vo.WorkVo;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/swork")
public class SworkController {

	@Autowired
	private SworkService sworkService;
	@Autowired
	private TworkService tworkService;
	@Autowired
	private SelectService selectService;
	@RequestMapping(value="/searchwork",produces = "application/json;charset=UTF-8")
	public PageResult searchwork(HttpSession session, int page, int limit  ){
//		TbUser user=(TbUser) session.getAttribute("student");
		TbUser user=new TbUser();
		user.setId(1);
		List<TbSelect> list = selectService.findAll(user.getId());
		
		PageResult find = tworkService.find(list, page, limit);
		PageResult result=new PageResult(find.getCode(),find.getMsg(),find.getCount());
		List<TbTwork> works=find.getData();
		List<WorkVo> vos=new ArrayList<>();
		for (TbTwork tbTwork : works) {
			WorkVo vo=new WorkVo();
			BeanUtils.copyProperties(tbTwork, vo);
			  TbSwork swork = sworkService.findTtid(user.getId(),tbTwork.getId());
			  int end=DateUtils.compare_date(tbTwork.getEndtime());
			  if(end<0) {
				  vo.setOver("Y");
			  }
			if(swork==null) {
				vo.setStatus("未提交");
			}else {
				vo.setStatus("已提交");
			}
			vos.add(vo);
		}
		result.setData(vos);
		return result;		
	}
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbSwork> findAll(){			
		return sworkService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return sworkService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param swork
	 * @return
	 */
	@RequestMapping(value="/add",produces = "application/json;charset=UTF-8")
	public Result add(@RequestBody TbSwork swork,HttpSession session){
//		TbUser user=(TbUser) session.getAttribute("student");
		TbUser user=new TbUser();
		user.setId(1);
		TbSwork tbSwork = sworkService.findTtid(user.getId(),swork.getTtid());//判断学生是否已经提交了作业
		swork.setUid(user.getId());
		swork.setCreatetime(DateUtils.getCurrent());
		swork.setSstatus(0);
		if(tbSwork==null) {//添加操作
			try {
				sworkService.add(swork);
				return new Result(true, "提交成功");
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false, "提交失败");
			}
		}else {//修改操作
			try {
				swork.setId(tbSwork.getId());
				sworkService.update(swork);
				return new Result(true, "提交成功");
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false, "提交失败");
			}	
		}
		
	}
	/**
	 * 设置学生批改作业
	 * @param swork
	 * @param session
	 * @return
	 */
	@RequestMapping("/exchange")
	public Result exchange(@RequestBody TbTwork tbTwork,HttpSession session){
		try {
			List<TbSwork> list=sworkService.findExchange(tbTwork.getId());
			if(list.size()==0||list.size()==1) {
				return new Result(false, "作业分配失败!");
			}
			for(int i=0;i<list.size();i++) {
				if(i==list.size()-1) {//将最后一个人的作业分给第一个学生
					list.get(i).setUid2(list.get(0).getUid());
				}else {
					list.get(i).setUid2(list.get(i+1).getUid());//其他人的作业依次分给后面一个人
				}
				
			}
			for (TbSwork tbSwork : list) {
				sworkService.update(tbSwork);
			}
			
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	/**
	 * 修改
	 * @param swork
	 * @return
	 */
	@RequestMapping("/score")
	public Result update(@RequestBody TbSwork swork,HttpSession session){
		try {
			TbTeacher tbTeacher=(TbTeacher) session.getAttribute("teacher");
			tbTeacher=new TbTeacher();
			tbTeacher.setId(1);
			swork.setTid(1);
			sworkService.update(swork);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	@RequestMapping("/stuscore")
	public Result stuscore(@RequestBody TbSwork swork,HttpSession session){
		try {
			TbUser user=(TbUser) session.getAttribute("student");
			user=new TbUser();
			user.setId(1);
			swork.setUid2(user.getId());;
			sworkService.update(swork);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	

	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbSwork findOne(Integer id){
		return sworkService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Integer [] ids){
		try {
			sworkService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbSwork swork, int page, int rows  ){
		return sworkService.findPage(swork, page, rows);		
	}
	   @RequestMapping("/fileDownLoad")
	    public ResponseEntity<byte[]> fileDownLoad(Integer id,HttpServletRequest request) throws Exception{
	      TbSwork swork = sworkService.findOne(id);
	     // ServletContext servletContext = request.getServletContext();
	      String fileName=swork.getUrl();
	      /* String realPath = servletContext.getRealPath("/file/"+fileName);*///得到文件所在位置
	      String realPath=request.getSession().getServletContext().getRealPath("file/"+fileName);
	        InputStream in=new FileInputStream(new File(realPath));//将该文件加入到输入流之中
	         byte[] body=null;
	         body=new byte[in.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
	         in.read(body);//读入到输入流里面
	        
	        fileName=new String(fileName.getBytes("gbk"),"iso8859-1");//防止中文乱码
	        HttpHeaders headers=new HttpHeaders();//设置响应头
	        headers.add("Content-Disposition", "attachment;filename="+fileName);
	        HttpStatus statusCode = HttpStatus.OK;//设置响应吗
	        ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
	        swork.setSstatus(1);
	        sworkService.update(swork);
	        
	        return response;
	    } 
	    /**
	     * 学生作业查找
	     * @param session
	     * @param page
	     * @param limit
	     * @return
	     */
	    @RequestMapping("/searchswork")
		public PageResult searchswork( HttpSession session,int page, int limit  ){
			TbUser user=(TbUser) session.getAttribute("student");
			return sworkService.findOtherWork(user.getId(), page, limit);
		}
	    @RequestMapping("/myhomework")
	    public PageResult myhomework( HttpSession session,int page, int limit  ){
	    	TbUser user=(TbUser) session.getAttribute("student");
	    	return sworkService.findMyWork(user.getId(), page, limit);
	    }
		
}
