package win.yulongsun.talents.common;

/**
 * @Date 2016/4/13
 * @Version 1.0.0
 * @Description
 */
public class Response {
    private int    code;
    private String msg;
    private Object data;


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

    public void setData(Object data) {
        this.data = data;
    }

//========================   success  ===============================

    public void respSuccess() {
        this.code = CODE.SUCCESS;
        this.msg = MSG.DEFAULT_SUCCESS;
        this.data = "";
    }

    public void respSuccess(Object data) {
        this.code = CODE.SUCCESS;
        this.msg = MSG.DEFAULT_SUCCESS;
        this.data = data;
    }

    public void respSuccess(String msg, Object data) {
        this.code = CODE.SUCCESS;
        this.msg = msg;
        this.data = data;
    }


    //========================   error  ===============================
    public void respError() {
        this.code = CODE.ERROR;
        this.msg = MSG.DEFAULT_ERROR;
        this.data = "";
    }

    public void respError(String msg) {
        this.code = CODE.ERROR;
        this.msg = msg;
        this.data = "";
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
