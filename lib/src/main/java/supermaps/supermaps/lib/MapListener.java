package supermaps.supermaps.lib;

import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Created by maximilianalexander on 5/7/16.
 */
public interface MapListener {

    LatLngBounds onRegionWillChange(SuperMap superMap);
    LatLngBounds onRegionDidChange(SuperMap superMap);

}
