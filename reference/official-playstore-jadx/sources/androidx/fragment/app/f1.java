package androidx.fragment.app;

/* JADX INFO: loaded from: classes3.dex */
public final class f1 implements androidx.lifecycle.g1 {
    @Override // androidx.lifecycle.g1
    public final androidx.lifecycle.d1 create(Class cls) {
        return new FragmentManagerViewModel(true);
    }

    @Override // androidx.lifecycle.g1
    public final androidx.lifecycle.d1 create(Class cls, o1.c cVar) {
        return create(cls);
    }
}
