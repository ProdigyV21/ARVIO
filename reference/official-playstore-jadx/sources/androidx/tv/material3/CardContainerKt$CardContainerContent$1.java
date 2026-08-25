package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class CardContainerKt$CardContainerContent$1 extends kotlin.jvm.internal.r implements r7.p<Composer, Integer, x6.t0> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ r7.p f5539i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.p f5540l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.p f5541m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CardContainerKt$CardContainerContent$1(r7.p pVar, r7.p pVar2, r7.p pVar3) {
        super(2);
        this.f5539i = pVar;
        this.f5540l = pVar2;
        this.f5541m = pVar3;
    }

    @Override // r7.p
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1573094805, i10, -1, "androidx.tv.material3.CardContainerContent.<anonymous> (CardContainer.kt:156)");
        }
        CardKt.CardContent(this.f5539i, this.f5540l, this.f5541m, composer, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
