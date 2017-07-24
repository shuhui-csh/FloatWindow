package csh.com.floatwindow;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by shuhuiChen on  2017/7/23  16:11.
 * Email ：shuhuiCSH@126.com
 */

public class FloatWindowManager {
    private final WindowManager mWindowManager;
    private Context mContext;
    private View mWindowView;

    public FloatWindowManager(Context context) {
        this.mContext = context;
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    public void showFloatWindow() {
        if (mWindowView == null) {
            mWindowView = getWindowView(mContext);
        }

        WindowManager.LayoutParams layoutParams = getWindowLayoutParams();
        boolean isShowing = ViewCompat.isAttachedToWindow(mWindowView);
        if (isShowing) {
            mWindowManager.removeView(mWindowView);
        }

        mWindowManager.addView(mWindowView, layoutParams);
    }

    public void removeFloatWindow() {
        if (mWindowView == null) {
            return;
        }

        boolean isShowing = ViewCompat.isAttachedToWindow(mWindowView);
        if (isShowing) {
            mWindowManager.removeView(mWindowView);
        }
    }

    private WindowManager.LayoutParams getWindowLayoutParams() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_FULLSCREEN;
        params.format = PixelFormat.TRANSLUCENT;
        params.gravity = Gravity.CENTER;

        // 运行的系统版本小于4.4（SDK版本为19以下，toast类型无法处理触摸和点击）
        // 或者大于7.0（SDK版本为24以上，比如7.1.1(25),toast类似会自动消失，无法控制展示时长）
        // 需要在清单文件里申请权限，创建phone类型的悬浮窗
        if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT > 24) {
            params.type = WindowManager.LayoutParams.TYPE_PHONE;
        } else {
            // toast类型不需要在清单文件里申请权限，但是仅能在（19 <= 系统版本 <= 24）的系统版本上
            // 满足使用条件（可接收触摸和点击事件，并且可以控制展示时长）
            params.type = WindowManager.LayoutParams.TYPE_TOAST;
        }

        params.width = 600;
        params.height = 200;
        params.x = 0;
        params.y = 0;

        return params;
    }

    private View getWindowView(Context context) {
        if (context == null) {
            return null;
        }

        LayoutInflater inflater = LayoutInflater.from(context);
        View windowView = inflater.inflate(R.layout.flaot_window_layout, null);
        return windowView;
    }

}
