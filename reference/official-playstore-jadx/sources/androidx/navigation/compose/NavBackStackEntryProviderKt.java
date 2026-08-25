package androidx.navigation.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.d1;
import androidx.lifecycle.j1;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a.\u0010\u0007\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a&\u0010\t\u001a\u00020\u0004*\u00020\u00012\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0003¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/navigation/i;", "Landroidx/compose/runtime/saveable/SaveableStateHolder;", "saveableStateHolder", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "LocalOwnersProvider", "(Landroidx/navigation/i;Landroidx/compose/runtime/saveable/SaveableStateHolder;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "SaveableStateProvider", "(Landroidx/compose/runtime/saveable/SaveableStateHolder;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "navigation-compose_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NavBackStackEntryProviderKt {

    /* JADX INFO: renamed from: androidx.navigation.compose.NavBackStackEntryProviderKt$LocalOwnersProvider$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements r7.p<Composer, Integer, t0> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ SaveableStateHolder f4547i;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final /* synthetic */ r7.p f4548l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final /* synthetic */ int f4549m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SaveableStateHolder saveableStateHolder, r7.p pVar, int i10) {
            super(2);
            this.f4547i = saveableStateHolder;
            this.f4548l = pVar;
            this.f4549m = i10;
        }

        @Override // r7.p
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 11) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-52928304, i10, -1, "androidx.navigation.compose.LocalOwnersProvider.<anonymous> (NavBackStackEntryProvider.kt:51)");
            }
            NavBackStackEntryProviderKt.SaveableStateProvider(this.f4547i, this.f4548l, composer, ((this.f4549m >> 3) & 112) | 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static final void LocalOwnersProvider(androidx.navigation.i iVar, SaveableStateHolder saveableStateHolder, r7.p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-1579360880);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1579360880, i10, -1, "androidx.navigation.compose.LocalOwnersProvider (NavBackStackEntryProvider.kt:45)");
        }
        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{LocalViewModelStoreOwner.f3302b.provides(iVar), AndroidCompositionLocals_androidKt.getLocalLifecycleOwner().provides(iVar), AndroidCompositionLocals_androidKt.getLocalSavedStateRegistryOwner().provides(iVar)}, ComposableLambdaKt.composableLambda(composerStartRestartGroup, -52928304, true, new AnonymousClass1(saveableStateHolder, pVar, i10)), composerStartRestartGroup, 56);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new q(iVar, saveableStateHolder, pVar, i10, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SaveableStateProvider(SaveableStateHolder saveableStateHolder, r7.p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1211832233);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1211832233, i10, -1, "androidx.navigation.compose.SaveableStateProvider (NavBackStackEntryProvider.kt:56)");
        }
        composerStartRestartGroup.startReplaceableGroup(1729797275);
        j1 current = LocalViewModelStoreOwner.f3301a.getCurrent(composerStartRestartGroup, 6);
        if (current == null) {
            throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
        }
        d1 d1VarViewModel = ViewModelKt.viewModel(BackStackEntryIdViewModel.class, current, null, null, current instanceof androidx.lifecycle.k ? ((androidx.lifecycle.k) current).getDefaultViewModelCreationExtras() : o1.a.f20721b, composerStartRestartGroup, 36936, 0);
        composerStartRestartGroup.endReplaceableGroup();
        BackStackEntryIdViewModel backStackEntryIdViewModel = (BackStackEntryIdViewModel) d1VarViewModel;
        backStackEntryIdViewModel.setSaveableStateHolderRef(new WeakReference<>(saveableStateHolder));
        saveableStateHolder.SaveableStateProvider(backStackEntryIdViewModel.getId(), pVar, composerStartRestartGroup, (i10 & 112) | 520);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new n(i10, 1, saveableStateHolder, pVar));
    }
}
