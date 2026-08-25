package androidx.tv.foundation.lazy.grid;

import java.util.Comparator;

/* JADX INFO: loaded from: classes3.dex */
public final class m implements Comparator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5271i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ i2.k f5272l;

    public /* synthetic */ m(i2.k kVar, int i10) {
        this.f5271i = i10;
        this.f5272l = kVar;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.f5271i) {
            case 0:
                Object obj3 = ((c0) obj).f5176b;
                i2.k kVar = this.f5272l;
                return com.google.common.util.concurrent.r0.e(Integer.valueOf(kVar.getIndex(obj3)), Integer.valueOf(kVar.getIndex(((c0) obj2).f5176b)));
            case 1:
                Object obj4 = ((c0) obj2).f5176b;
                i2.k kVar2 = this.f5272l;
                return com.google.common.util.concurrent.r0.e(Integer.valueOf(kVar2.getIndex(obj4)), Integer.valueOf(kVar2.getIndex(((c0) obj).f5176b)));
            case 2:
                Object obj5 = ((androidx.tv.foundation.lazy.list.x) obj).f5514j;
                i2.k kVar3 = this.f5272l;
                return com.google.common.util.concurrent.r0.e(Integer.valueOf(kVar3.getIndex(obj5)), Integer.valueOf(kVar3.getIndex(((androidx.tv.foundation.lazy.list.x) obj2).f5514j)));
            default:
                Object obj6 = ((androidx.tv.foundation.lazy.list.x) obj2).f5514j;
                i2.k kVar4 = this.f5272l;
                return com.google.common.util.concurrent.r0.e(Integer.valueOf(kVar4.getIndex(obj6)), Integer.valueOf(kVar4.getIndex(((androidx.tv.foundation.lazy.list.x) obj).f5514j)));
        }
    }
}
