package ba.fit.android.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import ba.fit.android.R;
import ba.fit.android.data.PosiljkaVM;
import ba.fit.android.data.Storage;
import ba.fit.android.helper.MyFragmentUtils;

public class PosiljkaList extends Fragment {

    private Button btnNovaPosiljka;
    private ListView listViewPosiljke;
    private BaseAdapter adapter;

    public static PosiljkaList newInstance(){
        return new PosiljkaList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // dohvatimo željeni fragment na aktivitiju
        final View view = inflater.inflate(R.layout.posiljka_list,container,false);

        // dohvacamo elemente
        // svaki element na aktivnosti je view
        btnNovaPosiljka = (Button) view.findViewById(R.id.btn_nova_posiljka);
        listViewPosiljke = (ListView) view.findViewById(R.id.list_posiljka);

        // ucitava podatke u listu
        LoadData();

        // dodajemo event na button
        btnNovaPosiljka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnDodajClick(); // evente implementiramo u zasebnim funkcijama
            }
        });

        // vraca kompletan view sa listom
        return view;
    }

    private void LoadData(){
        // uzimamo podatke iz storagea, finale znaci da se podaci nece mijenjati
        final List<PosiljkaVM> podaci = Storage.getPosiljke();

        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return podaci.size();
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
                    // uzimamo inflater activitya
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    // dohvacamo fragment i smjestamo u parent
                    v = inflater.inflate(R.layout.stavka_posiljka, parent,false);
                }

                TextView txtFirstLine = v.findViewById(R.id.txtFirstLine);
                TextView txtSecondLine = v.findViewById(R.id.txtSecondLine);

                // uzimamo jednu posiljku iz liste i smjestamo u textview
                PosiljkaVM x = podaci.get(position);
                txtFirstLine.setText(x.primaoc.getImePrezime());
                txtSecondLine.setText(x.primaoc.getOpstinaVM().toString());

                // vraca se view koji predstavlja 1 clan liste
                return v;
            }
        };

        listViewPosiljke.setAdapter(adapter);

        listViewPosiljke.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PosiljkaVM x = podaci.get(position);
                listViewLongClick(x);
                return true;
            }
        });
    }

    // event na long click itema
    private void listViewLongClick(final PosiljkaVM x) {
        // pozivamo ugradjeni dialog
        AlertDialog.Builder aDialog = new AlertDialog.Builder(getActivity());
        aDialog.setTitle("Warning");
        aDialog.setMessage("Želite li obrisati pošiljku ?");

        aDialog.setPositiveButton("DA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Storage.removePosiljka(x);
                dialog.dismiss();
                // osvjezava listu
                adapter.notifyDataSetChanged();
            }
        });

        aDialog.setNegativeButton("NE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        aDialog.setIcon(android.R.drawable.ic_dialog_alert);
        aDialog.show();
    }


    private void do_btnDodajClick() {
        MyFragmentUtils.openAsReplace(getActivity(), R.id.placeToLoad, PosiljkaAdd1.newInstance());
    }
}
