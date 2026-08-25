package androidx.mediarouter.media;

import android.os.Bundle;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public final class g1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ h1 f4367a;

    public g1(h1 h1Var) {
        this.f4367a = h1Var;
    }

    public static void a(String str, Bundle bundle) {
        Log.d("MediaRouteProviderProxy", "Error: " + str + ", data: " + bundle);
    }

    public final void b(Bundle bundle) {
        bundle.getString("groupableTitle");
        h1 h1Var = this.f4367a;
        h1Var.getClass();
        bundle.getString("transferableTitle");
        h1Var.getClass();
    }
}
