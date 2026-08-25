package androidx.compose.ui.layout;

import kotlin.Metadata;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 \n2\u00020\u0001:\u0001\nB#\b\u0004\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006R,\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0007\u001a\u0004\b\b\u0010\t\u0082\u0001\u0002\u000b\f¨\u0006\r"}, d2 = {"Landroidx/compose/ui/layout/AlignmentLine;", "", "Lkotlin/Function2;", "", "merger", "<init>", "(Lr7/p;)V", "Lr7/p;", "getMerger$ui_release", "()Lr7/p;", "Companion", "Landroidx/compose/ui/layout/HorizontalAlignmentLine;", "Landroidx/compose/ui/layout/VerticalAlignmentLine;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class AlignmentLine {
    public static final int $stable = 0;
    public static final int Unspecified = Integer.MIN_VALUE;
    private final p<Integer, Integer, Integer> merger;

    public /* synthetic */ AlignmentLine(p pVar, kotlin.jvm.internal.h hVar) {
        this(pVar);
    }

    public final p<Integer, Integer, Integer> getMerger$ui_release() {
        return this.merger;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private AlignmentLine(p<? super Integer, ? super Integer, Integer> pVar) {
        this.merger = pVar;
    }
}
