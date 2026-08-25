package io.sentry.android.core;

import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes5.dex */
public final class f0 extends CopyOnWriteArrayList {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16477i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f16478l;

    public /* synthetic */ f0(Object obj, int i10) {
        this.f16477i = i10;
        this.f16478l = obj;
    }

    @Override // java.util.concurrent.CopyOnWriteArrayList, java.util.List, java.util.Collection
    public final boolean add(Object obj) throws IllegalAccessException, InvocationTargetException {
        switch (this.f16477i) {
            case 0:
                e0 e0Var = (e0) obj;
                boolean zAdd = super.add(e0Var);
                if (Boolean.FALSE.equals(((g0) this.f16478l).f16489l.f16500n)) {
                    e0Var.i();
                } else if (Boolean.TRUE.equals(((g0) this.f16478l).f16489l.f16500n)) {
                    e0Var.j();
                }
                return zAdd;
            default:
                io.sentry.android.replay.h hVar = (io.sentry.android.replay.h) obj;
                io.sentry.android.replay.u uVar = (io.sentry.android.replay.u) this.f16478l;
                io.sentry.util.a aVarA = uVar.f17033l.a();
                try {
                    for (View view : uVar.f17035n) {
                        if (hVar != null) {
                            hVar.i(view, true);
                        }
                        break;
                    }
                    androidx.work.impl.t.h(aVarA, null);
                    return super.add(hVar);
                } finally {
                }
        }
    }

    @Override // java.util.concurrent.CopyOnWriteArrayList, java.util.List, java.util.Collection
    public /* bridge */ boolean contains(Object obj) {
        switch (this.f16477i) {
            case 1:
                if (obj == null ? true : obj instanceof io.sentry.android.replay.h) {
                    return super.contains((io.sentry.android.replay.h) obj);
                }
                return false;
            default:
                return super.contains(obj);
        }
    }

    @Override // java.util.concurrent.CopyOnWriteArrayList, java.util.List
    public /* bridge */ int indexOf(Object obj) {
        switch (this.f16477i) {
            case 1:
                if (obj == null ? true : obj instanceof io.sentry.android.replay.h) {
                    return super.indexOf((io.sentry.android.replay.h) obj);
                }
                return -1;
            default:
                return super.indexOf(obj);
        }
    }

    @Override // java.util.concurrent.CopyOnWriteArrayList, java.util.List
    public /* bridge */ int lastIndexOf(Object obj) {
        switch (this.f16477i) {
            case 1:
                if (obj == null ? true : obj instanceof io.sentry.android.replay.h) {
                    return super.lastIndexOf((io.sentry.android.replay.h) obj);
                }
                return -1;
            default:
                return super.lastIndexOf(obj);
        }
    }

    @Override // java.util.concurrent.CopyOnWriteArrayList, java.util.List, java.util.Collection
    public /* bridge */ boolean remove(Object obj) {
        switch (this.f16477i) {
            case 1:
                if (obj == null ? true : obj instanceof io.sentry.android.replay.h) {
                    return super.remove((io.sentry.android.replay.h) obj);
                }
                return false;
            default:
                return super.remove(obj);
        }
    }
}
