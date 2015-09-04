# Internet Archive - Wayback Machine (Unofficial) client for mirroring sites
Mirror any website on Internet (Web) Archive - Wayback Machine
(read more: https://archive.org/about/)

Basically Internet Archive is a "museum" of the internet, since it's early days.

It crawls the web indexing websites in different times and saves mirrors that you may see whenever you want. The interesting point is that you can tell then which site they need to index and when.

That's what we do, but since their API doens't let us save a mirror, I've decided to create this simple code to create our mirror and return it with the original and the mirrored urls in JSON format.

##Example usage

Using:
```java
public static void main(String[] args) throws Exception {
  JSONObject fetchedUrl = Mirrors.getSiteMirror("http://"
    + "www.avianca.com.br/amigo/seuamigo-nossoamigo-encerrada");
  System.out.println(fetchedUrl);
}
```

Output: 
```json
{"mirror":"http:\/\/web.archive.org\/web\/20150904175556\/http:\/\/www.avianca.com.br\/amigo\/seuamigo-nossoamigo-encerrada","url":"http:\/\/www.avianca.com.br\/amigo\/seuamigo-nossoamigo-encerrada"}
```

Formatted output: <br />
```
{
    "mirror": "http://web.archive.org/web/20150904175556/http://www.avianca.com.br/amigo/seuamigo-nossoamigo-encerrada",
    "url": "http://www.avianca.com.br/amigo/seuamigo-nossoamigo-encerrada"
}
```
###Contact
Author: Rafael Silvério Amaral<br />
E-mail: contato@rafael.science

###License
https://github.com/rafaelsilverioit/webarchive/blob/master/LICENSE
