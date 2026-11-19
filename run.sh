#!/usr/bin/env bash
# usage: run.sh <task-dir-name>
set +e
T="$1"
ROOT="$(pwd)"
mkdir -p "$ROOT/out"
cd "$ROOT/tasks/$T" || exit 0
chmod +x gradlew
adb logcat -c
if [ "$T" = "317613_logcat" ]; then
  ./gradlew --no-daemon installDebug
  adb shell am start -n com.example.company.myapplication/.MainActivity
  sleep 8
else
  ./gradlew --no-daemon connectedDebugAndroidTest
fi
adb logcat -d -v time -s CHECKER:* > "$ROOT/out/$T.txt"
adb logcat -d -v time > "$ROOT/out/$T.full.txt"
cat "$ROOT/out/$T.txt"
exit 0
