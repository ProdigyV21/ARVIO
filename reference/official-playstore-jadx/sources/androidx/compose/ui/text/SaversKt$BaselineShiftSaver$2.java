package androidx.compose.ui.text;

import androidx.compose.ui.text.style.BaselineShift;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/text/style/BaselineShift;", "it", "", "invoke-jTk7eUs"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SaversKt$BaselineShiftSaver$2 extends r implements l<Object, BaselineShift> {
    public static final SaversKt$BaselineShiftSaver$2 INSTANCE = new SaversKt$BaselineShiftSaver$2();

    public SaversKt$BaselineShiftSaver$2() {
        super(1);
    }

    @Override // r7.l
    /* JADX INFO: renamed from: invoke-jTk7eUs, reason: not valid java name and merged with bridge method [inline-methods] */
    public final BaselineShift invoke(Object obj) {
        return BaselineShift.m5466boximpl(BaselineShift.m5467constructorimpl(((Float) obj).floatValue()));
    }
}
