package gb;

import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes5.dex */
public final class g0 {
    public int A;
    public long B;
    public kb.d C;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public androidx.emoji2.text.e0 f15663a = new androidx.emoji2.text.e0(8);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public o f15664b = new o(5, 5, TimeUnit.MINUTES);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f15665c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f15666d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public com.google.firebase.installations.d f15667e = new com.google.firebase.installations.d(2);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f15668f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public u f15669g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f15670h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f15671i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public r f15672j;
    public g k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public t f15673l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ProxySelector f15674m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public u f15675n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public SocketFactory f15676o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public SSLSocketFactory f15677p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public X509TrustManager f15678q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f15679r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f15680s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ub.c f15681t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public k f15682u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public t7.a f15683v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f15684w;
    public int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f15685y;
    public int z;

    public g0() {
        u uVar = b.f15616a;
        this.f15669g = uVar;
        this.f15670h = true;
        this.f15671i = true;
        this.f15672j = r.f15809b;
        this.f15673l = t.f15815a;
        this.f15675n = uVar;
        this.f15676o = SocketFactory.getDefault();
        this.f15679r = h0.O;
        this.f15680s = h0.N;
        this.f15681t = ub.c.f22117a;
        this.f15682u = k.f15727c;
        this.x = 10000;
        this.f15685y = 10000;
        this.z = 10000;
        this.B = 1024L;
    }

    public final void a(long j10, TimeUnit timeUnit) {
        this.f15684w = ib.c.b("timeout", j10, timeUnit);
    }

    public final void b(long j10, TimeUnit timeUnit) {
        this.x = ib.c.b("timeout", j10, timeUnit);
    }

    public final void c(r rVar) {
        this.f15672j = rVar;
    }

    public final void d(t tVar) {
        if (!tVar.equals(this.f15673l)) {
            this.C = null;
        }
        this.f15673l = tVar;
    }

    public final void e(long j10, TimeUnit timeUnit) {
        this.f15685y = ib.c.b("timeout", j10, timeUnit);
    }

    public final void f(long j10) {
        this.z = ib.c.b("timeout", j10, TimeUnit.SECONDS);
    }
}
