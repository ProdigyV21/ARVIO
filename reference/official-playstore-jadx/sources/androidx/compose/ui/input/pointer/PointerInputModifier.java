package androidx.compose.ui.input.pointer;

import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputModifier;", "Landroidx/compose/ui/Modifier$Element;", "pointerInputFilter", "Landroidx/compose/ui/input/pointer/PointerInputFilter;", "getPointerInputFilter", "()Landroidx/compose/ui/input/pointer/PointerInputFilter;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface PointerInputModifier extends Modifier.Element {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static boolean all(PointerInputModifier pointerInputModifier, l<? super Modifier.Element, Boolean> lVar) {
            return androidx.compose.ui.b.a(pointerInputModifier, lVar);
        }

        @Deprecated
        public static boolean any(PointerInputModifier pointerInputModifier, l<? super Modifier.Element, Boolean> lVar) {
            return androidx.compose.ui.b.b(pointerInputModifier, lVar);
        }

        @Deprecated
        public static <R> R foldIn(PointerInputModifier pointerInputModifier, R r4, p<? super R, ? super Modifier.Element, ? extends R> pVar) {
            return (R) androidx.compose.ui.b.c(pointerInputModifier, r4, pVar);
        }

        @Deprecated
        public static <R> R foldOut(PointerInputModifier pointerInputModifier, R r4, p<? super Modifier.Element, ? super R, ? extends R> pVar) {
            return (R) androidx.compose.ui.b.d(pointerInputModifier, r4, pVar);
        }

        @Deprecated
        public static Modifier then(PointerInputModifier pointerInputModifier, Modifier modifier) {
            return androidx.compose.ui.a.a(pointerInputModifier, modifier);
        }
    }

    PointerInputFilter getPointerInputFilter();
}
