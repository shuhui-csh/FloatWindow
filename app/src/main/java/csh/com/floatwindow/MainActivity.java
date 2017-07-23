package csh.com.floatwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private FloatWindowManager mFloatWindowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mFloatWindowManager = new FloatWindowManager(this.getApplicationContext());
        View showFloatWindow = findViewById(R.id.show_float_window);
        View removeFloatWindow = findViewById(R.id.remove_float_window);
        showFloatWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFloatWindowManager.showFloatWindow();
            }
        });

        removeFloatWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFloatWindowManager.removeFloatWindow();
            }
        });
    }

    @Override
    protected void onDestroy() {
        //        mFloatWindowManager.removeFloatWindow();
        super.onDestroy();
    }
}
