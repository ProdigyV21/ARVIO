package androidx.navigation;

import android.os.Bundle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class q extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4751i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Serializable f4752l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4753m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4754n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f4755o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q(Serializable serializable, Object obj, Object obj2, Object obj3, int i10) {
        super(1);
        this.f4751i = i10;
        this.f4752l = serializable;
        this.f4753m = obj;
        this.f4754n = obj2;
        this.f4755o = obj3;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f4751i) {
            case 0:
                ((kotlin.jvm.internal.f0) this.f4752l).f19738i = true;
                ((s) this.f4753m).a((d0) this.f4754n, (Bundle) this.f4755o, (i) obj, kotlin.collections.z.f19728i);
                break;
            default:
                v9.l0 l0Var = (v9.l0) obj;
                Iterator it = ((ArrayList) this.f4752l).iterator();
                while (it.hasNext()) {
                    l0Var.a(new v9.d((v9.n0) this.f4753m, (y9.p) this.f4754n, (y9.i) it.next(), (y9.i) this.f4755o));
                }
                break;
        }
        return x6.t0.f22605a;
    }
}
