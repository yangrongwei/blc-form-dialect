package com.yar9.blp.thymeleaf.processor

import org.thymeleaf.context.ITemplateContext
import org.thymeleaf.dialect.AbstractProcessorDialect
import org.thymeleaf.engine.AttributeName
import org.thymeleaf.model.IProcessableElementTag
import org.thymeleaf.processor.IProcessor
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor
import org.thymeleaf.processor.element.IElementTagStructureHandler
import org.thymeleaf.templatemode.TemplateMode

class Dialect(
) : AbstractProcessorDialect(
        "BLC Dialect",
        "blc",
        1000
) {
    override fun getProcessors(dialectPrefix: String): Set<IProcessor> {
        val processors = setOf(ResourceBundleProcessor(dialectPrefix))
        return processors
    }
}


abstract class DialectAbstractAttributeTagProcessor(
        dialectPrefix: String,
        attributeName: String,
        precedence: Int
) : AbstractAttributeTagProcessor(
        TemplateMode.HTML, // This processor will apply only to HTML mode
        dialectPrefix, // Prefix to be applied to name for matching
        null, // No tag name: match any tag name
        false,  // No prefix to be applied to tag name
        attributeName,
        true,  // Apply dialect prefix to attribute name
        precedence,   // Precedence (inside dialect's precedence)
        true // Remove the matched attribute afterwards
)

/**
 * Referencing [org.broadleafcommerce.common.web.processor.ResourceBundleProcessor]
 */
class ResourceBundleProcessor(
        dialectPrefix: String
) : DialectAbstractAttributeTagProcessor(
        dialectPrefix,
        "sayto",
        10000
) {

    override fun doProcess(
            p0: ITemplateContext?,
            p1: IProcessableElementTag?,
            p2: AttributeName?,
            p3: String?,
            p4: IElementTagStructureHandler?
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
