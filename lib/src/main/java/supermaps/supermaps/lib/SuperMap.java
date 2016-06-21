package supermaps.supermaps.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maximilianalexander on 5/7/16.
 */
public class SuperMap extends FrameLayout implements TouchableWrapper.TouchAction {

    com.google.android.gms.maps.GoogleMap googleMap;
    TouchableWrapper touchableWrapper;
    MapViewManager mapViewManager;

    public SuperMap(Context context) {
        super(context);
    }

    public SuperMap(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.commonInit(context, attrs);
    }

    public SuperMap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SuperMap(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setCenterLatLng(LatLng latLng, Boolean animated){

    }

    private void commonInit(Context context, AttributeSet attrs) {

        if(this.touchableWrapper == null) {
            this.touchableWrapper = new TouchableWrapper(context, attrs);

        }

        this.touchableWrapper.setmTouchAction(this);
        this.mapViewManager = new MapViewManager(this);

    }

    /**
     * This add an annotation to the map.
     * @param annotations
     */
    void addAnnotations(Annotation[] annotations) {

        for (Annotation annotation :
                annotations) {
            this.mapViewManager.annotationToAnnotationViewMap.put(annotation, null);

        }

        this.mapCycle();
    }

    /**
     * This removes an annotation if it exists from the map.
     * @param annotations
     */
    void removeAnnotations(Annotation[] annotations){

        for (Annotation annotation :
                annotations) {
            this.mapViewManager.annotationToAnnotationViewMap.remove(annotation);
        }

        this.mapCycle();
    }

    /**
     * Gets the view associated with the annotation. If the AnnotationView is null,
     * it doesn't simply mean that the Annotation doesn't exist. It means that
     *
     * @param annotation the annotation that you're looking for. You should give an annotation
     *                   that is already being tracked.
     * @return AnnotationView instance if it exists.
     *
     *
     */
    public AnnotationView getViewForAnnotation(Annotation annotation){
        return this.mapViewManager.viewForAnnotation(annotation);
    }

    /**
     * This method gives you any annotation that is available to be reused.
     * It'll return null if no annotationView is available
     * @param reuseId the Reuseable AnnotationView Id associated with the Annotation View that you
     *                want back.
     * @return a reusable AnnotationView, If it's null, then no AnnotationView is available to be reused.
     */
    public AnnotationView dequeueAnnotationViewWithReuseId(String reuseId){
        return this.mapViewManager.dequeueReusableAnnotationViewWithIdentifier(reuseId);
    }

    /**
     * Visible AnnotationViews in the current LatLng Bounds of the Map.
     * @return A List of AnnotationViews that have overlapping frames with the map.
     */
    private List<AnnotationView> getVisibleAnnotationViews(){

        List<AnnotationView> visibleAnnotationViewslist = new ArrayList<>();

        for (Annotation annotation :
                this.mapViewManager.getCurrentlyVisibleAnnotations()) {
            visibleAnnotationViewslist.add(this.mapViewManager.annotationToAnnotationViewMap.get(annotation));
        }

        return visibleAnnotationViewslist;
    }


    /**
     * Reposition any annotations in the viewport.
     * When any coordinate changes or map bounds change
     */
    void mapCycle(){
        this.mapViewManager.update();
    }

    @Override
    public void onTouch(MotionEvent event) {
        this.mapCycle();

    }
}
