package io.ktor.util.converters;

import io.ktor.http.LinkHeader;
import io.ktor.util.KtorDsl;
import io.ktor.util.converters.DelegatingConversionService;
import io.ktor.util.reflect.TypeInfo;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.collections.z;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.q0;
import kotlin.reflect.d;
import kotlin.reflect.q;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J'\u0010\f\u001a\u0004\u0018\u00010\u000b2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R$\u0010\u0013\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0012\u0012\u0004\u0012\u00020\u00010\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lio/ktor/util/converters/DataConversion;", "Lio/ktor/util/converters/ConversionService;", "Lio/ktor/util/converters/DataConversion$Configuration;", "configuration", "<init>", "(Lio/ktor/util/converters/DataConversion$Configuration;)V", "", "", "values", "Lio/ktor/util/reflect/TypeInfo;", LinkHeader.Parameters.Type, "", "fromValues", "(Ljava/util/List;Lio/ktor/util/reflect/TypeInfo;)Ljava/lang/Object;", "value", "toValues", "(Ljava/lang/Object;)Ljava/util/List;", "", "Lkotlin/reflect/d;", "converters", "Ljava/util/Map;", "Configuration", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DataConversion implements ConversionService {
    private final Map<d<?>, ConversionService> converters;

    @KtorDsl
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\t\u001a\u00020\b2\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ>\u0010\t\u001a\u00020\b\"\b\b\u0000\u0010\u000b*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\f2\u001d\u0010\u0010\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e\u0012\u0004\u0012\u00020\b0\r¢\u0006\u0002\b\u000f¢\u0006\u0004\b\t\u0010\u0011J@\u0010\t\u001a\u00020\b\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\u00012\u001f\b\b\u0010\u0010\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e\u0012\u0004\u0012\u00020\b0\r¢\u0006\u0002\b\u000fH\u0086\bø\u0001\u0000¢\u0006\u0004\b\t\u0010\u0012R*\u0010\u0014\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\u00060\u00138\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0018"}, d2 = {"Lio/ktor/util/converters/DataConversion$Configuration;", "", "<init>", "()V", "Lkotlin/reflect/d;", LinkHeader.Parameters.Type, "Lio/ktor/util/converters/ConversionService;", "convertor", "Lx6/t0;", "convert", "(Lkotlin/reflect/d;Lio/ktor/util/converters/ConversionService;)V", "T", "Lkotlin/reflect/q;", "Lkotlin/Function1;", "Lio/ktor/util/converters/DelegatingConversionService$Configuration;", "Lx6/n;", "configure", "(Lkotlin/reflect/q;Lr7/l;)V", "(Lr7/l;)V", "", "converters", "Ljava/util/Map;", "getConverters$ktor_utils", "()Ljava/util/Map;", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Configuration {
        private final Map<d<?>, ConversionService> converters = new LinkedHashMap();

        public final void convert(d<?> type, ConversionService convertor) {
            this.converters.put(type, convertor);
        }

        public final Map<d<?>, ConversionService> getConverters$ktor_utils() {
            return this.converters;
        }

        public final <T> void convert(q type, l<? super DelegatingConversionService.Configuration<T>, t0> configure) {
            d<?> dVar = (d) type.b();
            DelegatingConversionService.Configuration configuration = new DelegatingConversionService.Configuration(dVar);
            configure.invoke(configuration);
            l<List<String>, T> decoder$ktor_utils = configuration.getDecoder$ktor_utils();
            l<T, List<String>> encoder$ktor_utils = configuration.getEncoder$ktor_utils();
            q0.e(1, encoder$ktor_utils);
            convert(dVar, new DelegatingConversionService(dVar, decoder$ktor_utils, encoder$ktor_utils));
        }

        public final <T> void convert(l<? super DelegatingConversionService.Configuration<T>, t0> configure) {
            p.h();
            throw null;
        }
    }

    public DataConversion(Configuration configuration) {
        this.converters = h0.B0(configuration.getConverters$ktor_utils());
    }

    @Override // io.ktor.util.converters.ConversionService
    public Object fromValues(List<String> values, TypeInfo type) {
        if (values.isEmpty()) {
            return null;
        }
        ConversionService conversionService = this.converters.get(type.getType());
        if (conversionService == null) {
            conversionService = DefaultConversionService.INSTANCE;
        }
        return conversionService.fromValues(values, type);
    }

    @Override // io.ktor.util.converters.ConversionService
    public List<String> toValues(Object value) {
        if (value == null) {
            return z.f19728i;
        }
        ConversionService conversionService = this.converters.get(l0.f19747a.b(value.getClass()));
        if (conversionService == null) {
            conversionService = DefaultConversionService.INSTANCE;
        }
        return conversionService.toValues(value);
    }
}
