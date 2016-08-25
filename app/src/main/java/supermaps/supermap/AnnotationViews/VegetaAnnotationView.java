package supermaps.supermap.AnnotationViews;

import android.content.Context;
import android.util.AttributeSet;

import supermaps.supermaps.lib.Annotation;
import supermaps.supermaps.lib.AnnotationView;

/**
 * Created by ariochdivij666 on 8/25/16.
 */
public class VegetaAnnotationView extends AnnotationView {

    /**
     * fields
     */
    private Annotation annotation;

    /**
     * Constructors
     */
    public VegetaAnnotationView(Context context) {
        super(context);
    }

    public VegetaAnnotationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VegetaAnnotationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VegetaAnnotationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * getters and setters
     */
    @Override
    public Annotation getAnnotation() {
        return this.annotation;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }
}
