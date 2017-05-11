package win.yulongsun.talents.controller;

import win.yulongsun.talents.common.BaseController;
import win.yulongsun.talents.common.Response;
import win.yulongsun.talents.model.Msg;
import win.yulongsun.talents.util.GTMsgUtils;
import win.yulongsun.talents.util.ValidateUtils;

import java.util.Date;

/**
 * Created by sunyulong on 2016/12/20.
 */
public class MsgController extends BaseController {
    public void add() {

    }

    public void list() {

    }

    public void update() {

    }

    public void delete() {

    }

    //推送单条消息
    public void pushMsgSingle() {
        Integer msg_from_id = getParaToInt("msg_from_id");
        Integer msg_to_id   = getParaToInt("msg_to_id");
        String  msg_title   = getPara("msg_title");
        String  msg_content = getPara("msg_content");
        String  msg_type    = getPara("msg_type");
        boolean isNull      = ValidateUtils.validatePara(msg_from_id, msg_to_id, msg_title, msg_content);
        if (isNull) {
            renderError(Response.MSG.REQ_IS_NULL);
        }
        Msg msg = new Msg();
        msg.setMsgFromId(msg_from_id);
        msg.setMsgToId(msg_to_id);
        msg.setMsgTitle(msg_title);
        msg.setMsgContent(msg_content);
        msg.setMsgType(msg_type);
        msg.setCreateAt(new Date());
        GTMsgUtils.PushResult pushResult = GTMsgUtils.pushMsgToSingle(msg);
        if (pushResult.success) {
            renderSuccess();
        } else {
            renderError();
        }

    }
}
