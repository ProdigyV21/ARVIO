package ga;

import androidx.core.view.z0;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class t implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m f15603a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r7.l f15604b;

    public t(m mVar, r7.l lVar) {
        this.f15603a = mVar;
        this.f15604b = lVar;
    }

    @Override // ga.m
    public final Iterator iterator() {
        return new z0(this);
    }
}
