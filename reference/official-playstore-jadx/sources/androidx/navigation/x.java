package androidx.navigation;

import android.os.Bundle;

/* JADX INFO: loaded from: classes3.dex */
public final class x extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4801i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Bundle f4802l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ x(int i10, Bundle bundle) {
        super(1);
        this.f4801i = i10;
        this.f4802l = bundle;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f4801i) {
            case 0:
                return Boolean.valueOf(!this.f4802l.containsKey((String) obj));
            default:
                return Boolean.valueOf(!this.f4802l.containsKey((String) obj));
        }
    }
}
