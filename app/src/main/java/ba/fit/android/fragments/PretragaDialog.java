package ba.fit.android.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ba.fit.android.R;
import ba.fit.android.data.KorisnikVM;
import ba.fit.android.data.PosiljkaVM;
import ba.fit.android.data.Storage;
import ba.fit.android.helper.MyRunnable;

public class PretragaDialog extends DialogFragment {

    private static final String MY_KEY = "MojKey";
    private EditText text;
    private Button btn;
    private ListView lv;
    private MyRunnable<KorisnikVM> callback;

    public static PretragaDialog newInstance(MyRunnable call) {
        PretragaDialog fragment = new PretragaDialog();
        Bundle args = new Bundle();
        //Serialization is persisting an object from memory to a sequence of bits, for instance for saving onto the disk.
        // postavljamo argument dialoga sa kljucem
        args.putSerializable(MY_KEY,call);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            // pozivamo argument dijaloga sa kljucem
            callback = (MyRunnable<KorisnikVM>) getArguments().getSerializable(MY_KEY);
        }
        // kreiramo stil u style.xml fajlu i dodjeljujemo ovom fragmentu
        setStyle(STYLE_NORMAL,R.style.MojDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pretraga_dialog, container, false);

        text = view.findViewById(R.id.editText);
        btn = view.findViewById(R.id.btnTrazi);
        lv = view.findViewById(R.id.listViewPretraga);
        // ucitavamo listu
        LoadListView(null);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPretragaClick();
            }
        });

        return view;
    }

    private void btnPretragaClick() {
        String val = text.getText().toString();
        LoadListView(val);
    }

    private void LoadListView(String val) {

        final List<KorisnikVM> list = val == null ? Storage.getKorisnici() : Storage.getKorisniciByIme(val);

        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View v, ViewGroup parent) {

                if(v == null){
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = inflater.inflate(R.layout.stavka_primaoci, parent,false);
                }

                TextView txtFirstLine = v.findViewById(R.id.txtFirstLine);
                TextView txtSecondLine = v.findViewById(R.id.txtSecondLine);

                KorisnikVM x = list.get(position);

                txtFirstLine.setText(x.getIme() + " " + x.getPrezime());
                txtSecondLine.setText(x.getOpstinaVM().toString());

                return v;
            }
        });

        // dodajemo event na iteme liste
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // dohvacamo kliknuti item
                KorisnikVM x = list.get(position);
                // zatvaramo dijalog
                getDialog().dismiss();
                // prenosimo podatke sa liste na fragment
                callback.run(x);
            }
        });

    }
}
