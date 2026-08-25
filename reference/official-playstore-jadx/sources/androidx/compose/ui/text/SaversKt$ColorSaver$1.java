package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "Landroidx/compose/runtime/saveable/SaverScope;", "it", "Landroidx/compose/ui/graphics/Color;", "invoke-4WTKRHQ", "(Landroidx/compose/runtime/saveable/SaverScope;J)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SaversKt$ColorSaver$1 extends r implements p<SaverScope, Color, Object> {
    public static final SaversKt$ColorSaver$1 INSTANCE = new SaversKt$ColorSaver$1();

    public SaversKt$ColorSaver$1() {
        super(2);
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m5133invoke4WTKRHQ((SaverScope) obj, ((Color) obj2).m3482unboximpl());
    }

    /* JADX INFO: renamed from: invoke-4WTKRHQ, reason: not valid java name */
    public final Object m5133invoke4WTKRHQ(SaverScope saverScope, long j10) {
        return j10 == Color.INSTANCE.m3508getUnspecified0d7_KjU() ? Boolean.FALSE : Integer.valueOf(ColorKt.m3526toArgb8_81llA(j10));
    }
}
