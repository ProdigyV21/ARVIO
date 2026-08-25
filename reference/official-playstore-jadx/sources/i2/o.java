package i2;

import androidx.compose.ui.semantics.CollectionInfo;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.tv.material3.d4;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class o extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ d4 f16060i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f16061l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ ScrollAxisRange f16062m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.p f16063n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.l f16064o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ CollectionInfo f16065p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(d4 d4Var, boolean z, ScrollAxisRange scrollAxisRange, p pVar, androidx.navigation.compose.h hVar, CollectionInfo collectionInfo) {
        super(1);
        this.f16060i = d4Var;
        this.f16061l = z;
        this.f16062m = scrollAxisRange;
        this.f16063n = pVar;
        this.f16064o = hVar;
        this.f16065p = collectionInfo;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj;
        SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver, true);
        SemanticsPropertiesKt.indexForKey(semanticsPropertyReceiver, this.f16060i);
        boolean z = this.f16061l;
        ScrollAxisRange scrollAxisRange = this.f16062m;
        if (z) {
            SemanticsPropertiesKt.setVerticalScrollAxisRange(semanticsPropertyReceiver, scrollAxisRange);
        } else {
            SemanticsPropertiesKt.setHorizontalScrollAxisRange(semanticsPropertyReceiver, scrollAxisRange);
        }
        r7.p pVar = this.f16063n;
        if (pVar != null) {
            SemanticsPropertiesKt.scrollBy$default(semanticsPropertyReceiver, null, pVar, 1, null);
        }
        r7.l lVar = this.f16064o;
        if (lVar != null) {
            SemanticsPropertiesKt.scrollToIndex$default(semanticsPropertyReceiver, null, lVar, 1, null);
        }
        SemanticsPropertiesKt.setCollectionInfo(semanticsPropertyReceiver, this.f16065p);
        return t0.f22605a;
    }
}
