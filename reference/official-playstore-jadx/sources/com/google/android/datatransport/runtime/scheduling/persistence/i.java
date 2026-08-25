package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;
import androidx.appcompat.widget.z;
import androidx.emoji2.text.e0;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class i implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f12628a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ m f12629b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f12630c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f12631d;

    public /* synthetic */ i(m mVar, Object obj, Object obj2, int i10) {
        this.f12628a = i10;
        this.f12629b = mVar;
        this.f12631d = obj;
        this.f12630c = obj2;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.j
    public final Object apply(Object obj) throws Throwable {
        Cursor cursor;
        String str;
        long jInsert;
        g3.c cVar;
        int i10 = this.f12628a;
        String str2 = "bytes";
        int i11 = 5;
        int i12 = 4;
        int i13 = 3;
        g3.c cVar2 = g3.c.CACHE_FULL;
        int i14 = 2;
        Object obj2 = this.f12630c;
        Object obj3 = this.f12631d;
        m mVar = this.f12629b;
        int i15 = 0;
        switch (i10) {
            case 0:
                ArrayList arrayList = (ArrayList) obj3;
                com.google.android.datatransport.runtime.i iVar = (com.google.android.datatransport.runtime.i) obj2;
                Cursor cursor2 = (Cursor) obj;
                while (cursor2.moveToNext()) {
                    long j10 = cursor2.getLong(0);
                    boolean z = cursor2.getInt(7) != 0;
                    z zVar = new z();
                    zVar.f1847f = new HashMap();
                    String string = cursor2.getString(1);
                    if (string == null) {
                        throw new NullPointerException("Null transportName");
                    }
                    zVar.f1842a = string;
                    zVar.f1845d = Long.valueOf(cursor2.getLong(i14));
                    zVar.f1846e = Long.valueOf(cursor2.getLong(3));
                    if (z) {
                        String string2 = cursor2.getString(4);
                        zVar.f1844c = new com.google.android.datatransport.runtime.n(string2 == null ? m.f12634p : new z2.b(string2), cursor2.getBlob(5));
                        str = str2;
                    } else {
                        String string3 = cursor2.getString(4);
                        z2.b bVar = string3 == null ? m.f12634p : new z2.b(string3);
                        Cursor cursorQuery = mVar.o().query("event_payloads", new String[]{str2}, "event_id = ?", new String[]{String.valueOf(j10)}, null, null, "sequence_num");
                        try {
                            ArrayList arrayList2 = new ArrayList();
                            int length = 0;
                            while (cursorQuery.moveToNext()) {
                                byte[] blob = cursorQuery.getBlob(0);
                                arrayList2.add(blob);
                                length += blob.length;
                                break;
                            }
                            byte[] bArr = new byte[length];
                            int i16 = 0;
                            int length2 = 0;
                            while (i16 < arrayList2.size()) {
                                byte[] bArr2 = (byte[]) arrayList2.get(i16);
                                String str3 = str2;
                                cursor = cursorQuery;
                                try {
                                    System.arraycopy(bArr2, 0, bArr, length2, bArr2.length);
                                    length2 += bArr2.length;
                                    i16++;
                                    cursorQuery = cursor;
                                    str2 = str3;
                                } catch (Throwable th) {
                                    th = th;
                                    cursor.close();
                                    throw th;
                                }
                            }
                            str = str2;
                            cursorQuery.close();
                            zVar.f1844c = new com.google.android.datatransport.runtime.n(bVar, bArr);
                        } catch (Throwable th2) {
                            th = th2;
                            cursor = cursorQuery;
                        }
                    }
                    if (!cursor2.isNull(6)) {
                        zVar.f1843b = Integer.valueOf(cursor2.getInt(6));
                    }
                    arrayList.add(new b(j10, iVar, zVar.c()));
                    str2 = str;
                    i14 = 2;
                }
                return null;
            case 1:
                com.google.android.datatransport.runtime.h hVar = (com.google.android.datatransport.runtime.h) obj3;
                com.google.android.datatransport.runtime.i iVar2 = (com.google.android.datatransport.runtime.i) obj2;
                SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) obj;
                long jSimpleQueryForLong = mVar.o().compileStatement("PRAGMA page_size").simpleQueryForLong() * mVar.o().compileStatement("PRAGMA page_count").simpleQueryForLong();
                a aVar = mVar.f12638n;
                if (jSimpleQueryForLong >= aVar.f12610a) {
                    mVar.l(1L, cVar2, hVar.f12583a);
                    return -1L;
                }
                Long lX = m.x(sQLiteDatabase, iVar2);
                if (lX != null) {
                    jInsert = lX.longValue();
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("backend_name", iVar2.f12589a);
                    contentValues.put("priority", Integer.valueOf(n3.a.a(iVar2.f12591c)));
                    contentValues.put("next_request_ms", (Integer) 0);
                    byte[] bArr3 = iVar2.f12590b;
                    if (bArr3 != null) {
                        contentValues.put("extras", Base64.encodeToString(bArr3, 0));
                    }
                    jInsert = sQLiteDatabase.insert("transport_contexts", null, contentValues);
                }
                int i17 = aVar.f12614e;
                byte[] bArr4 = hVar.f12585c.f12601b;
                boolean z5 = bArr4.length <= i17;
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("context_id", Long.valueOf(jInsert));
                contentValues2.put("transport_name", hVar.f12583a);
                contentValues2.put("timestamp_ms", Long.valueOf(hVar.f12586d));
                contentValues2.put("uptime_ms", Long.valueOf(hVar.f12587e));
                contentValues2.put("payload_encoding", hVar.f12585c.f12600a.f23163a);
                contentValues2.put("code", hVar.f12584b);
                contentValues2.put("num_attempts", (Integer) 0);
                contentValues2.put("inline", Boolean.valueOf(z5));
                contentValues2.put("payload", z5 ? bArr4 : new byte[0]);
                long jInsert2 = sQLiteDatabase.insert("events", null, contentValues2);
                if (!z5) {
                    int iCeil = (int) Math.ceil(((double) bArr4.length) / ((double) i17));
                    for (int i18 = 1; i18 <= iCeil; i18++) {
                        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr4, (i18 - 1) * i17, Math.min(i18 * i17, bArr4.length));
                        ContentValues contentValues3 = new ContentValues();
                        contentValues3.put("event_id", Long.valueOf(jInsert2));
                        contentValues3.put("sequence_num", Integer.valueOf(i18));
                        contentValues3.put("bytes", bArrCopyOfRange);
                        sQLiteDatabase.insert("event_payloads", null, contentValues3);
                    }
                }
                for (Map.Entry entry : Collections.unmodifiableMap(hVar.f12588f).entrySet()) {
                    ContentValues contentValues4 = new ContentValues();
                    contentValues4.put("event_id", Long.valueOf(jInsert2));
                    contentValues4.put(ContentDisposition.Parameters.Name, (String) entry.getKey());
                    contentValues4.put("value", (String) entry.getValue());
                    sQLiteDatabase.insert("event_metadata", null, contentValues4);
                }
                return Long.valueOf(jInsert2);
            default:
                HashMap map = (HashMap) obj3;
                e0 e0Var = (e0) obj2;
                ArrayList arrayList3 = (ArrayList) e0Var.f2601m;
                Cursor cursor3 = (Cursor) obj;
                while (cursor3.moveToNext()) {
                    String string4 = cursor3.getString(i15);
                    int i19 = cursor3.getInt(1);
                    g3.c cVar3 = g3.c.REASON_UNKNOWN;
                    if (i19 != 0) {
                        if (i19 == 1) {
                            cVar3 = g3.c.MESSAGE_TOO_OLD;
                        } else if (i19 == 2) {
                            cVar = cVar2;
                        } else if (i19 == i13) {
                            cVar3 = g3.c.PAYLOAD_TOO_BIG;
                        } else if (i19 == i12) {
                            cVar3 = g3.c.MAX_RETRIES_REACHED;
                        } else if (i19 == i11) {
                            cVar3 = g3.c.INVALID_PAYLOD;
                        } else if (i19 == 6) {
                            cVar3 = g3.c.SERVER_ERROR;
                        } else {
                            ac.b.x("SQLiteEventStore", Integer.valueOf(i19), "%n is not valid. No matched LogEventDropped-Reason found. Treated it as REASON_UNKNOWN");
                        }
                        cVar = cVar3;
                    } else {
                        cVar = cVar3;
                    }
                    long j11 = cursor3.getLong(2);
                    if (!map.containsKey(string4)) {
                        map.put(string4, new ArrayList());
                    }
                    ((List) map.get(string4)).add(new g3.d(j11, cVar));
                    i15 = 0;
                    i11 = 5;
                    i12 = 4;
                    i13 = 3;
                }
                for (Map.Entry entry2 : map.entrySet()) {
                    int i20 = g3.e.f15477c;
                    new ArrayList();
                    arrayList3.add(new g3.e((String) entry2.getKey(), Collections.unmodifiableList((List) entry2.getValue())));
                }
                long jA = mVar.f12636l.a();
                SQLiteDatabase sQLiteDatabaseO = mVar.o();
                sQLiteDatabaseO.beginTransaction();
                try {
                    Cursor cursorRawQuery = sQLiteDatabaseO.rawQuery("SELECT last_metrics_upload_ms FROM global_log_event_state LIMIT 1", new String[0]);
                    try {
                        cursorRawQuery.moveToNext();
                        g3.g gVar = new g3.g(cursorRawQuery.getLong(0), jA);
                        cursorRawQuery.close();
                        sQLiteDatabaseO.setTransactionSuccessful();
                        sQLiteDatabaseO.endTransaction();
                        e0Var.f2600l = gVar;
                        e0Var.f2602n = new g3.b(new g3.f(mVar.o().compileStatement("PRAGMA page_size").simpleQueryForLong() * mVar.o().compileStatement("PRAGMA page_count").simpleQueryForLong(), a.f12609f.f12610a));
                        e0Var.f2603o = (String) mVar.f12639o.get();
                        return new g3.a((g3.g) e0Var.f2600l, Collections.unmodifiableList(arrayList3), (g3.b) e0Var.f2602n, (String) e0Var.f2603o);
                    } catch (Throwable th3) {
                        cursorRawQuery.close();
                        throw th3;
                    }
                } catch (Throwable th4) {
                    sQLiteDatabaseO.endTransaction();
                    throw th4;
                }
        }
    }
}
