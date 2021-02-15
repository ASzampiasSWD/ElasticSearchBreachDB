for /l %%x in (0, 1, 347) do (
   echo Currently on file: %%x
   curl -s -H "Content-Type: application/x-ndjson" -XPOST "localhost:9200/_bulk" --data-binary @webhost_%%x.json > NUL
)