package androidx.tv.foundation.lazy.list;

import com.google.common.util.concurrent.r0;
import java.util.Comparator;

/* JADX INFO: loaded from: classes3.dex */
public final class j implements Comparator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5399i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ k f5400l;

    public /* synthetic */ j(k kVar, int i10) {
        this.f5399i = i10;
        this.f5400l = kVar;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.f5399i) {
            case 0:
                k kVar = this.f5400l;
                return r0.e(Integer.valueOf(((i2.k) kVar.f5412f).getIndex(((x) obj).f5514j)), Integer.valueOf(((i2.k) kVar.f5412f).getIndex(((x) obj2).f5514j)));
            default:
                k kVar2 = this.f5400l;
                return r0.e(Integer.valueOf(((i2.k) kVar2.f5412f).getIndex(((x) obj2).f5514j)), Integer.valueOf(((i2.k) kVar2.f5412f).getIndex(((x) obj).f5514j)));
        }
    }
}
