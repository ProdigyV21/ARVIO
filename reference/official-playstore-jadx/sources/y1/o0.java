package y1;

import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f22836a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f22837b;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Executor f22841f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Executor f22842g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a3.b f22843h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f22844i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f22846l;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public HashSet f22850p;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f22838c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f22839d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f22840e = new ArrayList();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f22845j = 1;
    public boolean k = true;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f22847m = -1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final x8.o f22848n = new x8.o(1);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final LinkedHashSet f22849o = new LinkedHashSet();

    public o0(Context context, String str) {
        this.f22836a = context;
        this.f22837b = str;
    }

    public final void a(z1.b... bVarArr) {
        if (this.f22850p == null) {
            this.f22850p = new HashSet();
        }
        for (z1.b bVar : bVarArr) {
            this.f22850p.add(Integer.valueOf(bVar.f23158a));
            this.f22850p.add(Integer.valueOf(bVar.f23159b));
        }
        this.f22848n.a((z1.b[]) Arrays.copyOf(bVarArr, bVarArr.length));
    }
}
