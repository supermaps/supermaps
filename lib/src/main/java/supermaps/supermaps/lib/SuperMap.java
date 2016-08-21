package supermaps.supermaps.lib;

import android.app.Fragment;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maximilianalexander on 5/7/16.
 */
public class SuperMap extends Fragment implements AnnotationViewWrapper.TouchAction, OnMapReadyCallback {

    AnnotationViewWrapper annotationViewWrapper;
    private MapFragment superMapGoogleMapFragment;
    private View rootView;

    Rect superMapsFrameLayoutRect;

    /**
     * need to figure out y this is not being used.
     * This is supposed to tell us if the annotation view is part of the currently visible
     * area on the screen.
     */
    private LatLngBounds latLngBounds;

    private GoogleMap googleMap;

    /**
     * Interface object for the renderer.
     * This allows the implementor to get callbacks from the manager
     */
    private MapRenderer mapRenderer;

    /**
     *
     * String reuseID:
     * @see AnnotationView#reuseId
     * List of annotation views that are used for reuse when required.
     */
    private Map<String, List<AnnotationView>> mapReuseIdToAnnotationViewsQueue;

    Map<Annotation, AnnotationView> annotationToAnnotationViewMap;

    public void setCenterLatLng(LatLng latLng, Boolean animated){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.superMapsFrameLayoutRect = new Rect();

        this.annotationToAnnotationViewMap = new HashMap<>();
        this.mapReuseIdToAnnotationViewsQueue = new HashMap<>();
    }

