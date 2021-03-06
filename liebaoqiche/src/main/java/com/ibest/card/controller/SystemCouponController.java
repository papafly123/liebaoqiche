package com.ibest.card.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ibest.accesssystem.entity.AccessSystem;
import com.ibest.accesssystem.service.AccessSystemService;
import com.ibest.utils.ConstantUtils;
import org.apache.commons.lang3.RandomUtils;
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
import com.ibest.card.dto.input.SystemCouponInputDTO;
import com.ibest.card.entity.SystemCoupon;
import com.ibest.card.service.SystemCouponService;

@Controller
@RequestMapping(value = "${adminPath}/card/systemCoupon")
public class SystemCouponController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(SystemCouponController.class);

	@Autowired
	private SystemCouponService systemCouponService;

	@Autowired
	private AccessSystemService accessSystemService;

	/**
	 * 进入到列表页
	 */
	@RequestMapping(value = "/")
	public String index() {
		return "module/card/systemCoupon/systemCouponList";
	}

	/**
	 * 进入到表单页-创建
	 */
	@RequestMapping(value = "/add")
	public String add() {

		return "module/card/systemCoupon/systemCouponForm";
	}

	/**
	 * 进入到表单页，编辑
	 */
	@RequestMapping(value = "/edit")
	public String edit(@RequestParam String id, Model model) {
		try {
			if (StringUtils.isNotEmpty(id)) {
				SystemCoupon systemCoupon = systemCouponService.findById(id);
				if (systemCoupon != null) {
					model.addAttribute("systemCoupon", systemCoupon);
				} else {
					setRtnCodeAndMsg(EnumsRtnMapResult.FAILURE.getCode(), "您查看的信息不存在！", model);
				}
			} else {
				setRtnCodeAndMsg(EnumsRtnMapResult.FAILURE.getCode(), "请选择需要编辑的信息！", model);
			}
		} catch (Exception e) {
			setRtnCodeAndMsg(EnumsRtnMapResult.EXCEPTION.getCode(), EnumsRtnMapResult.EXCEPTION.getMsg(), model);
		}
		return "module/card/systemCoupon/systemCouponForm";
	}

	/**
	 * 进入到详情页
	 */
	@RequestMapping(value = "/view")
	public String view(@RequestParam String id, Model model) {
		try {
			if (StringUtils.isNotEmpty(id)) {
				SystemCoupon systemCoupon = systemCouponService.findById(id);
				if (systemCoupon != null) {
					model.addAttribute("systemCoupon", systemCoupon);
				} else {
					setRtnCodeAndMsg(EnumsRtnMapResult.FAILURE.getCode(), "您查看的信息不存在！", model);
				}
			} else {
				setRtnCodeAndMsg(EnumsRtnMapResult.FAILURE.getCode(), "请选择需要查看的信息！", model);
			}
		} catch (Exception e) {
			setRtnCodeAndMsg(EnumsRtnMapResult.EXCEPTION.getCode(), EnumsRtnMapResult.EXCEPTION.getMsg(), model);
		}
		return "module/card/systemCoupon/systemCouponDetail";
	}

	/**
	 * 异步分页查询
	 */
	@ResponseBody
	@RequiresPermissions("systemCoupon:query")
	@RequestMapping(value = "/list")
	public PageList<SystemCoupon> list(SystemCouponInputDTO systemCoupon, HttpServletRequest request) {

		PageList<SystemCoupon> pageList = new PageList<SystemCoupon>();

		try {
			//设置分页参数
			super.setPage(request, pageList);

			pageList = systemCouponService.findByPage(pageList, systemCoupon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageList;
	}

	/**
	 * 异步表单提交
	 */
	@ResponseBody
	@RequiresPermissions("systemCoupon:create")
	@RequestMapping(value = "create")
	public Map<String, Object> insert(SystemCoupon systemCoupon) {

		Map<String, Object> rtnMap = new HashMap<String, Object>();
		setRtnCodeAndMsgBySuccess(rtnMap, "保存成功");

		try {
			systemCoupon.setId(com.ibest.utils.RandomUtils.RandomUUID());
			systemCoupon.setIsDelete(ConstantUtils.UPDATE_SET_ZERO);
			getSystem(systemCoupon);
			int result = systemCouponService.insert(systemCoupon);
			if (result == 0) {
				setRtnCodeAndMsgByFailure(rtnMap, "保存失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			setRtnCodeAndMsgByException(rtnMap, null);
		}
		return rtnMap;
	}

	private void getSystem(SystemCoupon systemCoupon)
			throws Exception {
		AccessSystem accessSystem = accessSystemService.findById(systemCoupon.getAccessSystemId());
//		systemCoupon.setAccessSystemName(accessSystem.getAccessSystemName());
	}

	@ResponseBody
	@RequiresPermissions("systemCoupon:update")
	@RequestMapping(value = "update")
	public Map<String, Object> update(SystemCoupon systemCoupon) {

		Map<String, Object> rtnMap = new HashMap<String, Object>();
		setRtnCodeAndMsgBySuccess(rtnMap, "保存成功");

		try {
			getSystem(systemCoupon);
			int result = systemCouponService.update(systemCoupon);
			if (result == 0) {
				setRtnCodeAndMsgByFailure(rtnMap, "保存失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			setRtnCodeAndMsgByException(rtnMap, null);
		}
		return rtnMap;
	}

	@ResponseBody
	@RequiresPermissions("systemCoupon:delete")
	@RequestMapping(value = "delete")
	public Map<String, Object> delete(@RequestParam(required = true) String ids) {

		Map<String, Object> rtnMap = new HashMap<String, Object>();
		setRtnCodeAndMsgBySuccess(rtnMap, "删除成功");

		try {
			int result = systemCouponService.deleteByIds(ids);
			if (result == 0) {
				setRtnCodeAndMsgByFailure(rtnMap, "删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			setRtnCodeAndMsgByException(rtnMap, null);
		}
		return rtnMap;
	}
}
