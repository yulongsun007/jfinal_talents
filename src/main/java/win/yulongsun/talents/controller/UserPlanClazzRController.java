package win.yulongsun.talents.controller;

import win.yulongsun.talents.common.BaseController;
import win.yulongsun.talents.common.Response;
import win.yulongsun.talents.model.Clazz;
import win.yulongsun.talents.model.UserPlanClazzR;
import win.yulongsun.talents.model.UserPlanR;
import win.yulongsun.talents.util.ValidateUtils;

import java.util.List;

/**
 * Created by sunyulong on 2016/12/26.
 */
public class UserPlanClazzRController extends BaseController {
    public void add() {

    }

    public void list() {

    }

    public void update() {
        String  _id          = getPara("_id");
        String  user_id      = getPara("user_id");
        String  plan_id      = getPara("plan_id");
        String  clazz_grade  = getPara("clazz_grade");
        String  clazz_status = getPara("clazz_status");
        boolean isNull       = ValidateUtils.validatePara(user_id, plan_id, _id, clazz_status, clazz_grade);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
            return;
        }
        UserPlanClazzR clazzR = new UserPlanClazzR();
        if (_id != null) {
            clazzR.setId(Integer.valueOf(_id));
        }
        clazzR.setUserId(Integer.valueOf(user_id));
        clazzR.setPlanId(Integer.valueOf(plan_id));
        clazzR.setClazzGrade(Integer.valueOf(clazz_grade));
        clazzR.setClazzStatus(clazz_status);
        boolean update = clazzR.update();
        if (update) {
            //查出此培养计划下的课程的学习情况
            List<Clazz> clazzList = Clazz.dao.find("select c.*,r._id,r.`clazz_grade`,r.`clazz_status` " +
                    "from `t_clazz` c left join `t_user_plan_clazz_r` r " +
                    "on c.`clazz_id` = r.`clazz_id` " +
                    "where r.`user_id`=? and r.`plan_id`=? ", user_id, plan_id);
            //更新t_user_plan
            if ("已完成".equals(clazz_status)) {
                int            totalScore = 0;
                int            totalHour  = 0;
                UserPlanClazzR planClazzR = UserPlanClazzR.dao.findById(_id);
                Clazz          c          = Clazz.dao.findById(planClazzR.getClazzId());
                totalScore += c.getClazzScore();
                totalHour += c.getClazzHour();
                UserPlanR userPlanR = UserPlanR.dao.findFirst("select * from t_user_plan_r where plan_id =? ", plan_id);
                userPlanR.setPlanAlreadyHour(totalHour);
                userPlanR.setPlanAlreadyScore(totalScore);
                userPlanR.update();
            }

            renderSuccess(clazzList);
        } else {
            renderError();
        }
    }

    public void delete() {

    }
}
