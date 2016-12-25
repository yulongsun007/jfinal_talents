package win.yulongsun.talents.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by sunyulong on 2016/12/25.
 */
public class IPUtils {

    public static String getIp() {
        try {
            return String.valueOf(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUploadPath() {
        return "http://" + getIp() + ":8081/talents/upload/";
    }

    public static void main(String agrs[]) {
        System.out.printf(getIp());
    }
}
