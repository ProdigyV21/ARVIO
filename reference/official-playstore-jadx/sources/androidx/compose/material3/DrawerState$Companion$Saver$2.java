package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/material3/DrawerState;", "it", "Landroidx/compose/material3/DrawerValue;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class DrawerState$Companion$Saver$2 extends r implements l<DrawerValue, DrawerState> {
    final /* synthetic */ l<DrawerValue, Boolean> $confirmStateChange;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DrawerState$Companion$Saver$2(l<? super DrawerValue, Boolean> lVar) {
        super(1);
        this.$confirmStateChange = lVar;
    }

    @Override // r7.l
    public final DrawerState invoke(DrawerValue drawerValue) {
        return new DrawerState(drawerValue, this.$confirmStateChange);
    }
}
