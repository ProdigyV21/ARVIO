package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/layout/OnPlacedModifier;", "Landroidx/compose/ui/Modifier$Element;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "coordinates", "Lx6/t0;", "onPlaced", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface OnPlacedModifier extends Modifier.Element {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static boolean all(OnPlacedModifier onPlacedModifier, r7.l<? super Modifier.Element, Boolean> lVar) {
            return androidx.compose.ui.b.a(onPlacedModifier, lVar);
        }

        @Deprecated
        public static boolean any(OnPlacedModifier onPlacedModifier, r7.l<? super Modifier.Element, Boolean> lVar) {
            return androidx.compose.ui.b.b(onPlacedModifier, lVar);
        }

        @Deprecated
        public static <R> R foldIn(OnPlacedModifier onPlacedModifier, R r4, p<? super R, ? super Modifier.Element, ? extends R> pVar) {
            return (R) androidx.compose.ui.b.c(onPlacedModifier, r4, pVar);
        }

        @Deprecated
        public static <R> R foldOut(OnPlacedModifier onPlacedModifier, R r4, p<? super Modifier.Element, ? super R, ? extends R> pVar) {
            return (R) androidx.compose.ui.b.d(onPlacedModifier, r4, pVar);
        }

        @Deprecated
        public static Modifier then(OnPlacedModifier onPlacedModifier, Modifier modifier) {
            return androidx.compose.ui.a.a(onPlacedModifier, modifier);
        }
    }

    void onPlaced(LayoutCoordinates coordinates);
}
