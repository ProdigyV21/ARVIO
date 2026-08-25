package io.ktor.websocket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0007\u001a\u00020\u00062\u000e\u0010\u0005\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJD\u0010\u000e\u001a\u00020\u0006\"\b\b\u0000\u0010\t*\u00020\u00012\u0010\u0010\n\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u00042\u0019\b\u0002\u0010\r\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u000b¢\u0006\u0002\b\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010¢\u0006\u0004\b\u0012\u0010\u0013R(\u0010\u0017\u001a\u0016\u0012\u0012\u0012\u0010\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0015j\u0002`\u00160\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lio/ktor/websocket/WebSocketExtensionsConfig;", "", "<init>", "()V", "Lio/ktor/websocket/WebSocketExtensionFactory;", "extensionFactory", "Lx6/t0;", "checkConflicts", "(Lio/ktor/websocket/WebSocketExtensionFactory;)V", "ConfigType", "extension", "Lkotlin/Function1;", "Lx6/n;", "config", "install", "(Lio/ktor/websocket/WebSocketExtensionFactory;Lr7/l;)V", "", "Lio/ktor/websocket/WebSocketExtension;", "build", "()Ljava/util/List;", "", "Lkotlin/Function0;", "Lio/ktor/websocket/ExtensionInstaller;", "installers", "Ljava/util/List;", "", "", "rcv", "[Ljava/lang/Boolean;", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WebSocketExtensionsConfig {
    private final List<a<WebSocketExtension<?>>> installers = new ArrayList();
    private final Boolean[] rcv;

    /* JADX INFO: renamed from: io.ktor.websocket.WebSocketExtensionsConfig$install$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00028\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "ConfigType", "Lx6/t0;", "invoke", "(Ljava/lang/Object;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m7016invoke(Object obj) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m7016invoke(obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.websocket.WebSocketExtensionsConfig$install$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/websocket/WebSocketExtension;", "ConfigType", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements a<WebSocketExtension<?>> {
        final /* synthetic */ l<ConfigType, t0> $config;
        final /* synthetic */ WebSocketExtensionFactory<ConfigType, ?> $extension;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(WebSocketExtensionFactory<ConfigType, ?> webSocketExtensionFactory, l<? super ConfigType, t0> lVar) {
            super(0);
            this.$extension = webSocketExtensionFactory;
            this.$config = lVar;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // r7.a
        public final WebSocketExtension<?> invoke() {
            return this.$extension.install(this.$config);
        }
    }

    public WebSocketExtensionsConfig() {
        Boolean bool = Boolean.FALSE;
        this.rcv = new Boolean[]{bool, bool, bool};
    }

    private final void checkConflicts(WebSocketExtensionFactory<?, ?> extensionFactory) {
        boolean z = true;
        if ((!extensionFactory.getRsv1() || !this.rcv[1].booleanValue()) && ((!extensionFactory.getRsv2() || !this.rcv[2].booleanValue()) && (!extensionFactory.getRsv3() || !this.rcv[3].booleanValue()))) {
            z = false;
        }
        if (z) {
            throw new IllegalStateException("Failed to install extension. Please check configured extensions for conflicts.");
        }
    }

    public static /* synthetic */ void install$default(WebSocketExtensionsConfig webSocketExtensionsConfig, WebSocketExtensionFactory webSocketExtensionFactory, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        webSocketExtensionsConfig.install(webSocketExtensionFactory, lVar);
    }

    public final List<WebSocketExtension<?>> build() {
        List<a<WebSocketExtension<?>>> list = this.installers;
        ArrayList arrayList = new ArrayList(s.U(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add((WebSocketExtension) ((a) it.next()).invoke());
        }
        return arrayList;
    }

    public final <ConfigType> void install(WebSocketExtensionFactory<ConfigType, ?> extension, l<? super ConfigType, t0> config) {
        checkConflicts(extension);
        this.installers.add(new AnonymousClass2(extension, config));
    }
}
