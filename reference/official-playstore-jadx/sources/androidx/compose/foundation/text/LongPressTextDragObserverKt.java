package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import com.google.android.gms.cast.MediaError;
import d7.d;
import e7.a;
import f7.e;
import f7.h;
import f7.j;
import k2.c;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.v1;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001c\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0080@¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0080@¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010\u0007\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0082@¢\u0006\u0004\b\u0007\u0010\u0005\u001a\u001c\u0010\b\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0082@¢\u0006\u0004\b\b\u0010\u0005¨\u0006\t"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Landroidx/compose/foundation/text/TextDragObserver;", "observer", "Lx6/t0;", "detectDragGesturesAfterLongPressWithObserver", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/text/TextDragObserver;Ld7/d;)Ljava/lang/Object;", "detectDownAndDragGesturesWithObserver", "detectPreDragGesturesWithObserver", "detectDragGesturesWithObserver", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LongPressTextDragObserverKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDownAndDragGesturesWithObserver$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lka/v1;", "<anonymous>", "(Lka/k0;)Lka/v1;"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDownAndDragGesturesWithObserver$2", f = "LongPressTextDragObserver.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<? super v1>, Object> {
        final /* synthetic */ TextDragObserver $observer;
        final /* synthetic */ PointerInputScope $this_detectDownAndDragGesturesWithObserver;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDownAndDragGesturesWithObserver$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDownAndDragGesturesWithObserver$2$1", f = "LongPressTextDragObserver.kt", l = {83}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ TextDragObserver $observer;
            final /* synthetic */ PointerInputScope $this_detectDownAndDragGesturesWithObserver;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.$this_detectDownAndDragGesturesWithObserver = pointerInputScope;
                this.$observer = textDragObserver;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new AnonymousClass1(this.$this_detectDownAndDragGesturesWithObserver, this.$observer, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    PointerInputScope pointerInputScope = this.$this_detectDownAndDragGesturesWithObserver;
                    TextDragObserver textDragObserver = this.$observer;
                    this.label = 1;
                    Object objDetectPreDragGesturesWithObserver = LongPressTextDragObserverKt.detectPreDragGesturesWithObserver(pointerInputScope, textDragObserver, this);
                    a aVar = a.f15033i;
                    if (objDetectPreDragGesturesWithObserver == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDownAndDragGesturesWithObserver$2$2, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDownAndDragGesturesWithObserver$2$2", f = "LongPressTextDragObserver.kt", l = {86}, m = "invokeSuspend")
        public static final class C00452 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ TextDragObserver $observer;
            final /* synthetic */ PointerInputScope $this_detectDownAndDragGesturesWithObserver;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00452(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, d<? super C00452> dVar) {
                super(2, dVar);
                this.$this_detectDownAndDragGesturesWithObserver = pointerInputScope;
                this.$observer = textDragObserver;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new C00452(this.$this_detectDownAndDragGesturesWithObserver, this.$observer, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    PointerInputScope pointerInputScope = this.$this_detectDownAndDragGesturesWithObserver;
                    TextDragObserver textDragObserver = this.$observer;
                    this.label = 1;
                    Object objDetectDragGesturesWithObserver = LongPressTextDragObserverKt.detectDragGesturesWithObserver(pointerInputScope, textDragObserver, this);
                    a aVar = a.f15033i;
                    if (objDetectDragGesturesWithObserver == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((C00452) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$this_detectDownAndDragGesturesWithObserver = pointerInputScope;
            this.$observer = textDragObserver;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_detectDownAndDragGesturesWithObserver, this.$observer, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            k0 k0Var = (k0) this.L$0;
            m0.p(k0Var, null, 4, new AnonymousClass1(this.$this_detectDownAndDragGesturesWithObserver, this.$observer, null), 1);
            return m0.p(k0Var, null, 4, new C00452(this.$this_detectDownAndDragGesturesWithObserver, this.$observer, null), 1);
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super v1> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDragGesturesAfterLongPressWithObserver$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03832 extends r implements l<Offset, t0> {
        final /* synthetic */ TextDragObserver $observer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03832(TextDragObserver textDragObserver) {
            super(1);
            this.$observer = textDragObserver;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m837invokek4lQ0M(((Offset) obj).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final void m837invokek4lQ0M(long j10) {
            this.$observer.mo877onStartk4lQ0M(j10);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDragGesturesAfterLongPressWithObserver$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements r7.a<t0> {
        final /* synthetic */ TextDragObserver $observer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(TextDragObserver textDragObserver) {
            super(0);
            this.$observer = textDragObserver;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m838invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m838invoke() {
            this.$observer.onStop();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDragGesturesAfterLongPressWithObserver$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements r7.a<t0> {
        final /* synthetic */ TextDragObserver $observer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(TextDragObserver textDragObserver) {
            super(0);
            this.$observer = textDragObserver;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m839invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m839invoke() {
            this.$observer.onCancel();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDragGesturesAfterLongPressWithObserver$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "<anonymous parameter 0>", "Landroidx/compose/ui/geometry/Offset;", "offset", "Lx6/t0;", "invoke-Uv8p0NA", "(Landroidx/compose/ui/input/pointer/PointerInputChange;J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass5 extends r implements p<PointerInputChange, Offset, t0> {
        final /* synthetic */ TextDragObserver $observer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(TextDragObserver textDragObserver) {
            super(2);
            this.$observer = textDragObserver;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            m840invokeUv8p0NA((PointerInputChange) obj, ((Offset) obj2).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
        public final void m840invokeUv8p0NA(PointerInputChange pointerInputChange, long j10) {
            this.$observer.mo876onDragk4lQ0M(j10);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDragGesturesWithObserver$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03842 extends r implements l<Offset, t0> {
        final /* synthetic */ TextDragObserver $observer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03842(TextDragObserver textDragObserver) {
            super(1);
            this.$observer = textDragObserver;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m841invokek4lQ0M(((Offset) obj).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final void m841invokek4lQ0M(long j10) {
            this.$observer.mo877onStartk4lQ0M(j10);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDragGesturesWithObserver$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03853 extends r implements r7.a<t0> {
        final /* synthetic */ TextDragObserver $observer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03853(TextDragObserver textDragObserver) {
            super(0);
            this.$observer = textDragObserver;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m842invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m842invoke() {
            this.$observer.onStop();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDragGesturesWithObserver$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03864 extends r implements r7.a<t0> {
        final /* synthetic */ TextDragObserver $observer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03864(TextDragObserver textDragObserver) {
            super(0);
            this.$observer = textDragObserver;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m843invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m843invoke() {
            this.$observer.onCancel();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDragGesturesWithObserver$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "<anonymous parameter 0>", "Landroidx/compose/ui/geometry/Offset;", "offset", "Lx6/t0;", "invoke-Uv8p0NA", "(Landroidx/compose/ui/input/pointer/PointerInputChange;J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03875 extends r implements p<PointerInputChange, Offset, t0> {
        final /* synthetic */ TextDragObserver $observer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03875(TextDragObserver textDragObserver) {
            super(2);
            this.$observer = textDragObserver;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            m844invokeUv8p0NA((PointerInputChange) obj, ((Offset) obj2).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
        public final void m844invokeUv8p0NA(PointerInputChange pointerInputChange, long j10) {
            this.$observer.mo876onDragk4lQ0M(j10);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.LongPressTextDragObserverKt$detectPreDragGesturesWithObserver$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text.LongPressTextDragObserverKt$detectPreDragGesturesWithObserver$2", f = "LongPressTextDragObserver.kt", l = {99, MediaError.DetailedErrorCode.MEDIA_NETWORK}, m = "invokeSuspend")
    public static final class C03882 extends h implements p<AwaitPointerEventScope, d<? super t0>, Object> {
        final /* synthetic */ TextDragObserver $observer;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03882(TextDragObserver textDragObserver, d<? super C03882> dVar) {
            super(2, dVar);
            this.$observer = textDragObserver;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C03882 c03882 = new C03882(this.$observer, dVar);
            c03882.L$0 = obj;
            return c03882;
        }

        @Override // r7.p
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d<? super t0> dVar) {
            return ((C03882) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x005b, code lost:
        
            if (r14 != r3) goto L18;
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x005b -> B:18:0x005e). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                r13 = this;
                int r0 = r13.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L2a
                if (r0 == r2) goto L21
                if (r0 != r1) goto L19
                java.lang.Object r0 = r13.L$1
                androidx.compose.ui.input.pointer.PointerInputChange r0 = (androidx.compose.ui.input.pointer.PointerInputChange) r0
                java.lang.Object r4 = r13.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r4 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r4
                k2.c.G(r14)
                r7 = r13
                goto L5e
            L19:
                java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r14.<init>(r0)
                throw r14
            L21:
                java.lang.Object r0 = r13.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                k2.c.G(r14)
                r7 = r13
                goto L43
            L2a:
                k2.c.G(r14)
                java.lang.Object r14 = r13.L$0
                r4 = r14
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r4 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r4
                r13.L$0 = r4
                r13.label = r2
                r5 = 0
                r6 = 0
                r8 = 2
                r9 = 0
                r7 = r13
                java.lang.Object r14 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown$default(r4, r5, r6, r7, r8, r9)
                if (r14 != r3) goto L42
                goto L5d
            L42:
                r0 = r4
            L43:
                androidx.compose.ui.input.pointer.PointerInputChange r14 = (androidx.compose.ui.input.pointer.PointerInputChange) r14
                androidx.compose.foundation.text.TextDragObserver r4 = r7.$observer
                long r5 = r14.getPosition()
                r4.mo875onDownk4lQ0M(r5)
                r4 = r0
                r0 = r14
            L50:
                r7.L$0 = r4
                r7.L$1 = r0
                r7.label = r1
                r14 = 0
                java.lang.Object r14 = androidx.compose.ui.input.pointer.a.t(r4, r14, r13, r2, r14)
                if (r14 != r3) goto L5e
            L5d:
                return r3
            L5e:
                androidx.compose.ui.input.pointer.PointerEvent r14 = (androidx.compose.ui.input.pointer.PointerEvent) r14
                java.util.List r14 = r14.getChanges()
                int r5 = r14.size()
                r6 = 0
            L69:
                if (r6 >= r5) goto L89
                java.lang.Object r8 = r14.get(r6)
                androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
                long r9 = r8.getId()
                long r11 = r0.getId()
                boolean r9 = androidx.compose.ui.input.pointer.PointerId.m4500equalsimpl0(r9, r11)
                if (r9 == 0) goto L86
                boolean r8 = r8.getPressed()
                if (r8 == 0) goto L86
                goto L50
            L86:
                int r6 = r6 + 1
                goto L69
            L89:
                androidx.compose.foundation.text.TextDragObserver r14 = r7.$observer
                r14.onUp()
                x6.t0 r14 = x6.t0.f22605a
                return r14
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.LongPressTextDragObserverKt.C03882.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final Object detectDownAndDragGesturesWithObserver(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, d<? super t0> dVar) {
        Object objC = l0.c(new AnonymousClass2(pointerInputScope, textDragObserver, null), dVar);
        return objC == a.f15033i ? objC : t0.f22605a;
    }

    public static final Object detectDragGesturesAfterLongPressWithObserver(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, d<? super t0> dVar) {
        Object objDetectDragGesturesAfterLongPress = DragGestureDetectorKt.detectDragGesturesAfterLongPress(pointerInputScope, new C03832(textDragObserver), new AnonymousClass3(textDragObserver), new AnonymousClass4(textDragObserver), new AnonymousClass5(textDragObserver), dVar);
        return objDetectDragGesturesAfterLongPress == a.f15033i ? objDetectDragGesturesAfterLongPress : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object detectDragGesturesWithObserver(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, d<? super t0> dVar) {
        Object objDetectDragGestures = DragGestureDetectorKt.detectDragGestures(pointerInputScope, new C03842(textDragObserver), new C03853(textDragObserver), new C03864(textDragObserver), new C03875(textDragObserver), dVar);
        return objDetectDragGestures == a.f15033i ? objDetectDragGestures : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object detectPreDragGesturesWithObserver(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, d<? super t0> dVar) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new C03882(textDragObserver, null), dVar);
        return objAwaitEachGesture == a.f15033i ? objAwaitEachGesture : t0.f22605a;
    }
}
