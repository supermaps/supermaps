package supermaps.supermap;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import java.util.List;

import supermaps.supermaps.lib.Annotation;
import supermaps.supermaps.lib.AnnotationView;
import supermaps.supermaps.lib.MapRenderer;
import supermaps.supermaps.lib.SuperMap;

/**
 * TODO Figure out the reason y latlng bounds are not being used in the lib.
 */
public class MainActivity extends AppCompatActivity implements MapRenderer {

    private SuperMap mSuperMap;
    private List<SuperMapDemoAnnotation> superMapDemoAnnotationList;

    private static final String REUSER_ID1 = "demoResuseId1";
    private static final String REUSER_ID2 = "demoResuseId2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SuperMapDemoAnnotation superMapDemoAnnotation = new SuperMapDemoAnnotation();

        mSuperMap = (SuperMap) this.getFragmentManager().findFragmentById(R.id.superMapFragment);
        mSuperMap.addAnnotation(superMapDemoAnnotation);

        mSuperMap.setMapRenderer(this);

    }

    @Override
    public AnnotationView viewForAnnotation(Annotation annotation) {

        /**
         * @see AnnotationView is the view that is returned.
         * @see SuperMap#dequeueReusableAnnotationViewWithIdentifier(String)
         * IT MAY RETURN NULL
         */
        AnnotationView annotationView = mSuperMap.dequeueReusableAnnotationViewWithIdentifier(MainActivity.REUSER_ID1);
        if (annotationView == null) {
            annotationView = new SuperMapDemoAnnotationView(this);
            annotationView.setAnnotation(annotation);
            annotationView.setReuseId(MainActivity.REUSER_ID1);

            annotationView.setMinimumHeight(100);
            annotationView.setMinimumWidth(100);
            annotationView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));

            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    (int) getResources().getDimension(R.dimen.activity_horizontal_margin),
                    (int) getResources().getDimension(R.dimen.activity_horizontal_margin));

            annotationView.setLayoutParams(layoutParams);

        } else {
            annotationView.setAnnotation(annotation);
        }

        return annotationView;
    }

    @Override
    public void annotationViewWillBeRemoved(AnnotationView annotationView) {

    }

    @Override
    public void annotationViewDidRemove(AnnotationView annotationView) {

    }

    @Override
    public void annotationViewWillBeAdded(AnnotationView annotationView) {

    }

    @Override
    public void annotationViewDidAdd(AnnotationView annotationView) {

    }
}
