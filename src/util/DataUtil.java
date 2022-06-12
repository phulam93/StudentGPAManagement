package util;

import java.util.List;

public class DataUtil {
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static <T> boolean isEmptyCollection(T[] lst) {
        return isNull(lst) || isEmpty(lst);
    }

    public static <T> boolean isEmpty(T[] lst){
        for (int i = 0; i < lst.length; i++) {
            if (!isNull(lst[i])){
                return false;
            }
        }
        return true;
    }
}
