package win.yulongsun.talents.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseJobTemplate<M extends BaseJobTemplate<M>> extends Model<M> implements IBean {

	public void setTmpId(java.lang.Integer tmpId) {
		set("tmp_id", tmpId);
	}

	public java.lang.Integer getTmpId() {
		return get("tmp_id");
	}

	public void setTmpJobName(java.lang.String tmpJobName) {
		set("tmp_job_name", tmpJobName);
	}

	public java.lang.String getTmpJobName() {
		return get("tmp_job_name");
	}

	public void setTmpJobNum(java.lang.Integer tmpJobNum) {
		set("tmp_job_num", tmpJobNum);
	}

	public java.lang.Integer getTmpJobNum() {
		return get("tmp_job_num");
	}

	public void setTmpJobBizDirect(java.lang.String tmpJobBizDirect) {
		set("tmp_job_biz_direct", tmpJobBizDirect);
	}

	public java.lang.String getTmpJobBizDirect() {
		return get("tmp_job_biz_direct");
	}

	public void setTmpJobAddr(java.lang.String tmpJobAddr) {
		set("tmp_job_addr", tmpJobAddr);
	}

	public java.lang.String getTmpJobAddr() {
		return get("tmp_job_addr");
	}

	public void setTmpJobExperYear(java.lang.String tmpJobExperYear) {
		set("tmp_job_exper_year", tmpJobExperYear);
	}

	public java.lang.String getTmpJobExperYear() {
		return get("tmp_job_exper_year");
	}

	public void setTmpJobDesc(java.lang.String tmpJobDesc) {
		set("tmp_job_desc", tmpJobDesc);
	}

	public java.lang.String getTmpJobDesc() {
		return get("tmp_job_desc");
	}

	public void setTmpJobSkillRequire(java.lang.String tmpJobSkillRequire) {
		set("tmp_job_skill_require", tmpJobSkillRequire);
	}

	public java.lang.String getTmpJobSkillRequire() {
		return get("tmp_job_skill_require");
	}

	public void setTmpJobEduRequire(java.lang.String tmpJobEduRequire) {
		set("tmp_job_edu_require", tmpJobEduRequire);
	}

	public java.lang.String getTmpJobEduRequire() {
		return get("tmp_job_edu_require");
	}

	public void setTmpJobSalary(java.lang.String tmpJobSalary) {
		set("tmp_job_salary", tmpJobSalary);
	}

	public java.lang.String getTmpJobSalary() {
		return get("tmp_job_salary");
	}

	public void setTmpJobDepart(java.lang.String tmpJobDepart) {
		set("tmp_job_depart", tmpJobDepart);
	}

	public java.lang.String getTmpJobDepart() {
		return get("tmp_job_depart");
	}

	public void setCreateBy(java.lang.String createBy) {
		set("create_by", createBy);
	}

	public java.lang.String getCreateBy() {
		return get("create_by");
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
