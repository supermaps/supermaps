package supermaps.supermaps.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class AnnotationViewWrapper extends FrameLayout {

    public AnnotationViewWrapper(Context context) {
        super(context);

        this.initialize();
    }

    private void initialize() {
    }

    public AnnotationViewWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initialize();
    }

    public AnnotationViewWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initialize();
    }

    public AnnotationViewWrapper(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.initialize();
    }
}
