package com.ilnur.calculator

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project

class CalculatorAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project: Project = e.project ?: return
        val editor: Editor = e.getData(com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR)
            ?: return

        val selectionModel = editor.selectionModel

        val selectedText = if (selectionModel.hasSelection()) {
            selectionModel.selectedText ?: ""
        } else {
            ""
        }

        CalculatorPopup(project, editor, selectedText).show()
    }
}