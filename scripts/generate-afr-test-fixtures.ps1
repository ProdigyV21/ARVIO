$ErrorActionPreference = 'Stop'
$assets = Join-Path $PSScriptRoot '../app/src/androidTest/assets/afr'
New-Item -ItemType Directory -Path $assets -Force | Out-Null
$fixtures = @(
    @{ Name = 'film-23976.mkv'; Rate = '24000/1001' },
    @{ Name = 'pal-25.mp4'; Rate = '25' },
    @{ Name = 'video-5994.mkv'; Rate = '60000/1001' }
)
foreach ($fixture in $fixtures) {
    $output = Join-Path $assets $fixture.Name
    & ffmpeg -hide_banner -loglevel error -y -f lavfi -i "testsrc2=size=256x144:rate=$($fixture.Rate)" `
        -t 12 -an -c:v libx264 -preset veryfast -crf 32 -pix_fmt yuv420p -g 48 -bf 2 $output
    if ($LASTEXITCODE -ne 0) { throw "Could not generate $($fixture.Name)" }
}
