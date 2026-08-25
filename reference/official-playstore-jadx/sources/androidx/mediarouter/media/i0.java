package androidx.mediarouter.media;

import android.content.ComponentName;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.cast.CredentialsData;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g0 f4386a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f4387b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f4388c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f4389d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f4390e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Uri f4391f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4392g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f4393h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4394i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f4395j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f4396l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4397m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f4398n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f4399o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f4400p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f4401q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Bundle f4403s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public IntentSender f4404t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public m f4405u;
    public final ArrayList k = new ArrayList();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f4402r = -1;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ArrayList f4406v = new ArrayList();

    public i0(g0 g0Var, String str, String str2, boolean z) {
        this.f4386a = g0Var;
        this.f4387b = str;
        this.f4388c = str2;
        this.f4393h = z;
    }

    public final w a() {
        g0 g0Var = this.f4386a;
        g0Var.getClass();
        k0.b();
        return g0Var.f4362a;
    }

    public final int b() {
        Bundle bundle;
        if (!this.f4406v.isEmpty()) {
            if (k0.f4425c == null) {
                return 0;
            }
            o0 o0Var = k0.c().f4346v;
            if (o0Var != null && (bundle = o0Var.f4470e) != null && !bundle.getBoolean("androidx.mediarouter.media.MediaRouterParams.ENABLE_GROUP_VOLUME_UX", true)) {
                return 0;
            }
        }
        return this.f4399o;
    }

    public final boolean c() {
        k0.b();
        i0 i0Var = k0.c().f4347w;
        if (i0Var == null) {
            throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
        }
        if (i0Var == this || this.f4398n == 3) {
            return true;
        }
        return TextUtils.equals(((ComponentName) a().f4494l.f4483b).getPackageName(), CredentialsData.CREDENTIALS_TYPE_ANDROID) && h("android.media.intent.category.LIVE_AUDIO") && !h("android.media.intent.category.LIVE_VIDEO");
    }

    public final boolean d() {
        return this.f4405u != null && this.f4392g;
    }

    public final boolean e(z zVar) {
        if (zVar == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        k0.b();
        ArrayList<IntentFilter> arrayList = this.k;
        if (arrayList == null) {
            return false;
        }
        zVar.a();
        if (zVar.f4518b.isEmpty()) {
            return false;
        }
        for (IntentFilter intentFilter : arrayList) {
            if (intentFilter != null) {
                Iterator it = zVar.f4518b.iterator();
                while (it.hasNext()) {
                    if (intentFilter.hasCategory((String) it.next())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x0101 A[EDGE_INSN: B:129:0x0101->B:68:0x0101 BREAK  A[LOOP:0: B:35:0x0094->B:135:0x0094], EDGE_INSN: B:130:0x0101->B:68:0x0101 BREAK  A[LOOP:0: B:35:0x0094->B:135:0x0094], EDGE_INSN: B:131:0x0101->B:68:0x0101 BREAK  A[LOOP:0: B:35:0x0094->B:135:0x0094], EDGE_INSN: B:132:0x0101->B:68:0x0101 BREAK  A[LOOP:0: B:35:0x0094->B:135:0x0094, LOOP_LABEL: LOOP:0: B:35:0x0094->B:135:0x0094], EDGE_INSN: B:133:0x0101->B:68:0x0101 BREAK  A[LOOP:0: B:35:0x0094->B:135:0x0094], EDGE_INSN: B:134:0x0101->B:68:0x0101 BREAK  A[LOOP:0: B:35:0x0094->B:135:0x0094, LOOP_LABEL: LOOP:0: B:35:0x0094->B:135:0x0094]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int f(androidx.mediarouter.media.m r15) {
        /*
            Method dump skipped, instruction units count: 590
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.i0.f(androidx.mediarouter.media.m):int");
    }

    public final void g(boolean z) {
        k0.b();
        k0.c().l(this, 3, z);
    }

    public final boolean h(String str) {
        k0.b();
        Iterator it = this.k.iterator();
        while (it.hasNext()) {
            if (((IntentFilter) it.next()).hasCategory(str)) {
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("MediaRouter.RouteInfo{ uniqueId=");
        sb2.append(this.f4388c);
        sb2.append(", name=");
        sb2.append(this.f4389d);
        sb2.append(", description=");
        sb2.append(this.f4390e);
        sb2.append(", iconUri=");
        sb2.append(this.f4391f);
        sb2.append(", enabled=");
        sb2.append(this.f4392g);
        sb2.append(", isSystemRoute=");
        sb2.append(this.f4393h);
        sb2.append(", connectionState=");
        sb2.append(this.f4394i);
        sb2.append(", canDisconnect=");
        sb2.append(this.f4395j);
        sb2.append(", playbackType=");
        sb2.append(this.f4396l);
        sb2.append(", playbackStream=");
        sb2.append(this.f4397m);
        sb2.append(", deviceType=");
        sb2.append(this.f4398n);
        sb2.append(", volumeHandling=");
        sb2.append(this.f4399o);
        sb2.append(", volume=");
        sb2.append(this.f4400p);
        sb2.append(", volumeMax=");
        sb2.append(this.f4401q);
        sb2.append(", presentationDisplayId=");
        sb2.append(this.f4402r);
        sb2.append(", extras=");
        sb2.append(this.f4403s);
        sb2.append(", settingsIntent=");
        sb2.append(this.f4404t);
        sb2.append(", providerPackageName=");
        sb2.append(((ComponentName) this.f4386a.f4365d.f4483b).getPackageName());
        if (!this.f4406v.isEmpty()) {
            sb2.append(", members=[");
            int size = this.f4406v.size();
            for (int i10 = 0; i10 < size; i10++) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                if (this.f4406v.get(i10) != this) {
                    sb2.append(((i0) this.f4406v.get(i10)).f4388c);
                }
            }
            sb2.append(']');
        }
        sb2.append(" }");
        return sb2.toString();
    }
}
