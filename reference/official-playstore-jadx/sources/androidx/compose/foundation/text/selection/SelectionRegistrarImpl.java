package androidx.compose.foundation.text.selection;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.common.util.concurrent.r0;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.collections.w;
import kotlin.jvm.internal.h;
import r7.l;
import r7.p;
import r7.r;
import r7.t;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0000\u0018\u0000 s2\u00020\u0001:\u0001sB\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\t\b\u0016¢\u0006\u0004\b\u0004\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0016\u0010\u0005J2\u0010 \u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010!\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b!\u0010\"JB\u0010(\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00182\u0006\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016ø\u0001\u0000¢\u0006\u0004\b&\u0010'J\u000f\u0010)\u001a\u00020\u000bH\u0016¢\u0006\u0004\b)\u0010\u0006J\u0017\u0010*\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0002H\u0016¢\u0006\u0004\b*\u0010\u0005R\"\u0010+\u001a\u00020\u001c8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u00102\u001a\b\u0012\u0004\u0012\u00020\u0007018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R \u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0007048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R\u001a\u00109\u001a\u000607j\u0002`88\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b9\u0010:R0\u0010<\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010;8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010ARB\u0010C\u001a\"\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u000b\u0018\u00010B8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR6\u0010J\u001a\u0016\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010I8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bJ\u0010K\u001a\u0004\bL\u0010M\"\u0004\bN\u0010ORN\u0010Q\u001a.\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001c\u0018\u00010P8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bQ\u0010R\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR*\u0010X\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010W8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bX\u0010Y\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R0\u0010^\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010;8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b^\u0010=\u001a\u0004\b_\u0010?\"\u0004\b`\u0010AR0\u0010a\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010;8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\ba\u0010=\u001a\u0004\bb\u0010?\"\u0004\bc\u0010ARC\u0010m\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020e0d2\u0012\u0010f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020e0d8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\bg\u0010h\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\u001a\u0010p\u001a\b\u0012\u0004\u0012\u00020\u00070\u00128@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bn\u0010oR \u0010r\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070d8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bq\u0010j\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006t"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl;", "Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "", "initialIncrementId", "<init>", "(J)V", "()V", "Landroidx/compose/foundation/text/selection/Selectable;", "selectable", "subscribe", "(Landroidx/compose/foundation/text/selection/Selectable;)Landroidx/compose/foundation/text/selection/Selectable;", "Lx6/t0;", "unsubscribe", "(Landroidx/compose/foundation/text/selection/Selectable;)V", "nextSelectableId", "()J", "Landroidx/compose/ui/layout/LayoutCoordinates;", "containerLayoutCoordinates", "", "sort", "(Landroidx/compose/ui/layout/LayoutCoordinates;)Ljava/util/List;", "selectableId", "notifyPositionChange", "layoutCoordinates", "Landroidx/compose/ui/geometry/Offset;", "startPosition", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "adjustment", "", "isInTouchMode", "notifySelectionUpdateStart-ubNVwUQ", "(Landroidx/compose/ui/layout/LayoutCoordinates;JLandroidx/compose/foundation/text/selection/SelectionAdjustment;Z)V", "notifySelectionUpdateStart", "notifySelectionUpdateSelectAll", "(JZ)V", "newPosition", "previousPosition", "isStartHandle", "notifySelectionUpdate-njBpvok", "(Landroidx/compose/ui/layout/LayoutCoordinates;JJZLandroidx/compose/foundation/text/selection/SelectionAdjustment;Z)Z", "notifySelectionUpdate", "notifySelectionUpdateEnd", "notifySelectableChange", "sorted", "Z", "getSorted$foundation_release", "()Z", "setSorted$foundation_release", "(Z)V", "", "_selectables", "Ljava/util/List;", "", "_selectableMap", "Ljava/util/Map;", "Ljava/util/concurrent/atomic/AtomicLong;", "Landroidx/compose/foundation/AtomicLong;", "incrementId", "Ljava/util/concurrent/atomic/AtomicLong;", "Lkotlin/Function1;", "onPositionChangeCallback", "Lr7/l;", "getOnPositionChangeCallback$foundation_release", "()Lr7/l;", "setOnPositionChangeCallback$foundation_release", "(Lr7/l;)V", "Lkotlin/Function4;", "onSelectionUpdateStartCallback", "Lr7/r;", "getOnSelectionUpdateStartCallback$foundation_release", "()Lr7/r;", "setOnSelectionUpdateStartCallback$foundation_release", "(Lr7/r;)V", "Lkotlin/Function2;", "onSelectionUpdateSelectAll", "Lr7/p;", "getOnSelectionUpdateSelectAll$foundation_release", "()Lr7/p;", "setOnSelectionUpdateSelectAll$foundation_release", "(Lr7/p;)V", "Lkotlin/Function6;", "onSelectionUpdateCallback", "Lr7/t;", "getOnSelectionUpdateCallback$foundation_release", "()Lr7/t;", "setOnSelectionUpdateCallback$foundation_release", "(Lr7/t;)V", "Lkotlin/Function0;", "onSelectionUpdateEndCallback", "Lr7/a;", "getOnSelectionUpdateEndCallback$foundation_release", "()Lr7/a;", "setOnSelectionUpdateEndCallback$foundation_release", "(Lr7/a;)V", "onSelectableChangeCallback", "getOnSelectableChangeCallback$foundation_release", "setOnSelectableChangeCallback$foundation_release", "afterSelectableUnsubscribe", "getAfterSelectableUnsubscribe$foundation_release", "setAfterSelectableUnsubscribe$foundation_release", "", "Landroidx/compose/foundation/text/selection/Selection;", "<set-?>", "subselections$delegate", "Landroidx/compose/runtime/MutableState;", "getSubselections", "()Ljava/util/Map;", "setSubselections", "(Ljava/util/Map;)V", "subselections", "getSelectables$foundation_release", "()Ljava/util/List;", "selectables", "getSelectableMap$foundation_release", "selectableMap", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SelectionRegistrarImpl implements SelectionRegistrar {
    private final Map<Long, Selectable> _selectableMap;
    private final List<Selectable> _selectables;
    private l<? super Long, t0> afterSelectableUnsubscribe;
    private AtomicLong incrementId;
    private l<? super Long, t0> onPositionChangeCallback;
    private l<? super Long, t0> onSelectableChangeCallback;
    private t<? super Boolean, ? super LayoutCoordinates, ? super Offset, ? super Offset, ? super Boolean, ? super SelectionAdjustment, Boolean> onSelectionUpdateCallback;
    private r7.a<t0> onSelectionUpdateEndCallback;
    private p<? super Boolean, ? super Long, t0> onSelectionUpdateSelectAll;
    private r<? super Boolean, ? super LayoutCoordinates, ? super Offset, ? super SelectionAdjustment, t0> onSelectionUpdateStartCallback;
    private boolean sorted;

    /* JADX INFO: renamed from: subselections$delegate, reason: from kotlin metadata */
    private final MutableState subselections;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final Saver<SelectionRegistrarImpl, Long> Saver = SaverKt.Saver(SelectionRegistrarImpl$Companion$Saver$1.INSTANCE, SelectionRegistrarImpl$Companion$Saver$2.INSTANCE);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl;", "", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final Saver<SelectionRegistrarImpl, Long> getSaver() {
            return SelectionRegistrarImpl.Saver;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionRegistrarImpl$sort$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "Landroidx/compose/foundation/text/selection/Selectable;", "b", "invoke", "(Landroidx/compose/foundation/text/selection/Selectable;Landroidx/compose/foundation/text/selection/Selectable;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements p<Selectable, Selectable, Integer> {
        final /* synthetic */ LayoutCoordinates $containerLayoutCoordinates;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LayoutCoordinates layoutCoordinates) {
            super(2);
            this.$containerLayoutCoordinates = layoutCoordinates;
        }

        @Override // r7.p
        public final Integer invoke(Selectable selectable, Selectable selectable2) {
            LayoutCoordinates layoutCoordinates = selectable.getLayoutCoordinates();
            LayoutCoordinates layoutCoordinates2 = selectable2.getLayoutCoordinates();
            long jMo4639localPositionOfR5De75A = layoutCoordinates != null ? this.$containerLayoutCoordinates.mo4639localPositionOfR5De75A(layoutCoordinates, Offset.INSTANCE.m3252getZeroF1C5BW0()) : Offset.INSTANCE.m3252getZeroF1C5BW0();
            long jMo4639localPositionOfR5De75A2 = layoutCoordinates2 != null ? this.$containerLayoutCoordinates.mo4639localPositionOfR5De75A(layoutCoordinates2, Offset.INSTANCE.m3252getZeroF1C5BW0()) : Offset.INSTANCE.m3252getZeroF1C5BW0();
            return Integer.valueOf(Offset.m3237getYimpl(jMo4639localPositionOfR5De75A) == Offset.m3237getYimpl(jMo4639localPositionOfR5De75A2) ? r0.e(Float.valueOf(Offset.m3236getXimpl(jMo4639localPositionOfR5De75A)), Float.valueOf(Offset.m3236getXimpl(jMo4639localPositionOfR5De75A2))) : r0.e(Float.valueOf(Offset.m3237getYimpl(jMo4639localPositionOfR5De75A)), Float.valueOf(Offset.m3237getYimpl(jMo4639localPositionOfR5De75A2))));
        }
    }

    public /* synthetic */ SelectionRegistrarImpl(long j10, h hVar) {
        this(j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int sort$lambda$2(p pVar, Object obj, Object obj2) {
        return ((Number) pVar.invoke(obj, obj2)).intValue();
    }

    public final l<Long, t0> getAfterSelectableUnsubscribe$foundation_release() {
        return this.afterSelectableUnsubscribe;
    }

    public final l<Long, t0> getOnPositionChangeCallback$foundation_release() {
        return this.onPositionChangeCallback;
    }

    public final l<Long, t0> getOnSelectableChangeCallback$foundation_release() {
        return this.onSelectableChangeCallback;
    }

    public final t<Boolean, LayoutCoordinates, Offset, Offset, Boolean, SelectionAdjustment, Boolean> getOnSelectionUpdateCallback$foundation_release() {
        return this.onSelectionUpdateCallback;
    }

    public final r7.a<t0> getOnSelectionUpdateEndCallback$foundation_release() {
        return this.onSelectionUpdateEndCallback;
    }

    public final p<Boolean, Long, t0> getOnSelectionUpdateSelectAll$foundation_release() {
        return this.onSelectionUpdateSelectAll;
    }

    public final r<Boolean, LayoutCoordinates, Offset, SelectionAdjustment, t0> getOnSelectionUpdateStartCallback$foundation_release() {
        return this.onSelectionUpdateStartCallback;
    }

    public final Map<Long, Selectable> getSelectableMap$foundation_release() {
        return this._selectableMap;
    }

    public final List<Selectable> getSelectables$foundation_release() {
        return this._selectables;
    }

    /* JADX INFO: renamed from: getSorted$foundation_release, reason: from getter */
    public final boolean getSorted() {
        return this.sorted;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    public Map<Long, Selection> getSubselections() {
        return (Map) this.subselections.getValue();
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    public long nextSelectableId() {
        long andIncrement = this.incrementId.getAndIncrement();
        while (andIncrement == 0) {
            andIncrement = this.incrementId.getAndIncrement();
        }
        return andIncrement;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    public void notifyPositionChange(long selectableId) {
        this.sorted = false;
        l<? super Long, t0> lVar = this.onPositionChangeCallback;
        if (lVar != null) {
            lVar.invoke(Long.valueOf(selectableId));
        }
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    public void notifySelectableChange(long selectableId) {
        l<? super Long, t0> lVar = this.onSelectableChangeCallback;
        if (lVar != null) {
            lVar.invoke(Long.valueOf(selectableId));
        }
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    /* JADX INFO: renamed from: notifySelectionUpdate-njBpvok */
    public boolean mo1023notifySelectionUpdatenjBpvok(LayoutCoordinates layoutCoordinates, long newPosition, long previousPosition, boolean isStartHandle, SelectionAdjustment adjustment, boolean isInTouchMode) {
        t<? super Boolean, ? super LayoutCoordinates, ? super Offset, ? super Offset, ? super Boolean, ? super SelectionAdjustment, Boolean> tVar = this.onSelectionUpdateCallback;
        if (tVar != null) {
            return ((Boolean) tVar.invoke(Boolean.valueOf(isInTouchMode), layoutCoordinates, Offset.m3225boximpl(newPosition), Offset.m3225boximpl(previousPosition), Boolean.valueOf(isStartHandle), adjustment)).booleanValue();
        }
        return true;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    public void notifySelectionUpdateEnd() {
        r7.a<t0> aVar = this.onSelectionUpdateEndCallback;
        if (aVar != null) {
            aVar.invoke();
        }
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    public void notifySelectionUpdateSelectAll(long selectableId, boolean isInTouchMode) {
        p<? super Boolean, ? super Long, t0> pVar = this.onSelectionUpdateSelectAll;
        if (pVar != null) {
            pVar.invoke(Boolean.valueOf(isInTouchMode), Long.valueOf(selectableId));
        }
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    /* JADX INFO: renamed from: notifySelectionUpdateStart-ubNVwUQ */
    public void mo1024notifySelectionUpdateStartubNVwUQ(LayoutCoordinates layoutCoordinates, long startPosition, SelectionAdjustment adjustment, boolean isInTouchMode) {
        r<? super Boolean, ? super LayoutCoordinates, ? super Offset, ? super SelectionAdjustment, t0> rVar = this.onSelectionUpdateStartCallback;
        if (rVar != null) {
            rVar.invoke(Boolean.valueOf(isInTouchMode), layoutCoordinates, Offset.m3225boximpl(startPosition), adjustment);
        }
    }

    public final void setAfterSelectableUnsubscribe$foundation_release(l<? super Long, t0> lVar) {
        this.afterSelectableUnsubscribe = lVar;
    }

    public final void setOnPositionChangeCallback$foundation_release(l<? super Long, t0> lVar) {
        this.onPositionChangeCallback = lVar;
    }

    public final void setOnSelectableChangeCallback$foundation_release(l<? super Long, t0> lVar) {
        this.onSelectableChangeCallback = lVar;
    }

    public final void setOnSelectionUpdateCallback$foundation_release(t<? super Boolean, ? super LayoutCoordinates, ? super Offset, ? super Offset, ? super Boolean, ? super SelectionAdjustment, Boolean> tVar) {
        this.onSelectionUpdateCallback = tVar;
    }

    public final void setOnSelectionUpdateEndCallback$foundation_release(r7.a<t0> aVar) {
        this.onSelectionUpdateEndCallback = aVar;
    }

    public final void setOnSelectionUpdateSelectAll$foundation_release(p<? super Boolean, ? super Long, t0> pVar) {
        this.onSelectionUpdateSelectAll = pVar;
    }

    public final void setOnSelectionUpdateStartCallback$foundation_release(r<? super Boolean, ? super LayoutCoordinates, ? super Offset, ? super SelectionAdjustment, t0> rVar) {
        this.onSelectionUpdateStartCallback = rVar;
    }

    public final void setSorted$foundation_release(boolean z) {
        this.sorted = z;
    }

    public void setSubselections(Map<Long, Selection> map) {
        this.subselections.setValue(map);
    }

    public final List<Selectable> sort(LayoutCoordinates containerLayoutCoordinates) {
        if (!this.sorted) {
            w.X(new a7.a(new AnonymousClass1(containerLayoutCoordinates), 1), this._selectables);
            this.sorted = true;
        }
        return getSelectables$foundation_release();
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    public Selectable subscribe(Selectable selectable) {
        if (selectable.getSelectableId() == 0) {
            throw new IllegalArgumentException(("The selectable contains an invalid id: " + selectable.getSelectableId()).toString());
        }
        if (this._selectableMap.containsKey(Long.valueOf(selectable.getSelectableId()))) {
            throw new IllegalArgumentException(("Another selectable with the id: " + selectable + ".selectableId has already subscribed.").toString());
        }
        this._selectableMap.put(Long.valueOf(selectable.getSelectableId()), selectable);
        this._selectables.add(selectable);
        this.sorted = false;
        return selectable;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionRegistrar
    public void unsubscribe(Selectable selectable) {
        if (this._selectableMap.containsKey(Long.valueOf(selectable.getSelectableId()))) {
            this._selectables.remove(selectable);
            this._selectableMap.remove(Long.valueOf(selectable.getSelectableId()));
            l<? super Long, t0> lVar = this.afterSelectableUnsubscribe;
            if (lVar != null) {
                lVar.invoke(Long.valueOf(selectable.getSelectableId()));
            }
        }
    }

    private SelectionRegistrarImpl(long j10) {
        this._selectables = new ArrayList();
        this._selectableMap = new LinkedHashMap();
        this.incrementId = new AtomicLong(j10);
        this.subselections = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(a0.f19683i, null, 2, null);
    }

    public SelectionRegistrarImpl() {
        this(1L);
    }
}
