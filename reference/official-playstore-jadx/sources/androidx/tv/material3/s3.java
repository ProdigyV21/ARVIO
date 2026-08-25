package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class s3 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6389i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f6390l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ s3(int i10, r7.a aVar) {
        super(0);
        this.f6389i = i10;
        this.f6390l = aVar;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f6389i) {
            case 0:
                r7.a aVar = this.f6390l;
                if (aVar == null) {
                    return Boolean.FALSE;
                }
                aVar.invoke();
                return Boolean.TRUE;
            case 1:
                r7.a aVar2 = this.f6390l;
                if (aVar2 == null) {
                    return Boolean.FALSE;
                }
                aVar2.invoke();
                return Boolean.TRUE;
            case 2:
                this.f6390l.invoke();
                return Boolean.TRUE;
            case 3:
                r7.a aVar3 = this.f6390l;
                if (aVar3 == null) {
                    return Boolean.FALSE;
                }
                aVar3.invoke();
                return Boolean.TRUE;
            default:
                p9.n nVar = (p9.n) this.f6390l.invoke();
                return nVar instanceof p9.j ? ((p9.j) nVar).h() : nVar;
        }
    }
}
