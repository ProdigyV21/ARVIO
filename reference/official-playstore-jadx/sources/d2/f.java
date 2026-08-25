package d2;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Pair;
import androidx.appcompat.widget.f0;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
public final class f extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final /* synthetic */ int f14665r = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f14666i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final a8.e f14667l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final f0 f14668m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f14669n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f14670o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final e2.a f14671p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f14672q;

    public f(Context context, String str, final a8.e eVar, final f0 f0Var, boolean z) {
        super(context, str, null, f0Var.f1566l, new DatabaseErrorHandler(f0Var, eVar) { // from class: d2.d

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ a8.e f14662a;

            {
                this.f14662a = eVar;
            }

            @Override // android.database.DatabaseErrorHandler
            public final void onCorruption(SQLiteDatabase sQLiteDatabase) {
                int i10 = f.f14665r;
                a8.e eVar2 = this.f14662a;
                c cVar = (c) eVar2.f183l;
                if (cVar == null || !p.a(cVar.f14660i, sQLiteDatabase)) {
                    cVar = new c(sQLiteDatabase);
                    eVar2.f183l = cVar;
                }
                SQLiteDatabase sQLiteDatabase2 = cVar.f14660i;
                Log.e("SupportSQLite", "Corruption reported by sqlite on database: " + cVar + ".path");
                if (!sQLiteDatabase2.isOpen()) {
                    String path = sQLiteDatabase2.getPath();
                    if (path != null) {
                        f0.c(path);
                        return;
                    }
                    return;
                }
                List list = null;
                try {
                    try {
                        list = cVar.f14661l;
                    } catch (SQLiteException unused) {
                    }
                    try {
                        cVar.close();
                    } catch (IOException unused2) {
                    }
                    if (list != null) {
                        return;
                    }
                } finally {
                    if (list != null) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            f0.c((String) ((Pair) it.next()).second);
                        }
                    } else {
                        String path2 = sQLiteDatabase2.getPath();
                        if (path2 != null) {
                            f0.c(path2);
                        }
                    }
                }
            }
        });
        this.f14666i = context;
        this.f14667l = eVar;
        this.f14668m = f0Var;
        this.f14669n = z;
        this.f14671p = new e2.a(str == null ? UUID.randomUUID().toString() : str, context.getCacheDir(), false);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public final void close() {
        e2.a aVar = this.f14671p;
        try {
            aVar.a(aVar.f15021a);
            super.close();
            this.f14667l.f183l = null;
            this.f14672q = false;
        } finally {
            aVar.b();
        }
    }

    public final c2.b i(boolean z) {
        e2.a aVar = this.f14671p;
        try {
            aVar.a((this.f14672q || getDatabaseName() == null) ? false : true);
            this.f14670o = false;
            SQLiteDatabase sQLiteDatabaseK = k(z);
            if (!this.f14670o) {
                c cVarJ = j(sQLiteDatabaseK);
                aVar.b();
                return cVarJ;
            }
            close();
            c2.b bVarI = i(z);
            aVar.b();
            return bVarI;
        } catch (Throwable th) {
            aVar.b();
            throw th;
        }
    }

    public final c j(SQLiteDatabase sQLiteDatabase) {
        a8.e eVar = this.f14667l;
        c cVar = (c) eVar.f183l;
        if (cVar != null && p.a(cVar.f14660i, sQLiteDatabase)) {
            return cVar;
        }
        c cVar2 = new c(sQLiteDatabase);
        eVar.f183l = cVar2;
        return cVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v12, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v9, types: [android.database.sqlite.SQLiteDatabase] */
    public final SQLiteDatabase k(boolean z) throws Throwable {
        File parentFile;
        String databaseName = getDatabaseName();
        Context context = this.f14666i;
        if (databaseName != null && (parentFile = context.getDatabasePath(databaseName).getParentFile()) != null) {
            parentFile.mkdirs();
            if (!parentFile.isDirectory()) {
                Log.w("SupportSQLite", "Invalid database parent file, not a directory: " + parentFile);
            }
        }
        try {
            z = z != 0 ? getWritableDatabase() : getReadableDatabase();
            return z;
        } catch (Throwable unused) {
            super.close();
            try {
                Thread.sleep(500L);
            } catch (InterruptedException unused2) {
            }
            try {
                z = z != 0 ? getWritableDatabase() : getReadableDatabase();
                return z;
            } catch (Throwable th) {
                super.close();
                if (th instanceof e) {
                    e eVar = th;
                    int iC = h.f0.c(eVar.f14663i);
                    Throwable th2 = eVar.f14664l;
                    if (iC == 0 || iC == 1 || iC == 2 || iC == 3 || !(th2 instanceof SQLiteException)) {
                        throw th2;
                    }
                } else if (!(th instanceof SQLiteException) || databaseName == null || !this.f14669n) {
                    throw th;
                }
                context.deleteDatabase(databaseName);
                try {
                    return z != 0 ? getWritableDatabase() : getReadableDatabase();
                } catch (e e5) {
                    throw e5.f14664l;
                }
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onConfigure(SQLiteDatabase sQLiteDatabase) {
        try {
            f0 f0Var = this.f14668m;
            j(sQLiteDatabase);
            f0Var.getClass();
        } catch (Throwable th) {
            throw new e(th, 1);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f14668m.f(j(sQLiteDatabase));
        } catch (Throwable th) {
            throw new e(th, 2);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i10, int i11) {
        this.f14670o = true;
        try {
            this.f14668m.h(j(sQLiteDatabase), i10, i11);
        } catch (Throwable th) {
            throw new e(th, 4);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        if (!this.f14670o) {
            try {
                this.f14668m.g(j(sQLiteDatabase));
            } catch (Throwable th) {
                throw new e(th, 5);
            }
        }
        this.f14672q = true;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i10, int i11) {
        this.f14670o = true;
        try {
            this.f14668m.h(j(sQLiteDatabase), i10, i11);
        } catch (Throwable th) {
            throw new e(th, 3);
        }
    }
}
