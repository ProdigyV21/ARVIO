package androidx.work.impl;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import androidx.core.view.a2;
import androidx.core.view.b2;
import androidx.core.view.i2;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.extractor.text.ttml.TtmlNode;
import c2.d;
import com.arvio.tv.R;
import g8.b1;
import io.ktor.http.ContentDisposition;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.collections.a0;
import kotlin.collections.r0;
import kotlin.collections.x;
import kotlin.collections.y;
import m2.g0;
import v9.h0;
import v9.t0;
import y1.o0;
import y1.z;

/* JADX INFO: loaded from: classes3.dex */
public abstract class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f6975a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Method f6976b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f6977c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Field f6978d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Boolean f6979e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Boolean f6980f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static Boolean f6981g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static Boolean f6982h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static Boolean f6983i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static Boolean f6984j;
    public static Boolean k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static Context f6985l;

    public static final List A(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex(TtmlNode.ATTR_ID);
        int columnIndex2 = cursor.getColumnIndex("seq");
        int columnIndex3 = cursor.getColumnIndex("from");
        int columnIndex4 = cursor.getColumnIndex("to");
        z6.c cVarN = t7.a.n();
        while (cursor.moveToNext()) {
            cVarN.add(new a2.d(cursor.getInt(columnIndex), cursor.getInt(columnIndex2), cursor.getString(columnIndex3), cursor.getString(columnIndex4)));
        }
        return x.V0(t7.a.e(cVarN));
    }

    public static final a2.e B(d2.c cVar, String str, boolean z) throws IOException {
        Cursor cursorJ = cVar.j("PRAGMA index_xinfo(`" + str + "`)");
        try {
            int columnIndex = cursorJ.getColumnIndex("seqno");
            int columnIndex2 = cursorJ.getColumnIndex(CmcdConfiguration.KEY_CONTENT_ID);
            int columnIndex3 = cursorJ.getColumnIndex(ContentDisposition.Parameters.Name);
            int columnIndex4 = cursorJ.getColumnIndex("desc");
            if (columnIndex != -1 && columnIndex2 != -1 && columnIndex3 != -1 && columnIndex4 != -1) {
                TreeMap treeMap = new TreeMap();
                TreeMap treeMap2 = new TreeMap();
                while (cursorJ.moveToNext()) {
                    if (cursorJ.getInt(columnIndex2) >= 0) {
                        int i10 = cursorJ.getInt(columnIndex);
                        String string = cursorJ.getString(columnIndex3);
                        String str2 = cursorJ.getInt(columnIndex4) > 0 ? "DESC" : "ASC";
                        treeMap.put(Integer.valueOf(i10), string);
                        treeMap2.put(Integer.valueOf(i10), str2);
                    }
                }
                a2.e eVar = new a2.e(str, z, x.c1(treeMap.values()), x.c1(treeMap2.values()));
                cursorJ.close();
                return eVar;
            }
            cursorJ.close();
            return null;
        } finally {
        }
    }

    public static final Iterator C(Iterator it, int i10, int i11, boolean z, boolean z5) {
        return !it.hasNext() ? y.f19727i : f4.f.n(new r0(i10, i11, it, z5, z, null));
    }

    public static void D(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 1);
        }
    }

    public static Object a(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static androidx.core.provider.e b(androidx.core.provider.e eVar) {
        if (((Double) eVar.f2142n) != null) {
            return eVar;
        }
        return new androidx.core.provider.e((Boolean) eVar.f2140l, (Double) eVar.f2141m, c((Boolean) eVar.f2140l, null, (Double) eVar.f2141m), (Boolean) eVar.f2143o, (Double) eVar.f2144p);
    }

    public static Double c(Boolean bool, Double d4, Double d10) {
        if (d4 != null) {
            return d4;
        }
        double dC = io.sentry.util.q.a().c();
        if (d10 == null || bool == null) {
            return Double.valueOf(dC);
        }
        if (bool.booleanValue()) {
            return Double.valueOf(d10.doubleValue() * dC);
        }
        return Double.valueOf(((1.0d - d10.doubleValue()) * dC) + d10.doubleValue());
    }

    public static final void d(View view) {
        ga.n nVarN = f4.f.n(new i2(view, null));
        while (nVarN.hasNext()) {
            ArrayList arrayList = s((View) nVarN.next()).f22337a;
            for (int iW = t7.a.w(arrayList); -1 < iW; iW--) {
                ((w0.a) arrayList.get(iW)).onRelease();
            }
        }
    }

    public static final void e(WorkDatabase workDatabase, m2.c cVar, m mVar) {
        int i10;
        if (Build.VERSION.SDK_INT < 24) {
            return;
        }
        ArrayList arrayListG = t7.a.G(mVar);
        int i11 = 0;
        while (!arrayListG.isEmpty()) {
            List list = ((m) x.O0(arrayListG)).f6900f;
            if (list.isEmpty()) {
                i10 = 0;
            } else {
                Iterator it = list.iterator();
                i10 = 0;
                while (it.hasNext()) {
                    if (((g0) it.next()).f20199b.f6931j.a() && (i10 = i10 + 1) < 0) {
                        t7.a.P();
                        throw null;
                    }
                }
            }
            i11 += i10;
        }
        if (i11 == 0) {
            return;
        }
        int iY = workDatabase.t().y();
        int i12 = cVar.f20181i;
        if (iY + i11 > i12) {
            throw new IllegalArgumentException(androidx.compose.material3.d.j(i11, ".\nTo address this issue you can: \n1. enqueue less workers or batch some of workers with content uri triggers together;\n2. increase limit via Configuration.Builder.setContentUriTriggerWorkersLimit;\nPlease beware that workers with content uri triggers immediately occupy slots in JobScheduler so no updates to content uris are missed.", androidx.compose.foundation.c.v("Too many workers with contentUriTriggers are enqueued:\ncontentUriTrigger workers limit: ", i12, ";\nalready enqueued count: ", iY, ";\ncurrent enqueue operation count: ")));
        }
    }

    public static void f(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static final void g(int i10, int i11) {
        if (i10 <= 0 || i11 <= 0) {
            throw new IllegalArgumentException((i10 != i11 ? androidx.compose.foundation.c.s("Both size ", i10, " and step ", i11, " must be greater than zero.") : androidx.compose.foundation.c.o(i10, "size ", " must be greater than zero.")).toString());
        }
    }

    public static final void h(io.sentry.util.a aVar, Throwable th) throws IllegalAccessException, InvocationTargetException {
        if (th != null) {
            try {
                g8.b.l(aVar);
                return;
            } catch (Throwable th2) {
                com.google.common.util.concurrent.r0.a(th, th2);
                return;
            }
        }
        if (aVar instanceof AutoCloseable) {
            aVar.close();
        } else {
            if (!(aVar instanceof ExecutorService)) {
                throw new IllegalArgumentException();
            }
            throw null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String i(g8.f r3, y8.d0 r4) {
        /*
            r4.getClass()
            g8.k r0 = r3.d()
            f9.f r1 = r3.getName()
            if (r1 == 0) goto L14
            f9.f r2 = f9.h.f15372a
            boolean r2 = r1.f15369l
            if (r2 != 0) goto L14
            goto L16
        L14:
            f9.f r1 = f9.h.f15374c
        L16:
            java.lang.String r1 = r1.f()
            boolean r2 = r0 instanceof g8.h0
            if (r2 == 0) goto L4a
            g8.h0 r0 = (g8.h0) r0
            f9.c r3 = r0.c()
            boolean r4 = r3.d()
            if (r4 == 0) goto L2b
            return r1
        L2b:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r3 = r3.b()
            r0 = 46
            r2 = 47
            java.lang.String r3 = r3.replace(r0, r2)
            r4.append(r3)
            r4.append(r2)
            r4.append(r1)
            java.lang.String r3 = r4.toString()
            return r3
        L4a:
            boolean r2 = r0 instanceof g8.f
            if (r2 == 0) goto L52
            r2 = r0
            g8.f r2 = (g8.f) r2
            goto L53
        L52:
            r2 = 0
        L53:
            if (r2 == 0) goto L60
            java.lang.String r3 = i(r2, r4)
            r4 = 36
            java.lang.String r3 = androidx.compose.foundation.c.m(r4, r3, r1)
            return r3
        L60:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unexpected container: "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = " for "
            r1.append(r0)
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            r4.<init>(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.t.i(g8.f, y8.d0):java.lang.String");
    }

    public static com.google.firebase.components.b j(String str, String str2) {
        t5.a aVar = new t5.a(str, str2);
        com.google.firebase.components.a aVarB = com.google.firebase.components.b.b(t5.a.class);
        aVarB.f14252e = 1;
        aVarB.f14253f = new a3.b(aVar, 16);
        return aVarB.b();
    }

    public static final h0 k(g8.f fVar, g8.f fVar2) {
        fVar.n().size();
        fVar2.n().size();
        List listN = fVar.n();
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(listN, 10));
        Iterator it = listN.iterator();
        while (it.hasNext()) {
            arrayList.add(((b1) it.next()).g());
        }
        List listN2 = fVar2.n();
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(listN2, 10));
        Iterator it2 = listN2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(new t0(((b1) it2.next()).m()));
        }
        return new h0(kotlin.collections.h0.A0(x.i1(arrayList, arrayList2)), 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final r l(Context context, m2.c cVar) {
        o0 o0Var;
        boolean zContainsKey;
        boolean z;
        androidx.work.impl.utils.taskexecutor.d dVar = new androidx.work.impl.utils.taskexecutor.d(cVar.f20174b);
        Context applicationContext = context.getApplicationContext();
        m2.u uVar = cVar.f20175c;
        int i10 = 11;
        if (context.getResources().getBoolean(R.bool.workmanager_test_configuration)) {
            o0Var = new o0(applicationContext, null);
            o0Var.f22844i = true;
        } else {
            if (kotlin.text.o.h0("androidx.work.workdb")) {
                throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
            }
            o0 o0Var2 = new o0(applicationContext, "androidx.work.workdb");
            o0Var2.f22843h = new a3.b(applicationContext, i10);
            o0Var = o0Var2;
        }
        o0Var.f22841f = dVar.f7076a;
        b bVar = new b(uVar);
        ArrayList arrayList = o0Var.f22838c;
        arrayList.add(bVar);
        o0Var.a(e.f6849h);
        o0Var.a(new h(applicationContext, 2, 3));
        o0Var.a(e.f6850i);
        o0Var.a(e.f6851j);
        o0Var.a(new h(applicationContext, 5, 6));
        o0Var.a(e.k);
        o0Var.a(e.f6852l);
        o0Var.a(e.f6853m);
        o0Var.a(new h(applicationContext));
        o0Var.a(new h(applicationContext, 10, 11));
        o0Var.a(e.f6845d);
        o0Var.a(e.f6846e);
        o0Var.a(e.f6847f);
        o0Var.a(e.f6848g);
        o0Var.k = false;
        o0Var.f22846l = true;
        Executor executor = o0Var.f22841f;
        if (executor == null && o0Var.f22842g == null) {
            androidx.credentials.a aVar = o.a.f20716f;
            o0Var.f22842g = aVar;
            o0Var.f22841f = aVar;
        } else if (executor != null && o0Var.f22842g == null) {
            o0Var.f22842g = executor;
        } else if (executor == null) {
            o0Var.f22841f = o0Var.f22842g;
        }
        HashSet hashSet = o0Var.f22850p;
        LinkedHashSet linkedHashSet = o0Var.f22849o;
        if (hashSet != null) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                int iIntValue = ((Number) it.next()).intValue();
                if (linkedHashSet.contains(Integer.valueOf(iIntValue))) {
                    throw new IllegalArgumentException(a0.c.i(iIntValue, "Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: ").toString());
                }
            }
        }
        d.a aVar2 = o0Var.f22843h;
        if (aVar2 == null) {
            aVar2 = new a1.a();
        }
        d.a aVar3 = aVar2;
        if (o0Var.f22847m > 0) {
            if (o0Var.f22837b != null) {
                throw new IllegalArgumentException("Required value was null.");
            }
            throw new IllegalArgumentException("Cannot create auto-closing database for an in-memory database.");
        }
        boolean z5 = o0Var.f22844i;
        int i11 = o0Var.f22845j;
        if (i11 == 0) {
            throw null;
        }
        Context context2 = o0Var.f22836a;
        int i12 = i11 != 1 ? i11 : !((ActivityManager) context2.getSystemService("activity")).isLowRamDevice() ? 3 : 2;
        Executor executor2 = o0Var.f22841f;
        if (executor2 == null) {
            throw new IllegalArgumentException("Required value was null.");
        }
        Executor executor3 = o0Var.f22842g;
        if (executor3 == null) {
            throw new IllegalArgumentException("Required value was null.");
        }
        boolean z10 = o0Var.k;
        boolean z11 = o0Var.f22846l;
        String str = o0Var.f22837b;
        x8.o oVar = o0Var.f22848n;
        ArrayList arrayList2 = o0Var.f22839d;
        ArrayList arrayList3 = o0Var.f22840e;
        boolean z12 = false;
        y1.g gVar = new y1.g(context2, str, aVar3, oVar, arrayList, z5, i12, executor2, executor3, z10, z11, linkedHashSet, arrayList2, arrayList3);
        String name = WorkDatabase.class.getPackage().getName();
        String canonicalName = WorkDatabase.class.getCanonicalName();
        if (name.length() != 0) {
            canonicalName = canonicalName.substring(name.length() + 1);
        }
        String str2 = canonicalName.replace('.', '_') + "_Impl";
        try {
            WorkDatabase workDatabase = (WorkDatabase) Class.forName(name.length() == 0 ? str2 : name + '.' + str2, true, WorkDatabase.class.getClassLoader()).newInstance();
            z zVar = workDatabase.f6712d;
            LinkedHashMap linkedHashMap = workDatabase.f6715g;
            workDatabase.f6711c = workDatabase.e(gVar);
            Set setI = workDatabase.i();
            BitSet bitSet = new BitSet();
            Iterator it2 = setI.iterator();
            while (true) {
                int i13 = -1;
                if (!it2.hasNext()) {
                    boolean z13 = z12;
                    int size = arrayList3.size() - 1;
                    if (size >= 0) {
                        while (true) {
                            int i14 = size - 1;
                            if (!bitSet.get(size)) {
                                throw new IllegalArgumentException("Unexpected auto migration specs found. Annotate AutoMigrationSpec implementation with @ProvidedAutoMigrationSpec annotation or remove this spec from the builder.");
                            }
                            if (i14 < 0) {
                                break;
                            }
                            size = i14;
                        }
                    }
                    for (z1.b bVar2 : workDatabase.g()) {
                        int i15 = bVar2.f23158a;
                        int i16 = bVar2.f23159b;
                        LinkedHashMap linkedHashMap2 = oVar.f22669a;
                        if (linkedHashMap2.containsKey(Integer.valueOf(i15))) {
                            Map map = (Map) linkedHashMap2.get(Integer.valueOf(i15));
                            if (map == null) {
                                map = a0.f19683i;
                            }
                            zContainsKey = map.containsKey(Integer.valueOf(i16));
                        } else {
                            zContainsKey = z13 ? 1 : 0;
                        }
                        if (!zContainsKey) {
                            z1.b[] bVarArr = new z1.b[1];
                            bVarArr[z13 ? 1 : 0] = bVar2;
                            oVar.a(bVarArr);
                        }
                    }
                    workDatabase.h().setWriteAheadLoggingEnabled(gVar.f22829g == 3 ? true : z13 ? 1 : 0);
                    workDatabase.f6714f = gVar.f22827e;
                    workDatabase.f6710b = gVar.f22830h;
                    new ArrayDeque();
                    workDatabase.f6713e = gVar.f22828f;
                    Map mapJ = workDatabase.j();
                    BitSet bitSet2 = new BitSet();
                    for (Map.Entry entry : mapJ.entrySet()) {
                        Class cls = (Class) entry.getKey();
                        for (Class cls2 : (List) entry.getValue()) {
                            int size2 = arrayList2.size() - 1;
                            if (size2 >= 0) {
                                while (true) {
                                    int i17 = size2 - 1;
                                    if (cls2.isAssignableFrom(arrayList2.get(size2).getClass())) {
                                        bitSet2.set(size2);
                                        break;
                                    }
                                    if (i17 < 0) {
                                        break;
                                    }
                                    size2 = i17;
                                }
                                size2 = -1;
                            } else {
                                size2 = -1;
                            }
                            if ((size2 >= 0 ? 1 : z13 ? 1 : 0) == 0) {
                                throw new IllegalArgumentException(("A required type converter (" + cls2 + ") for " + cls.getCanonicalName() + " is missing in the database configuration.").toString());
                            }
                            workDatabase.k.put(cls2, arrayList2.get(size2));
                        }
                    }
                    int size3 = arrayList2.size() - 1;
                    if (size3 >= 0) {
                        while (true) {
                            int i18 = size3 - 1;
                            if (!bitSet2.get(size3)) {
                                throw new IllegalArgumentException("Unexpected type converter " + arrayList2.get(size3) + ". Annotate TypeConverter class with @ProvidedTypeConverter annotation or remove this converter from the builder.");
                            }
                            if (i18 < 0) {
                                break;
                            }
                            size3 = i18;
                        }
                    }
                    androidx.work.impl.constraints.trackers.m mVar = new androidx.work.impl.constraints.trackers.m(context.getApplicationContext(), dVar);
                    g gVar2 = new g(context.getApplicationContext(), cVar, dVar, workDatabase);
                    return new r(context.getApplicationContext(), cVar, dVar, workDatabase, (List) s.f6974i.invoke(context, cVar, dVar, workDatabase, mVar, gVar2), gVar2, mVar);
                }
                Class cls3 = (Class) it2.next();
                int size4 = arrayList3.size() - 1;
                if (size4 >= 0) {
                    while (true) {
                        int i19 = size4 - 1;
                        z = z12;
                        if (cls3.isAssignableFrom(arrayList3.get(size4).getClass())) {
                            bitSet.set(size4);
                            i13 = size4;
                            break;
                        }
                        if (i19 < 0) {
                            break;
                        }
                        size4 = i19;
                        z12 = z;
                    }
                } else {
                    z = z12;
                }
                if (i13 < 0) {
                    throw new IllegalArgumentException(("A required auto migration spec (" + cls3.getCanonicalName() + ") is missing in the database configuration.").toString());
                }
                linkedHashMap.put(cls3, arrayList3.get(i13));
                z12 = z;
            }
        } catch (ClassNotFoundException unused) {
            throw new RuntimeException("Cannot find implementation for " + WorkDatabase.class.getCanonicalName() + ". " + str2 + " does not exist");
        } catch (IllegalAccessException unused2) {
            throw new RuntimeException("Cannot access the constructor " + WorkDatabase.class + ".canonicalName");
        } catch (InstantiationException unused3) {
            throw new RuntimeException("Failed to create an instance of " + WorkDatabase.class + ".canonicalName");
        }
    }

    public static boolean m(View view, KeyEvent keyEvent) {
        int iIndexOfKey;
        WeakHashMap weakHashMap = b2.f2200a;
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        ArrayList arrayList = a2.f2193d;
        a2 a2Var = (a2) view.getTag(R.id.tag_unhandled_key_event_manager);
        WeakReference weakReference = null;
        if (a2Var == null) {
            a2Var = new a2();
            a2Var.f2194a = null;
            a2Var.f2195b = null;
            a2Var.f2196c = null;
            view.setTag(R.id.tag_unhandled_key_event_manager, a2Var);
        }
        WeakReference weakReference2 = a2Var.f2196c;
        if (weakReference2 != null && weakReference2.get() == keyEvent) {
            return false;
        }
        a2Var.f2196c = new WeakReference(keyEvent);
        if (a2Var.f2195b == null) {
            a2Var.f2195b = new SparseArray();
        }
        SparseArray sparseArray = a2Var.f2195b;
        if (keyEvent.getAction() == 1 && (iIndexOfKey = sparseArray.indexOfKey(keyEvent.getKeyCode())) >= 0) {
            weakReference = (WeakReference) sparseArray.valueAt(iIndexOfKey);
            sparseArray.removeAt(iIndexOfKey);
        }
        if (weakReference == null) {
            weakReference = (WeakReference) sparseArray.get(keyEvent.getKeyCode());
        }
        if (weakReference == null) {
            return false;
        }
        View view2 = (View) weakReference.get();
        if (view2 != null && view2.isAttachedToWindow()) {
            a2.b(view2, keyEvent);
        }
        return true;
    }

    public static boolean n(androidx.core.view.a0 a0Var, View view, Window.Callback callback, KeyEvent keyEvent) {
        DialogInterface.OnKeyListener onKeyListener;
        boolean zBooleanValue = false;
        if (a0Var != null) {
            if (Build.VERSION.SDK_INT >= 28) {
                return a0Var.superDispatchKeyEvent(keyEvent);
            }
            if (callback instanceof Activity) {
                Activity activity = (Activity) callback;
                activity.onUserInteraction();
                Window window = activity.getWindow();
                if (window.hasFeature(8)) {
                    ActionBar actionBar = activity.getActionBar();
                    if (keyEvent.getKeyCode() == 82 && actionBar != null) {
                        if (!f6975a) {
                            try {
                                f6976b = actionBar.getClass().getMethod("onMenuKeyEvent", KeyEvent.class);
                            } catch (NoSuchMethodException unused) {
                            }
                            f6975a = true;
                        }
                        Method method = f6976b;
                        if (method != null) {
                            try {
                                Object objInvoke = method.invoke(actionBar, keyEvent);
                                if (objInvoke != null) {
                                    zBooleanValue = ((Boolean) objInvoke).booleanValue();
                                }
                            } catch (IllegalAccessException | InvocationTargetException unused2) {
                            }
                        }
                        if (zBooleanValue) {
                            return true;
                        }
                    }
                }
                if (window.superDispatchKeyEvent(keyEvent)) {
                    return true;
                }
                View decorView = window.getDecorView();
                if (b2.b(decorView, keyEvent)) {
                    return true;
                }
                return keyEvent.dispatch(activity, decorView != null ? decorView.getKeyDispatcherState() : null, activity);
            }
            if (callback instanceof Dialog) {
                Dialog dialog = (Dialog) callback;
                if (!f6977c) {
                    try {
                        Field declaredField = Dialog.class.getDeclaredField("mOnKeyListener");
                        f6978d = declaredField;
                        declaredField.setAccessible(true);
                    } catch (NoSuchFieldException unused3) {
                    }
                    f6977c = true;
                }
                Field field = f6978d;
                if (field != null) {
                    try {
                        onKeyListener = (DialogInterface.OnKeyListener) field.get(dialog);
                    } catch (IllegalAccessException unused4) {
                        onKeyListener = null;
                    }
                } else {
                    onKeyListener = null;
                }
                if (onKeyListener != null && onKeyListener.onKey(dialog, keyEvent.getKeyCode(), keyEvent)) {
                    return true;
                }
                Window window2 = dialog.getWindow();
                if (window2.superDispatchKeyEvent(keyEvent)) {
                    return true;
                }
                View decorView2 = window2.getDecorView();
                if (b2.b(decorView2, keyEvent)) {
                    return true;
                }
                return keyEvent.dispatch(dialog, decorView2 != null ? decorView2.getKeyDispatcherState() : null, dialog);
            }
            if ((view != null && b2.b(view, keyEvent)) || a0Var.superDispatchKeyEvent(keyEvent)) {
                return true;
            }
        }
        return false;
    }

    public static final Object o(z0.g gVar, r7.p pVar, d7.d dVar) {
        return gVar.a(new c1.c(pVar, null, 1), dVar);
    }

    public static com.google.firebase.components.b p(String str, t5.d dVar) {
        com.google.firebase.components.a aVarB = com.google.firebase.components.b.b(t5.a.class);
        aVarB.f14252e = 1;
        aVarB.a(com.google.firebase.components.m.b(Context.class));
        aVarB.f14253f = new androidx.media3.exoplayer.analytics.b(str, dVar, 23);
        return aVarB.b();
    }

    /* JADX WARN: Removed duplicated region for block: B:91:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0170  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static io.sentry.android.replay.viewhierarchy.g q(android.view.View r13, io.sentry.android.replay.viewhierarchy.g r14, androidx.appcompat.app.o0 r15) {
        /*
            Method dump skipped, instruction units count: 406
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.t.q(android.view.View, io.sentry.android.replay.viewhierarchy.g, androidx.appcompat.app.o0):io.sentry.android.replay.viewhierarchy.g");
    }

    public static Calendar r(Calendar calendar, Locale locale) {
        if (calendar == null) {
            return Calendar.getInstance(locale);
        }
        long timeInMillis = calendar.getTimeInMillis();
        Calendar calendar2 = Calendar.getInstance(locale);
        calendar2.setTimeInMillis(timeInMillis);
        return calendar2;
    }

    public static final w0.b s(View view) {
        w0.b bVar = (w0.b) view.getTag(R.id.pooling_container_listener_holder_tag);
        if (bVar != null) {
            return bVar;
        }
        w0.b bVar2 = new w0.b();
        view.setTag(R.id.pooling_container_listener_holder_tag, bVar2);
        return bVar2;
    }

    public static boolean t(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (f6984j == null) {
            f6984j = Boolean.valueOf(Build.VERSION.SDK_INT >= 26 && packageManager.hasSystemFeature("android.hardware.type.automotive"));
        }
        return f6984j.booleanValue();
    }

    public static boolean u(Resources resources) {
        boolean z = false;
        if (resources == null) {
            return false;
        }
        if (f6982h == null) {
            Configuration configuration = resources.getConfiguration();
            if ((configuration.screenLayout & 15) <= 3 && configuration.smallestScreenWidthDp >= 600) {
                z = true;
            }
            f6982h = Boolean.valueOf(z);
        }
        return f6982h.booleanValue();
    }

    public static boolean v(Context context) {
        Resources resources = context.getResources();
        if (resources == null) {
            return false;
        }
        if (f6980f == null) {
            f6980f = Boolean.valueOf((resources.getConfiguration().screenLayout & 15) > 3 || u(resources));
        }
        return f6980f.booleanValue();
    }

    public static boolean w(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (k == null) {
            k = Boolean.valueOf(packageManager.hasSystemFeature("com.google.android.tv") || packageManager.hasSystemFeature("android.hardware.type.television") || packageManager.hasSystemFeature("android.software.leanback"));
        }
        return k.booleanValue();
    }

    public static boolean x(Double d4, boolean z) {
        return d4 == null ? z : !d4.isNaN() && d4.doubleValue() >= 0.0d && d4.doubleValue() <= 1.0d;
    }

    public static String y(String str, Object... objArr) {
        int iIndexOf;
        String string;
        String strValueOf = String.valueOf(str);
        int i10 = 0;
        for (int i11 = 0; i11 < objArr.length; i11++) {
            Object obj = objArr[i11];
            if (obj == null) {
                string = "null";
            } else {
                try {
                    string = obj.toString();
                } catch (Exception e5) {
                    String str2 = obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
                    Logger.getLogger("com.google.common.base.Strings").log(Level.WARNING, "Exception during lenientFormat for " + str2, (Throwable) e5);
                    StringBuilder sbT = a0.c.t("<", str2, " threw ");
                    sbT.append(e5.getClass().getName());
                    sbT.append(">");
                    string = sbT.toString();
                }
            }
            objArr[i11] = string;
        }
        StringBuilder sb2 = new StringBuilder((objArr.length * 16) + strValueOf.length());
        int i12 = 0;
        while (i10 < objArr.length && (iIndexOf = strValueOf.indexOf("%s", i12)) != -1) {
            sb2.append((CharSequence) strValueOf, i12, iIndexOf);
            sb2.append(objArr[i10]);
            i12 = iIndexOf + 2;
            i10++;
        }
        sb2.append((CharSequence) strValueOf, i12, strValueOf.length());
        if (i10 < objArr.length) {
            sb2.append(" [");
            sb2.append(objArr[i10]);
            for (int i13 = i10 + 1; i13 < objArr.length; i13++) {
                sb2.append(", ");
                sb2.append(objArr[i13]);
            }
            sb2.append(']');
        }
        return sb2.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0334  */
    /* JADX WARN: Type inference failed for: r25v0, types: [r7.q] */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v14, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v16, types: [y8.t] */
    /* JADX WARN: Type inference failed for: r6v17, types: [y8.v] */
    /* JADX WARN: Type inference failed for: r6v26, types: [java.lang.Object, y8.u] */
    /* JADX WARN: Type inference failed for: r6v27, types: [y8.t] */
    /* JADX WARN: Type inference failed for: r6v36 */
    /* JADX WARN: Type inference failed for: r6v37 */
    /* JADX WARN: Type inference failed for: r6v38 */
    /* JADX WARN: Type inference failed for: r6v40 */
    /* JADX WARN: Type inference failed for: r6v41 */
    /* JADX WARN: Type inference failed for: r6v42 */
    /* JADX WARN: Type inference failed for: r6v43 */
    /* JADX WARN: Type inference failed for: r6v44 */
    /* JADX WARN: Type inference failed for: r6v45 */
    /* JADX WARN: Type inference failed for: r6v46 */
    /* JADX WARN: Type inference failed for: r6v47 */
    /* JADX WARN: Type inference failed for: r6v48 */
    /* JADX WARN: Type inference failed for: r6v49 */
    /* JADX WARN: Type inference failed for: r6v50 */
    /* JADX WARN: Type inference failed for: r6v51 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object z(v9.w r23, y8.e0 r24, r7.q r25) {
        /*
            Method dump skipped, instruction units count: 1058
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.t.z(v9.w, y8.e0, r7.q):java.lang.Object");
    }
}
