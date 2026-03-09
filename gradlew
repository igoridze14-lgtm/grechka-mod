#!/usr/bin/env sh
# Gradle wrapper script
if [ -z "$GRADLE_HOME" ]; then
  export GRADLE_HOME="$(dirname "$0")/gradle"
fi

exec "$GRADLE_HOME/bin/gradle" "$@"