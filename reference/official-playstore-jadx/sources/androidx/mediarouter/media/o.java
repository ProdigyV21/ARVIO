package androidx.mediarouter.media;

import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class o implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4461i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ s f4462l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r f4463m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ m f4464n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Collection f4465o;

    public /* synthetic */ o(s sVar, b bVar, m mVar, Collection collection, int i10) {
        this.f4461i = i10;
        this.f4462l = sVar;
        this.f4463m = bVar;
        this.f4464n = mVar;
        this.f4465o = collection;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4461i) {
            case 0:
                this.f4463m.a(this.f4462l, this.f4464n, (ArrayList) this.f4465o);
                break;
            default:
                s sVar = this.f4462l;
                sVar.getClass();
                this.f4463m.a(sVar, this.f4464n, this.f4465o);
                break;
        }
    }
}
