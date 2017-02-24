package net.anumbrella.menusummary;

import net.anumbrella.menusummary.utils.AppUtils;

/**
 * author：anumbrella
 * Date:17/2/23 下午4:53
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);
    }
}
