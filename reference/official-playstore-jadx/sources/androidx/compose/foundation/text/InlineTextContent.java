package androidx.compose.foundation.text;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.text.Placeholder;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B(\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000b\u001a\u0004\b\f\u0010\rR(\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/compose/foundation/text/InlineTextContent;", "", "Landroidx/compose/ui/text/Placeholder;", "placeholder", "Lkotlin/Function1;", "", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "children", "<init>", "(Landroidx/compose/ui/text/Placeholder;Lr7/q;)V", "Landroidx/compose/ui/text/Placeholder;", "getPlaceholder", "()Landroidx/compose/ui/text/Placeholder;", "Lr7/q;", "getChildren", "()Lr7/q;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class InlineTextContent {
    public static final int $stable = 0;
    private final q<String, Composer, Integer, t0> children;
    private final Placeholder placeholder;

    /* JADX WARN: Multi-variable type inference failed */
    public InlineTextContent(Placeholder placeholder, q<? super String, ? super Composer, ? super Integer, t0> qVar) {
        this.placeholder = placeholder;
        this.children = qVar;
    }

    public final q<String, Composer, Integer, t0> getChildren() {
        return this.children;
    }

    public final Placeholder getPlaceholder() {
        return this.placeholder;
    }
}
