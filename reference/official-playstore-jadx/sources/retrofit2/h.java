package retrofit2;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes5.dex */
public final class h implements e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21508i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Type f21509l;

    public /* synthetic */ h(int i10, Type type) {
        this.f21508i = i10;
        this.f21509l = type;
    }

    @Override // retrofit2.e
    public final Type h() {
        switch (this.f21508i) {
        }
        return this.f21509l;
    }

    @Override // retrofit2.e
    public final Object u(y yVar) {
        switch (this.f21508i) {
            case 0:
                i iVar = new i(yVar);
                yVar.G(new g(iVar, 0));
                return iVar;
            default:
                i iVar2 = new i(yVar);
                yVar.G(new g(iVar2, 1));
                return iVar2;
        }
    }
}
