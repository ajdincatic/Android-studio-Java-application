package ba.fit.android.data;

import java.util.List;

public class PosiljkaPregledVM {
    public static class Row
    {
        public String primaocImePrezime;
        public String primaocAdresa;
        public float masa;
        public String napomena;
        public int brojPosiljke;
        public float iznos;
        public boolean placaPouzecem;
    }

    public List<Row> rows;
}
