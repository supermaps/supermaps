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

    /**
     * Annotations whose latlng are within the bounds.
     */
    List<Annotation> activeAnnotationListOfVisibleAnnotations;

    private Map<String, List<AnnotationView>> mapAnnotationTypeEnumToAnnotationViews;

    private Map<Annotation, AnnotationView> annotationToAnnotationViewMap = new HashMap<>();

    public MapViewManager(Map<String, AnnotationView> stringStringMap, Map<Annotation, AnnotationView> annotationToAnnotationViewMap,Map<String, List<AnnotationView>> mapAnnotationTypeEnumToAnnotationViews) {
        this.stringStringMap = stringStringMap;
        this.annotationToAnnotationViewMap = annotationToAnnotationViewMap;
        this.mapAnnotationTypeEnumToAnnotationViews = mapAnnotationTypeEnumToAnnotationViews;
    }

    AnnotationView viewForAnnotation(Annotation annotation){
        return annotationToAnnotationViewMap.get(annotation);
    }

    public class calc extends Thread {

        private LatLngBounds latLngBounds;



        public calc(LatLngBounds latLngBounds) {
            this.latLngBounds = latLngBounds;

        }

        @Override
        public void run() {
            super.run();

            if(MapViewManager.this.activeAnnotationListOfVisibleAnnotations == null) {
                MapViewManager.this.activeAnnotationListOfVisibleAnnotations = new ArrayList<>();
            }

            for (Annotation annotation :
                    MapViewManager.this.annotationToAnnotationViewMap.keySet()) {

                if(this.latLngBounds.contains(annotation.getLatLng())) {
                    MapViewManager.this.activeAnnotationListOfVisibleAnnotations.add(annotation);
                }
            }

            /**
             * 1) latlong bounds contain an annotation && View is on screen move it
             * 2) latlong bounds contains an annotation but view doesnt exist - GET VIEW!!!!
             * 3) latlong bounds doesnt contain do nothing
             * 4) latlong bounds doesnt contain annotation && View has moved off screen now. - recycle
             */

        }
    }
}
