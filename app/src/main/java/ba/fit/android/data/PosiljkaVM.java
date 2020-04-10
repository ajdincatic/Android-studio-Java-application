package ba.fit.android.data;

import java.io.Serializable;

/**
 * Created by Adil on 02/02/2017.
 */

public class PosiljkaVM implements Serializable {
    public KorisnikVM primaoc;
    public float masa;
    public String napomena;
    public int brojPosiljke;
    public float iznos;
    public boolean placaPouzecem;

    public PosiljkaVM(KorisnikVM primaoc, float masa, float iznos, String napomena) {
        this.primaoc = primaoc;
        this.masa = masa;
        this.iznos = iznos;
        this.napomena = napomena;
    }

    public PosiljkaVM()
    {

    }
}
