package win.yulongsun.talents.util;

import java.util.List;


/**
 * Created by yulongsun on 2016/5/5.
 */
public class ValidateUtils {

    public static boolean validatePara(Object... para) {
        boolean isNull = false;
        for (int i = 0; i < para.length; i++) {
            if (para[i] == null || "".equals(para[i]) || "null".equals(para[i])) {
                isNull = true;
            }
        }
        return isNull;
    }

    public static boolean isListEmpty(List list) {
        if (list == null && list.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Object object) {
        if (null == object || "".equals(object)) {
            return true;
        } else {
            return false;
        }
    }
}
