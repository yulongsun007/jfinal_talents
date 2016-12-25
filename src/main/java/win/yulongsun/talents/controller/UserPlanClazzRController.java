package win.yulongsun.talents.controller;

import win.yulongsun.talents.common.BaseController;
import win.yulongsun.talents.common.Response;
import win.yulongsun.talents.model.Clazz;
import win.yulongsun.talents.model.UserPlanClazzR;
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
            renderSuccess(clazzList);
        } else {
            renderError();
        }
    }

    public void delete() {

    }
}
