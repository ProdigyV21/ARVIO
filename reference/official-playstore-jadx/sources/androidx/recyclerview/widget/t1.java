package androidx.recyclerview.widget;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public final class t1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5051a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5052b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f5053c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5054d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5055e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f5056f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ StaggeredGridLayoutManager f5057g;

    public t1(StaggeredGridLayoutManager staggeredGridLayoutManager) {
        this.f5057g = staggeredGridLayoutManager;
        a();
    }

    public final void a() {
        this.f5051a = -1;
        this.f5052b = Integer.MIN_VALUE;
        this.f5053c = false;
        this.f5054d = false;
        this.f5055e = false;
        int[] iArr = this.f5056f;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
    }
}
