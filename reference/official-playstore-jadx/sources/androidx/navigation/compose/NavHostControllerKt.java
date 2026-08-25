package androidx.navigation.compose;

import a8.g0;
import android.content.Context;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.navigation.a1;
import androidx.navigation.b1;
import androidx.navigation.l0;
import java.util.Arrays;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001b\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001*\u00020\u0000H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\u001a3\u0010\n\u001a\u00020\t2\"\u0010\b\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u00060\u0005\"\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006H\u0007¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/navigation/s;", "Landroidx/compose/runtime/State;", "Landroidx/navigation/i;", "currentBackStackEntryAsState", "(Landroidx/navigation/s;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "", "Landroidx/navigation/a1;", "Landroidx/navigation/d0;", "navigators", "Landroidx/navigation/l0;", "rememberNavController", "([Landroidx/navigation/a1;Landroidx/compose/runtime/Composer;I)Landroidx/navigation/l0;", "navigation-compose_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NavHostControllerKt {
    public static final l0 a(Context context) {
        l0 l0Var = new l0(context);
        b1 b1Var = l0Var.f4780v;
        b1Var.a(new d(b1Var));
        l0Var.f4780v.a(new f());
        l0Var.f4780v.a(new p());
        return l0Var;
    }

    public static final State<androidx.navigation.i> currentBackStackEntryAsState(androidx.navigation.s sVar, Composer composer, int i10) {
        composer.startReplaceableGroup(-120375203);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-120375203, i10, -1, "androidx.navigation.compose.currentBackStackEntryAsState (NavHostController.kt:41)");
        }
        State<androidx.navigation.i> stateCollectAsState = SnapshotStateKt.collectAsState(sVar.D, null, null, composer, 56, 2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateCollectAsState;
    }

    public static final l0 rememberNavController(a1[] a1VarArr, Composer composer, int i10) {
        composer.startReplaceableGroup(-312215566);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-312215566, i10, -1, "androidx.navigation.compose.rememberNavController (NavHostController.kt:57)");
        }
        Context context = (Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext());
        l0 l0Var = (l0) RememberSaveableKt.m3084rememberSaveable(Arrays.copyOf(a1VarArr, a1VarArr.length), SaverKt.Saver(r.f4600i, new ab.h(context, 3)), (String) null, (r7.a) new g0(context, 8), composer, 72, 4);
        for (a1 a1Var : a1VarArr) {
            l0Var.f4780v.a(a1Var);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return l0Var;
    }
}
