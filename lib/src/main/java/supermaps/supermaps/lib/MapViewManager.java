package supermaps.supermaps.lib;

import java.util.List;
import java.util.Map;

/**
 * Created by maximilianalexander on 5/16/16.
 */
public class MapViewManager<T extends Enum<T> & IEnum> {

    Map<T, List<AnnotationView>> mapAnnotationTypeEnumToAnnotationViews;

    public MapViewManager(Map<T, List<AnnotationView>> mapAnnotationTypeEnumToAnnotationViews) {
        this.mapAnnotationTypeEnumToAnnotationViews = mapAnnotationTypeEnumToAnnotationViews;
    }
}
