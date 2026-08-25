package com.arflix.tv.data.telegram;

import android.content.Context;
import android.util.Log;
import androidx.work.impl.t;
import com.arflix.tv.data.telegram.TelegramAuthState;
import com.arflix.tv.util.DataStoresKt;
import dagger.hilt.android.qualifiers.ApplicationContext;
import f7.j;
import ga.r;
import java.io.File;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.h;
import l7.i;
import na.h1;
import na.k;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 A2\u00020\u0001:\u0001AB#\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u000fJ\u0015\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0011¢\u0006\u0004\b\u0016\u0010\u0014J\u0015\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u0011¢\u0006\u0004\b\u0018\u0010\u0014J\r\u0010\u0019\u001a\u00020\r¢\u0006\u0004\b\u0019\u0010\u000fJ\r\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\r¢\u0006\u0004\b\u001d\u0010\u000fJ \u0010\"\u001a\b\u0012\u0004\u0012\u00020!0 2\b\b\u0002\u0010\u001f\u001a\u00020\u001eH\u0086@¢\u0006\u0004\b\"\u0010#J0\u0010'\u001a\b\u0012\u0004\u0012\u00020&0 2\u0006\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u001eH\u0086@¢\u0006\u0004\b'\u0010(J(\u0010)\u001a\b\u0012\u0004\u0012\u00020&0 2\u0006\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u001eH\u0086@¢\u0006\u0004\b)\u0010*J\u0015\u0010,\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u001e¢\u0006\u0004\b,\u0010-J\u0019\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0/0.¢\u0006\u0004\b0\u00101J\u001e\u00103\u001a\u00020\r2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u001a0/H\u0086@¢\u0006\u0004\b3\u00104J \u00106\u001a\u00020\r2\u0006\u0010$\u001a\u00020\u001a2\u0006\u00105\u001a\u00020\nH\u0086@¢\u0006\u0004\b6\u00107R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00108R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u00109R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010:R\u001d\u0010=\u001a\b\u0012\u0004\u0012\u00020<0;8\u0006¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@¨\u0006B"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramRepository;", "", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/telegram/TelegramClient;", "client", "Lcom/arflix/tv/data/telegram/TelegramStreamingProxy;", "proxy", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/telegram/TelegramClient;Lcom/arflix/tv/data/telegram/TelegramStreamingProxy;)V", "", "isAuthenticated", "()Z", "Lx6/t0;", "startAuth", "()V", "requestQrCode", "", "phone", "submitPhone", "(Ljava/lang/String;)V", "code", "submitCode", "password", "submitPassword", "disconnect", "", "getCacheSize", "()J", "clearCache", "", "limit", "", "Lcom/arflix/tv/data/telegram/TelegramChat;", "getChats", "(ILd7/d;)Ljava/lang/Object;", "chatId", "query", "Lcom/arflix/tv/data/telegram/TelegramVideoMessage;", "searchVideoMessagesInChat", "(JLjava/lang/String;ILd7/d;)Ljava/lang/Object;", "searchVideoMessages", "(Ljava/lang/String;ILd7/d;)Ljava/lang/Object;", "fileId", "getStreamUrl", "(I)Ljava/lang/String;", "Lna/j;", "", "getExcludedChatIds", "()Lna/j;", "ids", "setExcludedChatIds", "(Ljava/util/Set;Ld7/d;)Ljava/lang/Object;", "exclude", "toggleChatExclusion", "(JZLd7/d;)Ljava/lang/Object;", "Landroid/content/Context;", "Lcom/arflix/tv/data/telegram/TelegramClient;", "Lcom/arflix/tv/data/telegram/TelegramStreamingProxy;", "Lna/h1;", "Lcom/arflix/tv/data/telegram/TelegramAuthState;", "authState", "Lna/h1;", "getAuthState", "()Lna/h1;", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TelegramRepository {
    private static final String TAG = "TelegramRepository";
    private final h1<TelegramAuthState> authState;
    private final TelegramClient client;
    private final Context context;
    private final TelegramStreamingProxy proxy;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final c1.e KEY_EXCLUDED_CHATS = new c1.e("excluded_chat_ids");

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramRepository$Companion;", "", "<init>", "()V", "Landroid/content/Context;", "context", "Ljava/io/File;", "sessionMarker", "(Landroid/content/Context;)Ljava/io/File;", "Lx6/t0;", "wipeTdlibFiles", "(Landroid/content/Context;)V", "", "TAG", "Ljava/lang/String;", "Lc1/e;", "KEY_EXCLUDED_CHATS", "Lc1/e;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final File sessionMarker(Context context) {
            return new File(context.getFilesDir(), "tdlib_session_ok");
        }

        public final void wipeTdlibFiles(Context context) {
            sessionMarker(context).delete();
            i.p0(new File(context.getFilesDir(), "tdlib"));
            i.p0(new File(context.getFilesDir(), "tdlib_files"));
            Log.d(TelegramRepository.TAG, "TDLib database wiped");
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramRepository$getChats$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramRepository", f = "TelegramRepository.kt", l = {96, 101}, m = "getChats", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TelegramRepository.this.getChats(0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramRepository$searchVideoMessages$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramRepository", f = "TelegramRepository.kt", l = {198}, m = "searchVideoMessages", v = 2)
    public static final class C12951 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C12951(d7.d<? super C12951> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TelegramRepository.this.searchVideoMessages(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramRepository$searchVideoMessagesInChat$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramRepository", f = "TelegramRepository.kt", l = {143}, m = "searchVideoMessagesInChat", v = 2)
    public static final class C12961 extends f7.c {
        int I$0;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C12961(d7.d<? super C12961> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TelegramRepository.this.searchVideoMessagesInChat(0L, null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramRepository$setExcludedChatIds$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramRepository$setExcludedChatIds$2", f = "TelegramRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends j implements p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ Set<Long> $ids;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Set<Long> set, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$ids = set;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$ids, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            bVar.d(TelegramRepository.KEY_EXCLUDED_CHATS, x.u0(this.$ids, ",", null, null, null, 62));
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramRepository$toggleChatExclusion$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramRepository", f = "TelegramRepository.kt", l = {267, 269}, m = "toggleChatExclusion", v = 2)
    public static final class C12971 extends f7.c {
        long J$0;
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C12971(d7.d<? super C12971> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TelegramRepository.this.toggleChatExclusion(0L, false, this);
        }
    }

    @Inject
    public TelegramRepository(@ApplicationContext Context context, TelegramClient telegramClient, TelegramStreamingProxy telegramStreamingProxy) {
        this.context = context;
        this.client = telegramClient;
        this.proxy = telegramStreamingProxy;
        this.authState = telegramClient.getAuthState();
        telegramStreamingProxy.start();
        if (INSTANCE.sessionMarker(context).exists()) {
            clearCache();
            telegramClient.initialize();
        } else {
            i.p0(new File(context.getFilesDir(), "tdlib"));
            i.p0(new File(context.getFilesDir(), "tdlib_files"));
        }
    }

    public static /* synthetic */ Object getChats$default(TelegramRepository telegramRepository, int i10, d7.d dVar, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = 200;
        }
        return telegramRepository.getChats(i10, dVar);
    }

    public static /* synthetic */ Object searchVideoMessages$default(TelegramRepository telegramRepository, String str, int i10, d7.d dVar, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = 50;
        }
        return telegramRepository.searchVideoMessages(str, i10, dVar);
    }

    public static /* synthetic */ Object searchVideoMessagesInChat$default(TelegramRepository telegramRepository, long j10, String str, int i10, d7.d dVar, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            i10 = 20;
        }
        return telegramRepository.searchVideoMessagesInChat(j10, str, i10, dVar);
    }

    public final void clearCache() {
        File[] fileArrListFiles = new File(this.context.getFilesDir(), "tdlib_files").listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                i.p0(file);
            }
        }
    }

    public final void disconnect() {
        this.client.reset();
        INSTANCE.wipeTdlibFiles(this.context);
    }

    public final h1<TelegramAuthState> getAuthState() {
        return this.authState;
    }

    public final long getCacheSize() {
        File file = new File(this.context.getFilesDir(), "tdlib_files");
        long length = 0;
        if (file.exists()) {
            ga.h hVar = new ga.h(r.I(new l7.h(file), new d(0)));
            while (hVar.hasNext()) {
                length += ((File) hVar.next()).length();
            }
        }
        return length;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0079, code lost:
    
        if (r2 == r14) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c3, code lost:
    
        if (r2 == r14) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c5, code lost:
    
        return r14;
     */
    /* JADX WARN: Path cross not found for [B:38:0x00d0, B:39:0x00d2], limit reached: 51 */
    /* JADX WARN: Path cross not found for [B:38:0x00d0, B:44:0x00dd], limit reached: 51 */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00c3 -> B:14:0x004c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getChats(int r24, d7.d<? super java.util.List<com.arflix.tv.data.telegram.TelegramChat>> r25) {
        /*
            Method dump skipped, instruction units count: 311
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramRepository.getChats(int, d7.d):java.lang.Object");
    }

    public final na.j<Set<Long>> getExcludedChatIds() {
        final na.j data = DataStoresKt.getTelegramDataStore(this.context).getData();
        return new na.j<Set<? extends Long>>() { // from class: com.arflix.tv.data.telegram.TelegramRepository$getExcludedChatIds$$inlined$map$1

            /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramRepository$getExcludedChatIds$$inlined$map$1$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 3, 0})
            public static final class AnonymousClass2<T> implements k {
                final /* synthetic */ k $this_unsafeFlow;

                /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramRepository$getExcludedChatIds$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
                @f7.e(c = "com.arflix.tv.data.telegram.TelegramRepository$getExcludedChatIds$$inlined$map$1$2", f = "TelegramRepository.kt", l = {223}, m = "emit", v = 2)
                public static final class AnonymousClass1 extends f7.c {
                    int I$0;
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(d7.d dVar) {
                        super(dVar);
                    }

                    @Override // f7.a
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(k kVar) {
                    this.$this_unsafeFlow = kVar;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // na.k
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r6, d7.d r7) {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof com.arflix.tv.data.telegram.TelegramRepository$getExcludedChatIds$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.arflix.tv.data.telegram.TelegramRepository$getExcludedChatIds$$inlined$map$1$2$1 r0 = (com.arflix.tv.data.telegram.TelegramRepository$getExcludedChatIds$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.arflix.tv.data.telegram.TelegramRepository$getExcludedChatIds$$inlined$map$1$2$1 r0 = new com.arflix.tv.data.telegram.TelegramRepository$getExcludedChatIds$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        int r1 = r0.label
                        r2 = 1
                        if (r1 == 0) goto L35
                        if (r1 != r2) goto L2d
                        java.lang.Object r6 = r0.L$3
                        na.k r6 = (na.k) r6
                        java.lang.Object r6 = r0.L$1
                        com.arflix.tv.data.telegram.TelegramRepository$getExcludedChatIds$$inlined$map$1$2$1 r6 = (com.arflix.tv.data.telegram.TelegramRepository$getExcludedChatIds$$inlined$map$1.AnonymousClass2.AnonymousClass1) r6
                        k2.c.G(r7)
                        goto L94
                    L2d:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L35:
                        k2.c.G(r7)
                        na.k r7 = r5.$this_unsafeFlow
                        c1.g r6 = (c1.g) r6
                        c1.e r1 = com.arflix.tv.data.telegram.TelegramRepository.access$getKEY_EXCLUDED_CHATS$cp()
                        c1.b r6 = (c1.b) r6
                        java.util.LinkedHashMap r6 = r6.f7368a
                        java.lang.Object r6 = r6.get(r1)
                        java.lang.String r6 = (java.lang.String) r6
                        r1 = 0
                        if (r6 == 0) goto L7c
                        java.lang.String r3 = ","
                        java.lang.String[] r3 = new java.lang.String[]{r3}
                        r4 = 6
                        java.util.List r6 = kotlin.text.o.y0(r6, r3, r1, r4)
                        java.util.ArrayList r3 = new java.util.ArrayList
                        r3.<init>()
                        java.util.Iterator r6 = r6.iterator()
                    L61:
                        boolean r4 = r6.hasNext()
                        if (r4 == 0) goto L77
                        java.lang.Object r4 = r6.next()
                        java.lang.String r4 = (java.lang.String) r4
                        java.lang.Long r4 = kotlin.text.u.S(r4)
                        if (r4 == 0) goto L61
                        r3.add(r4)
                        goto L61
                    L77:
                        java.util.Set r6 = kotlin.collections.x.g1(r3)
                        goto L7e
                    L7c:
                        kotlin.collections.b0 r6 = kotlin.collections.b0.f19686i
                    L7e:
                        r3 = 0
                        r0.L$0 = r3
                        r0.L$1 = r3
                        r0.L$2 = r3
                        r0.L$3 = r3
                        r0.I$0 = r1
                        r0.label = r2
                        java.lang.Object r6 = r7.emit(r6, r0)
                        e7.a r7 = e7.a.f15033i
                        if (r6 != r7) goto L94
                        return r7
                    L94:
                        x6.t0 r6 = x6.t0.f22605a
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramRepository$getExcludedChatIds$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, d7.d):java.lang.Object");
                }
            }

            @Override // na.j
            public Object collect(k kVar, d7.d dVar) {
                Object objCollect = data.collect(new AnonymousClass2(kVar), dVar);
                return objCollect == e7.a.f15033i ? objCollect : t0.f22605a;
            }
        };
    }

    public final String getStreamUrl(int fileId) {
        return this.proxy.getUrl(fileId);
    }

    public final boolean isAuthenticated() {
        return this.client.getAuthState().getValue() instanceof TelegramAuthState.Ready;
    }

    public final void requestQrCode() {
        this.client.requestQrCode();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01ae A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11, types: [org.drinkless.tdlib.TdApi$FoundMessages] */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [int] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x00bf -> B:21:0x00c1). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object searchVideoMessages(java.lang.String r29, int r30, d7.d<? super java.util.List<com.arflix.tv.data.telegram.TelegramVideoMessage>> r31) {
        /*
            Method dump skipped, instruction units count: 431
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramRepository.searchVideoMessages(java.lang.String, int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x00d0 -> B:21:0x00da). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object searchVideoMessagesInChat(long r32, java.lang.String r34, int r35, d7.d<? super java.util.List<com.arflix.tv.data.telegram.TelegramVideoMessage>> r36) {
        /*
            Method dump skipped, instruction units count: 467
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramRepository.searchVideoMessagesInChat(long, java.lang.String, int, d7.d):java.lang.Object");
    }

    public final Object setExcludedChatIds(Set<Long> set, d7.d<? super t0> dVar) {
        Object objO = t.o(DataStoresKt.getTelegramDataStore(this.context), new AnonymousClass2(set, null), dVar);
        return objO == e7.a.f15033i ? objO : t0.f22605a;
    }

    public final void startAuth() {
        this.client.initialize();
    }

    public final void submitCode(String code) {
        this.client.submitCode(code);
    }

    public final void submitPassword(String password) {
        this.client.submitPassword(password);
    }

    public final void submitPhone(String phone) {
        this.client.submitPhone(phone);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0078, code lost:
    
        if (setExcludedChatIds(r9, r0) == r4) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object toggleChatExclusion(long r6, boolean r8, d7.d<? super x6.t0> r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof com.arflix.tv.data.telegram.TelegramRepository.C12971
            if (r0 == 0) goto L13
            r0 = r9
            com.arflix.tv.data.telegram.TelegramRepository$toggleChatExclusion$1 r0 = (com.arflix.tv.data.telegram.TelegramRepository.C12971) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.telegram.TelegramRepository$toggleChatExclusion$1 r0 = new com.arflix.tv.data.telegram.TelegramRepository$toggleChatExclusion$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3e
            if (r1 == r3) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r6 = r0.L$0
            java.util.Set r6 = (java.util.Set) r6
            k2.c.G(r9)
            goto L7b
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L36:
            boolean r8 = r0.Z$0
            long r6 = r0.J$0
            k2.c.G(r9)
            goto L52
        L3e:
            k2.c.G(r9)
            na.j r9 = r5.getExcludedChatIds()
            r0.J$0 = r6
            r0.Z$0 = r8
            r0.label = r3
            java.lang.Object r9 = na.y0.l(r9, r0)
            if (r9 != r4) goto L52
            goto L7a
        L52:
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.Set r9 = kotlin.collections.x.f1(r9)
            if (r8 == 0) goto L63
            java.lang.Long r1 = new java.lang.Long
            r1.<init>(r6)
            r9.add(r1)
            goto L6b
        L63:
            java.lang.Long r1 = new java.lang.Long
            r1.<init>(r6)
            r9.remove(r1)
        L6b:
            r1 = 0
            r0.L$0 = r1
            r0.J$0 = r6
            r0.Z$0 = r8
            r0.label = r2
            java.lang.Object r6 = r5.setExcludedChatIds(r9, r0)
            if (r6 != r4) goto L7b
        L7a:
            return r4
        L7b:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramRepository.toggleChatExclusion(long, boolean, d7.d):java.lang.Object");
    }
}
