This is Aster SQL-MR UDF to do morphological analysis  using kuromoji.

How to build (I used Intellij IDEA)
1. File -> Project Structure -> Artifacts -> + -> JAR -> From modules with dependencies -> OK
2. Build -> Build Artifacts... -> Build
3. Please check kuromoji.jar file is generated under ./out/artifacts/kuromoji_jar directory

Install to aster instance
1. Login to Aster with act
2. Install kuromoji.jar
```
\install kuromoji.jar

(uninstall)
\remove kuromoji.jar
```

Execute kuromoji udf
```sql
select * from kuromoji ( on (select "テラデータは、アナリティクスとデータが企業の潜在能力を解き放つと信じています。"))
;
```
```
0,テラ,名詞,一般,*,*,テラ,テラ,
2,データ,名詞,一般,*,*,データ,データ,
5,は,助詞,係助詞,*,*,ハ,ワ,
6,、,記号,読点,*,*,、,、,
7,アナリティクス,名詞,一般,*,*,*,*,
14,と,助詞,並立助詞,*,*,ト,ト,
15,データ,名詞,一般,*,*,データ,データ,
18,が,助詞,格助詞,一般,*,ガ,ガ,
19,企業,名詞,一般,*,*,キギョウ,キギョー,
21,の,助詞,連体化,*,*,ノ,ノ,
22,潜在,名詞,サ変接続,*,*,センザイ,センザイ,
24,能力,名詞,一般,*,*,ノウリョク,ノーリョク,
26,を,助詞,格助詞,一般,*,ヲ,ヲ,
27,解き放つ,動詞,自立,*,*,トキハナツ,トキハナツ,
31,と,助詞,格助詞,引用,*,ト,ト,
32,信じ,動詞,自立,*,*,シンジ,シンジ,
34,て,助詞,接続助詞,*,*,テ,テ,
35,い,動詞,非自立,*,*,イ,イ,
36,ます,助動詞,*,*,*,マス,マス,
38,。,記号,句点,*,*,。,。,
```