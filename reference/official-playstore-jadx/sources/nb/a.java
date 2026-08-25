package nb;

import androidx.appcompat.widget.u;
import java.io.IOException;
import lb.k;
import xb.j;
import xb.l;
import xb.o0;
import xb.q0;
import xb.w;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a implements o0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final w f20698i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f20699l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ u f20700m;

    public a(u uVar) {
        this.f20700m = uVar;
        this.f20698i = new w(((l) uVar.f1765d).timeout());
    }

    public final void i() {
        u uVar = this.f20700m;
        int i10 = uVar.f1762a;
        if (i10 == 6) {
            return;
        }
        if (i10 != 5) {
            throw new IllegalStateException("state: " + uVar.f1762a);
        }
        w wVar = this.f20698i;
        q0 q0Var = wVar.f22799e;
        wVar.f22799e = q0.f22791d;
        q0Var.a();
        q0Var.b();
        uVar.f1762a = 6;
    }

    @Override // xb.o0
    public long read(j jVar, long j10) throws IOException {
        u uVar = this.f20700m;
        try {
            return ((l) uVar.f1765d).read(jVar, j10);
        } catch (IOException e5) {
            ((k) uVar.f1764c).l();
            i();
            throw e5;
        }
    }

    @Override // xb.o0
    public final q0 timeout() {
        return this.f20698i;
    }
}
