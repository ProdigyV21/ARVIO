package io.ktor.util.converters;

import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import io.ktor.util.reflect.TypeInfo;
import java.util.List;
import kotlin.Metadata;
import kotlin.reflect.d;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001\u0018Bm\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002\u0012+\u0010\u000b\u001a'\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0004\u0012+\u0010\r\u001a'\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ'\u0010\u0012\u001a\u0004\u0018\u00010\n2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\u0010\f\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0016R9\u0010\u000b\u001a'\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\u0017R9\u0010\r\u001a'\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u0017¨\u0006\u0019"}, d2 = {"Lio/ktor/util/converters/DelegatingConversionService;", "Lio/ktor/util/converters/ConversionService;", "Lkotlin/reflect/d;", "klass", "Lkotlin/Function1;", "", "", "Lx6/y;", ContentDisposition.Parameters.Name, "values", "", "decoder", "value", "encoder", "<init>", "(Lkotlin/reflect/d;Lr7/l;Lr7/l;)V", "Lio/ktor/util/reflect/TypeInfo;", LinkHeader.Parameters.Type, "fromValues", "(Ljava/util/List;Lio/ktor/util/reflect/TypeInfo;)Ljava/lang/Object;", "toValues", "(Ljava/lang/Object;)Ljava/util/List;", "Lkotlin/reflect/d;", "Lr7/l;", "Configuration", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DelegatingConversionService implements ConversionService {
    private final l<List<String>, Object> decoder;
    private final l<Object, List<String>> encoder;
    private final d<?> klass;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B\u0017\b\u0001\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0005\u0010\u0006J6\u0010\u000f\u001a\u00020\u000e2'\u0010\r\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\t0\b¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\u000f\u0010\u0010J6\u0010\u0012\u001a\u00020\u000e2'\u0010\r\u001a#\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007¢\u0006\u0004\b\u0012\u0010\u0010R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015RE\u0010\u0016\u001a%\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\t0\b¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00078\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u0010RE\u0010\u001b\u001a%\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0018\u00010\u00078\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u0017\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u0010¨\u0006\u001e"}, d2 = {"Lio/ktor/util/converters/DelegatingConversionService$Configuration;", "", "T", "Lkotlin/reflect/d;", "klass", "<init>", "(Lkotlin/reflect/d;)V", "Lkotlin/Function1;", "", "", "Lx6/y;", ContentDisposition.Parameters.Name, "values", "converter", "Lx6/t0;", "decode", "(Lr7/l;)V", "value", "encode", "Lkotlin/reflect/d;", "getKlass$ktor_utils", "()Lkotlin/reflect/d;", "decoder", "Lr7/l;", "getDecoder$ktor_utils", "()Lr7/l;", "setDecoder$ktor_utils", "encoder", "getEncoder$ktor_utils", "setEncoder$ktor_utils", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Configuration<T> {
        private l<? super List<String>, ? extends T> decoder;
        private l<? super T, ? extends List<String>> encoder;
        private final d<T> klass;

        public Configuration(d<T> dVar) {
            this.klass = dVar;
        }

        public final void decode(l<? super List<String>, ? extends T> converter) {
            if (this.decoder == null) {
                this.decoder = converter;
                return;
            }
            throw new IllegalStateException("Decoder has already been set for type '" + this.klass + '\'');
        }

        public final void encode(l<? super T, ? extends List<String>> converter) {
            if (this.encoder == null) {
                this.encoder = converter;
                return;
            }
            throw new IllegalStateException("Encoder has already been set for type '" + this.klass + '\'');
        }

        public final l<List<String>, T> getDecoder$ktor_utils() {
            return this.decoder;
        }

        public final l<T, List<String>> getEncoder$ktor_utils() {
            return this.encoder;
        }

        public final d<T> getKlass$ktor_utils() {
            return this.klass;
        }

        public final void setDecoder$ktor_utils(l<? super List<String>, ? extends T> lVar) {
            this.decoder = lVar;
        }

        public final void setEncoder$ktor_utils(l<? super T, ? extends List<String>> lVar) {
            this.encoder = lVar;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DelegatingConversionService(d<?> dVar, l<? super List<String>, ? extends Object> lVar, l<Object, ? extends List<String>> lVar2) {
        this.klass = dVar;
        this.decoder = lVar;
        this.encoder = lVar2;
    }

    @Override // io.ktor.util.converters.ConversionService
    public Object fromValues(List<String> values, TypeInfo type) {
        l<List<String>, Object> lVar = this.decoder;
        if (lVar != null) {
            return lVar.invoke(values);
        }
        throw new IllegalStateException("Decoder was not specified for type '" + this.klass + '\'');
    }

    @Override // io.ktor.util.converters.ConversionService
    public List<String> toValues(Object value) {
        l<Object, List<String>> lVar = this.encoder;
        if (lVar != null) {
            return (List) lVar.invoke(value);
        }
        throw new IllegalStateException("Encoder was not specified for type '" + this.klass + '\'');
    }
}
