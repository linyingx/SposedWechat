package linyx.android.sposedwechat;


import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.DexClass;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by linyx on 2017/11/9.
 */

public class WechatXposedHook implements IXposedHookLoadPackage {
    public final String WECHAT_PACKAGE_NAME = "com.tencent.mm";

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {
        if (!lpparam.packageName.equals(WECHAT_PACKAGE_NAME)) {
            return;
        }

        XposedHelpers.findAndHookMethod("android.app.Application", lpparam.classLoader,
                "attach", android.content.Context.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                        XposedBridge.log("findAndHookMethod: " + lpparam.packageName);
                    }
                });

        DexClass[] classes = null;
        ApkFile apkFile = null;
    }
}