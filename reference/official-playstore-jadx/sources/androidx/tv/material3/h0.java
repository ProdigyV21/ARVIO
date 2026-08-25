package androidx.tv.material3;

import androidx.compose.ui.semantics.CollectionInfo;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;

/* JADX INFO: loaded from: classes3.dex */
public final class h0 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ ScrollAxisRange f6020i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ androidx.navigation.compose.j f6021l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f6022m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h0(ScrollAxisRange scrollAxisRange, androidx.navigation.compose.j jVar, int i10) {
        super(1);
        this.f6020i = scrollAxisRange;
        this.f6021l = jVar;
        this.f6022m = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj;
        SemanticsPropertiesKt.setHorizontalScrollAxisRange(semanticsPropertyReceiver, this.f6020i);
        SemanticsPropertiesKt.scrollBy$default(semanticsPropertyReceiver, null, this.f6021l, 1, null);
        SemanticsPropertiesKt.setCollectionInfo(semanticsPropertyReceiver, new CollectionInfo(1, this.f6022m));
        return x6.t0.f22605a;
    }
}
