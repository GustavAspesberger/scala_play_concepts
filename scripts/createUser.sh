#!/usr/bin/env bash

set -o nounset

file=$(mktemp)
curl http://localhost:9000/api/user \
  -u 'user:pass' -X POST -H 'Content-Type: application/json' -sv --max-time 30 \
  -d @- << EOF > ${file}
{
  "name": "Test",
  "surname": "Erer",
  "email": "test.erer@tal.com"
}
EOF
jq '.' ${file}
if [ $? -ne 0 ]; then
  cat ${file}
  echo
fi
rm ${file}