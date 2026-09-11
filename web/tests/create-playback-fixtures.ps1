$ErrorActionPreference = 'Stop'
$ffmpeg = (Get-Command ffmpeg -ErrorAction Stop).Source
$root = [IO.Path]::GetFullPath((Join-Path $PSScriptRoot '../../.playback-fixtures'))
New-Item -ItemType Directory -Force -Path $root, (Join-Path $root 'hls') | Out-Null

function Convert-Fixture([string[]]$Arguments) {
    & $ffmpeg -hide_banner -loglevel error -y @Arguments
    if ($LASTEXITCODE -ne 0) { throw 'FFmpeg fixture generation failed.' }
}

$mp4 = Join-Path $root 'aac.mp4'
Convert-Fixture @('-f', 'lavfi', '-i', 'testsrc2=size=960x540:rate=24', '-f', 'lavfi', '-i', 'sine=frequency=440:sample_rate=48000', '-t', '100', '-c:v', 'libx264', '-preset', 'veryfast', '-profile:v', 'baseline', '-g', '48', '-pix_fmt', 'yuv420p', '-c:a', 'aac', '-ac', '2', '-movflags', '+faststart', $mp4)
Convert-Fixture @('-i', $mp4, '-c', 'copy', (Join-Path $root 'aac.mkv'))
Convert-Fixture @('-i', $mp4, '-c:v', 'copy', '-c:a', 'eac3', (Join-Path $root 'eac3.mkv'))
Convert-Fixture @('-i', $mp4, '-c:v', 'copy', '-c:a', 'dca', '-strict', '-2', (Join-Path $root 'dts.mkv'))
Convert-Fixture @('-i', $mp4, '-c:v', 'copy', '-an', (Join-Path $root 'silent.mkv'))
Convert-Fixture @('-i', $mp4, '-f', 'lavfi', '-i', 'sine=frequency=880:sample_rate=48000', '-map', '0:v', '-map', '0:a', '-map', '1:a', '-t', '100', '-c:v', 'copy', '-c:a', 'aac', '-ac', '2', '-metadata:s:a:0', 'language=eng', '-metadata:s:a:1', 'language=nld', (Join-Path $root 'multi.mkv'))
Convert-Fixture @('-i', $mp4, '-c', 'copy', '-f', 'hls', '-hls_time', '2', '-hls_playlist_type', 'vod', (Join-Path $root 'hls/index.m3u8'))
Write-Output "Synthetic fixtures ready in $root"
