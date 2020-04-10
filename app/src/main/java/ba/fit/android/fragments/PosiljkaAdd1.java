package ba.fit.android.fragments;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ba.fit.android.R;
import ba.fit.android.data.KorisnikVM;
import ba.fit.android.data.PosiljkaVM;
import ba.fit.android.helper.MyFragmentUtils;
import ba.fit.android.helper.MyRunnable;

public class PosiljkaAdd1 extends Fragment {

    private EditText txtIme;
    private EditText txtAdresa;
    private Button btnPromijeni;
    private Button btnDalje;

    public static PosiljkaAdd1 newInstance() {
        return new PosiljkaAdd1();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_posiljka_add1, container, false);

        txtIme = view.findViewById(R.id.editTxtIme); // ctrl + alt + F kreira atribut klase
        txtAdresa = view.findViewById(R.id.viewAdresa);
        btnPromijeni = view.findViewById(R.id.btnPromijeniPrimaoca);
        btnDalje = view.findViewById(R.id.btnDalje);

        btnPromijeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPromijeniClick();
            }
        });

        btnDalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDaljeClick();
            }
        });

        return view;
    }

    private PosiljkaVM posiljkaVM = new PosiljkaVM();

    private void btnPromijeniClick() {
        // kreiramo servis koji saljemo dialogu
        MyRunnable callback = new MyRunnable<KorisnikVM>() {
            @Override
            public void run(KorisnikVM obj) {
                posiljkaVM.primaoc = obj;
                txtIme.setText(obj.getIme()+" "+obj.getPrezime());
                txtAdresa.setText(obj.getOpstinaVM().toString());
            }
        };

        PretragaDialog pd = PretragaDialog.newInstance(callback);
        MyFragmentUtils.openAsDialog(getActivity(), pd);
    }

    private void btnDaljeClick() {
        MyFragmentUtils.openAsReplace(getActivity(),R.id.placeToLoad, PosiljkaAdd2.newInstance(posiljkaVM));
    }

}
