package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import a9.k1;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import kotlin.jvm.internal.l0;
import s9.g0;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class j extends kotlin.jvm.internal.l implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f19865i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j(int i10, Object obj, int i11) {
        super(i10, obj);
        this.f19865i = i11;
    }

    @Override // kotlin.jvm.internal.e, kotlin.reflect.c
    public final String getName() {
        switch (this.f19865i) {
            case 0:
                return "simpleType";
            case 1:
                return "getValueClassPropertyType";
            case 2:
                return "<init>";
            case 3:
                return "searchMethodsByNameWithoutBuiltinMagic";
            case 4:
                return "searchMethodsInSupertypesWithoutBuiltinMagic";
            case 5:
                return "loadResource";
            default:
                return "prepareType";
        }
    }

    @Override // kotlin.jvm.internal.e
    public final kotlin.reflect.f getOwner() {
        switch (this.f19865i) {
            case 0:
                return l0.f19747a.b(kotlin.jvm.internal.o.class);
            case 1:
                return l0.f19747a.b(k.class);
            case 2:
                return l0.f19747a.b(g.class);
            case 3:
                return l0.f19747a.b(t8.n.class);
            case 4:
                return l0.f19747a.b(t8.n.class);
            case 5:
                return l0.f19747a.b(t9.d.class);
            default:
                return l0.f19747a.b(w9.d.class);
        }
    }

    @Override // kotlin.jvm.internal.e
    public final String getSignature() {
        switch (this.f19865i) {
            case 0:
                return "computeValueClassRepresentation$simpleType(Lorg/jetbrains/kotlin/serialization/deserialization/TypeDeserializer;Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;)Lorg/jetbrains/kotlin/types/SimpleType;";
            case 1:
                return "getValueClassPropertyType(Lorg/jetbrains/kotlin/name/Name;)Lorg/jetbrains/kotlin/types/SimpleType;";
            case 2:
                return "<init>(Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedClassDescriptor;Lorg/jetbrains/kotlin/types/checker/KotlinTypeRefiner;)V";
            case 3:
                return "searchMethodsByNameWithoutBuiltinMagic(Lorg/jetbrains/kotlin/name/Name;)Ljava/util/Collection;";
            case 4:
                return "searchMethodsInSupertypesWithoutBuiltinMagic(Lorg/jetbrains/kotlin/name/Name;)Ljava/util/Collection;";
            case 5:
                return "loadResource(Ljava/lang/String;)Ljava/io/InputStream;";
            default:
                return "prepareType(Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;)Lorg/jetbrains/kotlin/types/UnwrappedType;";
        }
    }

    @Override // r7.l
    public final Object invoke(Object obj) throws IOException {
        switch (this.f19865i) {
            case 0:
                return ((g0) this.receiver).d((k1) obj, true);
            case 1:
                return ((k) this.receiver).H0((f9.f) obj);
            case 2:
                return new g((k) this.receiver, (w9.e) obj);
            case 3:
                return t8.n.u((t8.n) this.receiver, (f9.f) obj);
            case 4:
                return t8.n.v((t8.n) this.receiver, (f9.f) obj);
            case 5:
                String str = (String) obj;
                ((t9.d) this.receiver).getClass();
                ClassLoader classLoader = t9.d.class.getClassLoader();
                if (classLoader == null) {
                    return ClassLoader.getSystemResourceAsStream(str);
                }
                URL resource = classLoader.getResource(str);
                if (resource == null) {
                    return null;
                }
                URLConnection uRLConnectionOpenConnection = resource.openConnection();
                uRLConnectionOpenConnection.setUseCaches(false);
                return uRLConnectionOpenConnection.getInputStream();
            default:
                return ((w9.d) this.receiver).a((y9.h) obj);
        }
    }
}
