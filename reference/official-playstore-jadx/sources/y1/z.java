package y1;

import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.work.impl.WorkDatabase_Impl;
import io.sentry.p7;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes3.dex */
public final class z {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String[] f22861m = {"UPDATE", "DELETE", "INSERT"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WorkDatabase_Impl f22862a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f22863b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LinkedHashMap f22864c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String[] f22865d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AtomicBoolean f22866e = new AtomicBoolean(false);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile boolean f22867f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public volatile c2.g f22868g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final p7 f22869h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final p.f f22870i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f22871j;
    public final Object k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final androidx.leanback.widget.u f22872l;

    public z(WorkDatabase_Impl workDatabase_Impl, HashMap map, HashMap map2, String... strArr) {
        this.f22862a = workDatabase_Impl;
        this.f22863b = map;
        this.f22869h = new p7(strArr.length);
        Collections.newSetFromMap(new IdentityHashMap());
        this.f22870i = new p.f();
        this.f22871j = new Object();
        this.k = new Object();
        this.f22864c = new LinkedHashMap();
        int length = strArr.length;
        String[] strArr2 = new String[length];
        for (int i10 = 0; i10 < length; i10++) {
            String str = strArr[i10];
            Locale locale = Locale.US;
            String lowerCase = str.toLowerCase(locale);
            this.f22864c.put(lowerCase, Integer.valueOf(i10));
            String str2 = (String) this.f22863b.get(strArr[i10]);
            String lowerCase2 = str2 != null ? str2.toLowerCase(locale) : null;
            if (lowerCase2 != null) {
                lowerCase = lowerCase2;
            }
            strArr2[i10] = lowerCase;
        }
        this.f22865d = strArr2;
        for (Map.Entry entry : this.f22863b.entrySet()) {
            String str3 = (String) entry.getValue();
            Locale locale2 = Locale.US;
            String lowerCase3 = str3.toLowerCase(locale2);
            if (this.f22864c.containsKey(lowerCase3)) {
                String lowerCase4 = ((String) entry.getKey()).toLowerCase(locale2);
                LinkedHashMap linkedHashMap = this.f22864c;
                linkedHashMap.put(lowerCase4, kotlin.collections.h0.s0(linkedHashMap, lowerCase3));
            }
        }
        this.f22872l = new androidx.leanback.widget.u(this, 18);
    }

    public final boolean a() {
        d2.c cVar = this.f22862a.f6709a;
        if (!kotlin.jvm.internal.p.a(cVar != null ? Boolean.valueOf(cVar.f14660i.isOpen()) : null, Boolean.TRUE)) {
            return false;
        }
        if (!this.f22867f) {
            this.f22862a.h().getWritableDatabase();
        }
        if (this.f22867f) {
            return true;
        }
        Log.e("ROOM", "database is not initialized even though it is open");
        return false;
    }

    public final void b(c2.b bVar, int i10) {
        bVar.p("INSERT OR IGNORE INTO room_table_modification_log VALUES(" + i10 + ", 0)");
        String str = this.f22865d[i10];
        for (int i11 = 0; i11 < 3; i11++) {
            String str2 = f22861m[i11];
            StringBuilder sb2 = new StringBuilder("CREATE TEMP TRIGGER IF NOT EXISTS ");
            y.a.i(sb2, "`room_table_modification_trigger_" + str + '_' + str2 + '`', " AFTER ", str2, " ON `");
            sb2.append(str);
            sb2.append("` BEGIN UPDATE room_table_modification_log SET invalidated = 1 WHERE table_id = ");
            sb2.append(i10);
            sb2.append(" AND invalidated = 0; END");
            bVar.p(sb2.toString());
        }
    }

    public final void c(c2.b bVar) {
        if (bVar.E0()) {
            return;
        }
        try {
            ReentrantReadWriteLock.ReadLock lock = this.f22862a.f6716h.readLock();
            lock.lock();
            try {
                synchronized (this.f22871j) {
                    int[] iArrD = this.f22869h.d();
                    if (iArrD != null) {
                        if (bVar.H0()) {
                            bVar.E();
                        } else {
                            bVar.m();
                        }
                        try {
                            int length = iArrD.length;
                            int i10 = 0;
                            int i11 = 0;
                            while (i10 < length) {
                                int i12 = iArrD[i10];
                                int i13 = i11 + 1;
                                if (i12 == 1) {
                                    b(bVar, i11);
                                } else if (i12 == 2) {
                                    String str = this.f22865d[i11];
                                    String[] strArr = f22861m;
                                    for (int i14 = 0; i14 < 3; i14++) {
                                        String str2 = strArr[i14];
                                        StringBuilder sb2 = new StringBuilder("DROP TRIGGER IF EXISTS ");
                                        sb2.append("`room_table_modification_trigger_" + str + '_' + str2 + '`');
                                        bVar.p(sb2.toString());
                                    }
                                }
                                i10++;
                                i11 = i13;
                            }
                            bVar.C();
                            bVar.K();
                        } catch (Throwable th) {
                            bVar.K();
                            throw th;
                        }
                    }
                }
            } finally {
                lock.unlock();
            }
        } catch (SQLiteException e5) {
            Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e5);
        } catch (IllegalStateException e6) {
            Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e6);
        }
    }
}
