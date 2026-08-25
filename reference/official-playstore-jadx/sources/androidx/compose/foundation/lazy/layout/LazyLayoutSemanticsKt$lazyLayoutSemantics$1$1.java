package androidx.compose.foundation.lazy.layout;

import androidx.compose.ui.semantics.CollectionInfo;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class LazyLayoutSemanticsKt$lazyLayoutSemantics$1$1 extends r implements l<SemanticsPropertyReceiver, t0> {
    final /* synthetic */ ScrollAxisRange $accessibilityScrollState;
    final /* synthetic */ CollectionInfo $collectionInfo;
    final /* synthetic */ l<Object, Integer> $indexForKeyMapping;
    final /* synthetic */ boolean $isVertical;
    final /* synthetic */ p<Float, Float, Boolean> $scrollByAction;
    final /* synthetic */ l<Integer, Boolean> $scrollToIndexAction;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LazyLayoutSemanticsKt$lazyLayoutSemantics$1$1(l<Object, Integer> lVar, boolean z, ScrollAxisRange scrollAxisRange, p<? super Float, ? super Float, Boolean> pVar, l<? super Integer, Boolean> lVar2, CollectionInfo collectionInfo) {
        super(1);
        this.$indexForKeyMapping = lVar;
        this.$isVertical = z;
        this.$accessibilityScrollState = scrollAxisRange;
        this.$scrollByAction = pVar;
        this.$scrollToIndexAction = lVar2;
        this.$collectionInfo = collectionInfo;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SemanticsPropertyReceiver) obj);
        return t0.f22605a;
    }

    public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver, true);
        SemanticsPropertiesKt.indexForKey(semanticsPropertyReceiver, this.$indexForKeyMapping);
        if (this.$isVertical) {
            SemanticsPropertiesKt.setVerticalScrollAxisRange(semanticsPropertyReceiver, this.$accessibilityScrollState);
        } else {
            SemanticsPropertiesKt.setHorizontalScrollAxisRange(semanticsPropertyReceiver, this.$accessibilityScrollState);
        }
        p<Float, Float, Boolean> pVar = this.$scrollByAction;
        if (pVar != null) {
            SemanticsPropertiesKt.scrollBy$default(semanticsPropertyReceiver, null, pVar, 1, null);
        }
        l<Integer, Boolean> lVar = this.$scrollToIndexAction;
        if (lVar != null) {
            SemanticsPropertiesKt.scrollToIndex$default(semanticsPropertyReceiver, null, lVar, 1, null);
        }
        SemanticsPropertiesKt.setCollectionInfo(semanticsPropertyReceiver, this.$collectionInfo);
    }
}
