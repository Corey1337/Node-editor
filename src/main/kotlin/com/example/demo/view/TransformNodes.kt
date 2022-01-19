package com.example.demo.view

class move_node : DraggableNode(){
    init {
        node_name.text = "Move"
    }
}

class scale_node : DraggableNode(){
    init {
        node_name.text = "Scale"
    }
}

class rotate_node : DraggableNode(){
    init {
        node_name.text = "Rotate"
    }
}