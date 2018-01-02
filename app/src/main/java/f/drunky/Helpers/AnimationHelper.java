package f.drunky.Helpers;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by AZhloba on 12/29/2017.
 */

public class AnimationHelper {
    public static void animateTopMargin(View view, int from, int to, int duration) {
        ViewGroup.MarginLayoutParams lpView = (ViewGroup.MarginLayoutParams)view.getLayoutParams();

        ValueAnimator vaQuestion = ValueAnimator.ofInt(from, to);
        vaQuestion.addUpdateListener(valueAnimator -> {
            lpView.topMargin = (int)valueAnimator.getAnimatedValue();
            view.setLayoutParams(lpView);
        });
        vaQuestion.setDuration(duration).start();
    }
}
