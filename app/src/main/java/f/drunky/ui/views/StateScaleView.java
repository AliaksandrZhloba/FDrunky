package f.drunky.ui.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;


public class StateScaleView extends View {
    private static final int LINE_WIDTH = 6;
    private static final int LINE_WIDTH_DIV2 = 6;
    private static final double HILL_PART = 2.0/5.0;

    private static final int STATES_LINE_WIDTH = 4;

    private Path _backPath;
    private int _width;
    private int _height;
    private Paint _backPaint;

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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        _width = getWidth();
        _height = getHeight();

        _backPath = new Path();
        _backPath.moveTo(LINE_WIDTH_DIV2, _height - LINE_WIDTH_DIV2);
        _backPath.lineTo(LINE_WIDTH_DIV2, (int)(_height * HILL_PART + LINE_WIDTH_DIV2));
        _backPath.lineTo(_width / 2, LINE_WIDTH_DIV2);
        _backPath.lineTo(_width - LINE_WIDTH_DIV2, (int)(_height * HILL_PART + LINE_WIDTH_DIV2));
        _backPath.lineTo(_width - LINE_WIDTH_DIV2, _height - LINE_WIDTH_DIV2);
        _backPath.close();

        _backPaint = new Paint();
        _backPaint.setAntiAlias(true);
        _backPaint.setColor(Color.parseColor("#4A148C"));
        _backPaint.setStyle(Paint.Style.FILL);
        _backPaint.setStrokeJoin(Paint.Join.ROUND);
        _backPaint.setStrokeWidth(LINE_WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(_backPath, _backPaint);

        Paint statePaint = new Paint();
        statePaint.setShader(new LinearGradient(0, 0, _width, getHeight(), Color.parseColor("#FF80AB"), Color.parseColor("#C51162"), Shader.TileMode.MIRROR));

        int x = _state * _width / 100;
        Path statePath;
        if (_state > 0) {
            if (_state <= 50) {
                int y = (int) (_height * HILL_PART * (50 - _state) / 50);

                statePath = new Path();
                statePath.moveTo(LINE_WIDTH_DIV2, _height - LINE_WIDTH_DIV2);
                statePath.lineTo(LINE_WIDTH_DIV2, (int)(_height * HILL_PART + LINE_WIDTH_DIV2));
                statePath.lineTo(x, y + LINE_WIDTH_DIV2);
                statePath.lineTo(x, _height - LINE_WIDTH_DIV2);
                statePath.close();
            } else {
                int y = (int) (_height * HILL_PART * (_state - 50) / 50);

                statePath = new Path();
                statePath.moveTo(LINE_WIDTH_DIV2, _height - LINE_WIDTH_DIV2);
                statePath.lineTo(LINE_WIDTH_DIV2, (int)(_height * HILL_PART + LINE_WIDTH_DIV2));
                statePath.lineTo(_width / 2, LINE_WIDTH_DIV2);
                statePath.lineTo(x, y + LINE_WIDTH_DIV2);
                statePath.lineTo(x, _height - LINE_WIDTH_DIV2);
                statePath.close();
            }

            canvas.drawPath(statePath, statePaint);
        }


        Paint relaxPaint = _state < 25 ? _backPaint : statePaint;
        relaxPaint.setStrokeWidth(STATES_LINE_WIDTH);
        canvas.drawLine(_width / 4, _height - STATES_LINE_WIDTH, _width / 4, STATES_LINE_WIDTH, relaxPaint);
        canvas.drawLine(_width / 4, STATES_LINE_WIDTH, STATES_LINE_WIDTH, STATES_LINE_WIDTH, relaxPaint);

        Paint leglessPaint = _state < 75 ? _backPaint : statePaint;
        relaxPaint.setStrokeWidth(STATES_LINE_WIDTH);
        canvas.drawLine(_width * 3/4, _height - STATES_LINE_WIDTH, _width * 3/4, STATES_LINE_WIDTH, leglessPaint);
        canvas.drawLine(_width * 3/4, STATES_LINE_WIDTH, _width - STATES_LINE_WIDTH, STATES_LINE_WIDTH, leglessPaint);
    }
}
