package i5;

import android.util.Base64;
import android.util.JsonWriter;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.g;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class e implements com.google.firebase.encoders.e, g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f16226a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final JsonWriter f16227b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map f16228c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Map f16229d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final com.google.firebase.encoders.d f16230e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f16231f;

    public e(Writer writer, HashMap map, HashMap map2, a aVar, boolean z) {
        this.f16227b = new JsonWriter(writer);
        this.f16228c = map;
        this.f16229d = map2;
        this.f16230e = aVar;
        this.f16231f = z;
    }

    @Override // com.google.firebase.encoders.e
    public final com.google.firebase.encoders.e a(com.google.firebase.encoders.c cVar, boolean z) throws IOException {
        String str = cVar.f14367a;
        j();
        JsonWriter jsonWriter = this.f16227b;
        jsonWriter.name(str);
        j();
        jsonWriter.value(z);
        return this;
    }

    @Override // com.google.firebase.encoders.e
    public final com.google.firebase.encoders.e b(com.google.firebase.encoders.c cVar, long j10) throws IOException {
        String str = cVar.f14367a;
        j();
        JsonWriter jsonWriter = this.f16227b;
        jsonWriter.name(str);
        j();
        jsonWriter.value(j10);
        return this;
    }

    @Override // com.google.firebase.encoders.e
    public final com.google.firebase.encoders.e c(com.google.firebase.encoders.c cVar, int i10) throws IOException {
        String str = cVar.f14367a;
        j();
        JsonWriter jsonWriter = this.f16227b;
        jsonWriter.name(str);
        j();
        jsonWriter.value(i10);
        return this;
    }

    @Override // com.google.firebase.encoders.e
    public final com.google.firebase.encoders.e d(com.google.firebase.encoders.c cVar, double d4) throws IOException {
        String str = cVar.f14367a;
        j();
        JsonWriter jsonWriter = this.f16227b;
        jsonWriter.name(str);
        j();
        jsonWriter.value(d4);
        return this;
    }

    @Override // com.google.firebase.encoders.g
    public final g e(String str) throws IOException {
        j();
        this.f16227b.value(str);
        return this;
    }

    @Override // com.google.firebase.encoders.e
    public final com.google.firebase.encoders.e f(com.google.firebase.encoders.c cVar, Object obj) throws IOException {
        i(obj, cVar.f14367a);
        return this;
    }

    @Override // com.google.firebase.encoders.g
    public final g g(boolean z) throws IOException {
        j();
        this.f16227b.value(z);
        return this;
    }

    public final e h(Object obj) {
        JsonWriter jsonWriter = this.f16227b;
        if (obj == null) {
            jsonWriter.nullValue();
            return this;
        }
        if (obj instanceof Number) {
            jsonWriter.value((Number) obj);
            return this;
        }
        int i10 = 0;
        if (!obj.getClass().isArray()) {
            if (obj instanceof Collection) {
                jsonWriter.beginArray();
                Iterator it = ((Collection) obj).iterator();
                while (it.hasNext()) {
                    h(it.next());
                }
                jsonWriter.endArray();
                return this;
            }
            if (obj instanceof Map) {
                jsonWriter.beginObject();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    Object key = entry.getKey();
                    try {
                        i(entry.getValue(), (String) key);
                    } catch (ClassCastException e5) {
                        throw new EncodingException(String.format("Only String keys are currently supported in maps, got %s of type %s instead.", key, key.getClass()), e5);
                    }
                }
                jsonWriter.endObject();
                return this;
            }
            com.google.firebase.encoders.d dVar = (com.google.firebase.encoders.d) this.f16228c.get(obj.getClass());
            if (dVar != null) {
                jsonWriter.beginObject();
                dVar.encode(obj, this);
                jsonWriter.endObject();
                return this;
            }
            com.google.firebase.encoders.f fVar = (com.google.firebase.encoders.f) this.f16229d.get(obj.getClass());
            if (fVar != null) {
                fVar.encode(obj, this);
                return this;
            }
            if (!(obj instanceof Enum)) {
                jsonWriter.beginObject();
                this.f16230e.encode(obj, this);
                jsonWriter.endObject();
                return this;
            }
            if (obj instanceof f) {
                int number = ((f) obj).getNumber();
                j();
                jsonWriter.value(number);
                return this;
            }
            String strName = ((Enum) obj).name();
            j();
            jsonWriter.value(strName);
            return this;
        }
        if (obj instanceof byte[]) {
            j();
            jsonWriter.value(Base64.encodeToString((byte[]) obj, 2));
            return this;
        }
        jsonWriter.beginArray();
        if (obj instanceof int[]) {
            int length = ((int[]) obj).length;
            while (i10 < length) {
                jsonWriter.value(r7[i10]);
                i10++;
            }
        } else if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            int length2 = jArr.length;
            while (i10 < length2) {
                long j10 = jArr[i10];
                j();
                jsonWriter.value(j10);
                i10++;
            }
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            int length3 = dArr.length;
            while (i10 < length3) {
                jsonWriter.value(dArr[i10]);
                i10++;
            }
        } else if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            int length4 = zArr.length;
            while (i10 < length4) {
                jsonWriter.value(zArr[i10]);
                i10++;
            }
        } else if (obj instanceof Number[]) {
            Number[] numberArr = (Number[]) obj;
            int length5 = numberArr.length;
            while (i10 < length5) {
                h(numberArr[i10]);
                i10++;
            }
        } else {
            Object[] objArr = (Object[]) obj;
            int length6 = objArr.length;
            while (i10 < length6) {
                h(objArr[i10]);
                i10++;
            }
        }
        jsonWriter.endArray();
        return this;
    }

    public final e i(Object obj, String str) throws IOException {
        boolean z = this.f16231f;
        JsonWriter jsonWriter = this.f16227b;
        if (z) {
            if (obj == null) {
                return this;
            }
            j();
            jsonWriter.name(str);
            h(obj);
            return this;
        }
        j();
        jsonWriter.name(str);
        if (obj == null) {
            jsonWriter.nullValue();
            return this;
        }
        h(obj);
        return this;
    }

    public final void j() {
        if (!this.f16226a) {
            throw new IllegalStateException("Parent context used since this context was created. Cannot use this context anymore.");
        }
    }
}
