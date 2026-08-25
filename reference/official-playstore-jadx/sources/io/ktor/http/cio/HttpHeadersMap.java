package io.ktor.http.cio;

import a8.x1;
import ga.m;
import ga.t;
import io.ktor.http.ContentDisposition;
import io.ktor.http.cio.internals.CharArrayBuilder;
import io.ktor.http.cio.internals.CharsKt;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0015\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J=\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u0006¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0011\u001a\u00020\u0010H\u0086\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u00182\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0006¢\u0006\u0004\b\u001c\u0010\u001dJ\u0015\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0006¢\u0006\u0004\b\u001e\u0010\u001dJ\r\u0010\u001f\u001a\u00020\r¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u0010H\u0016¢\u0006\u0004\b!\u0010\"R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010#R$\u0010%\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u0016\u0010*\u001a\u00020)8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+¨\u0006,"}, d2 = {"Lio/ktor/http/cio/HttpHeadersMap;", "", "Lio/ktor/http/cio/internals/CharArrayBuilder;", "builder", "<init>", "(Lio/ktor/http/cio/internals/CharArrayBuilder;)V", "", "nameHash", "valueHash", "nameStartIndex", "nameEndIndex", "valueStartIndex", "valueEndIndex", "Lx6/t0;", "put", "(IIIIII)V", "", ContentDisposition.Parameters.Name, "fromIndex", "find", "(Ljava/lang/String;I)I", "", "get", "(Ljava/lang/String;)Ljava/lang/CharSequence;", "Lga/m;", "getAll", "(Ljava/lang/String;)Lga/m;", "idx", "nameAt", "(I)Ljava/lang/CharSequence;", "valueAt", "release", "()V", "toString", "()Ljava/lang/String;", "Lio/ktor/http/cio/internals/CharArrayBuilder;", "<set-?>", ContentDisposition.Parameters.Size, "I", "getSize", "()I", "", "indexes", "[I", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpHeadersMap {
    private final CharArrayBuilder builder;
    private int[] indexes = (int[]) HttpHeadersMapKt.IntArrayPool.borrow();
    private int size;

    /* JADX INFO: renamed from: io.ktor.http.cio.HttpHeadersMap$getAll$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<Integer, Integer> {
        public AnonymousClass1() {
            super(1);
        }

        public final Integer invoke(int i10) {
            int i11 = i10 + 1;
            if (i11 >= HttpHeadersMap.this.getSize()) {
                return null;
            }
            return Integer.valueOf(i11);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.HttpHeadersMap$getAll$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<Integer, Integer> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        public final Integer invoke(int i10) {
            return Integer.valueOf(i10 * 8);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.HttpHeadersMap$getAll$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(I)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements l<Integer, Boolean> {
        final /* synthetic */ int $nameHash;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(int i10) {
            super(1);
            this.$nameHash = i10;
        }

        public final Boolean invoke(int i10) {
            return Boolean.valueOf(HttpHeadersMap.this.indexes[i10] == this.$nameHash);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.HttpHeadersMap$getAll$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements l<Integer, CharSequence> {
        public AnonymousClass4() {
            super(1);
        }

        public final CharSequence invoke(int i10) {
            return HttpHeadersMap.this.builder.subSequence(HttpHeadersMap.this.indexes[i10 + 4], HttpHeadersMap.this.indexes[i10 + 5]);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    public HttpHeadersMap(CharArrayBuilder charArrayBuilder) {
        this.builder = charArrayBuilder;
    }

    public static /* synthetic */ int find$default(HttpHeadersMap httpHeadersMap, String str, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = 0;
        }
        return httpHeadersMap.find(str, i10);
    }

    public final int find(String name, int fromIndex) {
        int iHashCodeLowerCase$default = CharsKt.hashCodeLowerCase$default(name, 0, 0, 3, null);
        int i10 = this.size;
        while (fromIndex < i10) {
            if (this.indexes[fromIndex * 8] == iHashCodeLowerCase$default) {
                return fromIndex;
            }
            fromIndex++;
        }
        return -1;
    }

    public final CharSequence get(String name) {
        int iHashCodeLowerCase$default = CharsKt.hashCodeLowerCase$default(name, 0, 0, 3, null);
        int i10 = this.size;
        for (int i11 = 0; i11 < i10; i11++) {
            int i12 = i11 * 8;
            int[] iArr = this.indexes;
            if (iArr[i12] == iHashCodeLowerCase$default) {
                return this.builder.subSequence(iArr[i12 + 4], iArr[i12 + 5]);
            }
        }
        return null;
    }

    public final m<CharSequence> getAll(String name) {
        return new t(ga.r.I(new t(ga.r.M(0, new AnonymousClass1()), AnonymousClass2.INSTANCE), new AnonymousClass3(CharsKt.hashCodeLowerCase$default(name, 0, 0, 3, null))), new AnonymousClass4());
    }

    public final int getSize() {
        return this.size;
    }

    public final CharSequence nameAt(int idx) {
        if (idx < 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (idx >= this.size) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        int i10 = idx * 8;
        int[] iArr = this.indexes;
        return this.builder.subSequence(iArr[i10 + 2], iArr[i10 + 3]);
    }

    public final void put(int nameHash, int valueHash, int nameStartIndex, int nameEndIndex, int valueStartIndex, int valueEndIndex) {
        int i10 = this.size;
        int i11 = i10 * 8;
        int[] iArr = this.indexes;
        if (i11 >= iArr.length) {
            throw new x1("An operation is not implemented: Implement headers overflow");
        }
        iArr[i11] = nameHash;
        iArr[i11 + 1] = valueHash;
        iArr[i11 + 2] = nameStartIndex;
        iArr[i11 + 3] = nameEndIndex;
        iArr[i11 + 4] = valueStartIndex;
        iArr[i11 + 5] = valueEndIndex;
        iArr[i11 + 6] = -1;
        iArr[i11 + 7] = -1;
        this.size = i10 + 1;
    }

    public final void release() {
        this.size = 0;
        int[] iArr = this.indexes;
        this.indexes = HttpHeadersMapKt.EMPTY_INT_LIST;
        if (iArr != HttpHeadersMapKt.EMPTY_INT_LIST) {
            HttpHeadersMapKt.IntArrayPool.recycle(iArr);
        }
    }

    public String toString() throws IOException {
        StringBuilder sb2 = new StringBuilder();
        HttpHeadersMapKt.dumpTo(this, "", sb2);
        return sb2.toString();
    }

    public final CharSequence valueAt(int idx) {
        if (idx < 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (idx >= this.size) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        int i10 = idx * 8;
        int[] iArr = this.indexes;
        return this.builder.subSequence(iArr[i10 + 4], iArr[i10 + 5]);
    }
}
