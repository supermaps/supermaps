package supermaps.supermaps.lib;

import android.graphics.Point;
import android.graphics.Rect;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maximilianalexander on 5/16/16.
 * This should not be
 */
class MapViewManager {

    Rect superMapsFrameLayoutRect;
    /**
     *
     */
    private Map<String, AnnotationView> stringStringMap = new HashMap<>();
    private LatLngBounds latLngBounds;

    private GoogleMap googleMap;
    private SuperMap superMapsView;

    /**
     * Interface object for the renderer.
     * This allows the implementor to get callbacks from the manager
     */
    MapRenderer mapRenderer;

    /**
     * Annotations whose latlng are within the bounds.
     */
    List<Annotation> activeAnnotationListOfVisibleAnnotations;

    /**
     *
     * String reuseID:
     * @see AnnotationView#reuseId
     * List of annotation views that are used for reuse when required.
     */
    private Map<String, List<AnnotationView>> mapReuseIdToAnnotationViewsQueue;

    private Map<Annotation, AnnotationView> annotationToAnnotationViewMap = new HashMap<>();

    public MapViewManager(Map<String, AnnotationView> stringStringMap, Map<Annotation,
                        AnnotationView> annotationToAnnotationViewMap,
                          Map<String, List<AnnotationView>> mapReuseIdToAnnotationViewsQueue,
                          SuperMap superMap) {
        this.stringStringMap = stringStringMap;
        this.annotationToAnnotationViewMap = annotationToAnnotationViewMap;
        this.mapReuseIdToAnnotationViewsQueue = mapReuseIdToAnnotationViewsQueue;

        this.superMapsView = superMap;
        this.googleMap = superMap.googleMap;

        this.superMapsFrameLayoutRect = new Rect();
    }

    AnnotationView viewForAnnotation(Annotation annotation){
        return annotationToAnnotationViewMap.get(annotation);
    }

    public List<Annotation> getCurrentlyVisibleAnnotations() {

        List<Annotation> currentVisibleAnnotations = new ArrayList<>();

        for(int childCountIndex = 0;
            childCountIndex < this.superMapsView.frameLayout.getChildCount();
            childCountIndex++) {

            AnnotationView annotationView = (AnnotationView) this.superMapsView.frameLayout.getChildAt(childCountIndex);

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
    private void update() {

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
            MapViewManager.this.annotationToAnnotationViewMap.keySet()) {

            /**
             * 1) the view is visible, and we need to make sure that the view is visible and available.
             * 2) enqueue the view so we have a list of views associated with the annotation type
             * 3)
             */

                /**
                 * checking to make sure the annontation has a view associated with it.
                 * Try and make sure if the view is null the annotation view entry is removed
                 * from the map
                 */
                if(this.annotationToAnnotationViewMap.containsKey(annotation)) {
                    if(this.annotationToAnnotationViewMap.get(annotation) == null) {
                        //create view
                        AnnotationView annotationView = this.mapRenderer.viewForAnnotation(annotation, this);

                        //Annotation View will not be null at this point!
                        if(annotationView == null) {
                            throw new NullPointerException("Can not return a null annotation view from viewForAnnotation. The user must implement the method");

                        }

                        if(annotationView.getParent() == null) {
                            this.superMapsView.frameLayout.addView(annotationView);
                        }

                        Point currentPoint = currentProjection.toScreenLocation(annotation.getLatLng());

                        annotationView.setCenter(currentPoint);

                        /**
                         * check if this view is within the bounds of the screen else enqueue it
                         */
                        this.superMapsView.frameLayout.getHitRect(superMapsFrameLayoutRect);

                        if(annotationView.getLocalVisibleRect(superMapsFrameLayoutRect)){
                            //View is visible even it its 1px
                        } else {
                            //Enqueue the view with the reuse ID
                            this.enqueueReusableAnnotationViewWithIdentifier(annotationView);
                        }

                    }
                }

                /**
                 * lat lng -> viewport system
                 * call for a view
                 */
        }
    }
}
