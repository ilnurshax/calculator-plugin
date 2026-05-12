package com.ilnur.calculator

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.ui.components.JBTextField
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JPanel

class CalculatorPopup(
    private val project: Project,
    private val editor: Editor,
    initialText: String
) {

    private val textField = JBTextField(initialText)

    fun show() {

        val panel = JPanel(BorderLayout())

        textField.preferredSize = Dimension(300, 40)

        panel.add(textField, BorderLayout.CENTER)

        val popup = JBPopupFactory.getInstance()
            .createComponentPopupBuilder(panel, textField)
            .setRequestFocus(true)
            .setFocusable(true)
            .setCancelOnClickOutside(true)
            .setMovable(false)
            .setResizable(false)
            .createPopup()

        textField.addKeyListener(object : KeyAdapter() {

            override fun keyPressed(e: KeyEvent) {

                if (e.keyCode == KeyEvent.VK_ENTER) {

                    val expression = textField.text

                    try {

                        val result = ExpressionEvaluator.evaluate(expression)

                        WriteCommandAction.runWriteCommandAction(project) {

                            val selectionModel = editor.selectionModel

                            if (selectionModel.hasSelection()) {

                                editor.document.replaceString(
                                    selectionModel.selectionStart,
                                    selectionModel.selectionEnd,
                                    result
                                )

                                selectionModel.removeSelection()

                            } else {

                                val offset = editor.caretModel.offset

                                editor.document.insertString(offset, result)
                            }
                        }

                        popup.closeOk(null)

                    } catch (ex: Exception) {

                        textField.text = "ERROR"

                    }
                }

                if (e.keyCode == KeyEvent.VK_ESCAPE) {
                    popup.cancel()
                }
            }
        })

        popup.showInBestPositionFor(editor)

        textField.selectAll()
    }
}