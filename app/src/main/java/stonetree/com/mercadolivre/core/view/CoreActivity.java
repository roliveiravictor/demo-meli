package stonetree.com.mercadolivre.core.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.github.ybq.android.spinkit.SpinKitView;

import stonetree.com.mercadolivre.R;

public class CoreActivity extends AppCompatActivity {

    private SpinKitView spinKitView;

    private LinearLayout rootLayout;

    public void findComponents() {
        spinKitView = findViewById(R.id.spin_kit);
        rootLayout = findViewById(R.id.rootLayout);
    }

    public void showLoading() {
        rootLayout.setVisibility(View.GONE);
        spinKitView.setVisibility(View.VISIBLE);
    }

    public void hideLoading(){
        rootLayout.setVisibility(View.VISIBLE);
        spinKitView.setVisibility(View.GONE);
    }

    public Object loadBundle(String bundleName) {
        final Bundle bundle = this.getIntent().getExtras();
        if (bundle != null)
            return bundle.getSerializable(bundleName);
        else
            return bundle;
    }

}
