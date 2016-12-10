package win.yulongsun.talents.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUserPlanR<M extends BaseUserPlanR<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer Id) {
		set("_id", Id);
	}

	public java.lang.Integer getId() {
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

	public void setApplyStatus(java.lang.Integer applyStatus) {
		set("apply_status", applyStatus);
	}

	public java.lang.Integer getApplyStatus() {
		return get("apply_status");
	}

	public void setCreateAt(java.util.Date createAt) {
		set("create_at", createAt);
	}

	public java.util.Date getCreateAt() {
		return get("create_at");
	}

	public void setUpdateAt(java.util.Date updateAt) {
		set("update_at", updateAt);
	}

	public java.util.Date getUpdateAt() {
		return get("update_at");
	}

}
