package supermaps.supermaps.lib;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maximilianalexander on 5/7/16.
 */
public class SuperMap extends Fragment implements TouchableWrapper.TouchAction, OnMapReadyCallback {

    GoogleMap googleMap;
    TouchableWrapper touchableWrapper;
    MapViewManager mapViewManager;
    private MapFragment superMapGoogleMapFragment;
    private View rootView;

    public void setCenterLatLng(LatLng latLng, Boolean animated){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.layout, null);

        superMapGoogleMapFragment = (MapFragment) this.getChildFragmentManager().findFragmentById(R.id.superMapGoogleMapFragment);
        superMapGoogleMapFragment.getMapAsync(this);
        return rootView;
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.touchableWrapper = (TouchableWrapper) this.getView().findViewById(R.id.touchableWrapper);
        this.googleMap = googleMap;

        this.touchableWrapper.setmTouchAction(this);
        this.mapViewManager = new MapViewManager(this);
    }
}
