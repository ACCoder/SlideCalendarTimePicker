package net.angrycode.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import net.angrycode.appdemo.R;

import java.io.File;

/**
 * Created by lancelot on 15/5/24.
 */
public class AppUtils {

    private static final String TAG = "AppUtils";

    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            Log.e(TAG, "Cannot find package and its version info.");
            return -1;
        }
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(
                    context.getPackageName() , 0).versionName;
        } catch (Exception e) {
            Log.e(TAG, "Cannot find package and its version info.");
            return "no version name";
        }
    }
    /**
     * 获取当前正在运行的Activity
     * @return
     * 		<uses-permission android:name="android.permission.GET_TASKS"/>
     */
    public static String getActivityName(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);
        String shortClassName = info.topActivity.getShortClassName();
        System.out.println("shortClassName=" + shortClassName);
        return shortClassName;
    }

    /**
     * 安装指定文件路径的apk文件
     * @param path
     */
    public static void installApk(Context context,String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File(path)),
                "application/vnd.android.package-archive");
        context.startActivity(intent); // 安装新版本
    }
    /**
     * 创建桌面快捷方式
     * @param resId  应用图标
     * 	<uses-permission android:name="com.android.launcher.permission.INSTALL_SHORTCUT" />
     */
    public static void createShortcut(Context context,int resId) {
        Intent shortcut = new Intent(
                "com.android.launcher.action.INSTALL_SHORTCUT");
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
                context.getString(R.string.app_name));
        shortcut.putExtra("duplicate", false);
        ComponentName comp = new ComponentName(context.getPackageName(), "."
                + ((Activity) context).getLocalClassName());
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(
                Intent.ACTION_MAIN).setComponent(comp));
        Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(context, resId);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
        context.sendBroadcast(shortcut);
    }
    /**
     * 获取一个应用程序的签名信息
     * @param pkgname 应用程序的包名
     * @return
     */
    public static String getSignature(Context context,String pkgname) {
        boolean isEmpty = TextUtils.isEmpty(pkgname);
        if (isEmpty) {
            return null;
        } else {
            try {
                PackageManager manager = context.getPackageManager();
                PackageInfo packageInfo = manager.getPackageInfo(pkgname, PackageManager.GET_SIGNATURES);
                Signature[] signatures = packageInfo.signatures;
                StringBuilder builder = new StringBuilder();
                for (Signature signature : signatures) {
                    builder.append(signature.toCharsString());
                }
                String signature = builder.toString();
                return signature;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
