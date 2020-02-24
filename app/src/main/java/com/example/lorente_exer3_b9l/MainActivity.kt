package com.example.lorente_exer3_b9l

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         var board : Array<Array<Int>> = arrayOf(
             arrayOf(1,1,1,1,1),
             arrayOf(1,1,1,1,1),
             arrayOf(1,1,1,1,1),
             arrayOf(1,1,1,1,1),
             arrayOf(1,1,1,1,1)
        )


        findViewById<Button>(R.id.change_button).setOnClickListener{
            changeNickname(it)
        }

        setListeners(board)

    }

    private fun changeNickname(view: View) {
        val editText = findViewById<EditText>(R.id.editText)
        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)

        nicknameTextView.text = editText.text

        editText.visibility = View.GONE
        view.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE
    }

    private fun setListeners(board: Array<Array<Int>>) {
        val boxList: List<List<Int>> = listOf(
            listOf(R.id.tile_11, R.id.tile_12, R.id.tile_13, R.id.tile_14, R.id.tile_15),
            listOf(R.id.tile_21, R.id.tile_22, R.id.tile_23, R.id.tile_24, R.id.tile_25),
            listOf(R.id.tile_31, R.id.tile_32, R.id.tile_33, R.id.tile_34, R.id.tile_35),
            listOf(R.id.tile_41, R.id.tile_42, R.id.tile_43, R.id.tile_44, R.id.tile_45),
            listOf(R.id.tile_51, R.id.tile_52, R.id.tile_53, R.id.tile_54, R.id.tile_55)
        )

        for (row: Int in (0..4)) {
            for(column: Int in (0..4)) {
                findViewById<TextView>(boxList[row][column]).setOnClickListener {
                    changeColor(it, board, boxList,row,column)
                }
            }
        }

    }

    private fun changeColor(view: View, board: Array<Array<Int>>, boxList: List<List<Int>>, row : Int, column: Int) {

//        var id = resources.getResourceEntryName(view.id)
//        var id_split = id.split("_")
//        var index = id_split[1]
//        Toast.makeText(this, index, Toast.LENGTH_LONG).show()
//
//        // Getting the row and column index from the
//        var rowChar = index[0]
//        var columnChar = index[1]
//
//        var row = rowChar.toInt()
//        var column = columnChar.toInt()
        if(board[row][column] == 1) {
            view.setBackgroundColor(Color.BLACK)
            board[row][column] = 0
        }
        else{
            view.setBackgroundColor(Color.WHITE)
            board[row][column] = 1
        }

        flipLights(board,boxList,row,column)

    }

    private fun flipLights(board: Array<Array<Int>>, boxList: List<List<Int>>, row: Int, column: Int) {

        // For the right box
        try {
            if (board[row][column + 1] == 1) {
                findViewById<TextView>(boxList[row][column + 1]).setBackgroundColor(Color.BLACK)
            } else {
                findViewById<TextView>(boxList[row][column + 1]).setBackgroundColor(Color.WHITE)
            }
        }catch (e: Exception) {}

        // For Left Box
        try {
            if (board[row][column - 1] == 1) {
                findViewById<TextView>(boxList[row][column - 1]).setBackgroundColor(Color.BLACK)
            } else {
                findViewById<TextView>(boxList[row][column - 1]).setBackgroundColor(Color.WHITE)
            }
        }catch (e: Exception) {}

        // For bottom box
        try {
            if (board[row + 1][column] == 1) {
                findViewById<TextView>(boxList[row + 1][column]).setBackgroundColor(Color.BLACK)
            } else {
                findViewById<TextView>(boxList[row + 1][column + 1]).setBackgroundColor(Color.WHITE)
            }
        }catch (e: Exception) {}

        // For Top Box
        try {
            if (board[row - 1][column] == 1) {
                findViewById<TextView>(boxList[row - 1][column]).setBackgroundColor(Color.BLACK)
            } else {
                findViewById<TextView>(boxList[row - 1][column]).setBackgroundColor(Color.WHITE)
            }
        }catch (e: Exception) {}




    }



}


