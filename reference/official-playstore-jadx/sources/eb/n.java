package eb;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class n extends kotlin.jvm.internal.m implements r7.p {
    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ab.g gVar = (ab.g) obj;
        int iIntValue = ((Number) obj2).intValue();
        o oVar = (o) this.receiver;
        oVar.getClass();
        boolean z = !gVar.i(iIntValue) && gVar.g(iIntValue).b();
        oVar.f15164b = z;
        return Boolean.valueOf(z);
    }
}
