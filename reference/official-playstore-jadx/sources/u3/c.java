package u3;

import com.google.android.gms.common.api.h;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.api.internal.x;
import com.google.android.gms.common.api.internal.y;
import com.google.android.gms.common.api.n;
import com.google.android.gms.common.internal.a0;
import com.google.android.gms.common.internal.b0;
import com.google.android.gms.internal.auth.j4;

/* JADX INFO: loaded from: classes4.dex */
public final class c extends n implements b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i f22042a = new i("ClientTelemetry.API", new j4(11), new h());

    public final com.google.android.gms.tasks.h c(a0 a0Var) {
        x xVarBuilder = y.builder();
        xVarBuilder.f12809c = new o3.d[]{o4.d.f20771a};
        xVarBuilder.f12808b = false;
        xVarBuilder.f12807a = new kb.d(a0Var, 17);
        return doBestEffortWrite(xVarBuilder.a());
    }
}
