package win.yulongsun.talents.controller;

import win.yulongsun.talents.common.BaseController;
import win.yulongsun.talents.common.Response;
import win.yulongsun.talents.model.JobTemplate;
import win.yulongsun.talents.model.JobTemplateDeploy;
import win.yulongsun.talents.model.User;
import win.yulongsun.talents.util.StringUtils;
import win.yulongsun.talents.util.ValidateUtils;

import java.util.ArrayList;
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

    //HR：查询未发布的招聘模板
    public void hrListUnDeploy() {
        String  create_by = getPara("create_by");
        boolean isNull    = ValidateUtils.validatePara(create_by);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        List<JobTemplate> list = JobTemplate.dao.find("select * from `t_job_template` where `tmp_id` not in (select tmp_id from `t_job_template_deploy`) and create_by = ?", create_by);
        renderSuccess(list);
    }

    ///////////////////////////////////////////////////////////////////////////

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

    //查询某个公司下已发布的招聘
    public void listDeploy() {
        Integer user_company_id = getParaToInt("user_company_id");
        boolean isNull          = ValidateUtils.validatePara(user_company_id);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        //查出一家公司下的所有HR
        List<User> users = User.dao.find("select * from t_user where user_company_id = ? and user_role_id=1", user_company_id);
        //查出所有的已发布的招聘信息
        List<JobTemplateDeploy> result = new ArrayList<JobTemplateDeploy>();
        for (User user : users) {
            List<JobTemplateDeploy> list = JobTemplateDeploy.dao.find("SELECT d._id,d.start_at,d.ent_at,d.deploy_at,t.* " +
                    "FROM t_job_template_deploy d Left JOIN t_job_template t " +
                    "ON d.tmp_id=t.tmp_id " +
                    "WHERE t.create_by=? AND d.is_active=1 order by d._id desc", user.getUserId());
            result.addAll(list);
        }
        renderSuccess(result);
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

    //模糊查询，根据模板ID或者模板名
    public void queryDeploy() {
        String  create_by    = getPara("create_by");
        String  tmp_id       = getPara("tmp_id");
        String  tmp_job_name = getPara("tmp_job_name");
        boolean isNull       = ValidateUtils.validatePara(create_by);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        String sql = "SELECT d._id,d.start_at,d.ent_at,d.deploy_at,t.* " +
                "FROM t_job_template_deploy d Left JOIN t_job_template t " +
                "ON d.tmp_id=t.tmp_id " +
                "WHERE t.create_by=" + create_by + " AND d.is_active=1 ";
        if (StringUtils.isNotEmpty(tmp_id) && !"null".equals(tmp_id)) {
            sql += "AND d.tmp_id=" + tmp_id;
        }
        if (StringUtils.isNotEmpty(tmp_job_name)) {
            sql += "AND  t.tmp_job_name like '%" + tmp_job_name + "%' ";
        }
        sql += " order by d._id desc";
        List<JobTemplateDeploy> list = JobTemplateDeploy.dao.find(sql);
        renderSuccess(list);
    }


    //查询所有已发布的招聘信息
    public void listAllDeploy() {
        // ALL HR
        List<User> userList = User.dao.find("select * from t_user where user_role_id=1");
        //查出所有的已发布的招聘信息
        List<JobTemplateDeploy> result = new ArrayList<JobTemplateDeploy>();
        for (User user : userList) {
            List<JobTemplateDeploy> list = JobTemplateDeploy.dao.find("SELECT d._id,d.start_at,d.ent_at,d.deploy_at,t.* " +
                    "FROM t_job_template_deploy d Left JOIN t_job_template t " +
                    "ON d.tmp_id=t.tmp_id " +
                    "WHERE t.create_by=? AND d.is_active=1 order by d._id desc", user.getUserId());
            result.addAll(list);
        }
        renderSuccess(result);
    }
}
