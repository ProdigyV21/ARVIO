package androidx.emoji2.text;

import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import androidx.leanback.widget.t1;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.work.impl.WorkDatabase_Impl;
import g8.a1;
import g8.v0;
import gb.p0;
import io.ktor.http.ContentDisposition;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import ka.u1;
import ka.v1;
import kotlinx.coroutines.channels.ClosedSendChannelException;
import y1.r0;

/* JADX INFO: loaded from: classes3.dex */
public final class e0 implements androidx.work.impl.model.h, androidx.work.impl.model.n, gb.j, n.a, s9.e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2599i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f2600l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f2601m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f2602n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Object f2603o;

    public /* synthetic */ e0(int i10, boolean z) {
        this.f2599i = i10;
    }

    public static /* synthetic */ void m(int i10) {
        String str = (i10 == 4 || i10 == 5 || i10 == 6 || i10 == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 4 || i10 == 5 || i10 == 6 || i10 == 7) ? 2 : 3];
        switch (i10) {
            case 1:
                objArr[0] = "valueParameters";
                break;
            case 2:
                objArr[0] = "typeParameters";
                break;
            case 3:
                objArr[0] = "signatureErrors";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/components/SignaturePropagator$PropagatedSignature";
                break;
            default:
                objArr[0] = "returnType";
                break;
        }
        if (i10 == 4) {
            objArr[1] = "getReturnType";
        } else if (i10 == 5) {
            objArr[1] = "getValueParameters";
        } else if (i10 == 6) {
            objArr[1] = "getTypeParameters";
        } else if (i10 != 7) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/SignaturePropagator$PropagatedSignature";
        } else {
            objArr[1] = "getErrors";
        }
        if (i10 != 4 && i10 != 5 && i10 != 6 && i10 != 7) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i10 != 4 && i10 != 5 && i10 != 6 && i10 != 7) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static final Message n(e0 e0Var, ArrayList arrayList, int i10) {
        Object obj;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (((Message) obj2).what == i10) {
                arrayList2.add(obj2);
            }
        }
        Iterator it = arrayList2.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            if (it.hasNext()) {
                long when = ((Message) next).getWhen();
                do {
                    Object next2 = it.next();
                    long when2 = ((Message) next2).getWhen();
                    if (when < when2) {
                        next = next2;
                        when = when2;
                    }
                } while (it.hasNext());
            }
            obj = next;
        } else {
            obj = null;
        }
        return (Message) obj;
    }

    public synchronized int A() {
        return ((ArrayDeque) this.f2602n).size() + ((ArrayDeque) this.f2603o).size();
    }

    public void B(int i10) {
        ArrayList arrayList = new ArrayList();
        ((LinkedBlockingDeque) this.f2602n).drainTo(arrayList);
        arrayList.add(Message.obtain(null, i10, 0, 0));
        ka.m0.p(ka.l0.a((d7.j) this.f2600l), null, 0, new androidx.work.impl.constraints.controllers.c(this, arrayList, null, 7), 3);
    }

    @Override // androidx.work.impl.model.n
    public void a(String str) {
        WorkDatabase_Impl workDatabase_Impl = (WorkDatabase_Impl) this.f2600l;
        workDatabase_Impl.b();
        androidx.work.impl.model.i iVar = (androidx.work.impl.model.i) this.f2602n;
        c2.g gVarC = iVar.c();
        if (str == null) {
            gVarC.C0(1);
        } else {
            gVarC.e0(1, str);
        }
        workDatabase_Impl.c();
        try {
            gVarC.r();
            workDatabase_Impl.o();
        } finally {
            workDatabase_Impl.k();
            iVar.h(gVarC);
        }
    }

    @Override // n.a
    public boolean b(n.b bVar, Menu menu) {
        ActionMode.Callback callback = (ActionMode.Callback) this.f2600l;
        n.g gVarU = u(bVar);
        t.l0 l0Var = (t.l0) this.f2603o;
        Menu f0Var = (Menu) l0Var.get(menu);
        if (f0Var == null) {
            f0Var = new androidx.appcompat.view.menu.f0((Context) this.f2601m, (k0.a) menu);
            l0Var.put(menu, f0Var);
        }
        return callback.onCreateActionMode(gVarU, f0Var);
    }

    @Override // s9.e
    public s9.d c(f9.b bVar) {
        a9.n nVar = (a9.n) ((LinkedHashMap) this.f2603o).get(bVar);
        if (nVar == null) {
            return null;
        }
        return new s9.d((io.sentry.internal.debugmeta.c) this.f2600l, nVar, (b9.a) this.f2601m, (v0) ((s9.f0) this.f2602n).invoke(bVar));
    }

    @Override // androidx.work.impl.model.h
    public androidx.work.impl.model.g d(androidx.work.impl.model.j jVar) {
        String str = jVar.f6914a;
        int i10 = jVar.f6915b;
        WorkDatabase_Impl workDatabase_Impl = (WorkDatabase_Impl) this.f2600l;
        r0 r0VarK = r0.k(2, "SELECT * FROM SystemIdInfo WHERE work_spec_id=? AND generation=?");
        if (str == null) {
            r0VarK.C0(1);
        } else {
            r0VarK.e0(1, str);
        }
        r0VarK.n0(2, i10);
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            int iO = a.a.O(cursorM, "work_spec_id");
            int iO2 = a.a.O(cursorM, "generation");
            int iO3 = a.a.O(cursorM, "system_id");
            androidx.work.impl.model.g gVar = null;
            String string = null;
            if (cursorM.moveToFirst()) {
                if (!cursorM.isNull(iO)) {
                    string = cursorM.getString(iO);
                }
                gVar = new androidx.work.impl.model.g(string, cursorM.getInt(iO2), cursorM.getInt(iO3));
            }
            return gVar;
        } finally {
            cursorM.close();
            r0VarK.l();
        }
    }

    @Override // n.a
    public boolean e(n.b bVar, Menu menu) {
        ActionMode.Callback callback = (ActionMode.Callback) this.f2600l;
        n.g gVarU = u(bVar);
        t.l0 l0Var = (t.l0) this.f2603o;
        Menu f0Var = (Menu) l0Var.get(menu);
        if (f0Var == null) {
            f0Var = new androidx.appcompat.view.menu.f0((Context) this.f2601m, (k0.a) menu);
            l0Var.put(menu, f0Var);
        }
        return callback.onPrepareActionMode(gVarU, f0Var);
    }

    @Override // androidx.work.impl.model.n
    public void f() {
        WorkDatabase_Impl workDatabase_Impl = (WorkDatabase_Impl) this.f2600l;
        workDatabase_Impl.b();
        androidx.work.impl.model.i iVar = (androidx.work.impl.model.i) this.f2603o;
        c2.g gVarC = iVar.c();
        workDatabase_Impl.c();
        try {
            gVarC.r();
            workDatabase_Impl.o();
        } finally {
            workDatabase_Impl.k();
            iVar.h(gVarC);
        }
    }

    @Override // androidx.work.impl.model.n
    public void g(androidx.work.impl.model.m mVar) {
        WorkDatabase_Impl workDatabase_Impl = (WorkDatabase_Impl) this.f2600l;
        workDatabase_Impl.b();
        workDatabase_Impl.c();
        try {
            ((androidx.work.impl.model.c) this.f2601m).j(mVar);
            workDatabase_Impl.o();
        } finally {
            workDatabase_Impl.k();
        }
    }

    @Override // androidx.work.impl.model.h
    public void h(androidx.work.impl.model.j jVar) {
        String str = jVar.f6914a;
        int i10 = jVar.f6915b;
        WorkDatabase_Impl workDatabase_Impl = (WorkDatabase_Impl) this.f2600l;
        workDatabase_Impl.b();
        androidx.work.impl.model.i iVar = (androidx.work.impl.model.i) this.f2602n;
        c2.g gVarC = iVar.c();
        if (str == null) {
            gVarC.C0(1);
        } else {
            gVarC.e0(1, str);
        }
        gVarC.n0(2, i10);
        workDatabase_Impl.c();
        try {
            gVarC.r();
            workDatabase_Impl.o();
        } finally {
            workDatabase_Impl.k();
            iVar.h(gVarC);
        }
    }

    @Override // n.a
    public boolean i(n.b bVar, MenuItem menuItem) {
        return ((ActionMode.Callback) this.f2600l).onActionItemClicked(u(bVar), new androidx.appcompat.view.menu.y((Context) this.f2601m, (k0.b) menuItem));
    }

    @Override // androidx.work.impl.model.h
    public ArrayList j() {
        r0 r0VarK = r0.k(0, "SELECT DISTINCT work_spec_id FROM SystemIdInfo");
        WorkDatabase_Impl workDatabase_Impl = (WorkDatabase_Impl) this.f2600l;
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

    @Override // androidx.work.impl.model.h
    public void k(androidx.work.impl.model.g gVar) {
        WorkDatabase_Impl workDatabase_Impl = (WorkDatabase_Impl) this.f2600l;
        workDatabase_Impl.b();
        workDatabase_Impl.c();
        try {
            ((androidx.work.impl.model.c) this.f2601m).j(gVar);
            workDatabase_Impl.o();
        } finally {
            workDatabase_Impl.k();
        }
    }

    @Override // androidx.work.impl.model.h
    public void l(String str) {
        WorkDatabase_Impl workDatabase_Impl = (WorkDatabase_Impl) this.f2600l;
        workDatabase_Impl.b();
        androidx.work.impl.model.i iVar = (androidx.work.impl.model.i) this.f2603o;
        c2.g gVarC = iVar.c();
        if (str == null) {
            gVarC.C0(1);
        } else {
            gVarC.e0(1, str);
        }
        workDatabase_Impl.c();
        try {
            gVarC.r();
            workDatabase_Impl.o();
        } finally {
            workDatabase_Impl.k();
            iVar.h(gVarC);
        }
    }

    public void o(ArrayList arrayList) {
        if (arrayList == null) {
            throw new IllegalArgumentException("filters must not be null");
        }
        if (arrayList.isEmpty()) {
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            IntentFilter intentFilter = (IntentFilter) it.next();
            if (intentFilter != null) {
                ArrayList arrayList2 = (ArrayList) this.f2602n;
                if (!arrayList2.contains(intentFilter)) {
                    arrayList2.add(intentFilter);
                }
            }
        }
    }

    @Override // gb.j
    public void onFailure(gb.i iVar, IOException iOException) {
        ArrayList arrayList = (ArrayList) this.f2600l;
        synchronized (arrayList) {
            arrayList.add(iOException);
        }
        ((CountDownLatch) this.f2601m).countDown();
    }

    @Override // gb.j
    public void onResponse(gb.i iVar, p0 p0Var) {
        hb.b.b(p0Var, (String) this.f2602n, (ArrayList) this.f2603o, (ArrayList) this.f2600l);
        ((CountDownLatch) this.f2601m).countDown();
    }

    public androidx.mediarouter.media.m p() {
        Bundle bundle = (Bundle) this.f2600l;
        bundle.putParcelableArrayList("controlFilters", new ArrayList<>((ArrayList) this.f2602n));
        bundle.putStringArrayList("groupMemberIds", new ArrayList<>((ArrayList) this.f2601m));
        bundle.putStringArrayList("allowedPackages", new ArrayList<>((HashSet) this.f2603o));
        return new androidx.mediarouter.media.m(bundle);
    }

    public synchronized ExecutorService q() {
        try {
            if (((ThreadPoolExecutor) this.f2600l) == null) {
                this.f2600l = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), new ib.b(ib.c.f16275h + " Dispatcher", false));
            }
        } catch (Throwable th) {
            throw th;
        }
        return (ThreadPoolExecutor) this.f2600l;
    }

    public void r(ArrayDeque arrayDeque, Object obj) {
        synchronized (this) {
            if (!arrayDeque.remove(obj)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
        }
        y();
    }

    public void s(lb.f fVar) {
        fVar.f20106l.decrementAndGet();
        r((ArrayDeque) this.f2602n, fVar);
    }

    @Override // n.a
    public void t(n.b bVar) {
        ((ActionMode.Callback) this.f2600l).onDestroyActionMode(u(bVar));
    }

    public String toString() {
        switch (this.f2599i) {
            case 1:
                return "horizontal=" + ((t1) this.f2601m) + "; vertical=" + ((t1) this.f2600l);
            default:
                return super.toString();
        }
    }

    public n.g u(n.b bVar) {
        ArrayList arrayList = (ArrayList) this.f2602n;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            n.g gVar = (n.g) arrayList.get(i10);
            if (gVar != null && gVar.f20387b == bVar) {
                return gVar;
            }
        }
        n.g gVar2 = new n.g((Context) this.f2601m, bVar);
        arrayList.add(gVar2);
        return gVar2;
    }

    public g8.f v(f9.b bVar, List list) {
        return (g8.f) ((u9.q) this.f2603o).invoke(new g8.e0(bVar, list));
    }

    public boolean w(a1 a1Var) {
        if (((a1) this.f2601m).equals(a1Var)) {
            return true;
        }
        e0 e0Var = (e0) this.f2600l;
        return e0Var != null ? e0Var.w(a1Var) : false;
    }

    public void x(z0.n nVar) throws Throwable {
        Object objMo6685trySendJP2dKIU = ((ma.f) this.f2602n).mo6685trySendJP2dKIU(nVar);
        if (objMo6685trySendJP2dKIU instanceof ma.m) {
            Throwable th = ((ma.m) objMo6685trySendJP2dKIU).f20345a;
            if (th != null) {
                throw th;
            }
            throw new ClosedSendChannelException("Channel was closed normally");
        }
        if (objMo6685trySendJP2dKIU instanceof ma.n) {
            throw new IllegalStateException("Check failed.");
        }
        if (((AtomicInteger) this.f2603o).getAndIncrement() == 0) {
            ka.m0.p((pa.e) this.f2600l, null, 0, new androidx.work.impl.constraints.controllers.c(this, null, 10), 3);
        }
    }

    public void y() {
        byte[] bArr = ib.c.f16268a;
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            try {
                Iterator it = ((ArrayDeque) this.f2601m).iterator();
                while (it.hasNext()) {
                    lb.f fVar = (lb.f) it.next();
                    if (((ArrayDeque) this.f2602n).size() >= 64) {
                        break;
                    }
                    if (fVar.f20106l.get() < 5) {
                        it.remove();
                        fVar.f20106l.incrementAndGet();
                        arrayList.add(fVar);
                        ((ArrayDeque) this.f2602n).add(fVar);
                    }
                }
                A();
            } catch (Throwable th) {
                throw th;
            }
        }
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            lb.f fVar2 = (lb.f) arrayList.get(i10);
            ExecutorService executorServiceQ = q();
            lb.i iVar = fVar2.f20107m;
            byte[] bArr2 = ib.c.f16268a;
            try {
                try {
                    ((ThreadPoolExecutor) executorServiceQ).execute(fVar2);
                } catch (Throwable th2) {
                    iVar.f20110i.f15700i.s(fVar2);
                    throw th2;
                }
            } catch (RejectedExecutionException e5) {
                InterruptedIOException interruptedIOException = new InterruptedIOException("executor rejected");
                interruptedIOException.initCause(e5);
                iVar.h(interruptedIOException);
                fVar2.f20105i.onFailure(iVar, interruptedIOException);
                iVar.f20110i.f15700i.s(fVar2);
            }
        }
    }

    public void z(Message message) {
        LinkedBlockingDeque linkedBlockingDeque = (LinkedBlockingDeque) this.f2602n;
        if (!linkedBlockingDeque.offer(message)) {
            Log.d("SessionLifecycleClient", "Failed to enqueue message " + message.what + ". Dropping.");
            return;
        }
        Log.d("SessionLifecycleClient", "Queued message " + message.what + ". Queue size " + linkedBlockingDeque.size());
    }

    public /* synthetic */ e0(Object obj, Object obj2, Object obj3, Object obj4, int i10) {
        this.f2599i = i10;
        this.f2600l = obj;
        this.f2601m = obj2;
        this.f2602n = obj3;
        this.f2603o = obj4;
    }

    public e0(u9.p pVar, g8.b0 b0Var) {
        this.f2599i = 7;
        this.f2600l = pVar;
        this.f2601m = b0Var;
        this.f2602n = pVar.g(new g8.g0(this, 1));
        this.f2603o = pVar.g(new g8.g0(this, 0));
    }

    public e0(a9.p0 p0Var, io.sentry.internal.debugmeta.c cVar, b9.a aVar, s9.f0 f0Var) {
        this.f2599i = 15;
        this.f2600l = cVar;
        this.f2601m = aVar;
        this.f2602n = f0Var;
        List list = p0Var.f621q;
        int iQ0 = kotlin.collections.i0.q0(kotlin.collections.s.U(list, 10));
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0 < 16 ? 16 : iQ0);
        for (Object obj : list) {
            io.sentry.internal.debugmeta.c cVar2 = (io.sentry.internal.debugmeta.c) this.f2600l;
            int i10 = ((a9.n) obj).f584o;
            linkedHashMap.put(f9.b.e(cVar2.o(i10), cVar2.k(i10)), obj);
        }
        this.f2603o = linkedHashMap;
    }

    public e0(int i10) {
        this.f2599i = i10;
        switch (i10) {
            case 8:
                this.f2601m = new ArrayDeque();
                this.f2602n = new ArrayDeque();
                this.f2603o = new ArrayDeque();
                break;
            default:
                t1 t1Var = new t1();
                this.f2600l = t1Var;
                t1 t1Var2 = new t1();
                this.f2601m = t1Var2;
                this.f2602n = t1Var2;
                this.f2603o = t1Var;
                break;
        }
    }

    public e0(pa.e eVar, s8.d dVar, z0.o oVar) {
        this.f2599i = 18;
        this.f2600l = eVar;
        this.f2601m = oVar;
        this.f2602n = m2.f0.a(Integer.MAX_VALUE, 0, 6);
        this.f2603o = new AtomicInteger(0);
        v1 v1Var = (v1) eVar.f21262i.get(u1.f19642i);
        if (v1Var == null) {
            return;
        }
        v1Var.invokeOnCompletion(new androidx.navigation.p(dVar, this, 19));
    }

    public e0(WorkDatabase_Impl workDatabase_Impl, int i10) {
        this.f2599i = i10;
        switch (i10) {
            case 4:
                this.f2600l = workDatabase_Impl;
                this.f2601m = new androidx.work.impl.model.c(workDatabase_Impl, 4);
                this.f2602n = new androidx.work.impl.model.i(workDatabase_Impl, 2);
                this.f2603o = new androidx.work.impl.model.i(workDatabase_Impl, 3);
                break;
            default:
                this.f2600l = workDatabase_Impl;
                this.f2601m = new androidx.work.impl.model.c(workDatabase_Impl, 2);
                this.f2602n = new androidx.work.impl.model.i(workDatabase_Impl, 0);
                this.f2603o = new androidx.work.impl.model.i(workDatabase_Impl, 1);
                break;
        }
    }

    public e0(d7.j jVar) {
        this.f2599i = 17;
        this.f2600l = jVar;
        this.f2602n = new LinkedBlockingDeque(20);
        this.f2603o = new w5.k0(this);
    }

    public e0(v9.w wVar, List list, ArrayList arrayList) {
        this.f2599i = 14;
        List list2 = Collections.EMPTY_LIST;
        if (list2 != null) {
            this.f2600l = wVar;
            this.f2601m = list;
            this.f2602n = arrayList;
            this.f2603o = list2;
            return;
        }
        m(3);
        throw null;
    }

    public e0(Typeface typeface, androidx.emoji2.text.flatbuffer.b bVar) {
        int i10;
        int i11;
        int i12;
        int i13;
        this.f2599i = 0;
        this.f2603o = typeface;
        this.f2600l = bVar;
        this.f2602n = new d0(1024);
        int iA = bVar.a(6);
        if (iA != 0) {
            int i14 = iA + bVar.f2238i;
            i10 = ((ByteBuffer) bVar.f2241n).getInt(((ByteBuffer) bVar.f2241n).getInt(i14) + i14);
        } else {
            i10 = 0;
        }
        this.f2601m = new char[i10 * 2];
        int iA2 = bVar.a(6);
        if (iA2 != 0) {
            int i15 = iA2 + bVar.f2238i;
            i11 = ((ByteBuffer) bVar.f2241n).getInt(((ByteBuffer) bVar.f2241n).getInt(i15) + i15);
        } else {
            i11 = 0;
        }
        for (int i16 = 0; i16 < i11; i16++) {
            i0 i0Var = new i0(this, i16);
            androidx.emoji2.text.flatbuffer.a aVarB = i0Var.b();
            int iA3 = aVarB.a(4);
            Character.toChars(iA3 != 0 ? ((ByteBuffer) aVarB.f2241n).getInt(iA3 + aVarB.f2238i) : 0, (char[]) this.f2601m, i16 * 2);
            androidx.emoji2.text.flatbuffer.a aVarB2 = i0Var.b();
            int iA4 = aVarB2.a(16);
            if (iA4 != 0) {
                int i17 = iA4 + aVarB2.f2238i;
                i12 = ((ByteBuffer) aVarB2.f2241n).getInt(((ByteBuffer) aVarB2.f2241n).getInt(i17) + i17);
            } else {
                i12 = 0;
            }
            if (i12 > 0) {
                d0 d0Var = (d0) this.f2602n;
                androidx.emoji2.text.flatbuffer.a aVarB3 = i0Var.b();
                int iA5 = aVarB3.a(16);
                if (iA5 != 0) {
                    int i18 = iA5 + aVarB3.f2238i;
                    i13 = ((ByteBuffer) aVarB3.f2241n).getInt(((ByteBuffer) aVarB3.f2241n).getInt(i18) + i18);
                } else {
                    i13 = 0;
                }
                d0Var.a(i0Var, 0, i13 - 1);
            } else {
                throw new IllegalArgumentException("invalid metadata codepoint length");
            }
        }
    }

    public e0(ArrayList arrayList, CountDownLatch countDownLatch, hb.b bVar, String str, ArrayList arrayList2) {
        this.f2599i = 9;
        this.f2600l = arrayList;
        this.f2601m = countDownLatch;
        this.f2602n = str;
        this.f2603o = arrayList2;
    }

    public e0(Context context, ActionMode.Callback callback) {
        this.f2599i = 13;
        this.f2601m = context;
        this.f2600l = callback;
        this.f2602n = new ArrayList();
        this.f2603o = new t.l0(0);
    }

    public e0(io.sentry.android.core.d0 d0Var) {
        this.f2599i = 10;
        this.f2600l = d0Var;
        this.f2601m = null;
        this.f2602n = null;
        this.f2603o = null;
    }

    public e0(io.sentry.android.core.d0 d0Var, byte[] bArr) {
        this.f2599i = 10;
        this.f2600l = d0Var;
        this.f2601m = bArr;
        this.f2602n = null;
        this.f2603o = null;
    }

    public e0(kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.k kVar) {
        this.f2599i = 11;
        this.f2603o = kVar;
        List list = kVar.f19866o.D;
        int iQ0 = kotlin.collections.i0.q0(kotlin.collections.s.U(list, 10));
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0 < 16 ? 16 : iQ0);
        for (Object obj : list) {
            linkedHashMap.put(t7.a.x(kVar.f19873v.f21732b, ((a9.b0) obj).f340n), obj);
        }
        this.f2600l = linkedHashMap;
        kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.k kVar2 = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.k) this.f2603o;
        this.f2601m = kVar2.f19873v.f21731a.f21706a.e(new androidx.navigation.p(this, kVar2, 11));
        u9.p pVar = ((kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.k) this.f2603o).f19873v.f21731a.f21706a;
        kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.r rVar = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.r(this, 2);
        pVar.getClass();
        this.f2602n = new u9.l(pVar, rVar);
    }

    public e0(String str, String str2) {
        this.f2599i = 2;
        this.f2601m = new ArrayList();
        this.f2602n = new ArrayList();
        this.f2603o = new HashSet();
        Bundle bundle = new Bundle();
        this.f2600l = bundle;
        if (str != null) {
            bundle.putString(TtmlNode.ATTR_ID, str);
            if (str2 != null) {
                bundle.putString(ContentDisposition.Parameters.Name, str2);
                return;
            }
            throw new NullPointerException("name must not be null");
        }
        throw new NullPointerException("id must not be null");
    }

    public e0(androidx.mediarouter.media.m mVar) {
        this.f2599i = 2;
        this.f2601m = new ArrayList();
        this.f2602n = new ArrayList();
        this.f2603o = new HashSet();
        this.f2600l = new Bundle(mVar.f4440a);
        this.f2601m = mVar.c();
        this.f2602n = mVar.b();
        this.f2603o = mVar.a();
    }
}
