package j8;

import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class e implements v9.o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ f f19261a;

    public e(f fVar) {
        this.f19261a = fVar;
    }

    @Override // v9.o0
    public final Collection d() {
        return ((kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.w) this.f19261a).o0().I0().d();
    }

    @Override // v9.o0
    public final g8.h e() {
        return this.f19261a;
    }

    @Override // v9.o0
    public final boolean f() {
        return true;
    }

    @Override // v9.o0
    public final List getParameters() {
        List list = ((kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.w) this.f19261a).A;
        if (list != null) {
            return list;
        }
        kotlin.jvm.internal.p.i("typeConstructorParameters");
        throw null;
    }

    @Override // v9.o0
    public final d8.k j() {
        return m9.d.e(this.f19261a);
    }

    public final String toString() {
        return "[typealias " + this.f19261a.getName().d() + ']';
    }
}
