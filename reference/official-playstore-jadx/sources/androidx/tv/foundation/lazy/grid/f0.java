package androidx.tv.foundation.lazy.grid;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.SnapshotIntStateKt;
import io.sentry.p7;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final class f0 implements jb.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5231a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f5232b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f5233c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5234d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f5235e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f5236f;

    public f0(int i10, int i11, int i12) {
        this.f5231a = i12;
        switch (i12) {
            case 1:
                this.f5232b = SnapshotIntStateKt.mutableIntStateOf(i10);
                this.f5233c = SnapshotIntStateKt.mutableIntStateOf(i11);
                this.f5236f = new i2.l(i10, 30, 100);
                break;
            default:
                this.f5232b = SnapshotIntStateKt.mutableIntStateOf(i10);
                this.f5233c = SnapshotIntStateKt.mutableIntStateOf(i11);
                this.f5236f = new i2.l(i10, 90, 200);
                break;
        }
    }

    public void a() {
        synchronized (((gb.g) this.f5236f)) {
            if (this.f5234d) {
                return;
            }
            this.f5234d = true;
            ib.c.d((xb.m0) this.f5233c);
            try {
                ((p7) this.f5232b).a();
            } catch (IOException unused) {
            }
        }
    }

    public void b(int i10, int i11) {
        switch (this.f5231a) {
            case 0:
                if (i10 < 0.0f) {
                    throw new IllegalArgumentException(a0.c.k("Index should be non-negative (", i10, ')').toString());
                }
                ((MutableIntState) this.f5232b).setIntValue(i10);
                ((i2.l) this.f5236f).a(i10);
                ((MutableIntState) this.f5233c).setIntValue(i11);
                return;
            default:
                if (i10 < 0.0f) {
                    throw new IllegalArgumentException(a0.c.k("Index should be non-negative (", i10, ')').toString());
                }
                ((MutableIntState) this.f5232b).setIntValue(i10);
                ((i2.l) this.f5236f).a(i10);
                ((MutableIntState) this.f5233c).setIntValue(i11);
                return;
        }
    }

    public f0(gb.g gVar, p7 p7Var) {
        this.f5231a = 2;
        this.f5236f = gVar;
        this.f5232b = p7Var;
        xb.m0 m0VarE = p7Var.e(1);
        this.f5233c = m0VarE;
        this.f5235e = new gb.f(gVar, this, m0VarE);
    }
}
