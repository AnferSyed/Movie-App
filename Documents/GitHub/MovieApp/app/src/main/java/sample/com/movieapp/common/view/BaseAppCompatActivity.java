package sample.com.movieapp.common.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by anfer on 25-Sep-18.
 */

public class BaseAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * showToast
     *
     * @param msg
     */
    protected void showToast(String msg, int toastLength) {
        Toast.makeText(this, msg, toastLength).show();
    }

}
