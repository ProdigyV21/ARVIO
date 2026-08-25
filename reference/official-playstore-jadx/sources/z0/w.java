package z0;

import java.io.FileInputStream;

/* JADX INFO: loaded from: classes3.dex */
public final class w extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a0 f23135i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public FileInputStream f23136l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f23137m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ a0 f23138n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f23139o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(a0 a0Var, f7.c cVar) {
        super(cVar);
        this.f23138n = a0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f23137m = obj;
        this.f23139o |= Integer.MIN_VALUE;
        return this.f23138n.g(this);
    }
}
