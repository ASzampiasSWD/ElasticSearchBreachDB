for /l %%x in (1, 1, 2834) do (
   echo On file: %%x;
   curl -s -H "Content-Type: application/x-ndjson" -XPOST "localhost:9200/_bulk" --data-binary @linkedin_%%x.json > NUL;
)