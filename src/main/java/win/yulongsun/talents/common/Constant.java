package win.yulongsun.talents.common;

/**
 * Created by sunyulong on 2016/12/26.
 */
public class Constant {
    public interface APPLY_STATUS {
        //－1:投递失败\n0：未投递\n1：推荐人处理中\n2：已推荐\n3：已谢绝\n4：hr处理中\n5：面试邀请\n
        int ERROR      = -1;
        int UN_COMMIT  = 0;
        int COMMIT     = 1;
        int REFERRERED = 2;
        int HR_REJECT  = 3;
        int HR_DOING   = 4;
        int HR_PASS    = 5;
    }
}
