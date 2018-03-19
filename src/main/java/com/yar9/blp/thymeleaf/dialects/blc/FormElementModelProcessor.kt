package com.yar9.blp.thymeleaf.dialects.blc

import org.thymeleaf.context.ITemplateContext
import org.thymeleaf.model.*
import org.thymeleaf.processor.element.AbstractElementModelProcessor
import org.thymeleaf.processor.element.AbstractElementTagProcessor
import org.thymeleaf.processor.element.IElementModelStructureHandler
import org.thymeleaf.processor.element.IElementTagStructureHandler
import org.thymeleaf.templatemode.TemplateMode
import java.util.*

/**
 * ------------------
 * Origin is  [org/broadleafcommerce/common/web/processor/FormProcessor.java]
 * ------------------
 *
 *  Works with the `<blc:form>` element tag.
 *
 *  This implementation simply replace `<blc:form>` with standard `<form>` tag
 *
 *  @author Raymond Yang
 *  @since 2018-3-19
 */
class FormElementModelProcessor(
        dialectPrefix: String
) : AbstractElementModelProcessor(
        TemplateMode.HTML,
        dialectPrefix,
        "form", // Tag name: match specifically this tag
        true,
        null,
        false,
        1001 // Precedence (inside dialect's own precedence)
) {
    override fun doProcess(
            context: ITemplateContext,
            model: IModel,
            structureHandler: IElementModelStructureHandler
    ) {

        val tag = "blc:form"
        var openTagPosition: Int = -1
        var closeTagPosition: Int = -1

        val count = 0..(model.size() - 1)
        for (i in count) {
            val element = model.get(i)
            if (element is IOpenElementTag && element.elementCompleteName == tag) {
                openTagPosition = if (openTagPosition == -1) i else throw IllegalArgumentException("Found more than one open tag for '$tag'")
            }

            if (element is ICloseElementTag && element.elementCompleteName == tag) {
                closeTagPosition = if (closeTagPosition == -1) i else throw IllegalArgumentException("Found more than one close tag for '$tag'")
            }
        }

        val openTag = model.get(openTagPosition) as IOpenElementTag
        var closeTag = model.get(closeTagPosition) as ICloseElementTag

        val modelFactory = context.modelFactory
        val formOpen = modelFactory.createOpenElementTag("form", openTag.attributeMap, AttributeValueQuotes.DOUBLE, false)
        val formClose = modelFactory.createCloseElementTag("form")

        model.replace(openTagPosition, formOpen)
        model.replace(closeTagPosition, formClose)

    }
}