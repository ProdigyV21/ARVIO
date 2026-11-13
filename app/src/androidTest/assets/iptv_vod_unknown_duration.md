Synthetic MPEG-TS fixture for IPTV VOD tracking (#893), derived from the existing
seek_preview_device_test.mp4 asset. Generated with:

    ffmpeg -stream_loop -1 -i seek_preview_device_test.mp4 -t 20 -c copy -f mpegts iptv_vod_unknown_duration.ts

The emulator test serves it as chunked HTTP without a Content-Length header to
exercise Media3's unknown-duration path. No real subscription or provider media.
