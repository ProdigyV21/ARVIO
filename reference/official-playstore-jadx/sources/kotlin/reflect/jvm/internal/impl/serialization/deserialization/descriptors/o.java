package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import kotlin.collections.q0;

/* JADX INFO: loaded from: classes5.dex */
public final class o extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f19876i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ q f19877l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ s f19878m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o(q qVar, s sVar, int i10) {
        super(0);
        this.f19876i = i10;
        this.f19877l = qVar;
        this.f19878m = sVar;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f19876i) {
            case 0:
                return q0.b0(this.f19877l.f19882a.keySet(), this.f19878m.o());
            default:
                return q0.b0(this.f19877l.f19883b.keySet(), this.f19878m.p());
        }
    }
}
