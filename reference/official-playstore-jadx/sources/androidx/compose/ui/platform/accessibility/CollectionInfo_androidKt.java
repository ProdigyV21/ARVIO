package androidx.compose.ui.platform.accessibility;

import android.view.accessibility.AccessibilityNodeInfo;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.semantics.CollectionInfo;
import androidx.compose.ui.semantics.CollectionItemInfo;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsProperties;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.r;
import q0.i;
import q0.j;
import q0.k;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001f\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0007\u0010\u0006\u001a\u0013\u0010\t\u001a\u00020\b*\u00020\u0000H\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001d\u0010\r\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00000\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000e\u001a\u001b\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010*\u00020\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a#\u0010\u0017\u001a\n \u0011*\u0004\u0018\u00010\u00160\u0016*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\"\u0018\u0010\u0019\u001a\u00020\b*\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsNode;", "node", "Lq0/k;", "info", "Lx6/t0;", "setCollectionInfo", "(Landroidx/compose/ui/semantics/SemanticsNode;Lq0/k;)V", "setCollectionItemInfo", "", "hasCollectionInfo", "(Landroidx/compose/ui/semantics/SemanticsNode;)Z", "", "items", "calculateIfHorizontallyStacked", "(Ljava/util/List;)Z", "Landroidx/compose/ui/semantics/CollectionInfo;", "Lq0/i;", "kotlin.jvm.PlatformType", "toAccessibilityCollectionInfo", "(Landroidx/compose/ui/semantics/CollectionInfo;)Lq0/i;", "Landroidx/compose/ui/semantics/CollectionItemInfo;", "itemNode", "Lq0/j;", "toAccessibilityCollectionItemInfo", "(Landroidx/compose/ui/semantics/CollectionItemInfo;Landroidx/compose/ui/semantics/SemanticsNode;)Lq0/j;", "isLazyCollection", "(Landroidx/compose/ui/semantics/CollectionInfo;)Z", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CollectionInfo_androidKt {

    /* JADX INFO: renamed from: androidx.compose.ui.platform.accessibility.CollectionInfo_androidKt$toAccessibilityCollectionItemInfo$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            return Boolean.FALSE;
        }
    }

    private static final boolean calculateIfHorizontallyStacked(List<SemanticsNode> list) {
        List arrayList;
        long packedValue;
        if (list.size() < 2) {
            return true;
        }
        if (list.size() == 0 || list.size() == 1) {
            arrayList = z.f19728i;
        } else {
            arrayList = new ArrayList();
            SemanticsNode semanticsNode = list.get(0);
            int iW = t7.a.w(list);
            int i10 = 0;
            while (i10 < iW) {
                i10++;
                SemanticsNode semanticsNode2 = list.get(i10);
                SemanticsNode semanticsNode3 = semanticsNode2;
                SemanticsNode semanticsNode4 = semanticsNode;
                arrayList.add(Offset.m3225boximpl(OffsetKt.Offset(Math.abs(Offset.m3236getXimpl(semanticsNode4.getBoundsInRoot().m3266getCenterF1C5BW0()) - Offset.m3236getXimpl(semanticsNode3.getBoundsInRoot().m3266getCenterF1C5BW0())), Math.abs(Offset.m3237getYimpl(semanticsNode4.getBoundsInRoot().m3266getCenterF1C5BW0()) - Offset.m3237getYimpl(semanticsNode3.getBoundsInRoot().m3266getCenterF1C5BW0())))));
                semanticsNode = semanticsNode2;
            }
        }
        if (arrayList.size() == 1) {
            packedValue = ((Offset) x.m0(arrayList)).getPackedValue();
        } else {
            if (arrayList.isEmpty()) {
                throw new UnsupportedOperationException("Empty collection can't be reduced.");
            }
            Object objM0 = x.m0(arrayList);
            int iW2 = t7.a.w(arrayList);
            if (1 <= iW2) {
                int i11 = 1;
                while (true) {
                    objM0 = Offset.m3225boximpl(Offset.m3241plusMKHz9U(((Offset) objM0).getPackedValue(), ((Offset) arrayList.get(i11)).getPackedValue()));
                    if (i11 == iW2) {
                        break;
                    }
                    i11++;
                }
            }
            packedValue = ((Offset) objM0).getPackedValue();
        }
        return Offset.m3227component2impl(packedValue) < Offset.m3226component1impl(packedValue);
    }

    public static final boolean hasCollectionInfo(SemanticsNode semanticsNode) {
        SemanticsConfiguration config = semanticsNode.getConfig();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        return (SemanticsConfigurationKt.getOrNull(config, semanticsProperties.getCollectionInfo()) == null && SemanticsConfigurationKt.getOrNull(semanticsNode.getConfig(), semanticsProperties.getSelectableGroup()) == null) ? false : true;
    }

    private static final boolean isLazyCollection(CollectionInfo collectionInfo) {
        return collectionInfo.getRowCount() < 0 || collectionInfo.getColumnCount() < 0;
    }

    public static final void setCollectionInfo(SemanticsNode semanticsNode, k kVar) {
        SemanticsConfiguration config = semanticsNode.getConfig();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        CollectionInfo collectionInfo = (CollectionInfo) SemanticsConfigurationKt.getOrNull(config, semanticsProperties.getCollectionInfo());
        if (collectionInfo != null) {
            i accessibilityCollectionInfo = toAccessibilityCollectionInfo(collectionInfo);
            kVar.f21319a.setCollectionInfo(accessibilityCollectionInfo == null ? null : (AccessibilityNodeInfo.CollectionInfo) accessibilityCollectionInfo.f21316a);
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (SemanticsConfigurationKt.getOrNull(semanticsNode.getConfig(), semanticsProperties.getSelectableGroup()) != null) {
            List<SemanticsNode> replacedChildren$ui_release = semanticsNode.getReplacedChildren$ui_release();
            int size = replacedChildren$ui_release.size();
            for (int i10 = 0; i10 < size; i10++) {
                SemanticsNode semanticsNode2 = replacedChildren$ui_release.get(i10);
                if (semanticsNode2.getConfig().contains(SemanticsProperties.INSTANCE.getSelected())) {
                    arrayList.add(semanticsNode2);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        boolean zCalculateIfHorizontallyStacked = calculateIfHorizontallyStacked(arrayList);
        kVar.f21319a.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(zCalculateIfHorizontallyStacked ? 1 : arrayList.size(), zCalculateIfHorizontallyStacked ? arrayList.size() : 1, false, 0));
    }

    public static final void setCollectionItemInfo(SemanticsNode semanticsNode, k kVar) {
        SemanticsConfiguration config = semanticsNode.getConfig();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        CollectionItemInfo collectionItemInfo = (CollectionItemInfo) SemanticsConfigurationKt.getOrNull(config, semanticsProperties.getCollectionItemInfo());
        if (collectionItemInfo != null) {
            kVar.h(toAccessibilityCollectionItemInfo(collectionItemInfo, semanticsNode));
        }
        SemanticsNode parent = semanticsNode.getParent();
        if (parent == null || SemanticsConfigurationKt.getOrNull(parent.getConfig(), semanticsProperties.getSelectableGroup()) == null) {
            return;
        }
        CollectionInfo collectionInfo = (CollectionInfo) SemanticsConfigurationKt.getOrNull(parent.getConfig(), semanticsProperties.getCollectionInfo());
        if ((collectionInfo == null || !isLazyCollection(collectionInfo)) && semanticsNode.getConfig().contains(semanticsProperties.getSelected())) {
            ArrayList arrayList = new ArrayList();
            List<SemanticsNode> replacedChildren$ui_release = parent.getReplacedChildren$ui_release();
            int size = replacedChildren$ui_release.size();
            int i10 = 0;
            for (int i11 = 0; i11 < size; i11++) {
                SemanticsNode semanticsNode2 = replacedChildren$ui_release.get(i11);
                if (semanticsNode2.getConfig().contains(SemanticsProperties.INSTANCE.getSelected())) {
                    arrayList.add(semanticsNode2);
                    if (semanticsNode2.getLayoutNode().getPlaceOrder$ui_release() < semanticsNode.getLayoutNode().getPlaceOrder$ui_release()) {
                        i10++;
                    }
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            boolean zCalculateIfHorizontallyStacked = calculateIfHorizontallyStacked(arrayList);
            kVar.h(j.a(zCalculateIfHorizontallyStacked ? 0 : i10, 1, zCalculateIfHorizontallyStacked ? i10 : 0, 1, ((Boolean) semanticsNode.getConfig().getOrElse(SemanticsProperties.INSTANCE.getSelected(), CollectionInfo_androidKt$setCollectionItemInfo$itemInfo$1.INSTANCE)).booleanValue()));
        }
    }

    private static final i toAccessibilityCollectionInfo(CollectionInfo collectionInfo) {
        return new i(AccessibilityNodeInfo.CollectionInfo.obtain(collectionInfo.getRowCount(), collectionInfo.getColumnCount(), false, 0));
    }

    private static final j toAccessibilityCollectionItemInfo(CollectionItemInfo collectionItemInfo, SemanticsNode semanticsNode) {
        return j.a(collectionItemInfo.getRowIndex(), collectionItemInfo.getRowSpan(), collectionItemInfo.getColumnIndex(), collectionItemInfo.getColumnSpan(), ((Boolean) semanticsNode.getConfig().getOrElse(SemanticsProperties.INSTANCE.getSelected(), AnonymousClass1.INSTANCE)).booleanValue());
    }
}
