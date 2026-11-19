# Thumbnail extractor compatibility patch

`FrameExtractor.java` and `FrameExtractorInternal.java` are Apache-2.0 sources from
AndroidX Media3 **1.9.0**, relocated into ARVIO's namespace. Original license headers
are retained. Upstream:
https://github.com/androidx/media/tree/1.9.0/libraries/inspector/src/main/java/androidx/media3/inspector

The only behavioral changes are null-safe asynchronous completion callbacks:

- READY without a decoded video frame fails the preview future with
  `ERROR_CODE_DECODING_FORMAT_UNSUPPORTED` instead of throwing on the main looper.
- Late errors and GPU results with no pending consumer cannot crash the app.
- Unclaimed GPU bitmaps are recycled.

The READY crash was reproduced with a remote Dolby Vision 4K remux on the API 31
TV emulator. Do not suppress application-wide uncaught exceptions to work around it.
The playback player is unaffected by this private thumbnail-only copy.

When upgrading Media3, compare these files with the matching upstream version and
remove the relocation once equivalent fixes are verified. Keep the focused callback
regression tests and the opt-in remote-file instrumentation test.
