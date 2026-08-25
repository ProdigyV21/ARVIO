package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "Landroidx/compose/runtime/saveable/SaverScope;", "it", "Landroidx/compose/ui/geometry/Offset;", "invoke-Uv8p0NA", "(Landroidx/compose/runtime/saveable/SaverScope;J)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SaversKt$OffsetSaver$1 extends r implements p<SaverScope, Offset, Object> {
    public static final SaversKt$OffsetSaver$1 INSTANCE = new SaversKt$OffsetSaver$1();

    public SaversKt$OffsetSaver$1() {
        super(2);
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m5135invokeUv8p0NA((SaverScope) obj, ((Offset) obj2).getPackedValue());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
    public final Object m5135invokeUv8p0NA(SaverScope saverScope, long j10) {
        return Offset.m3233equalsimpl0(j10, Offset.INSTANCE.m3251getUnspecifiedF1C5BW0()) ? Boolean.FALSE : t7.a.a(SaversKt.save(Float.valueOf(Offset.m3236getXimpl(j10))), SaversKt.save(Float.valueOf(Offset.m3237getYimpl(j10))));
    }
}
