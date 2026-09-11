$ErrorActionPreference = 'Stop'
$ffmpeg = (Get-Command ffmpeg -ErrorAction Stop).Source
$root = [IO.Path]::GetFullPath((Join-Path $PSScriptRoot '../../.playback-fixtures'))
New-Item -ItemType Directory -Force -Path $root | Out-Null
function Convert-Codec([string[]]$Arguments) {
    & $ffmpeg -hide_banner -loglevel error -y @Arguments
    if ($LASTEXITCODE -ne 0) { throw 'FFmpeg codec fixture generation failed.' }
}
$inputArgs = @('-f', 'lavfi', '-i', 'testsrc2=size=640x360:rate=24', '-f', 'lavfi', '-i', 'sine=frequency=440:sample_rate=48000', '-t', '80')
Convert-Codec ($inputArgs + @('-c:v', 'libx265', '-preset', 'ultrafast', '-x265-params', 'pools=4:log-level=error', '-g', '48', '-pix_fmt', 'yuv420p10le', '-tag:v', 'hvc1', '-c:a', 'aac', '-ac', '2', '-movflags', '+faststart', (Join-Path $root 'hevc.mp4')))
Convert-Codec ($inputArgs + @('-c:v', 'libvpx-vp9', '-deadline', 'realtime', '-cpu-used', '8', '-threads', '4', '-g', '48', '-c:a', 'libopus', '-ac', '2', (Join-Path $root 'vp9.webm')))
Convert-Codec ($inputArgs + @('-c:v', 'libsvtav1', '-preset', '12', '-svtav1-params', 'lp=4', '-g', '48', '-c:a', 'aac', '-ac', '2', '-movflags', '+faststart', (Join-Path $root 'av1.mp4')))
Write-Output "Synthetic codec fixtures ready in $root"
