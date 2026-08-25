package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.layout.LazyLayoutAnimation;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimationSpecsNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u0004\u0018\u00010\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"EmptyArray", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "[Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "specs", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimationSpecsNode;", "", "getSpecs", "(Ljava/lang/Object;)Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimationSpecsNode;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyListItemAnimatorKt {
    private static final LazyLayoutAnimation[] EmptyArray = new LazyLayoutAnimation[0];

    /* JADX INFO: Access modifiers changed from: private */
    public static final LazyLayoutAnimationSpecsNode getSpecs(Object obj) {
        if (obj instanceof LazyLayoutAnimationSpecsNode) {
            return (LazyLayoutAnimationSpecsNode) obj;
        }
        return null;
    }
}
