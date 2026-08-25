package androidx.compose.material3.carousel;

import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u001a8\u0010\n\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a@\u0010\n\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00002\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0000¢\u0006\u0004\b\n\u0010\u000f¨\u0006\u0010"}, d2 = {"", "carouselMainAxisSize", "Landroidx/compose/material3/carousel/CarouselAlignment;", "carouselAlignment", "Lkotlin/Function1;", "Landroidx/compose/material3/carousel/KeylineListScope;", "Lx6/t0;", "Lx6/n;", "keylines", "Landroidx/compose/material3/carousel/KeylineList;", "keylineListOf", "(FLandroidx/compose/material3/carousel/CarouselAlignment;Lr7/l;)Landroidx/compose/material3/carousel/KeylineList;", "", "pivotIndex", "pivotOffset", "(FIFLr7/l;)Landroidx/compose/material3/carousel/KeylineList;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KeylineKt {
    public static final KeylineList keylineListOf(float f10, CarouselAlignment carouselAlignment, l<? super KeylineListScope, t0> lVar) {
        KeylineListScopeImpl keylineListScopeImpl = new KeylineListScopeImpl();
        lVar.invoke(keylineListScopeImpl);
        return keylineListScopeImpl.createWithAlignment(f10, carouselAlignment);
    }

    public static final KeylineList keylineListOf(float f10, int i10, float f11, l<? super KeylineListScope, t0> lVar) {
        KeylineListScopeImpl keylineListScopeImpl = new KeylineListScopeImpl();
        lVar.invoke(keylineListScopeImpl);
        return keylineListScopeImpl.createWithPivot(f10, i10, f11);
    }
}
