package d7;

import d7.j;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b implements j.b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final r f14683i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final j.b f14684l;

    /* JADX WARN: Multi-variable type inference failed */
    public b(j.b bVar, r7.l lVar) {
        this.f14683i = (r) lVar;
        this.f14684l = bVar instanceof b ? ((b) bVar).f14684l : bVar;
    }
}
