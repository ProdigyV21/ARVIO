package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a7\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a'\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\b\u0010\t\u001a2\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010\u00002\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u00000\n¢\u0006\u0002\b\f¢\u0006\u0004\b\u000e\u0010\u000f\u001a>\u0010\u0016\u001a\u00020\u00132\u001a\u0010\u0012\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010\"\u0006\u0012\u0002\b\u00030\u00112\u0011\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\u00130\u0003¢\u0006\u0002\b\u0014H\u0007¢\u0006\u0004\b\u0016\u0010\u0017\u001a.\u0010\u0016\u001a\u00020\u00132\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u00112\u0011\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\u00130\u0003¢\u0006\u0002\b\u0014H\u0007¢\u0006\u0004\b\u0016\u0010\u0019\u001a*\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001a2\u0011\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\u00130\u0003¢\u0006\u0002\b\u0014H\u0007¢\u0006\u0004\b\u0016\u0010\u001c¨\u0006\u001d"}, d2 = {"T", "Landroidx/compose/runtime/SnapshotMutationPolicy;", "policy", "Lkotlin/Function0;", "defaultFactory", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "compositionLocalOf", "(Landroidx/compose/runtime/SnapshotMutationPolicy;Lr7/a;)Landroidx/compose/runtime/ProvidableCompositionLocal;", "staticCompositionLocalOf", "(Lr7/a;)Landroidx/compose/runtime/ProvidableCompositionLocal;", "Lkotlin/Function1;", "Landroidx/compose/runtime/CompositionLocalAccessorScope;", "Lx6/n;", "defaultComputation", "compositionLocalWithComputedDefaultOf", "(Lr7/l;)Landroidx/compose/runtime/ProvidableCompositionLocal;", "", "Landroidx/compose/runtime/ProvidedValue;", "values", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "CompositionLocalProvider", "([Landroidx/compose/runtime/ProvidedValue;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "value", "(Landroidx/compose/runtime/ProvidedValue;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/runtime/CompositionLocalContext;", "context", "(Landroidx/compose/runtime/CompositionLocalContext;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CompositionLocalKt {

    /* JADX INFO: renamed from: androidx.compose.runtime.CompositionLocalKt$CompositionLocalProvider$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ ProvidedValue<?>[] $values;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(ProvidedValue<?>[] providedValueArr, p<? super Composer, ? super Integer, t0> pVar, int i10) {
            super(2);
            this.$values = providedValueArr;
            this.$content = pVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            ProvidedValue<?>[] providedValueArr = this.$values;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) Arrays.copyOf(providedValueArr, providedValueArr.length), this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.CompositionLocalKt$CompositionLocalProvider$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ ProvidedValue<?> $value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(ProvidedValue<?> providedValue, p<? super Composer, ? super Integer, t0> pVar, int i10) {
            super(2);
            this.$value = providedValue;
            this.$content = pVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            CompositionLocalKt.CompositionLocalProvider(this.$value, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.CompositionLocalKt$CompositionLocalProvider$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ CompositionLocalContext $context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(CompositionLocalContext compositionLocalContext, p<? super Composer, ? super Integer, t0> pVar, int i10) {
            super(2);
            this.$context = compositionLocalContext;
            this.$content = pVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            CompositionLocalKt.CompositionLocalProvider(this.$context, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    public static final void CompositionLocalProvider(ProvidedValue<?>[] providedValueArr, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-1390796515);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1390796515, i10, -1, "androidx.compose.runtime.CompositionLocalProvider (CompositionLocal.kt:377)");
        }
        composerStartRestartGroup.startProviders(providedValueArr);
        pVar.invoke(composerStartRestartGroup, Integer.valueOf((i10 >> 3) & 14));
        composerStartRestartGroup.endProviders();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass1(providedValueArr, pVar, i10));
        }
    }

    public static final <T> ProvidableCompositionLocal<T> compositionLocalOf(SnapshotMutationPolicy<T> snapshotMutationPolicy, r7.a<? extends T> aVar) {
        return new DynamicProvidableCompositionLocal(snapshotMutationPolicy, aVar);
    }

    public static /* synthetic */ ProvidableCompositionLocal compositionLocalOf$default(SnapshotMutationPolicy snapshotMutationPolicy, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            snapshotMutationPolicy = SnapshotStateKt.structuralEqualityPolicy();
        }
        return compositionLocalOf(snapshotMutationPolicy, aVar);
    }

    public static final <T> ProvidableCompositionLocal<T> compositionLocalWithComputedDefaultOf(r7.l<? super CompositionLocalAccessorScope, ? extends T> lVar) {
        return new ComputedProvidableCompositionLocal(lVar);
    }

    public static final <T> ProvidableCompositionLocal<T> staticCompositionLocalOf(r7.a<? extends T> aVar) {
        return new StaticProvidableCompositionLocal(aVar);
    }

    public static final void CompositionLocalProvider(ProvidedValue<?> providedValue, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-1350970552);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1350970552, i10, -1, "androidx.compose.runtime.CompositionLocalProvider (CompositionLocal.kt:398)");
        }
        composerStartRestartGroup.startProvider(providedValue);
        pVar.invoke(composerStartRestartGroup, Integer.valueOf((i10 >> 3) & 14));
        composerStartRestartGroup.endProvider();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass2(providedValue, pVar, i10));
        }
    }

    public static final void CompositionLocalProvider(CompositionLocalContext compositionLocalContext, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1853897736);
        int i11 = (i10 & 6) == 0 ? (composerStartRestartGroup.changed(compositionLocalContext) ? 4 : 2) | i10 : i10;
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1853897736, i11, -1, "androidx.compose.runtime.CompositionLocalProvider (CompositionLocal.kt:417)");
            }
            PersistentCompositionLocalMap compositionLocals$runtime_release = compositionLocalContext.getCompositionLocals();
            ArrayList arrayList = new ArrayList(compositionLocals$runtime_release.size());
            for (Map.Entry<CompositionLocal<Object>, ValueHolder<Object>> entry : compositionLocals$runtime_release.entrySet()) {
                arrayList.add(entry.getValue().toProvided(entry.getKey()));
            }
            ProvidedValue[] providedValueArr = (ProvidedValue[]) arrayList.toArray(new ProvidedValue[0]);
            CompositionLocalProvider((ProvidedValue<?>[]) Arrays.copyOf(providedValueArr, providedValueArr.length), pVar, composerStartRestartGroup, i11 & 112);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass4(compositionLocalContext, pVar, i10));
        }
    }
}
