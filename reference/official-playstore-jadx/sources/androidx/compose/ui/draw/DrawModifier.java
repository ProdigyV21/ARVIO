package androidx.compose.ui.draw;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import kotlin.Metadata;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0013\u0010\u0004\u001a\u00020\u0003*\u00020\u0002H&¢\u0006\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/draw/DrawModifier;", "Landroidx/compose/ui/Modifier$Element;", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "Lx6/t0;", "draw", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface DrawModifier extends Modifier.Element {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static boolean all(DrawModifier drawModifier, l<? super Modifier.Element, Boolean> lVar) {
            return androidx.compose.ui.b.a(drawModifier, lVar);
        }

        @Deprecated
        public static boolean any(DrawModifier drawModifier, l<? super Modifier.Element, Boolean> lVar) {
            return androidx.compose.ui.b.b(drawModifier, lVar);
        }

        @Deprecated
        public static <R> R foldIn(DrawModifier drawModifier, R r4, p<? super R, ? super Modifier.Element, ? extends R> pVar) {
            return (R) androidx.compose.ui.b.c(drawModifier, r4, pVar);
        }

        @Deprecated
        public static <R> R foldOut(DrawModifier drawModifier, R r4, p<? super Modifier.Element, ? super R, ? extends R> pVar) {
            return (R) androidx.compose.ui.b.d(drawModifier, r4, pVar);
        }

        @Deprecated
        public static Modifier then(DrawModifier drawModifier, Modifier modifier) {
            return androidx.compose.ui.a.a(drawModifier, modifier);
        }
    }

    void draw(ContentDrawScope contentDrawScope);
}
