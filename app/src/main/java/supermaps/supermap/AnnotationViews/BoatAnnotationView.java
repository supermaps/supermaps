package supermaps.supermap.AnnotationViews;

import android.content.Context;
import android.util.AttributeSet;

import supermaps.supermaps.lib.Annotation;
import supermaps.supermaps.lib.AnnotationView;

/**
 * Created by ariochdivij666 on 8/24/16.
 */
public class BoatAnnotationView extends AnnotationView {

    private Annotation annotation;

    public BoatAnnotationView(Context context) {
        super(context);
    }

    public BoatAnnotationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BoatAnnotationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BoatAnnotationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public Annotation getAnnotation() {
        return this.annotation;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }
}
