package androidx.recyclerview.widget;

import android.os.Trace;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public final class u implements Runnable {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final ThreadLocal f5058o = new ThreadLocal();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final a8.h f5059p = new a8.h(3);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList f5060i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f5061l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f5062m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ArrayList f5063n;

    public static p1 c(RecyclerView recyclerView, int i10, long j10) {
        int childCount = ((j0) recyclerView.f4843p.f1059l).f4952a.getChildCount();
        for (int i11 = 0; i11 < childCount; i11++) {
            p1 p1VarG = RecyclerView.G(((j0) recyclerView.f4843p.f1059l).f4952a.getChildAt(i11));
            if (p1VarG.mPosition == i10 && !p1VarG.isInvalid()) {
                return null;
            }
        }
        f1 f1Var = recyclerView.f4837m;
        try {
            recyclerView.O();
            p1 p1VarK = f1Var.k(i10, j10);
            if (p1VarK != null) {
                if (!p1VarK.isBound() || p1VarK.isInvalid()) {
                    f1Var.a(p1VarK, false);
                } else {
                    f1Var.h(p1VarK.itemView);
                }
            }
            recyclerView.P(false);
            return p1VarK;
        } catch (Throwable th) {
            recyclerView.P(false);
            throw th;
        }
    }

    public final void a(RecyclerView recyclerView, int i10, int i11) {
        if (recyclerView.D && this.f5061l == 0) {
            this.f5061l = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        s sVar = recyclerView.f4844p0;
        sVar.f5040a = i10;
        sVar.f5041b = i11;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(long r17) {
        /*
            Method dump skipped, instruction units count: 341
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.u.b(long):void");
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList arrayList = this.f5060i;
        try {
            int i10 = androidx.core.os.r.f2132a;
            Trace.beginSection("RV Prefetch");
            if (!arrayList.isEmpty()) {
                int size = arrayList.size();
                long jMax = 0;
                for (int i11 = 0; i11 < size; i11++) {
                    RecyclerView recyclerView = (RecyclerView) arrayList.get(i11);
                    if (recyclerView.getWindowVisibility() == 0) {
                        jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                    }
                }
                if (jMax != 0) {
                    b(TimeUnit.MILLISECONDS.toNanos(jMax) + this.f5062m);
                }
            }
            this.f5061l = 0L;
            Trace.endSection();
        } catch (Throwable th) {
            this.f5061l = 0L;
            int i12 = androidx.core.os.r.f2132a;
            Trace.endSection();
            throw th;
        }
    }
}
