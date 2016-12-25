package win.yulongsun.talents.common;

/**
 * Created by sunyulong on 2016/12/26.
 */
public class Constant {
    public interface APPLY_STATUS {
        int UN_COMMIT       = 0;//未投递
        int COMMIT          = 1;//已提交
        int REFERRER_REJECT = 2;//推荐人拒绝
        int REFERRER_PASS   = 3;//推荐人已推荐
        int HR_REJECT       = 4;//已谢绝
        int HR_PASS         = 5;//面试邀请
    }

}
