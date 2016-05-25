package supermaps.supermaps.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.Map;

/**
 * Created by maximilianalexander on 5/7/16.
 */
public class MapView extends FrameLayout {

    public com.google.android.gms.maps.MapView googleMapView;
    public FrameLayout frameLayout;


    public MapView(Context context) {
        super(context);
    }

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MapView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setCenterLatLng(LatLng latLng, Boolean animated){

    }
    /**
     * This add an annotation to the map.
     * @param annotation
     */
    void addAnnotation(Annotation annotation) {

    }

    /**
     * This removes an annotation if it exists from the map.
     * @param annotation
     */
    void removeAnnotation(Annotation annotation){

    }

    /**
     * Gets the view associated with the annotation. If the AnnotationView is null,
     * it doesn't simply mean that the Annotation doesn't exist. It means that
     *
     * @param annotation the annotation that you're looking for. You should give an annotation
     *                   that is already being tracked.
     * @return AnnotationView instance if it exists.
     */
    AnnotationView getViewForAnnotation(Annotation annotation){
        return null;
    }

    /**
     * This method gives you any annotation that is available to be reused.
     * It'll return null if no annotationView is available
     * @param reuseId the Reuseable AnnotationView Id associated with the Annotation View that you
     *                want back.
     * @return a reusable AnnotationView, If it's null, then no AnnotationView is available to be reused.
     */
    AnnotationView dequeueAnnotationViewWithReuseId(String reuseId){
        return null;
    }

    /**
     * Visible AnnotationViews in the current LatLng Bounds of the Map.
     * @return A List of AnnotationViews that have overlapping frames with the map.
     */
    private List<AnnotationView> getVisibleAnnotationViews(){
        return null;
    }


    /**
     * Reposition any annotations in the viewport.
     * When any coordinate changes or map bounds change
     */
    void mapCycle(){

    }

}
