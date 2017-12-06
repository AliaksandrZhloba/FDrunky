package f.drunky.Helpers;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.TypedValue;

/**
 * Created by AZhloba on 11/2/2017.
 */

public class Utils {
    @ColorInt
    public static int getThemeColor
            (
                    @NonNull final Context context,
                    @AttrRes final int attributeColor
            )
    {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(attributeColor, value, true);
        return value.data;
    }
}
