package win.yulongsun.talents.controller;

import win.yulongsun.talents.common.BaseController;
import win.yulongsun.talents.common.Response;
import win.yulongsun.talents.model.Resume;
import win.yulongsun.talents.model.ResumeExper;
import win.yulongsun.talents.util.ValidateUtils;

import java.util.List;

/**
 * Created by sunyulong on 2016/12/10.
 */
public class ResumeController extends BaseController {
    public void add() {
        String  resume_name        = getPara("resume_name");
        String  resume_desc        = getPara("resume_desc");
        String  resume_gender      = getPara("resume_gender");
        String  resume_academy     = getPara("resume_academy");
        String  resume_is_study    = getPara("resume_is_study");
        String  resume_major       = getPara("resume_major");
        String  resume_graduate_at = getPara("resume_graduate_at");
        String  resume_mobile      = getPara("resume_mobile");
        String  resume_email       = getPara("resume_email");
        Integer create_by          = getParaToInt("create_by");
        boolean isNull = ValidateUtils.validatePara(resume_name, resume_desc, resume_gender, resume_academy, resume_is_study, resume_major,
                resume_graduate_at, resume_mobile, resume_email, create_by);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        Resume resume = new Resume();
        resume.setResumeName(resume_name);
        resume.setResumeDesc(resume_desc);
        resume.setResumeGender(resume_gender);
        resume.setResumeAcademy(resume_academy);
        resume.setResumeIsStudy(resume_is_study);
        resume.setResumeMajor(resume_major);
        resume.setResumeGraduateAt(resume_graduate_at);
        resume.setResumeMobile(resume_mobile);
        resume.setResumeEmail(resume_email);
        resume.setCreateBy(create_by);
        boolean save = resume.save();
        if (save) {
            renderSuccess();
        } else {
            renderError(Response.MSG.ADD_ERROR);
        }
    }

    public void list() {
        Integer create_by = getParaToInt("create_by");
        boolean isNull    = ValidateUtils.validatePara(create_by);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        List<Resume> resumes = Resume.dao.find("select * from t_resume where create_by = ?", create_by);
        for (Resume resume : resumes) {
            Integer           resumeId  = resume.getResumeId();
            List<ResumeExper> experList = ResumeExper.dao.find("select * from t_resume_exper where resume_id = ?", resumeId);
            resume.put("experList", experList);
        }
        renderSuccess(resumes);
    }

    public void update() {

    }

    public void delete() {
        Integer resume_id = getParaToInt("resume_id");
        boolean isNull    = ValidateUtils.validatePara(resume_id);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        Resume resume = new Resume();
        resume.setResumeId(resume_id);
        boolean delete = resume.delete();
        if (delete) {
            renderSuccess();
        } else {
            renderError(Response.MSG.DELETE_ERROR);
        }
    }
}
