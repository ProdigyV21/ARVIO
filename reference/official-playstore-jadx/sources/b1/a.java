package b1;

import a8.g0;
import a8.l0;
import android.content.Context;
import java.util.Collections;
import kotlin.collections.z;
import kotlin.reflect.m;
import z0.a0;

/* JADX INFO: loaded from: classes3.dex */
public final class a implements u7.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7106a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final pa.e f7107b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f7108c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile c1.d f7109d;

    public a(String str, pa.e eVar) {
        this.f7106a = str;
        this.f7107b = eVar;
    }

    @Override // u7.c
    public final Object getValue(Object obj, m mVar) {
        c1.d dVar;
        Context context = (Context) obj;
        c1.d dVar2 = this.f7109d;
        if (dVar2 != null) {
            return dVar2;
        }
        synchronized (this.f7108c) {
            try {
                if (this.f7109d == null) {
                    Context applicationContext = context.getApplicationContext();
                    z zVar = z.f19728i;
                    pa.e eVar = this.f7107b;
                    this.f7109d = new c1.d(new a0(new g0(new l0(applicationContext, this, 5), 14), Collections.singletonList(new androidx.work.impl.constraints.controllers.c(zVar, null, 9)), new a1.a(), eVar));
                }
                dVar = this.f7109d;
            } catch (Throwable th) {
                throw th;
            }
        }
        return dVar;
    }
}
