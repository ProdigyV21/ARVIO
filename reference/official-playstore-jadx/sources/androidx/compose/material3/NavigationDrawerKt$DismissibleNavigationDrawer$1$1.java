package androidx.compose.material3;

import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class NavigationDrawerKt$DismissibleNavigationDrawer$1$1 extends r implements r7.a<t0> {
    final /* synthetic */ Density $density;
    final /* synthetic */ DrawerState $drawerState;
    final /* synthetic */ float $maxValue;
    final /* synthetic */ float $minValue;

    /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/material3/DraggableAnchorsConfig;", "Landroidx/compose/material3/DrawerValue;", "Lx6/t0;", "invoke", "(Landroidx/compose/material3/DraggableAnchorsConfig;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<DraggableAnchorsConfig<DrawerValue>, t0> {
        final /* synthetic */ float $maxValue;
        final /* synthetic */ float $minValue;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(float f10, float f11) {
            super(1);
            this.$minValue = f10;
            this.$maxValue = f11;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((DraggableAnchorsConfig<DrawerValue>) obj);
            return t0.f22605a;
        }

        public final void invoke(DraggableAnchorsConfig<DrawerValue> draggableAnchorsConfig) {
            draggableAnchorsConfig.at(DrawerValue.Closed, this.$minValue);
            draggableAnchorsConfig.at(DrawerValue.Open, this.$maxValue);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NavigationDrawerKt$DismissibleNavigationDrawer$1$1(DrawerState drawerState, Density density, float f10, float f11) {
        super(0);
        this.$drawerState = drawerState;
        this.$density = density;
        this.$minValue = f10;
        this.$maxValue = f11;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m1721invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m1721invoke() {
        this.$drawerState.setDensity$material3_release(this.$density);
        AnchoredDraggableState.updateAnchors$default(this.$drawerState.getAnchoredDraggableState$material3_release(), AnchoredDraggableKt.DraggableAnchors(new AnonymousClass1(this.$minValue, this.$maxValue)), null, 2, null);
    }
}
