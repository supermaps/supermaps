package supermaps.supermaps.lib;

import com.google.android.gms.maps.model.LatLng;

import java.util.Observable;

/**
 * Created by maximilianalexander on 5/7/16.
 * This is equivalent to an index in RecyclerView. Since MapViews don't have index, we need a object that hosts a LatLng
 */
public interface Annotation {

    /**
     * Create a variable in the annotation class that implements this interface
     * assign the value to that variable.
     * @param latLng
     */
    void setLatLng(LatLng latLng);

    /**
     * Return the variable u created above, in this method.
     * @return
     */
    LatLng getLatLng();


}

