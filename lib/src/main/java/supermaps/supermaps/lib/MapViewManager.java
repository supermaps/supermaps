package supermaps.supermaps.lib;

import java.util.HashMap;
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

    private Map<Annotation, AnnotationView> annotationToAnnotationViewMap = new HashMap<>();

    MapViewManager(Map<String, AnnotationView> stringStringMap, Map<Annotation, AnnotationView> annotationToAnnotationViewMap) {
        this.stringStringMap = stringStringMap;
        this.annotationToAnnotationViewMap = annotationToAnnotationViewMap;
    }

    AnnotationView viewForAnnotation(Annotation annotation){
        return annotationToAnnotationViewMap.get(annotation);
    }


}
