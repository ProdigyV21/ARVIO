package l5;

import android.content.Context;
import com.google.android.gms.tasks.t;
import com.google.firebase.components.i;
import java.util.Set;
import java.util.concurrent.Executor;
import qb.l;

/* JADX INFO: loaded from: classes4.dex */
public final class c implements e, f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f20032a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f20033b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final m5.b f20034c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Set f20035d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Executor f20036e;

    public c(Context context, String str, Set set, m5.b bVar, Executor executor) {
        this.f20032a = new i(context, str, 1);
        this.f20035d = set;
        this.f20036e = executor;
        this.f20034c = bVar;
        this.f20033b = context;
    }

    @Override // l5.e
    public final t a() {
        if (!ac.b.R(this.f20033b)) {
            return l.n("");
        }
        return l.d(this.f20036e, new b(this, 0));
    }

    public final void b() {
        if (this.f20035d.size() <= 0) {
            l.n(null);
        } else if (!ac.b.R(this.f20033b)) {
            l.n(null);
        } else {
            l.d(this.f20036e, new b(this, 1));
        }
    }
}
