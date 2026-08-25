package androidx.compose.foundation;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpSize;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0099\u0001\u0012\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006\u0012\u001b\b\u0002\u0010\b\u001a\u0015\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\u0002\b\u0006\u0012\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0010\u001a\u00020\t\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u000e\u0012\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u001a\u0010 \u001a\u00020\u000e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eH\u0096\u0002¢\u0006\u0004\b \u0010!J\u000f\u0010#\u001a\u00020\"H\u0016¢\u0006\u0004\b#\u0010$J\u0013\u0010&\u001a\u00020\n*\u00020%H\u0016¢\u0006\u0004\b&\u0010'R%\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010(R'\u0010\b\u001a\u0015\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\u0002\b\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010(R\"\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010(R\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010)R\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010*R\u001a\u0010\u0010\u001a\u00020\t8\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0010\u0010+R\u001a\u0010\u0012\u001a\u00020\u00118\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0012\u0010)R\u001a\u0010\u0013\u001a\u00020\u00118\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0013\u0010)R\u0014\u0010\u0014\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010*R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010,\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006-"}, d2 = {"Landroidx/compose/foundation/MagnifierElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/MagnifierNode;", "Lkotlin/Function1;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/geometry/Offset;", "Lx6/n;", "sourceCenter", "magnifierCenter", "Landroidx/compose/ui/unit/DpSize;", "Lx6/t0;", "onSizeChanged", "", "zoom", "", "useTextDefault", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/unit/Dp;", "cornerRadius", "elevation", "clippingEnabled", "Landroidx/compose/foundation/PlatformMagnifierFactory;", "platformMagnifierFactory", "<init>", "(Lr7/l;Lr7/l;Lr7/l;FZJFFZLandroidx/compose/foundation/PlatformMagnifierFactory;Lkotlin/jvm/internal/h;)V", "create", "()Landroidx/compose/foundation/MagnifierNode;", "node", "update", "(Landroidx/compose/foundation/MagnifierNode;)V", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "Lr7/l;", "F", "Z", "J", "Landroidx/compose/foundation/PlatformMagnifierFactory;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MagnifierElement extends ModifierNodeElement<MagnifierNode> {
    public static final int $stable = 0;
    private final boolean clippingEnabled;
    private final float cornerRadius;
    private final float elevation;
    private final l<Density, Offset> magnifierCenter;
    private final l<DpSize, t0> onSizeChanged;
    private final PlatformMagnifierFactory platformMagnifierFactory;
    private final long size;
    private final l<Density, Offset> sourceCenter;
    private final boolean useTextDefault;
    private final float zoom;

    public /* synthetic */ MagnifierElement(l lVar, l lVar2, l lVar3, float f10, boolean z, long j10, float f11, float f12, boolean z5, PlatformMagnifierFactory platformMagnifierFactory, h hVar) {
        this(lVar, lVar2, lVar3, f10, z, j10, f11, f12, z5, platformMagnifierFactory);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MagnifierElement)) {
            return false;
        }
        MagnifierElement magnifierElement = (MagnifierElement) other;
        return p.a(this.sourceCenter, magnifierElement.sourceCenter) && p.a(this.magnifierCenter, magnifierElement.magnifierCenter) && this.zoom == magnifierElement.zoom && this.useTextDefault == magnifierElement.useTextDefault && DpSize.m5773equalsimpl0(this.size, magnifierElement.size) && Dp.m5683equalsimpl0(this.cornerRadius, magnifierElement.cornerRadius) && Dp.m5683equalsimpl0(this.elevation, magnifierElement.elevation) && this.clippingEnabled == magnifierElement.clippingEnabled && p.a(this.onSizeChanged, magnifierElement.onSizeChanged) && p.a(this.platformMagnifierFactory, magnifierElement.platformMagnifierFactory);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        int iHashCode = this.sourceCenter.hashCode() * 31;
        l<Density, Offset> lVar = this.magnifierCenter;
        int iC = (a0.c.C(this.elevation, a0.c.C(this.cornerRadius, (DpSize.m5778hashCodeimpl(this.size) + ((a0.c.b(this.zoom, (iHashCode + (lVar != null ? lVar.hashCode() : 0)) * 31, 31) + (this.useTextDefault ? 1231 : 1237)) * 31)) * 31, 31), 31) + (this.clippingEnabled ? 1231 : 1237)) * 31;
        l<DpSize, t0> lVar2 = this.onSizeChanged;
        return this.platformMagnifierFactory.hashCode() + ((iC + (lVar2 != null ? lVar2.hashCode() : 0)) * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("magnifier");
        inspectorInfo.getProperties().set("sourceCenter", this.sourceCenter);
        inspectorInfo.getProperties().set("magnifierCenter", this.magnifierCenter);
        inspectorInfo.getProperties().set("zoom", Float.valueOf(this.zoom));
        inspectorInfo.getProperties().set(ContentDisposition.Parameters.Size, DpSize.m5764boximpl(this.size));
        a0.c.e(this.elevation, a0.c.e(this.cornerRadius, inspectorInfo.getProperties(), "cornerRadius", inspectorInfo), "elevation", inspectorInfo).set("clippingEnabled", Boolean.valueOf(this.clippingEnabled));
    }

    public /* synthetic */ MagnifierElement(l lVar, l lVar2, l lVar3, float f10, boolean z, long j10, float f11, float f12, boolean z5, PlatformMagnifierFactory platformMagnifierFactory, int i10, h hVar) {
        this(lVar, (i10 & 2) != 0 ? null : lVar2, (i10 & 4) != 0 ? null : lVar3, (i10 & 8) != 0 ? Float.NaN : f10, (i10 & 16) != 0 ? false : z, (i10 & 32) != 0 ? DpSize.INSTANCE.m5785getUnspecifiedMYxV2XQ() : j10, (i10 & 64) != 0 ? Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM() : f11, (i10 & 128) != 0 ? Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM() : f12, (i10 & 256) != 0 ? true : z5, platformMagnifierFactory, null);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public MagnifierNode create() {
        return new MagnifierNode(this.sourceCenter, this.magnifierCenter, this.onSizeChanged, this.zoom, this.useTextDefault, this.size, this.cornerRadius, this.elevation, this.clippingEnabled, this.platformMagnifierFactory, null);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(MagnifierNode node) {
        node.m247update5F03MCQ(this.sourceCenter, this.magnifierCenter, this.zoom, this.useTextDefault, this.size, this.cornerRadius, this.elevation, this.clippingEnabled, this.onSizeChanged, this.platformMagnifierFactory);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private MagnifierElement(l<? super Density, Offset> lVar, l<? super Density, Offset> lVar2, l<? super DpSize, t0> lVar3, float f10, boolean z, long j10, float f11, float f12, boolean z5, PlatformMagnifierFactory platformMagnifierFactory) {
        this.sourceCenter = lVar;
        this.magnifierCenter = lVar2;
        this.onSizeChanged = lVar3;
        this.zoom = f10;
        this.useTextDefault = z;
        this.size = j10;
        this.cornerRadius = f11;
        this.elevation = f12;
        this.clippingEnabled = z5;
        this.platformMagnifierFactory = platformMagnifierFactory;
    }
}
