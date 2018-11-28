package stonetree.com.mercadolivre.core.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CoreActivity extends AppCompatActivity {

    public Object loadBundle(String bundleName) {
        final Bundle bundle = this.getIntent().getExtras();
        if (bundle != null)
            return bundle.getSerializable(bundleName);
        else
            return bundle;
    }

}
