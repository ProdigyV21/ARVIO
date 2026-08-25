package androidx.tv.foundation.lazy.list;

/* JADX INFO: loaded from: classes3.dex */
public final class u extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ kotlin.collections.p f5493i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ x f5494l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(kotlin.collections.p pVar, x xVar) {
        super(1);
        this.f5493i = pVar;
        this.f5494l = xVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        x xVar = (x) obj;
        int i10 = xVar.f5505a;
        kotlin.collections.p pVar = this.f5493i;
        return Boolean.valueOf((i10 >= ((x) pVar.first()).f5505a && xVar.f5505a <= ((x) pVar.last()).f5505a) || xVar == this.f5494l);
    }
}
