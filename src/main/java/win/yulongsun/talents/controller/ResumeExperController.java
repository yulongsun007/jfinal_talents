package win.yulongsun.talents.controller;

import win.yulongsun.talents.common.BaseController;
import win.yulongsun.talents.common.Response;
import win.yulongsun.talents.model.ResumeExper;
import win.yulongsun.talents.util.ValidateUtils;

import java.util.List;

/**
 * Created by sunyulong on 2016/12/15.
 * 项目经验
 */
public class ResumeExperController extends BaseController {
    public void add() {
        Integer resume_id      = getParaToInt("resume_id");
        String  exper_time     = getPara("exper_time");
        String  exper_name     = getPara("exper_name");
        String  exper_job      = getPara("exper_job");
        String  exper_job_desc = getPara("exper_job_desc");
        String  exper_desc     = getPara("exper_desc");
        boolean isNull         = ValidateUtils.validatePara(resume_id, exper_desc, exper_job, exper_job_desc, exper_name, exper_time);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        ResumeExper exper = new ResumeExper();
        exper.setResumeId(resume_id);
        exper.setExperName(exper_name);
        exper.setExperJob(exper_job);
        exper.setExperJobDesc(exper_job_desc);
        exper.setExperTime(exper_time);
        exper.setExperDesc(exper_desc);
        boolean save = exper.save();
        if (save) {
            List<ResumeExper> list = ResumeExper.dao.findByResumeId(resume_id);
            renderSuccess(list);
        } else {
            renderError(Response.MSG.ADD_ERROR);
        }
    }

    public void list() {

    }

    public void update() {

    }

    public void delete() {
        Integer resume_id = getParaToInt("resume_id");
        Integer exper_id  = getParaToInt("exper_id");
        boolean isNull    = ValidateUtils.validatePara(exper_id);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        boolean delete = ResumeExper.dao.deleteById(exper_id);
        if (delete) {
            List<ResumeExper> list = ResumeExper.dao.findByResumeId(resume_id);
            renderSuccess(list);
        } else {
            renderError(Response.MSG.DELETE_ERROR);
        }
    }
}
