package supermaps.supermap;

import com.google.android.gms.maps.model.LatLng;

import supermaps.supermaps.lib.Annotation;

/**
 * Created by ariochdivij666 on 8/17/16.
 */
public class SuperMapDemoAnnotation implements Annotation {

    private LatLng latLng;

    /**
     * Default constructor that puts in a San Francisco address
     */
    public SuperMapDemoAnnotation() {
        this.latLng = new LatLng(37.774929, -122.419416);
    }

    /**
     * Give it the latlng u have received or want to use.
     * @param latLng
     */
    public SuperMapDemoAnnotation(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override
    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override
    public LatLng getLatLng() {
        return this.latLng;
    }
}
