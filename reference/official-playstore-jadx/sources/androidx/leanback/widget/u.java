package androidx.leanback.widget;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import androidx.appcompat.widget.g2;
import androidx.core.view.b2;
import androidx.leanback.widget.picker.DatePicker;
import androidx.work.Worker;
import j$.util.Objects;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes3.dex */
public final class u implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3145i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3146l;

    public /* synthetic */ u(Object obj, int i10) {
        this.f3145i = i10;
        this.f3146l = obj;
    }

    private final void b() {
        Object obj;
        synchronized (((androidx.lifecycle.h0) this.f3146l).f3239a) {
            obj = ((androidx.lifecycle.h0) this.f3146l).f3244f;
            ((androidx.lifecycle.h0) this.f3146l).f3244f = androidx.lifecycle.h0.k;
        }
        ((androidx.lifecycle.h0) this.f3146l).i(obj);
    }

    private final void c() {
        kb.a aVarC;
        long jNanoTime;
        while (true) {
            kb.e eVar = (kb.e) this.f3146l;
            synchronized (eVar) {
                aVarC = eVar.c();
            }
            if (aVarC == null) {
                return;
            }
            kb.b bVar = aVarC.f19664c;
            kb.e eVar2 = (kb.e) this.f3146l;
            boolean zIsLoggable = kb.e.f19675i.isLoggable(Level.FINE);
            if (zIsLoggable) {
                kb.e eVar3 = bVar.f19666a;
                jNanoTime = System.nanoTime();
                qb.d.b(aVarC, bVar, "starting");
            } else {
                jNanoTime = -1;
            }
            try {
                kb.e.a(eVar2, aVarC);
                if (zIsLoggable) {
                    kb.e eVar4 = bVar.f19666a;
                    qb.d.b(aVarC, bVar, "finished run in ".concat(qb.d.A(System.nanoTime() - jNanoTime)));
                }
            } finally {
            }
        }
    }

    public z6.i a() throws IOException {
        y1.z zVar = (y1.z) this.f3146l;
        z6.i iVar = new z6.i();
        Cursor cursorM = zVar.f22862a.m(new c2.a("SELECT * FROM room_table_modification_log WHERE invalidated = 1;", 0));
        while (cursorM.moveToNext()) {
            try {
                iVar.add(Integer.valueOf(cursorM.getInt(0)));
            } finally {
            }
        }
        cursorM.close();
        z6.i iVarC = ac.b.c(iVar);
        if (iVarC.f23241i.isEmpty()) {
            return iVarC;
        }
        if (((y1.z) this.f3146l).f22868g == null) {
            throw new IllegalStateException("Required value was null.");
        }
        c2.g gVar = ((y1.z) this.f3146l).f22868g;
        if (gVar == null) {
            throw new IllegalArgumentException("Required value was null.");
        }
        gVar.r();
        return iVarC;
    }

    @Override // java.lang.Runnable
    public final void run() {
        io.sentry.util.a aVarA;
        boolean z;
        boolean z5;
        Set setA;
        switch (this.f3145i) {
            case 0:
                ((a0) this.f3146l).y0();
                return;
            case 1:
                b();
                return;
            case 2:
                ((com.google.android.gms.common.api.internal.e0) this.f3146l).b();
                return;
            case 3:
                com.google.android.gms.common.api.internal.e0 e0Var = (com.google.android.gms.common.api.internal.e0) ((a8.e) this.f3146l).f183l;
                e0Var.f12717d.disconnect(e0Var.f12717d.getClass().getName().concat(" disconnecting because it was signed out."));
                return;
            case 4:
                ((com.google.android.gms.common.api.internal.v0) this.f3146l).f12805i.C(new o3.b(4, null, null));
                return;
            case 5:
                ((io.sentry.android.replay.capture.a) this.f3146l).invoke();
                return;
            case 6:
                ((io.sentry.android.replay.capture.a) this.f3146l).invoke();
                return;
            case 7:
                ((io.sentry.android.replay.capture.a) this.f3146l).invoke();
                return;
            case 8:
                ((io.sentry.android.replay.capture.a) this.f3146l).invoke();
                return;
            case 9:
                ((io.sentry.android.replay.capture.a) this.f3146l).invoke();
                return;
            case 10:
                ((io.sentry.android.replay.capture.a) this.f3146l).invoke();
                return;
            case 11:
                io.sentry.logger.d dVar = (io.sentry.logger.d) this.f3146l;
                do {
                    dVar.d();
                } while (dVar.f17452n.size() >= 100);
                aVarA = dVar.f17455q.a();
                try {
                    if (!dVar.f17452n.isEmpty()) {
                        dVar.f(false);
                        break;
                    }
                    aVarA.close();
                    return;
                } finally {
                }
            case 12:
                io.sentry.logger.d dVar2 = (io.sentry.logger.d) this.f3146l;
                do {
                    dVar2.c();
                } while (dVar2.f17452n.size() >= 1000);
                aVarA = dVar2.f17455q.a();
                try {
                    if (!dVar2.f17452n.isEmpty()) {
                        dVar2.e(false);
                        break;
                    }
                    aVarA.close();
                    return;
                } finally {
                }
            case 13:
                c();
                return;
            case 14:
                l.e eVar = (l.e) this.f3146l;
                eVar.a(true);
                eVar.invalidateSelf();
                return;
            case 15:
                DatePicker datePicker = (DatePicker) this.f3146l;
                int[] iArr = {datePicker.E, datePicker.D, datePicker.F};
                boolean z10 = true;
                boolean z11 = true;
                for (int i10 = 2; i10 >= 0; i10--) {
                    int i11 = iArr[i10];
                    if (i11 >= 0) {
                        int i12 = DatePicker.M[i10];
                        ArrayList arrayList = datePicker.f20154m;
                        m1.f fVar = arrayList == null ? null : (m1.f) arrayList.get(i11);
                        if (z10) {
                            int i13 = datePicker.I.get(i12);
                            if (i13 != fVar.f20167b) {
                                fVar.f20167b = i13;
                                z = true;
                            }
                            z = false;
                        } else {
                            int actualMinimum = datePicker.K.getActualMinimum(i12);
                            if (actualMinimum != fVar.f20167b) {
                                fVar.f20167b = actualMinimum;
                                z = true;
                            }
                            z = false;
                        }
                        if (z11) {
                            int i14 = datePicker.J.get(i12);
                            if (i14 != fVar.f20168c) {
                                fVar.f20168c = i14;
                                z5 = true;
                            }
                            z5 = false;
                        } else {
                            int actualMaximum = datePicker.K.getActualMaximum(i12);
                            if (actualMaximum != fVar.f20168c) {
                                fVar.f20168c = actualMaximum;
                                z5 = true;
                            }
                            z5 = false;
                        }
                        boolean z12 = z | z5;
                        z10 &= datePicker.K.get(i12) == datePicker.I.get(i12);
                        z11 &= datePicker.K.get(i12) == datePicker.J.get(i12);
                        if (z12) {
                            datePicker.a(iArr[i10], fVar);
                        }
                        int i15 = iArr[i10];
                        int i16 = datePicker.K.get(i12);
                        m1.f fVar2 = (m1.f) datePicker.f20154m.get(i15);
                        if (fVar2.f20166a != i16) {
                            fVar2.f20166a = i16;
                            VerticalGridView verticalGridView = (VerticalGridView) datePicker.f20153l.get(i15);
                            if (verticalGridView != null) {
                                verticalGridView.setSelectedPosition(i16 - ((m1.f) datePicker.f20154m.get(i15)).f20167b);
                            }
                        }
                    }
                }
                return;
            case 16:
                Worker worker = (Worker) this.f3146l;
                try {
                    worker.f6698i.h(worker.a());
                    return;
                } catch (Throwable th) {
                    worker.f6698i.i(th);
                    return;
                }
            case 17:
                u0.h hVar = (u0.h) this.f3146l;
                g2 g2Var = hVar.f22023m;
                u0.a aVar = hVar.f22021i;
                if (hVar.f22034y) {
                    if (hVar.f22033w) {
                        hVar.f22033w = false;
                        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                        aVar.f22016e = jCurrentAnimationTimeMillis;
                        aVar.f22018g = -1L;
                        aVar.f22017f = jCurrentAnimationTimeMillis;
                        aVar.f22019h = 0.5f;
                    }
                    if ((aVar.f22018g > 0 && AnimationUtils.currentAnimationTimeMillis() > aVar.f22018g + ((long) aVar.f22020i)) || !hVar.e()) {
                        hVar.f22034y = false;
                        return;
                    }
                    if (hVar.x) {
                        hVar.x = false;
                        long jUptimeMillis = SystemClock.uptimeMillis();
                        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                        g2Var.onTouchEvent(motionEventObtain);
                        motionEventObtain.recycle();
                    }
                    if (aVar.f22017f == 0) {
                        throw new RuntimeException("Cannot compute scroll delta before calling start()");
                    }
                    long jCurrentAnimationTimeMillis2 = AnimationUtils.currentAnimationTimeMillis();
                    float fA = aVar.a(jCurrentAnimationTimeMillis2);
                    long j10 = jCurrentAnimationTimeMillis2 - aVar.f22017f;
                    aVar.f22017f = jCurrentAnimationTimeMillis2;
                    hVar.A.scrollListBy((int) (j10 * ((fA * 4.0f) + ((-4.0f) * fA * fA)) * aVar.f22015d));
                    WeakHashMap weakHashMap = b2.f2200a;
                    g2Var.postOnAnimation(this);
                    return;
                }
                return;
            default:
                ReentrantReadWriteLock.ReadLock lock = ((y1.z) this.f3146l).f22862a.f6716h.readLock();
                lock.lock();
                try {
                    try {
                        try {
                        } catch (SQLiteException e5) {
                            Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e5);
                            setA = kotlin.collections.b0.f19686i;
                        }
                    } catch (IllegalStateException e6) {
                        Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e6);
                        setA = kotlin.collections.b0.f19686i;
                    }
                    if (((y1.z) this.f3146l).a() && ((y1.z) this.f3146l).f22866e.compareAndSet(true, false) && !((y1.z) this.f3146l).f22862a.h().getWritableDatabase().E0()) {
                        c2.b writableDatabase = ((y1.z) this.f3146l).f22862a.h().getWritableDatabase();
                        writableDatabase.E();
                        try {
                            setA = a();
                            writableDatabase.C();
                            if (setA.isEmpty()) {
                                return;
                            }
                            y1.z zVar = (y1.z) this.f3146l;
                            synchronized (zVar.f22870i) {
                                Iterator it = zVar.f22870i.iterator();
                                while (true) {
                                    p.b bVar = (p.b) it;
                                    if (bVar.hasNext()) {
                                        ((y1.y) ((Map.Entry) bVar.next()).getValue()).a(setA);
                                    }
                                }
                            }
                            return;
                        } finally {
                            writableDatabase.K();
                        }
                    }
                    return;
                } finally {
                    lock.unlock();
                }
        }
    }

    public u(com.google.android.gms.common.api.internal.v0 v0Var) {
        this.f3145i = 4;
        Objects.requireNonNull(v0Var);
        this.f3146l = v0Var;
    }
}
