package supermaps.supermap.AnnotationViews;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import supermaps.supermap.R;
import supermaps.supermaps.lib.Annotation;
import supermaps.supermaps.lib.AnnotationView;
import supermaps.supermaps.lib.SuperMap;

/**
 * Created by ariochdivij666 on 8/24/16.
 */
public class BoatAnnotationView extends AnnotationView {

    private Annotation annotation;

    public BoatAnnotationView(Context context) {
        super(context);
        this.init();
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

    private void init() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.smallboat);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(100, 100);
        imageView.setLayoutParams(layoutParams);

        this.addView(imageView);
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
