package win.yulongsun.talents.controller;

import com.taobao.api.ApiException;
import com.taobao.api.domain.BizResult;
import win.yulongsun.talents.common.BaseController;
import win.yulongsun.talents.common.Response;
import win.yulongsun.talents.model.User;
import win.yulongsun.talents.model.UserCompanyR;
import win.yulongsun.talents.util.DayuSMSUtils;
import win.yulongsun.talents.util.ValidateUtils;

import java.util.List;


/**
 * Created by yulongsun on 2016/5/5.
 */
public class UserController extends BaseController {


//    public void register() {
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
//    }

//    public void login() {
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
//    }

//    @Before(POST.class)
//    public void upload() {
//        UploadFile user_img = getFile("user_img");
//        String     user_id  = getPara("user_id");
//        boolean    isNull   = ValidateUtils.validatePara(user_id, user_img.toString());
//        if (isNull) {
//            response.setFailureResponse(Response.ErrorCode.REQUEST_NULL);
//            renderJson(response);
//            return;
//        }
//        User user = User.dao.findById(user_id);
//    }

    public void sendVerifyCode() {
        String  user_mobile = getPara("user_mobile");
        boolean isNull      = ValidateUtils.validatePara(user_mobile);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        if (userIsRegister(user_mobile)) {
            renderError("此手机号已经被注册");
            return;
        }
        try {
            int       code      = (int) ((Math.random() * 9 + 1) * 100000);
            BizResult bizResult = DayuSMSUtils.sendSms(user_mobile, code);
            if (bizResult.getSuccess()) {
                //将发送记录存在session中
//                setSessionAttr(user_mobile, code);
                renderSuccess();
            } else {
                renderError(bizResult.getMsg());
            }
        } catch (ApiException e) {
            e.printStackTrace();
            renderError("验证码发送失败");
        }

    }

    //注册
    public void add() {
        String  user_mobile     = getPara("user_mobile");
        String  user_token      = getPara("user_token");
        Integer user_role       = getParaToInt("user_role_id");
        String  user_name       = getPara("user_name");
        Integer user_company_id = getParaToInt("user_company_id");
        String  company_name    = getPara("company_name");
        String  company_addr    = getPara("company_addr");
        String  company_contact = getPara("company_contact");
        boolean isNull          = ValidateUtils.validatePara(user_mobile, user_token, user_role, user_name);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        if (userIsRegister(user_mobile)) {
            renderError("此手机号已经被注册");
            return;
        }
        if (user_company_id == null) {
            //没有公司Id的用户
            User         user    = new User();
            UserCompanyR company = new UserCompanyR();
            company.setCompanyName(company_name);
            company.setCompanyAddr(company_addr);
            company.setCompanyContact(company_contact);
            boolean companySave = company.save();
            if (companySave) {
                Integer companyId = company.getCompanyId();
                user.setUserCompanyId(companyId);
            } else {
                renderError(Response.MSG.ADD_ERROR);
                return;
            }
            user.setUserMobile(user_mobile);
            user.setUserToken(user_token);
            user.setUserRoleId(user_role);
            user.setUserName(user_name);
            boolean save = user.save();
            if (save) {
                renderSuccess();
            } else {
                renderError(Response.MSG.ADD_ERROR);
            }
        } else {
            //有公司Id的用户
            UserCompanyR company = UserCompanyR.dao.findById(user_company_id);
            if (company == null) {
                renderError("找不到对应编号的公司");
                return;
            }
            User user = new User();
            user.setUserMobile(user_mobile);
            user.setUserToken(user_token);
            user.setUserCompanyId(user_company_id);
            user.setUserRoleId(user_role);
            user.setUserName(user_name);
            boolean save = user.save();
            if (save) {
                renderSuccess();
            } else {
                renderError(Response.MSG.ADD_ERROR);
            }
        }

    }

    //判断用户是否已经注册
    private boolean userIsRegister(String user_mobile) {
        //判断用户是否存在
        User user = User.dao.findFirst("select * from t_user where user_mobile =?", user_mobile);
        return user != null;
    }

    public void list() {

    }

    public void update() {

    }

    public void delete() {

    }


    //登录
    public void login() {
        String  user_mobile = getPara("user_mobile");
        String  user_token  = getPara("user_token");
        boolean isNull      = ValidateUtils.validatePara(user_mobile, user_token);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        User user = User.dao.findFirst("select u.*,c.company_name,c.company_addr,c.company_contact from t_user u left join t_user_company_r c on u.user_company_id = c.company_id " +
                "where user_mobile = ? and user_token = ?", user_mobile, user_token);
        if (user == null) {
            renderError("用户名或密码错误");
        } else {
            renderSuccess(user);
        }
    }

    //查询积分列表
    public void listScore() {
        Integer user_company_id = getParaToInt("user_company_id");
        boolean isNull          = ValidateUtils.validatePara(user_company_id);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        List<User> users = User.dao.find("select * from t_user where user_role_id = 2 and user_company_id = ? order by user_score desc", user_company_id);
        renderSuccess(users);
    }

    //查询HR名下的推荐人
    public void listReferrer() {
        Integer user_company_id = getParaToInt("user_company_id");
        boolean isNull          = ValidateUtils.validatePara(user_company_id);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        List<User> users = User.dao.find("select u.*,c.company_name,c.company_addr,c.company_contact from t_user u left join t_user_company_r c on u.user_company_id = c.company_id" +
                " where user_company_id = ? and user_role_id = 2", user_company_id);
        renderSuccess(users);
    }
}
