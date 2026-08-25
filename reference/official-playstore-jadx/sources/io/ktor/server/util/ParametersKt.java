package io.ktor.server.util;

import io.ktor.http.ContentDisposition;
import io.ktor.http.Parameters;
import io.ktor.server.plugins.MissingRequestParameterException;
import io.ktor.server.plugins.ParameterConversionException;
import io.ktor.util.converters.DefaultConversionService;
import io.ktor.util.reflect.TypeInfo;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.reflect.m;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a6\u0010\u0006\u001a\u00028\u0000\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00002\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0086\n¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001c\u0010\n\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0086\b¢\u0006\u0004\b\n\u0010\u000b\u001a(\u0010\n\u001a\u00028\u0000\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0086\b¢\u0006\u0004\b\n\u0010\f\u001a-\u0010\u000f\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00020\u00022\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\rH\u0001¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"", "R", "Lio/ktor/http/Parameters;", "thisRef", "Lkotlin/reflect/m;", "property", "getValue", "(Lio/ktor/http/Parameters;Ljava/lang/Object;Lkotlin/reflect/m;)Ljava/lang/Object;", "", ContentDisposition.Parameters.Name, "getOrFail", "(Lio/ktor/http/Parameters;Ljava/lang/String;)Ljava/lang/String;", "(Lio/ktor/http/Parameters;Ljava/lang/String;)Ljava/lang/Object;", "Lio/ktor/util/reflect/TypeInfo;", "typeInfo", "getOrFailImpl", "(Lio/ktor/http/Parameters;Ljava/lang/String;Lio/ktor/util/reflect/TypeInfo;)Ljava/lang/Object;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ParametersKt {
    /* JADX INFO: renamed from: getOrFail, reason: collision with other method in class */
    public static final String m6719getOrFail(Parameters parameters, String str) throws MissingRequestParameterException {
        String str2 = parameters.get(str);
        if (str2 != null) {
            return str2;
        }
        throw new MissingRequestParameterException(str);
    }

    public static final <R> R getOrFailImpl(Parameters parameters, String str, TypeInfo typeInfo) throws MissingRequestParameterException, ParameterConversionException {
        List<String> all = parameters.getAll(str);
        if (all == null) {
            throw new MissingRequestParameterException(str);
        }
        try {
            return (R) DefaultConversionService.INSTANCE.fromValues(all, typeInfo);
        } catch (Exception e5) {
            String strR = typeInfo.getType().r();
            if (strR == null) {
                strR = typeInfo.getType().toString();
            }
            throw new ParameterConversionException(str, strR, e5);
        }
    }

    public static final <R> R getValue(Parameters parameters, Object obj, m<?> mVar) {
        mVar.getName();
        p.h();
        throw null;
    }

    public static final <R> R getOrFail(Parameters parameters, String str) {
        p.h();
        throw null;
    }
}
