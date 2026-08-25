package com.arflix.tv.ui.screens.player;

import android.util.Log;
import androidx.media3.common.C;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\n\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u0010\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\t¢\u0006\u0004\b\u0012\u0010\u000bJ\u000f\u0010\u0013\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\tH\u0002¢\u0006\u0004\b\u001c\u0010\u000bJ\u000f\u0010\u001d\u001a\u00020\tH\u0002¢\u0006\u0004\b\u001d\u0010\u000bJ\u000f\u0010\u001e\u001a\u00020\tH\u0002¢\u0006\u0004\b\u001e\u0010\u000bJ\u000f\u0010\u001f\u001a\u00020\tH\u0002¢\u0006\u0004\b\u001f\u0010\u000bJ\u000f\u0010 \u001a\u00020\tH\u0002¢\u0006\u0004\b \u0010\u000bJ\u000f\u0010!\u001a\u00020\tH\u0002¢\u0006\u0004\b!\u0010\u000bJ\u000f\u0010\"\u001a\u00020\tH\u0002¢\u0006\u0004\b\"\u0010\u000bR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010#R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010$R\"\u0010%\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010\u0014\"\u0004\b(\u0010\u001bR\u001c\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u001f\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030,8\u0006¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100R\u001a\u00102\u001a\b\u0012\u0004\u0012\u0002010)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u0010+R\u001d\u00103\u001a\b\u0012\u0004\u0012\u0002010,8\u0006¢\u0006\f\n\u0004\b3\u0010.\u001a\u0004\b4\u00100R\u001c\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u0010+R\u001f\u00106\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030,8\u0006¢\u0006\f\n\u0004\b6\u0010.\u001a\u0004\b7\u00100R\u0018\u00109\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b9\u0010:R\u001a\u0010<\u001a\b\u0012\u0004\u0012\u00020\f0;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=R\u0018\u0010?\u001a\u0004\u0018\u00010>8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b?\u0010@R\u0018\u0010A\u001a\u0004\u0018\u00010>8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010@R\u0016\u0010B\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bB\u0010CR\u0016\u0010E\u001a\u00020D8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bE\u0010FR\u0016\u0010G\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bG\u0010CR\u001a\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00030H8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bI\u0010JR\u001a\u0010M\u001a\u00060Kj\u0002`L8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bM\u0010NR\u0018\u0010O\u001a\u0004\u0018\u00010>8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bO\u0010@R\u0014\u0010Q\u001a\u00020P8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bQ\u0010R¨\u0006S"}, d2 = {"Lcom/arflix/tv/ui/screens/player/GeminiLiveTranslationService;", "", "Lkotlin/Function0;", "", "apiKeyProvider", "Lka/k0;", "scope", "<init>", "(Lr7/a;Lka/k0;)V", "Lx6/t0;", "connect", "()V", "", "pcm16Bytes", "", "captureTimeMs", "sendAudioChunk", "([BJ)V", "disconnect", "buildSetupMessage", "()Ljava/lang/String;", "Lorg/json/JSONObject;", "content", "extractModelTurnText", "(Lorg/json/JSONObject;)Ljava/lang/String;", "raw", "handleMessage", "(Ljava/lang/String;)V", "onTurnComplete", "extractCompletedSentences", "startDrainer", "scheduleClear", "resetSegmentation", "startSender", "stopSender", "Lr7/a;", "Lka/k0;", "targetLanguageCode", "Ljava/lang/String;", "getTargetLanguageCode", "setTargetLanguageCode", "Lna/q0;", "_text", "Lna/q0;", "Lna/h1;", "translatedText", "Lna/h1;", "getTranslatedText", "()Lna/h1;", "Lcom/arflix/tv/ui/screens/player/GeminiLiveState;", "_state", "state", "getState", "_errorMessage", "errorMessage", "getErrorMessage", "Lgb/v0;", "ws", "Lgb/v0;", "Lma/j;", "audioQueue", "Lma/j;", "Lka/v1;", "senderJob", "Lka/v1;", "clearJob", "sentenceStartMs", "J", "", "firstFragmentLogged", "Z", "lastFragmentTimeMs", "Lkotlin/collections/p;", "pendingLines", "Lkotlin/collections/p;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "livePartial", "Ljava/lang/StringBuilder;", "lineJob", "Lgb/h0;", "client", "Lgb/h0;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class GeminiLiveTranslationService {
    public static final int $stable = 8;
    private final na.q0<String> _errorMessage;
    private final na.q0<GeminiLiveState> _state;
    private final na.q0<String> _text;
    private final r7.a<String> apiKeyProvider;
    private final ma.j<byte[]> audioQueue;
    private ka.v1 clearJob;
    private final gb.h0 client;
    private final na.h1<String> errorMessage;
    private boolean firstFragmentLogged;
    private long lastFragmentTimeMs;
    private ka.v1 lineJob;
    private StringBuilder livePartial;
    private final kotlin.collections.p pendingLines;
    private final ka.k0 scope;
    private ka.v1 senderJob;
    private long sentenceStartMs;
    private final na.h1<GeminiLiveState> state;
    private volatile String targetLanguageCode = "he";
    private final na.h1<String> translatedText;
    private gb.v0 ws;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.GeminiLiveTranslationService$scheduleClear$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.player.GeminiLiveTranslationService$scheduleClear$1", f = "GeminiLiveTranslationService.kt", l = {254}, m = "invokeSuspend", v = 2)
    public static final class C14031 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C14031(d7.d<? super C14031> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return GeminiLiveTranslationService.this.new C14031(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                this.label = 1;
                Object objA = ka.s0.a(C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            if (GeminiLiveTranslationService.this.pendingLines.isEmpty() && kotlin.text.o.h0(GeminiLiveTranslationService.this.livePartial)) {
                GeminiLiveTranslationService.this._text.setValue(null);
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14031) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.GeminiLiveTranslationService$startDrainer$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.player.GeminiLiveTranslationService$startDrainer$1", f = "GeminiLiveTranslationService.kt", l = {242}, m = "invokeSuspend", v = 2)
    public static final class C14041 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        Object L$0;
        int label;

        public C14041(d7.d<? super C14041> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return GeminiLiveTranslationService.this.new C14041(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
            k2.c.G(obj);
            while (!GeminiLiveTranslationService.this.pendingLines.isEmpty()) {
                GeminiLiveTranslationService.this._text.setValue((String) GeminiLiveTranslationService.this.pendingLines.removeFirst());
                GeminiLiveTranslationService.this.sentenceStartMs = 0L;
                GeminiLiveTranslationService.this.firstFragmentLogged = false;
                this.L$0 = null;
                this.label = 1;
                Object objA = ka.s0.a(900L, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            }
            String string = kotlin.text.o.L0(GeminiLiveTranslationService.this.livePartial.toString()).toString();
            if (string.length() > 0) {
                GeminiLiveTranslationService.this._text.setValue(string);
            }
            GeminiLiveTranslationService.this.scheduleClear();
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14041) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.GeminiLiveTranslationService$startSender$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.player.GeminiLiveTranslationService$startSender$1", f = "GeminiLiveTranslationService.kt", l = {282}, m = "invokeSuspend", v = 2)
    public static final class C14051 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ kotlin.jvm.internal.i0 $n;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14051(kotlin.jvm.internal.i0 i0Var, d7.d<? super C14051> dVar) {
            super(2, dVar);
            this.$n = i0Var;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return GeminiLiveTranslationService.this.new C14051(this.$n, dVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0031 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x002f -> B:12:0x0032). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) throws org.json.JSONException {
            /*
                r7 = this;
                int r0 = r7.label
                r1 = 1
                if (r0 == 0) goto L17
                if (r0 != r1) goto Lf
                java.lang.Object r0 = r7.L$0
                ma.l r0 = (ma.l) r0
                k2.c.G(r8)
                goto L32
            Lf:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L17:
                k2.c.G(r8)
                com.arflix.tv.ui.screens.player.GeminiLiveTranslationService r8 = com.arflix.tv.ui.screens.player.GeminiLiveTranslationService.this
                ma.j r8 = com.arflix.tv.ui.screens.player.GeminiLiveTranslationService.access$getAudioQueue$p(r8)
                ma.l r8 = r8.iterator()
                r0 = r8
            L25:
                r7.L$0 = r0
                r7.label = r1
                java.lang.Object r8 = r0.a(r7)
                e7.a r2 = e7.a.f15033i
                if (r8 != r2) goto L32
                return r2
            L32:
                java.lang.Boolean r8 = (java.lang.Boolean) r8
                boolean r8 = r8.booleanValue()
                java.lang.String r2 = "GeminiLive"
                if (r8 == 0) goto L9c
                java.lang.Object r8 = r0.next()
                byte[] r8 = (byte[]) r8
                com.arflix.tv.ui.screens.player.GeminiLiveTranslationService r3 = com.arflix.tv.ui.screens.player.GeminiLiveTranslationService.this
                gb.v0 r3 = com.arflix.tv.ui.screens.player.GeminiLiveTranslationService.access$getWs$p(r3)
                if (r3 != 0) goto L4b
                goto L9c
            L4b:
                kotlin.jvm.internal.i0 r4 = r7.$n
                int r5 = r4.f19744i
                int r5 = r5 + r1
                r4.f19744i = r5
                r4 = 3
                if (r5 <= r4) goto L59
                int r4 = r5 % 50
                if (r4 != 0) goto L6a
            L59:
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                java.lang.String r6 = "chunk #"
                r4.<init>(r6)
                r4.append(r5)
                java.lang.String r4 = r4.toString()
                android.util.Log.d(r2, r4)
            L6a:
                r2 = 2
                java.lang.String r8 = android.util.Base64.encodeToString(r8, r2)
                org.json.JSONObject r2 = new org.json.JSONObject
                r2.<init>()
                org.json.JSONObject r4 = new org.json.JSONObject
                r4.<init>()
                org.json.JSONObject r5 = new org.json.JSONObject
                r5.<init>()
                java.lang.String r6 = "data"
                r5.put(r6, r8)
                java.lang.String r8 = "mimeType"
                java.lang.String r6 = "audio/pcm;rate=16000"
                r5.put(r8, r6)
                java.lang.String r8 = "audio"
                r4.put(r8, r5)
                java.lang.String r8 = "realtimeInput"
                r2.put(r8, r4)
                java.lang.String r8 = r2.toString()
                r3.a(r8)
                goto L25
            L9c:
                kotlin.jvm.internal.i0 r8 = r7.$n
                int r8 = r8.f19744i
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "sender ended after "
                r0.<init>(r1)
                r0.append(r8)
                java.lang.String r8 = " chunks"
                r0.append(r8)
                java.lang.String r8 = r0.toString()
                android.util.Log.d(r2, r8)
                x6.t0 r8 = x6.t0.f22605a
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.GeminiLiveTranslationService.C14051.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14051) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    public GeminiLiveTranslationService(r7.a<String> aVar, ka.k0 k0Var) {
        this.apiKeyProvider = aVar;
        this.scope = k0Var;
        na.j1 j1VarB = na.y0.b(null);
        this._text = j1VarB;
        this.translatedText = na.y0.e(j1VarB);
        na.j1 j1VarB2 = na.y0.b(GeminiLiveState.DISCONNECTED);
        this._state = j1VarB2;
        this.state = na.y0.e(j1VarB2);
        na.j1 j1VarB3 = na.y0.b(null);
        this._errorMessage = j1VarB3;
        this.errorMessage = na.y0.e(j1VarB3);
        this.audioQueue = m2.f0.a(Integer.MAX_VALUE, 0, 6);
        this.pendingLines = new kotlin.collections.p();
        this.livePartial = new StringBuilder();
        gb.g0 g0Var = new gb.g0();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        g0Var.b(10L, timeUnit);
        g0Var.e(0L, timeUnit);
        this.client = new gb.h0(g0Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String buildSetupMessage() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("model", "models/gemini-3.5-live-translate-preview");
        JSONObject jSONObject3 = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("TEXT");
        jSONObject3.put("response_modalities", jSONArray);
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("target_language_code", this.targetLanguageCode);
        jSONObject3.put("translation_config", jSONObject4);
        jSONObject2.put("generation_config", jSONObject3);
        jSONObject.put("setup", jSONObject2);
        return jSONObject.toString();
    }

    private final void extractCompletedSentences() {
        String string = this.livePartial.toString();
        int i10 = -1;
        int length = string.length() - 1;
        if (length >= 0) {
            while (true) {
                int i11 = length - 1;
                if (kotlin.collections.r.H(GeminiLiveTranslationServiceKt.SENTENCE_TERMINATORS, string.charAt(length))) {
                    i10 = length;
                    break;
                } else if (i11 < 0) {
                    break;
                } else {
                    length = i11;
                }
            }
        }
        if (i10 < 0) {
            return;
        }
        int i12 = i10 + 1;
        String strSubstring = string.substring(0, i12);
        String strSubstring2 = string.substring(i12);
        Iterator it = GeminiLiveTranslationServiceKt.SENTENCE_SPLIT.h(strSubstring).iterator();
        while (it.hasNext()) {
            String string2 = kotlin.text.o.L0((String) it.next()).toString();
            if (string2.length() > 0) {
                this.pendingLines.addLast(string2);
            }
        }
        this.livePartial = new StringBuilder(strSubstring2);
        while (this.pendingLines.size() > 3) {
            this.pendingLines.removeFirst();
        }
    }

    private final String extractModelTurnText(JSONObject content) {
        JSONArray jSONArrayOptJSONArray;
        JSONObject jSONObjectOptJSONObject = content.optJSONObject("modelTurn");
        if ((jSONObjectOptJSONObject == null && (jSONObjectOptJSONObject = content.optJSONObject("model_turn")) == null) || (jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("parts")) == null) {
            return null;
        }
        StringBuilder sb2 = new StringBuilder();
        int length = jSONArrayOptJSONArray.length();
        for (int i10 = 0; i10 < length; i10++) {
            JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i10);
            String strOptString = jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2.optString("text", "") : null;
            String str = strOptString != null ? strOptString : "";
            if (str.length() > 0) {
                sb2.append(str);
            }
        }
        String string = sb2.toString();
        if (kotlin.text.o.h0(string)) {
            return null;
        }
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleMessage(String raw) {
        try {
            JSONObject jSONObject = new JSONObject(raw);
            if (!jSONObject.has("setupComplete") && !jSONObject.has("setup_complete")) {
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("serverContent");
                if (jSONObjectOptJSONObject == null && (jSONObjectOptJSONObject = jSONObject.optJSONObject("server_content")) == null) {
                    return;
                }
                String strExtractModelTurnText = extractModelTurnText(jSONObjectOptJSONObject);
                if (strExtractModelTurnText == null) {
                    JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("outputTranscription");
                    strExtractModelTurnText = jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2.optString("text", "") : null;
                    if (strExtractModelTurnText == null) {
                        JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject.optJSONObject("output_transcription");
                        strExtractModelTurnText = jSONObjectOptJSONObject3 != null ? jSONObjectOptJSONObject3.optString("text", "") : null;
                    }
                }
                if (strExtractModelTurnText != null && !kotlin.text.o.h0(strExtractModelTurnText)) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    long j10 = this.lastFragmentTimeMs;
                    if (j10 > 0 && jCurrentTimeMillis - j10 > C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS) {
                        resetSegmentation();
                    }
                    this.lastFragmentTimeMs = jCurrentTimeMillis;
                    if (!this.firstFragmentLogged) {
                        long j11 = this.sentenceStartMs;
                        long j12 = j11 > 0 ? jCurrentTimeMillis - j11 : -1L;
                        Log.i("GeminiLive", "⏱ first fragment after " + j12 + "ms | text=\"" + kotlin.text.o.L0(strExtractModelTurnText).toString() + "\"");
                        this.firstFragmentLogged = true;
                    }
                    this.livePartial.append(strExtractModelTurnText);
                    extractCompletedSentences();
                    Log.d("GeminiLive", "live: partial=\"" + kotlin.text.o.L0(this.livePartial.toString()).toString() + "\" queued=" + this.pendingLines.size());
                    startDrainer();
                }
                if (!jSONObjectOptJSONObject.optBoolean("turnComplete", false) && !jSONObjectOptJSONObject.optBoolean("turn_complete", false)) {
                    return;
                }
                onTurnComplete();
                return;
            }
            Log.d("GeminiLive", "setupComplete");
        } catch (Exception e5) {
            androidx.compose.material3.d.y("parse error: ", e5.getMessage(), "GeminiLive");
        }
    }

    private final void onTurnComplete() {
        String string = kotlin.text.o.L0(this.livePartial.toString()).toString();
        if (string.length() > 0) {
            this.pendingLines.addLast(string);
            while (this.pendingLines.size() > 3) {
                this.pendingLines.removeFirst();
            }
            this.livePartial = new StringBuilder();
            startDrainer();
        }
        this.sentenceStartMs = 0L;
        this.firstFragmentLogged = false;
    }

    private final void resetSegmentation() {
        ka.v1 v1Var = this.lineJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        ka.v1 v1Var2 = this.clearJob;
        if (v1Var2 != null) {
            v1Var2.cancel((CancellationException) null);
        }
        this.pendingLines.clear();
        this.livePartial = new StringBuilder();
        this.sentenceStartMs = 0L;
        this.firstFragmentLogged = false;
        this._text.setValue(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleClear() {
        ka.v1 v1Var = this.clearJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.clearJob = ka.m0.p(this.scope, null, 0, new C14031(null), 3);
    }

    private final void startDrainer() {
        ka.v1 v1Var = this.lineJob;
        if (v1Var == null || !v1Var.isActive()) {
            this.lineJob = ka.m0.p(this.scope, null, 0, new C14041(null), 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startSender() {
        Log.d("GeminiLive", "startSender launched");
        this.senderJob = ka.m0.p(this.scope, null, 0, new C14051(new kotlin.jvm.internal.i0(), null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopSender() {
        ka.v1 v1Var = this.senderJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.senderJob = null;
    }

    public final void connect() {
        Log.d("GeminiLive", "connect() state=" + this._state.getValue());
        Object value = this._state.getValue();
        GeminiLiveState geminiLiveState = GeminiLiveState.CONNECTING;
        if (value == geminiLiveState || this._state.getValue() == GeminiLiveState.READY) {
            return;
        }
        String str = (String) this.apiKeyProvider.invoke();
        if (kotlin.text.o.h0(str)) {
            this._state.setValue(GeminiLiveState.ERROR);
            this._errorMessage.setValue("API key missing");
            return;
        }
        this._state.setValue(geminiLiveState);
        this._errorMessage.setValue(null);
        gb.j0 j0Var = new gb.j0();
        j0Var.i("wss://generativelanguage.googleapis.com/ws/google.ai.generativelanguage.v1beta.GenerativeService.BidiGenerateContent?key=".concat(str));
        this.ws = this.client.c(j0Var.b(), new gb.w0() { // from class: com.arflix.tv.ui.screens.player.GeminiLiveTranslationService.connect.1
            @Override // gb.w0
            public void onClosed(gb.v0 socket, int code, String reason) {
                Log.e("GeminiLive", "WS closed code=" + code + " reason=" + reason);
                GeminiLiveTranslationService.this._state.setValue(GeminiLiveState.DISCONNECTED);
                GeminiLiveTranslationService.this.stopSender();
            }

            /* JADX WARN: Removed duplicated region for block: B:8:0x000c  */
            @Override // gb.w0
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onFailure(gb.v0 r4, java.lang.Throwable r5, gb.p0 r6) {
                /*
                    r3 = this;
                    r4 = 0
                    if (r6 == 0) goto Lc
                    gb.s0 r0 = r6.f15786q     // Catch: java.lang.Exception -> Lc
                    if (r0 == 0) goto Lc
                    java.lang.String r0 = r0.x()     // Catch: java.lang.Exception -> Lc
                    goto Ld
                Lc:
                    r0 = r4
                Ld:
                    if (r6 == 0) goto L15
                    int r4 = r6.f15783n
                    java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                L15:
                    java.lang.String r6 = r5.getMessage()
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    java.lang.String r2 = "WS failure code="
                    r1.<init>(r2)
                    r1.append(r4)
                    java.lang.String r4 = " body="
                    r1.append(r4)
                    r1.append(r0)
                    java.lang.String r4 = " err="
                    r1.append(r4)
                    r1.append(r6)
                    java.lang.String r4 = r1.toString()
                    java.lang.String r6 = "GeminiLive"
                    android.util.Log.e(r6, r4)
                    com.arflix.tv.ui.screens.player.GeminiLiveTranslationService r4 = com.arflix.tv.ui.screens.player.GeminiLiveTranslationService.this
                    na.q0 r4 = com.arflix.tv.ui.screens.player.GeminiLiveTranslationService.access$get_state$p(r4)
                    com.arflix.tv.ui.screens.player.GeminiLiveState r6 = com.arflix.tv.ui.screens.player.GeminiLiveState.ERROR
                    r4.setValue(r6)
                    com.arflix.tv.ui.screens.player.GeminiLiveTranslationService r4 = com.arflix.tv.ui.screens.player.GeminiLiveTranslationService.this
                    na.q0 r4 = com.arflix.tv.ui.screens.player.GeminiLiveTranslationService.access$get_errorMessage$p(r4)
                    java.lang.String r5 = r5.getMessage()
                    if (r5 != 0) goto L55
                    java.lang.String r5 = "Connection failed"
                L55:
                    r4.setValue(r5)
                    com.arflix.tv.ui.screens.player.GeminiLiveTranslationService r4 = com.arflix.tv.ui.screens.player.GeminiLiveTranslationService.this
                    com.arflix.tv.ui.screens.player.GeminiLiveTranslationService.access$stopSender(r4)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.GeminiLiveTranslationService.AnonymousClass1.onFailure(gb.v0, java.lang.Throwable, gb.p0):void");
            }

            @Override // gb.w0
            public void onMessage(gb.v0 socket, String text) {
                GeminiLiveTranslationService.this.handleMessage(text);
            }

            @Override // gb.w0
            public void onOpen(gb.v0 socket, gb.p0 response) {
                Log.d("GeminiLive", "WS open, sending setup");
                socket.a(GeminiLiveTranslationService.this.buildSetupMessage());
                GeminiLiveTranslationService.this._state.setValue(GeminiLiveState.READY);
                GeminiLiveTranslationService.this.startSender();
            }

            @Override // gb.w0
            public void onMessage(gb.v0 socket, xb.m bytes) {
                GeminiLiveTranslationService.this.handleMessage(bytes.t());
            }
        });
    }

    public final void disconnect() {
        stopSender();
        ka.v1 v1Var = this.lineJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        ka.v1 v1Var2 = this.clearJob;
        if (v1Var2 != null) {
            v1Var2.cancel((CancellationException) null);
        }
        gb.v0 v0Var = this.ws;
        if (v0Var != null) {
            v0Var.e(1000, "stopped");
        }
        this.ws = null;
        this._state.setValue(GeminiLiveState.DISCONNECTED);
        this._text.setValue(null);
        this.pendingLines.clear();
        this.livePartial = new StringBuilder();
        this.sentenceStartMs = 0L;
        this.firstFragmentLogged = false;
        this.lastFragmentTimeMs = 0L;
    }

    public final na.h1<String> getErrorMessage() {
        return this.errorMessage;
    }

    public final na.h1<GeminiLiveState> getState() {
        return this.state;
    }

    public final String getTargetLanguageCode() {
        return this.targetLanguageCode;
    }

    public final na.h1<String> getTranslatedText() {
        return this.translatedText;
    }

    public final void sendAudioChunk(byte[] pcm16Bytes, long captureTimeMs) {
        if (this._state.getValue() != GeminiLiveState.READY) {
            return;
        }
        if (this.sentenceStartMs == 0) {
            this.sentenceStartMs = System.currentTimeMillis();
            this.firstFragmentLogged = false;
        }
        this.audioQueue.mo6685trySendJP2dKIU(pcm16Bytes);
    }

    public final void setTargetLanguageCode(String str) {
        this.targetLanguageCode = str;
    }
}
