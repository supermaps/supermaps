package supermaps.supermaps.lib;

/**
 * Created by maximilianalexander on 5/7/16.
 * This should be an abstract class that hosts the view presentable on the map.
 * It will have a reference to the annotation that it represent.
 */
public abstract class AnnotationView {

    abstract Annotation getAnnotation();
    abstract void setAnnotation(Annotation annotation);

}
