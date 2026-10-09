param(
    [string]$RepositoryRoot = (Split-Path -Parent $PSScriptRoot),
    [switch]$RequireTemplateFeed
)
$ErrorActionPreference = 'Stop'

function Assert-Contract([bool]$Condition, [string]$Message) {
    if (-not $Condition) { throw $Message }
}

function Read-SafeXml([string]$Path) {
    $settings = [System.Xml.XmlReaderSettings]::new()
    $settings.DtdProcessing = [System.Xml.DtdProcessing]::Prohibit
    $settings.XmlResolver = $null
    $reader = [System.Xml.XmlReader]::Create($Path, $settings)
    try {
        $document = [System.Xml.XmlDocument]::new()
        $document.XmlResolver = $null
        $document.Load($reader)
        return $document
    } finally { $reader.Dispose() }
}

function Assert-FlatField([System.Xml.XmlElement]$Element, [string]$Label) {
    Assert-Contract ($Element.NamespaceURI -eq '') "Namespaced $Label is not supported."
    Assert-Contract ($Element.Attributes.Count -eq 0) "Unexpected $Label attributes."
    Assert-Contract (@($Element.ChildNodes | Where-Object { $_.NodeType -eq [System.Xml.XmlNodeType]::Element }).Count -eq 0) "Nested $Label elements are not supported."
}

