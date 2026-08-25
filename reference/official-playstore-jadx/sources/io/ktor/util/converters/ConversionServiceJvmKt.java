package io.ktor.util.converters;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import kotlin.jvm.internal.p;
import kotlin.reflect.d;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\u001a%\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0001\u001a\u00020\u00002\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a%\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0001\u001a\u00020\u00002\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0002¢\u0006\u0004\b\u0007\u0010\u0006\u001a\u001f\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\b2\u0006\u0010\u0001\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"", "value", "Lkotlin/reflect/d;", "klass", "", "platformDefaultFromValues", "(Ljava/lang/String;Lkotlin/reflect/d;)Ljava/lang/Object;", "convertSimpleTypes", "", "platformDefaultToValues", "(Ljava/lang/Object;)Ljava/util/List;", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ConversionServiceJvmKt {
    private static final Object convertSimpleTypes(String str, d<?> dVar) {
        m0 m0Var = l0.f19747a;
        if (p.a(dVar, m0Var.b(Integer.class))) {
            return Integer.valueOf(Integer.parseInt(str));
        }
        if (p.a(dVar, m0Var.b(Float.class))) {
            return Float.valueOf(Float.parseFloat(str));
        }
        if (p.a(dVar, m0Var.b(Double.class))) {
            return Double.valueOf(Double.parseDouble(str));
        }
        if (p.a(dVar, m0Var.b(Long.class))) {
            return Long.valueOf(Long.parseLong(str));
        }
        if (p.a(dVar, m0Var.b(Short.class))) {
            return Short.valueOf(Short.parseShort(str));
        }
        if (p.a(dVar, m0Var.b(Boolean.class))) {
            return Boolean.valueOf(Boolean.parseBoolean(str));
        }
        if (p.a(dVar, m0Var.b(String.class))) {
            return str;
        }
        if (p.a(dVar, m0Var.b(Character.class))) {
            return Character.valueOf(str.charAt(0));
        }
        if (p.a(dVar, m0Var.b(BigDecimal.class))) {
            return new BigDecimal(str);
        }
        if (p.a(dVar, m0Var.b(BigInteger.class))) {
            return new BigInteger(str);
        }
        if (p.a(dVar, m0Var.b(UUID.class))) {
            return UUID.fromString(str);
        }
        return null;
    }

    public static final Object platformDefaultFromValues(String str, d<?> dVar) throws DataConversionException {
        Object objConvertSimpleTypes = convertSimpleTypes(str, dVar);
        if (objConvertSimpleTypes != null) {
            return objConvertSimpleTypes;
        }
        f fVar = (f) dVar;
        Object obj = null;
        if (!fVar.c().isEnum()) {
            return null;
        }
        Object[] enumConstants = fVar.c().getEnumConstants();
        if (enumConstants != null) {
            int length = enumConstants.length;
            int i10 = 0;
            while (true) {
                if (i10 >= length) {
                    break;
                }
                Object obj2 = enumConstants[i10];
                if (p.a(((Enum) obj2).name(), str)) {
                    obj = obj2;
                    break;
                }
                i10++;
            }
            if (obj != null) {
                return obj;
            }
        }
        throw new DataConversionException("Value " + str + " is not a enum member name of " + dVar);
    }

    public static final List<String> platformDefaultToValues(Object obj) {
        if (obj instanceof Enum) {
            return Collections.singletonList(((Enum) obj).name());
        }
        if (obj instanceof Integer) {
            return Collections.singletonList(obj.toString());
        }
        if (obj instanceof Float) {
            return Collections.singletonList(obj.toString());
        }
        if (obj instanceof Double) {
            return Collections.singletonList(obj.toString());
        }
        if (obj instanceof Long) {
            return Collections.singletonList(obj.toString());
        }
        if (obj instanceof Boolean) {
            return Collections.singletonList(obj.toString());
        }
        if (obj instanceof Short) {
            return Collections.singletonList(obj.toString());
        }
        if (obj instanceof String) {
            return Collections.singletonList(obj.toString());
        }
        if (obj instanceof Character) {
            return Collections.singletonList(obj.toString());
        }
        if (obj instanceof BigDecimal) {
            return Collections.singletonList(obj.toString());
        }
        if (obj instanceof BigInteger) {
            return Collections.singletonList(obj.toString());
        }
        if (obj instanceof UUID) {
            return Collections.singletonList(obj.toString());
        }
        return null;
    }
}
