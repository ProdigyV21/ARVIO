package androidx.activity;

import android.os.Bundle;
import androidx.lifecycle.w0;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g implements b2.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f942a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f943b;

    public /* synthetic */ g(Object obj, int i10) {
        this.f942a = i10;
        this.f943b = obj;
    }

    @Override // b2.d
    public final Bundle a() {
        switch (this.f942a) {
            case 0:
                return t.c((t) this.f943b);
            default:
                return w0.a((w0) this.f943b);
        }
    }
}
