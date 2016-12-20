package win.yulongsun.talents.controller;

import win.yulongsun.talents.common.BaseController;
import win.yulongsun.talents.common.Response;
import win.yulongsun.talents.model.Clazz;
import win.yulongsun.talents.util.ValidateUtils;

/**
 * Created by sunyulong on 2016/12/10.
 */
public class ClazzController extends BaseController {
    public void add() {
        String clazz_name = getPara("clazz_name");
        String clazz_desc = getPara("clazz_desc");
        String clazz_require = getPara("clazz_require");
        Integer clazz_hour = getParaToInt("clazz_hour");
        boolean isNull = ValidateUtils.validatePara(clazz_name, clazz_desc, clazz_require, clazz_hour);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        Clazz clazz = new Clazz();
        clazz.setClazzName(clazz_name);
        clazz.setClazzDesc(clazz_desc);
        clazz.setClazzRequire(clazz_require);
        clazz.setClazzHour(clazz_hour);
        boolean save = clazz.save();
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
