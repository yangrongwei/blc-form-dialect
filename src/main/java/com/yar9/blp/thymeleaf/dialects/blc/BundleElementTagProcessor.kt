package com.yar9.blp.thymeleaf.dialects.blc

import org.thymeleaf.context.ITemplateContext
import org.thymeleaf.model.AttributeValueQuotes
import org.thymeleaf.model.IProcessableElementTag
import org.thymeleaf.model.ITemplateEvent
import org.thymeleaf.processor.element.AbstractElementTagProcessor
import org.thymeleaf.processor.element.IElementTagStructureHandler
import org.thymeleaf.standard.expression.StandardExpressions
import org.thymeleaf.templatemode.TemplateMode

/**
 * ------------------
 * Origin is  [org/broadleafcommerce/common/web/processor/ResourceBundleProcessor.java]
 * ------------------
 *
 * Works with the `<blc:bundle>` element tag.
 * ```
 *     <blc:bundle name="" mapping-prefix="" files="csv-file-list"> </blc:bundle>
 * ```
 *
 * This implementation translates `<blc:bundle>` to
 * ```
 *      <script src="$fileName">
 * ```
 *  or
 * ```
 *      <link rel="stylesheet"  href="$fileName">
 * ```
 *  Where
 * ```
 *       $fileName := Evaluated result of Thymeleaf link expression  @{ mapping-prefix + file }
 * ```
 *
 *  @author Raymond Yang
 *  @since
 */
class BundleElementTagProcessor(
        dialectPrefix: String
) : AbstractElementTagProcessor(
        TemplateMode.HTML,
        dialectPrefix,
        "bundle",
        true,
        null,
        false,
        10000
) {
    override fun doProcess(
            context: ITemplateContext,
            tag: IProcessableElementTag,
            structureHandler: IElementTagStructureHandler
    ) {
        val name = tag.getAttributeValue("name")
        val mappingPrefix = tag.getAttributeValue("mapping-prefix")
        val files = tag.getAttributeValue("files") ?: "";

        val fileList = files.split(',').map { it.trim() }

        val expressionParser = StandardExpressions.getExpressionParser(context.configuration)
        val modelFactory = context.modelFactory
        val model = modelFactory.createModel()

        fileList.forEach {
            val expression = "@{$mappingPrefix$it}"
            val value = expressionParser.parseExpression(context, expression).execute(context) as String

            val element: ITemplateEvent = when {
                value.endsWith(".js", true) -> {
                    modelFactory.createStandaloneElementTag(
                            "script",
                            "src",
                            value)
                }
                value.endsWith(".css", true) -> {
                    modelFactory.createStandaloneElementTag(
                            "link",
                            mapOf("rel" to "stylesheet", "href" to value),
                            AttributeValueQuotes.SINGLE,
                            false,
                            true
                    )
                }
                else -> throw IllegalArgumentException("Unknown extension for: '$value' - only .js and .css are supported")
            }
            model.add(element)
            model.add(modelFactory.createText("\n")) // Add white-character(s) between elements, in order to pass Thymeleaf-testing.
        }
        structureHandler.replaceWith(model, true);
    }
}
