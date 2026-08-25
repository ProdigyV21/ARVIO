package androidx.compose.ui.input.pointer;

import androidx.compose.ui.Modifier;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import d7.d;
import kotlin.Metadata;
import kotlin.collections.z;
import r7.p;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a<\u0010\b\u001a\u00020\u0000*\u00020\u00002'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0004\b\b\u0010\t\u001aD\u0010\b\u001a\u00020\u0000*\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u00052'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001¢\u0006\u0002\b\u0006¢\u0006\u0004\b\b\u0010\u000b\u001aN\u0010\b\u001a\u00020\u0000*\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u00052'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001¢\u0006\u0002\b\u0006¢\u0006\u0004\b\b\u0010\r\u001aR\u0010\b\u001a\u00020\u0000*\u00020\u00002\u0016\u0010\u000f\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u000e\"\u0004\u0018\u00010\u00052'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001¢\u0006\u0002\b\u0006¢\u0006\u0004\b\b\u0010\u0010\u001a6\u0010\u0013\u001a\u00020\u00122'\u0010\u0011\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001¢\u0006\u0002\b\u0006¢\u0006\u0004\b\u0013\u0010\u0014\"\u001a\u0010\u0016\u001a\u00020\u00158\u0002X\u0082T¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u0012\u0004\b\u0018\u0010\u0019\"\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Landroidx/compose/ui/Modifier;", "Lkotlin/Function2;", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "block", "pointerInput", "(Landroidx/compose/ui/Modifier;Lr7/p;)Landroidx/compose/ui/Modifier;", "key1", "(Landroidx/compose/ui/Modifier;Ljava/lang/Object;Lr7/p;)Landroidx/compose/ui/Modifier;", "key2", "(Landroidx/compose/ui/Modifier;Ljava/lang/Object;Ljava/lang/Object;Lr7/p;)Landroidx/compose/ui/Modifier;", "", UserMetadata.KEYDATA_FILENAME, "(Landroidx/compose/ui/Modifier;[Ljava/lang/Object;Lr7/p;)Landroidx/compose/ui/Modifier;", "pointerInputHandler", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "SuspendingPointerInputModifierNode", "(Lr7/p;)Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "", "PointerInputModifierNoParamError", "Ljava/lang/String;", "getPointerInputModifierNoParamError$annotations", "()V", "Landroidx/compose/ui/input/pointer/PointerEvent;", "EmptyPointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SuspendingPointerInputFilterKt {
    private static final PointerEvent EmptyPointerEvent = new PointerEvent(z.f19728i);
    private static final String PointerInputModifierNoParamError = "Modifier.pointerInput must provide one or more 'key' parameters that define the identity of the modifier and determine when its previous input processing coroutine should be cancelled and a new effect launched for the new key.";

    public static final SuspendingPointerInputModifierNode SuspendingPointerInputModifierNode(p<? super PointerInputScope, ? super d<? super t0>, ? extends Object> pVar) {
        return new SuspendingPointerInputModifierNodeImpl(pVar);
    }

    private static /* synthetic */ void getPointerInputModifierNoParamError$annotations() {
    }

    @e
    public static final Modifier pointerInput(Modifier modifier, p<? super PointerInputScope, ? super d<? super t0>, ? extends Object> pVar) {
        throw new IllegalStateException(PointerInputModifierNoParamError);
    }

    public static final Modifier pointerInput(Modifier modifier, Object obj, p<? super PointerInputScope, ? super d<? super t0>, ? extends Object> pVar) {
        return modifier.then(new SuspendPointerInputElement(obj, null, null, pVar, 6, null));
    }

    public static final Modifier pointerInput(Modifier modifier, Object obj, Object obj2, p<? super PointerInputScope, ? super d<? super t0>, ? extends Object> pVar) {
        return modifier.then(new SuspendPointerInputElement(obj, obj2, null, pVar, 4, null));
    }

    public static final Modifier pointerInput(Modifier modifier, Object[] objArr, p<? super PointerInputScope, ? super d<? super t0>, ? extends Object> pVar) {
        return modifier.then(new SuspendPointerInputElement(null, null, objArr, pVar, 3, null));
    }
}
