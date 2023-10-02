$datetime = Get-Date -Format FileDateTime
$fileName = "scout_reports_$datetime.csv"

New-Item -Path . -Name $fileName -ItemType "file"

$output = adb shell content query --uri content://com.cyberknights4911.scouting.provider/report
$handleHeaders = $true

foreach ($line in $output) {
  $pairs = $line -split ', '
  if ($handleHeaders) {
    $firstHeader = $true
    foreach ($pair in $pairs) {
      $thing = $pair -split '='
      $header = $thing[0]
      if ($firstHeader) {
        $splits = $header -split ' '
        $header = $splits[2]
        $firstHeader = $false
      }
      Add-Content $fileName "$header," -NoNewline
    }
    Add-Content $fileName ""
    $handleHeaders = $false
  }
  foreach ($pair in $pairs) {
    $thing = $pair -split '='
    $value = $thing[1]
    Add-Content $fileName "$value," -NoNewline
  }
  Add-Content $fileName ""
}