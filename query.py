#!/usr/bin/env python
 
# Author: Amanda Szampias
 
from elasticsearch import Elasticsearch
 
es = Elasticsearch([{'host': 'localhost', 'port': 9200}])
 
def get_email(author, maxsize=50, doc_type=False):
  return es.search(index='breach*', size=maxsize, q='Email:"' + author + '"')

def main():
  print(get_email('test@gmail.com', 100))
  print('-----------------------------')
 
if __name__ == "__main__":
  main()