package supermaps.supermap.AnnotationViews;

import android.content.Context;
import android.util.AttributeSet;

import supermaps.supermaps.lib.Annotation;
import supermaps.supermaps.lib.AnnotationView;

/**
 * Created by ariochdivij666 on 8/24/16.
 */
public class TowerAnnotationView extends AnnotationView {

    private Annotation annotation;

    public TowerAnnotationView(Context context) {
        super(context);
    }

    public TowerAnnotationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TowerAnnotationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TowerAnnotationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    @Override
    public Annotation getAnnotation() {
        return this.annotation;
    }
}
