package ba.fit.android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import ba.fit.android.data.KorisnikVM;
import ba.fit.android.helper.MySession;

public class MainActivity extends LauncherActivity {

    // launcher aktivnost
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        KorisnikVM x = MySession.getKorisnik();
        if (x==null)
            startActivity(new Intent(this, LoginActivity.class));
        else
            startActivity(new Intent(this, GlavniActivity.class));
    }
}
