package org.drinkless.tdlib;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.drinkless.tdlib.TdApi;

/* JADX INFO: loaded from: classes5.dex */
public final class Client {
    private static final AtomicLong clientCount;
    private static final AtomicLong currentQueryId;
    private static final ConcurrentHashMap<Integer, ExceptionHandler> defaultExceptionHandlers;
    private static final ConcurrentHashMap<Long, Handler> handlers;
    private static final ResponseReceiver responseReceiver;
    private static final ConcurrentHashMap<Integer, Handler> updateHandlers;
    private final int nativeClientId;

    public interface ExceptionHandler {
        void onException(Throwable th);
    }

    public static class ExecutionException extends Exception {
        public final TdApi.Error error;

        public ExecutionException(TdApi.Error error) {
            super(error.code + ": " + error.message);
            this.error = error;
        }
    }

    public static class Handler {
        final ExceptionHandler exceptionHandler;
        final ResultHandler resultHandler;

        public Handler(ResultHandler resultHandler, ExceptionHandler exceptionHandler) {
            this.resultHandler = resultHandler;
            this.exceptionHandler = exceptionHandler;
        }
    }

    public interface LogMessageHandler {
        void onLogMessage(int i10, String str);
    }

    public static class ResponseReceiver implements Runnable {
        private static final int MAX_EVENTS = 1000;
        private final int[] clientIds;
        private final long[] eventIds;
        private final TdApi.Object[] events;
        public boolean isRun;

        public /* synthetic */ ResponseReceiver(int i10) {
            this();
        }

        private void processResult(int i10, long j10, TdApi.Object object) {
            boolean z = j10 == 0 && (object instanceof TdApi.UpdateAuthorizationState) && (((TdApi.UpdateAuthorizationState) object).authorizationState instanceof TdApi.AuthorizationStateClosed);
            Handler handler = (Handler) (j10 == 0 ? Client.updateHandlers.get(Integer.valueOf(i10)) : Client.handlers.remove(Long.valueOf(j10)));
            if (handler != null) {
                try {
                    handler.resultHandler.onResult(object);
                } catch (Throwable th) {
                    ExceptionHandler exceptionHandler = handler.exceptionHandler;
                    if (exceptionHandler == null) {
                        exceptionHandler = (ExceptionHandler) Client.defaultExceptionHandlers.get(Integer.valueOf(i10));
                    }
                    if (exceptionHandler != null) {
                        try {
                            exceptionHandler.onException(th);
                        } catch (Throwable unused) {
                        }
                    }
                }
            }
            if (z) {
                Client.updateHandlers.remove(Integer.valueOf(i10));
                Client.defaultExceptionHandlers.remove(Integer.valueOf(i10));
                Client.clientCount.decrementAndGet();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                int iE = Client.e(this.clientIds, this.eventIds, this.events);
                for (int i10 = 0; i10 < iE; i10++) {
                    processResult(this.clientIds[i10], this.eventIds[i10], this.events[i10]);
                    this.events[i10] = null;
                }
            }
        }

        private ResponseReceiver() {
            this.isRun = false;
            this.clientIds = new int[1000];
            this.eventIds = new long[1000];
            this.events = new TdApi.Object[1000];
        }
    }

    public interface ResultHandler {
        void onResult(TdApi.Object object);
    }

    static {
        try {
            System.loadLibrary("tdjni");
        } catch (UnsatisfiedLinkError e5) {
            e5.printStackTrace();
        }
        defaultExceptionHandlers = new ConcurrentHashMap<>();
        updateHandlers = new ConcurrentHashMap<>();
        handlers = new ConcurrentHashMap<>();
        currentQueryId = new AtomicLong();
        clientCount = new AtomicLong();
        responseReceiver = new ResponseReceiver(0);
    }

    private Client(ResultHandler resultHandler, ExceptionHandler exceptionHandler, ExceptionHandler exceptionHandler2) {
        clientCount.incrementAndGet();
        int iCreateNativeClient = createNativeClient();
        this.nativeClientId = iCreateNativeClient;
        if (resultHandler != null) {
            updateHandlers.put(Integer.valueOf(iCreateNativeClient), new Handler(resultHandler, exceptionHandler));
        }
        if (exceptionHandler2 != null) {
            defaultExceptionHandlers.put(Integer.valueOf(iCreateNativeClient), exceptionHandler2);
        }
        send(new TdApi.GetOption("version"), null, null);
    }

    public static Client create(ResultHandler resultHandler, ExceptionHandler exceptionHandler, ExceptionHandler exceptionHandler2) {
        Client client = new Client(resultHandler, exceptionHandler, exceptionHandler2);
        ResponseReceiver responseReceiver2 = responseReceiver;
        synchronized (responseReceiver2) {
            try {
                if (!responseReceiver2.isRun) {
                    responseReceiver2.isRun = true;
                    Thread thread = new Thread(responseReceiver2, "TDLib thread");
                    thread.setDaemon(true);
                    thread.start();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return client;
    }

    private static native int createNativeClient();

    public static /* bridge */ /* synthetic */ int e(int[] iArr, long[] jArr, TdApi.Object[] objectArr) {
        return nativeClientReceive(iArr, jArr, objectArr, 100000.0d);
    }

    public static <T extends TdApi.Object> T execute(TdApi.Function<T> function) throws ExecutionException {
        TdApi.Error error = (T) nativeClientExecute(function);
        if (error instanceof TdApi.Error) {
            throw new ExecutionException(error);
        }
        return error;
    }

    private static native TdApi.Object nativeClientExecute(TdApi.Function function);

    private static native int nativeClientReceive(int[] iArr, long[] jArr, TdApi.Object[] objectArr, double d4);

    private static native void nativeClientSend(int i10, long j10, TdApi.Function function);

    private static native void nativeClientSetLogMessageHandler(int i10, LogMessageHandler logMessageHandler);

    public static void setLogMessageHandler(int i10, LogMessageHandler logMessageHandler) {
        nativeClientSetLogMessageHandler(i10, logMessageHandler);
    }

    public void send(TdApi.Function function, ResultHandler resultHandler, ExceptionHandler exceptionHandler) {
        long jIncrementAndGet = currentQueryId.incrementAndGet();
        if (resultHandler != null) {
            handlers.put(Long.valueOf(jIncrementAndGet), new Handler(resultHandler, exceptionHandler));
        }
        nativeClientSend(this.nativeClientId, jIncrementAndGet, function);
    }

    public void send(TdApi.Function function, ResultHandler resultHandler) {
        send(function, resultHandler, null);
    }
}
