package io.ktor.http.content;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\u001a;\u0010\u0007\u001a\u00020\u0004*\u00020\u00002\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\t*\u00020\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lio/ktor/http/content/MultiPartData;", "Lkotlin/Function2;", "Lio/ktor/http/content/PartData;", "Ld7/d;", "Lx6/t0;", "", "partHandler", "forEachPart", "(Lio/ktor/http/content/MultiPartData;Lr7/p;Ld7/d;)Ljava/lang/Object;", "", "readAllParts", "(Lio/ktor/http/content/MultiPartData;Ld7/d;)Ljava/lang/Object;", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MultipartKt {

    /* JADX INFO: renamed from: io.ktor.http.content.MultipartKt$forEachPart$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.content.MultipartKt", f = "Multipart.kt", l = {128, TsExtractor.TS_STREAM_TYPE_AC3}, m = "forEachPart")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MultipartKt.forEachPart(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.content.MultipartKt$readAllParts$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.content.MultipartKt", f = "Multipart.kt", l = {TsExtractor.TS_STREAM_TYPE_DTS, 143}, m = "readAllParts")
    public static final class C18331 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C18331(d<? super C18331> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MultipartKt.readAllParts(null, this);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x006d, code lost:
    
        if (r6.invoke(r8, r0) == r4) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x006d -> B:13:0x0031). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object forEachPart(io.ktor.http.content.MultiPartData r6, r7.p<? super io.ktor.http.content.PartData, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r7, d7.d<? super x6.t0> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.http.content.MultipartKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.http.content.MultipartKt$forEachPart$1 r0 = (io.ktor.http.content.MultipartKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.http.content.MultipartKt$forEachPart$1 r0 = new io.ktor.http.content.MultipartKt$forEachPart$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L49
            if (r1 == r3) goto L3d
            if (r1 != r2) goto L35
            java.lang.Object r6 = r0.L$1
            r7.p r6 = (r7.p) r6
            java.lang.Object r7 = r0.L$0
            io.ktor.http.content.MultiPartData r7 = (io.ktor.http.content.MultiPartData) r7
            k2.c.G(r8)
        L31:
            r5 = r7
            r7 = r6
            r6 = r5
            goto L4c
        L35:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3d:
            java.lang.Object r6 = r0.L$1
            r7.p r6 = (r7.p) r6
            java.lang.Object r7 = r0.L$0
            io.ktor.http.content.MultiPartData r7 = (io.ktor.http.content.MultiPartData) r7
            k2.c.G(r8)
            goto L5c
        L49:
            k2.c.G(r8)
        L4c:
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r8 = r6.readPart(r0)
            if (r8 != r4) goto L59
            goto L6f
        L59:
            r5 = r7
            r7 = r6
            r6 = r5
        L5c:
            io.ktor.http.content.PartData r8 = (io.ktor.http.content.PartData) r8
            if (r8 != 0) goto L63
            x6.t0 r6 = x6.t0.f22605a
            return r6
        L63:
            r0.L$0 = r7
            r0.L$1 = r6
            r0.label = r2
            java.lang.Object r8 = r6.invoke(r8, r0)
            if (r8 != r4) goto L31
        L6f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.content.MultipartKt.forEachPart(io.ktor.http.content.MultiPartData, r7.p, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x004d, code lost:
    
        if (r7 == r4) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006c, code lost:
    
        if (r7 != r4) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006e, code lost:
    
        return r4;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x006c -> B:27:0x006f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readAllParts(io.ktor.http.content.MultiPartData r6, d7.d<? super java.util.List<? extends io.ktor.http.content.PartData>> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.http.content.MultipartKt.C18331
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.http.content.MultipartKt$readAllParts$1 r0 = (io.ktor.http.content.MultipartKt.C18331) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.http.content.MultipartKt$readAllParts$1 r0 = new io.ktor.http.content.MultipartKt$readAllParts$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L42
            if (r1 == r3) goto L3a
            if (r1 != r2) goto L32
            java.lang.Object r6 = r0.L$1
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            java.lang.Object r1 = r0.L$0
            io.ktor.http.content.MultiPartData r1 = (io.ktor.http.content.MultiPartData) r1
            k2.c.G(r7)
            goto L6f
        L32:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3a:
            java.lang.Object r6 = r0.L$0
            io.ktor.http.content.MultiPartData r6 = (io.ktor.http.content.MultiPartData) r6
            k2.c.G(r7)
            goto L50
        L42:
            k2.c.G(r7)
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r7 = r6.readPart(r0)
            if (r7 != r4) goto L50
            goto L6e
        L50:
            io.ktor.http.content.PartData r7 = (io.ktor.http.content.PartData) r7
            if (r7 != 0) goto L57
            kotlin.collections.z r6 = kotlin.collections.z.f19728i
            return r6
        L57:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r1.add(r7)
            r5 = r1
            r1 = r6
            r6 = r5
        L62:
            r0.L$0 = r1
            r0.L$1 = r6
            r0.label = r2
            java.lang.Object r7 = r1.readPart(r0)
            if (r7 != r4) goto L6f
        L6e:
            return r4
        L6f:
            io.ktor.http.content.PartData r7 = (io.ktor.http.content.PartData) r7
            if (r7 != 0) goto L74
            return r6
        L74:
            r6.add(r7)
            goto L62
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.content.MultipartKt.readAllParts(io.ktor.http.content.MultiPartData, d7.d):java.lang.Object");
    }
}
