package io.sentry.cache;

import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class f implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f17157i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ g f17158l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Map f17159m;

    public /* synthetic */ f(g gVar, Map map, int i10) {
        this.f17157i = i10;
        this.f17158l = gVar;
        this.f17159m = map;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f17157i) {
            case 0:
                this.f17158l.f(this.f17159m, "tags.json");
                break;
            default:
                this.f17158l.f(this.f17159m, "extras.json");
                break;
        }
    }
}
