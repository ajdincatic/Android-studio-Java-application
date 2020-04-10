package ba.fit.android.helper;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import ba.fit.android.fragments.PretragaDialog;

public class MyFragmentUtils {
    // ucitavanje fragmenta na aktivnost
    public static void openAsReplace(Activity activity, int id, Fragment fragment) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // uvitavanje activitya
        fragmentTransaction.replace(id, fragment);
        // potvrđujemo promjenu
        fragmentTransaction.commit();
    }

    public static void openAsDialog(Activity activity,DialogFragment df){
        FragmentManager fm = activity.getFragmentManager(); // getactivity() vraca trenutnu aktivnost
        df.show(fm,"Dialog"); // prikaz dialoga
    }

}
