package com.example.lorente_exer3_b9l

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var numOfClicks = 0
    private lateinit var showCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showCount = findViewById<TextView>(R.id.clickNumber)

        // Array for checking if a tile is lit; 1 for lit and 0 for unlit
        var board : Array<Array<Int>> = arrayOf(
             arrayOf(1,1,1,1,1),
             arrayOf(1,1,1,1,1),
             arrayOf(1,1,1,1,1),
             arrayOf(1,1,1,1,1),
             arrayOf(1,1,1,1,1)
        )

        // List for iterating through the ID of the textview tiles
        val boxList: List<List<Int>> = listOf(
            listOf(R.id.tile_11, R.id.tile_12, R.id.tile_13, R.id.tile_14, R.id.tile_15),
            listOf(R.id.tile_21, R.id.tile_22, R.id.tile_23, R.id.tile_24, R.id.tile_25),
            listOf(R.id.tile_31, R.id.tile_32, R.id.tile_33, R.id.tile_34, R.id.tile_35),
            listOf(R.id.tile_41, R.id.tile_42, R.id.tile_43, R.id.tile_44, R.id.tile_45),
            listOf(R.id.tile_51, R.id.tile_52, R.id.tile_53, R.id.tile_54, R.id.tile_55)
        )

        // Setting Listeners
        findViewById<Button>(R.id.change_button).setOnClickListener{
            changeNickname(it)
        }

        findViewById<TextView>(R.id.nickname_text).setOnClickListener{
            updateNickname()
        }

        setListeners(board,boxList)

        findViewById<Button>(R.id.retry_button).setOnClickListener{
            retry(board, boxList)
        }


    }


    private fun changeNickname(view: View) {
        val editText = findViewById<EditText>(R.id.editText)
        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)

        nicknameTextView.text = editText.text


        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

        editText.visibility = View.GONE
        view.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE
    }

    private fun setListeners(board: Array<Array<Int>>, boxList: List<List<Int>>) {

        for (row: Int in (0..4)) {
            for(column: Int in (0..4)) {
                findViewById<TextView>(boxList[row][column]).setOnClickListener {
                    changeColor(it, board, boxList,row,column)
                }
            }
        }
    }

    private fun changeColor(view: View, board: Array<Array<Int>>, boxList: List<List<Int>>, row : Int, column: Int) {
        if(board[row][column] == 1) {
            view.setBackgroundColor(Color.BLACK)
            board[row][column] = 0
            flipLights(board,boxList,row,column)
        }
        else{
            view.setBackgroundColor(Color.WHITE)
            board[row][column] = 1
            flipLights(board,boxList,row,column)
        }

        var win = winChecker(board)
        if(win == 1) {
            for(i in 0..4) {
                for(j in 0..4) {
                    findViewById<TextView>(boxList[i][j]).visibility = View.INVISIBLE
                }
            }
            findViewById<TextView>(R.id.retry_button).visibility = View.GONE
            findViewById<TextView>(R.id.win_text).visibility = View.VISIBLE

        }

        numOfClicks++

        if(showCount != null) {
            showCount.setText(numOfClicks.toString())
        }


    }

    private fun flipLights(board: Array<Array<Int>>, boxList: List<List<Int>>, row: Int, column: Int) {

        // For the right box
        try {
            if (board[row][column + 1] == 1) {
                findViewById<TextView>(boxList[row][column + 1]).setBackgroundColor(Color.BLACK)
                board[row][column + 1] = 0
            } else {
                findViewById<TextView>(boxList[row][column + 1]).setBackgroundColor(Color.WHITE)
                board[row][column + 1] = 1
            }
        }catch (e: Exception) {}

        // For Left Box
        try {
            if (board[row][column - 1] == 1) {
                findViewById<TextView>(boxList[row][column - 1]).setBackgroundColor(Color.BLACK)
                board[row][column - 1] = 0
            } else {
                findViewById<TextView>(boxList[row][column - 1]).setBackgroundColor(Color.WHITE)
                board[row][column - 1] = 1
            }
        }catch (e: Exception) {}

        // For bottom box
        try {
            if (board[row + 1][column] == 1) {
                findViewById<TextView>(boxList[row + 1][column]).setBackgroundColor(Color.BLACK)
                board[row + 1][column] = 0
            } else {
                findViewById<TextView>(boxList[row + 1][column]).setBackgroundColor(Color.WHITE)
                board[row + 1][column] = 1
            }
        }catch (e: Exception) {}

        // For Top Box
        try {
            if (board[row - 1][column] == 1) {
                findViewById<TextView>(boxList[row - 1][column]).setBackgroundColor(Color.BLACK)
                board[row - 1][column] = 0
            } else {
                findViewById<TextView>(boxList[row - 1][column]).setBackgroundColor(Color.WHITE)
                board[row - 1][column] = 1
            }
        }catch (e: Exception) {}

    }

    private fun retry(board: Array<Array<Int>>, boxList: List<List<Int>>) {
        for(row: Int in 0..4) {
            for(column: Int in 0..4) {
                board[row][column] = 1
                findViewById<TextView>(boxList[row][column]).setBackgroundColor(Color.WHITE)
            }
        }

        numOfClicks = 0
        showCount.setText(numOfClicks.toString())
    }

    private fun updateNickname() {
        val editText = findViewById<EditText>(R.id.editText)
        editText.visibility = View.VISIBLE

        editText.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)

        findViewById<Button>(R.id.change_button).visibility = View.VISIBLE

    }

    private fun winChecker(board: Array<Array<Int>>): Int {
        for(i in 0..4) {
            for(j in 0..4) {
                if(board[i][j] == 1) {
                    return 0
                }
            }
        }
        return 1
    }
}


