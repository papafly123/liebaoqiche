package com.ibest.card.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ibest.accesssystem.entity.AccessSystem;
import com.ibest.accesssystem.service.AccessSystemService;
import com.ibest.card.entity.CouponBuild;
import com.ibest.card.entity.CouponGet;
import com.ibest.card.entity.CouponUse;
import com.ibest.card.service.CouponBuildService;
import com.ibest.card.service.CouponGetService;
import com.ibest.card.service.CouponUseService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibest.framework.common.enums.EnumsRtnMapResult;
import com.ibest.framework.common.persistence.BaseController;
import com.ibest.framework.common.utils.PageList;
import com.ibest.card.dto.input.UserCouponInputDTO;
import com.ibest.card.entity.UserCoupon;
import com.ibest.card.service.UserCouponService;

@Controller
@RequestMapping(value="${adminPath}/card/userCoupon")
public class UserCouponController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserCouponController.class);
	
	@Autowired
	private UserCouponService userCouponService;

	@Autowired
	private AccessSystemService accessSystemService;

	@Autowired
	private CouponBuildService couponBuildService;

	@Autowired
	private CouponGetService couponGetService;

	@Autowired
	private CouponUseService couponUseService;

	/**
	* 进入到列表页
	*/
	@RequestMapping(value="/")
	public String index(){
		return "module/card/userCoupon/userCouponList";
	}
	
	/**
	* 进入到表单页-创建
	*/
	@RequestMapping(value="/add")
	public String add(){
		
		return "module/card/userCoupon/userCouponForm";
	}
	
	/**
	* 进入到表单页，编辑
	*/
	@RequestMapping(value="/edit")
	public String edit(@RequestParam String id, Model model){
		try {
			if(StringUtils.isNotEmpty(id)){
				UserCoupon userCoupon = userCouponService.findById(id);
				if(userCoupon != null){
					model.addAttribute("userCoupon", userCoupon);
				}else{
					setRtnCodeAndMsg(EnumsRtnMapResult.FAILURE.getCode(), "您查看的信息不存在！", model);
				}
			}else{
				setRtnCodeAndMsg(EnumsRtnMapResult.FAILURE.getCode(), "请选择需要编辑的信息！", model);
			}
		} catch (Exception e) {
			setRtnCodeAndMsg(EnumsRtnMapResult.EXCEPTION.getCode(), EnumsRtnMapResult.EXCEPTION.getMsg(), model);
		}
		return "module/card/userCoupon/userCouponForm";
	}
	
	/**
	* 进入到详情页
	*/
	@RequestMapping(value="/view")
	public String view(@RequestParam String id, Model model){
		try {
			if(StringUtils.isNotEmpty(id)){
				UserCoupon userCoupon = userCouponService.findById(id);
				if(userCoupon != null){
					model.addAttribute("userCoupon", userCoupon);
				}else{
					setRtnCodeAndMsg(EnumsRtnMapResult.FAILURE.getCode(), "您查看的信息不存在！", model);
				}
			}else{
				setRtnCodeAndMsg(EnumsRtnMapResult.FAILURE.getCode(), "请选择需要查看的信息！", model);
			}
		} catch (Exception e) {
			setRtnCodeAndMsg(EnumsRtnMapResult.EXCEPTION.getCode(), EnumsRtnMapResult.EXCEPTION.getMsg(), model);
		}
		return "module/card/userCoupon/userCouponDetail";
	}

	/**
	 * 获取系统名称
	 * @param userCoupon
	 * @throws Exception
	 */
	private void getSystemAndBuildAndGetAndUse(UserCoupon userCoupon)
			throws Exception {
		AccessSystem accessSystem = accessSystemService.findById(userCoupon.getSignId());
		CouponBuild couponBuild = couponBuildService.findById(userCoupon.getCouponBuildId());
		CouponGet couponGet = couponGetService.findById(userCoupon.getCouponGetId());
		CouponUse couponUse = couponUseService.findById(userCoupon.getCouponUseId());
		if (null != accessSystem) {
//			userCoupon.setSystemSignName(accessSystem.getAccessSystemName());
		}
		if (null != couponBuild) {
			userCoupon.setCouponBuildName(couponBuild.getBuildType());
		}
		if (null != couponGet) {
			userCoupon.setCouponGetName(couponGet.getGetType());
		}
		if (null != couponUse) {
			userCoupon.setCouponUseName(couponUse.getCouponUseName());
		}
	}

	/**
	* 异步分页查询
	*/
	@ResponseBody
	@RequiresPermissions("userCoupon:query")
	@RequestMapping(value="/list")
	public PageList<UserCoupon> list(UserCouponInputDTO userCoupon, HttpServletRequest request){
		
		PageList<UserCoupon> pageList = new PageList<UserCoupon>();
		
		try {
			//设置分页参数
			super.setPage(request, pageList);
		
			pageList = userCouponService.findByPage(pageList, userCoupon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageList;
	}
	
	/**
	* 异步表单提交
	*/
	@ResponseBody
	@RequiresPermissions("userCoupon:create")
	@RequestMapping(value="create")
	public Map<String, Object> insert(UserCoupon userCoupon){

		Map<String, Object> rtnMap = new HashMap<String, Object>();
		setRtnCodeAndMsgBySuccess(rtnMap, "保存成功");
		
		try {
			int result = userCouponService.insert(userCoupon);
			if(result == 0){
				setRtnCodeAndMsgByFailure(rtnMap, "保存失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			setRtnCodeAndMsgByException(rtnMap, null);
		}
		return rtnMap;
	}
	
	@ResponseBody
	@RequiresPermissions("userCoupon:update")
	@RequestMapping(value="update")
	public Map<String, Object> update(UserCoupon userCoupon){

		Map<String, Object> rtnMap = new HashMap<String, Object>();
		setRtnCodeAndMsgBySuccess(rtnMap, "保存成功");
		
		try {
			getSystemAndBuildAndGetAndUse(userCoupon);
			int result = userCouponService.update(userCoupon);
			if(result == 0){
				setRtnCodeAndMsgByFailure(rtnMap, "保存失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			setRtnCodeAndMsgByException(rtnMap, null);
		}
		return rtnMap;
	}
	
	@ResponseBody
	@RequiresPermissions("userCoupon:delete")
	@RequestMapping(value="delete")
	public Map<String, Object> delete(@RequestParam(required=true) String ids){
		
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		setRtnCodeAndMsgBySuccess(rtnMap, "删除成功");
		
		try {
			int result = userCouponService.deleteByIds(ids);
			if(result == 0){
				setRtnCodeAndMsgByFailure(rtnMap, "删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			setRtnCodeAndMsgByException(rtnMap, null);
		}
		return rtnMap;
	}
}
