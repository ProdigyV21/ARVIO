package j5;

import com.google.firebase.encoders.EncodingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class f implements com.google.firebase.encoders.e {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Charset f19214f = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f19215g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f19216h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final i5.a f19217i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public OutputStream f19218a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f19219b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap f19220c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final com.google.firebase.encoders.d f19221d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final h f19222e = new h(this);

    static {
        a aVar = new a(1);
        HashMap map = new HashMap();
        map.put(e.class, aVar);
        f19215g = new com.google.firebase.encoders.c("key", Collections.unmodifiableMap(new HashMap(map)));
        a aVar2 = new a(2);
        HashMap map2 = new HashMap();
        map2.put(e.class, aVar2);
        f19216h = new com.google.firebase.encoders.c("value", Collections.unmodifiableMap(new HashMap(map2)));
        f19217i = new i5.a(1);
    }

    public f(ByteArrayOutputStream byteArrayOutputStream, HashMap map, HashMap map2, com.google.firebase.encoders.d dVar) {
        this.f19218a = byteArrayOutputStream;
        this.f19219b = map;
        this.f19220c = map2;
        this.f19221d = dVar;
    }

    public static int k(com.google.firebase.encoders.c cVar) {
        e eVar = (e) ((Annotation) cVar.f14368b.get(e.class));
        if (eVar != null) {
            return eVar.tag();
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    @Override // com.google.firebase.encoders.e
    public final com.google.firebase.encoders.e a(com.google.firebase.encoders.c cVar, boolean z) {
        h(cVar, z ? 1 : 0, true);
        return this;
    }

    @Override // com.google.firebase.encoders.e
    public final com.google.firebase.encoders.e b(com.google.firebase.encoders.c cVar, long j10) throws IOException {
        i(cVar, j10, true);
        return this;
    }

    @Override // com.google.firebase.encoders.e
    public final com.google.firebase.encoders.e c(com.google.firebase.encoders.c cVar, int i10) {
        h(cVar, i10, true);
        return this;
    }

    @Override // com.google.firebase.encoders.e
    public final com.google.firebase.encoders.e d(com.google.firebase.encoders.c cVar, double d4) throws IOException {
        g(cVar, d4, true);
        return this;
    }

    public final f e(com.google.firebase.encoders.c cVar, Object obj, boolean z) {
        if (obj != null) {
            if (obj instanceof CharSequence) {
                CharSequence charSequence = (CharSequence) obj;
                if (!z || charSequence.length() != 0) {
                    l((k(cVar) << 3) | 2);
                    byte[] bytes = charSequence.toString().getBytes(f19214f);
                    l(bytes.length);
                    this.f19218a.write(bytes);
                    return this;
                }
            } else if (obj instanceof Collection) {
                Iterator it = ((Collection) obj).iterator();
                while (it.hasNext()) {
                    e(cVar, it.next(), false);
                }
            } else if (obj instanceof Map) {
                Iterator it2 = ((Map) obj).entrySet().iterator();
                while (it2.hasNext()) {
                    j(f19217i, cVar, (Map.Entry) it2.next(), false);
                }
            } else {
                if (obj instanceof Double) {
                    g(cVar, ((Double) obj).doubleValue(), z);
                    return this;
                }
                if (obj instanceof Float) {
                    float fFloatValue = ((Float) obj).floatValue();
                    if (!z || fFloatValue != 0.0f) {
                        l((k(cVar) << 3) | 5);
                        this.f19218a.write(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(fFloatValue).array());
                        return this;
                    }
                } else {
                    if (obj instanceof Number) {
                        i(cVar, ((Number) obj).longValue(), z);
                        return this;
                    }
                    if (obj instanceof Boolean) {
                        h(cVar, ((Boolean) obj).booleanValue() ? 1 : 0, z);
                        return this;
                    }
                    if (!(obj instanceof byte[])) {
                        com.google.firebase.encoders.d dVar = (com.google.firebase.encoders.d) this.f19219b.get(obj.getClass());
                        if (dVar != null) {
                            j(dVar, cVar, obj, z);
                            return this;
                        }
                        com.google.firebase.encoders.f fVar = (com.google.firebase.encoders.f) this.f19220c.get(obj.getClass());
                        if (fVar != null) {
                            h hVar = this.f19222e;
                            hVar.f19224a = false;
                            hVar.f19226c = cVar;
                            hVar.f19225b = z;
                            fVar.encode(obj, hVar);
                            return this;
                        }
                        if (obj instanceof c) {
                            h(cVar, ((c) obj).getNumber(), true);
                            return this;
                        }
                        if (obj instanceof Enum) {
                            h(cVar, ((Enum) obj).ordinal(), true);
                            return this;
                        }
                        j(this.f19221d, cVar, obj, z);
                        return this;
                    }
                    byte[] bArr = (byte[]) obj;
                    if (!z || bArr.length != 0) {
                        l((k(cVar) << 3) | 2);
                        l(bArr.length);
                        this.f19218a.write(bArr);
                        return this;
                    }
                }
            }
        }
        return this;
    }

    @Override // com.google.firebase.encoders.e
    public final com.google.firebase.encoders.e f(com.google.firebase.encoders.c cVar, Object obj) {
        e(cVar, obj, true);
        return this;
    }

    public final void g(com.google.firebase.encoders.c cVar, double d4, boolean z) throws IOException {
        if (z && d4 == 0.0d) {
            return;
        }
        l((k(cVar) << 3) | 1);
        this.f19218a.write(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(d4).array());
    }

    public final void h(com.google.firebase.encoders.c cVar, int i10, boolean z) {
        if (z && i10 == 0) {
            return;
        }
        e eVar = (e) ((Annotation) cVar.f14368b.get(e.class));
        if (eVar == null) {
            throw new EncodingException("Field has no @Protobuf config");
        }
        int iOrdinal = eVar.intEncoding().ordinal();
        if (iOrdinal == 0) {
            l(eVar.tag() << 3);
            l(i10);
        } else if (iOrdinal == 1) {
            l(eVar.tag() << 3);
            l((i10 << 1) ^ (i10 >> 31));
        } else {
            if (iOrdinal != 2) {
                return;
            }
            l((eVar.tag() << 3) | 5);
            this.f19218a.write(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(i10).array());
        }
    }

    public final void i(com.google.firebase.encoders.c cVar, long j10, boolean z) throws IOException {
        if (z && j10 == 0) {
            return;
        }
        e eVar = (e) ((Annotation) cVar.f14368b.get(e.class));
        if (eVar == null) {
            throw new EncodingException("Field has no @Protobuf config");
        }
        int iOrdinal = eVar.intEncoding().ordinal();
        if (iOrdinal == 0) {
            l(eVar.tag() << 3);
            m(j10);
        } else if (iOrdinal == 1) {
            l(eVar.tag() << 3);
            m((j10 >> 63) ^ (j10 << 1));
        } else {
            if (iOrdinal != 2) {
                return;
            }
            l((eVar.tag() << 3) | 1);
            this.f19218a.write(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(j10).array());
        }
    }

    public final void j(com.google.firebase.encoders.d dVar, com.google.firebase.encoders.c cVar, Object obj, boolean z) throws IOException {
        b bVar = new b();
        bVar.f19211i = 0L;
        try {
            OutputStream outputStream = this.f19218a;
            this.f19218a = bVar;
            try {
                dVar.encode(obj, this);
                this.f19218a = outputStream;
                long j10 = bVar.f19211i;
                bVar.close();
                if (z && j10 == 0) {
                    return;
                }
                l((k(cVar) << 3) | 2);
                m(j10);
                dVar.encode(obj, this);
            } catch (Throwable th) {
                this.f19218a = outputStream;
                throw th;
            }
        } catch (Throwable th2) {
            try {
                bVar.close();
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
            throw th2;
        }
    }

    public final void l(int i10) throws IOException {
        while ((i10 & (-128)) != 0) {
            this.f19218a.write((i10 & 127) | 128);
            i10 >>>= 7;
        }
        this.f19218a.write(i10 & 127);
    }

    public final void m(long j10) throws IOException {
        while (((-128) & j10) != 0) {
            this.f19218a.write((((int) j10) & 127) | 128);
            j10 >>>= 7;
        }
        this.f19218a.write(((int) j10) & 127);
    }
}
