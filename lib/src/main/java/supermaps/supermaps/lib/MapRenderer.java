package supermaps.supermaps.lib;

/**
 * Created by maximilianalexander on 5/7/16.
 */
public interface MapRenderer {

    AnnotationView viewForAnnotation(Annotation annotation, MapViewManager mapViewManager);

    void annotationViewWillBeRemoved(AnnotationView annotationView);
    void annotationViewDidRemove(AnnotationView annotationView);

    void annotationViewWillBeAdded(AnnotationView annotationView);
    void annotationViewDidAdd(AnnotationView annotationView);

}
