package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Size;
import d7.d;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class a {
    public static long a(AwaitPointerEventScope awaitPointerEventScope) {
        return Size.INSTANCE.m3314getZeroNHjbRc();
    }

    public static Object b(AwaitPointerEventScope awaitPointerEventScope, long j10, p pVar, d dVar) {
        return u(awaitPointerEventScope, j10, pVar, dVar);
    }

    public static Object c(AwaitPointerEventScope awaitPointerEventScope, long j10, p pVar, d dVar) {
        return v(awaitPointerEventScope, j10, pVar, dVar);
    }

    public static /* synthetic */ Object t(AwaitPointerEventScope awaitPointerEventScope, PointerEventPass pointerEventPass, d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: awaitPointerEvent");
        }
        if ((i10 & 1) != 0) {
            pointerEventPass = PointerEventPass.Main;
        }
        return awaitPointerEventScope.awaitPointerEvent(pointerEventPass, dVar);
    }

    public static /* synthetic */ Object u(AwaitPointerEventScope awaitPointerEventScope, long j10, p pVar, d dVar) {
        return pVar.invoke(awaitPointerEventScope, dVar);
    }

    public static /* synthetic */ Object v(AwaitPointerEventScope awaitPointerEventScope, long j10, p pVar, d dVar) {
        return pVar.invoke(awaitPointerEventScope, dVar);
    }
}
