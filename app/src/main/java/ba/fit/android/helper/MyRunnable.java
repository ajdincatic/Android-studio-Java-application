package ba.fit.android.helper;

import java.io.Serializable;

// interface za prenos podataka sa jednog fragmenta na drugi
public interface MyRunnable<T> extends Serializable {
    void run(T t);
}
