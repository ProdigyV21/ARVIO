package androidx.compose.ui.focus;

import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/focus/FocusOrderModifier;", "Landroidx/compose/ui/Modifier$Element;", "Landroidx/compose/ui/focus/FocusOrder;", "focusOrder", "Lx6/t0;", "populateFocusOrder", "(Landroidx/compose/ui/focus/FocusOrder;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@x6.e
public interface FocusOrderModifier extends Modifier.Element {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static boolean all(FocusOrderModifier focusOrderModifier, l<? super Modifier.Element, Boolean> lVar) {
            return androidx.compose.ui.b.a(focusOrderModifier, lVar);
        }

        @Deprecated
        public static boolean any(FocusOrderModifier focusOrderModifier, l<? super Modifier.Element, Boolean> lVar) {
            return androidx.compose.ui.b.b(focusOrderModifier, lVar);
        }

        @Deprecated
        public static <R> R foldIn(FocusOrderModifier focusOrderModifier, R r4, p<? super R, ? super Modifier.Element, ? extends R> pVar) {
            return (R) androidx.compose.ui.b.c(focusOrderModifier, r4, pVar);
        }

        @Deprecated
        public static <R> R foldOut(FocusOrderModifier focusOrderModifier, R r4, p<? super Modifier.Element, ? super R, ? extends R> pVar) {
            return (R) androidx.compose.ui.b.d(focusOrderModifier, r4, pVar);
        }

        @Deprecated
        public static Modifier then(FocusOrderModifier focusOrderModifier, Modifier modifier) {
            return androidx.compose.ui.a.a(focusOrderModifier, modifier);
        }
    }

    void populateFocusOrder(FocusOrder focusOrder);
}
