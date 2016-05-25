package supermaps.supermap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import supermaps.supermaps.lib.MapView;

public class MainActivity extends AppCompatActivity {

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapView = (MapView)findViewById(R.id.map_view);
    }
}
