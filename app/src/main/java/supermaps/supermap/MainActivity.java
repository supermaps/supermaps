package supermaps.supermap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import supermaps.supermaps.lib.AnnotationView;
import supermaps.supermaps.lib.MapViewManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<DemoEnum, List<AnnotationView>> demoEnumListMap = new HashMap<>();

        MapViewManager<DemoEnum> demoEnumMapViewManager = new MapViewManager<>(demoEnumListMap);
    }
}
