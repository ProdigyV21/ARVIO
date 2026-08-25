package d2;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class c implements c2.b {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String[] f14658m = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final String[] f14659n = new String[0];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final SQLiteDatabase f14660i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final List f14661l;

    public c(SQLiteDatabase sQLiteDatabase) {
        this.f14660i = sQLiteDatabase;
        this.f14661l = sQLiteDatabase.getAttachedDbs();
    }

    @Override // c2.b
    public final Cursor A0(c2.f fVar) {
        final b bVar = new b(fVar);
        return this.f14660i.rawQueryWithFactory(new SQLiteDatabase.CursorFactory() { // from class: d2.a
            @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
            public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
                return (Cursor) bVar.invoke(sQLiteDatabase, sQLiteCursorDriver, str, sQLiteQuery);
            }
        }, fVar.i(), f14659n, null);
    }

    @Override // c2.b
    public final void C() {
        this.f14660i.setTransactionSuccessful();
    }

    @Override // c2.b
    public final void E() {
        this.f14660i.beginTransactionNonExclusive();
    }

    @Override // c2.b
    public final boolean E0() {
        return this.f14660i.inTransaction();
    }

    @Override // c2.b
    public final boolean H0() {
        return this.f14660i.isWriteAheadLoggingEnabled();
    }

    @Override // c2.b
    public final void K() {
        this.f14660i.endTransaction();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f14660i.close();
    }

    @Override // c2.b
    public final c2.g g0(String str) {
        return new i(this.f14660i.compileStatement(str));
    }

    public final void i(Object[] objArr) {
        this.f14660i.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", objArr);
    }

    public final Cursor j(String str) {
        return A0(new c2.a(str, 0));
    }

    @Override // c2.b
    public final void m() {
        this.f14660i.beginTransaction();
    }

    @Override // c2.b
    public final void p(String str) {
        this.f14660i.execSQL(str);
    }
}
