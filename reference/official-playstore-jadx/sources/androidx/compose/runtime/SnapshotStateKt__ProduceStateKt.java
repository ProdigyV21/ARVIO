package androidx.compose.runtime;

import androidx.compose.runtime.Composer;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.Arrays;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u0003\u001aR\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002-\u0010\b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0002¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a\\\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u00062-\u0010\b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0002¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0004\b\n\u0010\r\u001af\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u00062-\u0010\b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0002¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0004\b\n\u0010\u000f\u001ap\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062-\u0010\b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0002¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0004\b\n\u0010\u0011\u001aj\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002\u0016\u0010\u0013\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u0012\"\u0004\u0018\u00010\u00062-\u0010\b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0002¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0004\b\n\u0010\u0014¨\u0006\u0015"}, d2 = {"T", "initialValue", "Lkotlin/Function2;", "Landroidx/compose/runtime/ProduceStateScope;", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "producer", "Landroidx/compose/runtime/State;", "produceState", "(Ljava/lang/Object;Lr7/p;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "key1", "(Ljava/lang/Object;Ljava/lang/Object;Lr7/p;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "key2", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lr7/p;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "key3", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lr7/p;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "", UserMetadata.KEYDATA_FILENAME, "(Ljava/lang/Object;[Ljava/lang/Object;Lr7/p;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "runtime_release"}, k = 5, mv = {1, 8, 0}, xi = 48, xs = "androidx/compose/runtime/SnapshotStateKt")
final /* synthetic */ class SnapshotStateKt__ProduceStateKt {
    public static final <T> State<T> produceState(T t2, p<? super ProduceStateScope<T>, ? super d7.d<? super t0>, ? extends Object> pVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(10454275, i10, -1, "androidx.compose.runtime.produceState (ProduceState.kt:79)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t2, null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        MutableState mutableState = (MutableState) objRememberedValue;
        boolean zChangedInstance = composer.changedInstance(pVar);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new SnapshotStateKt__ProduceStateKt$produceState$1$1(pVar, mutableState, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.LaunchedEffect(t0.f22605a, (p<? super k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue2, composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return mutableState;
    }

    public static final <T> State<T> produceState(T t2, Object obj, p<? super ProduceStateScope<T>, ? super d7.d<? super t0>, ? extends Object> pVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1928268701, i10, -1, "androidx.compose.runtime.produceState (ProduceState.kt:112)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t2, null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        MutableState mutableState = (MutableState) objRememberedValue;
        boolean zChangedInstance = composer.changedInstance(pVar);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new SnapshotStateKt__ProduceStateKt$produceState$2$1(pVar, mutableState, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.LaunchedEffect(obj, (p<? super k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue2, composer, (i10 >> 3) & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return mutableState;
    }

    public static final <T> State<T> produceState(T t2, Object obj, Object obj2, p<? super ProduceStateScope<T>, ? super d7.d<? super t0>, ? extends Object> pVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1703169085, i10, -1, "androidx.compose.runtime.produceState (ProduceState.kt:146)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t2, null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        MutableState mutableState = (MutableState) objRememberedValue;
        boolean zChangedInstance = composer.changedInstance(pVar);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new SnapshotStateKt__ProduceStateKt$produceState$3$1(pVar, mutableState, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.LaunchedEffect(obj, obj2, (p) objRememberedValue2, composer, (i10 >> 3) & 126);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return mutableState;
    }

    public static final <T> State<T> produceState(T t2, Object obj, Object obj2, Object obj3, p<? super ProduceStateScope<T>, ? super d7.d<? super t0>, ? extends Object> pVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1807205155, i10, -1, "androidx.compose.runtime.produceState (ProduceState.kt:181)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t2, null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        MutableState mutableState = (MutableState) objRememberedValue;
        boolean zChangedInstance = composer.changedInstance(pVar);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new SnapshotStateKt__ProduceStateKt$produceState$4$1(pVar, mutableState, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.LaunchedEffect(obj, obj2, obj3, (p) objRememberedValue2, composer, (i10 >> 3) & AnalyticsListener.EVENT_DRM_SESSION_ACQUIRED);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return mutableState;
    }

    public static final <T> State<T> produceState(T t2, Object[] objArr, p<? super ProduceStateScope<T>, ? super d7.d<? super t0>, ? extends Object> pVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(490154582, i10, -1, "androidx.compose.runtime.produceState (ProduceState.kt:214)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t2, null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        MutableState mutableState = (MutableState) objRememberedValue;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        boolean zChangedInstance = composer.changedInstance(pVar);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new SnapshotStateKt__ProduceStateKt$produceState$5$1(pVar, mutableState, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.LaunchedEffect(objArrCopyOf, (p<? super k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue2, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return mutableState;
    }
}
