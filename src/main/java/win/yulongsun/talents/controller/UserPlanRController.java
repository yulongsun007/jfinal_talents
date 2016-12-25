package win.yulongsun.talents.controller;

import win.yulongsun.talents.common.BaseController;
import win.yulongsun.talents.common.Response;
import win.yulongsun.talents.model.UserPlanR;
import win.yulongsun.talents.util.ValidateUtils;

import java.util.List;

/**
 * Created by sunyulong on 2016/12/25.
 */
public class UserPlanRController extends BaseController {
    public void add() {
        Integer user_id      = getParaToInt("user_id");
        Integer plan_id      = getParaToInt("plan_id");
        Integer apply_status = getParaToInt("apply_status");
        boolean isNull       = ValidateUtils.validatePara(user_id, plan_id, apply_status);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
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
        boolean save = planR.save();
        if (save) {
            renderSuccess();
        } else {
            renderError(Response.MSG.ADD_ERROR);
        }

    }

    public void list() {

    }

    public void update() {

    }

    public void delete() {

    }
}
