package z9;

import g8.a1;
import g8.b1;
import g8.h;
import kotlin.jvm.internal.r;
import r7.l;
import v9.d1;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends r implements l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final a f23272l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a f23273m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f23274i;

    static {
        int i10 = 1;
        f23272l = new a(i10, 0);
        f23273m = new a(i10, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(int i10, int i11) {
        super(i10);
        this.f23274i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f23274i) {
            case 0:
                h hVarE = ((d1) obj).I0().e();
                boolean z = false;
                if (hVarE != null && (hVarE instanceof b1) && (((b1) hVarE).d() instanceof a1)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            default:
                h hVarE2 = ((d1) obj).I0().e();
                boolean z5 = false;
                if (hVarE2 != null && ((hVarE2 instanceof a1) || (hVarE2 instanceof b1))) {
                    z5 = true;
                }
                return Boolean.valueOf(z5);
        }
    }
}
