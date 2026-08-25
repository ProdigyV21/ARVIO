package androidx.activity.compose;

import androidx.activity.c;
import androidx.activity.l0;
import androidx.activity.n0;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.y;
import androidx.navigation.compose.h;
import com.arflix.tv.data.repository.g;
import d7.d;
import d7.k;
import e.i;
import io.ktor.http.ContentDisposition;
import ka.k0;
import kotlin.Metadata;
import na.j;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u001a\\\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u0001\u001a\u00020\u00002A\u0010\f\u001a=\b\u0001\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u000f\u0012\r\u0012\t\u0012\u00070\n¢\u0006\u0002\b\u00050\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0002H\u0007¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u0010²\u0006G\u0010\u000f\u001a=\b\u0001\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u000f\u0012\r\u0012\t\u0012\u00070\n¢\u0006\u0002\b\u00050\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00028\nX\u008a\u0084\u0002"}, d2 = {"", "enabled", "Lkotlin/Function2;", "Lna/j;", "Landroidx/activity/c;", "Lq7/o;", "Lx6/y;", ContentDisposition.Parameters.Name, "progress", "Ld7/d;", "Lx6/t0;", "", "onBack", "PredictiveBackHandler", "(ZLr7/p;Landroidx/compose/runtime/Composer;II)V", "currentOnBack", "activity-compose_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PredictiveBackHandlerKt {
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static final void PredictiveBackHandler(boolean z, p<j<c>, ? super d<t0>, ? extends Object> pVar, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(-642000585);
        int i13 = i11 & 1;
        if (i13 != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i11 & 2) != 0) {
            i12 |= 48;
        } else if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar) ? 32 : 16;
        }
        if ((i12 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i13 != 0) {
                z = true;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-642000585, i12, -1, "androidx.activity.compose.PredictiveBackHandler (PredictiveBackHandler.kt:76)");
            }
            State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(pVar, composerStartRestartGroup, (i12 >> 3) & 14);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composerStartRestartGroup), composerStartRestartGroup);
            }
            k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            Object obj = objRememberedValue2;
            if (objRememberedValue2 == companion.getEmpty()) {
                p pVar2 = (p) stateRememberUpdatedState.getValue();
                i iVar = new i(z);
                iVar.f14951d = coroutineScope;
                iVar.f14952e = pVar2;
                composerStartRestartGroup.updateRememberedValue(iVar);
                obj = iVar;
            }
            i iVar2 = (i) obj;
            boolean zChanged = composerStartRestartGroup.changed((p) stateRememberUpdatedState.getValue()) | composerStartRestartGroup.changed(coroutineScope);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue3 == companion.getEmpty()) {
                iVar2.f14952e = (p) stateRememberUpdatedState.getValue();
                iVar2.f14951d = coroutineScope;
                composerStartRestartGroup.updateRememberedValue(t0.f22605a);
            }
            Boolean boolValueOf = Boolean.valueOf(z);
            int i14 = i12 & 14;
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(iVar2) | (i14 == 4);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = new e.j(iVar2, z, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            EffectsKt.LaunchedEffect(boolValueOf, (p<? super k0, ? super d<? super t0>, ? extends Object>) objRememberedValue4, composerStartRestartGroup, i14);
            n0 current = LocalOnBackPressedDispatcherOwner.f929a.getCurrent(composerStartRestartGroup, 6);
            if (current == null) {
                throw new IllegalStateException("No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner");
            }
            l0 onBackPressedDispatcher = current.getOnBackPressedDispatcher();
            y yVar = (y) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(onBackPressedDispatcher) | composerStartRestartGroup.changedInstance(yVar) | composerStartRestartGroup.changedInstance(iVar2);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = new h(onBackPressedDispatcher, yVar, iVar2, 6);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            EffectsKt.DisposableEffect(yVar, onBackPressedDispatcher, (l) objRememberedValue5, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        boolean z5 = z;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new e.d(z5, pVar, i10, i11, 1));
        }
    }
}
