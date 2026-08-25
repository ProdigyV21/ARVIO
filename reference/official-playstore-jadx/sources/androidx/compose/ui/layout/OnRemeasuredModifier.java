package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006ø\u0001\u0001\u0082\u0002\r\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/layout/OnRemeasuredModifier;", "Landroidx/compose/ui/Modifier$Element;", "Landroidx/compose/ui/unit/IntSize;", ContentDisposition.Parameters.Size, "Lx6/t0;", "onRemeasured-ozmzZPI", "(J)V", "onRemeasured", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface OnRemeasuredModifier extends Modifier.Element {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static boolean all(OnRemeasuredModifier onRemeasuredModifier, r7.l<? super Modifier.Element, Boolean> lVar) {
            return androidx.compose.ui.b.a(onRemeasuredModifier, lVar);
        }

        @Deprecated
        public static boolean any(OnRemeasuredModifier onRemeasuredModifier, r7.l<? super Modifier.Element, Boolean> lVar) {
            return androidx.compose.ui.b.b(onRemeasuredModifier, lVar);
        }

        @Deprecated
        public static <R> R foldIn(OnRemeasuredModifier onRemeasuredModifier, R r4, p<? super R, ? super Modifier.Element, ? extends R> pVar) {
            return (R) androidx.compose.ui.b.c(onRemeasuredModifier, r4, pVar);
        }

        @Deprecated
        public static <R> R foldOut(OnRemeasuredModifier onRemeasuredModifier, R r4, p<? super Modifier.Element, ? super R, ? extends R> pVar) {
            return (R) androidx.compose.ui.b.d(onRemeasuredModifier, r4, pVar);
        }

        @Deprecated
        public static Modifier then(OnRemeasuredModifier onRemeasuredModifier, Modifier modifier) {
            return androidx.compose.ui.a.a(onRemeasuredModifier, modifier);
        }
    }

    /* JADX INFO: renamed from: onRemeasured-ozmzZPI, reason: not valid java name */
    void mo4661onRemeasuredozmzZPI(long size);
}
