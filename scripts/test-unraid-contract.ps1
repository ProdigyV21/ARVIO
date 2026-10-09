param([string]$RepositoryRoot = (Split-Path -Parent $PSScriptRoot))
$ErrorActionPreference = 'Stop'

# These are temporary, synthetic metadata fixtures. No repository files, Docker
# resources, credentials or network destinations are mutated or contacted.
$root = (Resolve-Path -LiteralPath $RepositoryRoot).Path
$validator = Join-Path $root 'scripts/check-unraid.ps1'
$temporaryParent = [System.IO.Path]::GetFullPath([System.IO.Path]::GetTempPath())
$fixtureRoot = Join-Path $temporaryParent ('arvio-unraid-contract-' + [guid]::NewGuid().ToString('N'))
$null = New-Item -ItemType Directory -Path $fixtureRoot

function Read-FixtureXml([string]$File) {
    $settings = [System.Xml.XmlReaderSettings]::new()
    $settings.DtdProcessing = [System.Xml.DtdProcessing]::Prohibit
    $settings.XmlResolver = $null
    $reader = [System.Xml.XmlReader]::Create($File, $settings)
    try {
        $document = [System.Xml.XmlDocument]::new()
        $document.XmlResolver = $null
        $document.Load($reader)
        return $document
    } finally { $reader.Dispose() }
}

$cases = @(
    @{ Name = 'baseline'; Accept = $true; Mutate = {} },
    @{ Name = 'duplicate-host-network'; Mutate = {
        param($template, $profile)
        $node = $template.CreateElement('Network'); $node.InnerText = 'host'
        $null = $template.DocumentElement.AppendChild($node)
    } },
    @{ Name = 'duplicate-privileged-flag'; Mutate = {
        param($template, $profile)
        $node = $template.CreateElement('Privileged'); $node.InnerText = 'true'
        $null = $template.DocumentElement.AppendChild($node)
    } },
    @{ Name = 'duplicate-extra-privileges'; Mutate = {
        param($template, $profile)
        $node = $template.CreateElement('ExtraParams'); $node.InnerText = '--privileged'
        $null = $template.DocumentElement.AppendChild($node)
    } },
    @{ Name = 'unknown-postargs'; Mutate = {
        param($template, $profile)
        $node = $template.CreateElement('PostArgs'); $node.InnerText = '--privileged'
        $null = $template.DocumentElement.AppendChild($node)
    } },
    @{ Name = 'unknown-device-node'; Mutate = {
        param($template, $profile)
        $node = $template.CreateElement('Device'); $node.InnerText = '/dev:/dev'
        $null = $template.DocumentElement.AppendChild($node)
    } },
    @{ Name = 'missing-network'; Mutate = {
        param($template, $profile)
        $null = $template.DocumentElement.RemoveChild($template.DocumentElement.SelectSingleNode('Network'))
    } },
    @{ Name = 'unexpected-template-namespace'; Mutate = {
        param($template, $profile)
        $old = $template.DocumentElement
        $node = $template.CreateElement('x', 'Container', 'urn:unraid-smoke:unsupported')
        $node.SetAttribute('version', '2')
        foreach ($child in @($old.ChildNodes)) { $null = $node.AppendChild($child.CloneNode($true)) }
        $null = $template.ReplaceChild($node, $old)
    } },
    @{ Name = 'nested-overview-payload'; Mutate = {
        param($template, $profile)
        $null = $template.DocumentElement.SelectSingleNode('Overview').AppendChild($template.CreateElement('Script'))
    } },
    @{ Name = 'unsafe-metadata-url'; Mutate = {
        param($template, $profile)
        $template.DocumentElement.SelectSingleNode('Support').InnerText = 'https://fixture-user:fixture-password@example.invalid/support'
    } },
    @{ Name = 'source-repository-template-url'; Mutate = {
        param($template, $profile)
        $template.DocumentElement.SelectSingleNode('TemplateURL').InnerText = 'https://raw.githubusercontent.com/ProdigyV21/ARVIO/main/templates/arvio-web.xml'
    } },
    @{ Name = 'wrong-template-readme'; Mutate = {
        param($template, $profile)
        $template.DocumentElement.SelectSingleNode('ReadMe').InnerText = 'https://github.com/ProdigyV21/ARVIO/blob/main/README.md'
    } },
    @{ Name = 'source-repository-profile'; Mutate = {
        param($template, $profile)
        $profile.DocumentElement.SelectSingleNode('WebPage').InnerText = 'https://github.com/ProdigyV21/ARVIO'
    } },
    @{ Name = 'unrelated-android-xml'; Mutate = {}; ExtraXml = '<resources><string name="example">Android resource, not an Unraid app</string></resources>' },
    @{ Name = 'broken-extra-xml'; Mutate = {}; ExtraXml = '<Container>' },
    @{ Name = 'duplicate-app-template'; Mutate = {}; DuplicateTemplate = $true },
    @{ Name = 'saved-key-default'; Mutate = {
        param($template, $profile)
        $template.DocumentElement.SelectSingleNode('Config[@Target="TMDB_API_KEY"]').SetAttribute('Default', 'synthetic-key-never-a-real-secret')
    } },
    @{ Name = 'saved-key-content'; Mutate = {
        param($template, $profile)
        $template.DocumentElement.SelectSingleNode('Config[@Target="TMDB_API_KEY"]').InnerText = 'synthetic-key-never-a-real-secret'
    } },
    @{ Name = 'credential-disguised-as-port'; Mutate = {
        param($template, $profile)
        $template.DocumentElement.SelectSingleNode('Config[@Target="TMDB_API_KEY"]').SetAttribute('Type', 'Port')
    } },
    @{ Name = 'credential-disguised-as-mount'; Mutate = {
        param($template, $profile)
        $template.DocumentElement.SelectSingleNode('Config[@Target="TMDB_API_KEY"]').SetAttribute('Type', 'Path')
    } },
    @{ Name = 'unmasked-secret'; Mutate = {
        param($template, $profile)
        $template.DocumentElement.SelectSingleNode('Config[@Target="TRAKT_CLIENT_SECRET"]').SetAttribute('Mask', 'false')
    } },
    @{ Name = 'unexpected-config-attribute'; Mutate = {
        param($template, $profile)
        $template.DocumentElement.SelectSingleNode('Config[@Target="TMDB_API_KEY"]').SetAttribute('Unexpected', 'synthetic')
    } },
    @{ Name = 'duplicate-config-target'; Mutate = {
        param($template, $profile)
        $template.DocumentElement.SelectSingleNode('Config[@Target="SIMKL_CLIENT_ID"]').SetAttribute('Target', 'TRAKT_CLIENT_ID')
    } },
    @{ Name = 'private-proxy-optout'; Mutate = {
        param($template, $profile)
        $node = $template.DocumentElement.SelectSingleNode('Config[@Target="ALLOW_PRIVATE_PROXY"]')
        $node.SetAttribute('Default', 'true'); $node.InnerText = 'true'
    } },
    @{ Name = 'port-value-diverges'; Mutate = {
        param($template, $profile)
        $template.DocumentElement.SelectSingleNode('Config[@Target="3000"]').InnerText = '9000'
    } },
    @{ Name = 'empty-profile'; Mutate = {
        param($template, $profile)
        $profile.DocumentElement.SelectSingleNode('Profile').InnerText = ' '
    } },
    @{ Name = 'duplicate-profile'; Mutate = {
        param($template, $profile)
        $null = $profile.DocumentElement.AppendChild($profile.DocumentElement.SelectSingleNode('Profile').CloneNode($true))
    } },
    @{ Name = 'unknown-profile-node'; Mutate = {
        param($template, $profile)
        $null = $profile.DocumentElement.AppendChild($profile.CreateElement('Unexpected'))
    } },
    @{ Name = 'profile-url-contains-token'; Mutate = {
        param($template, $profile)
        $profile.DocumentElement.SelectSingleNode('Icon').InnerText = 'https://example.invalid/icon.png?token=synthetic'
    } },
    @{ Name = 'empty-license'; Mutate = {}; EmptyLicense = $true },
    @{ Name = 'doctype-external-entity'; Mutate = {}; Dtd = $true }
)

