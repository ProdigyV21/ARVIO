package x5;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class b extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Map f22561i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Iterator f22562l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public d f22563m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ua.d f22564n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Map f22565o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f22566p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public /* synthetic */ Object f22567q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ c f22568r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f22569s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c cVar, f7.c cVar2) {
        super(cVar2);
        this.f22568r = cVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f22567q = obj;
        this.f22569s |= Integer.MIN_VALUE;
        return this.f22568r.b(this);
    }
}
