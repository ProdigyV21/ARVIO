package io.sentry.internal.debugmeta;

import a9.a1;
import a9.d1;
import a9.y0;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Build;
import android.os.Handler;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.support.v4.media.session.v;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.appcompat.app.d0;
import androidx.appcompat.app.s0;
import androidx.core.provider.h;
import androidx.core.provider.o;
import androidx.core.view.b2;
import androidx.core.view.i1;
import androidx.core.view.n2;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.lifecycle.j0;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkDatabase_Impl;
import androidx.work.impl.g;
import androidx.work.impl.model.e;
import androidx.work.impl.model.l;
import androidx.work.impl.model.s;
import androidx.work.impl.model.t;
import androidx.work.impl.p;
import androidx.work.impl.utils.futures.k;
import c9.f;
import com.arvio.tv.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.tasks.i;
import com.google.common.util.concurrent.p0;
import com.google.common.util.concurrent.w0;
import com.squareup.moshi.q;
import com.typesafe.config.impl.d;
import com.typesafe.config.impl.f1;
import com.typesafe.config.impl.m1;
import com.typesafe.config.impl.n1;
import com.typesafe.config.impl.o1;
import io.sentry.ILogger;
import io.sentry.w5;
import j$.util.DesugarCollections;
import j$.util.Objects;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.WeakHashMap;
import m2.a0;
import m2.x;
import m2.z;
import xb.j;
import xb.m;
import y1.r0;

