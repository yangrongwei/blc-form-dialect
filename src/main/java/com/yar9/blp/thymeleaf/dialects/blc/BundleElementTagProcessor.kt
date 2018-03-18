package com.yar9.blp.thymeleaf.dialects.blc

import org.thymeleaf.context.ITemplateContext
import org.thymeleaf.model.IProcessableElementTag
import org.thymeleaf.processor.element.AbstractElementTagProcessor
import org.thymeleaf.processor.element.IElementTagStructureHandler
import org.thymeleaf.templatemode.TemplateMode

/**
 * Works with the `<blc:bundle>` element tag.
 *
 * ```
 *  <blc:bundle name="" mapping-prefix="" file="">
 *  </blc:bundle>
 *  ```
 * ------------------
 * Origin is
 *  [org/broadleafcommerce/common/web/processor/ResourceBundleProcessor.java]
 *
 * This implementation simply translates `<blc:bundle>` to these standard element tags
 *
 *  - `<script src="$fileName">`
 *  - `<link rel="stylesheet" type="text/css" href="$fileName">`
 *
 *  Where $fileName := $mapping-prefix +  one element of $file
 *
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
        val files = tag.getAttributeValue("files")?.split(',').orEmpty()

        val srcList = files.map {
            //"@{'$mappingPrefix$it'}"
            "$mappingPrefix$it"
        }
        // val script = "<script type='text/javascript' src='$src'></script>"

        val modelFactory = context.getModelFactory()
        val model = modelFactory.createModel()

        val elementList = srcList.map { src: String ->
            modelFactory.createStandaloneElementTag(
                    "script",
                    "src",
                    src)
        }

        elementList.forEach {
            model.add(it)
        }

        structureHandler.replaceWith(model, false);

        // 1. pass unit test ....
        // 2. use this in learn-admin
    }
}
