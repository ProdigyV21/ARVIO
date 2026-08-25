package androidx.work;

import android.content.Context;
import androidx.core.provider.j;
import androidx.work.impl.r;
import f2.b;
import java.util.Collections;
import java.util.List;
import m2.c;
import m2.f0;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public final class WorkManagerInitializer implements b<f0> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f6697a = t.f("WrkMgrInitializer");

    @Override // f2.b
    public final Object create(Context context) {
        t.d().a(f6697a, "Initializing WorkManager with default configuration.");
        r.a0(context, new c(new j(6, (byte) 0)));
        return r.Z(context);
    }

    @Override // f2.b
    public final List dependencies() {
        return Collections.EMPTY_LIST;
    }
}
