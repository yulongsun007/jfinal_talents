package win.yulongsun.talents.controller;

import win.yulongsun.talents.common.BaseController;
import win.yulongsun.talents.common.Response;
import win.yulongsun.talents.model.Clazz;
import win.yulongsun.talents.model.UserPlanClazzR;
import win.yulongsun.talents.model.UserPlanR;
import win.yulongsun.talents.util.ValidateUtils;

import java.util.List;

/**
 * Created by sunyulong on 2016/12/25.
 */
public class UserPlanRController extends BaseController {
    //学习此计划
    public void add() {
        Integer user_id      = getParaToInt("user_id");
        Integer plan_id      = getParaToInt("plan_id");
        Integer apply_status = getParaToInt("apply_status");
        boolean isNull       = ValidateUtils.validatePara(user_id, plan_id, apply_status);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
            return;
        }
        List<UserPlanR> learnedPlanList = UserPlanR.dao.find("select * from t_user_plan_r where user_id = ? and plan_id = ?", user_id, plan_id);
        if (learnedPlanList.size() > 0) {
            renderError("您已添加了此培养计划，无需重复添加");
            return;
        }
        UserPlanR planR = new UserPlanR();
        planR.setUserId(user_id);
        planR.setPlanId(plan_id);
        planR.setApplyStatus(apply_status);
        boolean planRSave = planR.save();
        //根据plan_id查出所有课程,添加到关系表中
        List<Clazz> clazzList = Clazz.dao.findByPlanId(plan_id);
        for (Clazz clazz : clazzList) {
            UserPlanClazzR planClazzR = new UserPlanClazzR();
            planClazzR.setUserId(user_id);
            planClazzR.setPlanId(plan_id);
            planClazzR.setClazzId(clazz.getClazzId());
            planClazzR.setClazzStatus("未开始");
            planClazzR.setClazzGrade(0);
            planClazzR.save();
        }
        if (planRSave) {
            renderSuccess();
        } else {
            renderError(Response.MSG.ADD_ERROR);
        }

    }

    //查询已选的所有计划
    public void list() {
        String  user_id = getPara("user_id");
        boolean isNull  = ValidateUtils.validatePara(user_id);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
            return;
        }
        List<UserPlanR> planRList = UserPlanR.dao.find("select r.`plan_already_hour`,r.`plan_already_score`,r.`apply_status`,p.*" +
                "from t_user_plan_r r left join t_plan p " +
                "on r.`plan_id` = p.`plan_id` " +
                "where r.`user_id`=?; ", user_id);
        for (UserPlanR r : planRList) {
            List<Clazz> clazzList = Clazz.dao.findByPlanId(r.getPlanId());
            r.put("clazz", clazzList);
        }
        renderSuccess(planRList);
    }


    public void update() {


    }

    public void delete() {

    }

    //查询学生已学的所有计划
    public void listStu() {
        String  user_id = getPara("user_id");
        boolean isNull  = ValidateUtils.validatePara(user_id);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
            return;
        }
        List<UserPlanR> planRList = UserPlanR.dao.find("select r.`plan_already_hour`,r.`plan_already_score`,r.`apply_status`,p.*" +
                "from t_user_plan_r r left join t_plan p " +
                "on r.`plan_id` = p.`plan_id` " +
                "where r.`user_id`=?; ", user_id);
        for (UserPlanR r : planRList) {
            //查出此培养计划下的课程的学习情况
            List<Clazz> clazzList = Clazz.dao.find("select c.*,r._id,r.`clazz_grade`,r.`clazz_status` " +
                    "from `t_clazz` c left join `t_user_plan_clazz_r` r " +
                    "on c.`clazz_id` = r.`clazz_id` " +
                    "where r.`user_id`=? and r.`plan_id`=? ", user_id, r.getPlanId());
            r.put("clazz", clazzList);
        }
        renderSuccess(planRList);

    }
}
