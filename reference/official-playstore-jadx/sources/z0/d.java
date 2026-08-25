package z0;

import java.io.Serializable;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class d extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Serializable f23073i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Iterator f23074l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f23075m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f23076n;

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f23075m = obj;
        this.f23076n |= Integer.MIN_VALUE;
        return qb.d.c(null, null, this);
    }
}
