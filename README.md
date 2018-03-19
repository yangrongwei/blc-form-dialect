# ray-blc-thymeleaf-dialect
Extracting &amp; Leaning the Thymeleaf BLCDialect of Broadleaf 

Broadleaf has 

1. Built-in Thymeleaf dialects, _BLCDialect_ and _BLCAdminDialect_ .
2. A annotation-based and XML-based mechanism for its presentation layer.  

This combination can genernate CRUD UI for any entities automatically. I was impressed.

But their code are not ease to reuse for other projects. They are heavily depending on other Broadleaf internal stuffs (persistence, ...). This project is trying to Learn, Extract and Reuse this combination.

BTW, Broadleaf uses thymeleaf-layout-dialect as well. 
