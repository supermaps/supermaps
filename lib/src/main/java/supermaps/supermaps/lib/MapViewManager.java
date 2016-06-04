package supermaps.supermaps.lib;

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

    /**
     *
     */
    private Map<String, AnnotationView> stringStringMap = new HashMap<>();
    private LatLngBounds latLngBounds;

    private com.google.android.gms.maps.MapView googleMapView;

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
    private Map<String, List<AnnotationView>> mapReuseIdToAnnotationViews;

    private Map<Annotation, AnnotationView> annotationToAnnotationViewMap = new HashMap<>();

    public MapViewManager(Map<String, AnnotationView> stringStringMap, Map<Annotation, AnnotationView> annotationToAnnotationViewMap,Map<String, List<AnnotationView>> mapReuseIdToAnnotationViews) {
        this.stringStringMap = stringStringMap;
        this.annotationToAnnotationViewMap = annotationToAnnotationViewMap;
        this.mapReuseIdToAnnotationViews = mapReuseIdToAnnotationViews;
    }

    AnnotationView viewForAnnotation(Annotation annotation){
        return annotationToAnnotationViewMap.get(annotation);
    }


    /**
     * TODO MapViewManager.dequeueReusableAnnotationViewWithIdentifier(String reuseId) temp need to figure this out
     * @param reuseId
     * @return
     */
    public AnnotationView dequeueReusableAnnotationViewWithIdentifier(String reuseId) {
        return this.mapReuseIdToAnnotationViews.get(reuseId).get(0);
    }

    /**
     * called by the update method when trying to recycle views for a reuseid
     * @param annotationView
     */
    private void enqueueReusableAnnotationViewWithIdentifier(AnnotationView annotationView) {
        if(this.mapReuseIdToAnnotationViews.containsKey(annotationView.getReuseId())) {
            this.mapReuseIdToAnnotationViews.get(annotationView.getReuseId())
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

        if(MapViewManager.this.activeAnnotationListOfVisibleAnnotations == null) {
            MapViewManager.this.activeAnnotationListOfVisibleAnnotations = new ArrayList<>();
        }

        for (Annotation annotation :
            MapViewManager.this.annotationToAnnotationViewMap.keySet()) {

            /**
             * 1) latlong bounds contain an annotation && View is on screen move it
             * 2) latlong bounds contains an annotation but view doesnt exist - GET VIEW!!!!
             * 3) latlong bounds doesnt contain do nothing
             * 4) latlong bounds doesnt contain annotation && View has moved off screen now. - recycle
             */

            /**
             * 1) the view is visible, and we need to make sure that the view is visible and available.
             * 2) enqueue the view so we have a list of views associated with the annotation type
             * 3)
             *
             */
            if(this.latLngBounds.contains(annotation.getLatLng())) {
                //May need to move to somewhere else
                MapViewManager.this.activeAnnotationListOfVisibleAnnotations.add(annotation);

                /**
                 * checking to make sure the annontation has a view associated with it.
                 * Try and make sure if the view is null the annotation view entry is removed
                 * from the map
                 */
                if(this.annotationToAnnotationViewMap.containsKey(annotation)) {
                    if(this.annotationToAnnotationViewMap.get(annotation) == null) {
                        //create view


                    } else {
                        //TODO try to enqueue and dequeue the annotation view.


                    }

                }

                /**
                 * lat lng -> viewport system
                 * call for a view
                 */
            } else {
                //Annotation is not visible enqueue?


            }
        }
    }
}
