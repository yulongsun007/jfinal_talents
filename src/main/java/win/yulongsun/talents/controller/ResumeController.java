package win.yulongsun.talents.controller;

import com.jfinal.upload.UploadFile;
import win.yulongsun.talents.common.BaseController;
import win.yulongsun.talents.common.Response;
import win.yulongsun.talents.model.Resume;
import win.yulongsun.talents.model.ResumeExper;
import win.yulongsun.talents.util.IPUtils;
import win.yulongsun.talents.util.ValidateUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by sunyulong on 2016/12/10.
 */
public class ResumeController extends BaseController {
    public void add() {
        UploadFile file               = getFile("resume_img");
        String     resume_name        = getPara("resume_name");
        String     resume_desc        = getPara("resume_desc");
        String     resume_gender      = getPara("resume_gender");
        String     resume_academy     = getPara("resume_academy");
        String     resume_is_study    = getPara("resume_is_study");
        String     resume_major       = getPara("resume_major");
        String     resume_graduate_at = getPara("resume_graduate_at");
        String     resume_mobile      = getPara("resume_mobile");
        String     resume_email       = getPara("resume_email");
        Integer    create_by          = getParaToInt("create_by");
        boolean isNull = ValidateUtils.validatePara(resume_name, resume_desc, resume_gender, resume_academy, resume_is_study, resume_major,
                resume_graduate_at, resume_mobile, resume_email, create_by);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        Resume resume = new Resume();
        resume.setResumeImg(file.getFileName());
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
        resume.setCreateAt(new Date());
        resume.setUpdateAt(new Date());
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
        List<Resume> resumes = Resume.dao.find("select * from t_resume where create_by = ? order by resume_id desc", create_by);
        for (Resume r : resumes) {
            r.setResumeImg(IPUtils.getUploadPath() + r.getResumeImg());
            List<ResumeExper> experList = ResumeExper.dao.findByResumeId(r.getResumeId());
            r.put("experList", experList);
        }
        renderSuccess(resumes);
    }

    public void update() {
        String     has_img = getPara("has_img");
        UploadFile file    = null;
        if ("1".equals(has_img)) {
            file = getFile("resume_img");
        }
        Integer resume_id          = getParaToInt("resume_id");
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
        boolean isNull = ValidateUtils.validatePara(resume_id, resume_name, resume_desc, resume_gender, resume_academy, resume_is_study, resume_major,
                resume_graduate_at, resume_mobile, resume_email, create_by);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        Resume resume = new Resume();
        resume.setResumeId(resume_id);
        if (file != null) {
            resume.setResumeImg(file.getFileName());
        }
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
        resume.setUpdateAt(new Date());
        boolean save = resume.update();
        if (save) {
            List<Resume> resumes = Resume.dao.find("select * from t_resume where create_by = ? order by resume_id desc", create_by);
            for (Resume r : resumes) {
                r.setResumeImg(IPUtils.getUploadPath() + r.getResumeImg());
                List<ResumeExper> experList = ResumeExper.dao.findByResumeId(r.getResumeId());
                r.put("experList", experList);
            }
            renderSuccess(resumes);
        } else {
            renderError(Response.MSG.ADD_ERROR);
        }
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
