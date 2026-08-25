package w9;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public final class h extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22516i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ArrayList f22517l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h(int i10, ArrayList arrayList) {
        super(0);
        this.f22516i = i10;
        this.f22517l = arrayList;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f22516i) {
            case 0:
                return this.f22517l;
            case 1:
                return this.f22517l;
            default:
                return ((kotlin.reflect.q) this.f22517l.get(0)).b();
        }
    }
}
