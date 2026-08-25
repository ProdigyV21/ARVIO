package androidx.compose.ui.text.font;

import androidx.compose.material3.d;
import androidx.compose.ui.text.caches.LruCache;
import androidx.compose.ui.text.caches.SimpleArrayMap;
import androidx.compose.ui.text.platform.Synchronization_jvmKt;
import androidx.compose.ui.text.platform.SynchronizedObject;
import f7.e;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import q7.g;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0002&'B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ%\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010JJ\u0010\u0015\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u001e\u0010\u0014\u001a\u001a\b\u0001\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0012H\u0086@¢\u0006\u0004\b\u0015\u0010\u0016J2\u0010\u0018\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u000e\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0017H\u0086\b¢\u0006\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u000e8\u0002X\u0082\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR \u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u000e0\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR \u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u000e0 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006("}, d2 = {"Landroidx/compose/ui/text/font/AsyncTypefaceCache;", "", "<init>", "()V", "Landroidx/compose/ui/text/font/Font;", "font", "Landroidx/compose/ui/text/font/PlatformFontLoader;", "platformFontLoader", "result", "", "forever", "Lx6/t0;", "put", "(Landroidx/compose/ui/text/font/Font;Landroidx/compose/ui/text/font/PlatformFontLoader;Ljava/lang/Object;Z)V", "Landroidx/compose/ui/text/font/AsyncTypefaceCache$AsyncTypefaceResult;", "get-1ASDuI8", "(Landroidx/compose/ui/text/font/Font;Landroidx/compose/ui/text/font/PlatformFontLoader;)Landroidx/compose/ui/text/font/AsyncTypefaceCache$AsyncTypefaceResult;", "get", "Lkotlin/Function1;", "Ld7/d;", "block", "runCached", "(Landroidx/compose/ui/text/font/Font;Landroidx/compose/ui/text/font/PlatformFontLoader;ZLr7/l;Ld7/d;)Ljava/lang/Object;", "Lkotlin/Function0;", "runCachedBlocking", "(Landroidx/compose/ui/text/font/Font;Landroidx/compose/ui/text/font/PlatformFontLoader;Lr7/a;)Ljava/lang/Object;", "PermanentFailure", "Ljava/lang/Object;", "Landroidx/compose/ui/text/caches/LruCache;", "Landroidx/compose/ui/text/font/AsyncTypefaceCache$Key;", "resultCache", "Landroidx/compose/ui/text/caches/LruCache;", "Landroidx/compose/ui/text/caches/SimpleArrayMap;", "permanentCache", "Landroidx/compose/ui/text/caches/SimpleArrayMap;", "Landroidx/compose/ui/text/platform/SynchronizedObject;", "cacheLock", "Landroidx/compose/ui/text/platform/SynchronizedObject;", "AsyncTypefaceResult", "Key", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AsyncTypefaceCache {
    public static final int $stable = 8;
    private final Object PermanentFailure = AsyncTypefaceResult.m5261constructorimpl(null);
    private final LruCache<Key, AsyncTypefaceResult> resultCache = new LruCache<>(16);
    private final SimpleArrayMap<Key, AsyncTypefaceResult> permanentCache = new SimpleArrayMap<>(0, 1, null);
    private final SynchronizedObject cacheLock = Synchronization_jvmKt.createSynchronizedObject();

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0081@\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u000b\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0014HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0088\u0001\u0002¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/text/font/AsyncTypefaceCache$AsyncTypefaceResult;", "", "result", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "isPermanentFailure", "", "isPermanentFailure-impl", "(Ljava/lang/Object;)Z", "getResult", "()Ljava/lang/Object;", "equals", "other", "equals-impl", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/Object;)I", "toString", "", "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @g
    public static final class AsyncTypefaceResult {
        private final Object result;

        private /* synthetic */ AsyncTypefaceResult(Object obj) {
            this.result = obj;
        }

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ AsyncTypefaceResult m5260boximpl(Object obj) {
            return new AsyncTypefaceResult(obj);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static Object m5261constructorimpl(Object obj) {
            return obj;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m5262equalsimpl(Object obj, Object obj2) {
            return (obj2 instanceof AsyncTypefaceResult) && p.a(obj, ((AsyncTypefaceResult) obj2).m5267unboximpl());
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m5263equalsimpl0(Object obj, Object obj2) {
            return p.a(obj, obj2);
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m5264hashCodeimpl(Object obj) {
            if (obj == null) {
                return 0;
            }
            return obj.hashCode();
        }

        /* JADX INFO: renamed from: isPermanentFailure-impl, reason: not valid java name */
        public static final boolean m5265isPermanentFailureimpl(Object obj) {
            return obj == null;
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m5266toStringimpl(Object obj) {
            return "AsyncTypefaceResult(result=" + obj + ')';
        }

        public boolean equals(Object obj) {
            return m5262equalsimpl(this.result, obj);
        }

        public final Object getResult() {
            return this.result;
        }

        public int hashCode() {
            return m5264hashCodeimpl(this.result);
        }

        public String toString() {
            return m5266toStringimpl(this.result);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
        public final /* synthetic */ Object m5267unboximpl() {
            return this.result;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0005J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0001HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/text/font/AsyncTypefaceCache$Key;", "", "font", "Landroidx/compose/ui/text/font/Font;", "loaderKey", "(Landroidx/compose/ui/text/font/Font;Ljava/lang/Object;)V", "getFont", "()Landroidx/compose/ui/text/font/Font;", "getLoaderKey", "()Ljava/lang/Object;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class Key {
        public static final int $stable = 8;
        private final Font font;
        private final Object loaderKey;

        public Key(Font font, Object obj) {
            this.font = font;
            this.loaderKey = obj;
        }

        public static /* synthetic */ Key copy$default(Key key, Font font, Object obj, int i10, Object obj2) {
            if ((i10 & 1) != 0) {
                font = key.font;
            }
            if ((i10 & 2) != 0) {
                obj = key.loaderKey;
            }
            return key.copy(font, obj);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Font getFont() {
            return this.font;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Object getLoaderKey() {
            return this.loaderKey;
        }

        public final Key copy(Font font, Object loaderKey) {
            return new Key(font, loaderKey);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Key)) {
                return false;
            }
            Key key = (Key) other;
            return p.a(this.font, key.font) && p.a(this.loaderKey, key.loaderKey);
        }

        public final Font getFont() {
            return this.font;
        }

        public final Object getLoaderKey() {
            return this.loaderKey;
        }

        public int hashCode() {
            int iHashCode = this.font.hashCode() * 31;
            Object obj = this.loaderKey;
            return iHashCode + (obj == null ? 0 : obj.hashCode());
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("Key(font=");
            sb2.append(this.font);
            sb2.append(", loaderKey=");
            return d.p(sb2, this.loaderKey, ')');
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.text.font.AsyncTypefaceCache$runCached$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.ui.text.font.AsyncTypefaceCache", f = "FontListFontFamilyTypefaceAdapter.kt", l = {398}, m = "runCached")
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        Object L$1;
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
            return AsyncTypefaceCache.this.runCached(null, null, false, null, this);
        }
    }

    public static /* synthetic */ void put$default(AsyncTypefaceCache asyncTypefaceCache, Font font, PlatformFontLoader platformFontLoader, Object obj, boolean z, int i10, Object obj2) {
        if ((i10 & 8) != 0) {
            z = false;
        }
        asyncTypefaceCache.put(font, platformFontLoader, obj, z);
    }

    /* JADX INFO: renamed from: get-1ASDuI8, reason: not valid java name */
    public final AsyncTypefaceResult m5259get1ASDuI8(Font font, PlatformFontLoader platformFontLoader) {
        AsyncTypefaceResult asyncTypefaceResult;
        Key key = new Key(font, platformFontLoader.getCacheKey());
        synchronized (this.cacheLock) {
            asyncTypefaceResult = this.resultCache.get(key);
            if (asyncTypefaceResult == null) {
                asyncTypefaceResult = this.permanentCache.get(key);
            }
        }
        return asyncTypefaceResult;
    }

    public final void put(Font font, PlatformFontLoader platformFontLoader, Object result, boolean forever) {
        Key key = new Key(font, platformFontLoader.getCacheKey());
        synchronized (this.cacheLock) {
            try {
                if (result == null) {
                    this.permanentCache.put(key, AsyncTypefaceResult.m5260boximpl(this.PermanentFailure));
                } else if (forever) {
                    this.permanentCache.put(key, AsyncTypefaceResult.m5260boximpl(AsyncTypefaceResult.m5261constructorimpl(result)));
                } else {
                    this.resultCache.put(key, AsyncTypefaceResult.m5260boximpl(AsyncTypefaceResult.m5261constructorimpl(result)));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object runCached(androidx.compose.ui.text.font.Font r5, androidx.compose.ui.text.font.PlatformFontLoader r6, boolean r7, r7.l<? super d7.d<java.lang.Object>, ? extends java.lang.Object> r8, d7.d<java.lang.Object> r9) {
        /*
            r4 = this;
            boolean r0 = r9 instanceof androidx.compose.ui.text.font.AsyncTypefaceCache.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            androidx.compose.ui.text.font.AsyncTypefaceCache$runCached$1 r0 = (androidx.compose.ui.text.font.AsyncTypefaceCache.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.ui.text.font.AsyncTypefaceCache$runCached$1 r0 = new androidx.compose.ui.text.font.AsyncTypefaceCache$runCached$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            boolean r7 = r0.Z$0
            java.lang.Object r5 = r0.L$1
            androidx.compose.ui.text.font.AsyncTypefaceCache$Key r5 = (androidx.compose.ui.text.font.AsyncTypefaceCache.Key) r5
            java.lang.Object r6 = r0.L$0
            androidx.compose.ui.text.font.AsyncTypefaceCache r6 = (androidx.compose.ui.text.font.AsyncTypefaceCache) r6
            k2.c.G(r9)
            goto L79
        L31:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L39:
            k2.c.G(r9)
            androidx.compose.ui.text.font.AsyncTypefaceCache$Key r9 = new androidx.compose.ui.text.font.AsyncTypefaceCache$Key
            java.lang.Object r6 = r6.getCacheKey()
            r9.<init>(r5, r6)
            androidx.compose.ui.text.platform.SynchronizedObject r5 = r4.cacheLock
            monitor-enter(r5)
            androidx.compose.ui.text.caches.LruCache<androidx.compose.ui.text.font.AsyncTypefaceCache$Key, androidx.compose.ui.text.font.AsyncTypefaceCache$AsyncTypefaceResult> r6 = r4.resultCache     // Catch: java.lang.Throwable -> L5b
            java.lang.Object r6 = r6.get(r9)     // Catch: java.lang.Throwable -> L5b
            androidx.compose.ui.text.font.AsyncTypefaceCache$AsyncTypefaceResult r6 = (androidx.compose.ui.text.font.AsyncTypefaceCache.AsyncTypefaceResult) r6     // Catch: java.lang.Throwable -> L5b
            if (r6 != 0) goto L5d
            androidx.compose.ui.text.caches.SimpleArrayMap<androidx.compose.ui.text.font.AsyncTypefaceCache$Key, androidx.compose.ui.text.font.AsyncTypefaceCache$AsyncTypefaceResult> r6 = r4.permanentCache     // Catch: java.lang.Throwable -> L5b
            java.lang.Object r6 = r6.get(r9)     // Catch: java.lang.Throwable -> L5b
            androidx.compose.ui.text.font.AsyncTypefaceCache$AsyncTypefaceResult r6 = (androidx.compose.ui.text.font.AsyncTypefaceCache.AsyncTypefaceResult) r6     // Catch: java.lang.Throwable -> L5b
            goto L5d
        L5b:
            r6 = move-exception
            goto Lad
        L5d:
            if (r6 == 0) goto L65
            java.lang.Object r6 = r6.m5267unboximpl()     // Catch: java.lang.Throwable -> L5b
            monitor-exit(r5)
            return r6
        L65:
            monitor-exit(r5)
            r0.L$0 = r4
            r0.L$1 = r9
            r0.Z$0 = r7
            r0.label = r3
            java.lang.Object r5 = r8.invoke(r0)
            if (r5 != r1) goto L75
            return r1
        L75:
            r6 = r9
            r9 = r5
            r5 = r6
            r6 = r4
        L79:
            androidx.compose.ui.text.platform.SynchronizedObject r8 = r6.cacheLock
            monitor-enter(r8)
            if (r9 != 0) goto L8c
            androidx.compose.ui.text.caches.SimpleArrayMap<androidx.compose.ui.text.font.AsyncTypefaceCache$Key, androidx.compose.ui.text.font.AsyncTypefaceCache$AsyncTypefaceResult> r7 = r6.permanentCache     // Catch: java.lang.Throwable -> L8a
            java.lang.Object r6 = r6.PermanentFailure     // Catch: java.lang.Throwable -> L8a
            androidx.compose.ui.text.font.AsyncTypefaceCache$AsyncTypefaceResult r6 = androidx.compose.ui.text.font.AsyncTypefaceCache.AsyncTypefaceResult.m5260boximpl(r6)     // Catch: java.lang.Throwable -> L8a
            r7.put(r5, r6)     // Catch: java.lang.Throwable -> L8a
            goto La9
        L8a:
            r5 = move-exception
            goto Lab
        L8c:
            if (r7 == 0) goto L9c
            androidx.compose.ui.text.caches.SimpleArrayMap<androidx.compose.ui.text.font.AsyncTypefaceCache$Key, androidx.compose.ui.text.font.AsyncTypefaceCache$AsyncTypefaceResult> r6 = r6.permanentCache     // Catch: java.lang.Throwable -> L8a
            java.lang.Object r7 = androidx.compose.ui.text.font.AsyncTypefaceCache.AsyncTypefaceResult.m5261constructorimpl(r9)     // Catch: java.lang.Throwable -> L8a
            androidx.compose.ui.text.font.AsyncTypefaceCache$AsyncTypefaceResult r7 = androidx.compose.ui.text.font.AsyncTypefaceCache.AsyncTypefaceResult.m5260boximpl(r7)     // Catch: java.lang.Throwable -> L8a
            r6.put(r5, r7)     // Catch: java.lang.Throwable -> L8a
            goto La9
        L9c:
            androidx.compose.ui.text.caches.LruCache<androidx.compose.ui.text.font.AsyncTypefaceCache$Key, androidx.compose.ui.text.font.AsyncTypefaceCache$AsyncTypefaceResult> r6 = r6.resultCache     // Catch: java.lang.Throwable -> L8a
            java.lang.Object r7 = androidx.compose.ui.text.font.AsyncTypefaceCache.AsyncTypefaceResult.m5261constructorimpl(r9)     // Catch: java.lang.Throwable -> L8a
            androidx.compose.ui.text.font.AsyncTypefaceCache$AsyncTypefaceResult r7 = androidx.compose.ui.text.font.AsyncTypefaceCache.AsyncTypefaceResult.m5260boximpl(r7)     // Catch: java.lang.Throwable -> L8a
            r6.put(r5, r7)     // Catch: java.lang.Throwable -> L8a
        La9:
            monitor-exit(r8)
            return r9
        Lab:
            monitor-exit(r8)
            throw r5
        Lad:
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.font.AsyncTypefaceCache.runCached(androidx.compose.ui.text.font.Font, androidx.compose.ui.text.font.PlatformFontLoader, boolean, r7.l, d7.d):java.lang.Object");
    }

    public final Object runCachedBlocking(Font font, PlatformFontLoader platformFontLoader, r7.a<? extends Object> block) {
        synchronized (this.cacheLock) {
            try {
                Key key = new Key(font, platformFontLoader.getCacheKey());
                AsyncTypefaceResult asyncTypefaceResult = (AsyncTypefaceResult) this.resultCache.get(key);
                if (asyncTypefaceResult == null) {
                    asyncTypefaceResult = (AsyncTypefaceResult) this.permanentCache.get(key);
                }
                if (asyncTypefaceResult != null) {
                    return asyncTypefaceResult.m5267unboximpl();
                }
                Object objInvoke = block.invoke();
                put$default(this, font, platformFontLoader, objInvoke, false, 8, null);
                return objInvoke;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
