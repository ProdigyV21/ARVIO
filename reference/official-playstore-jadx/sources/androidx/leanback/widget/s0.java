package androidx.leanback.widget;

import android.graphics.Color;

/* JADX INFO: loaded from: classes3.dex */
public final class s0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f3115a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3116b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f3117c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f3118d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f3119e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f3120f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f3121g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f3122h = 1.0f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f3123i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ PagingIndicator f3124j;

    public s0(PagingIndicator pagingIndicator) {
        this.f3124j = pagingIndicator;
        this.f3123i = pagingIndicator.f2975i ? 1.0f : -1.0f;
    }

    public final void a() {
        int iRound = Math.round(this.f3115a * 255.0f);
        PagingIndicator pagingIndicator = this.f3124j;
        this.f3116b = Color.argb(iRound, Color.red(pagingIndicator.z), Color.green(pagingIndicator.z), Color.blue(pagingIndicator.z));
    }

    public final void b() {
        this.f3117c = 0.0f;
        this.f3118d = 0.0f;
        PagingIndicator pagingIndicator = this.f3124j;
        this.f3119e = pagingIndicator.f2976l;
        float f10 = pagingIndicator.f2977m;
        this.f3120f = f10;
        this.f3121g = f10 * pagingIndicator.F;
        this.f3115a = 0.0f;
        a();
    }
}
