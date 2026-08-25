package io.ktor.websocket;

import io.ktor.http.ContentDisposition;
import io.ktor.util.AttributeKey;
import io.ktor.websocket.Frame;
import io.ktor.websocket.internals.DeflaterUtilsKt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import r7.l;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 $2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002$%B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u0016\u0010\u001c\u001a\u00020\u00062\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0016J\u0010\u0010!\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0016J\u001c\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016R\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020\u00020\u00010\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000f\"\u0004\b\u0016\u0010\u0011R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006&"}, d2 = {"Lio/ktor/websocket/WebSocketDeflateExtension;", "Lio/ktor/websocket/WebSocketExtension;", "Lio/ktor/websocket/WebSocketDeflateExtension$Config;", "config", "(Lio/ktor/websocket/WebSocketDeflateExtension$Config;)V", "decompressIncoming", "", "deflater", "Ljava/util/zip/Deflater;", "factory", "Lio/ktor/websocket/WebSocketExtensionFactory;", "getFactory", "()Lio/ktor/websocket/WebSocketExtensionFactory;", "incomingNoContextTakeover", "getIncomingNoContextTakeover$ktor_websockets", "()Z", "setIncomingNoContextTakeover$ktor_websockets", "(Z)V", "inflater", "Ljava/util/zip/Inflater;", "outgoingNoContextTakeover", "getOutgoingNoContextTakeover$ktor_websockets", "setOutgoingNoContextTakeover$ktor_websockets", "protocols", "", "Lio/ktor/websocket/WebSocketExtensionHeader;", "getProtocols", "()Ljava/util/List;", "clientNegotiation", "negotiatedProtocols", "processIncomingFrame", "Lio/ktor/websocket/Frame;", "frame", "processOutgoingFrame", "serverNegotiation", "requestedProtocols", "Companion", "Config", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WebSocketDeflateExtension implements WebSocketExtension<Config> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<WebSocketDeflateExtension> key = new AttributeKey<>("WebsocketDeflateExtension");
    private static final boolean rsv1 = true;
    private static final boolean rsv2 = false;
    private static final boolean rsv3 = false;
    private final Config config;
    private boolean decompressIncoming;
    private final Deflater deflater;
    private boolean incomingNoContextTakeover;
    private boolean outgoingNoContextTakeover;
    private final List<WebSocketExtensionHeader> protocols;
    private final WebSocketExtensionFactory<Config, ? extends WebSocketExtension<Config>> factory = INSTANCE;
    private final Inflater inflater = new Inflater(true);

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J(\u0010\n\u001a\u00020\u00032\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0016¢\u0006\u0004\b\n\u0010\u000bR \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u00118\u0016X\u0096D¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u00118\u0016X\u0096D¢\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015R\u001a\u0010\u0018\u001a\u00020\u00118\u0016X\u0096D¢\u0006\f\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015¨\u0006\u001a"}, d2 = {"Lio/ktor/websocket/WebSocketDeflateExtension$Companion;", "Lio/ktor/websocket/WebSocketExtensionFactory;", "Lio/ktor/websocket/WebSocketDeflateExtension$Config;", "Lio/ktor/websocket/WebSocketDeflateExtension;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "config", "install", "(Lr7/l;)Lio/ktor/websocket/WebSocketDeflateExtension;", "Lio/ktor/util/AttributeKey;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "", "rsv1", "Z", "getRsv1", "()Z", "rsv2", "getRsv2", "rsv3", "getRsv3", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements WebSocketExtensionFactory<Config, WebSocketDeflateExtension> {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @Override // io.ktor.websocket.WebSocketExtensionFactory
        public AttributeKey<WebSocketDeflateExtension> getKey() {
            return WebSocketDeflateExtension.key;
        }

        @Override // io.ktor.websocket.WebSocketExtensionFactory
        public boolean getRsv1() {
            return WebSocketDeflateExtension.rsv1;
        }

        @Override // io.ktor.websocket.WebSocketExtensionFactory
        public boolean getRsv2() {
            return WebSocketDeflateExtension.rsv2;
        }

        @Override // io.ktor.websocket.WebSocketExtensionFactory
        public boolean getRsv3() {
            return WebSocketDeflateExtension.rsv3;
        }

        private Companion() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.websocket.WebSocketExtensionFactory
        public WebSocketDeflateExtension install(l<? super Config, t0> config) {
            Config config2 = new Config();
            config.invoke(config2);
            return new WebSocketDeflateExtension(config2);
        }
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u001a\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\f\u001a\u00020\n2'\u0010\u000b\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0004¢\u0006\u0004\b\f\u0010\rJ0\u0010\u0011\u001a\u00020\n2!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\u0004¢\u0006\u0004\b\u0011\u0010\rJ\u0015\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0016H\u0000¢\u0006\u0004\b\u0017\u0010\u0018R\"\u0010\u001a\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\"\u0010 \u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b \u0010\u001b\u001a\u0004\b!\u0010\u001d\"\u0004\b\"\u0010\u001fR\"\u0010#\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010\u0015R4\u0010(\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\n0\u00048\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010\rR.\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00100\u00048\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b-\u0010)\u001a\u0004\b.\u0010+\"\u0004\b/\u0010\r¨\u00060"}, d2 = {"Lio/ktor/websocket/WebSocketDeflateExtension$Config;", "", "<init>", "()V", "Lkotlin/Function1;", "", "Lio/ktor/websocket/WebSocketExtensionHeader;", "Lx6/y;", ContentDisposition.Parameters.Name, "protocols", "Lx6/t0;", "block", "configureProtocols", "(Lr7/l;)V", "Lio/ktor/websocket/Frame;", "frame", "", "compressIf", "", "bytes", "compressIfBiggerThan", "(I)V", "", "build$ktor_websockets", "()Ljava/util/List;", "build", "clientNoContextTakeOver", "Z", "getClientNoContextTakeOver", "()Z", "setClientNoContextTakeOver", "(Z)V", "serverNoContextTakeOver", "getServerNoContextTakeOver", "setServerNoContextTakeOver", "compressionLevel", "I", "getCompressionLevel", "()I", "setCompressionLevel", "manualConfig", "Lr7/l;", "getManualConfig$ktor_websockets", "()Lr7/l;", "setManualConfig$ktor_websockets", "compressCondition", "getCompressCondition$ktor_websockets", "setCompressCondition$ktor_websockets", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Config {
        private boolean clientNoContextTakeOver;
        private boolean serverNoContextTakeOver;
        private int compressionLevel = -1;
        private l<? super List<WebSocketExtensionHeader>, t0> manualConfig = WebSocketDeflateExtension$Config$manualConfig$1.INSTANCE;
        private l<? super Frame, Boolean> compressCondition = WebSocketDeflateExtension$Config$compressCondition$1.INSTANCE;

        public final List<WebSocketExtensionHeader> build$ktor_websockets() {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            if (this.clientNoContextTakeOver) {
                arrayList2.add("client_no_context_takeover");
            }
            if (this.serverNoContextTakeOver) {
                arrayList2.add("server_no_context_takeover");
            }
            arrayList.add(new WebSocketExtensionHeader("permessage-deflate", arrayList2));
            this.manualConfig.invoke(arrayList);
            return arrayList;
        }

        public final void compressIf(l<? super Frame, Boolean> block) {
            this.compressCondition = new WebSocketDeflateExtension$Config$compressIf$1(block, this.compressCondition);
        }

        public final void compressIfBiggerThan(int bytes) {
            compressIf(new WebSocketDeflateExtension$Config$compressIfBiggerThan$1(bytes));
        }

        public final void configureProtocols(l<? super List<WebSocketExtensionHeader>, t0> block) {
            this.manualConfig = new WebSocketDeflateExtension$Config$configureProtocols$1(this.manualConfig, block);
        }

        public final boolean getClientNoContextTakeOver() {
            return this.clientNoContextTakeOver;
        }

        public final l<Frame, Boolean> getCompressCondition$ktor_websockets() {
            return this.compressCondition;
        }

        public final int getCompressionLevel() {
            return this.compressionLevel;
        }

        public final l<List<WebSocketExtensionHeader>, t0> getManualConfig$ktor_websockets() {
            return this.manualConfig;
        }

        public final boolean getServerNoContextTakeOver() {
            return this.serverNoContextTakeOver;
        }

        public final void setClientNoContextTakeOver(boolean z) {
            this.clientNoContextTakeOver = z;
        }

        public final void setCompressCondition$ktor_websockets(l<? super Frame, Boolean> lVar) {
            this.compressCondition = lVar;
        }

        public final void setCompressionLevel(int i10) {
            this.compressionLevel = i10;
        }

        public final void setManualConfig$ktor_websockets(l<? super List<WebSocketExtensionHeader>, t0> lVar) {
            this.manualConfig = lVar;
        }

        public final void setServerNoContextTakeOver(boolean z) {
            this.serverNoContextTakeOver = z;
        }
    }

    public WebSocketDeflateExtension(Config config) {
        this.config = config;
        this.protocols = config.build$ktor_websockets();
        this.deflater = new Deflater(config.getCompressionLevel(), true);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // io.ktor.websocket.WebSocketExtension
    public boolean clientNegotiation(List<WebSocketExtensionHeader> negotiatedProtocols) {
        Object next;
        Iterator<T> it = negotiatedProtocols.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (p.a(((WebSocketExtensionHeader) next).getName(), "permessage-deflate")) {
                break;
            }
        }
        WebSocketExtensionHeader webSocketExtensionHeader = (WebSocketExtensionHeader) next;
        if (webSocketExtensionHeader == null) {
            return false;
        }
        this.incomingNoContextTakeover = this.config.getServerNoContextTakeOver();
        this.outgoingNoContextTakeover = this.config.getClientNoContextTakeOver();
        for (x xVar : webSocketExtensionHeader.parseParameters()) {
            String str = (String) xVar.f22608i;
            String str2 = (String) xVar.f22609l;
            switch (str.hashCode()) {
                case -708713803:
                    if (str.equals("client_no_context_takeover")) {
                        if (!o.h0(str2)) {
                            throw new IllegalStateException("WebSocket permessage-deflate extension parameter client_no_context_takeover shouldn't have a value. Current: ".concat(str2).toString());
                        }
                        this.outgoingNoContextTakeover = true;
                    } else {
                        continue;
                    }
                    break;
                case 646404390:
                    if (str.equals("client_max_window_bits") && !o.h0(str2) && Integer.parseInt(str2) != 15) {
                        throw new IllegalStateException("Only 15 window size is supported.");
                    }
                    break;
                    break;
                case 1266201133:
                    if (str.equals("server_no_context_takeover")) {
                        if (!o.h0(str2)) {
                            throw new IllegalStateException("WebSocket permessage-deflate extension parameter server_no_context_takeover shouldn't have a value. Current: ".concat(str2).toString());
                        }
                        this.incomingNoContextTakeover = true;
                    } else {
                        continue;
                    }
                    break;
                case 2034279582:
                    str.equals("server_max_window_bits");
                    break;
            }
        }
        return true;
    }

    @Override // io.ktor.websocket.WebSocketExtension
    public WebSocketExtensionFactory<Config, ? extends WebSocketExtension<Config>> getFactory() {
        return this.factory;
    }

    /* JADX INFO: renamed from: getIncomingNoContextTakeover$ktor_websockets, reason: from getter */
    public final boolean getIncomingNoContextTakeover() {
        return this.incomingNoContextTakeover;
    }

    /* JADX INFO: renamed from: getOutgoingNoContextTakeover$ktor_websockets, reason: from getter */
    public final boolean getOutgoingNoContextTakeover() {
        return this.outgoingNoContextTakeover;
    }

    @Override // io.ktor.websocket.WebSocketExtension
    public List<WebSocketExtensionHeader> getProtocols() {
        return this.protocols;
    }

    @Override // io.ktor.websocket.WebSocketExtension
    public Frame processIncomingFrame(Frame frame) {
        if (!WebSocketDeflateExtensionKt.isCompressed(frame) && !this.decompressIncoming) {
            return frame;
        }
        this.decompressIncoming = true;
        byte[] bArrInflateFully = DeflaterUtilsKt.inflateFully(this.inflater, frame.getData());
        if (this.incomingNoContextTakeover) {
            this.inflater.reset();
        }
        if (frame.getFin()) {
            this.decompressIncoming = false;
        }
        return Frame.INSTANCE.byType(frame.getFin(), frame.getFrameType(), bArrInflateFully, !rsv1, frame.getRsv2(), frame.getRsv3());
    }

    @Override // io.ktor.websocket.WebSocketExtension
    public Frame processOutgoingFrame(Frame frame) {
        if ((!(frame instanceof Frame.Text) && !(frame instanceof Frame.Binary)) || !((Boolean) this.config.getCompressCondition$ktor_websockets().invoke(frame)).booleanValue()) {
            return frame;
        }
        byte[] bArrDeflateFully = DeflaterUtilsKt.deflateFully(this.deflater, frame.getData());
        if (this.outgoingNoContextTakeover) {
            this.deflater.reset();
        }
        return Frame.INSTANCE.byType(frame.getFin(), frame.getFrameType(), bArrDeflateFully, rsv1, frame.getRsv2(), frame.getRsv3());
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // io.ktor.websocket.WebSocketExtension
    public List<WebSocketExtensionHeader> serverNegotiation(List<WebSocketExtensionHeader> requestedProtocols) {
        Object next;
        Iterator<T> it = requestedProtocols.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (p.a(((WebSocketExtensionHeader) next).getName(), "permessage-deflate")) {
                break;
            }
        }
        WebSocketExtensionHeader webSocketExtensionHeader = (WebSocketExtensionHeader) next;
        if (webSocketExtensionHeader == null) {
            return z.f19728i;
        }
        ArrayList arrayList = new ArrayList();
        for (x xVar : webSocketExtensionHeader.parseParameters()) {
            String str = (String) xVar.f22608i;
            String str2 = (String) xVar.f22609l;
            String lowerCase = str.toLowerCase(Locale.getDefault());
            switch (lowerCase.hashCode()) {
                case -708713803:
                    if (!lowerCase.equals("client_no_context_takeover")) {
                        throw new IllegalStateException(("Unsupported extension parameter: (" + str + ", " + str2 + ')').toString());
                    }
                    if (!o.h0(str2)) {
                        throw new IllegalStateException("Check failed.");
                    }
                    this.incomingNoContextTakeover = true;
                    arrayList.add("client_no_context_takeover");
                    break;
                    break;
                case 646404390:
                    if (!lowerCase.equals("client_max_window_bits")) {
                        throw new IllegalStateException(("Unsupported extension parameter: (" + str + ", " + str2 + ')').toString());
                    }
                    break;
                    break;
                case 1266201133:
                    if (!lowerCase.equals("server_no_context_takeover")) {
                        throw new IllegalStateException(("Unsupported extension parameter: (" + str + ", " + str2 + ')').toString());
                    }
                    if (!o.h0(str2)) {
                        throw new IllegalStateException("Check failed.");
                    }
                    this.outgoingNoContextTakeover = true;
                    arrayList.add("server_no_context_takeover");
                    break;
                    break;
                case 2034279582:
                    if (!lowerCase.equals("server_max_window_bits")) {
                        throw new IllegalStateException(("Unsupported extension parameter: (" + str + ", " + str2 + ')').toString());
                    }
                    if (Integer.parseInt(str2) != 15) {
                        throw new IllegalStateException("Only 15 window size is supported");
                    }
                    break;
                    break;
                default:
                    throw new IllegalStateException(("Unsupported extension parameter: (" + str + ", " + str2 + ')').toString());
            }
        }
        return Collections.singletonList(new WebSocketExtensionHeader("permessage-deflate", arrayList));
    }

    public final void setIncomingNoContextTakeover$ktor_websockets(boolean z) {
        this.incomingNoContextTakeover = z;
    }

    public final void setOutgoingNoContextTakeover$ktor_websockets(boolean z) {
        this.outgoingNoContextTakeover = z;
    }
}
