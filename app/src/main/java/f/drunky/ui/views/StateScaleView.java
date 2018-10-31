package f.drunky.ui.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;


public class StateScaleView extends View {
    private static final int LINE_WIDTH = 6;
    private static final int LINE_WIDTH_DIV2 = 6;

    private int _state = 0;


    public StateScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setState(int state) {
        ValueAnimator vaQuestion = ValueAnimator.ofInt(_state, state);
        vaQuestion.addUpdateListener(valueAnimator -> {
            _state = (int)valueAnimator.getAnimatedValue();
            invalidate();
        });
        vaQuestion.setDuration(200).start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        Path backPath = new Path();
        backPath.moveTo(LINE_WIDTH_DIV2, height - LINE_WIDTH_DIV2);
        backPath.lineTo(LINE_WIDTH_DIV2, height * 1 / 3 + LINE_WIDTH_DIV2);
        backPath.lineTo(width / 2, LINE_WIDTH_DIV2);
        backPath.lineTo(width - LINE_WIDTH_DIV2, height * 1 / 3 + LINE_WIDTH_DIV2);
        backPath.lineTo(width - LINE_WIDTH_DIV2, height - LINE_WIDTH_DIV2);
        backPath.close();

        Paint backPaint = new Paint();
        backPaint.setAntiAlias(true);
        backPaint.setColor(Color.parseColor("#4A148C"));
        backPaint.setStyle(Paint.Style.FILL);
        backPaint.setStrokeJoin(Paint.Join.ROUND);
        backPaint.setStrokeWidth(LINE_WIDTH);
        canvas.drawPath(backPath, backPaint);

        int x = _state * width / 100;
        Path statePath;
        if (_state > 0) {
            if (_state <= 50) {
                int y = (int) (height / 3d * (50 - _state) / 50);

                statePath = new Path();
                statePath.moveTo(LINE_WIDTH_DIV2, height - LINE_WIDTH_DIV2);
                statePath.lineTo(LINE_WIDTH_DIV2, height * 1 / 3 + LINE_WIDTH_DIV2);
                statePath.lineTo(x, y + LINE_WIDTH_DIV2);
                statePath.lineTo(x, height - LINE_WIDTH_DIV2);
                statePath.close();
            }
            else {
                int y = (int) (height / 3d * (_state - 50) / 50);

                statePath = new Path();
                statePath.moveTo(LINE_WIDTH_DIV2, height - LINE_WIDTH_DIV2);
                statePath.lineTo(LINE_WIDTH_DIV2, height * 1 / 3 + LINE_WIDTH_DIV2);
                statePath.lineTo(width / 2, LINE_WIDTH_DIV2);
                statePath.lineTo(x, y + LINE_WIDTH_DIV2);
                statePath.lineTo(x, height - LINE_WIDTH_DIV2);
                statePath.close();
            }

            Paint statePaint = new Paint();
            statePaint.setAntiAlias(true);
            statePaint.setColor(Color.parseColor("#FF80AB"));
            statePaint.setStyle(Paint.Style.FILL);
            statePaint.setStrokeJoin(Paint.Join.ROUND);
            statePaint.setStrokeWidth(LINE_WIDTH);
            canvas.drawPath(statePath, statePaint);
        }
    }
}
