package supermaps.supermap;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import supermaps.supermap.AnnotationViews.BoatAnnotationView;
import supermaps.supermap.Annotations.BoatAnnotation;
import supermaps.supermaps.lib.Annotation;
import supermaps.supermaps.lib.AnnotationView;
import supermaps.supermaps.lib.MapRenderer;
import supermaps.supermaps.lib.SuperMap;

/**
 * TODO Figure out the reason y latlng bounds are not being used in the lib.
 */
public class MainActivity extends AppCompatActivity implements MapRenderer {

    private SuperMap mSuperMap;
    private List<Annotation> mAnnotationList;


    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeData();

        mSuperMap = (SuperMap) this.getFragmentManager().findFragmentById(R.id.superMapFragment);

        //Create new annotation
        BoatAnnotation boatAnnotation = new BoatAnnotation();
        boatAnnotation.setLatLng(new LatLng(37.774929, -122.419416));

        //add annotation to map
        mSuperMap.addAnnotation(boatAnnotation);

//        mSuperMap.addAnnotations(this.mAnnotationList.toArray(new Annotation[]{}));

        mSuperMap.setMapRenderer(this);

    }

    private void initializeData() {
        this.mAnnotationList = new ArrayList<>();

    }

    @Override
    public AnnotationView viewForAnnotation(Annotation annotation) {

        AnnotationView annotationView = null;

        if (annotation instanceof BoatAnnotation) {
            annotationView = mSuperMap.dequeueReusableAnnotationViewWithIdentifier(SuperMapsConstants.ReuseIDs.BOAT);
            if (annotationView == null) {
                annotationView = new BoatAnnotationView(this);

            }
        }

        annotationView.setAnnotation(annotation);

        return annotationView;

        /**
         * @see AnnotationView is the view that is returned.
         * @see SuperMap#dequeueReusableAnnotationViewWithIdentifier(String)
         * IT MAY RETURN NULL
         */
//        this.count++;
//
//        annotationView = mSuperMap.dequeueReusableAnnotationViewWithIdentifier(SuperMapsConstants.ReuseIDs.BOAT);
//        if (count % 2 == 0) {
//            if (annotationView == null) {
//                annotationView.setAnnotation(annotation);
//                annotationView.setReuseId(SuperMapsConstants.ReuseIDs.BOAT);
//
//                annotationView.setMinimumHeight(100);
//                annotationView.setMinimumWidth(100);
//
//                annotationView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
//
//                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
//                        (int) getResources().getDimension(R.dimen.activity_horizontal_margin),
//                        (int) getResources().getDimension(R.dimen.activity_horizontal_margin));
//
//                annotationView.setLayoutParams(layoutParams);
//
//            } else {
//                annotationView.setAnnotation(annotation);
//            }
//
//            return annotationView;
//
//        } else {
//            annotationView = mSuperMap.dequeueReusableAnnotationViewWithIdentifier(MainActivity.REUSER_ID2);
//            if (annotationView == null) {
//                annotationView.setAnnotation(annotation);
//                annotationView.setReuseId(MainActivity.REUSER_ID2);
//
//                annotationView.setMinimumHeight(100);
//                annotationView.setMinimumWidth(100);
//
//                annotationView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
//
//                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
//                        (int) getResources().getDimension(R.dimen.activity_horizontal_margin),
//                        (int) getResources().getDimension(R.dimen.activity_horizontal_margin));
//
//                annotationView.setLayoutParams(layoutParams);
//
//            } else {
//                annotationView.setAnnotation(annotation);
//            }
//
//            return annotationView;

//        }
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
