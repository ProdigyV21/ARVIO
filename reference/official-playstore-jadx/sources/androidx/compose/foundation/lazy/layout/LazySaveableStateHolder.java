package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import androidx.compose.runtime.saveable.SaveableStateRegistryKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010#\n\u0002\b\u0004\b\u0002\u0018\u0000 .2\u00020\u00012\u00020\u0002:\u0001.B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005B1\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001\u0012\u001c\u0010\u000b\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t\u0018\u00010\u0007¢\u0006\u0004\b\u0004\u0010\fJ\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\nH\u0096\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0011\u001a\u00020\bH\u0096\u0001¢\u0006\u0004\b\u0012\u0010\u0013J(\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\b2\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0014H\u0096\u0001¢\u0006\u0004\b\u0017\u0010\u0018J#\u0010\u0019\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t0\u0007H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ*\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u0011\u001a\u00020\n2\u0011\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\u001b0\u0014¢\u0006\u0002\b\u001cH\u0017¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010 \u001a\u00020\u001b2\u0006\u0010\u0011\u001a\u00020\nH\u0016¢\u0006\u0004\b \u0010!R\u0014\u0010\u0003\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\"R/\u0010*\u001a\u0004\u0018\u00010\u00022\b\u0010#\u001a\u0004\u0018\u00010\u00028F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00020\n0+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-¨\u0006/"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazySaveableStateHolder;", "Landroidx/compose/runtime/saveable/SaveableStateRegistry;", "Landroidx/compose/runtime/saveable/SaveableStateHolder;", "wrappedRegistry", "<init>", "(Landroidx/compose/runtime/saveable/SaveableStateRegistry;)V", "parentRegistry", "", "", "", "", "restoredValues", "(Landroidx/compose/runtime/saveable/SaveableStateRegistry;Ljava/util/Map;)V", "value", "", "canBeSaved", "(Ljava/lang/Object;)Z", "key", "consumeRestored", "(Ljava/lang/String;)Ljava/lang/Object;", "Lkotlin/Function0;", "valueProvider", "Landroidx/compose/runtime/saveable/SaveableStateRegistry$Entry;", "registerProvider", "(Ljava/lang/String;Lr7/a;)Landroidx/compose/runtime/saveable/SaveableStateRegistry$Entry;", "performSave", "()Ljava/util/Map;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "SaveableStateProvider", "(Ljava/lang/Object;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "removeState", "(Ljava/lang/Object;)V", "Landroidx/compose/runtime/saveable/SaveableStateRegistry;", "<set-?>", "wrappedHolder$delegate", "Landroidx/compose/runtime/MutableState;", "getWrappedHolder", "()Landroidx/compose/runtime/saveable/SaveableStateHolder;", "setWrappedHolder", "(Landroidx/compose/runtime/saveable/SaveableStateHolder;)V", "wrappedHolder", "", "previouslyComposedKeys", "Ljava/util/Set;", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class LazySaveableStateHolder implements SaveableStateRegistry, SaveableStateHolder {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Set<Object> previouslyComposedKeys;

    /* JADX INFO: renamed from: wrappedHolder$delegate, reason: from kotlin metadata */
    private final MutableState wrappedHolder;
    private final SaveableStateRegistry wrappedRegistry;

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazySaveableStateHolder$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(Ljava/lang/Object;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<Object, Boolean> {
        final /* synthetic */ SaveableStateRegistry $parentRegistry;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SaveableStateRegistry saveableStateRegistry) {
            super(1);
            this.$parentRegistry = saveableStateRegistry;
        }

        @Override // r7.l
        public final Boolean invoke(Object obj) {
            SaveableStateRegistry saveableStateRegistry = this.$parentRegistry;
            return Boolean.valueOf(saveableStateRegistry != null ? saveableStateRegistry.canBeSaved(obj) : true);
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b0\u00060\u00042\b\u0010\t\u001a\u0004\u0018\u00010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazySaveableStateHolder$Companion;", "", "()V", "saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/lazy/layout/LazySaveableStateHolder;", "", "", "", "parentRegistry", "Landroidx/compose/runtime/saveable/SaveableStateRegistry;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final Saver<LazySaveableStateHolder, Map<String, List<Object>>> saver(SaveableStateRegistry parentRegistry) {
            return SaverKt.Saver(LazySaveableStateHolder$Companion$saver$1.INSTANCE, new LazySaveableStateHolder$Companion$saver$2(parentRegistry));
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazySaveableStateHolder$SaveableStateProvider$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/DisposableEffectResult;", "Landroidx/compose/runtime/DisposableEffectScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<DisposableEffectScope, DisposableEffectResult> {
        final /* synthetic */ Object $key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Object obj) {
            super(1);
            this.$key = obj;
        }

        @Override // r7.l
        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
            LazySaveableStateHolder.this.previouslyComposedKeys.remove(this.$key);
            final LazySaveableStateHolder lazySaveableStateHolder = LazySaveableStateHolder.this;
            final Object obj = this.$key;
            return new DisposableEffectResult() { // from class: androidx.compose.foundation.lazy.layout.LazySaveableStateHolder$SaveableStateProvider$2$invoke$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                    lazySaveableStateHolder.previouslyComposedKeys.add(obj);
                }
            };
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazySaveableStateHolder$SaveableStateProvider$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ Object $key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(Object obj, p<? super Composer, ? super Integer, t0> pVar, int i10) {
            super(2);
            this.$key = obj;
            this.$content = pVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            LazySaveableStateHolder.this.SaveableStateProvider(this.$key, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    public LazySaveableStateHolder(SaveableStateRegistry saveableStateRegistry) {
        this.wrappedRegistry = saveableStateRegistry;
        this.wrappedHolder = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.previouslyComposedKeys = new LinkedHashSet();
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateHolder
    public void SaveableStateProvider(Object obj, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-697180401);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-697180401, i10, -1, "androidx.compose.foundation.lazy.layout.LazySaveableStateHolder.SaveableStateProvider (LazySaveableStateHolder.kt:82)");
        }
        SaveableStateHolder wrappedHolder = getWrappedHolder();
        if (wrappedHolder == null) {
            throw new IllegalArgumentException("null wrappedHolder");
        }
        wrappedHolder.SaveableStateProvider(obj, pVar, composerStartRestartGroup, (i10 & 112) | 520);
        EffectsKt.DisposableEffect(obj, new AnonymousClass2(obj), composerStartRestartGroup, 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass3(obj, pVar, i10));
        }
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public boolean canBeSaved(Object value) {
        return this.wrappedRegistry.canBeSaved(value);
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public Object consumeRestored(String key) {
        return this.wrappedRegistry.consumeRestored(key);
    }

    public final SaveableStateHolder getWrappedHolder() {
        return (SaveableStateHolder) this.wrappedHolder.getValue();
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public Map<String, List<Object>> performSave() {
        SaveableStateHolder wrappedHolder = getWrappedHolder();
        if (wrappedHolder != null) {
            Iterator<T> it = this.previouslyComposedKeys.iterator();
            while (it.hasNext()) {
                wrappedHolder.removeState(it.next());
            }
        }
        return this.wrappedRegistry.performSave();
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public SaveableStateRegistry.Entry registerProvider(String key, r7.a<? extends Object> valueProvider) {
        return this.wrappedRegistry.registerProvider(key, valueProvider);
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateHolder
    public void removeState(Object key) {
        SaveableStateHolder wrappedHolder = getWrappedHolder();
        if (wrappedHolder == null) {
            throw new IllegalArgumentException("null wrappedHolder");
        }
        wrappedHolder.removeState(key);
    }

    public final void setWrappedHolder(SaveableStateHolder saveableStateHolder) {
        this.wrappedHolder.setValue(saveableStateHolder);
    }

    public LazySaveableStateHolder(SaveableStateRegistry saveableStateRegistry, Map<String, ? extends List<? extends Object>> map) {
        this(SaveableStateRegistryKt.SaveableStateRegistry(map, new AnonymousClass1(saveableStateRegistry)));
    }
}
