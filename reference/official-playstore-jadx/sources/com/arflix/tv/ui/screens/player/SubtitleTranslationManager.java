package com.arflix.tv.ui.screens.player;

import androidx.compose.ui.graphics.colorspace.ColorSpace;
import io.ktor.http.ContentDisposition;
import j$.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 U2\u00020\u0001:\u0002UVB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nH\u0082@¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0010\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0004¢\u0006\u0004\b\u0016\u0010\u0017J\u0018\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004H\u0086@¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\n¢\u0006\u0004\b\u001a\u0010\u001bJ\u001e\u0010\u001e\u001a\u00020\n2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00040\u001cH\u0086@¢\u0006\u0004\b\u001e\u0010\u001fR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010 R\"\u0010\u0005\u001a\u00020\u00048\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010&R\"\u0010'\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b'\u0010)\"\u0004\b*\u0010+R\"\u0010,\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b,\u0010(\u001a\u0004\b-\u0010)\"\u0004\b.\u0010+R0\u00100\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010/8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105RV\u0010;\u001a6\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b7\u0012\b\b8\u0012\u0004\b\b(9\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b7\u0012\b\b8\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\n\u0018\u0001068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R$\u0010B\u001a\u00020\u00152\u0006\u0010A\u001a\u00020\u00158\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\bB\u0010(\u001a\u0004\bB\u0010)R \u0010D\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040C8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bD\u0010ER&\u0010G\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040F0C8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bG\u0010ER\u0016\u0010I\u001a\u00020H8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bI\u0010JR\u0018\u0010L\u001a\u0004\u0018\u00010K8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bL\u0010MR\u001a\u0010P\u001a\b\u0012\u0004\u0012\u00020O0N8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bP\u0010QR\u0011\u0010T\u001a\u00020H8F¢\u0006\u0006\u001a\u0004\bR\u0010S¨\u0006W"}, d2 = {"Lcom/arflix/tv/ui/screens/player/SubtitleTranslationManager;", "", "Lcom/arflix/tv/ui/screens/player/SubtitleTranslationService;", "service", "", "targetLanguage", "Lka/k0;", "scope", "<init>", "(Lcom/arflix/tv/ui/screens/player/SubtitleTranslationService;Ljava/lang/String;Lka/k0;)V", "Lx6/t0;", "processBatches", "(Ld7/d;)Ljava/lang/Object;", "apiKey", "Lcom/arflix/tv/ui/screens/player/SubtitleAiModel;", "model", "updateService", "(Ljava/lang/String;Lcom/arflix/tv/ui/screens/player/SubtitleAiModel;)V", "text", "getCached", "(Ljava/lang/String;)Ljava/lang/String;", "", "isInFlight", "(Ljava/lang/String;)Z", "translate", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "reset", "()V", "", "texts", "preTranslateWindow", "(Ljava/util/List;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/ui/screens/player/SubtitleTranslationService;", "Ljava/lang/String;", "getTargetLanguage$app_playRelease", "()Ljava/lang/String;", "setTargetLanguage$app_playRelease", "(Ljava/lang/String;)V", "Lka/k0;", "isEnabled", "Z", "()Z", "setEnabled", "(Z)V", "removeHearingImpaired", "getRemoveHearingImpaired", "setRemoveHearingImpaired", "Lkotlin/Function1;", "onTranslatingChanged", "Lr7/l;", "getOnTranslatingChanged", "()Lr7/l;", "setOnTranslatingChanged", "(Lr7/l;)V", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "success", "error", "onBatchResult", "Lr7/p;", "getOnBatchResult", "()Lr7/p;", "setOnBatchResult", "(Lr7/p;)V", "value", "isTranslating", "j$/util/concurrent/ConcurrentHashMap", "cache", "Lj$/util/concurrent/ConcurrentHashMap;", "Lka/r;", "inFlight", "", "pendingCount", "I", "Lka/v1;", "hideTranslatingJob", "Lka/v1;", "Lma/j;", "Lcom/arflix/tv/ui/screens/player/SubtitleTranslationManager$PendingItem;", "queue", "Lma/j;", "getTranslatedCount", "()I", "translatedCount", "Companion", "PendingItem", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SubtitleTranslationManager {
    private static final long BATCH_WINDOW_MS = 150;
    public static final boolean MOCK_MODE = false;
    private ka.v1 hideTranslatingJob;
    private boolean isEnabled;
    private volatile boolean isTranslating;
    private r7.p<? super Boolean, ? super String, x6.t0> onBatchResult;
    private r7.l<? super Boolean, x6.t0> onTranslatingChanged;
    private volatile int pendingCount;
    private final ka.k0 scope;
    private SubtitleTranslationService service;
    private String targetLanguage;
    public static final int $stable = 8;
    private boolean removeHearingImpaired = true;
    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, ka.r<String>> inFlight = new ConcurrentHashMap<>();
    private final ma.j<PendingItem> queue = m2.f0.a(Integer.MAX_VALUE, 0, 6);

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.SubtitleTranslationManager$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.player.SubtitleTranslationManager$1", f = "SubtitleTranslationManager.kt", l = {48}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SubtitleTranslationManager.this.new AnonymousClass1(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SubtitleTranslationManager subtitleTranslationManager = SubtitleTranslationManager.this;
                this.label = 1;
                Object objProcessBatches = subtitleTranslationManager.processBatches(this);
                e7.a aVar = e7.a.f15033i;
                if (objProcessBatches == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ*\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004HÆ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u000e\u0010\tJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\tR\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0019\u0010\u000b¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/ui/screens/player/SubtitleTranslationManager$PendingItem;", "", "", "text", "Lka/r;", "deferred", "<init>", "(Ljava/lang/String;Lka/r;)V", "component1", "()Ljava/lang/String;", "component2", "()Lka/r;", "copy", "(Ljava/lang/String;Lka/r;)Lcom/arflix/tv/ui/screens/player/SubtitleTranslationManager$PendingItem;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getText", "Lka/r;", "getDeferred", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class PendingItem {
        private final ka.r<String> deferred;
        private final String text;

        public PendingItem(String str, ka.r<String> rVar) {
            this.text = str;
            this.deferred = rVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ PendingItem copy$default(PendingItem pendingItem, String str, ka.r rVar, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = pendingItem.text;
            }
            if ((i10 & 2) != 0) {
                rVar = pendingItem.deferred;
            }
            return pendingItem.copy(str, rVar);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getText() {
            return this.text;
        }

        public final ka.r<String> component2() {
            return this.deferred;
        }

        public final PendingItem copy(String text, ka.r<String> deferred) {
            return new PendingItem(text, deferred);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PendingItem)) {
                return false;
            }
            PendingItem pendingItem = (PendingItem) other;
            return kotlin.jvm.internal.p.a(this.text, pendingItem.text) && kotlin.jvm.internal.p.a(this.deferred, pendingItem.deferred);
        }

        public final ka.r<String> getDeferred() {
            return this.deferred;
        }

        public final String getText() {
            return this.text;
        }

        public int hashCode() {
            return this.deferred.hashCode() + (this.text.hashCode() * 31);
        }

        public String toString() {
            return "PendingItem(text=" + this.text + ", deferred=" + this.deferred + ")";
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.SubtitleTranslationManager$preTranslateWindow$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.player.SubtitleTranslationManager", f = "SubtitleTranslationManager.kt", l = {150, 158}, m = "preTranslateWindow", v = 2)
    public static final class C14381 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C14381(d7.d<? super C14381> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubtitleTranslationManager.this.preTranslateWindow(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.SubtitleTranslationManager$processBatches$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.player.SubtitleTranslationManager", f = "SubtitleTranslationManager.kt", l = {ColorSpace.MaxId, 72, 77, 87}, m = "processBatches", v = 2)
    public static final class C14391 extends f7.c {
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C14391(d7.d<? super C14391> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubtitleTranslationManager.this.processBatches(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.SubtitleTranslationManager$translate$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.player.SubtitleTranslationManager", f = "SubtitleTranslationManager.kt", l = {108, 117, 119}, m = "translate", v = 2)
    public static final class C14401 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C14401(d7.d<? super C14401> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubtitleTranslationManager.this.translate(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.SubtitleTranslationManager$translate$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.player.SubtitleTranslationManager$translate$4", f = "SubtitleTranslationManager.kt", l = {124}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass4 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public AnonymousClass4(d7.d<? super AnonymousClass4> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SubtitleTranslationManager.this.new AnonymousClass4(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                this.label = 1;
                Object objA = ka.s0.a(1500L, this);
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
            if (SubtitleTranslationManager.this.pendingCount == 0) {
                SubtitleTranslationManager.this.isTranslating = false;
                r7.l<Boolean, x6.t0> onTranslatingChanged = SubtitleTranslationManager.this.getOnTranslatingChanged();
                if (onTranslatingChanged != null) {
                    onTranslatingChanged.invoke(Boolean.FALSE);
                }
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((AnonymousClass4) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    public SubtitleTranslationManager(SubtitleTranslationService subtitleTranslationService, String str, ka.k0 k0Var) {
        this.service = subtitleTranslationService;
        this.targetLanguage = str;
        this.scope = k0Var;
        ka.m0.p(k0Var, null, 0, new AnonymousClass1(null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00c7 -> B:25:0x0097). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x015c -> B:21:0x0077). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:76:0x01b2 -> B:21:0x0077). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object processBatches(d7.d<? super x6.t0> r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 439
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.SubtitleTranslationManager.processBatches(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String updateService$lambda$0(String str) {
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SubtitleAiModel updateService$lambda$1(SubtitleAiModel subtitleAiModel) {
        return subtitleAiModel;
    }

    public final String getCached(String text) {
        return this.cache.get(text);
    }

    public final r7.p<Boolean, String, x6.t0> getOnBatchResult() {
        return this.onBatchResult;
    }

    public final r7.l<Boolean, x6.t0> getOnTranslatingChanged() {
        return this.onTranslatingChanged;
    }

    public final boolean getRemoveHearingImpaired() {
        return this.removeHearingImpaired;
    }

    /* JADX INFO: renamed from: getTargetLanguage$app_playRelease, reason: from getter */
    public final String getTargetLanguage() {
        return this.targetLanguage;
    }

    public final int getTranslatedCount() {
        return this.cache.size();
    }

    /* JADX INFO: renamed from: isEnabled, reason: from getter */
    public final boolean getIsEnabled() {
        return this.isEnabled;
    }

    public final boolean isInFlight(String text) {
        return this.inFlight.containsKey(text);
    }

    /* JADX INFO: renamed from: isTranslating, reason: from getter */
    public final boolean getIsTranslating() {
        return this.isTranslating;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00dd -> B:38:0x00de). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object preTranslateWindow(java.util.List<java.lang.String> r17, d7.d<? super x6.t0> r18) {
        /*
            Method dump skipped, instruction units count: 332
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.SubtitleTranslationManager.preTranslateWindow(java.util.List, d7.d):java.lang.Object");
    }

    public final void reset() {
        this.cache.clear();
        this.inFlight.clear();
        this.pendingCount = 0;
        this.isTranslating = false;
        r7.l<? super Boolean, x6.t0> lVar = this.onTranslatingChanged;
        if (lVar != null) {
            lVar.invoke(Boolean.FALSE);
        }
    }

    public final void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    public final void setOnBatchResult(r7.p<? super Boolean, ? super String, x6.t0> pVar) {
        this.onBatchResult = pVar;
    }

    public final void setOnTranslatingChanged(r7.l<? super Boolean, x6.t0> lVar) {
        this.onTranslatingChanged = lVar;
    }

    public final void setRemoveHearingImpaired(boolean z) {
        this.removeHearingImpaired = z;
    }

    public final void setTargetLanguage$app_playRelease(String str) {
        this.targetLanguage = str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c4, code lost:
    
        if (r11 == r1) goto L43;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object translate(java.lang.String r10, d7.d<? super java.lang.String> r11) {
        /*
            Method dump skipped, instruction units count: 263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.SubtitleTranslationManager.translate(java.lang.String, d7.d):java.lang.Object");
    }

    public final void updateService(String apiKey, SubtitleAiModel model) {
        this.service = new SubtitleTranslationService(new n1(apiKey, 2), new n1(model, 3));
    }
}
