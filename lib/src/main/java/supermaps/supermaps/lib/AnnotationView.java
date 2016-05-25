package supermaps.supermaps.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by maximilianalexander on 5/7/16.
 * This should be an abstract class that hosts the view presentable on the map.
 * It will have a reference to the annotation that it represent.
 */
public abstract class AnnotationView extends View {

    public AnnotationView(Context context) {
        super(context);
    }

    public AnnotationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnnotationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AnnotationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    abstract Annotation getAnnotation();
    abstract void setAnnotation(Annotation annotation);

}
