package supermaps.supermap;

import supermaps.supermaps.lib.IEnum;

/**
 * Created by ariochdivij666 on 5/16/16.
 *
 * IEnum is an interface that is part of the library. We are using that in the mapmanager for the map.
 * This is to explain what i would like to get form the user allowing us to have an easy
 * extensible way to have annotationViews associated with an enum. We may not necessarily have to
 * do it this way,
 *
 */
public enum DemoEnum implements IEnum {
    TEST;
}
