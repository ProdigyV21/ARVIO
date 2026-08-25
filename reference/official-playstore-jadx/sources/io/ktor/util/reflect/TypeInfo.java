package io.ktor.util.reflect;

import io.ktor.http.LinkHeader;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.reflect.d;
import kotlin.reflect.q;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002\u0012\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u0014\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0014\u0010\r\u001a\u00060\u0004j\u0002`\u0005HÆ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J8\u0010\u0011\u001a\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00022\f\b\u0002\u0010\u0006\u001a\u00060\u0004j\u0002`\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0017\u001a\u00020\u0016HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001b\u0010\u001cR\u001b\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001d\u001a\u0004\b\u001e\u0010\fR\u001b\u0010\u0006\u001a\u00060\u0004j\u0002`\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b \u0010\u000eR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010!\u001a\u0004\b\"\u0010\u0010¨\u0006#"}, d2 = {"Lio/ktor/util/reflect/TypeInfo;", "", "Lkotlin/reflect/d;", LinkHeader.Parameters.Type, "Ljava/lang/reflect/Type;", "Lio/ktor/util/reflect/Type;", "reifiedType", "Lkotlin/reflect/q;", "kotlinType", "<init>", "(Lkotlin/reflect/d;Ljava/lang/reflect/Type;Lkotlin/reflect/q;)V", "component1", "()Lkotlin/reflect/d;", "component2", "()Ljava/lang/reflect/Type;", "component3", "()Lkotlin/reflect/q;", "copy", "(Lkotlin/reflect/d;Ljava/lang/reflect/Type;Lkotlin/reflect/q;)Lio/ktor/util/reflect/TypeInfo;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lkotlin/reflect/d;", "getType", "Ljava/lang/reflect/Type;", "getReifiedType", "Lkotlin/reflect/q;", "getKotlinType", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class TypeInfo {
    private final q kotlinType;
    private final Type reifiedType;
    private final d<?> type;

    public TypeInfo(d<?> dVar, Type type, q qVar) {
        this.type = dVar;
        this.reifiedType = type;
        this.kotlinType = qVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TypeInfo copy$default(TypeInfo typeInfo, d dVar, Type type, q qVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            dVar = typeInfo.type;
        }
        if ((i10 & 2) != 0) {
            type = typeInfo.reifiedType;
        }
        if ((i10 & 4) != 0) {
            qVar = typeInfo.kotlinType;
        }
        return typeInfo.copy(dVar, type, qVar);
    }

    public final d<?> component1() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Type getReifiedType() {
        return this.reifiedType;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final q getKotlinType() {
        return this.kotlinType;
    }

    public final TypeInfo copy(d<?> type, Type reifiedType, q kotlinType) {
        return new TypeInfo(type, reifiedType, kotlinType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TypeInfo)) {
            return false;
        }
        TypeInfo typeInfo = (TypeInfo) other;
        return p.a(this.type, typeInfo.type) && p.a(this.reifiedType, typeInfo.reifiedType) && p.a(this.kotlinType, typeInfo.kotlinType);
    }

    public final q getKotlinType() {
        return this.kotlinType;
    }

    public final Type getReifiedType() {
        return this.reifiedType;
    }

    public final d<?> getType() {
        return this.type;
    }

    public int hashCode() {
        int iHashCode = (this.reifiedType.hashCode() + (this.type.hashCode() * 31)) * 31;
        q qVar = this.kotlinType;
        return iHashCode + (qVar == null ? 0 : qVar.hashCode());
    }

    public String toString() {
        return "TypeInfo(type=" + this.type + ", reifiedType=" + this.reifiedType + ", kotlinType=" + this.kotlinType + ')';
    }

    public /* synthetic */ TypeInfo(d dVar, Type type, q qVar, int i10, h hVar) {
        this(dVar, type, (i10 & 4) != 0 ? null : qVar);
    }
}
