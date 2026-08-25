package androidx.tv.foundation.lazy.grid;

import java.util.Comparator;

/* JADX INFO: loaded from: classes3.dex */
public final class n implements Comparator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5274i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f5275l;

    public /* synthetic */ n(Object obj, int i10) {
        this.f5274i = i10;
        this.f5275l = obj;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.f5274i) {
            case 0:
                o oVar = (o) this.f5275l;
                return com.google.common.util.concurrent.r0.e(Integer.valueOf(oVar.f5277b.getIndex(((c0) obj).f5176b)), Integer.valueOf(oVar.f5277b.getIndex(((c0) obj2).f5176b)));
            case 1:
                o oVar2 = (o) this.f5275l;
                return com.google.common.util.concurrent.r0.e(Integer.valueOf(oVar2.f5277b.getIndex(((c0) obj2).f5176b)), Integer.valueOf(oVar2.f5277b.getIndex(((c0) obj).f5176b)));
            default:
                r7.l lVar = (r7.l) this.f5275l;
                return com.google.common.util.concurrent.r0.e(lVar.invoke((v9.w) obj).toString(), lVar.invoke((v9.w) obj2).toString());
        }
    }
}
