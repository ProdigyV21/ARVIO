package jb;

import java.io.IOException;
import xb.o0;
import xb.v;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends v {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f19416i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ h f19417l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ e f19418m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(o0 o0Var, h hVar, e eVar) {
        super(o0Var);
        this.f19417l = hVar;
        this.f19418m = eVar;
    }

    @Override // xb.v, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        super.close();
        if (this.f19416i) {
            return;
        }
        this.f19416i = true;
        h hVar = this.f19417l;
        e eVar = this.f19418m;
        synchronized (hVar) {
            int i10 = eVar.f19426h - 1;
            eVar.f19426h = i10;
            if (i10 == 0 && eVar.f19424f) {
                hVar.T(eVar);
            }
        }
    }
}
