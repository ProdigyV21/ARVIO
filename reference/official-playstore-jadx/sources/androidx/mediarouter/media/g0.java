package androidx.mediarouter.media;

import android.content.ComponentName;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w f4362a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f4363b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f4364c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final t f4365d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public x f4366e;

    public g0(w wVar, boolean z) {
        this.f4362a = wVar;
        this.f4365d = wVar.f4494l;
        this.f4364c = z;
    }

    public final String toString() {
        return "MediaRouter.RouteProviderInfo{ packageName=" + ((ComponentName) this.f4365d.f4483b).getPackageName() + " }";
    }
}
