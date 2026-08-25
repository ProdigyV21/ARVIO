package androidx.tv.foundation.lazy.grid;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class x0 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final x0 f5329l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final x0 f5330m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5331i;

    static {
        int i10 = 1;
        f5329l = new x0(i10, 0);
        f5330m = new x0(i10, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ x0(int i10, int i11) {
        super(i10);
        this.f5331i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f5331i) {
            case 0:
                List list = (List) obj;
                return new b1(((Number) list.get(0)).intValue(), ((Number) list.get(1)).intValue());
            default:
                ((Number) obj).intValue();
                return kotlin.collections.z.f19728i;
        }
    }
}
