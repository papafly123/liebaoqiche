package com.ibest.common;

import com.ibest.accesssystem.enums.EnumSystemActivityStatus;
import com.ibest.accesssystem.enums.EnumsIsDelete;
import com.ibest.accesssystem.enums.EnumsIsOpen;
import com.ibest.accesssystem.enums.EnumsSystemActivityIsOpen;
import com.ibest.activity.enums.EnumsActivityDetailsSatus;
import com.ibest.activity.enums.EnumsActivityStatus;
import com.ibest.card.enums.*;
import com.ibest.experience.enums.EnumsPlatformType;
import com.ibest.framework.common.persistence.BaseController;
import com.ibest.integral.enums.EnumsPointRule;
import com.ibest.integral.enums.EnumsPointRuleType;
import com.ibest.integral.enums.EnumsPointType;
import com.ibest.pay.enums.EnumsAccessSystem;
import com.ibest.pay.enums.EnumsPayType;
import com.ibest.user.enums.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${adminPath}/enumData/lieBao")
public class EnumController extends BaseController {

	@RequestMapping("userApplyStatus")
	public Object getEnumsApplyStatus() {
		return EnumsApplyStatus.values();
	}

	@RequestMapping("userLocked")
	public Object getEnumsLocked() {
		return EnumsLocked.values();
	}

	@RequestMapping("userModified")
	public Object getEnumsModified() {
		return EnumsModified.values();
	}

	@RequestMapping("userRegisterType")
	public Object getEnumsRegisterType() {
		return EnumsRegisterType.values();
	}

	@RequestMapping("userSex")
	public Object getEnumsUserSex() {
		return EnumsSex.values();
	}
	
	@RequestMapping("payType")
	public Object getEnumsPayType() {
		return EnumsPayType.values();
	}
	
	@RequestMapping("accessSystem")
	public Object getEnumsAccessSystem() {
		return EnumsAccessSystem.values();
	}
	
	@RequestMapping("pointType")
	public Object getEnumsPointType(){return EnumsPointType.values(); }

	@RequestMapping("pointRule")
	public Object getEnumsPointRule(){return EnumsPointRule.values();}

	@RequestMapping("pointRuleType")
	public Object getEnumsPointRuleType(){return EnumsPointRuleType.values();}

	@RequestMapping("EnumsPlatformType")
	public Object getEnumsPlatformType(){return EnumsPlatformType.values();}

	@RequestMapping("EnumsCouponGrantStatus")
	public Object getEnumsCouponGrantStatus(){return EnumsCouponGrantStatus.values();}

	@RequestMapping("EnumsCouponOpenStatus")
	public Object getEnumsCouponOpenStatus(){return EnumsCouponOpenStatus.values();}

	@RequestMapping("EnumsCouponEditStatus")
	public Object getEnumsCouponEditStatus(){return EnumsCouponEditStatus.values();}

	@RequestMapping("EnumsActivityStatus")
	public Object getEnumsActivityStatus(){return EnumsActivityStatus.values();}

	@RequestMapping("EnumsCouponUseingStatus")
	public Object getEnumsCouponUseingStatus(){return EnumsCouponUseingStatus.values();}

	@RequestMapping("EnumsCouponIsAppointments")
	public Object getEnumsCouponIsAppointments(){return EnumsCouponIsAppointments.values();}

	@RequestMapping("EnumsCouponIsSingleUse")
	public Object getEnumsCouponIsSingleUse(){return EnumsCouponIsSingleUse.values();}

	@RequestMapping("EnumCardGrantOrRecall")
	public Object getEnumCardGrantOrRecall(){return EnumCardGrantOrRecall.values();}

	@RequestMapping("enumsCardUseStatus")
	public Object getEnumsCardUseStatus(){return EnumsCardUseStatus.values();}

	@RequestMapping("enumSystemActivityStatus")
	public Object getEnumSystemActivityStatus(){return EnumSystemActivityStatus.values();}

	@RequestMapping("isDelete")
	public Object getEnumsIsDelete(){return EnumsIsDelete.values();}
	
	@RequestMapping("isOpen")
	public Object getEnumsIsOpen(){return EnumsIsOpen.values();}
	
	@RequestMapping("enumsSystemActivityIsOpen")
	public Object getEnumsSystemActivityIsOpen(){return EnumsSystemActivityIsOpen.values();}
	
	@RequestMapping("enumsActivityDetailsSatus")
	public Object getEnumsActivityDetailsSatus(){return EnumsActivityDetailsSatus.values();}
}
