package androidx.compose.ui.semantics;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.b;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\u00020\u00038VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsModifier;", "Landroidx/compose/ui/Modifier$Element;", TtmlNode.ATTR_ID, "", "getId$annotations", "()V", "getId", "()I", "semanticsConfiguration", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "getSemanticsConfiguration", "()Landroidx/compose/ui/semantics/SemanticsConfiguration;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface SemanticsModifier extends Modifier.Element {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static boolean all(SemanticsModifier semanticsModifier, l<? super Modifier.Element, Boolean> lVar) {
            return b.a(semanticsModifier, lVar);
        }

        @Deprecated
        public static boolean any(SemanticsModifier semanticsModifier, l<? super Modifier.Element, Boolean> lVar) {
            return b.b(semanticsModifier, lVar);
        }

        @Deprecated
        public static <R> R foldIn(SemanticsModifier semanticsModifier, R r4, p<? super R, ? super Modifier.Element, ? extends R> pVar) {
            return (R) b.c(semanticsModifier, r4, pVar);
        }

        @Deprecated
        public static <R> R foldOut(SemanticsModifier semanticsModifier, R r4, p<? super Modifier.Element, ? super R, ? extends R> pVar) {
            return (R) b.d(semanticsModifier, r4, pVar);
        }

        @Deprecated
        public static int getId(SemanticsModifier semanticsModifier) {
            return a.a(semanticsModifier);
        }

        @e
        public static /* synthetic */ void getId$annotations() {
        }

        @Deprecated
        public static Modifier then(SemanticsModifier semanticsModifier, Modifier modifier) {
            return androidx.compose.ui.a.a(semanticsModifier, modifier);
        }
    }

    int getId();

    SemanticsConfiguration getSemanticsConfiguration();
}
