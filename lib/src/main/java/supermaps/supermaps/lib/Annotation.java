package supermaps.supermaps.lib;

import com.google.android.gms.maps.model.LatLng;

import java.util.Observable;

/**
 * Created by maximilianalexander on 5/7/16.
 * This is equivalent to an index in RecyclerView. Since MapViews don't have index, we need a object that hosts a LatLng
 */
public interface Annotation {

    void setLatLng(LatLng latLng);
    LatLng getLatLng();

}