package coil;

import android.content.Collections;
import android.graphics.Decoder;
import coil.fetch.Fetcher;
import coil.fetch.SourceResult;
import coil.intercept.Interceptor;
import coil.key.Keyer;
import coil.map.Mapper;
import coil.request.Options;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import x6.x;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u00010B«\u0001\b\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u00120\u0010\b\u001a,\u0012(\u0012&\u0012\u0014\u0012\u0012\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00070\u00050\u0002\u0012(\u0010\n\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\t\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00070\u00050\u0002\u0012(\u0010\f\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00070\u00050\u0002\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u0002¢\u0006\u0004\b\u000f\u0010\u0010B\t\b\u0016¢\u0006\u0004\b\u000f\u0010\u0011J\u001d\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0018\u0010\u0019J?\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001d\u001a\u00020\u001cH\u0007¢\u0006\u0004\b\u001f\u0010 J?\u0010$\u001a\u0010\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00052\u0006\u0010\"\u001a\u00020!2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001d\u001a\u00020\u001cH\u0007¢\u0006\u0004\b$\u0010%J\r\u0010'\u001a\u00020&¢\u0006\u0004\b'\u0010(R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010)\u001a\u0004\b*\u0010+RA\u0010\b\u001a,\u0012(\u0012&\u0012\u0014\u0012\u0012\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00070\u00050\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010)\u001a\u0004\b,\u0010+R9\u0010\n\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\t\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00070\u00050\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010)\u001a\u0004\b-\u0010+R9\u0010\f\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00070\u00050\u00028\u0006¢\u0006\f\n\u0004\b\f\u0010)\u001a\u0004\b.\u0010+R\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010)\u001a\u0004\b/\u0010+¨\u00061"}, d2 = {"Lcoil/ComponentRegistry;", "", "", "Lcoil/intercept/Interceptor;", "interceptors", "Lx6/x;", "Lcoil/map/Mapper;", "Ljava/lang/Class;", "mappers", "Lcoil/key/Keyer;", "keyers", "Lcoil/fetch/Fetcher$Factory;", "fetcherFactories", "Lcoil/decode/Decoder$Factory;", "decoderFactories", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "()V", "data", "Lcoil/request/Options;", "options", "map", "(Ljava/lang/Object;Lcoil/request/Options;)Ljava/lang/Object;", "", "key", "(Ljava/lang/Object;Lcoil/request/Options;)Ljava/lang/String;", "Lcoil/ImageLoader;", "imageLoader", "", "startIndex", "Lcoil/fetch/Fetcher;", "newFetcher", "(Ljava/lang/Object;Lcoil/request/Options;Lcoil/ImageLoader;I)Lx6/x;", "Lcoil/fetch/SourceResult;", "result", "Lcoil/decode/Decoder;", "newDecoder", "(Lcoil/fetch/SourceResult;Lcoil/request/Options;Lcoil/ImageLoader;I)Lx6/x;", "Lcoil/ComponentRegistry$Builder;", "newBuilder", "()Lcoil/ComponentRegistry$Builder;", "Ljava/util/List;", "getInterceptors", "()Ljava/util/List;", "getMappers", "getKeyers", "getFetcherFactories", "getDecoderFactories", "Builder", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ComponentRegistry {
    private final List<Decoder.Factory> decoderFactories;
    private final List<x> fetcherFactories;
    private final List<Interceptor> interceptors;
    private final List<x> keyers;
    private final List<x> mappers;

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0002\u0010\u0006J\u0015\u0010\t\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ.\u0010\t\u001a\u00020\u0000\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\u00012\u0010\u0010\r\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\fH\u0086\b¢\u0006\u0004\b\t\u0010\u000eJ7\u0010\t\u001a\u00020\u0000\"\b\b\u0000\u0010\u000b*\u00020\u00012\u0010\u0010\r\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f¢\u0006\u0004\b\t\u0010\u0011J*\u0010\t\u001a\u00020\u0000\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H\u0086\b¢\u0006\u0004\b\t\u0010\u0014J3\u0010\t\u001a\u00020\u0000\"\b\b\u0000\u0010\u000b*\u00020\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00122\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f¢\u0006\u0004\b\t\u0010\u0015J*\u0010\t\u001a\u00020\u0000\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\u00012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0086\b¢\u0006\u0004\b\t\u0010\u0018J3\u0010\t\u001a\u00020\u0000\"\b\b\u0000\u0010\u000b*\u00020\u00012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00162\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f¢\u0006\u0004\b\t\u0010\u0019J\u0015\u0010\t\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u001a¢\u0006\u0004\b\t\u0010\u001bJ\r\u0010\u001c\u001a\u00020\u0004¢\u0006\u0004\b\u001c\u0010\u001dR \u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070\u001e8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R@\u0010$\u001a(\u0012$\u0012\"\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0002\b\u00030\f\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000f0#0\u001e8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b$\u0010 \u001a\u0004\b%\u0010\"R<\u0010&\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0012\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000f0#0\u001e8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b&\u0010 \u001a\u0004\b'\u0010\"R<\u0010(\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000f0#0\u001e8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b(\u0010 \u001a\u0004\b)\u0010\"R \u0010*\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001e8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b*\u0010 \u001a\u0004\b+\u0010\"¨\u0006,"}, d2 = {"Lcoil/ComponentRegistry$Builder;", "", "<init>", "()V", "Lcoil/ComponentRegistry;", "registry", "(Lcoil/ComponentRegistry;)V", "Lcoil/intercept/Interceptor;", "interceptor", "add", "(Lcoil/intercept/Interceptor;)Lcoil/ComponentRegistry$Builder;", "T", "Lcoil/map/Mapper;", "mapper", "(Lcoil/map/Mapper;)Lcoil/ComponentRegistry$Builder;", "Ljava/lang/Class;", LinkHeader.Parameters.Type, "(Lcoil/map/Mapper;Ljava/lang/Class;)Lcoil/ComponentRegistry$Builder;", "Lcoil/key/Keyer;", "keyer", "(Lcoil/key/Keyer;)Lcoil/ComponentRegistry$Builder;", "(Lcoil/key/Keyer;Ljava/lang/Class;)Lcoil/ComponentRegistry$Builder;", "Lcoil/fetch/Fetcher$Factory;", "factory", "(Lcoil/fetch/Fetcher$Factory;)Lcoil/ComponentRegistry$Builder;", "(Lcoil/fetch/Fetcher$Factory;Ljava/lang/Class;)Lcoil/ComponentRegistry$Builder;", "Lcoil/decode/Decoder$Factory;", "(Lcoil/decode/Decoder$Factory;)Lcoil/ComponentRegistry$Builder;", "build", "()Lcoil/ComponentRegistry;", "", "interceptors", "Ljava/util/List;", "getInterceptors$coil_base_release", "()Ljava/util/List;", "Lx6/x;", "mappers", "getMappers$coil_base_release", "keyers", "getKeyers$coil_base_release", "fetcherFactories", "getFetcherFactories$coil_base_release", "decoderFactories", "getDecoderFactories$coil_base_release", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Builder {
        private final List<Decoder.Factory> decoderFactories;
        private final List<x> fetcherFactories;
        private final List<Interceptor> interceptors;
        private final List<x> keyers;
        private final List<x> mappers;

        public Builder() {
            this.interceptors = new ArrayList();
            this.mappers = new ArrayList();
            this.keyers = new ArrayList();
            this.fetcherFactories = new ArrayList();
            this.decoderFactories = new ArrayList();
        }

        public final Builder add(Interceptor interceptor) {
            this.interceptors.add(interceptor);
            return this;
        }

        public final ComponentRegistry build() {
            return new ComponentRegistry(Collections.toImmutableList(this.interceptors), Collections.toImmutableList(this.mappers), Collections.toImmutableList(this.keyers), Collections.toImmutableList(this.fetcherFactories), Collections.toImmutableList(this.decoderFactories), null);
        }

        public final List<Decoder.Factory> getDecoderFactories$coil_base_release() {
            return this.decoderFactories;
        }

        public final List<x> getFetcherFactories$coil_base_release() {
            return this.fetcherFactories;
        }

        public final List<Interceptor> getInterceptors$coil_base_release() {
            return this.interceptors;
        }

        public final List<x> getKeyers$coil_base_release() {
            return this.keyers;
        }

        public final List<x> getMappers$coil_base_release() {
            return this.mappers;
        }

        public final <T> Builder add(Mapper<T, ?> mapper, Class<T> type) {
            this.mappers.add(new x(mapper, type));
            return this;
        }

        public final <T> Builder add(Keyer<T> keyer, Class<T> type) {
            this.keyers.add(new x(keyer, type));
            return this;
        }

        public Builder(ComponentRegistry componentRegistry) {
            this.interceptors = new ArrayList(componentRegistry.getInterceptors());
            this.mappers = new ArrayList(componentRegistry.getMappers());
            this.keyers = new ArrayList(componentRegistry.getKeyers());
            this.fetcherFactories = new ArrayList(componentRegistry.getFetcherFactories());
            this.decoderFactories = new ArrayList(componentRegistry.getDecoderFactories());
        }

        public final <T> Builder add(Fetcher.Factory<T> factory, Class<T> type) {
            this.fetcherFactories.add(new x(factory, type));
            return this;
        }

        public final Builder add(Decoder.Factory factory) {
            this.decoderFactories.add(factory);
            return this;
        }

        public final <T> Builder add(Fetcher.Factory<T> factory) {
            p.h();
            throw null;
        }

        public final <T> Builder add(Keyer<T> keyer) {
            p.h();
            throw null;
        }

        public final <T> Builder add(Mapper<T, ?> mapper) {
            p.h();
            throw null;
        }
    }

    public /* synthetic */ ComponentRegistry(List list, List list2, List list3, List list4, List list5, h hVar) {
        this(list, list2, list3, list4, list5);
    }

    public static /* synthetic */ x newDecoder$default(ComponentRegistry componentRegistry, SourceResult sourceResult, Options options, ImageLoader imageLoader, int i10, int i11, Object obj) {
        if ((i11 & 8) != 0) {
            i10 = 0;
        }
        return componentRegistry.newDecoder(sourceResult, options, imageLoader, i10);
    }

    public static /* synthetic */ x newFetcher$default(ComponentRegistry componentRegistry, Object obj, Options options, ImageLoader imageLoader, int i10, int i11, Object obj2) {
        if ((i11 & 8) != 0) {
            i10 = 0;
        }
        return componentRegistry.newFetcher(obj, options, imageLoader, i10);
    }

    public final List<Decoder.Factory> getDecoderFactories() {
        return this.decoderFactories;
    }

    public final List<x> getFetcherFactories() {
        return this.fetcherFactories;
    }

    public final List<Interceptor> getInterceptors() {
        return this.interceptors;
    }

    public final List<x> getKeyers() {
        return this.keyers;
    }

    public final List<x> getMappers() {
        return this.mappers;
    }

    public final String key(Object data, Options options) {
        String strKey;
        List<x> list = this.keyers;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            x xVar = list.get(i10);
            Keyer keyer = (Keyer) xVar.f22608i;
            if (((Class) xVar.f22609l).isAssignableFrom(data.getClass()) && (strKey = keyer.key(data, options)) != null) {
                return strKey;
            }
        }
        return null;
    }

    public final Object map(Object data, Options options) {
        Object map;
        List<x> list = this.mappers;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            x xVar = list.get(i10);
            Mapper mapper = (Mapper) xVar.f22608i;
            if (((Class) xVar.f22609l).isAssignableFrom(data.getClass()) && (map = mapper.map(data, options)) != null) {
                data = map;
            }
        }
        return data;
    }

    public final Builder newBuilder() {
        return new Builder(this);
    }

    public final x newDecoder(SourceResult sourceResult, Options options, ImageLoader imageLoader) {
        return newDecoder$default(this, sourceResult, options, imageLoader, 0, 8, null);
    }

    public final x newFetcher(Object obj, Options options, ImageLoader imageLoader) {
        return newFetcher$default(this, obj, options, imageLoader, 0, 8, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ComponentRegistry(List<? extends Interceptor> list, List<? extends x> list2, List<? extends x> list3, List<? extends x> list4, List<? extends Decoder.Factory> list5) {
        this.interceptors = list;
        this.mappers = list2;
        this.keyers = list3;
        this.fetcherFactories = list4;
        this.decoderFactories = list5;
    }

    public final x newDecoder(SourceResult result, Options options, ImageLoader imageLoader, int startIndex) {
        int size = this.decoderFactories.size();
        while (startIndex < size) {
            Decoder decoderCreate = this.decoderFactories.get(startIndex).create(result, options, imageLoader);
            if (decoderCreate != null) {
                return new x(decoderCreate, Integer.valueOf(startIndex));
            }
            startIndex++;
        }
        return null;
    }

    public final x newFetcher(Object data, Options options, ImageLoader imageLoader, int startIndex) {
        Fetcher fetcherCreate;
        int size = this.fetcherFactories.size();
        while (startIndex < size) {
            x xVar = this.fetcherFactories.get(startIndex);
            Fetcher.Factory factory = (Fetcher.Factory) xVar.f22608i;
            if (((Class) xVar.f22609l).isAssignableFrom(data.getClass()) && (fetcherCreate = factory.create(data, options, imageLoader)) != null) {
                return new x(fetcherCreate, Integer.valueOf(startIndex));
            }
            startIndex++;
        }
        return null;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ComponentRegistry() {
        z zVar = z.f19728i;
        this(zVar, zVar, zVar, zVar, zVar);
    }
}
