package supermaps.supermap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import supermaps.supermaps.lib.SuperMap;

public class MainActivity extends AppCompatActivity {

    private SuperMap mSuperMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
