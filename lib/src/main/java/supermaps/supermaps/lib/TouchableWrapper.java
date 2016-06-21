package supermaps.supermaps.lib;

import android.content.Context;
import android.util.AttributeSet;
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
    }

    public TouchableWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchableWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TouchableWrapper(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if(motionEvent == null) { return false; }
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                this.mTouchAction.onTouch(motionEvent);
            case MotionEvent.ACTION_UP:
                this.mTouchAction.onTouch(motionEvent);
            case MotionEvent.ACTION_MOVE:
                this.mTouchAction.onTouch(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
