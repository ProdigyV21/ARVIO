package com.arflix.tv.data.telegram;

import android.content.Context;
import android.util.Log;
import com.arflix.tv.R;
import com.arflix.tv.data.telegram.TelegramAuthState;
import d7.h;
import dagger.hilt.android.qualifiers.ApplicationContext;
import f7.j;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.k0;
import ka.l;
import ka.l0;
import ka.m0;
import ka.x0;
import kotlin.Metadata;
import kotlin.text.o;
import l7.i;
import na.h1;
import na.j1;
import na.q0;
import na.y0;
import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;
import r7.p;
import x6.c0;
import x6.i0;
import x6.s;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u0000 =2\u00020\u0001:\u0001=B\u0013\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\b¢\u0006\u0004\b\u0015\u0010\fJ\r\u0010\u0016\u001a\u00020\u0006¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\b¢\u0006\u0004\b\u001a\u0010\fJ\u0015\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u0006¢\u0006\u0004\b\u001c\u0010\nJ\u0015\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u0006¢\u0006\u0004\b\u001e\u0010\nJ\u0015\u0010 \u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u0006¢\u0006\u0004\b \u0010\nJ,\u0010%\u001a\u0004\u0018\u00010\r2\u000e\u0010\"\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0!2\b\b\u0002\u0010$\u001a\u00020#H\u0086@¢\u0006\u0004\b%\u0010&J\r\u0010'\u001a\u00020\b¢\u0006\u0004\b'\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010(R\u0014\u0010*\u001a\u00020)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020-0,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\u001d\u00101\u001a\b\u0012\u0004\u0012\u00020-008\u0006¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R\u0018\u00106\u001a\u0004\u0018\u0001058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u001b\u0010;\u001a\u0002088FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<¨\u0006>"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramClient;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "step", "Lx6/t0;", "stepLog", "(Ljava/lang/String;)V", "sendTdlibParameters", "()V", "Lorg/drinkless/tdlib/TdApi$Object;", "obj", "handleUpdate", "(Lorg/drinkless/tdlib/TdApi$Object;)V", "Lorg/drinkless/tdlib/TdApi$AuthorizationState;", "state", "handleAuthState", "(Lorg/drinkless/tdlib/TdApi$AuthorizationState;)V", "initialize", "readInitLog", "()Ljava/lang/String;", "clearInitLog", "()Ljava/lang/Object;", "requestQrCode", "phone", "submitPhone", "code", "submitCode", "password", "submitPassword", "Lorg/drinkless/tdlib/TdApi$Function;", "function", "", "timeoutMs", "sendRequest", "(Lorg/drinkless/tdlib/TdApi$Function;JLd7/d;)Ljava/lang/Object;", "reset", "Landroid/content/Context;", "Lka/k0;", "scope", "Lka/k0;", "Lna/q0;", "Lcom/arflix/tv/data/telegram/TelegramAuthState;", "_authState", "Lna/q0;", "Lna/h1;", "authState", "Lna/h1;", "getAuthState", "()Lna/h1;", "Lorg/drinkless/tdlib/Client;", "client", "Lorg/drinkless/tdlib/Client;", "", "isAvailable$delegate", "Lx6/s;", "isAvailable", "()Z", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TelegramClient {
    private static final String TAG = "TelegramClient";
    private final q0<TelegramAuthState> _authState;
    private final h1<TelegramAuthState> authState;
    private Client client;
    private final Context context;

    /* JADX INFO: renamed from: isAvailable$delegate, reason: from kotlin metadata */
    private final s isAvailable;
    private final k0 scope = l0.a(h.a(m0.d(), x0.f19655d));
    public static final int $stable = 8;

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramClient$handleAuthState$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramClient$handleAuthState$1", f = "TelegramClient.kt", l = {162}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ Client $readyClient;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Client client, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$readyClient = client;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return TelegramClient.this.new AnonymousClass1(this.$readyClient, dVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:41:0x008b A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:42:0x008c  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) throws java.io.IOException {
            /*
                r11 = this;
                int r0 = r11.label
                java.lang.String r1 = ""
                r2 = 1
                x6.t0 r3 = x6.t0.f22605a
                if (r0 == 0) goto L1d
                if (r0 != r2) goto L15
                k2.c.G(r12)     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L10
                r8 = r11
                goto L37
            L10:
                r0 = move-exception
                r12 = r0
                r8 = r11
                goto L81
            L15:
                java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r12.<init>(r0)
                throw r12
            L1d:
                k2.c.G(r12)
                com.arflix.tv.data.telegram.TelegramClient r4 = com.arflix.tv.data.telegram.TelegramClient.this     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L7e
                org.drinkless.tdlib.TdApi$GetMe r5 = new org.drinkless.tdlib.TdApi$GetMe     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L7e
                r5.<init>()     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L7e
                r11.label = r2     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L7e
                r6 = 0
                r9 = 2
                r10 = 0
                r8 = r11
                java.lang.Object r12 = com.arflix.tv.data.telegram.TelegramClient.sendRequest$default(r4, r5, r6, r8, r9, r10)     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                e7.a r0 = e7.a.f15033i
                if (r12 != r0) goto L37
                return r0
            L37:
                boolean r0 = r12 instanceof org.drinkless.tdlib.TdApi.User     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                if (r0 == 0) goto L41
                org.drinkless.tdlib.TdApi$User r12 = (org.drinkless.tdlib.TdApi.User) r12     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                goto L42
            L3e:
                r0 = move-exception
            L3f:
                r12 = r0
                goto L81
            L41:
                r12 = 0
            L42:
                com.arflix.tv.data.telegram.TelegramClient r0 = com.arflix.tv.data.telegram.TelegramClient.this     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                org.drinkless.tdlib.Client r0 = com.arflix.tv.data.telegram.TelegramClient.access$getClient$p(r0)     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                org.drinkless.tdlib.Client r4 = r8.$readyClient     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                if (r0 == r4) goto L4d
                return r3
            L4d:
                java.io.File r0 = new java.io.File     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                com.arflix.tv.data.telegram.TelegramClient r4 = com.arflix.tv.data.telegram.TelegramClient.this     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                android.content.Context r4 = com.arflix.tv.data.telegram.TelegramClient.access$getContext$p(r4)     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                java.io.File r4 = r4.getFilesDir()     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                java.lang.String r5 = "tdlib_session_ok"
                r0.<init>(r4, r5)     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                r0.createNewFile()     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                com.arflix.tv.data.telegram.TelegramClient r0 = com.arflix.tv.data.telegram.TelegramClient.this     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                na.q0 r0 = com.arflix.tv.data.telegram.TelegramClient.access$get_authState$p(r0)     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                com.arflix.tv.data.telegram.TelegramAuthState$Ready r4 = new com.arflix.tv.data.telegram.TelegramAuthState$Ready     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                if (r12 == 0) goto L6f
                java.lang.String r5 = r12.firstName     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                if (r5 != 0) goto L70
            L6f:
                r5 = r1
            L70:
                if (r12 == 0) goto L75
                long r6 = r12.id     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                goto L77
            L75:
                r6 = 0
            L77:
                r4.<init>(r5, r6)     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                r0.setValue(r4)     // Catch: com.arflix.tv.data.telegram.TelegramApiException -> L3e
                goto Lba
            L7e:
                r0 = move-exception
                r8 = r11
                goto L3f
            L81:
                com.arflix.tv.data.telegram.TelegramClient r0 = com.arflix.tv.data.telegram.TelegramClient.this
                org.drinkless.tdlib.Client r0 = com.arflix.tv.data.telegram.TelegramClient.access$getClient$p(r0)
                org.drinkless.tdlib.Client r4 = r8.$readyClient
                if (r0 == r4) goto L8c
                return r3
            L8c:
                java.lang.String r0 = "TelegramClient"
                java.lang.String r4 = "Failed to load the authenticated Telegram user"
                android.util.Log.w(r0, r4, r12)
                com.arflix.tv.data.telegram.TelegramClient r0 = com.arflix.tv.data.telegram.TelegramClient.this
                na.q0 r0 = com.arflix.tv.data.telegram.TelegramClient.access$get_authState$p(r0)
                com.arflix.tv.data.telegram.TelegramAuthState$Error r4 = new com.arflix.tv.data.telegram.TelegramAuthState$Error
                com.arflix.tv.data.telegram.TelegramClient r5 = com.arflix.tv.data.telegram.TelegramClient.this
                android.content.Context r5 = com.arflix.tv.data.telegram.TelegramClient.access$getContext$p(r5)
                int r6 = com.arflix.tv.R.string.telegram_error_raw
                java.lang.String r12 = r12.getMessage()
                if (r12 != 0) goto Laa
                goto Lab
            Laa:
                r1 = r12
            Lab:
                java.lang.Object[] r12 = new java.lang.Object[r2]
                r2 = 0
                r12[r2] = r1
                java.lang.String r12 = r5.getString(r6, r12)
                r4.<init>(r12)
                r0.setValue(r4)
            Lba:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramClient.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramClient$initialize$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramClient$initialize$1", f = "TelegramClient.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C12941 extends j implements p<k0, d7.d<? super t0>, Object> {
        int label;

        public C12941(d7.d<? super C12941> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return TelegramClient.this.new C12941(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            Client client = TelegramClient.this.client;
            t0 t0Var = t0.f22605a;
            if (client != null) {
                return t0Var;
            }
            TelegramClient.this.stepLog("checking library availability");
            if (!TelegramClient.this.isAvailable()) {
                TelegramClient.this._authState.setValue(new TelegramAuthState.Error(TelegramClient.this.context.getString(R.string.telegram_tdlib_unavailable)));
                return t0Var;
            }
            TelegramClient.this.stepLog("library loaded OK");
            TelegramClient.this._authState.setValue(TelegramAuthState.Initializing.INSTANCE);
            final int i10 = 1;
            final int i11 = 0;
            try {
                TelegramClient.this.stepLog("calling Client.create");
                final TelegramClient telegramClient = TelegramClient.this;
                telegramClient.client = Client.create(new Client.ResultHandler() { // from class: com.arflix.tv.data.telegram.b
                    @Override // org.drinkless.tdlib.Client.ResultHandler
                    public final void onResult(TdApi.Object object) {
                        TelegramClient.access$handleUpdate(telegramClient, object);
                    }
                }, new Client.ExceptionHandler() { // from class: com.arflix.tv.data.telegram.c
                    @Override // org.drinkless.tdlib.Client.ExceptionHandler
                    public final void onException(Throwable th) {
                        switch (i11) {
                            case 0:
                                Log.e(TelegramClient.TAG, "Update exception", th);
                                break;
                            default:
                                Log.e(TelegramClient.TAG, "Default exception", th);
                                break;
                        }
                    }
                }, new Client.ExceptionHandler() { // from class: com.arflix.tv.data.telegram.c
                    @Override // org.drinkless.tdlib.Client.ExceptionHandler
                    public final void onException(Throwable th) {
                        switch (i10) {
                            case 0:
                                Log.e(TelegramClient.TAG, "Update exception", th);
                                break;
                            default:
                                Log.e(TelegramClient.TAG, "Default exception", th);
                                break;
                        }
                    }
                });
                TelegramClient.this.stepLog("Client.create returned");
                TelegramClient.this.sendTdlibParameters();
                TelegramClient.this.stepLog("SetTdlibParameters sent");
                return t0Var;
            } catch (Throwable th) {
                Log.e(TelegramClient.TAG, "TDLib Client.create failed", th);
                TelegramClient.this.stepLog("EXCEPTION: " + th.getMessage());
                q0 q0Var = TelegramClient.this._authState;
                Context context = TelegramClient.this.context;
                int i12 = R.string.telegram_tdlib_failed;
                String message = th.getMessage();
                if (message == null) {
                    message = "";
                }
                q0Var.setValue(new TelegramAuthState.Error(context.getString(i12, message)));
                return t0Var;
            }
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C12941) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramClient$sendRequest$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lorg/drinkless/tdlib/TdApi$Object;", "<anonymous>", "(Lka/k0;)Lorg/drinkless/tdlib/TdApi$Object;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramClient$sendRequest$2", f = "TelegramClient.kt", l = {239}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends j implements p<k0, d7.d<? super TdApi.Object>, Object> {
        final /* synthetic */ TdApi.Function<? extends TdApi.Object> $function;
        int I$0;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(TdApi.Function<? extends TdApi.Object> function, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$function = function;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return TelegramClient.this.new AnonymousClass2(this.$function, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            TelegramClient telegramClient = TelegramClient.this;
            final TdApi.Function<? extends TdApi.Object> function = this.$function;
            this.L$0 = telegramClient;
            this.L$1 = function;
            this.I$0 = 0;
            this.label = 1;
            final l lVar = new l(1, t7.a.A(this));
            lVar.s();
            Client client = telegramClient.client;
            if (client == null) {
                lVar.resumeWith(null);
            } else {
                client.send(function, new Client.ResultHandler() { // from class: com.arflix.tv.data.telegram.TelegramClient$sendRequest$2$1$1
                    @Override // org.drinkless.tdlib.Client.ResultHandler
                    public final void onResult(TdApi.Object object) {
                        if (lVar.isActive()) {
                            if (!(object instanceof TdApi.Error)) {
                                lVar.resumeWith(object);
                                return;
                            }
                            TelegramApiException telegramApiExceptionOrNull = TelegramClientKt.toTelegramApiExceptionOrNull((TdApi.Error) object);
                            if (telegramApiExceptionOrNull != null) {
                                lVar.resumeWith(new c0(telegramApiExceptionOrNull));
                                return;
                            }
                            Log.d("TelegramClient", "TDLib request aborted: " + kotlin.jvm.internal.l0.f19747a.b(function.getClass()).r());
                            lVar.resumeWith(null);
                        }
                    }
                });
            }
            Object objR = lVar.r();
            e7.a aVar = e7.a.f15033i;
            return objR == aVar ? aVar : objR;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super TdApi.Object> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public TelegramClient(@ApplicationContext Context context) {
        this.context = context;
        j1 j1VarB = y0.b(TelegramAuthState.Idle.INSTANCE);
        this._authState = j1VarB;
        this.authState = y0.e(j1VarB);
        this.isAvailable = new i0(new a());
    }

    public static final /* synthetic */ void access$handleUpdate(TelegramClient telegramClient, TdApi.Object object) {
        telegramClient.handleUpdate(object);
    }

    private final void handleAuthState(TdApi.AuthorizationState state) {
        Log.d(TAG, "authState -> " + kotlin.jvm.internal.l0.f19747a.b(state.getClass()).r());
        if (state instanceof TdApi.AuthorizationStateWaitTdlibParameters) {
            sendTdlibParameters();
            return;
        }
        if (state instanceof TdApi.AuthorizationStateWaitPhoneNumber) {
            this._authState.setValue(TelegramAuthState.WaitPhone.INSTANCE);
            return;
        }
        if (state instanceof TdApi.AuthorizationStateWaitCode) {
            TdApi.AuthenticationCodeType authenticationCodeType = ((TdApi.AuthorizationStateWaitCode) state).codeInfo.type;
            this._authState.setValue(new TelegramAuthState.WaitCode(authenticationCodeType instanceof TdApi.AuthenticationCodeTypeTelegramMessage ? ((TdApi.AuthenticationCodeTypeTelegramMessage) authenticationCodeType).length : authenticationCodeType instanceof TdApi.AuthenticationCodeTypeSms ? ((TdApi.AuthenticationCodeTypeSms) authenticationCodeType).length : 5));
            return;
        }
        if (state instanceof TdApi.AuthorizationStateWaitOtherDeviceConfirmation) {
            this._authState.setValue(new TelegramAuthState.WaitQr(((TdApi.AuthorizationStateWaitOtherDeviceConfirmation) state).link));
            return;
        }
        if (state instanceof TdApi.AuthorizationStateWaitPassword) {
            this._authState.setValue(TelegramAuthState.WaitPassword.INSTANCE);
            return;
        }
        if (state instanceof TdApi.AuthorizationStateReady) {
            Client client = this.client;
            if (client == null) {
                return;
            }
            m0.p(this.scope, null, 0, new AnonymousClass1(client, null), 3);
            return;
        }
        if ((state instanceof TdApi.AuthorizationStateClosing) || (state instanceof TdApi.AuthorizationStateClosed)) {
            this._authState.setValue(TelegramAuthState.Idle.INSTANCE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleUpdate(TdApi.Object obj) {
        if (obj instanceof TdApi.UpdateAuthorizationState) {
            handleAuthState(((TdApi.UpdateAuthorizationState) obj).authorizationState);
            return;
        }
        if (obj instanceof TdApi.Error) {
            TelegramAuthState telegramAuthState = (TelegramAuthState) this._authState.getValue();
            if ((telegramAuthState instanceof TelegramAuthState.Initializing) || (telegramAuthState instanceof TelegramAuthState.WaitPhone) || (telegramAuthState instanceof TelegramAuthState.WaitQr) || (telegramAuthState instanceof TelegramAuthState.WaitCode) || (telegramAuthState instanceof TelegramAuthState.WaitPassword)) {
                this._authState.setValue(new TelegramAuthState.Error(((TdApi.Error) obj).message));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isAvailable_delegate$lambda$0() {
        try {
            System.loadLibrary("tdjni");
            return true;
        } catch (Throwable th) {
            androidx.compose.material3.d.y("TDLib not available — Telegram integration disabled: ", th.getMessage(), TAG);
            return false;
        }
    }

    public static /* synthetic */ Object sendRequest$default(TelegramClient telegramClient, TdApi.Function function, long j10, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = 10000;
        }
        return telegramClient.sendRequest(function, j10, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendTdlibParameters() {
        String absolutePath = new File(this.context.getFilesDir(), "tdlib").getAbsolutePath();
        String absolutePath2 = new File(this.context.getFilesDir(), "tdlib_files").getAbsolutePath();
        Client client = this.client;
        if (client != null) {
            TdApi.SetTdlibParameters setTdlibParameters = new TdApi.SetTdlibParameters();
            setTdlibParameters.apiId = TelegramConfig.API_ID;
            setTdlibParameters.apiHash = TelegramConfig.API_HASH;
            setTdlibParameters.databaseDirectory = absolutePath;
            setTdlibParameters.filesDirectory = absolutePath2;
            setTdlibParameters.useMessageDatabase = false;
            setTdlibParameters.useSecretChats = false;
            setTdlibParameters.systemLanguageCode = "en";
            setTdlibParameters.deviceModel = "Android TV";
            setTdlibParameters.applicationVersion = "1.0";
            client.send(setTdlibParameters, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stepLog(String step) {
        Log.d(TAG, "STEP: " + step);
        try {
            File file = new File(this.context.getFilesDir(), "tdlib_init_log.txt");
            String str = System.currentTimeMillis() + " " + step + "\n";
            Charset charset = kotlin.text.a.f19924a;
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            try {
                i.w0(fileOutputStream, str, charset);
                fileOutputStream.close();
            } finally {
            }
        } catch (Throwable unused) {
        }
    }

    public final Object clearInitLog() {
        try {
            return Boolean.valueOf(new File(this.context.getFilesDir(), "tdlib_init_log.txt").delete());
        } catch (Throwable unused) {
            return t0.f22605a;
        }
    }

    public final h1<TelegramAuthState> getAuthState() {
        return this.authState;
    }

    public final void initialize() {
        if (this.client != null) {
            return;
        }
        m0.p(this.scope, null, 0, new C12941(null), 3);
    }

    public final boolean isAvailable() {
        return ((Boolean) this.isAvailable.getValue()).booleanValue();
    }

    public final String readInitLog() {
        try {
            return o.J0(2000, i.s0(new File(this.context.getFilesDir(), "tdlib_init_log.txt")));
        } catch (Throwable unused) {
            return "";
        }
    }

    public final void requestQrCode() {
        Client client = this.client;
        if (client != null) {
            client.send(new TdApi.RequestQrCodeAuthentication(new long[0]), null);
        }
    }

    public final void reset() {
        Client client = this.client;
        if (client != null) {
            client.send(new TdApi.Close(), null);
        }
        this.client = null;
        this._authState.setValue(TelegramAuthState.Idle.INSTANCE);
    }

    public final Object sendRequest(TdApi.Function<? extends TdApi.Object> function, long j10, d7.d<? super TdApi.Object> dVar) {
        return m0.A(j10, new AnonymousClass2(function, null), dVar);
    }

    public final void submitCode(String code) {
        Client client = this.client;
        if (client != null) {
            client.send(new TdApi.CheckAuthenticationCode(code), null);
        }
    }

    public final void submitPassword(String password) {
        Client client = this.client;
        if (client != null) {
            client.send(new TdApi.CheckAuthenticationPassword(password), null);
        }
    }

    public final void submitPhone(String phone) {
        Client client = this.client;
        if (client != null) {
            client.send(new TdApi.SetAuthenticationPhoneNumber(phone, null), null);
        }
    }
}
