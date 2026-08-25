package io.ktor.websocket;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "Lio/ktor/websocket/WebSocketExtensionHeader;", "it", "Lx6/t0;", "invoke", "(Ljava/util/List;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class WebSocketDeflateExtension$Config$configureProtocols$1 extends r implements l<List<WebSocketExtensionHeader>, t0> {
    final /* synthetic */ l<List<WebSocketExtensionHeader>, t0> $block;
    final /* synthetic */ l<List<WebSocketExtensionHeader>, t0> $old;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public WebSocketDeflateExtension$Config$configureProtocols$1(l<? super List<WebSocketExtensionHeader>, t0> lVar, l<? super List<WebSocketExtensionHeader>, t0> lVar2) {
        super(1);
        this.$old = lVar;
        this.$block = lVar2;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<WebSocketExtensionHeader>) obj);
        return t0.f22605a;
    }

    public final void invoke(List<WebSocketExtensionHeader> list) {
        this.$old.invoke(list);
        this.$block.invoke(list);
    }
}
