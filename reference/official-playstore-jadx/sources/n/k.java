package n;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.appcompat.widget.p4;
import androidx.core.view.n2;
import androidx.core.view.o2;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Interpolator f20422c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public o2 f20423d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f20424e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f20421b = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final p4 f20425f = new p4(this);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f20420a = new ArrayList();

    public final void a() {
        if (this.f20424e) {
            Iterator it = this.f20420a.iterator();
            while (it.hasNext()) {
                ((n2) it.next()).b();
            }
            this.f20424e = false;
        }
    }

    public final void b() {
        View view;
        if (this.f20424e) {
            return;
        }
        for (n2 n2Var : this.f20420a) {
            long j10 = this.f20421b;
            if (j10 >= 0) {
                n2Var.c(j10);
            }
            Interpolator interpolator = this.f20422c;
            if (interpolator != null && (view = (View) n2Var.f2294a.get()) != null) {
                view.animate().setInterpolator(interpolator);
            }
            if (this.f20423d != null) {
                n2Var.d(this.f20425f);
            }
            View view2 = (View) n2Var.f2294a.get();
            if (view2 != null) {
                view2.animate().start();
            }
        }
        this.f20424e = true;
    }
}
