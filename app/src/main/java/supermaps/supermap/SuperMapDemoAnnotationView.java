package supermaps.supermap;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

import supermaps.supermaps.lib.Annotation;
import supermaps.supermaps.lib.AnnotationView;

/**
 * Created by ariochdivij666 on 8/18/16.
 */
public class SuperMapDemoAnnotationView extends AnnotationView {

    SuperMapDemoAnnotation superMapDemoAnnotation;

    public SuperMapDemoAnnotationView(Context context) {
        super(context);
    }

    @Override
    public Annotation getAnnotation() {
        return this.superMapDemoAnnotation;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        this.superMapDemoAnnotation = (SuperMapDemoAnnotation) annotation;
    }
}
