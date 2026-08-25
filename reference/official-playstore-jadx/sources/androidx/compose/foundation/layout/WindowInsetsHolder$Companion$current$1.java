package androidx.compose.foundation.layout;

import android.view.View;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/DisposableEffectResult;", "Landroidx/compose/runtime/DisposableEffectScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class WindowInsetsHolder$Companion$current$1 extends r implements l<DisposableEffectScope, DisposableEffectResult> {
    final /* synthetic */ WindowInsetsHolder $insets;
    final /* synthetic */ View $view;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WindowInsetsHolder$Companion$current$1(WindowInsetsHolder windowInsetsHolder, View view) {
        super(1);
        this.$insets = windowInsetsHolder;
        this.$view = view;
    }

    @Override // r7.l
    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
        this.$insets.incrementAccessors(this.$view);
        final WindowInsetsHolder windowInsetsHolder = this.$insets;
        final View view = this.$view;
        return new DisposableEffectResult() { // from class: androidx.compose.foundation.layout.WindowInsetsHolder$Companion$current$1$invoke$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                windowInsetsHolder.decrementAccessors(view);
            }
        };
    }
}
