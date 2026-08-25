package androidx.compose.foundation.text;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.text.TextLayoutResult;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/text/TextLayoutResult;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/text/TextLayoutResult;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ClickableTextKt$ClickableText$2$1 extends r implements l<TextLayoutResult, t0> {
    final /* synthetic */ MutableState<TextLayoutResult> $layoutResult;
    final /* synthetic */ l<TextLayoutResult, t0> $onTextLayout;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ClickableTextKt$ClickableText$2$1(MutableState<TextLayoutResult> mutableState, l<? super TextLayoutResult, t0> lVar) {
        super(1);
        this.$layoutResult = mutableState;
        this.$onTextLayout = lVar;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((TextLayoutResult) obj);
        return t0.f22605a;
    }

    public final void invoke(TextLayoutResult textLayoutResult) {
        this.$layoutResult.setValue(textLayoutResult);
        this.$onTextLayout.invoke(textLayoutResult);
    }
}
