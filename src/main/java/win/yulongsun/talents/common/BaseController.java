package win.yulongsun.talents.common;

import com.jfinal.core.Controller;

import java.util.ArrayList;
import java.util.List;

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
        response.respSuccess();
        renderJson(response);
    }

    public void renderSuccess(String msg) {
        response.respSuccess(msg);
        renderJson(response);
    }

    public void renderSuccess(Object object) {
        ArrayList list = new ArrayList();
        list.add(object);
        response.respSuccess(list);
        renderJson(response);
    }

    public void renderSuccess(List object) {
        response.respSuccess(object);
        renderJson(response);
    }

    public void renderSuccess(String msg, List object) {
        response.respSuccess(msg, object);
        renderJson(response);
        return;
    }

    //========================   error  ===============================

    public void renderError() {
        renderJson(response);
    }

    public void renderError(String msg) {
        response.setMsg(msg);
        renderJson(response);
    }


}
