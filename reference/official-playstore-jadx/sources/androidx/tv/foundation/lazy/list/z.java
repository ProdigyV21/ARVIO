package androidx.tv.foundation.lazy.list;

import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;

/* JADX INFO: loaded from: classes3.dex */
public final class z implements LazyLayoutIntervalContent.Interval {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r7.l f5524a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r7.l f5525b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final r7.r f5526c;

    public z(r7.l lVar, r7.l lVar2, r7.r rVar) {
        this.f5524a = lVar;
        this.f5525b = lVar2;
        this.f5526c = rVar;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval
    public final r7.l getKey() {
        return this.f5524a;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval
    public final r7.l getType() {
        return this.f5525b;
    }
}
