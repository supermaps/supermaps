package supermaps.supermap;

import com.google.android.gms.maps.model.LatLng;

import supermaps.supermaps.lib.Annotation;

/**
 * Created by ariochdivij666 on 8/21/16.
 */
public class BoatAnnotation implements  Annotation {
    private LatLng latLng;
    private String ReuseId;

    @Override
    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override
    public LatLng getLatLng() {
        return this.latLng;
    }


}
