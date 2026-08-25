package androidx.loader.app;

import androidx.lifecycle.d1;
import androidx.lifecycle.g1;

/* JADX INFO: loaded from: classes3.dex */
public final class e implements g1 {
    @Override // androidx.lifecycle.g1
    public final d1 create(Class cls, o1.c cVar) {
        return create(cls);
    }

    @Override // androidx.lifecycle.g1
    public final d1 create(Class cls) {
        return new LoaderManagerImpl$LoaderViewModel();
    }
}
