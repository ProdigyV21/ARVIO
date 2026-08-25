package c1;

import androidx.datastore.core.CorruptionException;
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException;
import androidx.datastore.preferences.protobuf.r0;
import androidx.datastore.preferences.protobuf.t;
import androidx.datastore.preferences.protobuf.u;
import b1.j;
import h.f0;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.x;
import kotlin.jvm.internal.p;
import z0.k;

/* JADX INFO: loaded from: classes3.dex */
public final class i implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i f7377a = new i();

    public final b a(FileInputStream fileInputStream) throws CorruptionException {
        try {
            b1.d dVarQ = b1.d.q(fileInputStream);
            b bVar = new b(false);
            f[] fVarArr = (f[]) Arrays.copyOf(new f[0], 0);
            bVar.b();
            if (fVarArr.length > 0) {
                f fVar = fVarArr[0];
                throw null;
            }
            for (Map.Entry entry : dVarQ.o().entrySet()) {
                String str = (String) entry.getKey();
                j jVar = (j) entry.getValue();
                int iC = jVar.C();
                switch (iC == 0 ? -1 : h.f7376a[f0.c(iC)]) {
                    case -1:
                        throw new CorruptionException("Value case is null.", null);
                    case 0:
                    default:
                        throw new NoWhenBranchMatchedException();
                    case 1:
                        bVar.d(new e(str), Boolean.valueOf(jVar.u()));
                        break;
                    case 2:
                        bVar.d(new e(str), Float.valueOf(jVar.x()));
                        break;
                    case 3:
                        bVar.d(new e(str), Double.valueOf(jVar.w()));
                        break;
                    case 4:
                        bVar.d(new e(str), Integer.valueOf(jVar.y()));
                        break;
                    case 5:
                        bVar.d(new e(str), Long.valueOf(jVar.z()));
                        break;
                    case 6:
                        bVar.d(new e(str), jVar.A());
                        break;
                    case 7:
                        bVar.d(new e(str), x.g1(jVar.B().p()));
                        break;
                    case 8:
                        throw new CorruptionException("Value not set.", null);
                }
            }
            return new b(new LinkedHashMap(Collections.unmodifiableMap(bVar.f7368a)), true);
        } catch (InvalidProtocolBufferException e5) {
            throw new CorruptionException("Unable to parse preferences proto.", e5);
        }
    }

    public final void b(Object obj, xb.i iVar) {
        r0 r0VarF;
        Map mapUnmodifiableMap = Collections.unmodifiableMap(((b) ((g) obj)).f7368a);
        b1.b bVarP = b1.d.p();
        for (Map.Entry entry : mapUnmodifiableMap.entrySet()) {
            e eVar = (e) entry.getKey();
            Object value = entry.getValue();
            String str = eVar.f7375a;
            if (value instanceof Boolean) {
                b1.i iVarD = j.D();
                boolean zBooleanValue = ((Boolean) value).booleanValue();
                iVarD.h();
                j.r((j) iVarD.f2483l, zBooleanValue);
                r0VarF = iVarD.f();
            } else if (value instanceof Float) {
                b1.i iVarD2 = j.D();
                float fFloatValue = ((Number) value).floatValue();
                iVarD2.h();
                j.s((j) iVarD2.f2483l, fFloatValue);
                r0VarF = iVarD2.f();
            } else if (value instanceof Double) {
                b1.i iVarD3 = j.D();
                double dDoubleValue = ((Number) value).doubleValue();
                iVarD3.h();
                j.q((j) iVarD3.f2483l, dDoubleValue);
                r0VarF = iVarD3.f();
            } else if (value instanceof Integer) {
                b1.i iVarD4 = j.D();
                int iIntValue = ((Number) value).intValue();
                iVarD4.h();
                j.t((j) iVarD4.f2483l, iIntValue);
                r0VarF = iVarD4.f();
            } else if (value instanceof Long) {
                b1.i iVarD5 = j.D();
                long jLongValue = ((Number) value).longValue();
                iVarD5.h();
                j.n((j) iVarD5.f2483l, jLongValue);
                r0VarF = iVarD5.f();
            } else if (value instanceof String) {
                b1.i iVarD6 = j.D();
                iVarD6.h();
                j.o((j) iVarD6.f2483l, (String) value);
                r0VarF = iVarD6.f();
            } else {
                if (!(value instanceof Set)) {
                    throw new IllegalStateException(p.g(value.getClass().getName(), "PreferencesSerializer does not support type: "));
                }
                b1.i iVarD7 = j.D();
                b1.f fVarQ = b1.g.q();
                fVarQ.h();
                b1.g.n((b1.g) fVarQ.f2483l, (Set) value);
                iVarD7.h();
                j.p((j) iVarD7.f2483l, fVarQ);
                r0VarF = iVarD7.f();
            }
            bVarP.getClass();
            str.getClass();
            bVarP.h();
            b1.d.n((b1.d) bVarP.f2483l).put(str, (j) r0VarF);
        }
        b1.d dVar = (b1.d) bVarP.f();
        int iA = dVar.a();
        Logger logger = u.f2533d;
        if (iA > 4096) {
            iA = 4096;
        }
        t tVar = new t(iVar, iA);
        dVar.d(tVar);
        if (tVar.f2531h > 0) {
            tVar.X();
        }
    }
}
