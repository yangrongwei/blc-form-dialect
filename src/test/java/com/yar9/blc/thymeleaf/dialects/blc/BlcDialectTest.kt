package com.yar9.blc.thymeleaf.dialects.blc

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.thymeleaf.dialect.IDialect
import org.thymeleaf.standard.StandardDialect
import org.thymeleaf.testing.templateengine.context.web.WebProcessingContextBuilder
import org.thymeleaf.testing.templateengine.engine.TestExecutor
import org.thymeleaf.testing.templateengine.testable.ITest
import java.util.*
import javax.servlet.ServletContext
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


private class MyProcessingContextBuilder : WebProcessingContextBuilder() {
    override fun doAdditionalVariableProcessing(
            test: ITest,
            request: HttpServletRequest,
            response: HttpServletResponse?,
            servletContext: ServletContext,
            locale: Locale?,
            variables: MutableMap<String, Any>?
    ) {
        super.doAdditionalVariableProcessing(test, request, response, servletContext, locale, variables)
        Mockito.`when`(servletContext.contextPath).thenReturn("/sss")
        Mockito.`when`(request.contextPath).thenReturn("/rrr")  // replace hardcode contextPath in mock
    }
}

internal class BlcDialectTest {

//    @Test
//    fun hello() {
//        val executor = TestExecutor()
//        executor.execute("classpath:blctestset/hello.thtest")
//    }

//    private val executor: TestExecutor by lazy {
//        val dialects = listOf<IDialect>(
//                BlcDialect(),
//                StandardDialect()
//        )
//        val executor = TestExecutor()
//        executor.dialects = dialects
//        executor
//    }

    @Test
    fun bundleJs() {
        val dialects = listOf<IDialect>(
                BlcDialect(),
                StandardDialect()
        )
        val executor = TestExecutor()
        executor.dialects = dialects
        executor.execute("classpath:blctestset/bundle-js.thtest")
        Assertions.assertTrue(executor.isAllOK());
    }

    @Test
    fun bundleCss() {
        val dialects = listOf<IDialect>(
                BlcDialect(),
                StandardDialect()
        )
        val executor = TestExecutor()
        executor.dialects = dialects
        executor.execute("classpath:blctestset/bundle-css.thtest")
        Assertions.assertTrue(executor.isAllOK());
    }

    @Test
    fun formLogin() {
        val executor = TestExecutor()
        executor.dialects = listOf<IDialect>(
                BlcDialect()
        )
        executor.execute("classpath:blctestset/form-login.thtest")
        Assertions.assertTrue(executor.isAllOK());
    }
}
