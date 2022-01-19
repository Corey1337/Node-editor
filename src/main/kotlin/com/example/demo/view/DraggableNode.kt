package com.example.demo.view

import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.geometry.Point2D
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.input.ClipboardContent
import javafx.scene.input.TransferMode
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.BorderPane

open class DraggableNode: AnchorPane() {
    lateinit var but: Button
    lateinit var node_content: BorderPane
    lateinit var node_layout: AnchorPane
    lateinit var node_name: Label

    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("/draggable_node.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<DraggableNode>()

        node_content.top.onDragDetected = EventHandler { mouseEvent ->
            val offset = Point2D(
                mouseEvent.sceneX - node_layout.layoutX,
                mouseEvent.sceneY - node_layout.layoutY
            )
            node_layout.parent.onDragOver = EventHandler { dragEvent ->
                node_layout.layoutX = dragEvent.sceneX - offset.x
                node_layout.layoutY = dragEvent.sceneY - offset.y
                dragEvent.acceptTransferModes(*TransferMode.ANY)
                dragEvent.consume()
            }
            node_layout.parent.onDragDropped = EventHandler { dragEvent ->
                node_layout.parent.onDragOver = null
                node_layout.parent.onDragDropped = null
                dragEvent.isDropCompleted = true
                dragEvent.consume()
            }
            val content = ClipboardContent()
            content.putString("node")
            node_layout.startDragAndDrop(*TransferMode.ANY).setContent(content)
            mouseEvent.consume()
        }

        node_content.onDragDone = EventHandler { dragEvent ->
            node_layout.parent.onDragOver = null
            node_layout.parent.onDragDropped = null
            dragEvent.consume()
        }
        but.text = "123"
    }
}