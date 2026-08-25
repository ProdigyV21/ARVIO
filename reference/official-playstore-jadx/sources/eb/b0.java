package eb;

import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class b0 extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public x6.d f15111i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public androidx.appcompat.widget.a f15112l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public LinkedHashMap f15113m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f15114n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public /* synthetic */ Object f15115o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.widget.a f15116p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f15117q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b0(androidx.appcompat.widget.a aVar, f7.a aVar2) {
        super(aVar2);
        this.f15116p = aVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f15115o = obj;
        this.f15117q |= Integer.MIN_VALUE;
        return androidx.appcompat.widget.a.d(this.f15116p, null, this);
    }
}
