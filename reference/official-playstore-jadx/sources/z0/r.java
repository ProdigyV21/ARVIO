package z0;

import java.io.Serializable;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class r extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a0 f23106i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f23107l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Serializable f23108m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f23109n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public t f23110o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Iterator f23111p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public /* synthetic */ Object f23112q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ a0 f23113r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f23114s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(a0 a0Var, f7.c cVar) {
        super(cVar);
        this.f23113r = a0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f23112q = obj;
        this.f23114s |= Integer.MIN_VALUE;
        return this.f23113r.d(this);
    }
}
