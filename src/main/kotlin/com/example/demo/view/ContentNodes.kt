package com.example.demo.view

import javafx.application.Platform
import javafx.embed.swing.SwingFXUtils.toFXImage
import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.stage.FileChooser
import javafx.stage.Stage
import tornadofx.*
import java.io.File
import javax.imageio.ImageIO


class float_node : DraggableNode(){
    private val float_field = textfield {
        setLeftAnchor(this, 10.0)
        setRightAnchor(this, 10.0)
        setTopAnchor(this, 60.0)
        text = "0.0"
    }

    init {
        (this.refresh_node_but.parent as Pane).children.remove(this.refresh_node_but)

        node_name.text = "Float"

        (this.ImageView.parent as Pane).children.remove(this.ImageView)

        node_content.add(float_field)

        val out = out_()
        out.var_type = "float"
        node_content.add(out) //output node

        float_field.textProperty().addListener { _, oldvalue, newvalue ->
            if (newvalue == "" || !newvalue.matches("\\d{0,7}([\\.]\\d{0,4})?".toRegex())) {
                float_field.text = oldvalue;
            }
            out.content = float_field.text.toDouble().toString()
            this.full_upd()
        }
    }
}

class int_node : DraggableNode(){
    private val int_field = textfield {
        setLeftAnchor(this, 10.0)
        setRightAnchor(this, 10.0)
        setTopAnchor(this, 60.0)
        text = "0"
    }

    init {
        (this.refresh_node_but.parent as Pane).children.remove(this.refresh_node_but)

        node_name.text = "Int"

        (this.ImageView.parent as Pane).children.remove(this.ImageView)

        val out = out_()
        out.var_type = "int"
        node_content.add(out) //output node

        node_content.add(int_field)
        int_field.textProperty().addListener { _, oldvalue, newvalue ->
            if (newvalue == "" || !newvalue.matches("\\d{0,7}?".toRegex())) {
                int_field.text = oldvalue;
            }
            out.content = int_field.text.toInt().toString()
            this.full_upd()
        }
    }
}

class string_node : DraggableNode(){
    private val string_field = textfield {
        setLeftAnchor(this, 10.0)
        setRightAnchor(this, 10.0)
        setTopAnchor(this, 60.0)
    }
    init {
        (this.refresh_node_but.parent as Pane).children.remove(this.refresh_node_but)

        node_name.text = "String"

        (this.ImageView.parent as Pane).children.remove(this.ImageView)

        node_content.add(string_field)

        val out = out_()
        out.var_type = "string"
        node_content.add(out) //output node

        string_field.textProperty().addListener { _, oldvalue, newvalue ->
            out.content = string_field.text
            this.full_upd()
        }
    }
}

class image_node : DraggableNode(){
    private val fileButton = button {
        text = "Select Image"
        setBottomAnchor(this, 10.0)
        Platform.runLater { setLeftAnchor(this, (node_content.width/2 - this.width/2)) }

    }
    private var image: Image? = null
    private var file: File? = null

    init {
        (this.refresh_node_but.parent as Pane).children.remove(this.refresh_node_but)

        node_name.text = "Image"

        node_content.add(fileButton)

        val out = out_()
        out.var_type = "image"
        node_content.add(out) //output node

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
                out.content = image
                this.full_upd()
            }
        }
    }
}

class input_image_node : DraggableNode(){
    private val fileButton = button {
        text = "Select Image"
        setBottomAnchor(this, 10.0)
        Platform.runLater { setLeftAnchor(this, (node_content.width/2 - this.width/2)) }
    }
    private var image: Image? = null
    private var file: File? = null

    init {
        (this.delete_node_but.parent as Pane).children.remove(this.delete_node_but)
        (this.refresh_node_but.parent as Pane).children.remove(this.refresh_node_but)

        node_name.text = "Input"
        node_content.add(fileButton)

        val out = out_()
        out.var_type = "image"
        node_content.add(out) //output node

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
                out.content = image
                this.full_upd()
            }
        }
    }
}

class output_image_node : DraggableNode(){
    private var image: Image? = null
    private val viewButton = button {
        text = "View Image"
        setBottomAnchor(this, 10.0)
        Platform.runLater { setLeftAnchor(this, (node_content.width/2 - this.width/2)) }
    }

    init {
        (this.delete_node_but.parent as Pane).children.remove(this.delete_node_but)

        node_name.text = "Output"
        node_content.add(viewButton)

        var inp = in_()
        inp.var_type = "image"
        node_content.add(inp) //input node

        Platform.runLater { node_layout.layoutY = node_content.height }
        Platform.runLater { node_layout.layoutX = 5 * node_content.width}

        this.refresh_node_but.onAction = EventHandler {
            this.full_upd()

            this.on_refresh(inp.id)
        }

        viewButton.onAction = EventHandler {
            val fxmlLoader = FXMLLoader(javaClass.getResource("/image_view.fxml"))
            val root1 = fxmlLoader.load<Any>() as Parent
            if(image != null) {
                ((root1 as VBox).children[0] as ImageView).fitWidth = image?.width!!
                ((root1 as VBox).children[0] as ImageView).fitHeight = image?.height!!
            }
            ((root1 as VBox).children[0] as ImageView).image = image
            val stage = Stage()
            stage.scene = Scene(root1)
            stage.show()
        }

    }

    fun on_refresh(id: String?) {
        image = (node_content.children.filter { it.id == id }.first() as in_).content as Image?
        (node_content.children.filter { it.id == "ImageView" }.first() as ImageView).image = image
    }
}
