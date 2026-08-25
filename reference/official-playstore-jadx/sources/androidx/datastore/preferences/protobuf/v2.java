package androidx.datastore.preferences.protobuf;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class v2 implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2541i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2542l = -1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f2543m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Iterator f2544n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ AbstractMap f2545o;

    public /* synthetic */ v2(AbstractMap abstractMap, int i10) {
        this.f2541i = i10;
        this.f2545o = abstractMap;
    }

    public final Iterator a() {
        switch (this.f2541i) {
            case 0:
                if (this.f2544n == null) {
                    this.f2544n = ((r2) this.f2545o).f2519m.entrySet().iterator();
                }
                break;
            default:
                if (this.f2544n == null) {
                    this.f2544n = ((kotlin.reflect.jvm.internal.impl.protobuf.i0) this.f2545o).f19795m.entrySet().iterator();
                }
                break;
        }
        return this.f2544n;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.f2541i) {
            case 0:
                int i10 = this.f2542l + 1;
                r2 r2Var = (r2) this.f2545o;
                if (i10 >= r2Var.f2518l.size()) {
                    if (r2Var.f2519m.isEmpty() || !a().hasNext()) {
                    }
                }
                break;
            default:
                if (this.f2542l + 1 < ((kotlin.reflect.jvm.internal.impl.protobuf.i0) this.f2545o).f19794l.size() || a().hasNext()) {
                }
                break;
        }
        return true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f2541i) {
            case 0:
                this.f2543m = true;
                int i10 = this.f2542l + 1;
                this.f2542l = i10;
                r2 r2Var = (r2) this.f2545o;
                if (i10 >= r2Var.f2518l.size()) {
                }
                break;
            default:
                this.f2543m = true;
                int i11 = this.f2542l + 1;
                this.f2542l = i11;
                kotlin.reflect.jvm.internal.impl.protobuf.i0 i0Var = (kotlin.reflect.jvm.internal.impl.protobuf.i0) this.f2545o;
                if (i11 >= i0Var.f19794l.size()) {
                }
                break;
        }
        return (Map.Entry) a().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        int i10 = this.f2541i;
        AbstractMap abstractMap = this.f2545o;
        switch (i10) {
            case 0:
                r2 r2Var = (r2) abstractMap;
                if (!this.f2543m) {
                    throw new IllegalStateException("remove() was called before next()");
                }
                this.f2543m = false;
                int i11 = r2.f2516q;
                r2Var.c();
                if (this.f2542l >= r2Var.f2518l.size()) {
                    a().remove();
                    return;
                }
                int i12 = this.f2542l;
                this.f2542l = i12 - 1;
                r2Var.j(i12);
                return;
            default:
                kotlin.reflect.jvm.internal.impl.protobuf.i0 i0Var = (kotlin.reflect.jvm.internal.impl.protobuf.i0) abstractMap;
                if (!this.f2543m) {
                    throw new IllegalStateException("remove() was called before next()");
                }
                this.f2543m = false;
                int i13 = kotlin.reflect.jvm.internal.impl.protobuf.i0.f19792p;
                i0Var.c();
                if (this.f2542l >= i0Var.f19794l.size()) {
                    a().remove();
                    return;
                }
                int i14 = this.f2542l;
                this.f2542l = i14 - 1;
                i0Var.i(i14);
                return;
        }
    }
}