/* JADX INFO: loaded from: classes5.dex */
public class c implements a, n.a, a0, p, androidx.work.impl.model.b, e, l, t, f, com.google.android.gms.tasks.c, d, l6.b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f17366i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f17367l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f17368m;

    public /* synthetic */ c(int i10, Object obj, boolean z, Object obj2) {
        this.f17366i = i10;
        this.f17367l = obj;
        this.f17368m = obj2;
    }

    public static c B(String... strArr) {
        try {
            m[] mVarArr = new m[strArr.length];
            j jVar = new j();
            for (int i10 = 0; i10 < strArr.length; i10++) {
                q.R(jVar, strArr[i10]);
                jVar.readByte();
                mVarArr[i10] = jVar.c0(jVar.f22759l);
            }
            return new c(23, (String[]) strArr.clone(), false, xb.b.d(mVarArr));
        } catch (IOException e5) {
            throw new AssertionError(e5);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0047 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static io.sentry.internal.debugmeta.c v(android.content.Context r5) {
        /*
            java.lang.String r0 = "generatefid.lock"
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: java.nio.channels.OverlappingFileLockException -> L31 java.lang.Error -> L35 java.io.IOException -> L37
            java.io.File r5 = r5.getFilesDir()     // Catch: java.nio.channels.OverlappingFileLockException -> L31 java.lang.Error -> L35 java.io.IOException -> L37
            r2.<init>(r5, r0)     // Catch: java.nio.channels.OverlappingFileLockException -> L31 java.lang.Error -> L35 java.io.IOException -> L37
            java.io.RandomAccessFile r5 = new java.io.RandomAccessFile     // Catch: java.nio.channels.OverlappingFileLockException -> L31 java.lang.Error -> L35 java.io.IOException -> L37
            java.lang.String r0 = "rw"
            r5.<init>(r2, r0)     // Catch: java.nio.channels.OverlappingFileLockException -> L31 java.lang.Error -> L35 java.io.IOException -> L37
            java.nio.channels.FileChannel r5 = r5.getChannel()     // Catch: java.nio.channels.OverlappingFileLockException -> L31 java.lang.Error -> L35 java.io.IOException -> L37
            java.nio.channels.FileLock r0 = r5.lock()     // Catch: java.nio.channels.OverlappingFileLockException -> L2a java.lang.Error -> L2d java.io.IOException -> L2f
            io.sentry.internal.debugmeta.c r2 = new io.sentry.internal.debugmeta.c     // Catch: java.nio.channels.OverlappingFileLockException -> L24 java.lang.Error -> L26 java.io.IOException -> L28
            r3 = 22
            r4 = 0
            r2.<init>(r3, r5, r4, r0)     // Catch: java.nio.channels.OverlappingFileLockException -> L24 java.lang.Error -> L26 java.io.IOException -> L28
            return r2
        L24:
            r2 = move-exception
            goto L39
        L26:
            r2 = move-exception
            goto L39
        L28:
            r2 = move-exception
            goto L39
        L2a:
            r2 = move-exception
        L2b:
            r0 = r1
            goto L39
        L2d:
            r2 = move-exception
            goto L2b
        L2f:
            r2 = move-exception
            goto L2b
        L31:
            r2 = move-exception
        L32:
            r5 = r1
            r0 = r5
            goto L39
        L35:
            r2 = move-exception
            goto L32
        L37:
            r2 = move-exception
            goto L32
        L39:
            java.lang.String r3 = "CrossProcessLock"
            java.lang.String r4 = "encountered error while creating and acquiring the lock, ignoring"
            android.util.Log.e(r3, r4, r2)
            if (r0 == 0) goto L45
            r0.release()     // Catch: java.io.IOException -> L45
        L45:
            if (r5 == 0) goto L4a
            r5.close()     // Catch: java.io.IOException -> L4a
        L4a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.internal.debugmeta.c.v(android.content.Context):io.sentry.internal.debugmeta.c");
    }

    public void A(p0 p0Var) {
        k kVar = (k) this.f17368m;
        ((j0) this.f17367l).j(p0Var);
        if (p0Var instanceof z) {
            kVar.h((z) p0Var);
        } else if (p0Var instanceof x) {
            kVar.i(((x) p0Var).f20223a);
        }
    }

    public void C(h hVar) {
        Handler handler = (Handler) this.f17368m;
        a8.e eVar = (a8.e) this.f17367l;
        int i10 = hVar.f2153b;
        if (i10 == 0) {
            handler.post(new w0(eVar, hVar.f2152a, 4));
        } else {
            handler.post(new androidx.core.provider.a(eVar, i10, 0));
        }
    }

    public void D() {
        try {
            ((FileLock) this.f17368m).release();
            ((FileChannel) this.f17367l).close();
        } catch (IOException e5) {
            Log.e("CrossProcessLock", "encountered error while releasing, ignoring", e5);
        }
    }

    public androidx.work.impl.l E(androidx.work.impl.model.j jVar) {
        androidx.work.impl.l lVar;
        synchronized (this.f17367l) {
            lVar = (androidx.work.impl.l) ((LinkedHashMap) this.f17368m).remove(jVar);
        }
        return lVar;
    }

    public List F(String str) {
        List listC1;
        synchronized (this.f17367l) {
            try {
                LinkedHashMap linkedHashMap = (LinkedHashMap) this.f17368m;
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                for (Map.Entry entry : linkedHashMap.entrySet()) {
                    if (kotlin.jvm.internal.p.a(((androidx.work.impl.model.j) entry.getKey()).f6914a, str)) {
                        linkedHashMap2.put(entry.getKey(), entry.getValue());
                    }
                }
                Iterator it = linkedHashMap2.keySet().iterator();
                while (it.hasNext()) {
                    ((LinkedHashMap) this.f17368m).remove((androidx.work.impl.model.j) it.next());
                }
                listC1 = kotlin.collections.x.c1(linkedHashMap2.values());
            } catch (Throwable th) {
                throw th;
            }
        }
        return listC1;
    }

    public void G(androidx.work.impl.l lVar) {
        ((androidx.work.impl.utils.taskexecutor.b) this.f17368m).d(new o((g) this.f17367l, lVar, null, 2));
    }

    public void H(androidx.work.impl.l lVar, int i10) {
        ((androidx.work.impl.utils.taskexecutor.b) this.f17368m).d(new androidx.work.impl.utils.m((g) this.f17367l, lVar, false, i10));
    }

    public androidx.work.impl.l I(androidx.work.impl.model.j jVar) {
        androidx.work.impl.l lVar;
        synchronized (this.f17367l) {
            try {
                LinkedHashMap linkedHashMap = (LinkedHashMap) this.f17368m;
                Object lVar2 = linkedHashMap.get(jVar);
                if (lVar2 == null) {
                    lVar2 = new androidx.work.impl.l(jVar);
                    linkedHashMap.put(jVar, lVar2);
                }
                lVar = (androidx.work.impl.l) lVar2;
            } catch (Throwable th) {
                throw th;
            }
        }
        return lVar;
    }

    public x6.j0 J(int i10) {
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        boolean z = false;
        while (i10 != -1) {
            y0 y0Var = (y0) ((a1) this.f17368m).f325l.get(i10);
            String str = ((d1) this.f17367l).f399l.get(y0Var.f760n);
            int iOrdinal = y0Var.f761o.ordinal();
            if (iOrdinal == 0) {
                linkedList2.addFirst(str);
            } else if (iOrdinal == 1) {
                linkedList.addFirst(str);
            } else if (iOrdinal == 2) {
                linkedList2.addFirst(str);
                z = true;
            }
            i10 = y0Var.f759m;
        }
        return new x6.j0(linkedList, linkedList2, Boolean.valueOf(z));
    }

    public int K(Context context, com.google.android.gms.common.api.g gVar) {
        com.google.android.gms.common.internal.t.i(context);
        com.google.android.gms.common.internal.t.i(gVar);
        int iB = 0;
        if (!gVar.requiresGooglePlayServices()) {
            return 0;
        }
        int minApkVersion = gVar.getMinApkVersion();
        int iM = M(minApkVersion);
        if (iM != -1) {
            return iM;
        }
        SparseIntArray sparseIntArray = (SparseIntArray) this.f17367l;
        synchronized (sparseIntArray) {
            int i10 = 0;
            while (true) {
                try {
                    if (i10 >= sparseIntArray.size()) {
                        iB = -1;
                        break;
                    }
                    int iKeyAt = sparseIntArray.keyAt(i10);
                    if (iKeyAt > minApkVersion && sparseIntArray.get(iKeyAt) == 0) {
                        break;
                    }
                    i10++;
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (iB == -1) {
                iB = ((o3.e) this.f17368m).b(context, minApkVersion);
            }
            sparseIntArray.put(minApkVersion, iB);
        }
        return iB;
    }

    @Override // com.typesafe.config.impl.d
    public com.typesafe.config.impl.g L(com.typesafe.config.impl.g gVar, String str) {
        n1 n1VarC = ((m1) this.f17367l).c(gVar, (o1) this.f17368m);
        this.f17367l = n1VarC.f14593a;
        return n1VarC.f14594b;
    }

    public int M(int i10) {
        int i11;
        SparseIntArray sparseIntArray = (SparseIntArray) this.f17367l;
        synchronized (sparseIntArray) {
            i11 = sparseIntArray.get(i10, -1);
        }
        return i11;
    }

    public void N(boolean z, Status status) {
        HashMap map;
        HashMap map2;
        Map map3 = (Map) this.f17367l;
        synchronized (map3) {
            map = new HashMap(map3);
        }
        Map map4 = (Map) this.f17368m;
        synchronized (map4) {
            map2 = new HashMap(map4);
        }
        for (Map.Entry entry : map.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((BasePendingResult) entry.getKey()).forceFailureUnlessReady(status);
            }
        }
        for (Map.Entry entry2 : map2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((i) entry2.getKey()).c(new ApiException(status));
            }
        }
    }

    @Override // androidx.work.impl.model.b
    public void a(androidx.work.impl.model.a aVar) {
        WorkDatabase_Impl workDatabase_Impl = (WorkDatabase_Impl) this.f17367l;
        workDatabase_Impl.b();
        workDatabase_Impl.c();
        try {
            ((androidx.work.impl.model.c) this.f17368m).j(aVar);
            workDatabase_Impl.o();
        } finally {
            workDatabase_Impl.k();
        }
    }

    @Override // n.a
    public boolean b(n.b bVar, Menu menu) {
        return ((n.a) this.f17367l).b(bVar, menu);
    }

    @Override // l6.b
    public l6.l c() {
        return (l6.l) this.f17368m;
    }

    @Override // androidx.work.impl.model.b
    public ArrayList d(String str) {
        WorkDatabase_Impl workDatabase_Impl = (WorkDatabase_Impl) this.f17367l;
        r0 r0VarK = r0.k(1, "SELECT work_spec_id FROM dependency WHERE prerequisite_id=?");
        if (str == null) {
            r0VarK.C0(1);
        } else {
            r0VarK.e0(1, str);
        }
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            ArrayList arrayList = new ArrayList(cursorM.getCount());
            while (cursorM.moveToNext()) {
                arrayList.add(cursorM.isNull(0) ? null : cursorM.getString(0));
            }
            return arrayList;
        } finally {
            cursorM.close();
            r0VarK.l();
        }
    }

    @Override // n.a
    public boolean e(n.b bVar, Menu menu) {
        ViewGroup viewGroup = ((s0) this.f17368m).K;
        WeakHashMap weakHashMap = b2.f2200a;
        i1.c(viewGroup);
        return ((n.a) this.f17367l).e(bVar, menu);
    }

    @Override // androidx.work.impl.model.t
    public ArrayList f(String str) {
        WorkDatabase_Impl workDatabase_Impl = (WorkDatabase_Impl) this.f17367l;
        r0 r0VarK = r0.k(1, "SELECT DISTINCT tag FROM worktag WHERE work_spec_id=?");
        if (str == null) {
            r0VarK.C0(1);
        } else {
            r0VarK.e0(1, str);
        }
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            ArrayList arrayList = new ArrayList(cursorM.getCount());
            while (cursorM.moveToNext()) {
                arrayList.add(cursorM.isNull(0) ? null : cursorM.getString(0));
            }
            return arrayList;
        } finally {
            cursorM.close();
            r0VarK.l();
        }
    }

    @Override // androidx.work.impl.model.l
    public void g(androidx.work.impl.model.k kVar) {
        WorkDatabase_Impl workDatabase_Impl = (WorkDatabase_Impl) this.f17367l;
        workDatabase_Impl.b();
        workDatabase_Impl.c();
        try {
            ((androidx.work.impl.model.c) this.f17368m).j(kVar);
            workDatabase_Impl.o();
        } finally {
            workDatabase_Impl.k();
        }
    }

    @Override // c9.f
    public String getString(int i10) {
        return ((d1) this.f17367l).f399l.get(i10);
    }

    @Override // androidx.work.impl.model.b
    public boolean h(String str) {
        WorkDatabase_Impl workDatabase_Impl = (WorkDatabase_Impl) this.f17367l;
        r0 r0VarK = r0.k(1, "SELECT COUNT(*)=0 FROM dependency WHERE work_spec_id=? AND prerequisite_id IN (SELECT id FROM workspec WHERE state!=2)");
        if (str == null) {
            r0VarK.C0(1);
        } else {
            r0VarK.e0(1, str);
        }
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            boolean z = false;
            if (cursorM.moveToFirst()) {
                z = cursorM.getInt(0) != 0;
            }
            return z;
        } finally {
            cursorM.close();
            r0VarK.l();
        }
    }

    @Override // n.a
    public boolean i(n.b bVar, MenuItem menuItem) {
        return ((n.a) this.f17367l).i(bVar, menuItem);
    }

    @Override // androidx.work.impl.model.e
    public void j(androidx.work.impl.model.d dVar) {
        WorkDatabase workDatabase = (WorkDatabase) this.f17367l;
        workDatabase.b();
        workDatabase.c();
        try {
            ((androidx.work.impl.model.c) this.f17368m).j(dVar);
            workDatabase.o();
        } finally {
            workDatabase.k();
        }
    }

    @Override // c9.f
    public boolean k(int i10) {
        return ((Boolean) J(i10).f22589m).booleanValue();
    }

    @Override // androidx.work.impl.model.l
    public ArrayList l(String str) {
        WorkDatabase_Impl workDatabase_Impl = (WorkDatabase_Impl) this.f17367l;
        r0 r0VarK = r0.k(1, "SELECT name FROM workname WHERE work_spec_id=?");
        if (str == null) {
            r0VarK.C0(1);
        } else {
            r0VarK.e0(1, str);
        }
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            ArrayList arrayList = new ArrayList(cursorM.getCount());
            while (cursorM.moveToNext()) {
                arrayList.add(cursorM.isNull(0) ? null : cursorM.getString(0));
            }
            return arrayList;
        } finally {
            cursorM.close();
            r0VarK.l();
        }
    }

    @Override // l6.b
    public l6.m m(String str) {
        f1 f1Var = (f1) this.f17367l;
        if (com.typesafe.config.impl.x.f()) {
            com.typesafe.config.impl.x.e("Looking for '" + str + "' relative to " + f1Var);
        }
        return f1Var.q(str);
    }

    @Override // androidx.work.impl.p
    public void n(androidx.work.impl.l lVar) {
        H(lVar, -512);
    }

    @Override // c9.f
    public String o(int i10) {
        x6.j0 j0VarJ = J(i10);
        List list = (List) j0VarJ.f22587i;
        String strU0 = kotlin.collections.x.u0((List) j0VarJ.f22588l, ".", null, null, null, 62);
        if (list.isEmpty()) {
            return strU0;
        }
        return kotlin.collections.x.u0(list, DomExceptionUtils.SEPARATOR, null, null, null, 62) + '/' + strU0;
    }

    @Override // com.google.android.gms.tasks.c
    public void onComplete(com.google.android.gms.tasks.h hVar) {
        ((Map) ((c) this.f17368m).f17368m).remove((i) this.f17367l);
    }

    @Override // io.sentry.internal.debugmeta.a
    public List p() {
        ILogger iLogger = (ILogger) this.f17367l;
        ArrayList arrayList = new ArrayList();
        try {
            Enumeration<URL> resources = ((ClassLoader) this.f17368m).getResources("sentry-debug-meta.properties");
            while (resources.hasMoreElements()) {
                URL urlNextElement = resources.nextElement();
                try {
                    InputStream inputStreamOpenStream = urlNextElement.openStream();
                    try {
                        Properties properties = new Properties();
                        properties.load(inputStreamOpenStream);
                        arrayList.add(properties);
                        iLogger.q(w5.INFO, "Debug Meta Data Properties loaded from %s", urlNextElement);
                        if (inputStreamOpenStream != null) {
                            inputStreamOpenStream.close();
                        }
                    } catch (Throwable th) {
                        if (inputStreamOpenStream != null) {
                            try {
                                inputStreamOpenStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } catch (RuntimeException e5) {
                    iLogger.k(w5.ERROR, e5, "%s file is malformed.", urlNextElement);
                }
            }
        } catch (IOException e6) {
            iLogger.k(w5.ERROR, e6, "Failed to load %s", "sentry-debug-meta.properties");
        }
        if (!arrayList.isEmpty()) {
            return arrayList;
        }
        iLogger.q(w5.INFO, "No %s file was found.", "sentry-debug-meta.properties");
        return null;
    }

    @Override // androidx.work.impl.model.e
    public Long q(String str) {
        WorkDatabase workDatabase = (WorkDatabase) this.f17367l;
        r0 r0VarK = r0.k(1, "SELECT long_value FROM Preference where `key`=?");
        r0VarK.e0(1, str);
        workDatabase.b();
        Cursor cursorM = workDatabase.m(r0VarK);
        try {
            Long lValueOf = null;
            if (cursorM.moveToFirst() && !cursorM.isNull(0)) {
                lValueOf = Long.valueOf(cursorM.getLong(0));
            }
            return lValueOf;
        } finally {
            cursorM.close();
            r0VarK.l();
        }
    }

    @Override // androidx.work.impl.model.t
    public void r(String str, Set set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            s sVar = new s((String) it.next(), str);
            WorkDatabase_Impl workDatabase_Impl = (WorkDatabase_Impl) this.f17367l;
            workDatabase_Impl.b();
            workDatabase_Impl.c();
            try {
                ((androidx.work.impl.model.c) this.f17368m).j(sVar);
                workDatabase_Impl.o();
            } finally {
                workDatabase_Impl.k();
            }
        }
    }

    @Override // androidx.work.impl.model.b
    public boolean s(String str) {
        WorkDatabase_Impl workDatabase_Impl = (WorkDatabase_Impl) this.f17367l;
        r0 r0VarK = r0.k(1, "SELECT COUNT(*)>0 FROM dependency WHERE prerequisite_id=?");
        if (str == null) {
            r0VarK.C0(1);
        } else {
            r0VarK.e0(1, str);
        }
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            boolean z = false;
            if (cursorM.moveToFirst()) {
                z = cursorM.getInt(0) != 0;
            }
            return z;
        } finally {
            cursorM.close();
            r0VarK.l();
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [androidx.appcompat.app.u, java.lang.Object] */
    @Override // n.a
    public void t(n.b bVar) {
        ((n.a) this.f17367l).t(bVar);
        s0 s0Var = (s0) this.f17368m;
        if (s0Var.G != null) {
            s0Var.f1190v.getDecorView().removeCallbacks(s0Var.H);
        }
        if (s0Var.F != null) {
            n2 n2Var = s0Var.I;
            if (n2Var != null) {
                n2Var.b();
            }
            n2 n2VarA = b2.a(s0Var.F);
            n2VarA.a(0.0f);
            s0Var.I = n2VarA;
            n2VarA.d(new d0(this, 2));
        }
        s0Var.x.onSupportActionModeFinished(s0Var.E);
        s0Var.E = null;
        ViewGroup viewGroup = s0Var.K;
        WeakHashMap weakHashMap = b2.f2200a;
        i1.c(viewGroup);
        s0Var.K();
    }

    public String toString() {
        c cVar;
        switch (this.f17366i) {
            case 17:
                StringBuilder sb2 = new StringBuilder(100);
                sb2.append(this.f17368m.getClass().getSimpleName());
                sb2.append('{');
                ArrayList arrayList = (ArrayList) this.f17367l;
                int size = arrayList.size();
                for (int i10 = 0; i10 < size; i10++) {
                    sb2.append((String) arrayList.get(i10));
                    if (i10 < size - 1) {
                        sb2.append(", ");
                    }
                }
                sb2.append('}');
                return sb2.toString();
            case 24:
                StringBuffer stringBuffer = new StringBuffer("[");
                c cVar2 = (c) this.f17368m;
                if (cVar2 == null) {
                    cVar = this;
                } else {
                    cVar = new c(24, this.f17367l, false, null);
                    while (cVar2 != null) {
                        c cVar3 = new c(24, cVar2.f17367l, false, cVar);
                        cVar2 = (c) cVar2.f17368m;
                        cVar = cVar3;
                    }
                }
                while (cVar != null) {
                    c cVar4 = (c) cVar.f17368m;
                    stringBuffer.append(cVar.f17367l.toString());
                    if (cVar4 != null) {
                        stringBuffer.append(" <= ");
                    }
                    cVar = cVar4;
                }
                stringBuffer.append("]");
                return stringBuffer.toString();
            case 25:
                return "ResultWithPath(result=" + ((n1) this.f17367l) + ", pathFromRoot=" + ((c) this.f17368m) + ")";
            case 26:
                return "ValueWithPath(value=" + ((com.typesafe.config.impl.g) this.f17367l) + ", pathFromRoot=" + ((c) this.f17368m) + ")";
            default:
                return super.toString();
        }
    }

    @Override // l6.b
    public c u(l6.l lVar) {
        return new c(28, (f1) this.f17367l, false, lVar.e(0).d(null));
    }

    public void w(Object obj, String str) {
        int length = str.length();
        String strValueOf = String.valueOf(obj);
        ((ArrayList) this.f17367l).add(androidx.compose.material3.d.q(new StringBuilder(length + 1 + strValueOf.length()), str, "=", strValueOf));
    }

    public boolean x(androidx.work.impl.model.j jVar) {
        boolean zContainsKey;
        synchronized (this.f17367l) {
            zContainsKey = ((LinkedHashMap) this.f17368m).containsKey(jVar);
        }
        return zContainsKey;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public c3.d y(java.lang.String r14) {
        /*
            Method dump skipped, instruction units count: 273
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.internal.debugmeta.c.y(java.lang.String):c3.d");
    }

    public String z(String str) {
        String str2 = (String) this.f17368m;
        Resources resources = (Resources) this.f17367l;
        int identifier = resources.getIdentifier(str, "string", str2);
        if (identifier == 0) {
            return null;
        }
        return resources.getString(identifier);
    }

    public /* synthetic */ c(Object obj, Object obj2, int i10) {
        this.f17366i = i10;
        this.f17368m = obj;
        this.f17367l = obj2;
    }

    public c(c cVar, i iVar) {
        this.f17366i = 16;
        this.f17367l = iVar;
        Objects.requireNonNull(cVar);
        this.f17368m = cVar;
    }

    public /* synthetic */ c(Object obj) {
        this.f17366i = 17;
        this.f17368m = obj;
        this.f17367l = new ArrayList();
    }

    public c(f1 f1Var) {
        this.f17366i = 28;
        this.f17367l = f1Var;
        this.f17368m = f1Var.f14533b.e(0).d(null).b(true);
    }

    public c(WorkDatabase_Impl workDatabase_Impl, int i10) {
        this.f17366i = i10;
        switch (i10) {
            case 9:
                this.f17367l = workDatabase_Impl;
                this.f17368m = new androidx.work.impl.model.c(workDatabase_Impl, 3);
                break;
            case 10:
                this.f17367l = workDatabase_Impl;
                this.f17368m = new androidx.work.impl.model.c(workDatabase_Impl, 6);
                new androidx.work.impl.model.i(workDatabase_Impl, 20);
                break;
            default:
                this.f17367l = workDatabase_Impl;
                this.f17368m = new androidx.work.impl.model.c(workDatabase_Impl, 0);
                break;
        }
    }

    public c(ILogger iLogger) {
        this.f17366i = 0;
        ClassLoader classLoader = c.class.getClassLoader();
        this.f17367l = iLogger;
        this.f17368m = f4.f.e(classLoader);
    }

    public c(WorkDatabase workDatabase) {
        this.f17366i = 8;
        this.f17367l = workDatabase;
        this.f17368m = new androidx.work.impl.model.c(workDatabase, 1);
    }

    public c(int i10) {
        this.f17366i = i10;
        switch (i10) {
            case 5:
                this.f17367l = new Object();
                this.f17368m = new LinkedHashMap();
                break;
            case 13:
                this.f17367l = null;
                this.f17368m = null;
                break;
            case 14:
                this.f17367l = DesugarCollections.synchronizedMap(new WeakHashMap());
                this.f17368m = DesugarCollections.synchronizedMap(new WeakHashMap());
                break;
            case 19:
                o3.e eVar = o3.e.f20741d;
                this.f17367l = new SparseIntArray();
                this.f17368m = eVar;
                break;
            case 21:
                break;
            default:
                this.f17367l = new j0();
                this.f17368m = new k();
                A(a0.f20172e);
                break;
        }
    }

    public c(Context context, int i10) {
        this.f17366i = i10;
        switch (i10) {
            case 18:
                com.google.android.gms.common.internal.t.i(context);
                Resources resources = context.getResources();
                this.f17367l = resources;
                this.f17368m = resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue);
                break;
            default:
                this.f17368m = null;
                this.f17367l = context;
                break;
        }
    }

    public c(EditText editText) {
        this.f17366i = 29;
        this.f17367l = editText;
        e1.i iVar = new e1.i(editText);
        this.f17368m = iVar;
        editText.addTextChangedListener(iVar);
        if (e1.a.f15001b == null) {
            synchronized (e1.a.f15000a) {
                try {
                    if (e1.a.f15001b == null) {
                        e1.a aVar = new e1.a();
                        try {
                            e1.a.f15002c = Class.forName("android.text.DynamicLayout$ChangeWatcher", false, e1.a.class.getClassLoader());
                        } catch (Throwable unused) {
                        }
                        e1.a.f15001b = aVar;
                    }
                } finally {
                }
            }
        }
        editText.setEditableFactory(e1.a.f15001b);
    }

    public c(Context context, v vVar) {
        this.f17366i = 1;
        MediaSessionCompat$Token mediaSessionCompat$Token = vVar.f897a.f888c;
        if (mediaSessionCompat$Token != null) {
            this.f17368m = Collections.synchronizedSet(new HashSet());
            if (Build.VERSION.SDK_INT >= 29) {
                this.f17367l = new android.support.v4.media.session.h(context, mediaSessionCompat$Token);
                return;
            } else {
                this.f17367l = new android.support.v4.media.session.g(context, mediaSessionCompat$Token);
                return;
            }
        }
        throw new IllegalArgumentException("sessionToken must not be null");
    }
}
