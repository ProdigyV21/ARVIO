package androidx.compose.ui.platform;

import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.compose.ui.SessionMutex;
import androidx.compose.ui.text.input.TextInputService;
import java.util.concurrent.atomic.AtomicReference;
import ka.k0;
import ka.l0;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0096@¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u00038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0017R\u0014\u0010\u0007\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0018R \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010 \u001a\u00020\u001d8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\"\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\b\"\u0010#\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006$"}, d2 = {"Landroidx/compose/ui/platform/AndroidPlatformTextInputSession;", "Landroidx/compose/ui/platform/PlatformTextInputSessionScope;", "Lka/k0;", "Landroid/view/View;", "view", "Landroidx/compose/ui/text/input/TextInputService;", "textInputService", "coroutineScope", "<init>", "(Landroid/view/View;Landroidx/compose/ui/text/input/TextInputService;Lka/k0;)V", "Landroidx/compose/ui/platform/PlatformTextInputMethodRequest;", "request", "", "startInputMethod", "(Landroidx/compose/ui/platform/PlatformTextInputMethodRequest;Ld7/d;)Ljava/lang/Object;", "Landroid/view/inputmethod/EditorInfo;", "outAttrs", "Landroid/view/inputmethod/InputConnection;", "createInputConnection", "(Landroid/view/inputmethod/EditorInfo;)Landroid/view/inputmethod/InputConnection;", "Landroid/view/View;", "getView", "()Landroid/view/View;", "Landroidx/compose/ui/text/input/TextInputService;", "Lka/k0;", "Landroidx/compose/ui/SessionMutex;", "Landroidx/compose/ui/platform/InputMethodSession;", "methodSessionMutex", "Ljava/util/concurrent/atomic/AtomicReference;", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "coroutineContext", "", "isReadyForConnection", "()Z", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidPlatformTextInputSession implements PlatformTextInputSessionScope, k0 {
    public static final int $stable = 8;
    private final k0 coroutineScope;
    private final AtomicReference methodSessionMutex = SessionMutex.m3112constructorimpl();
    private final TextInputService textInputService;
    private final View view;

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.ui.platform.AndroidPlatformTextInputSession", f = "AndroidPlatformTextInputSession.android.kt", l = {69}, m = "startInputMethod")
    public static final class AnonymousClass1 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidPlatformTextInputSession.this.startInputMethod(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "coroutineScope", "Landroidx/compose/ui/platform/InputMethodSession;", "invoke", "(Lka/k0;)Landroidx/compose/ui/platform/InputMethodSession;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends kotlin.jvm.internal.r implements r7.l<k0, InputMethodSession> {
        final /* synthetic */ PlatformTextInputMethodRequest $request;

        /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements r7.a<t0> {
            final /* synthetic */ k0 $coroutineScope;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(k0 k0Var) {
                super(0);
                this.$coroutineScope = k0Var;
            }

            @Override // r7.a
            public /* bridge */ /* synthetic */ Object invoke() {
                m4941invoke();
                return t0.f22605a;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m4941invoke() {
                l0.b(this.$coroutineScope, null);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(PlatformTextInputMethodRequest platformTextInputMethodRequest) {
            super(1);
            this.$request = platformTextInputMethodRequest;
        }

        @Override // r7.l
        public final InputMethodSession invoke(k0 k0Var) {
            return new InputMethodSession(this.$request, new AnonymousClass1(k0Var));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "methodSession", "Landroidx/compose/ui/platform/InputMethodSession;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$3", f = "AndroidPlatformTextInputSession.android.kt", l = {158}, m = "invokeSuspend")
    public static final class AnonymousClass3 extends f7.j implements r7.p<InputMethodSession, d7.d<?>, Object> {
        /* synthetic */ Object L$0;
        Object L$1;
        int label;

        public AnonymousClass3(d7.d<? super AnonymousClass3> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass3 anonymousClass3 = AndroidPlatformTextInputSession.this.new AnonymousClass3(dVar);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // r7.p
        public final Object invoke(InputMethodSession inputMethodSession, d7.d<?> dVar) {
            return ((AnonymousClass3) create(inputMethodSession, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                InputMethodSession inputMethodSession = (InputMethodSession) this.L$0;
                AndroidPlatformTextInputSession androidPlatformTextInputSession = AndroidPlatformTextInputSession.this;
                this.L$0 = inputMethodSession;
                this.L$1 = androidPlatformTextInputSession;
                this.label = 1;
                ka.l lVar = new ka.l(1, t7.a.A(this));
                lVar.s();
                androidPlatformTextInputSession.textInputService.startInput();
                lVar.c(new AndroidPlatformTextInputSession$startInputMethod$3$1$1(inputMethodSession, androidPlatformTextInputSession));
                Object objR = lVar.r();
                e7.a aVar = e7.a.f15033i;
                if (objR == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    public AndroidPlatformTextInputSession(View view, TextInputService textInputService, k0 k0Var) {
        this.view = view;
        this.textInputService = textInputService;
        this.coroutineScope = k0Var;
    }

    public final InputConnection createInputConnection(EditorInfo outAttrs) {
        InputMethodSession inputMethodSession = (InputMethodSession) SessionMutex.m3116getCurrentSessionimpl(this.methodSessionMutex);
        if (inputMethodSession != null) {
            return inputMethodSession.createInputConnection(outAttrs);
        }
        return null;
    }

    @Override // androidx.compose.ui.platform.PlatformTextInputSessionScope, ka.k0
    public d7.j getCoroutineContext() {
        return this.coroutineScope.getCoroutineContext();
    }

    @Override // androidx.compose.ui.platform.PlatformTextInputSession
    public View getView() {
        return this.view;
    }

    public final boolean isReadyForConnection() {
        InputMethodSession inputMethodSession = (InputMethodSession) SessionMutex.m3116getCurrentSessionimpl(this.methodSessionMutex);
        return inputMethodSession != null && inputMethodSession.isActive();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.compose.ui.platform.PlatformTextInputSession
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object startInputMethod(androidx.compose.ui.platform.PlatformTextInputMethodRequest r5, d7.d<?> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.compose.ui.platform.AndroidPlatformTextInputSession.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$1 r0 = (androidx.compose.ui.platform.AndroidPlatformTextInputSession.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$1 r0 = new androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 == r2) goto L29
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L29:
            k2.c.G(r6)
            goto L48
        L2d:
            k2.c.G(r6)
            java.util.concurrent.atomic.AtomicReference r6 = r4.methodSessionMutex
            androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$2 r1 = new androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$2
            r1.<init>(r5)
            androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$3 r5 = new androidx.compose.ui.platform.AndroidPlatformTextInputSession$startInputMethod$3
            r3 = 0
            r5.<init>(r3)
            r0.label = r2
            java.lang.Object r5 = androidx.compose.ui.SessionMutex.m3119withSessionCancellingPreviousimpl(r6, r1, r5, r0)
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L48
            return r6
        L48:
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidPlatformTextInputSession.startInputMethod(androidx.compose.ui.platform.PlatformTextInputMethodRequest, d7.d):java.lang.Object");
    }
}
