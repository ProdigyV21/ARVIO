package c3;

import android.content.Context;
import androidx.appcompat.app.i1;
import io.sentry.util.l;

/* JADX INFO: loaded from: classes4.dex */
public final class g implements e3.d, d3.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7393a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f7394b;

    public /* synthetic */ g(Object obj, int i10) {
        this.f7393a = i10;
        this.f7394b = obj;
    }

    @Override // javax.inject.Provider
    public final Object get() {
        switch (this.f7393a) {
            case 0:
                return new i1((Context) ((g) this.f7394b).f7394b, new l(8), new l(7), 9);
            default:
                return this.f7394b;
        }
    }
}
