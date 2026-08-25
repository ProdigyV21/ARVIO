package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutItemContentFactoryKt$SkippableItem$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $index;
    final /* synthetic */ LazyLayoutItemProvider $itemProvider;
    final /* synthetic */ Object $key;
    final /* synthetic */ Object $saveableStateHolder;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyLayoutItemContentFactoryKt$SkippableItem$2(LazyLayoutItemProvider lazyLayoutItemProvider, Object obj, int i10, Object obj2, int i11) {
        super(2);
        this.$itemProvider = lazyLayoutItemProvider;
        this.$saveableStateHolder = obj;
        this.$index = i10;
        this.$key = obj2;
        this.$$changed = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        LazyLayoutItemContentFactoryKt.m688SkippableItemJVlU9Rs(this.$itemProvider, this.$saveableStateHolder, this.$index, this.$key, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
    }
}
