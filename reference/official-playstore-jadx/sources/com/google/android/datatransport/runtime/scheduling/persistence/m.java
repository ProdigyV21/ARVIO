package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.SystemClock;
import android.util.Base64;
import android.util.Log;
import androidx.emoji2.text.e0;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.inject.Provider;
import l3.a;

/* JADX INFO: loaded from: classes4.dex */
public final class m implements d, l3.a, c {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final z2.b f12634p = new z2.b("proto");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final p f12635i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final m3.a f12636l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final m3.a f12637m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final a f12638n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Provider f12639o;

    public m(m3.a aVar, m3.a aVar2, a aVar3, p pVar, Provider provider) {
        this.f12635i = pVar;
        this.f12636l = aVar;
        this.f12637m = aVar2;
        this.f12638n = aVar3;
        this.f12639o = provider;
    }

    public static String O(Iterable iterable) {
        StringBuilder sb2 = new StringBuilder("(");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            sb2.append(((b) it.next()).f12615a);
            if (it.hasNext()) {
                sb2.append(',');
            }
        }
        sb2.append(')');
        return sb2.toString();
    }

    public static Object R(Cursor cursor, j jVar) {
        try {
            return jVar.apply(cursor);
        } finally {
            cursor.close();
        }
    }

    public static Long x(SQLiteDatabase sQLiteDatabase, com.google.android.datatransport.runtime.i iVar) {
        StringBuilder sb2 = new StringBuilder("backend_name = ? and priority = ?");
        ArrayList arrayList = new ArrayList(Arrays.asList(iVar.f12589a, String.valueOf(n3.a.a(iVar.f12591c))));
        byte[] bArr = iVar.f12590b;
        if (bArr != null) {
            sb2.append(" and extras = ?");
            arrayList.add(Base64.encodeToString(bArr, 0));
        } else {
            sb2.append(" and extras is null");
        }
        Cursor cursorQuery = sQLiteDatabase.query("transport_contexts", new String[]{"_id"}, sb2.toString(), (String[]) arrayList.toArray(new String[0]), null, null, null);
        try {
            return !cursorQuery.moveToNext() ? null : Long.valueOf(cursorQuery.getLong(0));
        } finally {
            cursorQuery.close();
        }
    }

    public final ArrayList G(SQLiteDatabase sQLiteDatabase, com.google.android.datatransport.runtime.i iVar, int i10) {
        ArrayList arrayList = new ArrayList();
        Long lX = x(sQLiteDatabase, iVar);
        if (lX == null) {
            return arrayList;
        }
        R(sQLiteDatabase.query("events", new String[]{"_id", "transport_name", "timestamp_ms", "uptime_ms", "payload_encoding", "payload", "code", "inline"}, "context_id = ?", new String[]{lX.toString()}, null, null, null, String.valueOf(i10)), new i(this, arrayList, iVar, 0));
        return arrayList;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.d
    public final long J(com.google.android.datatransport.runtime.i iVar) {
        Cursor cursorRawQuery = o().rawQuery("SELECT next_request_ms FROM transport_contexts WHERE backend_name = ? and priority = ?", new String[]{iVar.f12589a, String.valueOf(n3.a.a(iVar.f12591c))});
        try {
            Long lValueOf = cursorRawQuery.moveToNext() ? Long.valueOf(cursorRawQuery.getLong(0)) : 0L;
            cursorRawQuery.close();
            return lValueOf.longValue();
        } catch (Throwable th) {
            cursorRawQuery.close();
            throw th;
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.d
    public final void W(Iterable iterable) {
        if (iterable.iterator().hasNext()) {
            y(new f(this, "UPDATE events SET num_attempts = num_attempts + 1 WHERE _id in " + O(iterable), 0));
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.d
    public final b a0(com.google.android.datatransport.runtime.i iVar, com.google.android.datatransport.runtime.h hVar) {
        z2.c cVar = iVar.f12591c;
        String str = hVar.f12583a;
        String str2 = iVar.f12589a;
        String strI = ac.b.I("SQLiteEventStore");
        if (Log.isLoggable(strI, 3)) {
            Log.d(strI, "Storing event with priority=" + cVar + ", name=" + str + " for destination " + str2);
        }
        long jLongValue = ((Long) y(new i(this, hVar, iVar, 1))).longValue();
        if (jLongValue < 1) {
            return null;
        }
        return new b(jLongValue, iVar, hVar);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f12635i.close();
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.d
    public final int e() {
        long jA = this.f12636l.a() - this.f12638n.f12613d;
        SQLiteDatabase sQLiteDatabaseO = o();
        sQLiteDatabaseO.beginTransaction();
        try {
            String[] strArr = {String.valueOf(jA)};
            Cursor cursorRawQuery = sQLiteDatabaseO.rawQuery("SELECT COUNT(*), transport_name FROM events WHERE timestamp_ms < ? GROUP BY transport_name", strArr);
            while (cursorRawQuery.moveToNext()) {
                try {
                    l(cursorRawQuery.getInt(0), g3.c.MESSAGE_TOO_OLD, cursorRawQuery.getString(1));
                } catch (Throwable th) {
                    cursorRawQuery.close();
                    throw th;
                }
            }
            cursorRawQuery.close();
            int iDelete = sQLiteDatabaseO.delete("events", "timestamp_ms < ?", strArr);
            sQLiteDatabaseO.setTransactionSuccessful();
            return iDelete;
        } finally {
            sQLiteDatabaseO.endTransaction();
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.c
    public final void i() {
        SQLiteDatabase sQLiteDatabaseO = o();
        sQLiteDatabaseO.beginTransaction();
        try {
            sQLiteDatabaseO.compileStatement("DELETE FROM log_event_dropped").execute();
            sQLiteDatabaseO.compileStatement("UPDATE global_log_event_state SET last_metrics_upload_ms=" + this.f12636l.a()).execute();
            sQLiteDatabaseO.setTransactionSuccessful();
        } finally {
            sQLiteDatabaseO.endTransaction();
        }
    }

    @Override // l3.a
    public final Object j(a.InterfaceC0242a interfaceC0242a) {
        SQLiteDatabase sQLiteDatabaseO = o();
        m3.a aVar = this.f12637m;
        long jA = aVar.a();
        while (true) {
            try {
                sQLiteDatabaseO.beginTransaction();
                try {
                    Object objExecute = interfaceC0242a.execute();
                    sQLiteDatabaseO.setTransactionSuccessful();
                    return objExecute;
                } finally {
                    sQLiteDatabaseO.endTransaction();
                }
            } catch (SQLiteDatabaseLockedException e5) {
                if (aVar.a() >= ((long) this.f12638n.f12612c) + jA) {
                    throw new SynchronizationException("Timed out while trying to acquire the lock.", e5);
                }
                SystemClock.sleep(50L);
            }
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.d
    public final Iterable j0(com.google.android.datatransport.runtime.i iVar) {
        return (Iterable) y(new f(this, iVar, 1));
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.c
    public final g3.a k() {
        int i10 = g3.a.f15460e;
        e0 e0Var = new e0(6, false);
        e0Var.f2600l = null;
        e0Var.f2601m = new ArrayList();
        e0Var.f2602n = null;
        e0Var.f2603o = "";
        HashMap map = new HashMap();
        SQLiteDatabase sQLiteDatabaseO = o();
        sQLiteDatabaseO.beginTransaction();
        try {
            g3.a aVar = (g3.a) R(sQLiteDatabaseO.rawQuery("SELECT log_source, reason, events_dropped_count FROM log_event_dropped", new String[0]), new i(this, map, e0Var, 2));
            sQLiteDatabaseO.setTransactionSuccessful();
            return aVar;
        } finally {
            sQLiteDatabaseO.endTransaction();
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.c
    public final void l(final long j10, final g3.c cVar, final String str) {
        y(new j() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.h
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.j
            public final Object apply(Object obj) {
                SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) obj;
                int i10 = cVar.f15474i;
                String string = Integer.toString(i10);
                String str2 = str;
                Cursor cursorRawQuery = sQLiteDatabase.rawQuery("SELECT 1 FROM log_event_dropped WHERE log_source = ? AND reason = ?", new String[]{str2, string});
                try {
                    boolean z = cursorRawQuery.getCount() > 0;
                    cursorRawQuery.close();
                    long j11 = j10;
                    if (z) {
                        sQLiteDatabase.execSQL(androidx.compose.material3.d.k(j11, "UPDATE log_event_dropped SET events_dropped_count = events_dropped_count + ", " WHERE log_source = ? AND reason = ?"), new String[]{str2, Integer.toString(i10)});
                        return null;
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("log_source", str2);
                    contentValues.put("reason", Integer.valueOf(i10));
                    contentValues.put("events_dropped_count", Long.valueOf(j11));
                    sQLiteDatabase.insert("log_event_dropped", null, contentValues);
                    return null;
                } catch (Throwable th) {
                    cursorRawQuery.close();
                    throw th;
                }
            }
        });
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.d
    public final void n(Iterable iterable) {
        if (iterable.iterator().hasNext()) {
            o().compileStatement("DELETE FROM events WHERE _id in " + O(iterable)).execute();
        }
    }

    public final SQLiteDatabase o() {
        p pVar = this.f12635i;
        Objects.requireNonNull(pVar);
        m3.a aVar = this.f12637m;
        long jA = aVar.a();
        while (true) {
            try {
                return pVar.getWritableDatabase();
            } catch (SQLiteDatabaseLockedException e5) {
                if (aVar.a() >= ((long) this.f12638n.f12612c) + jA) {
                    throw new SynchronizationException("Timed out while trying to open db.", e5);
                }
                SystemClock.sleep(50L);
            }
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.d
    public final List w() {
        SQLiteDatabase sQLiteDatabaseO = o();
        sQLiteDatabaseO.beginTransaction();
        try {
            Cursor cursorRawQuery = sQLiteDatabaseO.rawQuery("SELECT distinct t._id, t.backend_name, t.priority, t.extras FROM transport_contexts AS t, events AS e WHERE e.context_id = t._id", new String[0]);
            try {
                ArrayList arrayList = new ArrayList();
                while (cursorRawQuery.moveToNext()) {
                    String string = cursorRawQuery.getString(1);
                    if (string == null) {
                        throw new NullPointerException("Null backendName");
                    }
                    z2.c cVarB = n3.a.b(cursorRawQuery.getInt(2));
                    String string2 = cursorRawQuery.getString(3);
                    arrayList.add(new com.google.android.datatransport.runtime.i(string, string2 == null ? null : Base64.decode(string2, 0), cVarB));
                }
                cursorRawQuery.close();
                sQLiteDatabaseO.setTransactionSuccessful();
                return arrayList;
            } catch (Throwable th) {
                cursorRawQuery.close();
                throw th;
            }
        } finally {
            sQLiteDatabaseO.endTransaction();
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.d
    public final boolean w0(com.google.android.datatransport.runtime.i iVar) {
        Boolean bool;
        SQLiteDatabase sQLiteDatabaseO = o();
        sQLiteDatabaseO.beginTransaction();
        try {
            Long lX = x(sQLiteDatabaseO, iVar);
            if (lX == null) {
                bool = Boolean.FALSE;
            } else {
                Cursor cursorRawQuery = o().rawQuery("SELECT 1 FROM events WHERE context_id = ? LIMIT 1", new String[]{lX.toString()});
                try {
                    Boolean boolValueOf = Boolean.valueOf(cursorRawQuery.moveToNext());
                    cursorRawQuery.close();
                    bool = boolValueOf;
                } catch (Throwable th) {
                    cursorRawQuery.close();
                    throw th;
                }
            }
            sQLiteDatabaseO.setTransactionSuccessful();
            sQLiteDatabaseO.endTransaction();
            return bool.booleanValue();
        } catch (Throwable th2) {
            sQLiteDatabaseO.endTransaction();
            throw th2;
        }
    }

    public final Object y(j jVar) {
        SQLiteDatabase sQLiteDatabaseO = o();
        sQLiteDatabaseO.beginTransaction();
        try {
            Object objApply = jVar.apply(sQLiteDatabaseO);
            sQLiteDatabaseO.setTransactionSuccessful();
            return objApply;
        } finally {
            sQLiteDatabaseO.endTransaction();
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.d
    public final void z(final long j10, final com.google.android.datatransport.runtime.i iVar) {
        y(new j() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.g
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.j
            public final Object apply(Object obj) {
                SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) obj;
                ContentValues contentValues = new ContentValues();
                contentValues.put("next_request_ms", Long.valueOf(j10));
                com.google.android.datatransport.runtime.i iVar2 = iVar;
                String str = iVar2.f12589a;
                z2.c cVar = iVar2.f12591c;
                if (sQLiteDatabase.update("transport_contexts", contentValues, "backend_name = ? and priority = ?", new String[]{str, String.valueOf(n3.a.a(cVar))}) < 1) {
                    contentValues.put("backend_name", iVar2.f12589a);
                    contentValues.put("priority", Integer.valueOf(n3.a.a(cVar)));
                    sQLiteDatabase.insert("transport_contexts", null, contentValues);
                }
                return null;
            }
        });
    }
}
