package io.ktor.http.cio;

import f4.f;
import ga.t;
import io.ktor.http.ContentDisposition;
import io.ktor.http.Headers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.e0;
import kotlin.collections.x;
import kotlin.jvm.internal.r;
import qb.d;
import r7.l;
import r7.p;
import s7.a;
import x6.s;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010&\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001\u001bB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u0007H\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0014\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\r0\u00130\u0006H\u0016¢\u0006\u0004\b\u0014\u0010\tR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0015R!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\tR\u0014\u0010\u001a\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0012¨\u0006\u001c"}, d2 = {"Lio/ktor/http/cio/CIOHeaders;", "Lio/ktor/http/Headers;", "Lio/ktor/http/cio/HttpHeadersMap;", "headers", "<init>", "(Lio/ktor/http/cio/HttpHeadersMap;)V", "", "", "names", "()Ljava/util/Set;", ContentDisposition.Parameters.Name, "get", "(Ljava/lang/String;)Ljava/lang/String;", "", "getAll", "(Ljava/lang/String;)Ljava/util/List;", "", "isEmpty", "()Z", "", "entries", "Lio/ktor/http/cio/HttpHeadersMap;", "names$delegate", "Lx6/s;", "getNames", "getCaseInsensitiveName", "caseInsensitiveName", "Entry", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CIOHeaders implements Headers {
    private final HttpHeadersMap headers;

    /* JADX INFO: renamed from: names$delegate, reason: from kotlin metadata */
    private final s names = f.o(3, new AnonymousClass2());

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0082\u0004\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lio/ktor/http/cio/CIOHeaders$Entry;", "", "", "", "idx", "", "(Lio/ktor/http/cio/CIOHeaders;I)V", "key", "getKey", "()Ljava/lang/String;", "value", "getValue", "()Ljava/util/List;", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class Entry implements Map.Entry<String, List<? extends String>>, a {
        private final int idx;

        public Entry(int i10) {
            this.idx = i10;
        }

        @Override // java.util.Map.Entry
        public /* bridge */ /* synthetic */ List<? extends String> setValue(List<? extends String> list) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map.Entry
        public String getKey() {
            return CIOHeaders.this.headers.nameAt(this.idx).toString();
        }

        @Override // java.util.Map.Entry
        public List<? extends String> getValue() {
            return Collections.singletonList(CIOHeaders.this.headers.valueAt(this.idx).toString());
        }

        /* JADX INFO: renamed from: setValue, reason: avoid collision after fix types in other method */
        public List<String> setValue2(List<String> list) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.CIOHeaders$getAll$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<CharSequence, String> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final String invoke(CharSequence charSequence) {
            return charSequence.toString();
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.CIOHeaders$names$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Ljava/util/LinkedHashSet;", "", "Lkotlin/collections/LinkedHashSet;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements r7.a<LinkedHashSet<String>> {
        public AnonymousClass2() {
            super(0);
        }

        @Override // r7.a
        public final LinkedHashSet<String> invoke() {
            LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(CIOHeaders.this.headers.getSize());
            CIOHeaders cIOHeaders = CIOHeaders.this;
            int size = cIOHeaders.headers.getSize();
            for (int i10 = 0; i10 < size; i10++) {
                linkedHashSet.add(cIOHeaders.headers.nameAt(i10).toString());
            }
            return linkedHashSet;
        }
    }

    public CIOHeaders(HttpHeadersMap httpHeadersMap) {
        this.headers = httpHeadersMap;
    }

    private final Set<String> getNames() {
        return (Set) this.names.getValue();
    }

    @Override // io.ktor.util.StringValues
    public boolean contains(String str) {
        return Headers.DefaultImpls.contains(this, str);
    }

    @Override // io.ktor.util.StringValues
    public Set<Map.Entry<String, List<String>>> entries() {
        i iVarX = d.X(0, this.headers.getSize());
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iVarX, 10));
        Iterator it = iVarX.iterator();
        while (it.hasNext()) {
            arrayList.add(new Entry(((e0) it).nextInt()));
        }
        return x.g1(arrayList);
    }

    @Override // io.ktor.util.StringValues
    public void forEach(p<? super String, ? super List<String>, t0> pVar) {
        Headers.DefaultImpls.forEach(this, pVar);
    }

    @Override // io.ktor.util.StringValues
    public String get(String name) {
        CharSequence charSequence = this.headers.get(name);
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    @Override // io.ktor.util.StringValues
    public List<String> getAll(String name) {
        List<String> listU = ga.r.U(new t(this.headers.getAll(name), AnonymousClass1.INSTANCE));
        if (listU.isEmpty()) {
            return null;
        }
        return listU;
    }

    @Override // io.ktor.util.StringValues
    public boolean getCaseInsensitiveName() {
        return true;
    }

    @Override // io.ktor.util.StringValues
    public boolean isEmpty() {
        return this.headers.getSize() == 0;
    }

    @Override // io.ktor.util.StringValues
    public Set<String> names() {
        return getNames();
    }

    @Override // io.ktor.util.StringValues
    public boolean contains(String str, String str2) {
        return Headers.DefaultImpls.contains(this, str, str2);
    }
}
