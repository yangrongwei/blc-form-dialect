This project site includes notes.

#Port Broadleaf `blc` dialect.


In package `org.broadleafcommerce.common.web.processor`

Thymeleaf is not the base for `ResourceBundleProcessor`.

`ResourceBundleProcessor` implements its dialect by it alone.




[How to Publish Maven Site Docs to BitBucket or GitHub Pages][mvn-site-gh]
[mvn-site-gh]: https://dzone.com/articles/how-publish-maven-site-docs


#About Thymeleaf-testing

`WebProcessingContextBuilder`  is the default value of `TestExecutor.processingContextBuilder` 

While `WebProcessingContextBuilder`  is using hard coded **/testing** for

1. servletContext.contextPath
1. request.contextPath
1. (included in ) request.requestURI
1. (included in ) request.requestURL

**/testing** can be overridden by subclassing `WebProcessingContextBuilder`. 
I don't it is necessary. The **/testing** is enough and _should be remembered_ for writing _.thtest_ file. 
