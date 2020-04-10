package ba.fit.android;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ba.fit.android.fragments.PosiljkaList;
import ba.fit.android.helper.MyFragmentUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // na glavni activity dodajemo fragmente, parametri activity na koji se dodaje, gdje se dodaje i šta dodajemo
        MyFragmentUtils.openAsReplace(this,R.id.placeToLoad, PosiljkaList.newInstance());
    }

}
