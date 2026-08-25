package io.ktor.serialization.kotlinx.json;

import io.ktor.util.reflect.TypeInfo;
import io.ktor.util.reflect.TypeInfoJvmKt;
import kotlin.Metadata;
import kotlin.reflect.d;
import kotlin.reflect.q;
import kotlin.reflect.t;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"argumentTypeInfo", "Lio/ktor/util/reflect/TypeInfo;", "ktor-serialization-kotlinx-json"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KotlinxSerializationJsonExtensionsKt {
    public static final TypeInfo argumentTypeInfo(TypeInfo typeInfo) {
        q qVar = ((t) typeInfo.getKotlinType().g().get(0)).f19912b;
        return new TypeInfo((d) qVar.b(), TypeInfoJvmKt.getPlatformType(qVar), qVar);
    }
}
