package win.yulongsun.talents.controller;

import win.yulongsun.talents.common.BaseController;
import win.yulongsun.talents.common.Constant;
import win.yulongsun.talents.common.Response;
import win.yulongsun.talents.model.*;
import win.yulongsun.talents.util.IPUtils;
import win.yulongsun.talents.util.ValidateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sunyulong on 2016/12/25.
 * //－1:投递失败\n0：未投递\n1：推荐人处理中\n2：已推荐\n3：已谢绝\n4：hr处理中\n5：面试邀请\n
 */
public class UserPlanRController extends BaseController {

    //学习此计划
    public void add() {
        Integer user_id      = getParaToInt("user_id");
        Integer plan_id      = getParaToInt("plan_id");
        Integer apply_status = getParaToInt("apply_status");
        boolean isNull       = ValidateUtils.validatePara(user_id, plan_id, apply_status);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
            return;
        }
        List<UserPlanR> learnedPlanList = UserPlanR.dao.find("select * from t_user_plan_r where user_id = ? and plan_id = ?", user_id, plan_id);
        if (learnedPlanList.size() > 0) {
            renderError("您已添加了此培养计划，无需重复添加");
            return;
        }
        UserPlanR planR = new UserPlanR();
        planR.setUserId(user_id);
        planR.setPlanId(plan_id);
        planR.setApplyStatus(Constant.APPLY_STATUS.UN_COMMIT);
        boolean planRSave = planR.save();
        //根据plan_id查出所有课程,添加到关系表中
        List<Clazz> clazzList = Clazz.dao.findByPlanId(plan_id);
        for (Clazz clazz : clazzList) {
            UserPlanClazzR planClazzR = new UserPlanClazzR();
            planClazzR.setUserId(user_id);
            planClazzR.setPlanId(plan_id);
            planClazzR.setClazzId(clazz.getClazzId());
            planClazzR.setClazzStatus("未开始");
            planClazzR.setClazzGrade(0);
            planClazzR.save();
        }
        if (planRSave) {
            renderSuccess();
        } else {
            renderError(Response.MSG.ADD_ERROR);
        }

    }

    //查询已选的所有计划
    public void list() {
        String  user_id = getPara("user_id");
        boolean isNull  = ValidateUtils.validatePara(user_id);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
            return;
        }
        List<UserPlanR> planRList = UserPlanR.dao.find("select r.`plan_already_hour`,r.`plan_already_score`,r.`apply_status`,p.*" +
                "from t_user_plan_r r left join t_plan p " +
                "on r.`plan_id` = p.`plan_id` " +
                "where r.`user_id`=?; ", user_id);
        for (UserPlanR r : planRList) {
            List<Clazz> clazzList = Clazz.dao.findByPlanId(r.getPlanId());
            r.put("clazz", clazzList);
        }
        renderSuccess(planRList);
    }


    public void update() {

    }

    public void delete() {

    }

    //学生：给推荐人提交简历
    public void commitResume() {
        Integer   _id       = getParaToInt("_id");
        Integer   resume_id = getParaToInt("resume_id");
        UserPlanR planR     = new UserPlanR();
        planR.setId(_id);
        planR.setResumeId(resume_id);
        planR.setApplyStatus(Constant.APPLY_STATUS.COMMIT);
        boolean update = planR.update();
        if (update) {
            renderSuccess();
        } else {
            renderError();
        }
    }

    //学生：查询所有已提交简历的计划
    public void listCommit() {
        String  user_id = getPara("user_id");
        boolean isNull  = ValidateUtils.validatePara(user_id);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
            return;
        }

        List<Plan> planRList = Plan.dao.find("select r._id,r.resume_id,r.`plan_already_hour`,r.`plan_already_score`,r.apply_msg,r.`apply_status`,p.*" +
                "from t_user_plan_r r left join t_plan p " +
                "on r.`plan_id` = p.`plan_id` " +
                "where r.`user_id`=? and r.apply_status !=0; ", user_id);
        for (Plan plan : planRList) {
            //通过tmp查出：公司名，职位名，
            JobTemplate jobTemplate = JobTemplate.dao.findFirst("select * from t_job_template where tmp_id = ? ", plan.getJobTemplateId());
            plan.put("job", jobTemplate);
            //查公司名
            User user = User.dao.findFirst("select * from t_user where user_id = ?", jobTemplate.getCreateBy());
            //
            UserCompanyR company = UserCompanyR.dao.findFirst("select * from t_user_company_r where company_id = ?", user.getUserCompanyId());
            plan.put("company", company);
        }
        renderSuccess(planRList);

    }

    //学生：查询学生已学的所有计划
    public void listStu() {
        String  user_id = getPara("user_id");
        boolean isNull  = ValidateUtils.validatePara(user_id);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
            return;
        }
        //查询学生已学的所有计划
        List<UserPlanR> planRList = UserPlanR.dao.findPlanByUserId(user_id, String.valueOf(Constant.APPLY_STATUS.UN_COMMIT));
        for (UserPlanR r : planRList) {
            //查出此培养计划下的课程的学习情况
            List<Clazz> clazzList = Clazz.dao.find("select c.*,r._id,r.`clazz_grade`,r.`clazz_status` " +
                    "from `t_clazz` c left join `t_user_plan_clazz_r` r " +
                    "on c.`clazz_id` = r.`clazz_id` " +
                    "where r.`user_id`=? and r.`plan_id`=? ", user_id, r.getPlanId());
            r.put("clazz", clazzList);
        }
        renderSuccess(planRList);

    }


    //推荐人：查出推荐人下所有的学生信息
    //需要返回：
    //1.学生简历信息
    //2.培养计划信息
    //3.招聘ID
    public void listReferrerSubStu() {
        String plan_id = getPara("plan_id");
        //1.查出所有的投递信息
        List<UserPlanR> userPlanRList = UserPlanR.dao.find("select * from t_user_plan_r where plan_id = ? and apply_status = 1", plan_id);
        for (UserPlanR r : userPlanRList) {
            //2.根据resumeId 找出简历信息
            Resume resume = Resume.dao.findById(r.getResumeId());
            resume.setResumeImg(IPUtils.getUploadPath() + resume.getResumeImg());
            List<ResumeExper> experList = ResumeExper.dao.findByResumeId(r.getResumeId());
            resume.put("experList", experList);
            r.put("resume", resume);
        }
        renderSuccess(userPlanRList);
    }


    //推荐人：处理简历信息
    public void referrerDoResume() {
        String    _id          = getPara("_id");
        String    apply_status = getPara("apply_status");
        String    apply_msg    = getPara("apply_msg");
        UserPlanR userPlanR    = new UserPlanR();
        userPlanR.setId(Integer.valueOf(_id));
        userPlanR.setApplyStatus(Integer.valueOf(apply_status));
        userPlanR.setApplyMsg(apply_msg);
        userPlanR.setUpdateAt(new Date());
        boolean update = userPlanR.update();
        if (update) {
            renderSuccess();
        } else {
            renderError();
        }
    }


    //HR:查询他名下的学生
    public void hrListStu() {
        String user_company_id = getPara("user_company_id");
        //1.查推荐人
        List<User> referrerList = User.dao.find("select u.*,c.company_name,c.company_addr,c.company_contact from t_user u left join t_user_company_r c on u.user_company_id = c.company_id" +
                " where user_company_id = ? and user_role_id = 2", user_company_id);
        List<UserPlanR> result = new ArrayList<UserPlanR>();
        for (User user : referrerList) {
            //2.查推荐人创建的培养计划
            List<Plan> planList = Plan.dao.find("select * from t_plan where create_by = ?", user.getUserId());
            //3.查选了培养计划的学生
            for (Plan plan : planList) {
                List<UserPlanR> userPlanRList = UserPlanR.dao.find("select * from t_user_plan_r where plan_id = ? and apply_status = 3 ", plan.getPlanId());
                for (UserPlanR r : userPlanRList) {
                    //2.根据resumeId 找出简历信息
                    Resume resume = Resume.dao.findById(r.getResumeId());
                    resume.setResumeImg(IPUtils.getUploadPath() + resume.getResumeImg());
                    List<ResumeExper> experList = ResumeExper.dao.findByResumeId(r.getResumeId());
                    resume.put("experList", experList);
                    r.put("resume", resume);
                }
                result.addAll(userPlanRList);
            }
        }
        renderSuccess(result);
    }

    //HR:查询他名下的学生
    public void hrListStuHistory() {
        String user_company_id = getPara("user_company_id");
        //1.查推荐人
        List<User> referrerList = User.dao.find("select u.*,c.company_name,c.company_addr,c.company_contact from t_user u left join t_user_company_r c on u.user_company_id = c.company_id" +
                " where user_company_id = ? and user_role_id = 2", user_company_id);
        List<UserPlanR> result = new ArrayList<UserPlanR>();
        for (User user : referrerList) {
            //2.查推荐人创建的培养计划
            List<Plan> planList = Plan.dao.find("select * from t_plan where create_by = ?", user.getUserId());
            //3.查选了培养计划的学生
            for (Plan plan : planList) {
                List<UserPlanR> userPlanRList = UserPlanR.dao.find("select * from t_user_plan_r where plan_id = ? and apply_status= 4 or apply_status=5", plan.getPlanId());
                for (UserPlanR r : userPlanRList) {
                    //2.根据resumeId 找出简历信息
                    Resume resume = Resume.dao.findById(r.getResumeId());
                    resume.setResumeImg(IPUtils.getUploadPath() + resume.getResumeImg());
                    List<ResumeExper> experList = ResumeExper.dao.findByResumeId(r.getResumeId());
                    resume.put("experList", experList);
                    r.put("resume", resume);
                }
                result.addAll(userPlanRList);
            }
        }
        renderSuccess(result);
    }
    //HR:模糊查询查询他名下的学生
    @Deprecated
    public void hrQueryStu() {
        String user_company_id = getPara("user_company_id");
        //1.查推荐人
        List<User> referrerList = User.dao.find("select u.*,c.company_name,c.company_addr,c.company_contact from t_user u left join t_user_company_r c on u.user_company_id = c.company_id" +
                " where user_company_id = ? and user_role_id = 2", user_company_id);
        List<UserPlanR> result = new ArrayList<UserPlanR>();
        for (User user : referrerList) {
            //2.查推荐人创建的培养计划
            List<Plan> planList = Plan.dao.find("select * from t_plan where create_by = ?", user.getUserId());
            //3.查选了培养计划的学生
            for (Plan plan : planList) {
                List<UserPlanR> userPlanRList = UserPlanR.dao.find("select * from t_user_plan_r where plan_id = ? and apply_status= 4 or apply_status=5", plan.getPlanId());
                for (UserPlanR r : userPlanRList) {
                    //2.根据resumeId 找出简历信息
                    Resume resume = Resume.dao.findById(r.getResumeId());
                    resume.setResumeImg(IPUtils.getUploadPath() + resume.getResumeImg());
                    List<ResumeExper> experList = ResumeExper.dao.findByResumeId(r.getResumeId());
                    resume.put("experList", experList);
                    r.put("resume", resume);
                }
                result.addAll(userPlanRList);
            }
        }
        renderSuccess(result);
    }
}
