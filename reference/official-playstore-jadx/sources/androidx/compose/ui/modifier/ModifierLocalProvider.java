package androidx.compose.ui.modifier;

import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/modifier/ModifierLocalProvider;", "T", "Landroidx/compose/ui/Modifier$Element;", "key", "Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "getKey", "()Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "value", "getValue", "()Ljava/lang/Object;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ModifierLocalProvider<T> extends Modifier.Element {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static <T> boolean all(ModifierLocalProvider<T> modifierLocalProvider, l<? super Modifier.Element, Boolean> lVar) {
            return androidx.compose.ui.b.a(modifierLocalProvider, lVar);
        }

        @Deprecated
        public static <T> boolean any(ModifierLocalProvider<T> modifierLocalProvider, l<? super Modifier.Element, Boolean> lVar) {
            return androidx.compose.ui.b.b(modifierLocalProvider, lVar);
        }

        @Deprecated
        public static <T, R> R foldIn(ModifierLocalProvider<T> modifierLocalProvider, R r4, p<? super R, ? super Modifier.Element, ? extends R> pVar) {
            return (R) androidx.compose.ui.b.c(modifierLocalProvider, r4, pVar);
        }

        @Deprecated
        public static <T, R> R foldOut(ModifierLocalProvider<T> modifierLocalProvider, R r4, p<? super Modifier.Element, ? super R, ? extends R> pVar) {
            return (R) androidx.compose.ui.b.d(modifierLocalProvider, r4, pVar);
        }

        @Deprecated
        public static <T> Modifier then(ModifierLocalProvider<T> modifierLocalProvider, Modifier modifier) {
            return androidx.compose.ui.a.a(modifierLocalProvider, modifier);
        }
    }

    ProvidableModifierLocal<T> getKey();

    T getValue();
}
