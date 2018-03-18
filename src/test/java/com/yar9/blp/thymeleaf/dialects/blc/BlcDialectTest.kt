package com.yar9.blp.thymeleaf.dialects.blc

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.thymeleaf.dialect.IDialect
import org.thymeleaf.testing.templateengine.engine.TestExecutor

internal class BlcDialectTest {

    @Test
    fun hello() {
        val executor = TestExecutor()
        executor.execute("classpath:blctestset/hello.thtest")
    }

    @Test
    fun bundleJs() {
        val dialects = listOf<IDialect>(BlcDialect())
        val blcDialect : IDialect = BlcDialect()
        val executor = TestExecutor()
//        exector.dialects.add(blcDialect)
        executor.dialects = dialects
        executor.execute("classpath:blctestset/bundle-js.thtest")
        Assertions.assertTrue(executor.isAllOK());
    }
}