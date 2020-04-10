package ba.fit.android.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adil on 18/06/2016.
 */
public class Storage {
    private static List<OpstinaVM> opstine;
    private static int brojacPosiljki;

    public static List<OpstinaVM> getOpstine()
    {
        if (opstine == null)
        {
            opstine = new ArrayList<>();
            opstine.add(new OpstinaVM(1, "Mostar", "BiH"));
            opstine.add(new OpstinaVM(2, "Sarajevo", "BiH"));
            opstine.add(new OpstinaVM(3, "Tuzla", "BiH"));
            opstine.add(new OpstinaVM(4, "Banja Luka", "BiH"));
            opstine.add(new OpstinaVM(5, "Zagreb", "Hrvatska"));
            opstine.add(new OpstinaVM(6, "Tuzla", "BiH"));
            opstine.add(new OpstinaVM(7, "Goražde", "BiH"));
            opstine.add(new OpstinaVM(8, "Konjic", "BiH"));
        }
        return opstine;
    }

    private static List<KorisnikVM> korisnici;
    public static List<KorisnikVM> getKorisnici()
    {
        if (korisnici == null)
        {
            korisnici = new ArrayList<>();
            korisnici.add(new KorisnikVM("Emina", "Obradovic", getOpstine().get(0)));
            korisnici.add(new KorisnikVM("Adil", "Joldic", getOpstine().get(7)));
            korisnici.add(new KorisnikVM("Larisa", "Dedović", getOpstine().get(7)));
            korisnici.add(new KorisnikVM("Elmin", "Sudic", getOpstine().get(5)));
            korisnici.add(new KorisnikVM("Maria", "Herceg", getOpstine().get(2)));
            korisnici.add(new KorisnikVM("Fuad", "Dedić", getOpstine().get(2)));
            korisnici.add(new KorisnikVM("Ajdin", "Ćatić", getOpstine().get(2)));
            korisnici.add(new KorisnikVM("Amer", "Hadžić", getOpstine().get(2)));
            korisnici.add(new KorisnikVM("Almin", "Hatarić", getOpstine().get(2)));
        }
        return korisnici;
    }

    private static List<PosiljkaVM> posilje;
    public static List<PosiljkaVM> getPosiljke()
    {
        if (posilje == null)
        {
            posilje = new ArrayList<>();
            posilje.add(new PosiljkaVM(getKorisnici().get(0), 15, 5, "Pazi, lomljivo"));
            posilje.add(new PosiljkaVM(getKorisnici().get(2), 15, 5, ""));
            posilje.add(new PosiljkaVM(getKorisnici().get(3), 105, 15, "Uspravno držati"));
            posilje.add(new PosiljkaVM(getKorisnici().get(0), 5, 5, ""));
            posilje.add(new PosiljkaVM(getKorisnici().get(2), 51, 5, ""));
            posilje.add(new PosiljkaVM(getKorisnici().get(7), 51, 5, ""));
            posilje.add(new PosiljkaVM(getKorisnici().get(4), 51, 5, ""));
            posilje.add(new PosiljkaVM(getKorisnici().get(2), 51, 5, ""));
            posilje.add(new PosiljkaVM(getKorisnici().get(2), 51, 5, ""));

        }
        return posilje;
    }

    public static List<KorisnikVM> getKorisniciByIme(String query) {

        List<KorisnikVM> rezultat = new ArrayList<>();

        for (KorisnikVM x: getKorisnici())
        {
            if ((x.getIme() + " " + x.getPrezime()).toLowerCase().startsWith(query.toLowerCase()))
                rezultat.add(x);
        }

        return rezultat;
    }

    public static void addPosiljka(PosiljkaVM posiljkaVM) {
        posiljkaVM.brojPosiljke = brojacPosiljki++;
        getPosiljke().add(posiljkaVM);
    }

    public static void removePosiljka(PosiljkaVM x) {
        getPosiljke().remove(x);
    }
}
