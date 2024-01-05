# DSL搜索（match_phrase）

## 1、match_phrase 短语匹配

match：分词后只要有匹配就返回；

match_phras：分词结果必须在text字段分词中都包含，而且顺序必须相同，而且必须都是连续的。(搜索比较严格)；

slop：允许词语间跳过的数量

```
POST     /shop/_doc/_search
{
    "query": {
        "match_phrase": {
            "desc": {
            	//都有，顺序相同，连续
            	"query": "大学 毕业 研究生",
            	 // 允许跳过数量
            	"slop": 2
            }
        }
    }
}
```

