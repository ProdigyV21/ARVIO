package c2;

import android.database.Cursor;
import java.io.Closeable;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002À\u0006\u0001"}, d2 = {"Lc2/b;", "Ljava/io/Closeable;", "sqlite_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface b extends Closeable {
    Cursor A0(f fVar);

    void C();

    void E();

    boolean E0();

    boolean H0();

    void K();

    g g0(String str);

    void m();

    void p(String str);
}
