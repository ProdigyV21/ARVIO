package c3;

import android.content.Context;
import androidx.appcompat.app.i1;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class h implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f7395a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i1 f7396b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap f7397c;

    public h(Context context, i1 i1Var) {
        io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(context, 11);
        this.f7397c = new HashMap();
        this.f7395a = cVar;
        this.f7396b = i1Var;
    }

    @Override // c3.e
    public final synchronized j get(String str) {
        if (this.f7397c.containsKey(str)) {
            return (j) this.f7397c.get(str);
        }
        d dVarY = this.f7395a.y(str);
        if (dVarY == null) {
            return null;
        }
        i1 i1Var = this.f7396b;
        j jVarCreate = dVarY.create(new c((Context) i1Var.f1059l, (m3.a) i1Var.f1060m, (m3.a) i1Var.f1061n, str));
        this.f7397c.put(str, jVarCreate);
        return jVarCreate;
    }
}
