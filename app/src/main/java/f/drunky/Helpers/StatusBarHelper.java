package f.drunky.Helpers;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.Window;


/**
 * Created by AZhloba on 10/3/2017.
 */

public class StatusBarHelper {
    public static void setStatusBarColorWithAnimation(Activity activity, final int toColor, int msDuration) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final Window window = activity.getWindow();
            final int fromColor = window.getStatusBarColor();

            ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // Use animation position to blend colors.
                    float position = animation.getAnimatedFraction();

                    // Apply blended color to the status bar.
                    int blended = blendColors(fromColor, toColor, position);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(blended);
                    }
                }
            });

            anim.setDuration(msDuration).start();
        }
    }

    private static int blendColors(int from, int to, float ratio) {
        final float inverseRatio = 1f - ratio;

        final float r = Color.red(to) * ratio + Color.red(from) * inverseRatio;
        final float g = Color.green(to) * ratio + Color.green(from) * inverseRatio;
        final float b = Color.blue(to) * ratio + Color.blue(from) * inverseRatio;

        return Color.rgb((int) r, (int) g, (int) b);
    }
}
