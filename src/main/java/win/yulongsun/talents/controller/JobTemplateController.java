package win.yulongsun.talents.controller;

import win.yulongsun.talents.common.BaseController;
import win.yulongsun.talents.common.Response;
import win.yulongsun.talents.model.JobTemplate;
import win.yulongsun.talents.util.ValidateUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by sunyulong on 2016/12/10.
 */
public class JobTemplateController extends BaseController {


    public void add() {
        String  tmp_job_name        = getPara("tmp_job_name");
        Integer tmp_job_num         = getParaToInt("tmp_job_num");
        String  tmp_depart          = getPara("tmp_depart");
        String  tmp_depart_contact  = getPara("tmp_depart_contact");
        String  tmp_job_addr        = getPara("tmp_job_addr");
        String  tmp_job_category    = getPara("tmp_job_category");
        String  tmp_job_exper_year  = getPara("tmp_job_exper_year");
        String  tmp_job_desc        = getPara("tmp_job_desc");
        String  tmp_job_require     = getPara("tmp_job_require");
        String  tmp_special_require = getPara("tmp_special_require");
        String  tmp_salary          = getPara("tmp_salary");
        Integer tmp_urgency         = getParaToInt("tmp_urgency");
        Date    deadtime            = getParaToDate("deadtime");
        String  create_by           = getPara("create_by");

        boolean isNull = ValidateUtils.validatePara(tmp_job_name, tmp_job_num, tmp_depart, tmp_depart_contact
                , tmp_job_addr, tmp_job_category, tmp_job_exper_year, tmp_job_desc, tmp_job_require
                , tmp_special_require, tmp_salary, tmp_urgency, deadtime, create_by);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }

        JobTemplate tmp = new JobTemplate();
        tmp.setTmpJobName(tmp_job_name);
        tmp.setTmpJobNum(tmp_job_num);
        tmp.setTmpDepart(tmp_depart);
        tmp.setTmpDepartContact(tmp_depart_contact);
        tmp.setTmpJobAddr(tmp_job_addr);
        tmp.setTmpJobCategory(tmp_job_category);
        tmp.setTmpJobExperYear(tmp_job_exper_year);
        tmp.setTmpJobDesc(tmp_job_desc);
        tmp.setTmpJobRequire(tmp_job_require);
        tmp.setTmpSpecialRequire(tmp_special_require);
        tmp.setTmpSalary(tmp_salary);
        tmp.setTmpUrgency(tmp_urgency);
        tmp.setDeadtime(deadtime);
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
        List<JobTemplate> list = JobTemplate.dao.find("select * from t_job_template where create_by = ?", create_by);
        renderSuccess(list);
    }

    public void update() {
        Integer tmp_id              = getParaToInt("tmp_id");
        String  tmp_job_name        = getPara("tmp_job_name");
        Integer tmp_job_num         = getParaToInt("tmp_job_num");
        String  tmp_depart          = getPara("tmp_depart");
        String  tmp_depart_contact  = getPara("tmp_depart_contact");
        String  tmp_job_addr        = getPara("tmp_job_addr");
        String  tmp_job_category    = getPara("tmp_job_category");
        String  tmp_job_exper_year  = getPara("tmp_job_exper_year");
        String  tmp_job_desc        = getPara("tmp_job_desc");
        String  tmp_job_require     = getPara("tmp_job_require");
        String  tmp_special_require = getPara("tmp_special_require");
        String  tmp_salary          = getPara("tmp_salary");
        Integer tmp_urgency         = getParaToInt("tmp_urgency");
        Date    deadtime            = getParaToDate("deadtime");

        boolean isNull = ValidateUtils.validatePara(tmp_id, tmp_job_name, tmp_job_num, tmp_depart, tmp_depart_contact
                , tmp_job_addr, tmp_job_category, tmp_job_exper_year, tmp_job_desc, tmp_job_require
                , tmp_special_require, tmp_salary, tmp_urgency, deadtime);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }

        JobTemplate tmp = new JobTemplate();
        tmp.setTmpId(tmp_id);
        tmp.setTmpJobName(tmp_job_name);
        tmp.setTmpJobNum(tmp_job_num);
        tmp.setTmpDepart(tmp_depart);
        tmp.setTmpDepartContact(tmp_depart_contact);
        tmp.setTmpJobAddr(tmp_job_addr);
        tmp.setTmpJobCategory(tmp_job_category);
        tmp.setTmpJobExperYear(tmp_job_exper_year);
        tmp.setTmpJobDesc(tmp_job_desc);
        tmp.setTmpJobRequire(tmp_job_require);
        tmp.setTmpSpecialRequire(tmp_special_require);
        tmp.setTmpSalary(tmp_salary);
        tmp.setTmpUrgency(tmp_urgency);
        tmp.setDeadtime(deadtime);
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
        boolean isNull    = ValidateUtils.validatePara(tmp_id,create_by);
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
}
