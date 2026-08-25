package androidx.compose.ui.node;

import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\ba\u0018\u0000 .2\u00020\u0001:\u0001.R\"\u0010\u0002\u001a\u00020\u00038&@&X§\u000e¢\u0006\u0012\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\u00020\u000bX¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\u00020\u0011X¦\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\u00020\u0017X¦\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0018\u0010\u001c\u001a\u00020\u001dX¦\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0018\u0010\"\u001a\u00020#X¦\u000e¢\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0018\u0010(\u001a\u00020)X¦\u000e¢\u0006\f\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006/À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/ComposeUiNode;", "", "compositeKeyHash", "", "getCompositeKeyHash$annotations", "()V", "getCompositeKeyHash", "()I", "setCompositeKeyHash", "(I)V", "compositionLocalMap", "Landroidx/compose/runtime/CompositionLocalMap;", "getCompositionLocalMap", "()Landroidx/compose/runtime/CompositionLocalMap;", "setCompositionLocalMap", "(Landroidx/compose/runtime/CompositionLocalMap;)V", "density", "Landroidx/compose/ui/unit/Density;", "getDensity", "()Landroidx/compose/ui/unit/Density;", "setDensity", "(Landroidx/compose/ui/unit/Density;)V", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "measurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "getMeasurePolicy", "()Landroidx/compose/ui/layout/MeasurePolicy;", "setMeasurePolicy", "(Landroidx/compose/ui/layout/MeasurePolicy;)V", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "setModifier", "(Landroidx/compose/ui/Modifier;)V", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "setViewConfiguration", "(Landroidx/compose/ui/platform/ViewConfiguration;)V", "Companion", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ComposeUiNode {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\n\u0010\u0007\u001a\u0004\b\u000b\u0010\tR.\u0010\u0010\u001a\u0019\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R.\u0010\u0015\u001a\u0019\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f8\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0011\u001a\u0004\b\u0016\u0010\u0013R.\u0010\u0018\u001a\u0019\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f8\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0019\u0010\u0013R.\u0010\u001b\u001a\u0019\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0011\u001a\u0004\b\u001c\u0010\u0013R.\u0010\u001e\u001a\u0019\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u0011\u001a\u0004\b\u001f\u0010\u0013R.\u0010!\u001a\u0019\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f8\u0006¢\u0006\f\n\u0004\b!\u0010\u0011\u001a\u0004\b\"\u0010\u0013R7\u0010$\u001a\u0019\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f8GX\u0087\u0004¢\u0006\u0012\n\u0004\b$\u0010\u0011\u0012\u0004\b&\u0010\u0003\u001a\u0004\b%\u0010\u0013¨\u0006'"}, d2 = {"Landroidx/compose/ui/node/ComposeUiNode$Companion;", "", "<init>", "()V", "Lkotlin/Function0;", "Landroidx/compose/ui/node/ComposeUiNode;", "Constructor", "Lr7/a;", "getConstructor", "()Lr7/a;", "VirtualConstructor", "getVirtualConstructor", "Lkotlin/Function2;", "Landroidx/compose/ui/Modifier;", "Lx6/t0;", "Lx6/n;", "SetModifier", "Lr7/p;", "getSetModifier", "()Lr7/p;", "Landroidx/compose/ui/unit/Density;", "SetDensity", "getSetDensity", "Landroidx/compose/runtime/CompositionLocalMap;", "SetResolvedCompositionLocals", "getSetResolvedCompositionLocals", "Landroidx/compose/ui/layout/MeasurePolicy;", "SetMeasurePolicy", "getSetMeasurePolicy", "Landroidx/compose/ui/unit/LayoutDirection;", "SetLayoutDirection", "getSetLayoutDirection", "Landroidx/compose/ui/platform/ViewConfiguration;", "SetViewConfiguration", "getSetViewConfiguration", "", "SetCompositeKeyHash", "getSetCompositeKeyHash", "getSetCompositeKeyHash$annotations", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final r7.a<ComposeUiNode> Constructor = LayoutNode.INSTANCE.getConstructor$ui_release();
        private static final r7.a<ComposeUiNode> VirtualConstructor = ComposeUiNode$Companion$VirtualConstructor$1.INSTANCE;
        private static final p<ComposeUiNode, Modifier, t0> SetModifier = ComposeUiNode$Companion$SetModifier$1.INSTANCE;
        private static final p<ComposeUiNode, Density, t0> SetDensity = ComposeUiNode$Companion$SetDensity$1.INSTANCE;
        private static final p<ComposeUiNode, CompositionLocalMap, t0> SetResolvedCompositionLocals = ComposeUiNode$Companion$SetResolvedCompositionLocals$1.INSTANCE;
        private static final p<ComposeUiNode, MeasurePolicy, t0> SetMeasurePolicy = ComposeUiNode$Companion$SetMeasurePolicy$1.INSTANCE;
        private static final p<ComposeUiNode, LayoutDirection, t0> SetLayoutDirection = ComposeUiNode$Companion$SetLayoutDirection$1.INSTANCE;
        private static final p<ComposeUiNode, ViewConfiguration, t0> SetViewConfiguration = ComposeUiNode$Companion$SetViewConfiguration$1.INSTANCE;
        private static final p<ComposeUiNode, Integer, t0> SetCompositeKeyHash = ComposeUiNode$Companion$SetCompositeKeyHash$1.INSTANCE;

        private Companion() {
        }

        public static /* synthetic */ void getSetCompositeKeyHash$annotations() {
        }

        public final r7.a<ComposeUiNode> getConstructor() {
            return Constructor;
        }

        public final p<ComposeUiNode, Integer, t0> getSetCompositeKeyHash() {
            return SetCompositeKeyHash;
        }

        public final p<ComposeUiNode, Density, t0> getSetDensity() {
            return SetDensity;
        }

        public final p<ComposeUiNode, LayoutDirection, t0> getSetLayoutDirection() {
            return SetLayoutDirection;
        }

        public final p<ComposeUiNode, MeasurePolicy, t0> getSetMeasurePolicy() {
            return SetMeasurePolicy;
        }

        public final p<ComposeUiNode, Modifier, t0> getSetModifier() {
            return SetModifier;
        }

        public final p<ComposeUiNode, CompositionLocalMap, t0> getSetResolvedCompositionLocals() {
            return SetResolvedCompositionLocals;
        }

        public final p<ComposeUiNode, ViewConfiguration, t0> getSetViewConfiguration() {
            return SetViewConfiguration;
        }

        public final r7.a<ComposeUiNode> getVirtualConstructor() {
            return VirtualConstructor;
        }
    }

    int getCompositeKeyHash();

    CompositionLocalMap getCompositionLocalMap();

    Density getDensity();

    LayoutDirection getLayoutDirection();

    MeasurePolicy getMeasurePolicy();

    Modifier getModifier();

    ViewConfiguration getViewConfiguration();

    void setCompositeKeyHash(int i10);

    void setCompositionLocalMap(CompositionLocalMap compositionLocalMap);

    void setDensity(Density density);

    void setLayoutDirection(LayoutDirection layoutDirection);

    void setMeasurePolicy(MeasurePolicy measurePolicy);

    void setModifier(Modifier modifier);

    void setViewConfiguration(ViewConfiguration viewConfiguration);
}
