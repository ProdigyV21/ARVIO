package androidx.activity.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.navigation.o;
import e.c;
import e.g;
import g.a;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aO\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u00050\u0004H\u0007¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"I", "O", "Lg/a;", "contract", "Lkotlin/Function1;", "Lx6/t0;", "onResult", "Le/g;", "rememberLauncherForActivityResult", "(Lg/a;Lr7/l;Landroidx/compose/runtime/Composer;I)Le/g;", "activity-compose_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ActivityResultRegistryKt {
    public static final <I, O> g rememberLauncherForActivityResult(a aVar, l<? super O, t0> lVar, Composer composer, int i10) {
        Object obj;
        String str;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1408504823, i10, -1, "androidx.activity.compose.rememberLauncherForActivityResult (ActivityResultRegistry.kt:82)");
        }
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(aVar, composer, i10 & 14);
        Object objRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(lVar, composer, (i10 >> 3) & 14);
        String str2 = (String) RememberSaveableKt.m3084rememberSaveable(new Object[0], (Saver) null, (String) null, (r7.a) c.f14935l, composer, 3072, 6);
        androidx.activity.result.l current = LocalActivityResultRegistryOwner.f925a.getCurrent(composer, 6);
        if (current == null) {
            throw new IllegalStateException("No ActivityResultRegistryOwner was provided via LocalActivityResultRegistryOwner");
        }
        Object activityResultRegistry = current.getActivityResultRegistry();
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new e.a();
            composer.updateRememberedValue(objRememberedValue);
        }
        e.a aVar2 = (e.a) objRememberedValue;
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new g(aVar2, stateRememberUpdatedState);
            composer.updateRememberedValue(objRememberedValue2);
        }
        g gVar = (g) objRememberedValue2;
        boolean zChangedInstance = composer.changedInstance(aVar2) | composer.changedInstance(activityResultRegistry) | composer.changed(str2) | composer.changedInstance(aVar) | composer.changed(objRememberUpdatedState);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue3 == companion.getEmpty()) {
            obj = activityResultRegistry;
            Object oVar = new o(aVar2, obj, str2, aVar, objRememberUpdatedState, 1);
            str = str2;
            composer.updateRememberedValue(oVar);
            objRememberedValue3 = oVar;
        } else {
            str = str2;
            obj = activityResultRegistry;
        }
        Object obj2 = obj;
        EffectsKt.DisposableEffect(obj2, str, aVar, (l) objRememberedValue3, composer, (i10 << 6) & 896);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return gVar;
    }
}
