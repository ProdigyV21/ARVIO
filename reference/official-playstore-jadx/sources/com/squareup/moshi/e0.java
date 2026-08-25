package com.squareup.moshi;

import androidx.fragment.app.a2;
import java.io.EOFException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes4.dex */
public abstract class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f14422a = new a(5);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b0 f14423b = new b0(1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b0 f14424c = new b0(2);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b0 f14425d = new b0(3);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b0 f14426e = new b0(4);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b0 f14427f = new b0(5);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final b0 f14428g = new b0(6);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final b0 f14429h = new b0(7);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final b0 f14430i = new b0(8);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final b0 f14431j = new b0(0);

    public static Type a(Type type) {
        if (!Collection.class.isAssignableFrom(Collection.class)) {
            throw new IllegalArgumentException();
        }
        Type typeG = j6.e.g(type, Collection.class, j6.e.d(type, Collection.class, Collection.class), new LinkedHashSet());
        if (typeG instanceof WildcardType) {
            typeG = ((WildcardType) typeG).getUpperBounds()[0];
        }
        return typeG instanceof ParameterizedType ? ((ParameterizedType) typeG).getActualTypeArguments()[0] : Object.class;
    }

    public static boolean b(Type type, Type type2) {
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type2 instanceof GenericArrayType ? b(((Class) type).getComponentType(), ((GenericArrayType) type2).getGenericComponentType()) : type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            return b(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType instanceof j6.c ? ((j6.c) parameterizedType).f19232m : parameterizedType.getActualTypeArguments(), parameterizedType2 instanceof j6.c ? ((j6.c) parameterizedType2).f19232m : parameterizedType2.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            if (type2 instanceof Class) {
                return b(((Class) type2).getComponentType(), ((GenericArrayType) type).getGenericComponentType());
            }
            if (type2 instanceof GenericArrayType) {
                return b(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
            }
            return false;
        }
        if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            return Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds());
        }
        if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        }
        TypeVariable typeVariable = (TypeVariable) type;
        TypeVariable typeVariable2 = (TypeVariable) type2;
        return typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName());
    }

    public static String c(int i10, int[] iArr, String[] strArr, int[] iArr2) {
        StringBuilder sb2 = new StringBuilder("$");
        for (int i11 = 0; i11 < i10; i11++) {
            int i12 = iArr[i11];
            if (i12 == 1 || i12 == 2) {
                sb2.append('[');
                sb2.append(iArr2[i11]);
                sb2.append(']');
            } else if (i12 == 3 || i12 == 4 || i12 == 5) {
                sb2.append('.');
                String str = strArr[i11];
                if (str != null) {
                    sb2.append(str);
                }
            }
        }
        return sb2.toString();
    }

    public static Class d(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance((Class<?>) d(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return d(((WildcardType) type).getUpperBounds()[0]);
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
    }

    public static j6.c f(Class cls, Type... typeArr) {
        if (typeArr.length != 0) {
            return new j6.c(null, cls, typeArr);
        }
        throw new IllegalArgumentException("Missing type arguments for " + cls);
    }

    public static int g(p pVar, String str, int i10, int i11) throws EOFException {
        int iNextInt = pVar.nextInt();
        if (iNextInt >= i10 && iNextInt <= i11) {
            return iNextInt;
        }
        String strI = pVar.i();
        StringBuilder sbQ = a2.q("Expected ", str, " but was ", iNextInt, " at path ");
        sbQ.append(strI);
        throw new JsonDataException(sbQ.toString());
    }

    public abstract Object e();
}
