package androidx.compose.foundation.gestures;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a-\u0010\u0006\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001aC\u0010\u0006\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0006\u0010\u000b\u001a>\u0010\u0012\u001a\u00020\u0011*\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\bH\u0082@¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/Modifier;", "Landroidx/compose/foundation/gestures/TransformableState;", "state", "", "lockRotationOnZoomPan", "enabled", "transformable", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/gestures/TransformableState;ZZ)Landroidx/compose/ui/Modifier;", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "canPan", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/gestures/TransformableState;Lr7/l;ZZ)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "panZoomLock", "Lma/j;", "Landroidx/compose/foundation/gestures/TransformEvent;", "channel", "Lx6/t0;", "detectZoom", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;ZLma/j;Lr7/l;Ld7/d;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TransformableKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableKt$detectZoom$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.foundation.gestures.TransformableKt", f = "Transformable.kt", l = {216, 218, 262}, m = "detectZoom")
    public static final class AnonymousClass1 extends f7.c {
        float F$0;
        float F$1;
        float F$2;
        int I$0;
        int I$1;
        int I$2;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TransformableKt.detectZoom(null, false, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableKt$transformable$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/ui/geometry/Offset;", "invoke-k-4lQ0M", "(J)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C03271 extends r implements l<Offset, Boolean> {
        public static final C03271 INSTANCE = new C03271();

        public C03271() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return m391invokek4lQ0M(((Offset) obj).getPackedValue());
        }

        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final Boolean m391invokek4lQ0M(long j10) {
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x02ab, code lost:
    
        if (r4 != 0) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x02ad, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02b2 A[EDGE_INSN: B:103:0x02b2->B:92:0x02b2 BREAK  A[LOOP:0: B:86:0x029d->B:91:0x02af], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x014d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0016  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02c2  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:84:0x027e -> B:85:0x0290). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object detectZoom(androidx.compose.ui.input.pointer.AwaitPointerEventScope r28, boolean r29, ma.j<androidx.compose.foundation.gestures.TransformEvent> r30, r7.l<? super androidx.compose.ui.geometry.Offset, java.lang.Boolean> r31, d7.d<? super x6.t0> r32) {
        /*
            Method dump skipped, instruction units count: 730
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TransformableKt.detectZoom(androidx.compose.ui.input.pointer.AwaitPointerEventScope, boolean, ma.j, r7.l, d7.d):java.lang.Object");
    }

    public static final Modifier transformable(Modifier modifier, TransformableState transformableState, boolean z, boolean z5) {
        return transformable(modifier, transformableState, C03271.INSTANCE, z, z5);
    }

    public static /* synthetic */ Modifier transformable$default(Modifier modifier, TransformableState transformableState, boolean z, boolean z5, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        if ((i10 & 4) != 0) {
            z5 = true;
        }
        return transformable(modifier, transformableState, z, z5);
    }

    public static final Modifier transformable(Modifier modifier, TransformableState transformableState, l<? super Offset, Boolean> lVar, boolean z, boolean z5) {
        return modifier.then(new TransformableElement(transformableState, lVar, z, z5));
    }

    public static /* synthetic */ Modifier transformable$default(Modifier modifier, TransformableState transformableState, l lVar, boolean z, boolean z5, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z = false;
        }
        if ((i10 & 8) != 0) {
            z5 = true;
        }
        return transformable(modifier, transformableState, lVar, z, z5);
    }
}
