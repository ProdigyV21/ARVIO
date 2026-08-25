package p9;

import kotlin.collections.z;

/* JADX INFO: loaded from: classes5.dex */
public final class q extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21237i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r f21238l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q(r rVar, int i10) {
        super(0);
        this.f21237i = i10;
        this.f21238l = rVar;
    }

    @Override // r7.a
    public final Object invoke() {
        int i10 = this.f21237i;
        r rVar = this.f21238l;
        switch (i10) {
            case 0:
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.k kVar = rVar.f21240b;
                return t7.a.E(i9.q.i(kVar), i9.q.j(kVar));
            default:
                return rVar.f21241c ? t7.a.F(i9.q.h(rVar.f21240b)) : z.f19728i;
        }
    }
}
