package win.yulongsun.talents.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUserPlanClazzR<M extends BaseUserPlanClazzR<M>> extends Model<M> implements IBean {

	public void setId(Integer Id) {
		set("_id", Id);
	}

	public java.lang.Long getId() {
		return get("_id");
	}

	public void setUserId(java.lang.Integer userId) {
		set("user_id", userId);
	}

	public java.lang.Integer getUserId() {
		return get("user_id");
	}

	public void setPlanId(java.lang.Integer planId) {
		set("plan_id", planId);
	}

	public java.lang.Integer getPlanId() {
		return get("plan_id");
	}

	public void setClazzId(java.lang.Integer clazzId) {
		set("clazz_id", clazzId);
	}

	public java.lang.Integer getClazzId() {
		return get("clazz_id");
	}

	public void setClazzGrade(java.lang.Integer clazzGrade) {
		set("clazz_grade", clazzGrade);
	}

	public java.lang.Integer getClazzGrade() {
		return get("clazz_grade");
	}

	public void setClazzStatus(java.lang.String clazzStatus) {
		set("clazz_status", clazzStatus);
	}

	public java.lang.String getClazzStatus() {
		return get("clazz_status");
	}

}
