package androidx.compose.ui.layout;

import kotlin.Metadata;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class AlignmentLineKt$LastBaseline$1 extends kotlin.jvm.internal.m implements p<Integer, Integer, Integer> {
    public static final AlignmentLineKt$LastBaseline$1 INSTANCE = new AlignmentLineKt$LastBaseline$1();

    public AlignmentLineKt$LastBaseline$1() {
        super(2, t7.a.class, "max", "max(II)I", 1);
    }

    public final Integer invoke(int i10, int i11) {
        return Integer.valueOf(Math.max(i10, i11));
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke(((Number) obj).intValue(), ((Number) obj2).intValue());
    }
}
