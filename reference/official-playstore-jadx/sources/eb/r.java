package eb;

import androidx.fragment.app.a2;
import cb.b1;
import java.lang.annotation.Annotation;
import java.util.Map;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.json.internal.JsonDecodingException;
import kotlinx.serialization.json.internal.JsonEncodingException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final s f15173a = new s();

    public static final JsonEncodingException a(String str, Number number) {
        return new JsonEncodingException("Unexpected special floating-point value " + number + ". By default, non-finite floating point values are prohibited because they do not conform JSON specification. It is possible to deserialize them using 'JsonBuilder.allowSpecialFloatingPointValues = true'\nCurrent output: " + ((Object) m(str, -1)));
    }

    public static final JsonEncodingException b(ab.g gVar) {
        return new JsonEncodingException("Value of type '" + gVar.h() + "' can't be used in JSON as a key in the map. It should have either primitive or enum kind, but its kind is '" + gVar.getKind() + "'.\nUse 'allowStructuredMapKeys = true' in 'Json {}' builder to convert such maps to [key1, value1, key2, value2,...] arrays.");
    }

    public static final JsonDecodingException c(int i10, String str) {
        if (i10 >= 0) {
            str = "Unexpected JSON token at offset " + i10 + ": " + str;
        }
        return new JsonDecodingException(str);
    }

    public static final JsonDecodingException d(String str, CharSequence charSequence, int i10) {
        StringBuilder sbP = a2.p(str, "\nJSON input: ");
        sbP.append((Object) m(charSequence, i10));
        return c(i10, sbP.toString());
    }

    public static final void e(ya.s sVar, ya.s sVar2, String str) {
        if ((sVar instanceof ya.n) && b1.b(sVar2.getDescriptor()).contains(str)) {
            StringBuilder sbR = a2.r("Sealed class '", sVar2.getDescriptor().h(), "' cannot be serialized as base class '", ((ya.n) sVar).getDescriptor().h(), "' because it has property name that conflicts with JSON class discriminator '");
            sbR.append(str);
            sbR.append("'. You can either change class discriminator in JsonConfiguration, rename property with @SerialName annotation or fall back to array polymorphism");
            throw new IllegalStateException(sbR.toString().toString());
        }
    }

    public static final ab.g f(ab.g gVar, fb.b bVar) {
        if (!kotlin.jvm.internal.p.a(gVar.getKind(), ab.l.f816b)) {
            return gVar.isInline() ? f(gVar.g(0), bVar) : gVar;
        }
        if (t7.a.q(gVar) == null) {
            return gVar;
        }
        bVar.getClass();
        return gVar;
    }

    public static final byte g(char c10) {
        if (c10 < '~') {
            return i.f15150b[c10];
        }
        return (byte) 0;
    }

    public static final void h(a.a aVar) {
        if (aVar instanceof ab.m) {
            throw new IllegalStateException("Enums cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead");
        }
        if (aVar instanceof ab.f) {
            throw new IllegalStateException("Primitives cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead");
        }
        if (aVar instanceof ab.d) {
            throw new IllegalStateException("Actual serializer for polymorphic cannot be polymorphic itself");
        }
    }

    public static final String i(ab.g gVar, db.b bVar) {
        for (Annotation annotation : gVar.getAnnotations()) {
            if (annotation instanceof db.h) {
                return ((db.h) annotation).discriminator();
            }
        }
        return bVar.f14878a.f14907g;
    }

    public static final Object j(db.j jVar, ya.c cVar) {
        if (!(cVar instanceof cb.b)) {
            return cVar.deserialize(jVar);
        }
        db.i iVar = jVar.C().f14878a;
        String strI = i(cVar.getDescriptor(), jVar.C());
        db.m mVarE = jVar.e();
        ab.g descriptor = cVar.getDescriptor();
        if (mVarE instanceof db.b0) {
            db.b0 b0Var = (db.b0) mVarE;
            db.m mVar = (db.m) b0Var.get(strI);
            String strB = mVar != null ? db.n.g(mVar).b() : null;
            ya.c cVarA = ((cb.b) cVar).a(jVar, strB);
            if (cVarA == null) {
                throw d(androidx.compose.material3.d.C("Polymorphic serializer was not found for ", strB == null ? "missing class discriminator ('null')" : androidx.compose.material3.d.i('\'', "class discriminator '", strB)), b0Var.toString(), -1);
            }
            return j(new w(jVar.C(), b0Var, strI, cVarA.getDescriptor()), cVarA);
        }
        StringBuilder sb2 = new StringBuilder("Expected ");
        m0 m0Var = l0.f19747a;
        sb2.append(m0Var.b(db.b0.class));
        sb2.append(" as the serialized body of ");
        sb2.append(descriptor.h());
        sb2.append(", but had ");
        sb2.append(m0Var.b(mVarE.getClass()));
        throw c(-1, sb2.toString());
    }

    public static final int k(ab.g gVar, db.b bVar, String str) {
        n(gVar, bVar);
        int iC = gVar.c(str);
        if (iC != -3 || !bVar.f14878a.f14909i) {
            return iC;
        }
        Integer num = (Integer) ((Map) bVar.f14880c.K(gVar, f15173a, new a8.l0(gVar, bVar, 10))).get(str);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }

    public static final int l(ab.g gVar, db.b bVar, String str, String str2) {
        int iK = k(gVar, bVar, str);
        if (iK != -3) {
            return iK;
        }
        throw new SerializationException(gVar.h() + " does not contain element with name '" + str + '\'' + str2);
    }

    public static final CharSequence m(CharSequence charSequence, int i10) {
        if (charSequence.length() >= 200) {
            if (i10 != -1) {
                int i11 = i10 - 30;
                int i12 = i10 + 30;
                String str = i11 <= 0 ? "" : ".....";
                String str2 = i12 >= charSequence.length() ? "" : ".....";
                StringBuilder sbS = androidx.compose.material3.d.s(str);
                if (i11 < 0) {
                    i11 = 0;
                }
                int length = charSequence.length();
                if (i12 > length) {
                    i12 = length;
                }
                sbS.append(charSequence.subSequence(i11, i12).toString());
                sbS.append(str2);
                return sbS.toString();
            }
            int length2 = charSequence.length() - 60;
            if (length2 > 0) {
                return "....." + charSequence.subSequence(length2, charSequence.length()).toString();
            }
        }
        return charSequence;
    }

    public static final db.x n(ab.g gVar, db.b bVar) {
        if (kotlin.jvm.internal.p.a(gVar.getKind(), ab.n.f818b)) {
            db.i iVar = bVar.f14878a;
        }
        return null;
    }

    public static final int o(ab.g gVar, db.b bVar) {
        a.a kind = gVar.getKind();
        if (kind instanceof ab.d) {
            return 4;
        }
        if (kotlin.jvm.internal.p.a(kind, ab.n.f819c)) {
            return 2;
        }
        if (!kotlin.jvm.internal.p.a(kind, ab.n.f820d)) {
            return 1;
        }
        ab.g gVarF = f(gVar.g(0), bVar.f14879b);
        a.a kind2 = gVarF.getKind();
        if ((kind2 instanceof ab.f) || kotlin.jvm.internal.p.a(kind2, ab.m.f817b)) {
            return 3;
        }
        if (bVar.f14878a.f14904d) {
            return 2;
        }
        throw b(gVarF);
    }

    public static final String p(Number number, String str, String str2) {
        return "Unexpected special floating-point value " + number + " with key " + str + ". By default, non-finite floating point values are prohibited because they do not conform JSON specification. It is possible to deserialize them using 'JsonBuilder.allowSpecialFloatingPointValues = true'\nCurrent output: " + ((Object) m(str2, -1));
    }
}
