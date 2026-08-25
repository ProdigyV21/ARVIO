package androidx.tv.foundation.lazy.grid;

import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;

/* JADX INFO: loaded from: classes3.dex */
public final class i implements LazyLayoutIntervalContent.Interval {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r7.l f5247a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r7.p f5248b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final kotlin.jvm.internal.r f5249c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final r7.r f5250d;

    /* JADX WARN: Multi-variable type inference failed */
    public i(r7.l lVar, r7.p pVar, r7.l lVar2, r7.r rVar) {
        this.f5247a = lVar;
        this.f5248b = pVar;
        this.f5249c = (kotlin.jvm.internal.r) lVar2;
        this.f5250d = rVar;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval
    public final r7.l getKey() {
        return this.f5247a;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.jvm.internal.r, r7.l] */
    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval
    public final r7.l getType() {
        return this.f5249c;
    }
}
