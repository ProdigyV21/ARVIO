package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.media3.exoplayer.RendererCapabilities;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ListItemKt$ListItem$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ p<Composer, Integer, t0> $decoratedHeadlineContent;
    final /* synthetic */ p<Composer, Integer, t0> $decoratedLeadingContent;
    final /* synthetic */ p<Composer, Integer, t0> $decoratedOverlineContent;
    final /* synthetic */ p<Composer, Integer, t0> $decoratedSupportingContent;
    final /* synthetic */ p<Composer, Integer, t0> $decoratedTrailingContent;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ListItemKt$ListItem$2(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5) {
        super(2);
        this.$decoratedLeadingContent = pVar;
        this.$decoratedTrailingContent = pVar2;
        this.$decoratedHeadlineContent = pVar3;
        this.$decoratedOverlineContent = pVar4;
        this.$decoratedSupportingContent = pVar5;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1502590376, i10, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:150)");
        }
        ListItemKt.ListItemLayout(this.$decoratedLeadingContent, this.$decoratedTrailingContent, this.$decoratedHeadlineContent, this.$decoratedOverlineContent, this.$decoratedSupportingContent, composer, RendererCapabilities.DECODER_SUPPORT_MASK);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
