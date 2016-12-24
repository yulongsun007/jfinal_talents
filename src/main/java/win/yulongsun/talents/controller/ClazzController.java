package win.yulongsun.talents.controller;

import win.yulongsun.talents.common.BaseController;
import win.yulongsun.talents.common.Response;
import win.yulongsun.talents.model.Clazz;
import win.yulongsun.talents.util.ValidateUtils;

import java.util.List;

/**
 * Created by sunyulong on 2016/12/10.
 */
public class ClazzController extends BaseController {
    public void add() {
        String  clazz_name     = getPara("clazz_name");
        String  clazz_books    = getPara("clazz_books");
        Integer plan_id        = getParaToInt("plan_id");
        Integer clazz_priority = getParaToInt("clazz_priority");
        Integer clazz_hour     = getParaToInt("clazz_hour");
        Integer clazz_score    = getParaToInt("clazz_score");
        Integer create_by      = getParaToInt("create_by");
        boolean isNull         = ValidateUtils.validatePara(create_by, plan_id, clazz_books, clazz_score, clazz_name, clazz_priority, clazz_hour);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        Clazz clazz = new Clazz();
        clazz.setClazzName(clazz_name);
        clazz.setClazzBooks(clazz_books);
        clazz.setPlanId(plan_id);
        clazz.setClazzPriority(clazz_priority);
        clazz.setClazzHour(clazz_hour);
        clazz.setClazzScore(clazz_score);
        boolean save = clazz.save();
        if (save) {
            renderSuccess(list(plan_id));
        } else {
            renderError(Response.MSG.ADD_ERROR);
        }

    }

    public void list() {


    }

    public List<Clazz> list(Integer plan_id) {
        return Clazz.dao.find("select * from t_clazz where plan_id= ? order by clazz_priority asc", plan_id);
    }

    public void update() {

    }

    public void delete() {
        Integer plan_id  = getParaToInt("plan_id");
        Integer clazz_id = getParaToInt("clazz_id");
        boolean isNull   = ValidateUtils.validatePara(plan_id, clazz_id);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        boolean delete = Clazz.dao.deleteById(clazz_id);
        if (delete) {
            renderSuccess(Response.MSG.DLETE_SUCCESS, list(plan_id));
        } else {
            renderError();
        }
    }
}
