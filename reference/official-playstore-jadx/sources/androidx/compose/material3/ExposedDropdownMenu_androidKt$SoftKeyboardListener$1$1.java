package androidx.compose.material3;

import android.view.View;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/DisposableEffectResult;", "Landroidx/compose/runtime/DisposableEffectScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ExposedDropdownMenu_androidKt$SoftKeyboardListener$1$1 extends r implements l<DisposableEffectScope, DisposableEffectResult> {
    final /* synthetic */ r7.a<t0> $onKeyboardVisibilityChange;
    final /* synthetic */ View $view;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExposedDropdownMenu_androidKt$SoftKeyboardListener$1$1(View view, r7.a<t0> aVar) {
        super(1);
        this.$view = view;
        this.$onKeyboardVisibilityChange = aVar;
    }

    @Override // r7.l
    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
        final ExposedDropdownMenu_androidKt$SoftKeyboardListener$1$1$listener$1 exposedDropdownMenu_androidKt$SoftKeyboardListener$1$1$listener$1 = new ExposedDropdownMenu_androidKt$SoftKeyboardListener$1$1$listener$1(this.$view, this.$onKeyboardVisibilityChange);
        return new DisposableEffectResult() { // from class: androidx.compose.material3.ExposedDropdownMenu_androidKt$SoftKeyboardListener$1$1$invoke$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                exposedDropdownMenu_androidKt$SoftKeyboardListener$1$1$listener$1.dispose();
            }
        };
    }
}
