package androidx.tv.foundation.lazy.grid;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;

/* JADX INFO: loaded from: classes3.dex */
public final class q extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ LazyGridItemProviderImpl f5284i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f5285l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f5286m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f5287n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(LazyGridItemProviderImpl lazyGridItemProviderImpl, int i10, Object obj, int i11) {
        super(2);
        this.f5284i = lazyGridItemProviderImpl;
        this.f5285l = i10;
        this.f5286m = obj;
        this.f5287n = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iUpdateChangedFlags = RecomposeScopeImplKt.updateChangedFlags(this.f5287n | 1);
        this.f5284i.Item(this.f5285l, this.f5286m, (Composer) obj, iUpdateChangedFlags);
        return x6.t0.f22605a;
    }
}
