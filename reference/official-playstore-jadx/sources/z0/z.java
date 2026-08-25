package z0;

import java.io.File;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: classes3.dex */
public final class z extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a0 f23151i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public File f23152l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public FileOutputStream f23153m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public FileOutputStream f23154n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public /* synthetic */ Object f23155o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ a0 f23156p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f23157q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(a0 a0Var, f7.c cVar) {
        super(cVar);
        this.f23156p = a0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f23155o = obj;
        this.f23157q |= Integer.MIN_VALUE;
        return this.f23156p.j(null, this);
    }
}
