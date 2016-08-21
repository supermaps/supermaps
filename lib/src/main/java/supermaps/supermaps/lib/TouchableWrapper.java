package supermaps.supermaps.lib;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class TouchableWrapper extends FrameLayout {

    public interface TouchAction {
        void onTouch(MotionEvent event);
    }

    private TouchAction mTouchAction;

    public void setmTouchAction(TouchAction mTouchAction) {
        this.mTouchAction = mTouchAction;
    }

    public TouchableWrapper(Context context) {
        super(context);

        this.initialize();
    }

    private void initialize() {
    }


    public TouchableWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initialize();
    }

    public TouchableWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initialize();
    }

    public TouchableWrapper(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.initialize();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if(motionEvent == null) { return false; }

        Log.d("TouchableWeapperLogs", "any");
        this.mTouchAction.onTouch(motionEvent);

        switch (MotionEventCompat.getActionMasked(motionEvent)){
            case MotionEvent.ACTION_MOVE:
                Log.d("TouchableWeapperLogs", "MOVE");
                this.mTouchAction.onTouch(motionEvent);
                break;
            case MotionEvent.ACTION_DOWN:
                Log.d("TouchableWeapperLogs", "DOWN");
                this.mTouchAction.onTouch(motionEvent);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TouchableWeapperLogs", "UP");
                this.mTouchAction.onTouch(motionEvent);
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
