package io.ktor.util.converters;

import a0.c;
import io.ktor.http.LinkHeader;
import io.ktor.util.reflect.TypeInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import kotlin.jvm.internal.p;
import kotlin.reflect.d;
import kotlin.reflect.e;
import kotlin.reflect.q;
import kotlin.reflect.t;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\t\u001a\u0004\u0018\u00010\b2\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J'\u0010\u0015\u001a\u0004\u0018\u00010\b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J!\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lio/ktor/util/converters/DefaultConversionService;", "Lio/ktor/util/converters/ConversionService;", "<init>", "()V", "Lkotlin/reflect/d;", "klass", "", "value", "", "convertPrimitives", "(Lkotlin/reflect/d;Ljava/lang/String;)Ljava/lang/Object;", "typeName", "", "throwConversionException", "(Ljava/lang/String;)Ljava/lang/Void;", "", "toValues", "(Ljava/lang/Object;)Ljava/util/List;", "values", "Lio/ktor/util/reflect/TypeInfo;", LinkHeader.Parameters.Type, "fromValues", "(Ljava/util/List;Lio/ktor/util/reflect/TypeInfo;)Ljava/lang/Object;", "fromValue", "(Ljava/lang/String;Lkotlin/reflect/d;)Ljava/lang/Object;", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DefaultConversionService implements ConversionService {
    public static final DefaultConversionService INSTANCE = new DefaultConversionService();

    private DefaultConversionService() {
    }

    private final Object convertPrimitives(d<?> klass, String value) {
        m0 m0Var = l0.f19747a;
        if (p.a(klass, m0Var.b(Integer.TYPE))) {
            return Integer.valueOf(Integer.parseInt(value));
        }
        if (p.a(klass, m0Var.b(Float.TYPE))) {
            return Float.valueOf(Float.parseFloat(value));
        }
        if (p.a(klass, m0Var.b(Double.TYPE))) {
            return Double.valueOf(Double.parseDouble(value));
        }
        if (p.a(klass, m0Var.b(Long.TYPE))) {
            return Long.valueOf(Long.parseLong(value));
        }
        if (p.a(klass, m0Var.b(Short.TYPE))) {
            return Short.valueOf(Short.parseShort(value));
        }
        if (p.a(klass, m0Var.b(Character.TYPE))) {
            return Character.valueOf(o.v0(value));
        }
        if (p.a(klass, m0Var.b(Boolean.TYPE))) {
            return Boolean.valueOf(Boolean.parseBoolean(value));
        }
        if (p.a(klass, m0Var.b(String.class))) {
            return value;
        }
        return null;
    }

    private final Void throwConversionException(String typeName) throws DataConversionException {
        throw new DataConversionException(c.l("Type ", typeName, " is not supported in default data conversion service"));
    }

    public final Object fromValue(String value, d<?> klass) throws DataConversionException {
        Object objConvertPrimitives = convertPrimitives(klass, value);
        if (objConvertPrimitives != null) {
            return objConvertPrimitives;
        }
        Object objPlatformDefaultFromValues = ConversionServiceJvmKt.platformDefaultFromValues(value, klass);
        if (objPlatformDefaultFromValues != null) {
            return objPlatformDefaultFromValues;
        }
        throwConversionException(klass.toString());
        throw new KotlinNothingValueException();
    }

    @Override // io.ktor.util.converters.ConversionService
    public Object fromValues(List<String> values, TypeInfo type) throws DataConversionException {
        List listG;
        t tVar;
        q qVar;
        if (values.isEmpty()) {
            return null;
        }
        d<?> type2 = type.getType();
        m0 m0Var = l0.f19747a;
        if (p.a(type2, m0Var.b(List.class)) || p.a(type.getType(), m0Var.b(List.class))) {
            q kotlinType = type.getKotlinType();
            e eVarB = (kotlinType == null || (listG = kotlinType.g()) == null || (tVar = (t) x.R0(listG)) == null || (qVar = tVar.f19912b) == null) ? null : qVar.b();
            d<?> dVar = eVarB instanceof d ? (d) eVarB : null;
            if (dVar != null) {
                ArrayList arrayList = new ArrayList(s.U(values, 10));
                Iterator<T> it = values.iterator();
                while (it.hasNext()) {
                    arrayList.add(INSTANCE.fromValue((String) it.next(), dVar));
                }
                return arrayList;
            }
        }
        if (values.isEmpty()) {
            throw new DataConversionException("There are no values when trying to construct single value " + type);
        }
        if (values.size() <= 1) {
            return fromValue((String) x.R0(values), type.getType());
        }
        throw new DataConversionException("There are multiple values when trying to construct single value " + type);
    }

    @Override // io.ktor.util.converters.ConversionService
    public List<String> toValues(Object value) throws DataConversionException {
        if (value == null) {
            return z.f19728i;
        }
        List<String> listPlatformDefaultToValues = ConversionServiceJvmKt.platformDefaultToValues(value);
        if (listPlatformDefaultToValues != null) {
            return listPlatformDefaultToValues;
        }
        if (value instanceof Iterable) {
            ArrayList arrayList = new ArrayList();
            Iterator it = ((Iterable) value).iterator();
            while (it.hasNext()) {
                x.b0(arrayList, INSTANCE.toValues(it.next()));
            }
            return arrayList;
        }
        Class<?> cls = value.getClass();
        m0 m0Var = l0.f19747a;
        d dVarB = m0Var.b(cls);
        if (dVarB.equals(m0Var.b(Integer.TYPE)) ? true : dVarB.equals(m0Var.b(Float.TYPE)) ? true : dVarB.equals(m0Var.b(Double.TYPE)) ? true : dVarB.equals(m0Var.b(Long.TYPE)) ? true : dVarB.equals(m0Var.b(Short.TYPE)) ? true : dVarB.equals(m0Var.b(Character.TYPE)) ? true : dVarB.equals(m0Var.b(Boolean.TYPE)) ? true : dVarB.equals(m0Var.b(String.class))) {
            return Collections.singletonList(value.toString());
        }
        throw new DataConversionException("Class " + dVarB + " is not supported in default data conversion service");
    }
}
