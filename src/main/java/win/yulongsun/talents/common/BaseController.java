package win.yulongsun.talents.common;

import com.jfinal.core.Controller;

/**
 * Created by sunyulong on 2016/12/10.
 */
public abstract class BaseController extends Controller {

    protected Response response = new Response();

    //添加
    public abstract void add();

    //查询
    public abstract void list();

    //修改
    public abstract void update();

    //删除
    public abstract void delete();

    //========================   success  ===============================
    public void renderSuccess() {
        response.setCode(Response.CODE.SUCCESS);
        response.setMsg(Response.MSG.DEFAULT_SUCCESS);
        renderJson(response);
        return;
    }

    public void renderSuccess(String msg) {
        response.setCode(Response.CODE.SUCCESS);
        response.setMsg(msg);
        renderJson(response);
        return;
    }

    public void renderSuccess(Object object) {
        response.setCode(Response.CODE.SUCCESS);
        response.setMsg(Response.MSG.DEFAULT_SUCCESS);
        response.setData(object);
        renderJson(response);
        return;
    }

    //========================   error  ===============================

    public void renderError() {
        renderJson(response);
        return;
    }

    public void renderError(String msg) {
        response.setMsg(msg);
        renderJson(response);
        return;
    }


}
