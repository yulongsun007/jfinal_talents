package win.yulongsun.talents.controller;

import win.yulongsun.talents.common.BaseController;
import win.yulongsun.talents.common.Response;
import win.yulongsun.talents.model.JobTemplate;
import win.yulongsun.talents.model.JobTemplateDeploy;
import win.yulongsun.talents.util.ValidateUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by sunyulong on 2016/12/10.
 */
public class JobTemplateController extends BaseController {


    public void add() {
        String  tmp_job_biz_direct    = getPara("tmp_job_biz_direct");
        String  tmp_job_name          = getPara("tmp_job_name");
        String  tmp_job_depart        = getPara("tmp_job_depart");
        Integer tmp_job_num           = getParaToInt("tmp_job_num");
        String  tmp_job_edu_require   = getPara("tmp_job_edu_require");
        String  tmp_job_exper_year    = getPara("tmp_job_exper_year");
        String  tmp_job_salary        = getPara("tmp_job_salary");
        String  tmp_job_addr          = getPara("tmp_job_addr");
        String  tmp_job_skill_require = getPara("tmp_job_skill_require");
        String  tmp_job_desc          = getPara("tmp_job_desc");
        String  create_by             = getPara("create_by");

        boolean isNull = ValidateUtils.validatePara(tmp_job_biz_direct, tmp_job_name, tmp_job_num, tmp_job_depart
                , tmp_job_addr, tmp_job_exper_year, tmp_job_desc
                , tmp_job_skill_require, tmp_job_salary, create_by, tmp_job_edu_require);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }

        JobTemplate tmp = new JobTemplate();
        tmp.setTmpJobBizDirect(tmp_job_biz_direct);
        tmp.setTmpJobName(tmp_job_name);
        tmp.setTmpJobDepart(tmp_job_depart);
        tmp.setTmpJobNum(tmp_job_num);
        tmp.setTmpJobEduRequire(tmp_job_edu_require);
        tmp.setTmpJobSkillRequire(tmp_job_skill_require);
        tmp.setTmpJobExperYear(tmp_job_exper_year);
        tmp.setTmpJobAddr(tmp_job_addr);
        tmp.setTmpJobDesc(tmp_job_desc);
        tmp.setTmpJobSalary(tmp_job_salary);
        tmp.setCreateBy(create_by);
        boolean save = tmp.save();
        if (save) {
            renderSuccess();
        } else {
            renderError(Response.MSG.ADD_ERROR);
        }

    }

    public void list() {
        String  create_by = getPara("create_by");
        boolean isNull    = ValidateUtils.validatePara(create_by);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        List<JobTemplate> list = JobTemplate.dao.find("select * from t_job_template where create_by = ? order by tmp_id desc", create_by);
        renderSuccess(list);
    }

    public void update() {
        Integer tmp_id                = getParaToInt("tmp_id");
        String  tmp_job_biz_direct    = getPara("tmp_job_biz_direct");
        String  tmp_job_name          = getPara("tmp_job_name");
        String  tmp_job_depart        = getPara("tmp_job_depart");
        Integer tmp_job_num           = getParaToInt("tmp_job_num");
        String  tmp_job_edu_require   = getPara("tmp_job_edu_require");
        String  tmp_job_exper_year    = getPara("tmp_job_exper_year");
        String  tmp_job_salary        = getPara("tmp_job_salary");
        String  tmp_job_addr          = getPara("tmp_job_addr");
        String  tmp_job_skill_require = getPara("tmp_job_skill_require");
        String  tmp_job_desc          = getPara("tmp_job_desc");
        String  create_by             = getPara("create_by");

        boolean isNull = ValidateUtils.validatePara(tmp_id, tmp_job_name, tmp_job_num, tmp_job_depart, tmp_job_biz_direct
                , tmp_job_addr, tmp_job_exper_year, tmp_job_desc, tmp_job_skill_require, tmp_job_salary, create_by, tmp_job_edu_require);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }

        JobTemplate tmp = new JobTemplate();
        tmp.setTmpId(tmp_id);
        tmp.setTmpJobBizDirect(tmp_job_biz_direct);
        tmp.setTmpJobName(tmp_job_name);
        tmp.setTmpJobDepart(tmp_job_depart);
        tmp.setTmpJobNum(tmp_job_num);
        tmp.setTmpJobEduRequire(tmp_job_edu_require);
        tmp.setTmpJobSkillRequire(tmp_job_skill_require);
        tmp.setTmpJobExperYear(tmp_job_exper_year);
        tmp.setTmpJobAddr(tmp_job_addr);
        tmp.setTmpJobDesc(tmp_job_desc);
        tmp.setTmpJobSalary(tmp_job_salary);
        tmp.setCreateBy(create_by);
        boolean update = tmp.update();
        if (update) {
            renderSuccess();
        } else {
            renderError(Response.MSG.UPDATE_ERROR);
        }
    }

    public void delete() {
        Integer tmp_id    = getParaToInt("tmp_id");
        String  create_by = getPara("create_by");
        boolean isNull    = ValidateUtils.validatePara(tmp_id, create_by);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        JobTemplate tmp = new JobTemplate();
        tmp.setTmpId(tmp_id);
        tmp.setCreateBy(create_by);
        boolean delete = tmp.delete();
        if (delete) {
            renderSuccess(Response.MSG.DEFAULT_SUCCESS);
        } else {
            renderError(Response.MSG.DELETE_ERROR);
        }
    }

    //发布招聘
    public void deploy() {
        Integer tmp_id   = getParaToInt("tmp_id");
        Date    start_at = getParaToDate("start_at");
        Date    end_at   = getParaToDate("end_at");
        boolean isNull   = ValidateUtils.validatePara(tmp_id, start_at, end_at);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        JobTemplateDeploy deploy = new JobTemplateDeploy();
        deploy.setTmpId(tmp_id);
        deploy.setStartAt(start_at);
        deploy.setEntAt(end_at);
        deploy.setIsActive(1);
        boolean save = deploy.save();
        if (save) {
            renderSuccess();
        } else {
            renderError(Response.MSG.ADD_ERROR);
        }
    }

    //查询已发布的招聘
    public void listDeploy() {
        Integer create_by = getParaToInt("create_by");
        boolean isNull    = ValidateUtils.validatePara(create_by);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        List<JobTemplateDeploy> list = JobTemplateDeploy.dao.find("SELECT d._id,d.start_at,d.ent_at,d.deploy_at,t.* " +
                "FROM t_job_template_deploy d Left JOIN t_job_template t " +
                "ON d.tmp_id=t.tmp_id " +
                "WHERE t.create_by=? AND d.is_active=1 order by d._id desc", create_by);
        renderSuccess(list);
    }

    //删除已发布的招聘
    public void deleteDeploy() {
        Integer _id    = getParaToInt("_id");
        boolean isNull = ValidateUtils.validatePara(_id);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        JobTemplateDeploy deploy = new JobTemplateDeploy();
        deploy.setId(_id);
        deploy.setIsActive(0);
        boolean update = deploy.update();
        if (update) {
            renderSuccess();
        } else {
            renderError(Response.MSG.UPDATE_ERROR);
        }
    }
}
