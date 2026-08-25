package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import r7.p;
import r7.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u0080\u0001\u0010\u000e\u001a\u00020\f*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012`\u0010\r\u001a\\\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0003H\u0086@¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0011\u0010\u0011\u001a\u00020\t*\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0016\u0010\u0015\u001a\u00020\t*\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0011\u0010\u0016\u001a\u00020\t*\u00020\u0010¢\u0006\u0004\b\u0016\u0010\u0012\u001a\u0011\u0010\u0017\u001a\u00020\u0004*\u00020\u0010¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u001b\u0010\u001a\u001a\u00020\t*\u00020\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u0001¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001b\u0010\u001c\u001a\u00020\u0004*\u00020\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u0001¢\u0006\u0004\b\u001c\u0010\u001d\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001e"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "", "panZoomLock", "Lkotlin/Function4;", "Landroidx/compose/ui/geometry/Offset;", "Lx6/y;", ContentDisposition.Parameters.Name, "centroid", "pan", "", "zoom", "rotation", "Lx6/t0;", "onGesture", "detectTransformGestures", "(Landroidx/compose/ui/input/pointer/PointerInputScope;ZLr7/r;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/ui/input/pointer/PointerEvent;", "calculateRotation", "(Landroidx/compose/ui/input/pointer/PointerEvent;)F", "angle-k-4lQ0M", "(J)F", "angle", "calculateZoom", "calculatePan", "(Landroidx/compose/ui/input/pointer/PointerEvent;)J", "useCurrent", "calculateCentroidSize", "(Landroidx/compose/ui/input/pointer/PointerEvent;Z)F", "calculateCentroid", "(Landroidx/compose/ui/input/pointer/PointerEvent;Z)J", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TransformGestureDetectorKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformGestureDetectorKt$detectTransformGestures$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.TransformGestureDetectorKt$detectTransformGestures$2", f = "TransformGestureDetector.kt", l = {59, 61}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super t0>, Object> {
        final /* synthetic */ r<Offset, Offset, Float, Float, t0> $onGesture;
        final /* synthetic */ boolean $panZoomLock;
        float F$0;
        float F$1;
        float F$2;
        int I$0;
        int I$1;
        long J$0;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(boolean z, r<? super Offset, ? super Offset, ? super Float, ? super Float, t0> rVar, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$panZoomLock = z;
            this.$onGesture = rVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$panZoomLock, this.$onGesture, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0091, code lost:
        
            if (r15 == r11) goto L16;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:58:0x015b  */
        /* JADX WARN: Type inference failed for: r6v4 */
        /* JADX WARN: Type inference failed for: r6v5 */
        /* JADX WARN: Type inference failed for: r6v8 */
        /* JADX WARN: Type inference failed for: r7v12 */
        /* JADX WARN: Type inference failed for: r7v3 */
        /* JADX WARN: Type inference failed for: r7v4, types: [int] */
        /* JADX WARN: Type inference failed for: r8v15 */
        /* JADX WARN: Type inference failed for: r8v3 */
        /* JADX WARN: Type inference failed for: r8v4, types: [int] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0091 -> B:17:0x0094). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r25) {
            /*
                Method dump skipped, instruction units count: 422
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TransformGestureDetectorKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: angle-k-4lQ0M, reason: not valid java name */
    private static final float m389anglek4lQ0M(long j10) {
        if (Offset.m3236getXimpl(j10) == 0.0f && Offset.m3237getYimpl(j10) == 0.0f) {
            return 0.0f;
        }
        return ((-((float) Math.atan2(Offset.m3236getXimpl(j10), Offset.m3237getYimpl(j10)))) * 180.0f) / 3.1415927f;
    }

    public static final long calculateCentroid(PointerEvent pointerEvent, boolean z) {
        long jM3252getZeroF1C5BW0 = Offset.INSTANCE.m3252getZeroF1C5BW0();
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int size = changes.size();
        int i10 = 0;
        for (int i11 = 0; i11 < size; i11++) {
            PointerInputChange pointerInputChange = changes.get(i11);
            if (pointerInputChange.getPressed() && pointerInputChange.getPreviousPressed()) {
                jM3252getZeroF1C5BW0 = Offset.m3241plusMKHz9U(jM3252getZeroF1C5BW0, z ? pointerInputChange.getPosition() : pointerInputChange.getPreviousPosition());
                i10++;
            }
        }
        return i10 == 0 ? Offset.INSTANCE.m3251getUnspecifiedF1C5BW0() : Offset.m3231divtuRUvjQ(jM3252getZeroF1C5BW0, i10);
    }

    public static /* synthetic */ long calculateCentroid$default(PointerEvent pointerEvent, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        return calculateCentroid(pointerEvent, z);
    }

    public static final float calculateCentroidSize(PointerEvent pointerEvent, boolean z) {
        long jCalculateCentroid = calculateCentroid(pointerEvent, z);
        float fM3234getDistanceimpl = 0.0f;
        if (Offset.m3233equalsimpl0(jCalculateCentroid, Offset.INSTANCE.m3251getUnspecifiedF1C5BW0())) {
            return 0.0f;
        }
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int size = changes.size();
        int i10 = 0;
        for (int i11 = 0; i11 < size; i11++) {
            PointerInputChange pointerInputChange = changes.get(i11);
            if (pointerInputChange.getPressed() && pointerInputChange.getPreviousPressed()) {
                i10++;
                fM3234getDistanceimpl = Offset.m3234getDistanceimpl(Offset.m3240minusMKHz9U(z ? pointerInputChange.getPosition() : pointerInputChange.getPreviousPosition(), jCalculateCentroid)) + fM3234getDistanceimpl;
            }
        }
        return fM3234getDistanceimpl / i10;
    }

    public static /* synthetic */ float calculateCentroidSize$default(PointerEvent pointerEvent, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        return calculateCentroidSize(pointerEvent, z);
    }

    public static final long calculatePan(PointerEvent pointerEvent) {
        long jCalculateCentroid = calculateCentroid(pointerEvent, true);
        Offset.Companion companion = Offset.INSTANCE;
        return Offset.m3233equalsimpl0(jCalculateCentroid, companion.m3251getUnspecifiedF1C5BW0()) ? companion.m3252getZeroF1C5BW0() : Offset.m3240minusMKHz9U(jCalculateCentroid, calculateCentroid(pointerEvent, false));
    }

    public static final float calculateRotation(PointerEvent pointerEvent) {
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int size = changes.size();
        int i10 = 0;
        int i11 = 0;
        while (true) {
            int i12 = 1;
            if (i10 >= size) {
                break;
            }
            PointerInputChange pointerInputChange = changes.get(i10);
            if (!pointerInputChange.getPreviousPressed() || !pointerInputChange.getPressed()) {
                i12 = 0;
            }
            i11 += i12;
            i10++;
        }
        if (i11 < 2) {
            return 0.0f;
        }
        long jCalculateCentroid = calculateCentroid(pointerEvent, true);
        long jCalculateCentroid2 = calculateCentroid(pointerEvent, false);
        List<PointerInputChange> changes2 = pointerEvent.getChanges();
        int size2 = changes2.size();
        float f10 = 0.0f;
        float f11 = 0.0f;
        for (int i13 = 0; i13 < size2; i13++) {
            PointerInputChange pointerInputChange2 = changes2.get(i13);
            if (pointerInputChange2.getPressed() && pointerInputChange2.getPreviousPressed()) {
                long position = pointerInputChange2.getPosition();
                long jM3240minusMKHz9U = Offset.m3240minusMKHz9U(pointerInputChange2.getPreviousPosition(), jCalculateCentroid2);
                long jM3240minusMKHz9U2 = Offset.m3240minusMKHz9U(position, jCalculateCentroid);
                float fM389anglek4lQ0M = m389anglek4lQ0M(jM3240minusMKHz9U2) - m389anglek4lQ0M(jM3240minusMKHz9U);
                float fM3234getDistanceimpl = Offset.m3234getDistanceimpl(Offset.m3241plusMKHz9U(jM3240minusMKHz9U2, jM3240minusMKHz9U)) / 2.0f;
                if (fM389anglek4lQ0M > 180.0f) {
                    fM389anglek4lQ0M -= 360.0f;
                } else if (fM389anglek4lQ0M < -180.0f) {
                    fM389anglek4lQ0M += 360.0f;
                }
                f11 += fM389anglek4lQ0M * fM3234getDistanceimpl;
                f10 += fM3234getDistanceimpl;
            }
        }
        if (f10 == 0.0f) {
            return 0.0f;
        }
        return f11 / f10;
    }

    public static final float calculateZoom(PointerEvent pointerEvent) {
        float fCalculateCentroidSize = calculateCentroidSize(pointerEvent, true);
        float fCalculateCentroidSize2 = calculateCentroidSize(pointerEvent, false);
        if (fCalculateCentroidSize == 0.0f || fCalculateCentroidSize2 == 0.0f) {
            return 1.0f;
        }
        return fCalculateCentroidSize / fCalculateCentroidSize2;
    }

    public static final Object detectTransformGestures(PointerInputScope pointerInputScope, boolean z, r<? super Offset, ? super Offset, ? super Float, ? super Float, t0> rVar, d7.d<? super t0> dVar) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new AnonymousClass2(z, rVar, null), dVar);
        return objAwaitEachGesture == e7.a.f15033i ? objAwaitEachGesture : t0.f22605a;
    }

    public static /* synthetic */ Object detectTransformGestures$default(PointerInputScope pointerInputScope, boolean z, r rVar, d7.d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        return detectTransformGestures(pointerInputScope, z, rVar, dVar);
    }
}
