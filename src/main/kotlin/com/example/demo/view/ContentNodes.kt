package com.example.demo.view

import javafx.application.Platform
import javafx.embed.swing.SwingFXUtils.toFXImage
import javafx.event.EventHandler
import javafx.scene.control.TextField
import javafx.scene.control.cell.TextFieldTableCell
import javafx.scene.image.Image
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.stage.FileChooser
import javafx.stage.Stage
import javafx.util.converter.FloatStringConverter
import tornadofx.*
import java.io.File
import javax.imageio.ImageIO


class float_node : DraggableNode(){
    private val float_field = textfield {
        AnchorPane.setLeftAnchor(this, 10.0)
        AnchorPane.setRightAnchor(this, 10.0)
        AnchorPane.setTopAnchor(this, 60.0)
        text = "0.0"
    }

    init {
        node_name.text = "Float"

        (this.ImageView.parent as Pane).children.remove(this.ImageView)

        node_content.add(float_field)

        float_field.textProperty().addListener { _, oldvalue, newvalue ->
            if (!newvalue.matches("\\d{0,7}([\\.]\\d{0,4})?".toRegex())) {
                float_field.text = oldvalue;
            }
        }

        node_content.add(rectangle{
            width = 15.0
            height = 15.0
            stroke = Color.AQUA
            AnchorPane.setRightAnchor(this, 7.0)
            AnchorPane.setBottomAnchor(this, 15.0)
        })
    }
}

class int_node : DraggableNode(){
    private val int_field = textfield {
        AnchorPane.setLeftAnchor(this, 10.0)
        AnchorPane.setRightAnchor(this, 10.0)
        AnchorPane.setTopAnchor(this, 60.0)
        text = "0"
    }

    init {
        node_name.text = "Int"

        (this.ImageView.parent as Pane).children.remove(this.ImageView)

        node_content.add(int_field)

        int_field.textProperty().addListener { _, oldvalue, newvalue ->
            if (!newvalue.matches("\\d{0,7}?".toRegex())) {
                int_field.text = oldvalue;
            }
        }

        node_content.add(rectangle{
            width = 15.0
            height = 15.0
            stroke = Color.AQUA
            AnchorPane.setRightAnchor(this, 7.0)
            AnchorPane.setBottomAnchor(this, 15.0)
        })
    }
}

class string_node : DraggableNode(){
    private val string_field = textfield {
        AnchorPane.setLeftAnchor(this, 10.0)
        AnchorPane.setRightAnchor(this, 10.0)
        AnchorPane.setTopAnchor(this, 60.0)
    }
    init {
        node_name.text = "String"

        (this.ImageView.parent as Pane).children.remove(this.ImageView)

        node_content.add(string_field)

        node_content.add(rectangle{
            width = 15.0
            height = 15.0
            stroke = Color.AQUA
            AnchorPane.setRightAnchor(this, 7.0)
            AnchorPane.setBottomAnchor(this, 15.0)
        })
    }
}

class image_node : DraggableNode(){
    private val fileButton = button {
        text = "Select Image"
        AnchorPane.setBottomAnchor(this, 10.0)
        Platform.runLater { AnchorPane.setLeftAnchor(this, (node_content.width/2 - this.width/2).toDouble()) }

    }
    private var image: Image? = null
    private var file: File? = null

    init {
        node_name.text = "Image"
        node_content.add(fileButton)
        node_content.add(rectangle{
            width = 15.0
            height = 15.0
            stroke = Color.AQUA
            AnchorPane.setRightAnchor(this, 7.0)
            AnchorPane.setBottomAnchor(this, 15.0)
        })

        fileButton.onAction = EventHandler {
            val fileChooser = FileChooser()
            fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("Image Files", "*.png", "*jpg"))
            this.file = fileChooser.showOpenDialog(Stage())
            try{
                image = toFXImage(ImageIO.read(this.file), null)
            }
            catch (e: Exception) { }
            if(image !== null){
                this.ImageView.image = image
            }
        }
    }
}

class input_image_node : DraggableNode(){
    private val fileButton = button {
        text = "Select Image"
        AnchorPane.setBottomAnchor(this, 10.0)
        Platform.runLater { AnchorPane.setLeftAnchor(this, (node_content.width/2 - this.width/2).toDouble()) }

    }
    private var image: Image? = null
    private var file: File? = null

    init {
        (this.delete_node_but.parent as Pane).children.remove(this.delete_node_but)
        node_name.text = "Input"
        node_content.add(fileButton)
        node_content.add(rectangle{
            width = 15.0
            height = 15.0
            stroke = Color.AQUA
            AnchorPane.setRightAnchor(this, 7.0)
            AnchorPane.setBottomAnchor(this, 15.0)
        })

        Platform.runLater { node_layout.layoutY = node_content.height }
        Platform.runLater { node_layout.layoutX = node_content.width}

        fileButton.onAction = EventHandler {
            val fileChooser = FileChooser()
            fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("Image Files", "*.png", "*jpg"))
            this.file = fileChooser.showOpenDialog(Stage())
            try{
                image = toFXImage(ImageIO.read(this.file), null)
            }
            catch (e: Exception) { }
            if(image !== null){
                this.ImageView.image = image
            }
        }
    }
}

class output_image_node : DraggableNode(){
    private var image: Image? = null
    private var file: File? = null

    init {
        (this.delete_node_but.parent as Pane).children.remove(this.delete_node_but)
        node_name.text = "Output"
        node_content.add(rectangle{
            width = 15.0
            height = 15.0
            stroke = Color.AQUA
            AnchorPane.setLeftAnchor(this, 7.0)
            AnchorPane.setBottomAnchor(this, 15.0)
        })

        Platform.runLater { node_layout.layoutY = node_content.height }
        Platform.runLater { node_layout.layoutX = 5 * node_content.width}
    }
}