package supermaps.supermaps.lib;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.maps.MapFragment;

/**
 * Created by maximilianalexander on 11/6/16.
 */

public class TouchMapFragment extends MapFragment {
    public View mOriginalContentView;
    public TouchableWrapper mTouchView;
    private NonConsumingTouchListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        mOriginalContentView = super.onCreateView(inflater, parent, savedInstanceState);
        mTouchView = new TouchableWrapper(getActivity());
        mTouchView.addView(mOriginalContentView);
        return mTouchView;
    }

    @Override
    public View getView() {
        return mOriginalContentView;
    }

    public void setNonConsumingTouchListener(NonConsumingTouchListener listener) {
        mListener = listener;
    }

    public interface NonConsumingTouchListener {
        boolean onTouch(MotionEvent motionEvent);
    }

    public class TouchableWrapper extends FrameLayout {

        public TouchableWrapper(Context context) {
            super(context);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            if (mListener != null) {
                mListener.onTouch(event);
            }
            return super.dispatchTouchEvent(event);
        }
    }
}
