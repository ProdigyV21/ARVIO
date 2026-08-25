package d2;

import android.database.sqlite.SQLiteProgram;

/* JADX INFO: loaded from: classes3.dex */
public class h implements c2.e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final SQLiteProgram f14680i;

    public h(SQLiteProgram sQLiteProgram) {
        this.f14680i = sQLiteProgram;
    }

    @Override // c2.e
    public final void C0(int i10) {
        this.f14680i.bindNull(i10);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f14680i.close();
    }

    @Override // c2.e
    public final void e0(int i10, String str) {
        this.f14680i.bindString(i10, str);
    }

    @Override // c2.e
    public final void n0(int i10, long j10) {
        this.f14680i.bindLong(i10, j10);
    }

    @Override // c2.e
    public final void o0(int i10, byte[] bArr) {
        this.f14680i.bindBlob(i10, bArr);
    }

    @Override // c2.e
    public final void u(int i10, double d4) {
        this.f14680i.bindDouble(i10, d4);
    }
}
