package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class LazyLayoutItemContentFactory$CachedItemContent$createContentLambda$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ LazyLayoutItemContentFactory this$0;
    final /* synthetic */ LazyLayoutItemContentFactory.CachedItemContent this$1;

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory$CachedItemContent$createContentLambda$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/DisposableEffectResult;", "Landroidx/compose/runtime/DisposableEffectScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<DisposableEffectScope, DisposableEffectResult> {
        final /* synthetic */ LazyLayoutItemContentFactory.CachedItemContent this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(LazyLayoutItemContentFactory.CachedItemContent cachedItemContent) {
            super(1);
            this.this$0 = cachedItemContent;
        }

        @Override // r7.l
        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
            final LazyLayoutItemContentFactory.CachedItemContent cachedItemContent = this.this$0;
            return new DisposableEffectResult() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory$CachedItemContent$createContentLambda$1$2$invoke$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                    cachedItemContent._content = null;
                }
            };
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyLayoutItemContentFactory$CachedItemContent$createContentLambda$1(LazyLayoutItemContentFactory lazyLayoutItemContentFactory, LazyLayoutItemContentFactory.CachedItemContent cachedItemContent) {
        super(2);
        this.this$0 = lazyLayoutItemContentFactory;
        this.this$1 = cachedItemContent;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        Composer composer2;
        if ((i10 & 11) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1403994769, i10, -1, "androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory.CachedItemContent.createContentLambda.<anonymous> (LazyLayoutItemContentFactory.kt:91)");
        }
        LazyLayoutItemProvider lazyLayoutItemProvider = (LazyLayoutItemProvider) this.this$0.getItemProvider().invoke();
        int index = this.this$1.getIndex();
        if ((index >= lazyLayoutItemProvider.getItemCount() || !kotlin.jvm.internal.p.a(lazyLayoutItemProvider.getKey(index), this.this$1.getKey())) && (index = lazyLayoutItemProvider.getIndex(this.this$1.getKey())) != -1) {
            this.this$1.index = index;
        }
        int i11 = index;
        boolean z = i11 != -1;
        LazyLayoutItemContentFactory lazyLayoutItemContentFactory = this.this$0;
        LazyLayoutItemContentFactory.CachedItemContent cachedItemContent = this.this$1;
        composer.startReusableGroup(ComposerKt.reuseKey, Boolean.valueOf(z));
        boolean zChanged = composer.changed(z);
        if (z) {
            composer2 = composer;
            LazyLayoutItemContentFactoryKt.m688SkippableItemJVlU9Rs(lazyLayoutItemProvider, StableValue.m706constructorimpl(lazyLayoutItemContentFactory.saveableStateHolder), i11, StableValue.m706constructorimpl(cachedItemContent.getKey()), composer2, 0);
        } else {
            composer2 = composer;
            composer2.deactivateToEndGroup(zChanged);
        }
        composer2.endReusableGroup();
        EffectsKt.DisposableEffect(this.this$1.getKey(), new AnonymousClass2(this.this$1), composer2, 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
