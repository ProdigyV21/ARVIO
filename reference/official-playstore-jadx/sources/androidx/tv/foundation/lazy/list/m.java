package androidx.tv.foundation.lazy.list;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class m extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ LazyListItemProviderImpl f5423i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f5424l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f5425m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f5426n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(LazyListItemProviderImpl lazyListItemProviderImpl, int i10, Object obj, int i11) {
        super(2);
        this.f5423i = lazyListItemProviderImpl;
        this.f5424l = i10;
        this.f5425m = obj;
        this.f5426n = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iUpdateChangedFlags = RecomposeScopeImplKt.updateChangedFlags(this.f5426n | 1);
        this.f5423i.Item(this.f5424l, this.f5425m, (Composer) obj, iUpdateChangedFlags);
        return t0.f22605a;
    }
}
