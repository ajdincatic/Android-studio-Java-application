package ba.fit.android.helper;

import android.content.Context;
import android.content.SharedPreferences;

import ba.fit.android.data.KorisnikVM;

public class MySession {
    private static final String PREFS_NAME = "DatotekaZaSharedPrefernces";
    private static String nekikey="Key_korisnik";

    public static KorisnikVM getKorisnik()
    {
        SharedPreferences sharedPreferences = MyApp.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String strJson = sharedPreferences.getString(nekikey, "");
        if (strJson.length() == 0)
            return null;

        KorisnikVM x = MyGson.build().fromJson(strJson, KorisnikVM.class);
        return x;
    }

    public static void setKorisnik(KorisnikVM x)
    {
        String strJson = x!=null? MyGson.build().toJson(x):"";

        SharedPreferences sharedPreferences = MyApp.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(nekikey, strJson);
        editor.apply();
    }
}