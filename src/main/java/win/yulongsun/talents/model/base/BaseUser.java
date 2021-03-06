package win.yulongsun.talents.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUser<M extends BaseUser<M>> extends Model<M> implements IBean {

	public void setUserId(java.lang.Integer userId) {
		set("user_id", userId);
	}

	public java.lang.Integer getUserId() {
		return get("user_id");
	}

	public void setUserMobile(java.lang.String userMobile) {
		set("user_mobile", userMobile);
	}

	public java.lang.String getUserMobile() {
		return get("user_mobile");
	}

	public void setUserName(java.lang.String userName) {
		set("user_name", userName);
	}

	public java.lang.String getUserName() {
		return get("user_name");
	}

	public void setUserGender(java.lang.String userGender) {
		set("user_gender", userGender);
	}

	public java.lang.String getUserGender() {
		return get("user_gender");
	}

	public void setUserImg(java.lang.String userImg) {
		set("user_img", userImg);
	}

	public java.lang.String getUserImg() {
		return get("user_img");
	}

	public void setUserEmail(java.lang.String userEmail) {
		set("user_email", userEmail);
	}

	public java.lang.String getUserEmail() {
		return get("user_email");
	}

	public void setUserToken(java.lang.String userToken) {
		set("user_token", userToken);
	}

	public java.lang.String getUserToken() {
		return get("user_token");
	}

	public void setUserRoleId(java.lang.Integer userRoleId) {
		set("user_role_id", userRoleId);
	}

	public java.lang.Integer getUserRoleId() {
		return get("user_role_id");
	}

	public void setUserScore(java.lang.Integer userScore) {
		set("user_score", userScore);
	}

	public java.lang.Integer getUserScore() {
		return get("user_score");
	}

	public void setUserCompanyId(java.lang.Integer userCompanyId) {
		set("user_company_id", userCompanyId);
	}

	public java.lang.Integer getUserCompanyId() {
		return get("user_company_id");
	}

	public void setUserCompanyDepart(java.lang.String userCompanyDepart) {
		set("user_company_depart", userCompanyDepart);
	}

	public java.lang.String getUserCompanyDepart() {
		return get("user_company_depart");
	}

	public void setUserCompanyCareer(java.lang.String userCompanyCareer) {
		set("user_company_career", userCompanyCareer);
	}

	public java.lang.String getUserCompanyCareer() {
		return get("user_company_career");
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
