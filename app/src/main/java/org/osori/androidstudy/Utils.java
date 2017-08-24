package org.osori.androidstudy;

import android.content.Intent;
import android.text.TextUtils;

/**
 * Created by junsu on 2017-08-24.
 */

public class Utils {

    public Utils() {
        throw new IllegalStateException();
    }

    public static String getFlagInfo(int flag) {
        String flagInfo = "";
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT))
            flagInfo = buildFlagInfo(flagInfo, "brought_to_front");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_CLEAR_TASK))
            flagInfo = buildFlagInfo(flagInfo, "clear_task");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_CLEAR_TOP))
            flagInfo = buildFlagInfo(flagInfo, "clear_top");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS))
            flagInfo = buildFlagInfo(flagInfo, "exclude_from_recents");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_FORWARD_RESULT))
            flagInfo = buildFlagInfo(flagInfo, "forward_result");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT))
            flagInfo = buildFlagInfo(flagInfo, "launch_adjacent");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY))
            flagInfo = buildFlagInfo(flagInfo, "launched_from_history");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_MULTIPLE_TASK))
            flagInfo = buildFlagInfo(flagInfo, "multiple_task");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_NEW_DOCUMENT))
            flagInfo = buildFlagInfo(flagInfo, "new_document");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_NEW_TASK))
            flagInfo = buildFlagInfo(flagInfo, "new_task");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_NO_ANIMATION))
            flagInfo = buildFlagInfo(flagInfo, "no_animation");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_NO_HISTORY))
            flagInfo = buildFlagInfo(flagInfo, "no_history");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_NO_USER_ACTION))
            flagInfo = buildFlagInfo(flagInfo, "no_user_action");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP))
            flagInfo = buildFlagInfo(flagInfo, "previous_is_top");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_REORDER_TO_FRONT))
            flagInfo = buildFlagInfo(flagInfo, "reorder_to_front");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED))
            flagInfo = buildFlagInfo(flagInfo, "reset_task_if_needed");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS))
            flagInfo = buildFlagInfo(flagInfo, "retain_in_recents");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_SINGLE_TOP));
            flagInfo = buildFlagInfo(flagInfo, "single_top");
        if (isFlagOn(flag, Intent.FLAG_ACTIVITY_TASK_ON_HOME))
            flagInfo = buildFlagInfo(flagInfo, "task_on_home");

        return flagInfo;
    }

    private static boolean isFlagOn(int flag, int check) {
        return (flag & check) != 0;
    }

    private static String buildFlagInfo(String flagInfo, String appended) {
        if (TextUtils.isEmpty(flagInfo))
            flagInfo += appended;
        else
            flagInfo = flagInfo + " | " + appended;
        return flagInfo;
    }
}
