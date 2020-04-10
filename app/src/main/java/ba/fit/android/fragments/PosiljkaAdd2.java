package ba.fit.android.fragments;

import android.app.Fragment;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import ba.fit.android.R;
import ba.fit.android.data.PosiljkaVM;
import ba.fit.android.data.Storage;
import ba.fit.android.helper.MyFragmentUtils;


public class PosiljkaAdd2 extends Fragment {

    public static final String POSILJKA_KEY = "MOJKEY";

    private PosiljkaVM posiljkaVM;
    private EditText txtMasa;
    private EditText txtNapomena;
    private Switch switchPlatiPouzecem;
    private EditText txtIznos;
    private Button btnDalje;

    public static PosiljkaAdd2 newInstance(PosiljkaVM posiljkaVM) {
        Bundle args = new Bundle();
        PosiljkaAdd2 fragment = new PosiljkaAdd2();
        args.putSerializable(POSILJKA_KEY,posiljkaVM);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            posiljkaVM = (PosiljkaVM) getArguments().getSerializable(POSILJKA_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posiljka_add2, container, false);

        txtMasa = (EditText) view.findViewById(R.id.txtMasa);
        txtNapomena = (EditText) view.findViewById(R.id.txtNapomena);
        switchPlatiPouzecem = (Switch) view.findViewById(R.id.switchPlatiPouzecem);
        txtIznos = (EditText) view.findViewById(R.id.txtIznos);
        btnDalje = (Button) view.findViewById(R.id.btnDaljeAdd2);

        btnDalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDaljeClick();
            }
        });

        return view;
    }

    private void btnDaljeClick() {
        try {
            this.posiljkaVM.masa = Float.parseFloat(txtMasa.getText().toString());
            this.posiljkaVM.napomena = txtNapomena.getText().toString();
            this.posiljkaVM.iznos = Float.parseFloat(txtIznos.getText().toString());
            this.posiljkaVM.placaPouzecem = switchPlatiPouzecem.isSelected();
            Storage.addPosiljka(posiljkaVM);
            MyFragmentUtils.openAsReplace(getActivity(),R.id.placeToLoad, PosiljkaList.newInstance());
        }
        catch (Exception e){
            Toast.makeText(getActivity(),"Greška: " + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
