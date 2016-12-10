package win.yulongsun.talents.controller;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.POST;
import win.yulongsun.talents.common.BaseController;

/**
 * Created by yulongsun on 2016/5/5.
 */
public class UserController extends BaseController {



    public void register() {
//        String  user_name = getPara("user_name");
//        String  user_pwd  = getPara("user_pwd");
//        boolean isNull    = ValidateUtils.validatePara(user_name, user_pwd);
//        if (isNull) {
//            response.respError(Response.MSG.REQ_IS_NULL);
//            renderJson(response);
//            return;
//        }
//        List<User> userList = User.dao.find("SELECT * From user WHERE user_name=?", user_name);
//        if (userList == null || userList.size() != 0) {
//            response.respError(Response.MSG.REQ_IS_NULL);
//        } else {
//            User user = new User();
//            user.setUserName(user_name);
//            user.setUserToken(user_pwd);
//            user.save();
//            response.respSuccess("注册成功");
//        }
    }

    public void login() {
//        response = new Response();
//        String  user_name = getPara("user_name");
//        String  user_pwd  = getPara("user_pwd");
//        boolean isNull    = ValidateUtils.validatePara(user_name, user_pwd);
//        if (isNull) {
//            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
//            renderJson(response);
//            return;
//        }
//        User user = User.dao.findById(user_name);
//        if (user == null) {
//            response.setFailureResponse(Response.ErrorCode.USER_NULL);
//        } else {
//            if (user.getUserToken().equals(user_pwd)) {
//                String signature = TokenUtil.generateToken(user.getUserId().toString(), user.getUserId()).getSignature();
//                user.setUserToken(signature);
//                user.update();
//                response.setSuccessResponse("登陆成功", signature);
//            } else {
//                response.setFailureResponse(Response.ErrorCode.ERROR_PWD);
//            }
//        }
//        renderJson(response);
    }

    @Before(POST.class)
    public void upload() {
//        UploadFile user_img = getFile("user_img");
//        String     user_id  = getPara("user_id");
//        boolean    isNull   = ValidateUtils.validatePara(user_id, user_img.toString());
//        if (isNull) {
//            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
//            renderJson(response);
//            return;
//        }
//        User user = User.dao.findById(user_id);
    }

    public void add() {
    }

    public void list() {

    }

    public void update() {

    }

    public void delete() {

    }
}
