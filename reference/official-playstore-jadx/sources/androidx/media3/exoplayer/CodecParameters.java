package androidx.media3.exoplayer;

import android.media.MediaFormat;
import android.os.Bundle;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class CodecParameters {
    public static final CodecParameters EMPTY = new Builder().build();
    private final Map<String, Object> params;

    public static final class Builder {
        private final Map<String, Object> params;

        public CodecParameters build() {
            return new CodecParameters(this.params);
        }

        public Builder remove(String str) {
            this.params.remove(str);
            return this;
        }

        public Builder setByteBuffer(String str, ByteBuffer byteBuffer) {
            if (byteBuffer == null) {
                this.params.put(str, null);
                return this;
            }
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBuffer.remaining());
            byteBufferAllocate.put(byteBuffer.duplicate());
            byteBufferAllocate.flip();
            this.params.put(str, byteBufferAllocate);
            return this;
        }

        public Builder setFloat(String str, float f10) {
            this.params.put(str, Float.valueOf(f10));
            return this;
        }

        public Builder setInteger(String str, int i10) {
            this.params.put(str, Integer.valueOf(i10));
            return this;
        }

        public Builder setLong(String str, long j10) {
            this.params.put(str, Long.valueOf(j10));
            return this;
        }

        public Builder setString(String str, String str2) {
            this.params.put(str, str2);
            return this;
        }

        public Builder() {
            this.params = new HashMap();
        }

        private Builder(CodecParameters codecParameters) {
            this.params = new HashMap(codecParameters.params);
        }
    }

    public static Builder createFrom(MediaFormat mediaFormat, Set<String> set) {
        Builder builder = new Builder();
        for (String str : set) {
            if (mediaFormat.containsKey(str)) {
                int valueTypeForKey = mediaFormat.getValueTypeForKey(str);
                if (valueTypeForKey == 1) {
                    builder.setInteger(str, mediaFormat.getInteger(str));
                } else if (valueTypeForKey == 2) {
                    builder.setLong(str, mediaFormat.getLong(str));
                } else if (valueTypeForKey == 3) {
                    builder.setFloat(str, mediaFormat.getFloat(str));
                } else if (valueTypeForKey == 4) {
                    builder.setString(str, mediaFormat.getString(str));
                } else if (valueTypeForKey == 5) {
                    builder.setByteBuffer(str, mediaFormat.getByteBuffer(str));
                }
            }
        }
        return builder;
    }

    public void applyTo(MediaFormat mediaFormat) {
        for (Map.Entry<String, Object> entry : this.params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                mediaFormat.setString(key, null);
            } else if (value instanceof Integer) {
                mediaFormat.setInteger(key, ((Integer) value).intValue());
            } else if (value instanceof Long) {
                mediaFormat.setLong(key, ((Long) value).longValue());
            } else if (value instanceof Float) {
                mediaFormat.setFloat(key, ((Float) value).floatValue());
            } else if (value instanceof String) {
                mediaFormat.setString(key, (String) value);
            } else if (value instanceof ByteBuffer) {
                mediaFormat.setByteBuffer(key, (ByteBuffer) value);
            }
        }
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CodecParameters) {
            return this.params.equals(((CodecParameters) obj).params);
        }
        return false;
    }

    public Object get(String str) {
        return this.params.get(str);
    }

    public int hashCode() {
        return this.params.hashCode();
    }

    public Set<String> keySet() {
        return this.params.keySet();
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        for (Map.Entry<String, Object> entry : this.params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                if (value instanceof Integer) {
                    bundle.putInt(key, ((Integer) value).intValue());
                } else if (value instanceof Long) {
                    bundle.putLong(key, ((Long) value).longValue());
                } else if (value instanceof Float) {
                    bundle.putFloat(key, ((Float) value).floatValue());
                } else if (value instanceof String) {
                    bundle.putString(key, (String) value);
                } else if (value instanceof ByteBuffer) {
                    ByteBuffer byteBuffer = (ByteBuffer) value;
                    byte[] bArr = new byte[byteBuffer.remaining()];
                    byteBuffer.duplicate().get(bArr);
                    bundle.putByteArray(key, bArr);
                }
            }
        }
        return bundle;
    }

    private CodecParameters(Map<String, Object> map) {
        this.params = Collections.unmodifiableMap(map);
    }
}
