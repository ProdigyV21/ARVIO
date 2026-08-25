package na;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class l extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f20605i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f20606l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ androidx.work.impl.constraints.i f20607m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public k f20608n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Iterator f20609o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(androidx.work.impl.constraints.i iVar, d7.d dVar) {
        super(dVar);
        this.f20607m = iVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20605i = obj;
        this.f20606l |= Integer.MIN_VALUE;
        return this.f20607m.collect(null, this);
    }
}
