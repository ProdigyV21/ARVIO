package coil.fetch;

import android.content.Utils;
import android.graphics.DataSource;
import android.graphics.ImageSource;
import android.graphics.ImageSources;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import coil.ImageLoader;
import coil.disk.DiskCache;
import coil.fetch.Fetcher;
import coil.network.CacheResponse;
import coil.network.CacheStrategy;
import coil.request.Options;
import com.google.common.util.concurrent.r0;
import d7.d;
import f7.c;
import f7.e;
import gb.d0;
import gb.h;
import gb.i;
import gb.j0;
import gb.k0;
import gb.o0;
import gb.p0;
import gb.s0;
import java.io.IOException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import kotlin.text.u;
import x6.s;
import xb.h0;
import xb.i0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 >2\u00020\u0001:\u0002>?B=\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0011\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J5\u0010\u0019\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u001b\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0013H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u001f\u0010 J\u0015\u0010!\u001a\u0004\u0018\u00010\u0017*\u00020\u000fH\u0002¢\u0006\u0004\b!\u0010\"J\u0013\u0010$\u001a\u00020#*\u00020\u000fH\u0002¢\u0006\u0004\b$\u0010%J\u0013\u0010$\u001a\u00020#*\u00020&H\u0002¢\u0006\u0004\b$\u0010'J\u0013\u0010)\u001a\u00020(*\u00020\u0015H\u0002¢\u0006\u0004\b)\u0010*J\u0013\u0010,\u001a\u00020+H\u0096@ø\u0001\u0000¢\u0006\u0004\b,\u0010-J#\u00102\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010/\u001a\u0004\u0018\u00010.H\u0001¢\u0006\u0004\b0\u00101R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00103R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u00104R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u00105R\u001c\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u00105R\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u00106R\u0014\u00109\u001a\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b7\u00108R\u0014\u0010=\u001a\u00020:8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006@"}, d2 = {"Lcoil/fetch/HttpUriFetcher;", "Lcoil/fetch/Fetcher;", "", "url", "Lcoil/request/Options;", "options", "Lx6/s;", "Lgb/i$a;", "callFactory", "Lcoil/disk/DiskCache;", "diskCache", "", "respectCacheHeaders", "<init>", "(Ljava/lang/String;Lcoil/request/Options;Lx6/s;Lx6/s;Z)V", "Lcoil/disk/DiskCache$Snapshot;", "readFromDiskCache", "()Lcoil/disk/DiskCache$Snapshot;", "snapshot", "Lgb/k0;", "request", "Lgb/p0;", "response", "Lcoil/network/CacheResponse;", "cacheResponse", "writeToDiskCache", "(Lcoil/disk/DiskCache$Snapshot;Lgb/k0;Lgb/p0;Lcoil/network/CacheResponse;)Lcoil/disk/DiskCache$Snapshot;", "newRequest", "()Lgb/k0;", "executeNetworkRequest", "(Lgb/k0;Ld7/d;)Ljava/lang/Object;", "isCacheable", "(Lgb/k0;Lgb/p0;)Z", "toCacheResponse", "(Lcoil/disk/DiskCache$Snapshot;)Lcoil/network/CacheResponse;", "Lcoil/decode/ImageSource;", "toImageSource", "(Lcoil/disk/DiskCache$Snapshot;)Lcoil/decode/ImageSource;", "Lgb/s0;", "(Lgb/s0;)Lcoil/decode/ImageSource;", "Lcoil/decode/DataSource;", "toDataSource", "(Lgb/p0;)Lcoil/decode/DataSource;", "Lcoil/fetch/FetchResult;", "fetch", "(Ld7/d;)Ljava/lang/Object;", "Lgb/d0;", "contentType", "getMimeType$coil_base_release", "(Ljava/lang/String;Lgb/d0;)Ljava/lang/String;", "getMimeType", "Ljava/lang/String;", "Lcoil/request/Options;", "Lx6/s;", "Z", "getDiskCacheKey", "()Ljava/lang/String;", "diskCacheKey", "Lxb/s;", "getFileSystem", "()Lxb/s;", "fileSystem", "Companion", "Factory", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class HttpUriFetcher implements Fetcher {
    private static final String MIME_TYPE_TEXT_PLAIN = "text/plain";
    private final s<i.a> callFactory;
    private final s<DiskCache> diskCache;
    private final Options options;
    private final boolean respectCacheHeaders;
    private final String url;
    private static final h CACHE_CONTROL_FORCE_NETWORK_NO_CACHE = new h(true, true, -1, -1, false, false, false, -1, -1, false, false, false, null);
    private static final h CACHE_CONTROL_NO_NETWORK_NO_CACHE = new h(true, false, -1, -1, false, false, false, -1, -1, true, false, false, null);

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\r\u0010\u000eJ)\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0016R\u001c\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0016R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcoil/fetch/HttpUriFetcher$Factory;", "Lcoil/fetch/Fetcher$Factory;", "Landroid/net/Uri;", "Lx6/s;", "Lgb/i$a;", "callFactory", "Lcoil/disk/DiskCache;", "diskCache", "", "respectCacheHeaders", "<init>", "(Lx6/s;Lx6/s;Z)V", "data", "isApplicable", "(Landroid/net/Uri;)Z", "Lcoil/request/Options;", "options", "Lcoil/ImageLoader;", "imageLoader", "Lcoil/fetch/Fetcher;", "create", "(Landroid/net/Uri;Lcoil/request/Options;Lcoil/ImageLoader;)Lcoil/fetch/Fetcher;", "Lx6/s;", "Z", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Factory implements Fetcher.Factory<Uri> {
        private final s<i.a> callFactory;
        private final s<DiskCache> diskCache;
        private final boolean respectCacheHeaders;

        /* JADX WARN: Multi-variable type inference failed */
        public Factory(s<? extends i.a> sVar, s<? extends DiskCache> sVar2, boolean z) {
            this.callFactory = sVar;
            this.diskCache = sVar2;
            this.respectCacheHeaders = z;
        }

        private final boolean isApplicable(Uri data) {
            return p.a(data.getScheme(), "http") || p.a(data.getScheme(), "https");
        }

        @Override // coil.fetch.Fetcher.Factory
        public Fetcher create(Uri data, Options options, ImageLoader imageLoader) {
            if (isApplicable(data)) {
                return new HttpUriFetcher(data.toString(), options, this.callFactory, this.diskCache, this.respectCacheHeaders);
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: coil.fetch.HttpUriFetcher$executeNetworkRequest$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "coil.fetch.HttpUriFetcher", f = "HttpUriFetcher.kt", l = {224}, m = "executeNetworkRequest")
    public static final class AnonymousClass1 extends c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpUriFetcher.this.executeNetworkRequest(null, this);
        }
    }

    /* JADX INFO: renamed from: coil.fetch.HttpUriFetcher$fetch$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "coil.fetch.HttpUriFetcher", f = "HttpUriFetcher.kt", l = {77, 106}, m = "fetch")
    public static final class C06451 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C06451(d<? super C06451> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpUriFetcher.this.fetch(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public HttpUriFetcher(String str, Options options, s<? extends i.a> sVar, s<? extends DiskCache> sVar2, boolean z) {
        this.url = str;
        this.options = options;
        this.callFactory = sVar;
        this.diskCache = sVar2;
        this.respectCacheHeaders = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object executeNetworkRequest(gb.k0 r5, d7.d<? super gb.p0> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof coil.fetch.HttpUriFetcher.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            coil.fetch.HttpUriFetcher$executeNetworkRequest$1 r0 = (coil.fetch.HttpUriFetcher.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            coil.fetch.HttpUriFetcher$executeNetworkRequest$1 r0 = new coil.fetch.HttpUriFetcher$executeNetworkRequest$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r6)
            goto L70
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2d:
            k2.c.G(r6)
            boolean r6 = android.content.Utils.isMainThread()
            if (r6 == 0) goto L59
            coil.request.Options r6 = r4.options
            coil.request.CachePolicy r6 = r6.getNetworkCachePolicy()
            boolean r6 = r6.getReadEnabled()
            if (r6 != 0) goto L53
            x6.s<gb.i$a> r6 = r4.callFactory
            java.lang.Object r6 = r6.getValue()
            gb.i$a r6 = (gb.i.a) r6
            lb.i r5 = r6.a(r5)
            gb.p0 r5 = r5.d()
            goto L73
        L53:
            android.os.NetworkOnMainThreadException r5 = new android.os.NetworkOnMainThreadException
            r5.<init>()
            throw r5
        L59:
            x6.s<gb.i$a> r6 = r4.callFactory
            java.lang.Object r6 = r6.getValue()
            gb.i$a r6 = (gb.i.a) r6
            lb.i r5 = r6.a(r5)
            r0.label = r2
            java.lang.Object r6 = android.content.Calls.await(r5, r0)
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L70
            return r5
        L70:
            r5 = r6
            gb.p0 r5 = (gb.p0) r5
        L73:
            boolean r6 = r5.k()
            if (r6 != 0) goto L8c
            int r6 = r5.f15783n
            r0 = 304(0x130, float:4.26E-43)
            if (r6 == r0) goto L8c
            gb.s0 r6 = r5.f15786q
            if (r6 == 0) goto L86
            android.content.Utils.closeQuietly(r6)
        L86:
            coil.network.HttpException r6 = new coil.network.HttpException
            r6.<init>(r5)
            throw r6
        L8c:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.fetch.HttpUriFetcher.executeNetworkRequest(gb.k0, d7.d):java.lang.Object");
    }

    private final String getDiskCacheKey() {
        String diskCacheKey = this.options.getDiskCacheKey();
        return diskCacheKey == null ? this.url : diskCacheKey;
    }

    private final xb.s getFileSystem() {
        return ((DiskCache) this.diskCache.getValue()).getFileSystem();
    }

    private final boolean isCacheable(k0 request, p0 response) {
        if (this.options.getDiskCachePolicy().getWriteEnabled()) {
            return !this.respectCacheHeaders || CacheStrategy.INSTANCE.isCacheable(request, response);
        }
        return false;
    }

    private final k0 newRequest() {
        j0 j0Var = new j0();
        j0Var.i(this.url);
        j0Var.f15724c = this.options.getHeaders().c();
        for (Map.Entry<Class<?>, Object> entry : this.options.getTags().asMap().entrySet()) {
            j0Var.h(entry.getKey(), entry.getValue());
        }
        boolean readEnabled = this.options.getDiskCachePolicy().getReadEnabled();
        boolean readEnabled2 = this.options.getNetworkCachePolicy().getReadEnabled();
        if (!readEnabled2 && readEnabled) {
            j0Var.c(h.f15687o);
        } else if (!readEnabled2 || readEnabled) {
            if (!readEnabled2 && !readEnabled) {
                j0Var.c(CACHE_CONTROL_NO_NETWORK_NO_CACHE);
            }
        } else if (this.options.getDiskCachePolicy().getWriteEnabled()) {
            j0Var.c(h.f15686n);
        } else {
            j0Var.c(CACHE_CONTROL_FORCE_NETWORK_NO_CACHE);
        }
        return j0Var.b();
    }

    private final DiskCache.Snapshot readFromDiskCache() {
        DiskCache diskCache;
        if (!this.options.getDiskCachePolicy().getReadEnabled() || (diskCache = (DiskCache) this.diskCache.getValue()) == null) {
            return null;
        }
        return diskCache.openSnapshot(getDiskCacheKey());
    }

    private final CacheResponse toCacheResponse(DiskCache.Snapshot snapshot) throws Throwable {
        Throwable th;
        CacheResponse cacheResponse;
        try {
            i0 i0Var = new i0(getFileSystem().source(snapshot.getMetadata()));
            try {
                cacheResponse = new CacheResponse(i0Var);
                try {
                    i0Var.close();
                    th = null;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                try {
                    i0Var.close();
                } catch (Throwable th4) {
                    r0.a(th3, th4);
                }
                th = th3;
                cacheResponse = null;
            }
            if (th == null) {
                return cacheResponse;
            }
            throw th;
        } catch (IOException unused) {
            return null;
        }
    }

    private final DataSource toDataSource(p0 p0Var) {
        return p0Var.f15787r != null ? DataSource.NETWORK : DataSource.DISK;
    }

    private final ImageSource toImageSource(DiskCache.Snapshot snapshot) {
        return ImageSources.create(snapshot.getData(), getFileSystem(), getDiskCacheKey(), snapshot);
    }

    private final DiskCache.Snapshot writeToDiskCache(DiskCache.Snapshot snapshot, k0 request, p0 response, CacheResponse cacheResponse) {
        DiskCache.Editor editorOpenEditor;
        Throwable th = null;
        if (isCacheable(request, response)) {
            if (snapshot != null) {
                editorOpenEditor = snapshot.closeAndOpenEditor();
            } else {
                DiskCache diskCache = (DiskCache) this.diskCache.getValue();
                editorOpenEditor = diskCache != null ? diskCache.openEditor(getDiskCacheKey()) : null;
            }
            try {
                if (editorOpenEditor != null) {
                    try {
                        if (response.f15783n != 304 || cacheResponse == null) {
                            h0 h0Var = new h0(getFileSystem().sink(editorOpenEditor.getMetadata(), false));
                            try {
                                new CacheResponse(response).writeTo(h0Var);
                                try {
                                    h0Var.close();
                                    th = null;
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                try {
                                    h0Var.close();
                                } catch (Throwable th4) {
                                    r0.a(th, th4);
                                }
                            }
                            if (th != null) {
                                throw th;
                            }
                            h0 h0Var2 = new h0(getFileSystem().sink(editorOpenEditor.getData(), false));
                            try {
                                response.f15786q.o().D(h0Var2);
                                try {
                                    h0Var2.close();
                                } catch (Throwable th5) {
                                    th = th5;
                                }
                            } catch (Throwable th6) {
                                th = th6;
                                try {
                                    h0Var2.close();
                                } catch (Throwable th7) {
                                    r0.a(th, th7);
                                }
                            }
                            if (th != null) {
                                throw th;
                            }
                        } else {
                            o0 o0VarL = response.l();
                            o0VarL.f15767f = CacheStrategy.INSTANCE.combineHeaders(cacheResponse.getResponseHeaders(), response.f15785p).c();
                            p0 p0VarA = o0VarL.a();
                            h0 h0Var3 = new h0(getFileSystem().sink(editorOpenEditor.getMetadata(), false));
                            try {
                                new CacheResponse(p0VarA).writeTo(h0Var3);
                                try {
                                    h0Var3.close();
                                } catch (Throwable th8) {
                                    th = th8;
                                }
                            } catch (Throwable th9) {
                                th = th9;
                                try {
                                    h0Var3.close();
                                } catch (Throwable th10) {
                                    r0.a(th, th10);
                                }
                            }
                            if (th != null) {
                                throw th;
                            }
                        }
                        DiskCache.Snapshot snapshotCommitAndOpenSnapshot = editorOpenEditor.commitAndOpenSnapshot();
                        Utils.closeQuietly(response);
                        return snapshotCommitAndOpenSnapshot;
                    } catch (Exception e5) {
                        Utils.abortQuietly(editorOpenEditor);
                        throw e5;
                    }
                }
            } catch (Throwable th11) {
                Utils.closeQuietly(response);
                throw th11;
            }
        } else if (snapshot != null) {
            Utils.closeQuietly(snapshot);
            return null;
        }
        return null;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:0|2|(2:4|(1:6)(1:7))(0)|8|(3:(1:(1:(7:12|91|13|72|89|73|74)(2:17|18))(3:19|93|20))(6:24|88|(4:26|(2:31|32)|35|(2:37|(2:41|42))(4:43|(1:45)|46|47))(1:48)|49|(1:52)|70)|97|(4:56|(1:58)|62|63)(2:64|(2:66|67)(3:68|(5:71|72|89|73|74)|70)))|53|86|54|(2:(1:96)|(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x019e, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x019f, code lost:
    
        r1 = r12;
        r12 = r0;
        r2 = r5;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01aa  */
    @Override // coil.fetch.Fetcher
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object fetch(d7.d<? super coil.fetch.FetchResult> r12) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 430
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.fetch.HttpUriFetcher.fetch(d7.d):java.lang.Object");
    }

    public final String getMimeType$coil_base_release(String url, d0 contentType) {
        String mimeTypeFromUrl;
        String str = contentType != null ? contentType.f15635a : null;
        if ((str == null || u.P(str, "text/plain", false)) && (mimeTypeFromUrl = Utils.getMimeTypeFromUrl(MimeTypeMap.getSingleton(), url)) != null) {
            return mimeTypeFromUrl;
        }
        if (str != null) {
            return o.G0(';', str, str);
        }
        return null;
    }

    private final ImageSource toImageSource(s0 s0Var) {
        return ImageSources.create(s0Var.o(), this.options.getContext());
    }
}
