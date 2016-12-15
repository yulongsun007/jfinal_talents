package win.yulongsun.talents.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2016/4/13
 * @Version 1.0.0
 * @Description
 */
public class Response {
    private int    code;
    private String msg;
    private List data = new ArrayList();


    public Response() {
        respError();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    //========================   success  ===============================

    public void respSuccess() {
        respSuccess(MSG.DEFAULT_SUCCESS);
    }

    public void respSuccess(String msg) {
        respSuccess(msg, null);
    }

    public void respSuccess(List data) {
        respSuccess(MSG.DEFAULT_SUCCESS, data);
    }

    public void respSuccess(String msg, List data) {
        this.code = CODE.SUCCESS;
        this.msg = msg;
        if(data!=null){
            this.data.addAll(data);
        }
    }


    //========================   error  ===============================
    public void respError() {
        respError(MSG.DEFAULT_ERROR);
    }

    public void respError(String msg) {
        this.code = CODE.ERROR;
        this.msg = msg;
    }


    public interface CODE {
        int SUCCESS = 200;
        int ERROR   = 400;
    }

    public interface MSG {
        String DEFAULT_SUCCESS = "处理成功";
        String DEFAULT_ERROR   = "处理失败";
        String REQ_IS_NULL     = "参数不正确";
        String ADD_ERROR       = "添加失败";
        String UPDATE_ERROR    = "更新失败";
        String DELETE_ERROR    = "删除失败";
        String SELECT_ERROR    = "查询失败";
    }

}
