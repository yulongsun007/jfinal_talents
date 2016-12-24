package win.yulongsun.talents.controller;

import win.yulongsun.talents.common.BaseController;
import win.yulongsun.talents.common.Response;
import win.yulongsun.talents.model.Clazz;
import win.yulongsun.talents.model.Plan;
import win.yulongsun.talents.util.ValidateUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by sunyulong on 2016/12/10.
 * 课程
 */
public class PlanController extends BaseController {

    public void add() {
        String  plan_name       = getPara("plan_name");
        String  plan_desc       = getPara("plan_desc");
        String  plan_content    = getPara("plan_content");
        Integer job_template_id = getParaToInt("job_template_id");
        Integer create_by       = getParaToInt("create_by");
        boolean isNull          = ValidateUtils.validatePara(plan_content, plan_name, plan_desc, job_template_id, create_by);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        Plan plan = new Plan();
        plan.setPlanName(plan_name);
        plan.setPlanDesc(plan_desc);
        plan.setPlanContent(plan_content);
        plan.setJobTemplateId(job_template_id);
        plan.setCreateBy(create_by);
        plan.setCreateAt(new Date());
        boolean save = plan.save();
        if (save) {
            renderSuccess();
        } else {
            renderError(Response.MSG.ADD_ERROR);
        }
    }

    public void list() {
        Integer create_by = getParaToInt("create_by");
        boolean isNull    = ValidateUtils.validatePara(create_by);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        List<Plan> planList = Plan.dao.find("select * from t_plan where create_by = ?", create_by);
        //查出对应培养计划下的课程列表
        for (Plan plan : planList) {
            List<Clazz> clazzList = Clazz.dao.find("select * from t_clazz where plan_id=? order by clazz_priority asc", plan.getPlanId());
            plan.put("clazz", clazzList);
        }
        renderSuccess(planList);
    }

    public void update() {
        Integer plan_id      = getParaToInt("plan_id");
        String  plan_name    = getPara("plan_name");
        String  plan_desc    = getPara("plan_desc");
        String  plan_content = getPara("plan_content");
        boolean isNull       = ValidateUtils.validatePara(plan_id);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        Plan plan = new Plan();
        plan.setPlanId(plan_id);
        if (!ValidateUtils.isEmpty(plan_name)) {
            plan.setPlanName(plan_name);
        }
        if (!ValidateUtils.isEmpty(plan_desc)) {
            plan.setPlanDesc(plan_desc);
        }
        if (!ValidateUtils.isEmpty(plan_content)) {
            plan.setPlanContent(plan_content);
        }
        plan.setUpdateAt(new Date());
        boolean update = plan.update();
        if (update) {
            renderSuccess();
        } else {
            renderError(Response.MSG.UPDATE_ERROR);
        }
    }

    public void delete() {
        Integer plan_id = getParaToInt("plan_id");
        boolean isNull  = ValidateUtils.validatePara(plan_id);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        boolean delete = Plan.dao.deleteById(plan_id);
        if (delete) {
            renderSuccess();
        } else {
            renderError(Response.MSG.DELETE_ERROR);
        }
    }
}
