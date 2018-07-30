package org.trustnote.activity.common.utils;

import java.util.Arrays;
import java.util.List;

public class StatesUtils {
    public static String getStates(final int code) {
        final String[] content = {""};
        final List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        final List<String> stringList = Arrays.asList("未支付", "余额不足", "待发币", "已完成", "待确认", "转账失败", "推送消息失败", "小于0.01");
        integerList.stream()
                .forEach(integer -> {
                    if (integer == code) {
                        content[0] = stringList.get(code - 1);
                    }
                });
        return content[0];
    }
}