$root = (Resolve-Path -LiteralPath $RepositoryRoot).Path
$profile = Read-SafeXml (Join-Path $root 'ca_profile.xml')
$template = Read-SafeXml (Join-Path $root 'templates/arvio-web.xml')
Assert-Contract ($profile.DocumentElement.Name -eq 'CommunityApplications' -and $profile.DocumentElement.NamespaceURI -eq '' -and $profile.DocumentElement.Attributes.Count -eq 0) 'Invalid profile root.'
$profileFields = @('Profile','Icon','WebPage','Forum')
foreach ($element in @($profile.DocumentElement.ChildNodes | Where-Object { $_.NodeType -eq [System.Xml.XmlNodeType]::Element })) {
    Assert-Contract ($element.Name -in $profileFields) 'Unexpected repository profile field.'
    Assert-FlatField $element "profile $($element.Name)"
}
foreach ($field in $profileFields) {
    Assert-Contract ($profile.DocumentElement.SelectNodes($field).Count -eq 1) "Missing or duplicate profile $field."
    Assert-Contract (-not [string]::IsNullOrWhiteSpace($profile.DocumentElement.SelectSingleNode($field).InnerText)) "Empty profile $field."
}
$licensePath = Join-Path $root 'LICENSE'
Assert-Contract (Test-Path -LiteralPath $licensePath -PathType Leaf) 'Missing root LICENSE.'
Assert-Contract ((Get-Item -LiteralPath $licensePath).Length -gt 0) 'Empty root LICENSE.'
$container = $template.DocumentElement
Assert-Contract ($container.LocalName -eq 'Container' -and $container.NamespaceURI -eq '' -and $container.GetAttribute('version') -eq '2' -and $container.Attributes.Count -eq 1) 'Expected unnamespaced Container version 2.'
$singleFields = @('Name','Repository','Registry','Network','Shell','Privileged','ExtraParams','Beta','Icon','WebUI','Overview','Project','Support','ReadMe','TemplateURL','Category','License')
$allowedFields = $singleFields + @('ExtraSearchTerms','Config')
foreach ($element in @($container.ChildNodes | Where-Object { $_.NodeType -eq [System.Xml.XmlNodeType]::Element })) {
    Assert-Contract ($element.LocalName -in $allowedFields -and $element.NamespaceURI -eq '') 'Unexpected Docker template field.'
    if ($element.LocalName -ne 'Config') { Assert-FlatField $element $element.LocalName }
}
foreach ($field in $singleFields) {
    Assert-Contract ($container.SelectNodes($field).Count -eq 1) "Missing or duplicate $field."
    Assert-Contract (-not [string]::IsNullOrWhiteSpace($container.SelectSingleNode($field).InnerText)) "Empty $field."
}
Assert-Contract ($container.SelectNodes('ExtraSearchTerms').Count -le 1) 'Duplicate search metadata.'
Assert-Contract ($container.SelectSingleNode('Name').InnerText -eq 'ARVIO-Web') 'Unexpected app identity.'
Assert-Contract ($container.SelectSingleNode('Repository').InnerText -eq 'ghcr.io/prodigyv21/arvio-web:unraid-preview') 'Unexpected preview image.'
Assert-Contract ($container.SelectSingleNode('Network').InnerText -eq 'bridge') 'Host networking is not required.'
Assert-Contract ($container.SelectSingleNode('Shell').InnerText -eq 'sh') 'Unexpected container shell.'
Assert-Contract ($container.SelectSingleNode('Privileged').InnerText -eq 'false') 'Privileged mode is forbidden.'
Assert-Contract ($container.SelectSingleNode('Beta').InnerText -eq 'true') 'Preview must be labelled beta.'
Assert-Contract ($container.SelectSingleNode('ExtraParams').InnerText -eq '--init --cap-drop=ALL --security-opt=no-new-privileges') 'Unexpected extra Docker privileges.'
Assert-Contract ($container.SelectSingleNode('WebUI').InnerText -eq 'http://[IP]:[PORT:3000]/') 'WebUI must match container port.'
Assert-Contract ($container.SelectSingleNode('Category').InnerText -eq 'MediaApp:Video') 'Unexpected category.'
Assert-Contract ($container.SelectSingleNode('License').InnerText -eq 'Apache-2.0 (ARVIO source); third-party components retain their own licences') 'Unexpected distribution license.'
Assert-Contract ($null -eq $container.SelectSingleNode('Config[@Target="TELEGRAM_API_ID"]')) 'Telegram is not available in this preview.'
foreach ($field in @('Project','Support','Icon','ReadMe','TemplateURL','Registry')) {
    $uri = [uri]$container.SelectSingleNode($field).InnerText
    Assert-Contract ($uri.IsAbsoluteUri -and $uri.Scheme -eq 'https' -and -not $uri.UserInfo -and -not $uri.Query -and -not $uri.Fragment) "Unsafe $field URL."
}
foreach ($field in @('Icon','WebPage','Forum')) {
    $uri = [uri]$profile.DocumentElement.SelectSingleNode($field).InnerText
    Assert-Contract ($uri.IsAbsoluteUri -and $uri.Scheme -eq 'https' -and -not $uri.UserInfo -and -not $uri.Query -and -not $uri.Fragment) "Unsafe profile $field URL."
}
Assert-Contract ($profile.DocumentElement.SelectSingleNode('WebPage').InnerText -eq 'https://github.com/ProdigyV21/ARVIO-Unraid') 'Profile must identify the dedicated template feed, not the Android source repository.'
Assert-Contract ($container.SelectSingleNode('TemplateURL').InnerText -eq 'https://raw.githubusercontent.com/ProdigyV21/ARVIO-Unraid/main/templates/arvio-web.xml') 'TemplateURL must identify the canonical raw XML in the dedicated feed.'
Assert-Contract ($container.SelectSingleNode('ReadMe').InnerText -eq 'https://github.com/ProdigyV21/ARVIO-Unraid/blob/main/README.md') 'ReadMe must identify the dedicated feed installation guide.'
if ($RequireTemplateFeed) {
    $allowedXml = @('ca_profile.xml', 'templates/arvio-web.xml')
    $xmlFiles = @(Get-ChildItem -LiteralPath $root -Recurse -File -Filter '*.xml' |
        Where-Object { $_.FullName -notmatch '[\\/]\.git[\\/]' } |
        ForEach-Object { [System.IO.Path]::GetRelativePath($root, $_.FullName).Replace('\', '/') })
    Assert-Contract ($xmlFiles.Count -eq $allowedXml.Count) 'Template feed must contain exactly one repository profile and one Docker application XML; do not submit Android resource XML.'
    foreach ($path in $xmlFiles) {
        Assert-Contract ($path -cin $allowedXml) "Unexpected XML in the template feed: $path"
    }
}
$allowed = @('3000','TMDB_API_KEY','ALLOW_PRIVATE_PROXY','TRAKT_CLIENT_ID','TRAKT_CLIENT_SECRET','SIMKL_CLIENT_ID','SIMKL_V2_CLIENT_ID','SIMKL_CLIENT_SECRET','ARVIO_RESOLVER_URL')
$configs = @($container.SelectNodes('Config'))
$targets = @($configs | ForEach-Object { $_.GetAttribute('Target') })
Assert-Contract ($targets.Count -eq $allowed.Count -and @($targets | Select-Object -Unique).Count -eq $targets.Count) 'Missing or duplicated configuration targets.'
foreach ($entry in $configs) {
    Assert-Contract (@($entry.ChildNodes | Where-Object { $_.NodeType -eq [System.Xml.XmlNodeType]::Element }).Count -eq 0) 'Nested configuration elements are not supported.'
    $configAttributes = @('Name','Target','Default','Mode','Description','Type','Display','Required','Mask')
    foreach ($attribute in $entry.Attributes) {
        Assert-Contract ($attribute.Name -in $configAttributes -and $attribute.NamespaceURI -eq '') 'Unexpected configuration attribute.'
    }
    foreach ($attributeName in @('Name','Target','Default','Description','Type','Display','Required','Mask')) {
        Assert-Contract ($entry.HasAttribute($attributeName)) "Missing configuration $attributeName attribute."
    }
    Assert-Contract (-not [string]::IsNullOrWhiteSpace($entry.GetAttribute('Name')) -and -not [string]::IsNullOrWhiteSpace($entry.GetAttribute('Description'))) 'Configuration fields must explain their purpose.'
    Assert-Contract ($entry.GetAttribute('Display') -in @('always','advanced') -and $entry.GetAttribute('Required') -in @('true','false') -and $entry.GetAttribute('Mask') -in @('true','false')) 'Invalid configuration display/boolean attributes.'
    $target = $entry.GetAttribute('Target')
    Assert-Contract ($target -in $allowed) 'Unexpected configuration target or mount.'
    Assert-Contract ($entry.GetAttribute('Type') -in @('Port','Variable')) 'Filesystem/device mounts are not needed.'
    if ($target -eq '3000') {
        Assert-Contract ($entry.GetAttribute('Type') -eq 'Port' -and $entry.GetAttribute('Mode') -eq 'tcp' -and $entry.GetAttribute('Default') -eq '8133' -and $entry.InnerText -eq '8133' -and $entry.GetAttribute('Required') -eq 'true' -and $entry.GetAttribute('Mask') -eq 'false') 'Unexpected port mapping.'
    } elseif ($target -eq 'ALLOW_PRIVATE_PROXY') {
        Assert-Contract ($entry.GetAttribute('Type') -eq 'Variable' -and $entry.GetAttribute('Mode') -eq '') 'Proxy switch must be a runtime variable.'
        Assert-Contract ($entry.GetAttribute('Default') -eq 'false' -and $entry.InnerText -eq 'false') 'Private proxy must be opt-in.'
    } else {
        Assert-Contract ($entry.GetAttribute('Type') -eq 'Variable' -and $entry.GetAttribute('Mode') -eq '') 'Credential fields must be runtime variables, not mounts or ports.'
        Assert-Contract ($entry.GetAttribute('Default') -eq '' -and [string]::IsNullOrWhiteSpace($entry.InnerText)) 'Public template must not contain saved credentials.'
    }
    if ($target -in @('TMDB_API_KEY','TRAKT_CLIENT_SECRET','SIMKL_CLIENT_SECRET','TELEGRAM_API_HASH')) {
        Assert-Contract ($entry.GetAttribute('Mask') -eq 'true') 'Sensitive field must be masked in the form.'
    }
}
Assert-Contract ($configs.Where({ $_.GetAttribute('Target') -eq 'TMDB_API_KEY' })[0].GetAttribute('Required') -eq 'true') 'TMDB setup requirement must be explicit.'
Write-Output 'Unraid XML/profile contract passed. This does not confirm image publication, Unraid installation or catalog acceptance.'
if ($RequireTemplateFeed) { Write-Output 'Dedicated feed contains 1 Docker application template and 0 unrelated XML files.' }
