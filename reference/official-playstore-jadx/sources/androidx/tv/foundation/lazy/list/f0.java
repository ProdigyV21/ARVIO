package androidx.tv.foundation.lazy.list;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class f0 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final f0 f5387l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final f0 f5388m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5389i;

    static {
        int i10 = 1;
        f5387l = new f0(i10, 0);
        f5388m = new f0(i10, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f0(int i10, int i11) {
        super(i10);
        this.f5389i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f5389i) {
            case 0:
                ((Number) obj).intValue();
                return null;
            case 1:
                List list = (List) obj;
                return new m0(((Number) list.get(0)).intValue(), ((Number) list.get(1)).intValue());
            default:
                ((Number) obj).intValue();
                return null;
        }
    }
}