    @Override
    public void onResume() {
        super.onResume();
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
    public void addAnnotations(Annotation[] annotations) {

        for (Annotation annotation :
                annotations) {
            this.annotationToAnnotationViewMap.put(annotation, null);

        }
        this.update();
    }

    /**
     * add a single annotation to the map is a private method
     * @param annotation
     */
    public void addAnnotation(Annotation annotation) {
        this.addAnnotations(new Annotation[]{annotation});

    }

    /**
     * This removes an annotation if it exists from the map.
     * @param annotations
     */
    void removeAnnotations(Annotation[] annotations){

        for (Annotation annotation :
                annotations) {
            this.annotationToAnnotationViewMap.remove(annotation);
        }

        this.update();
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
        return this.viewForAnnotation(annotation);
    }

    /**
     * This method gives you any annotation that is available to be reused.
     * It'll return null if no annotationView is available
     * @param reuseId the Reuseable AnnotationView Id associated with the Annotation View that you
     *                want back.
     * @return a reusable AnnotationView, If it's null, then no AnnotationView is available to be reused.
     */
    public AnnotationView dequeueAnnotationViewWithReuseId(String reuseId){
        return this.dequeueReusableAnnotationViewWithIdentifier(reuseId);
    }

    /**
     * Visible AnnotationViews in the current LatLng Bounds of the Map.
     * @return A List of AnnotationViews that have overlapping frames with the map.
     */
    private List<AnnotationView> getVisibleAnnotationViews(){

        List<AnnotationView> visibleAnnotationViewslist = new ArrayList<>();

        for (Annotation annotation :
                this.getCurrentlyVisibleAnnotations()) {
            visibleAnnotationViewslist.add(this.annotationToAnnotationViewMap.get(annotation));
        }

        return visibleAnnotationViewslist;
    }

    @Override
    public void onTouch(MotionEvent event) {
        this.update();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.annotationViewWrapper = (AnnotationViewWrapper) this.getView().findViewById(R.id.touchableWrapper);
        this.googleMap = googleMap;

        this.annotationViewWrapper.setmTouchAction(this);

        this.setMapRenderer(this.mapRenderer);

        this.update();
    }

    public void setMapRenderer(MapRenderer mapRenderer) {
        this.mapRenderer = mapRenderer;
    }

    /**
     * Helper method for the user so they can access the map
     *
     * @param annotation
     * @return
     */
    AnnotationView viewForAnnotation(Annotation annotation){
        return this.annotationToAnnotationViewMap.get(annotation);
    }

    /**
     * Helper method for the user so they can access the map
     *
     * @return
     */
    public List<Annotation> getCurrentlyVisibleAnnotations() {

        List<Annotation> currentVisibleAnnotations = new ArrayList<>();

        for(int childCountIndex = 0;
            childCountIndex < this.annotationViewWrapper.getChildCount();
            childCountIndex++) {

            AnnotationView annotationView = (AnnotationView) this.annotationViewWrapper.getChildAt(childCountIndex);

            if(annotationView.getLocalVisibleRect(superMapsFrameLayoutRect)) {
                currentVisibleAnnotations.add(annotationView.getAnnotation());

            }
        }

        return currentVisibleAnnotations;
    }

    /**
     * @param reuseId
     * @return
     */
    @Nullable
    public AnnotationView dequeueReusableAnnotationViewWithIdentifier(String reuseId) {

        List<AnnotationView> annotationViewList = this.mapReuseIdToAnnotationViewsQueue.get(reuseId);

        AnnotationView annotationView;

        if(annotationViewList != null) {
            annotationView = annotationViewList.size() > 0 ? annotationViewList.get(0) : null;
        } else {
            return null;

        }

        if(annotationView != null) {
            /**
             * the prepare for reuse method is a stub that the user should implement if necessary.
             *
             * @see AnnotationView#prepareForReuse()
             */
            annotationView.prepareForReuse();
            annotationViewList.remove(annotationView);
            annotationView.clearAnnotationContext();
        }

        return annotationView;
    }

    /**
     * called by the update method when trying to recycle views for a reuseid
     * @param annotationView
     */
    private void enqueueReusableAnnotationViewWithIdentifier(AnnotationView annotationView) {

        if(annotationView == null) {
            return;

        }

        if(this.mapReuseIdToAnnotationViewsQueue.containsKey(annotationView.getReuseId())) {

            //The mapReuseIdToAnnotationViewsQueue.get(annotationView.getReuseId()) returns a list.
            this.mapReuseIdToAnnotationViewsQueue.get(annotationView.getReuseId())
                    .add(annotationView);

        } else {
            //Create the list to id map value
            List<AnnotationView> annotationViewList = new ArrayList<>();
            annotationViewList.add(annotationView);

            this.mapReuseIdToAnnotationViewsQueue.put(annotationView.getReuseId(), annotationViewList);


        }

    }

    /**
     * <p>
     * method for cycling and processing what has changed.
     *
     * <b>
     *     called on remove, add, move(user moves).
     * </b>
     * </p>
     */
    void update() {

        /**
         * The map is not ready yet, SuperMap Fragment is waiting for the callback from
         * GoogleMap.getMapAsync();
         */
        if (this.googleMap == null) {
            return;
        }

        /**
         * Getting the latlongBounds from the map. This is the maps latlngbounds.
         * We need to get the View system conversion of the latlng.
         */
        Projection currentProjection = this.googleMap.getProjection();
        this.latLngBounds = currentProjection.getVisibleRegion().latLngBounds;

        /**
         * Purpose is to loop through all the annotations and make the necessary changes based on:
         * 1) latlong bounds contain an annotation && View is on screen move it
         * 2) latlong bounds contains an annotation but view doesnt exist - GET VIEW!!!!
         * 3) latlong bounds doesnt contain do nothing
         * 4) latlong bounds doesnt contain annotation && View has moved off screen now. - recycle
         */
        for (Annotation annotation :
            this.annotationToAnnotationViewMap.keySet()) {

            /**
             * 1) the view is visible, and we need to make sure that the view is visible and available.
             * 2) enqueue the view so we have a list of views associated with the annotation type
             * 3)
             */
            AnnotationView annotationView = this.annotationToAnnotationViewMap.get(annotation);

            /**
             * checking to make sure the annontation has a view associated with it.
             * Try and make sure if the view is null the annotation view entry is removed
             * from the map
             */
            if(annotationView == null) {
                //reuse/create or ask for view.
                annotationView = this.mapRenderer.viewForAnnotation(annotation);

                //Annotation View will not be null at this point!
                if(annotationView == null) {
                    throw new NullPointerException("Can not return a null annotation view from viewForAnnotation. The user must implement the method");

                }

                /**
                 * Maps the annotation to the annotation View for future use when we need a view
                 * for this annotation
                 */
                this.annotationToAnnotationViewMap.put(annotation, annotationView);

                /**
                 *  Check if the touchable wrapper is the parent of the current view we have received
                 */
                if(annotationView.getParent() != this.annotationViewWrapper) {
                    this.annotationViewWrapper.addView(annotationView);
                }
            }

            /**
             * check if this view is within the bounds of the screen else enqueue it
             */
            this.annotationViewWrapper.getHitRect(superMapsFrameLayoutRect);

            if(superMapsFrameLayoutRect == null) {
                throw new NullPointerException("supermaps FrameLayout Rect is null");
            }

            if (annotationView.getLocalVisibleRect(superMapsFrameLayoutRect)) {
                //View is visible even if its 1px
                Point currentPoint = currentProjection.toScreenLocation(annotation.getLatLng());

                annotationView.setCenter(currentPoint);
            } else {
                //Enqueue the view with the reuse ID
                this.enqueueReusableAnnotationViewWithIdentifier(annotationView);
            }
        }
    }


}
