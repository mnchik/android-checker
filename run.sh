#!/usr/bin/env bash
# usage: run.sh <task-dir-name>
set +e
# Gradle старых шаблонов работает только на Java 8, sdkmanager/эмулятор — на 17
T="$1"
ROOT="$(pwd)"
GV=$(grep -o 'gradle-[0-9]*' "$ROOT/tasks/$T/gradle/wrapper/gradle-wrapper.properties" | head -1 | grep -o '[0-9]*')
if [ "${GV:-4}" -ge 7 ]; then export JAVA_HOME="$JAVA_HOME_17_X64"; else export JAVA_HOME="$JAVA_HOME_8_X64"; fi
export PATH="$JAVA_HOME/bin:$PATH"
echo "using $JAVA_HOME (gradle $GV)"
mkdir -p "$ROOT/out"
cd "$ROOT/tasks/$T" || exit 0
chmod +x gradlew
adb logcat -c
if [ "$T" = "317613_logcat" ]; then
  ./gradlew --no-daemon installDebug
  adb shell am start -n com.example.company.myapplication/.MainActivity
  sleep 8
else
  ./gradlew --no-daemon connectedDebugAndroidTest 2>&1 | tee "$ROOT/out/$T.gradle.txt"
fi
for f in app/build/outputs/androidTest-results/connected/*.xml; do [ -f "$f" ] && python3 -c "
import sys,re
s=open(sys.argv[1]).read()
for m in re.finditer(r'<testcase name=\"([^\"]*)\"[^>]*?(/>|>(.*?)</testcase>)',s,re.S):
    b=m.group(3) or ''
    f=re.search(r'<failure message=\"([^\"]*)\"',b)
    print('TEST',m.group(1),'FAIL: '+f.group(1)[:300].replace(chr(10),' ') if f else 'ok')
" "$f" > "$ROOT/out/$T.fail.txt"; done
adb logcat -d -v time -s CHECKER:* > "$ROOT/out/$T.txt"
adb logcat -d -v time > "$ROOT/out/$T.full.txt"
cat "$ROOT/out/$T.txt"
exit 0
