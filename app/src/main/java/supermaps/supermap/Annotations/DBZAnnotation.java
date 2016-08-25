package supermaps.supermap.Annotations;

import com.google.android.gms.maps.model.LatLng;

import supermaps.supermaps.lib.Annotation;

/**
 * Created by ariochdivij666 on 8/24/16.
 */
public class DBZAnnotation implements Annotation {
    private LatLng latLng;
    private static String ReuseId;

    @Override
    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override
    public LatLng getLatLng() {
        return this.latLng;
    }
}