try {
    foreach ($case in $cases) {
        if ($case.Name -notmatch '^[a-z0-9-]+$') { throw 'Invalid fixed fixture name.' }
        $caseRoot = Join-Path $fixtureRoot $case.Name
        $null = New-Item -ItemType Directory -Path (Join-Path $caseRoot 'templates')
        Copy-Item -LiteralPath (Join-Path $root 'LICENSE') -Destination (Join-Path $caseRoot 'LICENSE')
        $template = Read-FixtureXml (Join-Path $root 'templates/arvio-web.xml')
        $profile = Read-FixtureXml (Join-Path $root 'ca_profile.xml')
        & $case.Mutate $template $profile
        $templatePath = Join-Path $caseRoot 'templates/arvio-web.xml'
        $template.Save($templatePath)
        $profile.Save((Join-Path $caseRoot 'ca_profile.xml'))
        if ($case.EmptyLicense) { [System.IO.File]::WriteAllText((Join-Path $caseRoot 'LICENSE'), '') }
        if ($case.ExtraXml) { [System.IO.File]::WriteAllText((Join-Path $caseRoot 'unrelated.xml'), $case.ExtraXml) }
        if ($case.DuplicateTemplate) { Copy-Item -LiteralPath $templatePath -Destination (Join-Path $caseRoot 'templates/duplicate.xml') }
        if ($case.Dtd) {
            # The parser must reject DTDs before resolving this nonexistent file.
            $payload = '<!DOCTYPE Container [<!ENTITY xxe SYSTEM "file:///arvio-unraid-synthetic-nonexistent.txt">]><Container version="2"><Name>&xxe;</Name></Container>'
            [System.IO.File]::WriteAllText($templatePath, $payload)
        }
        $accepted = $false
        try {
            $null = & $validator -RepositoryRoot $caseRoot -RequireTemplateFeed
            $accepted = $true
        } catch {
            if ($case.Accept) { throw }
            if ($case.Dtd -and $_.Exception.Message -notmatch 'DTD') { throw 'DTD fixture was not rejected by the safe XML parser.' }
        }
        if ($accepted -ne [bool]$case.Accept) { throw "Unexpected acceptance for fixture $($case.Name)." }
        Write-Output "$($case.Name): passed"
    }
    Write-Output "$($cases.Count) Unraid metadata fixtures passed (1 valid, $($cases.Count - 1) rejected). No network/image/catalog acceptance was tested."
} finally {
    # Delete only this uniquely created test directory after verifying its exact
    # resolved parent/leaf. Never remove a workspace or arbitrary computed root.
    $resolvedFixture = [System.IO.Path]::GetFullPath($fixtureRoot)
    $parent = [System.IO.Path]::GetFullPath((Split-Path -Parent $resolvedFixture))
    if ($parent.TrimEnd([System.IO.Path]::DirectorySeparatorChar) -ne $temporaryParent.TrimEnd([System.IO.Path]::DirectorySeparatorChar) -or
        (Split-Path -Leaf $resolvedFixture) -notmatch '^arvio-unraid-contract-[0-9a-f]{32}$') {
        throw 'Refusing unsafe test-fixture cleanup target.'
    }
    if (Test-Path -LiteralPath $resolvedFixture) { Remove-Item -LiteralPath $resolvedFixture -Recurse -Force }
}
