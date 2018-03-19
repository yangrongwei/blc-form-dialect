package com.yar9.blp.thymeleaf.dialects.blc

import org.thymeleaf.dialect.AbstractProcessorDialect
import org.thymeleaf.processor.IProcessor
import org.thymeleaf.standard.StandardDialect

class BlcDialect : AbstractProcessorDialect(
        "BLC Dialect",
        "blc",
        StandardDialect.PROCESSOR_PRECEDENCE
) {
    override fun getProcessors(dialectPrefix: String): MutableSet<IProcessor> {
        val processors = mutableSetOf<IProcessor>(
                BundleElementTagProcessor(dialectPrefix),
                FormElementModelProcessor(dialectPrefix)
        )
        return processors
    }
}