package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0006\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a*\u0010\b\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\u0005\u001a*\u0010\u0006\u001a\u00020\u0000*\u00020\u00002\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f¢\u0006\u0004\b\u0006\u0010\r\u001a*\u0010\b\u001a\u00020\u0000*\u00020\u00002\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f¢\u0006\u0004\b\b\u0010\r\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/unit/Dp;", "x", "y", "offset-VpY3zN4", "(Landroidx/compose/ui/Modifier;FF)Landroidx/compose/ui/Modifier;", "offset", "absoluteOffset-VpY3zN4", "absoluteOffset", "Lkotlin/Function1;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/IntOffset;", "Lx6/n;", "(Landroidx/compose/ui/Modifier;Lr7/l;)Landroidx/compose/ui/Modifier;", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OffsetKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.OffsetKt$absoluteOffset$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<InspectorInfo, t0> {
        final /* synthetic */ float $x;
        final /* synthetic */ float $y;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(float f10, float f11) {
            super(1);
            this.$x = f10;
            this.$y = f11;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((InspectorInfo) obj);
            return t0.f22605a;
        }

        public final void invoke(InspectorInfo inspectorInfo) {
            inspectorInfo.setName("absoluteOffset");
            a0.c.e(this.$x, inspectorInfo.getProperties(), "x", inspectorInfo).set("y", Dp.m5676boximpl(this.$y));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.OffsetKt$absoluteOffset$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<InspectorInfo, t0> {
        final /* synthetic */ l<Density, IntOffset> $offset;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(l<? super Density, IntOffset> lVar) {
            super(1);
            this.$offset = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((InspectorInfo) obj);
            return t0.f22605a;
        }

        public final void invoke(InspectorInfo inspectorInfo) {
            inspectorInfo.setName("absoluteOffset");
            inspectorInfo.getProperties().set("offset", this.$offset);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.OffsetKt$offset$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03341 extends r implements l<InspectorInfo, t0> {
        final /* synthetic */ float $x;
        final /* synthetic */ float $y;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03341(float f10, float f11) {
            super(1);
            this.$x = f10;
            this.$y = f11;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((InspectorInfo) obj);
            return t0.f22605a;
        }

        public final void invoke(InspectorInfo inspectorInfo) {
            inspectorInfo.setName("offset");
            a0.c.e(this.$x, inspectorInfo.getProperties(), "x", inspectorInfo).set("y", Dp.m5676boximpl(this.$y));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.OffsetKt$offset$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03352 extends r implements l<InspectorInfo, t0> {
        final /* synthetic */ l<Density, IntOffset> $offset;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03352(l<? super Density, IntOffset> lVar) {
            super(1);
            this.$offset = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((InspectorInfo) obj);
            return t0.f22605a;
        }

        public final void invoke(InspectorInfo inspectorInfo) {
            inspectorInfo.setName("offset");
            inspectorInfo.getProperties().set("offset", this.$offset);
        }
    }

    public static final Modifier absoluteOffset(Modifier modifier, l<? super Density, IntOffset> lVar) {
        return modifier.then(new OffsetPxElement(lVar, false, new AnonymousClass2(lVar)));
    }

    /* JADX INFO: renamed from: absoluteOffset-VpY3zN4, reason: not valid java name */
    public static final Modifier m487absoluteOffsetVpY3zN4(Modifier modifier, float f10, float f11) {
        return modifier.then(new OffsetElement(f10, f11, false, new AnonymousClass1(f10, f11), null));
    }

    /* JADX INFO: renamed from: absoluteOffset-VpY3zN4$default, reason: not valid java name */
    public static /* synthetic */ Modifier m488absoluteOffsetVpY3zN4$default(Modifier modifier, float f10, float f11, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = Dp.m5678constructorimpl(0);
        }
        if ((i10 & 2) != 0) {
            f11 = Dp.m5678constructorimpl(0);
        }
        return m487absoluteOffsetVpY3zN4(modifier, f10, f11);
    }

    public static final Modifier offset(Modifier modifier, l<? super Density, IntOffset> lVar) {
        return modifier.then(new OffsetPxElement(lVar, true, new C03352(lVar)));
    }

    /* JADX INFO: renamed from: offset-VpY3zN4, reason: not valid java name */
    public static final Modifier m489offsetVpY3zN4(Modifier modifier, float f10, float f11) {
        return modifier.then(new OffsetElement(f10, f11, true, new C03341(f10, f11), null));
    }

    /* JADX INFO: renamed from: offset-VpY3zN4$default, reason: not valid java name */
    public static /* synthetic */ Modifier m490offsetVpY3zN4$default(Modifier modifier, float f10, float f11, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = Dp.m5678constructorimpl(0);
        }
        if ((i10 & 2) != 0) {
            f11 = Dp.m5678constructorimpl(0);
        }
        return m489offsetVpY3zN4(modifier, f10, f11);
    }
}
